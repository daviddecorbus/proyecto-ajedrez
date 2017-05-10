package com.telefonica.first.tableroprueba;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ExpandableListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.LinkedHashMap;

public class ListadoMenu extends AppCompatActivity{

    private LinkedHashMap<String, InformacionGrupo> mapa = new LinkedHashMap<>();
    private ArrayList<InformacionGrupo> listaGrupos = new ArrayList<>();

    private AdaptadorMenu adaptadorMenu;
    private ExpandableListView listaExpandible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_menus);

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
                i.putExtra("tipo",cabecera.getNombre());
                i.putExtra("nivel",cabecera.getSubmenus());
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

        añadirSubmenu("Jaque Mate","Fácil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Jaque Mate","Medio",R.mipmap.ic_launcher_round);
        añadirSubmenu("Jaque Mate","Difícil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Ataque Doble","Fácil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Ataque Doble","Medio",R.mipmap.ic_launcher_round);
        añadirSubmenu("Ataque Doble","Difícil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Clavada","Fácil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Clavada","Medio",R.mipmap.ic_launcher_round);
        añadirSubmenu("Clavada","Difícil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Eliminación de la Defensa","Fácil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Eliminación de la Defensa","Medio",R.mipmap.ic_launcher_round);
        añadirSubmenu("Eliminación de la Defensa","Difícil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Rayos X","Fácil",R.mipmap.ic_launcher_round);
        añadirSubmenu("Rayos X","Medio",R.mipmap.ic_launcher_round);
        añadirSubmenu("Rayos X","Difícil",R.mipmap.ic_launcher_round);

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

}