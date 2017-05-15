package com.telefonica.first.tableroprueba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListadoMenu extends AppCompatActivity{
    private String idioma;
    private LinkedHashMap<String, InformacionGrupo> mapa = new LinkedHashMap<>();
    private ArrayList<InformacionGrupo> listaGrupos = new ArrayList<>();

    private AdaptadorMenu adaptadorMenu;
    private ExpandableListView listaExpandible;


    /**
     * Aplicar misma letra a toda la app
     */

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    /**
     * Menu de Opciones
     * @param menu Menu
     * @return true o false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracion, menu);
        return true;
    }

    /**
     * Opciones del Menu
     * @param item Opcion del menu
     * @return true o false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Configuracion.class);
            SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
            String admin = pre.getString("admin", "no");
            intent.putExtra("admin",admin);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
        idioma = pre.getString("idioma", "es");
        Lenguaje.setLocale(this, idioma); // Inicializa el idioma
        setTitle(getString(R.string.tipoEjercicios));
        setContentView(R.layout.lista_menus);
        //setLenguaje(lenguaje); //Cambia el Idioma
        //Añade datos a la lista expandible
        cargarDatos();

        //Referencia la lista expandible
        listaExpandible = (ExpandableListView) findViewById(R.id.listaMenus);
        // Crea un adaptador con los datos
        adaptadorMenu = new AdaptadorMenu(this, listaGrupos);
        //Añade un adaptador a la lista
        listaExpandible.setAdapter(adaptadorMenu);

        //Expande todos los grupos
       // expandirGrupos();

        //Evento OnClick de los grupo
        listaExpandible.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Obtiene la cabecera del grupo
                InformacionGrupo cabecera = listaGrupos.get(groupPosition);
                //Obtiene la informacion de un submenu
                ChildInfo subCabecera =  cabecera.getSubmenus().get(childPosition);
                Intent i = new Intent(getApplicationContext(),ListaEjercicios.class);
                String tipo = cabecera.getNombre();
                String nivel = subCabecera.getNombre();
                if(idioma.equals("en") || idioma.equals("es")){
                    switch (cabecera.getNombre()){
                        case "Checkmate":
                            tipo = "Jaque Mate";
                            break;
                        case "Double attack":
                            tipo = "Ataque Doble";
                            break;
                        case "Nailed":
                            tipo = "Clavada";
                            break;
                        case "Elimination of Defense":
                            tipo = "Eliminacion de la Defensa";
                            break;
                        case "X-rays":
                            tipo = "Rayos X";
                            break;

                    }

                    switch (subCabecera.getNombre()){
                        case "Easy":
                            nivel = "Facil";
                            break;
                        case "Medium":
                            nivel = "Medio";
                            break;
                        case "Difficult":
                            nivel = "Dificil";
                            break;
                    }
                }


                i.putExtra("tipo",tipo);
                i.putExtra("nivel",nivel);
                startActivity(i);
                return true;
            }
        });
        //Evento Onclick de las Cabeceras
        listaExpandible.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //Obtiene la cabecera del grupo
                //InformacionGrupo cabecera = listaGrupos.get(groupPosition);
                    agruparGrupos(groupPosition);
                    //expandirGrupos(groupPosition);
                return true;
            }
        });



    }

    /**
     * Expande todos los grupos
     */
    private void expandirGrupos(int groupPosition) {
        int cantidad = adaptadorMenu.getGroupCount();
        for (int i = 0; i < cantidad; i++){
            if(groupPosition == i) {
                listaExpandible.expandGroup(i);
            }
        }
    }

    /**
     * Agrupa y desagrupa los grupos
     */
    private void agruparGrupos(int groupPosition) {
        int cantidad = adaptadorMenu.getGroupCount();
        for (int i = 0; i < cantidad; i++){
            if (listaExpandible.isGroupExpanded(groupPosition)){
                listaExpandible.collapseGroup(i);
            }else {
                if(groupPosition != i) {
                    listaExpandible.collapseGroup(i);
                }
                else {
                    listaExpandible.expandGroup(i);
                }
            }

        }

    }

    /**
     * Inicializa los datos
     */
    private void cargarDatos(){
        añadirSubmenu(getString(R.string.jaque),getString(R.string.nivel_facil),R.drawable.ic_facil);
        añadirSubmenu(getString(R.string.jaque),getString(R.string.nivel_medio),R.drawable.ic_medio);
        añadirSubmenu(getString(R.string.jaque),getString(R.string.nivel_dificil),R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.ataque),getString(R.string.nivel_facil),R.drawable.ic_facil);
        añadirSubmenu(getString(R.string.ataque),getString(R.string.nivel_medio),R.drawable.ic_medio);
        añadirSubmenu(getString(R.string.ataque),getString(R.string.nivel_dificil),R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.clavada),getString(R.string.nivel_facil),R.drawable.ic_facil);
        añadirSubmenu(getString(R.string.clavada),getString(R.string.nivel_medio),R.drawable.ic_medio);
        añadirSubmenu(getString(R.string.clavada),getString(R.string.nivel_dificil),R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.defensa),getString(R.string.nivel_facil),R.drawable.ic_facil);
        añadirSubmenu(getString(R.string.defensa),getString(R.string.nivel_medio),R.drawable.ic_medio);
        añadirSubmenu(getString(R.string.defensa),getString(R.string.nivel_dificil),R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.rayos),getString(R.string.nivel_facil),R.drawable.ic_facil);
        añadirSubmenu(getString(R.string.rayos),getString(R.string.nivel_medio),R.drawable.ic_medio);
        añadirSubmenu(getString(R.string.rayos),getString(R.string.nivel_dificil),R.drawable.ic_dificil);

    }


    /**
     * Añade los datos
     * @param grupo Grupo al que pertenece
     * @param nombre nombre
     * @param imagen imagen
     * @return la posición del grupo
     */
    private int añadirSubmenu(String grupo, String nombre,int imagen){

        int posicion = 0;

        //Comprueba si el grupo existe
        InformacionGrupo cabecera = mapa.get(grupo);
        //Si el grupo no existe
        if(cabecera == null){
            cabecera = new InformacionGrupo();
            cabecera.setNombre(grupo);
            mapa.put(grupo, cabecera);
            listaGrupos.add(cabecera);
        }

        //Obtiene los submenus del grupo
        ArrayList<ChildInfo> submenus = cabecera.getSubmenus();
        //Tamaño de los submenus
        int cantidad = submenus.size();
        cantidad++;

        //Crea un nuevo submenu y lo añade al grupo
        ChildInfo submenu = new ChildInfo();
        submenu.setImagen(imagen);
        //submenu .setTexto(String.valueOf(cantidad));
        submenu .setNombre(nombre);
        submenus.add(submenu);
        cabecera.setSubmenus(submenus);

        //Busca el grupo en la posición de la lista
        posicion = listaGrupos.indexOf(cabecera);
        return posicion;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(R.string.salirJuego);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}