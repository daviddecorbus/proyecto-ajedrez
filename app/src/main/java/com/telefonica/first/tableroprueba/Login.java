package com.telefonica.first.tableroprueba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.telefonica.first.tableroprueba.TableroEjercicio.ancho;
import static com.telefonica.first.tableroprueba.TableroEjercicio.dpi;


public class Login extends  Fragment {
private String email;
private String password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        return inflater.inflate(R.layout.activity_login,container,false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* LinearLayout linear = (LinearLayout) getView().findViewById(R.id.linear);
       linear.getLayoutParams().width = ancho-(ancho/3);*/
        // Asigna el método login() al botón del activity_login
        ajustarTamanos();
        Button boton = (Button) view.findViewById(R.id. btEnviar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }});
    }
    public void ajustarTamanos(){
        TextView text = (TextView)  getView().findViewById(R.id.login);
        text.setTextSize(TamañoLetra.tamañoLetraLogin(ancho,dpi));
        TextView text2 = (TextView)  getView().findViewById(R.id.tvEmail);
        text2.setTextSize(TamañoLetra.tamañoLetraEmailPassword(ancho,dpi));
        TextView text3 = (TextView)  getView().findViewById(R.id.tvTexto);
        text3.setTextSize(TamañoLetra.tamañoLetraEmailPassword(ancho,dpi));

        EditText text4 = (EditText) getView().findViewById(R.id.etEmail);
        text4.setTextSize(TamañoLetra.tamañoLetraEditText(ancho,dpi));
        EditText text5 = (EditText) getView().findViewById(R.id.etTexto);
        text5.setTextSize(TamañoLetra.tamañoLetraEditText(ancho,dpi));

        Button boton = (Button) getView().findViewById(R.id.btEnviar);
        boton.setTextSize(TamañoLetra.tamañoLetraBoton(ancho,dpi));
       // boton.getLayoutParams().width=TamañoLetra.tamañoAnchoBoton(ancho,dpi);
        boton.getLayoutParams().height=TamañoLetra.tamañoAltoBoton(ancho,dpi);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        LinearLayout linear1 = (LinearLayout) getView().findViewById(R.id.linearEmail);
        params.setMargins(TamañoLetra.margenesLinearIzquierda(ancho,dpi),TamañoLetra.margenesLinearTop(ancho,dpi),0,0);
        linear1.setLayoutParams(params);


    }
    /**
     * Comrpueba si hay conexion
     * @return si hay conexion
     */
    public boolean hayConexion() {

        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    /**
     * Recupera los datos de los EditText y revisa que sean datos válidos (si son válidos llama al AsyncTask crearLogin si no muestra
      muestra un alert con mensaje al usuario
     */
    public void login(){
        EditText mail = (EditText) getView().findViewById(R.id.etEmail);
        email= String.valueOf(mail.getText()).trim();
        System.out.println("el mail es "+email);
        EditText pass = (EditText) getView().findViewById(R.id.etTexto);
        password= String.valueOf(pass.getText()).trim();
        System.out.println("el password es "+password);
        if (!email.trim().equalsIgnoreCase("") && email.contains("@") && email.contains(".")){
            if (!password.trim().equalsIgnoreCase("")){
                if(hayConexion()){
                    CrearLogin com = new CrearLogin();
                    String parametro = "password=" + password.trim() + "&correo=" + email.trim();
                    com.execute("http://s682530403.mialojamiento.es/login2.php",parametro);
                }
                else {
                    Toast.makeText(getActivity(), R.string.conectividad, Toast.LENGTH_SHORT).show();
                }

            }else{
                System.out.println("introduce un password");
                Toast.makeText(getActivity(), R.string.falta_contraseña, Toast.LENGTH_SHORT).show();
            }
        }else{
            System.out.println("introduce un email válido");
            Toast.makeText(getActivity(), R.string.falta_correo, Toast.LENGTH_SHORT).show();
        }

    }
    /**
     * Llama a login2.php para revisar estado del email y la contraseña. Nos devuelve un código y depende del código
     * mostramos mensaje al usuario
     */
    private class CrearLogin extends AsyncTask<String, Void, String> {

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
                    System.out.println("el codigo cadenajson "+cadenaJson);
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            return cadenaJson;

        }

        @Override
        protected void onPreExecute() {
            //procesoEspera.show();
        }

        @Override
        protected void onPostExecute(String result) {
            // procesoEspera.dismiss();4

            try {
                JSONArray jarray=new JSONArray(result);
                String codigo="";
                for(int i=0;i<jarray.length();i++) {
                    JSONObject job = jarray.getJSONObject(i);
                    codigo = job.getString("codigo");
                    System.out.println("el codigo es "+codigo);
                    //String estado = job.getString("estado");
                }

                if(codigo.equalsIgnoreCase("-1")){
                    Toast.makeText(getActivity(),R.string.erronea,Toast.LENGTH_SHORT).show();
                }
                else if(codigo.equalsIgnoreCase("1")){
                    Toast.makeText(getActivity(), R.string.registrado, Toast.LENGTH_SHORT).show();
                }
                else if (codigo.equalsIgnoreCase("2")) {
                    Toast.makeText(getActivity(), R.string.usuario, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("com.telefonica.first.tableroprueba_preferences", MODE_PRIVATE).edit();
                    editor.putString("correo", email);
                    editor.putString("admin","no");
                    editor.apply();
                   TableroEjercicio.correo = email;
                    Intent intent = new Intent(getActivity(),ListadoMenu.class);
                    startActivity(intent);
                }
                else if (codigo.equalsIgnoreCase("3")) {
                    Toast.makeText(getActivity(), R.string.usuario, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("com.telefonica.first.tableroprueba_preferences", MODE_PRIVATE).edit();
                    editor.putString("correo", email);
                    editor.putString("admin","si");
                    editor.apply();
                    TableroEjercicio.correo = email;
                    Intent intent = new Intent(getActivity(),ListadoMenu.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), R.string.error2, Toast.LENGTH_SHORT).show();
                }
            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }
    }
}