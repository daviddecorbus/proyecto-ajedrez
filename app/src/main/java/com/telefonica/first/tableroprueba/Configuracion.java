package com.telefonica.first.tableroprueba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.telefonica.first.tableroprueba.ConversorCasillas.conversor;


public class Configuracion extends PreferenceActivity  {

    private Context mContext;
    private PreferenceScreen sugerencia;
    private CheckBoxPreference guia;
   private ListPreference lenguaje;
   private CheckBoxPreference coordenadas;
    private Locale lenguageActual;
    private PreferenceScreen salir;
    private PreferenceScreen admin;
   // private boolean guiaActivada;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext(); //Contexto
        addPreferencesFromResource(R.xml.configuracion); //Carga el xml de configuracio

        Bundle datos=getIntent().getExtras();

        PreferenceManager.setDefaultValues(Configuracion.this, R.xml.configuracion, false); //Pone los valores por defecto
        final PreferenceScreen preferencias = getPreferenceScreen(); // Obtiene las preferencias

        PreferenceCategory notificationsCategory = (PreferenceCategory) preferencias.findPreference("categoria3");


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

        lenguaje = (ListPreference) preferencias.findPreference("idioma");
        lenguaje.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.equals("Español") || newValue.equals("Spanish") ){
                    preference.getEditor().putString("idioma","es");
                    Lenguaje.setLocale(mContext,"es");
                    setLenguaje("es");
            }
                else {
                    preference.getEditor().putString("idioma","en");
                    Lenguaje.setLocale(mContext,"en");
                    finish();
                   setLenguaje("en");
                }
                return true;
            }
        });

        salir = (PreferenceScreen) preferencias.findPreference("cerrar");
        salir.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                preference.getEditor().putString("correo","").apply();
                finish();
                Intent i = new Intent(getApplicationContext(),SplashScreen.class);
                startActivity(i);
                return true;
            }
        });

        admin = (PreferenceScreen) preferencias.findPreference("admin");
        admin.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                finish();
                Intent i = new Intent(getApplicationContext(),CrearJugadas.class);
                startActivity(i);
                return true;
            }
        });

        sugerencia = (PreferenceScreen) preferencias.findPreference("sugerencia");
        sugerencia.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
              crearAlerta();
                return true;
            }
        });

        if(datos.getString("admin").equalsIgnoreCase("no")){
            notificationsCategory.setTitle("");
            notificationsCategory.removePreference(admin);
        }

    }
    private void crearAlerta(){


        LayoutInflater li = LayoutInflater.from(this);
        View vista = li.inflate(R.layout.sugerencia, null);

        final AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle(R.string.titulo_sugerencia);
        dialogo.setView(vista);


        final EditText cajaSugerencia = (EditText) vista.findViewById(R.id.edSugerencias);

        dialogo
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if(!cajaSugerencia.getText().equals("")){
                            MandarSugerencia com = new MandarSugerencia();
                            String parametro = "email=" + TableroEjercicio.correo + "&texto=" + cajaSugerencia.getText().toString().replaceAll(" ", "%20");
                            com.execute("http://caissamaister.esy.es/Sugerencia.php",parametro);
                        }
                    }

                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.dismiss();
                    }

                });

        AlertDialog alertDialogAndroid = dialogo.create();
        alertDialogAndroid.show();
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

    private void setLenguaje(String lenguage) {

        lenguageActual = new Locale(lenguage);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = lenguageActual;
        res.updateConfiguration(conf, dm);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        Intent r = new Intent(this, ListadoMenu.class);
        startActivity(r);
        overridePendingTransition(0, 0);
    }
}