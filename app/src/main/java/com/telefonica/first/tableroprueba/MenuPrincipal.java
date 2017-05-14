package com.telefonica.first.tableroprueba;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import static com.telefonica.first.tableroprueba.TableroEjercicio.ancho;
import static com.telefonica.first.tableroprueba.TableroEjercicio.largo;
import static com.telefonica.first.tableroprueba.TamañoLetra.tamañoLetra;

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

}
