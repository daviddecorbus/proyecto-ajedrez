package com.telefonica.first.tableroprueba;

import android.os.AsyncTask;

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

/**
 * Created by David Garrido on 12/05/2017.
 */

public class GrabarPartida extends AsyncTask<String, Void,String> {

        public GrabarPartida(  ){

        }
        @Override
        protected String doInBackground(String... params) {
            String echo="";
            try{
                //monta la url con la dirección y parámetro de envío
                // URL url=new URL(params[0]+"?json="+params[1]);
                URL url=new URL(params[0]+"?"+params[1]);
                System.out.println("URL: " + url);
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
