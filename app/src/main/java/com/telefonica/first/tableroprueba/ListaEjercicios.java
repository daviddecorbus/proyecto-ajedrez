package com.telefonica.first.tableroprueba;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class ListaEjercicios extends AppCompatActivity {
    ListView listaEjercicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_ejercicios); //Carga el Layout
        Bundle bundle = getIntent().getExtras();
        //Recuperamos el tipo y el nivel
        String tipo = bundle.getString("tipo");
        String nivel = bundle.getString("nivel");
        listaEjercicios =(ListView)this.findViewById(R.id.listaEjercicios);
        ComunicacionTask com=new ComunicacionTask();
        com.execute("");
    }
    private class ComunicacionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cadenaJson="";
            try{
                URL url=new URL(params[0]);
                URLConnection con=url.openConnection();
                //recuperacion de la respuesta JSON
                String s;
                InputStream is=con.getInputStream();
                //utilizamos UTF-8 para que interprete
                //correctamente las ñ y acentos
                BufferedReader bf=new BufferedReader(		new InputStreamReader(is, Charset.forName("UTF-8")));
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
            String[] datosCiudad=null;
            try{
                //creamos un array JSON a partir de la cadena recibida
                JSONArray jarray=new JSONArray(result);
                //creamos el array de String con el tamaño
                //del array JSON
                datosCiudad=new String[jarray.length()];
                for(int i=0;i<jarray.length();i++){
                    JSONObject job=jarray.getJSONObject(i);

                    int habitantes=job.getInt("Centro")+			job.getInt("Norte")+			job.getInt("Urbanizaciones");
                    datosCiudad[i]=job.getString("Año")+			" - "+habitantes;
                }
                cargarLista(datosCiudad);
            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }
        private void cargarLista(String[] datos){
            //creamos un arrayadapter con los datos del array
            // y lo asignamos al ListView
            ArrayAdapter<String> adp=new 	ArrayAdapter<String>(ListaEjercicios.this, 	android.R.layout.simple_list_item_1,datos);
           // lvCiudades.setAdapter(adp);
            listaEjercicios.setAdapter(adp);
        }
    }

}

