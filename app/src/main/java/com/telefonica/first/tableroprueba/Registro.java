package com.telefonica.first.tableroprueba;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class Registro extends Fragment{
    private String email;
    private String password;
    private String password2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        return inflater.inflate(R.layout.activity_registro,container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Asigna el método registro() al botón del activity_registro
        Button boton = (Button) getView().findViewById(R.id. registro);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro();
            }});
    }
    /**
     * Recupera los datos de los EditText y revisa que sean datos válidos (si son válidos llama al AsyncTask crearRegistro si no muestra
     muestra un alert con mensaje al usuario
     */
    public void registro(){
        EditText mail = (EditText) getView().findViewById(R.id.Email);
        email= String.valueOf(mail.getText());
        System.out.println("el mail es "+email);
        EditText pass = (EditText) getView().findViewById(R.id.password);
        password= String.valueOf(pass.getText());
        EditText pass2 = (EditText) getView().findViewById(R.id.password2);
        password2= String.valueOf(pass2.getText());
        System.out.println("el password es "+password2);
        if(!email.trim().equalsIgnoreCase("") && email.contains("@") && email.contains(".")){
            if(password.trim().equalsIgnoreCase(password2.trim()) && !password.trim().equalsIgnoreCase("")){
                CrearRegistro com = new CrearRegistro();
                String parametro = "password=" + password.trim() + "&correo=" + email.trim();
                com.execute("http://caissamaister.esy.es/login.php",parametro);

            }else{
                Toast.makeText(getActivity(), "Introduce la misma contraseña", Toast.LENGTH_SHORT).show();
                System.out.println("introduce la misma contraseña");
            }
        }else{
            Toast.makeText(getActivity(), "Introduce un email válido", Toast.LENGTH_SHORT).show();
            System.out.println("introduce un email válido");
        }


    }

    /**
     * Llama a login.php para crear un nuevo regitro de usuario. Nos devuelve un código y depende del código
     * mostramos mensaje al usuario
     */

    private class CrearRegistro extends AsyncTask<String, Void, String> {

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
                    Toast.makeText(getActivity(), "El email ya está registrado", Toast.LENGTH_SHORT).show();
                }
                else if(codigo.equalsIgnoreCase("1")){
                    Toast.makeText(getActivity(), "Registro realizado", Toast.LENGTH_SHORT).show();
                    TableroEjercicio.correo = email;
                    Intent intent = new Intent(getActivity(),ListadoMenu.class);
                    startActivity(intent);
                }
                else if (codigo.equalsIgnoreCase("2")) {
                    Toast.makeText(getActivity(), "El email y la contraseña ya están registrados", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Se ha producido un error desconocido", Toast.LENGTH_SHORT).show();
                }
            }
            catch(JSONException ex){
                ex.printStackTrace();
            }
        }
    }
}