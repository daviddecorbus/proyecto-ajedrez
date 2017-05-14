package com.telefonica.first.tableroprueba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.telefonica.first.tableroprueba.TableroEjercicio.ancho;
import static com.telefonica.first.tableroprueba.TableroEjercicio.largo;
import static com.telefonica.first.tableroprueba.TamañoLetra.tamañoLetra;

public class SplashScreen extends AppCompatActivity {
     Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tamaño();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()

                .setDefaultFontPath("font/apercubolditalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_splash_screen);


        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MenuPrincipal.class);
                startActivity(intent);
                finish();

            }
        }, 1000);

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
