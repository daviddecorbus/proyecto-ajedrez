package com.telefonica.first.tableroprueba;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    private final int numPaginas = 2; //Número de Páginas
    private AdaptadorViewPager adaptador;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String [] titulosPagina = new String[numPaginas];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_prinicipal);
        titulosPagina[0] = getString(R.string.logearse);
        titulosPagina[1] = getString(R.string.registrarse);

        //Iniciamos la barra de herramientas
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Iniciamos la barra de secciones
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // Añadimos las 2 tabs de las secciones.
        // Le damos modo "fixed" para que todas las tabs tengan el mismo tamaño. También le asignamos una gravedad centrada.
        // tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        for (int i = 0; i < numPaginas; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }


        // Iniciamos el viewPager.
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creamos el adaptador, al cual le pasamos por parámetros al gestor de Fragmentos
        // el nº de tabs o secciones que hemos creado.
        adaptador = new AdaptadorViewPager(getSupportFragmentManager(), numPaginas,titulosPagina);

        //Vinculamos el adaptador al viewpager
        viewPager.setAdapter(adaptador);

        //Vinculamos el viewpager con el control de tabs para sincronizar ambos.
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("¿Quieres salir del juego?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
//if user pressed "yes", then he is allowed to exit from application
                finishAffinity();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
//if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
