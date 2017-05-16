package com.telefonica.first.tableroprueba;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class MandarSugerencia extends AsyncTask<String, Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String echo="";
            try{
                //monta la url con la dirección y parámetro de envío
                // URL url=new URL(params[0]+"?json="+params[1]);
                URL url=new URL(params[0]+"?"+params[1]);
                System.out.println("URL de maister: " + url);
                URLConnection con=url.openConnection();
                String s;
                InputStream is=con.getInputStream();
                //utilizamos UTF-8 para que interprete correctamente las ñ y acentos
                BufferedReader bf=new BufferedReader(
                        new InputStreamReader(is, Charset.forName("UTF-8")));
                while((s=bf.readLine())!=null){

                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }

            return echo;
        }

        @Override
        protected void onPostExecute(String result) {
        }


}
