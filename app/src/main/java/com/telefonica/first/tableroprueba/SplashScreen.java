package com.telefonica.first.tableroprueba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import static com.telefonica.first.tableroprueba.TableroEjercicio.ancho;
import static com.telefonica.first.tableroprueba.TableroEjercicio.largo;

public class SplashScreen extends AppCompatActivity {
     Handler handler;
    String correo;
    int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tamaño();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()

                .setDefaultFontPath("font/apercubold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        setContentView(R.layout.activity_splash_screen);


        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); //Inicializa las preferencias
                correo = pre.getString("correo", "");
                String idioma = pre.getString("idioma", "es");
                Lenguaje.setLocale(SplashScreen.this, idioma); // Inicializa el idioma

                Intent intent;
                if(correo.equals("")){
                    intent = new Intent(SplashScreen.this, MenuPrincipal.class);
                }
                else {
                    TableroEjercicio.correo=correo;
                    intent = new Intent(SplashScreen.this, ListadoMenu.class);
                }
                startActivity(intent);
                finish();

            }
        }, 2000);

    }
    public void truco(View v){
        contador++;
        if(contador==10){
            Toast.makeText(this, "Los maister son Bea, Adri, Edu y David", Toast.LENGTH_SHORT).show();
        }
    }
    public void tamaño() { //define el valor de ancho, largo y dpi de la pantalla del dispositivo
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //Pantalla
        TableroEjercicio.ancho = display.getWidth(); //Anchura de la pantalla
        System.out.println("el ancho es "+ancho);
        largo = display.getHeight(); //Altura de la pantalla
        System.out.println("el largo es "+largo);
        //Saca los dpi para mandar al método tamañoLetra y que nos devuelva un tamaño dependiendo de la relación entre ancho y dpi
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        TableroEjercicio.dpi = metrics.densityDpi;
    }

}
