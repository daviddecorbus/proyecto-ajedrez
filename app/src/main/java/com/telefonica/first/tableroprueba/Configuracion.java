package com.telefonica.first.tableroprueba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Configuracion extends PreferenceActivity  {

    private Context mContext;
    private PreferenceScreen ayuda;
    private CheckBoxPreference guia;
   // private CheckBoxPreference sonido;
   private CheckBoxPreference coordenadas;
   // private boolean guiaActivada;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext(); //Contexto
        addPreferencesFromResource(R.xml.configuracion); //Carga el xml de configuracio

        PreferenceManager.setDefaultValues(Configuracion.this, R.xml.configuracion, false); //Pone los valores por defecto
        final PreferenceScreen preferencias = getPreferenceScreen(); // Obtiene las preferencias

       guia = (CheckBoxPreference) preferencias.findPreference("guia");
        guia.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
               preference.getEditor().putBoolean("guia",(boolean) newValue);
                return true;
            }
        });

        coordenadas = (CheckBoxPreference) preferencias.findPreference("coordenadas");
        guia.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.getEditor().putBoolean("coordenadas",(boolean) newValue);
                return true;
            }
        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Crea la toolbar y la flecha de retroceso
        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        bar.setTitleTextColor(Color.WHITE);
        bar.setSubtitleTextColor(Color.WHITE);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandarResultado();
            }
        });
    }

    /**
     * Manda el resultado
     */
    public void mandarResultado(){
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    /**
     * Tecla de Retroceso
     * @param keyCode Código del tecla
     * @param event //Evento
     * @return true o false
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            mandarResultado();
        }
        return super.onKeyDown(keyCode, event);
    }
}