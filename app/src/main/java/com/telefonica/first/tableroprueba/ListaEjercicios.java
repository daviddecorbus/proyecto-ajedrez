package com.telefonica.first.tableroprueba;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListaEjercicios extends AppCompatActivity {
    GridView listaEjercicios;
    ProgressDialog procesoEspera;
    AdaptadorEjercicios adaptadorEjercicios;
    Ejercicio [] ejercicios;
    Ejercicio [] ejercicios_usuario;
    String tipo;
    String nivel;
    String idioma;




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    /**
     * Menu de Opciones
     * @param menu Menu
     * @return true o false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracion, menu);
        return true;
    }

    /**
     * Opciones del Menu
     * @param item Opcion del menu
     * @return true o false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Configuracion.class);
            SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
            String admin = pre.getString("admin", "no");
            intent.putExtra("admin",admin);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(hayConexion()){
            ComunicacionTask com = new ComunicacionTask();
            String parametro = "tipo=" + tipo + "&nivel=" + nivel;
            com.execute("http://s682530403.mialojamiento.es/peticionEjercicios.php",parametro);
        }
        else {
            Toast.makeText(this, getString(R.string.error_cargar), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.tipoEjercicios));
        setContentView(R.layout.lista_ejercicios); //Carga el Layout
        Bundle bundle = getIntent().getExtras();
        //Recuperamos el tipo y el nivel
        tipo = bundle.getString("tipo");
        nivel = bundle.getString("nivel");
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
        idioma = pre.getString("idioma", "es");
        if(tipo.split(" ").length >=2){
           tipo = tipo.replaceAll(" ","-");

        }
        listaEjercicios =(GridView) this.findViewById(R.id.listaEjercicios);
        procesoEspera =  new ProgressDialog(ListaEjercicios.this);
        procesoEspera.setMessage(getString(R.string.cargando));
        procesoEspera.setTitle(getString(R.string.titulo_cargando));
        if(hayConexion()){
            ComunicacionTask com = new ComunicacionTask();
            String parametro = "tipo=" + tipo + "&nivel=" + nivel;
            com.execute("http://s682530403.mialojamiento.es/peticionEjercicios.php",parametro);
        }
        else {
            Toast.makeText(this, getString(R.string.error_cargar), Toast.LENGTH_SHORT).show();
            finish();
        }

        listaEjercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ejercicio ejercicioActual = ejercicios[position];
                Intent i = new Intent(ListaEjercicios.this,TableroEjercicio.class);
                i.putExtra("ejercicio",ejercicioActual);
                 startActivity(i);
            }
        });
    }

    private boolean hayConexion() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    private class ComunicacionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cadenaJson="";
            try{
                //monta la url con la dirección y parámetro de envío
                // URL url=new URL(params[0]+"?json="+params[1]);
                URL url=new URL(params[0]+"?"+params[1]);
                System.out.println("URL: " + url);
                URLConnection con=url.openConnection();
                //recuperacion de la respuesta JSON
                String s;
                InputStream is=con.getInputStream();
                //utilizamos UTF-8 para que interprete correctamente las ñ y acentos
                BufferedReader bf=new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8")));
                while((s=bf.readLine())!=null){
                    cadenaJson+=s;
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            return cadenaJson;

        }

        @Override
        protected void onPreExecute() {
            procesoEspera.show();
        }

        @Override
        protected void onPostExecute(String result) {
           // procesoEspera.dismiss();
            int contador = 1;
            try{
                //creamos un array JSON a partir de la cadena recibida
                JSONArray jarray=new JSONArray(result);
                //creamos el array de String con el tamaño
                //del array JSON
                ejercicios = new Ejercicio[jarray.length()];
                for(int i=0;i<jarray.length();i++){
                    JSONObject job=jarray.getJSONObject(i);
                    int cantidad_movimientos =job.getInt("cantidad_movimientos");
                    String color = job.getString("color_inicial");
                    String movimientos = job.getString("movimientos");
                    String tablero = job.getString("tablero");
                    //String estado = job.getString("estado");
                    int id_nivel = Integer.parseInt(job.getString("id_nivel"));

                    String texto = job.getString("descripcion");
                    String textoIngles = job.getString("descripcionIngles");
                    int id = job.getInt("id");
                    if(idioma.equalsIgnoreCase("es"))
                    ejercicios[i]= new Ejercicio(convertirTablero(tablero.split("-")),cantidad_movimientos,color,movimientos.split(","),texto,contador,id,"sin-empezar",id_nivel);
                    else
                        ejercicios[i]= new Ejercicio(convertirTablero(tablero.split("-")),cantidad_movimientos,color,movimientos.split(","),textoIngles,contador,id,"sin-empezar",id_nivel);
                    contador++;
                }
                ComunicacionTask2 com = new ComunicacionTask2();
                String parametro = "tipo=" + tipo + "&nivel=" + nivel + "&correo=" + TableroEjercicio.correo;
                com.execute("http://s682530403.mialojamiento.es/peticionEjercicios2.php",parametro);
            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }


        private String[][] convertirTablero (String[] tablero){
            String [] [] temp = new String[8][8];
            int cantidad=0;

            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if(cantidad==tablero.length) break;
                    temp[i][j]=tablero[cantidad];
                    cantidad++;
                }
            }
            return  temp;
        }
    }
    private class ComunicacionTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cadenaJson="";
            try{
                //monta la url con la dirección y parámetro de envío
                // URL url=new URL(params[0]+"?json="+params[1]);
                URL url=new URL(params[0]+"?"+params[1]);
                System.out.println("URL ejercicios usuario: " + url);
                URLConnection con=url.openConnection();
                //recuperacion de la respuesta JSON
                String s;
                InputStream is=con.getInputStream();
                //utilizamos UTF-8 para que interprete correctamente las ñ y acentos
                BufferedReader bf=new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8")));
                while((s=bf.readLine())!=null){
                    cadenaJson+=s;
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            return cadenaJson;

        }

        @Override
        protected void onPostExecute(String result) {
            procesoEspera.dismiss();
            int contador = 1;
            try{
                //creamos un array JSON a partir de la cadena recibida
                JSONArray jarray=new JSONArray(result);
                //creamos el array de String con el tamaño
                //del array JSON
                ejercicios_usuario = new Ejercicio[jarray.length()];
                for(int i=0;i<jarray.length();i++){
                    JSONObject job=jarray.getJSONObject(i);
                    int cantidad_movimientos =job.getInt("cantidad_movimientos");
                    String color = job.getString("color_inicial");
                    String movimientos = job.getString("movimientos");
                    String tablero = job.getString("tablero");
                    String estado = job.getString("estado");
                    String texto = job.getString("descripcion");
                    String textoIngles = job.getString("descripcionIngles");
                    int id_nivel = Integer.parseInt(job.getString("id_nivel"));
                    int id = job.getInt("id");
                    ejercicios_usuario[i]= new Ejercicio(convertirTablero(tablero.split("-")),cantidad_movimientos,color,movimientos.split(","),texto,contador,id,estado,id_nivel);
                    contador++;
                }

                cargarLista();
            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }
        private void cargarLista(){
            for (int i = 0; i < ejercicios_usuario.length;i++) {
                int id = ejercicios_usuario[i].getId();
                String estado = ejercicios_usuario[i].getEstado();
                buscar(id,estado);
            }
            //creamos un arrayadapter con los datos del array
            // y lo asignamos al ListView
            adaptadorEjercicios =new AdaptadorEjercicios(ListaEjercicios.this,R.layout.lista_ejercicios,ejercicios);
            listaEjercicios.setAdapter(adaptadorEjercicios);
        }

        private void buscar(int id,String estado){
            for (int i = 0; i < ejercicios.length;i++){
                if(id == ejercicios[i].getId()){
                    ejercicios[i].setEstado(estado);
                }
            }
        }

        private String[][] convertirTablero (String[] tablero){
            String [] [] temp = new String[8][8];
            int cantidad=0;

            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if(cantidad==tablero.length) break;
                    temp[i][j]=tablero[cantidad];
                    cantidad++;
                }
            }
            return  temp;
        }
    }

}

