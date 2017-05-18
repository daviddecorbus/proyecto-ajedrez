package com.telefonica.first.tableroprueba;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListadoMenu extends AppCompatActivity{
    private String idioma;
    private LinkedHashMap<String, InformacionGrupo> mapa = new LinkedHashMap<>();
    private ArrayList<InformacionGrupo> listaGrupos = new ArrayList<>();

    private AdaptadorMenu adaptadorMenu;
    private ExpandableListView listaExpandible;
    private int posicion;


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

    public void uno(View view){
        String nivel = "";
        ImageView imagen = (ImageView) view;
        switch (imagen.getTag().toString()){
            case "1":
                nivel = "Facil";
                break;
            case "2":
                nivel = "Medio";
                break;
            case "3":
                nivel = "Dificil";
                break;
        }
        //Obtiene la cabecera del grupo
        InformacionGrupo cabecera = listaGrupos.get(posicion);

       // Toast.makeText(this, c, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),ListaEjercicios.class);

        String tipo =  cabecera.getNombre();

        if(idioma.equals("en") || idioma.equals("es")) {
            switch (tipo) {
                case "Mate":
                    tipo = "Jaque Mate";
                    break;
                case "Double attack":
                    tipo = "Ataque Doble";
                    break;
                case "Nailed":
                    tipo = "Clavada";
                    break;
                case "Defense Elimination":
                    tipo = "Eliminacion de la Defensa";
                    break;
                case "X-rays":
                    tipo = "Rayos X";
                    break;
                case "Eliminación de la Defensa":
                    tipo = "Eliminacion de la Defensa";
                    break;

            }

            i.putExtra("tipo", tipo);
            i.putExtra("nivel", nivel);
            startActivity(i);
        }
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
            startActivityForResult(intent,1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 1:
                if(resultCode == Activity.RESULT_OK){
                    //Devuelve valores de Preferencia
                    // boolean guia = data.getExtras().getBoolean("guiaActivada");
                    SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
                    String idioma = pre.getString("idioma", "es");
                    Lenguaje.setLocale(ListadoMenu.this, idioma); // Inicializa el idioma
                }
                break;

        }
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
                //InformacionGrupo cabecera = listaGrupos.get(groupPosition);
                //Obtiene la informacion de un submenu
               // ChildInfo subCabecera =  cabecera.getSubmenus().get(childPosition);


                return false;
            }
        });
        //Evento Onclick de las Cabeceras
        listaExpandible.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //Obtiene la cabecera del grupo
                posicion = groupPosition;
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
        añadirSubmenu(getString(R.string.jaque),R.drawable.ic_facil,R.drawable.ic_medio,R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.ataque),R.drawable.ic_facil,R.drawable.ic_medio,R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.clavada),R.drawable.ic_facil,R.drawable.ic_medio,R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.defensa),R.drawable.ic_facil,R.drawable.ic_medio,R.drawable.ic_dificil);
        añadirSubmenu(getString(R.string.rayos),R.drawable.ic_facil,R.drawable.ic_medio,R.drawable.ic_dificil);

    }


    /**
     * Añade los datos
     * @param grupo Grupo al que pertenece
     * @param imagen imagen
     * @return la posición del grupo
     */
    private int añadirSubmenu(String grupo, int imagen,int imagen2,int imagen3){

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
                SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String idioma = pre.getString("idioma", "es");
                Lenguaje.setLocale(ListadoMenu.this, idioma); // Inicializa el idioma
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