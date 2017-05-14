package com.telefonica.first.tableroprueba;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import static com.telefonica.first.tableroprueba.ConversorCasillas.conversor;


public class CrearJugadas extends AppCompatActivity {
    private static final int CONFIGURACION = 1;
    private String[][] tablero; //Un Array Multidimensional con el nombre de las piezas
    private int ancho = 0; //Ancho de la Pantalla
    private int largo = 0; //Largo de la Pantalla
    protected static Tablero tableroPiezas = new Tablero(); //Tablero
    private TableLayout tableroVisual; //Tablero Visual
    private TableLayout tablerodePiezas;// Tablero con todas las Piezas
    private ImageView recuadro; //Recuadro del tablero
    private String[] piezas = {"br","bn","bb","bk","bq","bb","bn","br",
            "bp","bp","bp","bp","bp","bp","bp","bp",
            "wr","wn","wb","wk","wq","wb","wn","wr",
            "wp","wp","wp","wp","wp","wp","wp","wp"};
    ImageView imagenMovida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba); //Carga el Layout
        tableroVisual = (TableLayout) findViewById(R.id.tablero); //Inicializa el tablero visual
        tablerodePiezas = (TableLayout) findViewById(R.id.piezas); //Inicializa el tablero de  piezzas
        recuadro = (ImageView) findViewById(R.id.imagerojo); //Cargar el recuadro del tablero
        recuperarTablero(); //Recupera los datos del tablero
        tableroPiezas.cargarTableroJugada(tablero);//Cargar el tablero
        tamañoTabla(); // Ajusta el tablero
        pintarTablero(); //Pinta las piezas
        pintarTableroFichas(); // Pinta el tablero de fichas
        conversor("a-1-b-2/c-3-d-4/e-5-f-6/g-7-h-8");
    }


    /**
     * Menu de Opciones
     * @param menu Menu
     * @return true o false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tablero, menu);
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
        if (id == R.id.borrar) {
            recuperarTablero();
            tableroPiezas.cargarTableroJugada(tablero);//Cargar el tablero
            pintarTablero(); //Pinta las piezas
            verPiezas();
            return true;
        }
        else if (id == R.id.guardar) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Permite desocultar una pieza
     * @param pieza pieza a desocultar
     */
    public void verPieza(String pieza){
        String imagenActual = "";
        for (int i = 1; i <=32 ; i++) {
            String imageId = "pieza" + (i); //Obtiene el id de la imagen en formato cadena
            int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
            ImageView img = (ImageView) findViewById(resID); //Inicializa la imagen con ese id
            imagenActual = img.getTag().toString();
            if(pieza.equalsIgnoreCase(imagenActual) && img.getVisibility() == View.INVISIBLE){ //Si la pieza es la buscada y esta en invisible
               img.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    /**
     * Permite ver todas las piezas
     */
    public void verPiezas(){
        String imagenActual = "";
        for (int i = 1; i <=32 ; i++) {
            String imageId = "pieza" + (i); //Obtiene el id de la imagen en formato cadena
            int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
            ImageView img = (ImageView) findViewById(resID); //Inicializa la imagen con ese id
            img.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Pinta el tablero
     */
    private void pintarTableroFichas(){
        for(int i = 0; i < piezas.length; i++){
            String imageId = "pieza" + (i+1); //Obtiene el id de la imagen en formato cadena
            int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
            ImageView img = (ImageView) findViewById(resID); //Inicializa la imagen con ese id
            int imagenID = getResources().getIdentifier(piezas[i], "drawable", getPackageName());
            img.setImageResource(imagenID);
            img.getLayoutParams().height = (ancho - 40) / 8; //Le da altura
            img.getLayoutParams().width = (ancho - 40) / 8; //Le da anchura
            img.setTag(piezas[i]);
            img.setOnTouchListener(new MyTouchListener());
        }
    }

    /**
     * Crea un tablero vacio
     */
    private void recuperarTablero(){
        tablero = new String[][]{{"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},};
    }

    /**
     * Borra una ficha
     * @param v
     */
    public void toque(View v){
        ImageView imagen = (ImageView) v;
        String[] posicion = imagen.getTag().toString().split("-"); //Cortamos el tag de la imagen para obtener la coordenada x e y (ej (0-0))
        int x = Integer.parseInt(posicion[0]); //Posicion x ej
        int y = Integer.parseInt(posicion[1]); //Posicion y ej(0)
        String nombrePieza = tableroPiezas.getPiezas()[x][y].getImagen();
        tablero[x][y] = "v"; //Actualiza tablero
        tableroPiezas.cargarTableroJugada(tablero); //Cargar el tablero
        repintarTablero(); //Repinta el tablero
        verPieza(nombrePieza);
    }
    /**
     * Ajusta el tamaño de la tabla
     */
    public void tamañoTabla() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //Pantalla
        ancho = display.getWidth(); //Anchura de la pantalla
        largo = display.getHeight(); //Altura de la pantalla
        System.out.println("el ancho de pantalla es: " + ancho);
        System.out.println("el largo de pantalla es: " + largo);
        int lado = ancho - 40; //Lado
        tableroVisual.getLayoutParams().height = lado; //Le pone una altura al tablero
        tableroVisual.getLayoutParams().width = lado; //Le pone una anchura al tablero
        tablerodePiezas.getLayoutParams().height = lado; //Le pone una altura al tablero
        tablerodePiezas.getLayoutParams().width = lado; //Le pone una anchura al tablero
        recuadro.getLayoutParams().height = ancho - 10; //Altura del recuadro del tablero
        recuadro.getLayoutParams().width = ancho; //Anchiura del recuadro del tablero
    }

    /**
     * Pinta las fichas en el tablero
     */
    public void pintarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String imageId = "image" + (i) + "." + (j); //Obtiene el id de la imagen en formato cadena
                int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
                ImageView img = ((ImageView) findViewById(resID)); //Inicializa la imagen con ese id
                img.getLayoutParams().height = (ancho - 40) / 8; //Le da altura
                img.getLayoutParams().width = (ancho - 40) / 8; //Le da anchura
                int imagenID = getResources().getIdentifier(tableroPiezas.getPiezas()[i][j].getImagen(), "drawable", getPackageName());
                img.setImageResource(imagenID);
                img.setOnDragListener(new MyDragListener());
            }
        }
    }

    /**
     * Pinta las fichas en el tablero
     */
    public void repintarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String imageId = "image" + (i) + "." + (j); //Obtiene el id de la imagen en formato cadena
                int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
                ImageView img = (ImageView) findViewById(resID); //Inicializa la imagen con ese id
                img.getLayoutParams().height = (ancho - 40) / 8; //Le da altura
                img.getLayoutParams().width = (ancho - 40) / 8; //Le da anchura
                int imagenID = getResources().getIdentifier(tableroPiezas.getPiezas()[i][j].getImagen(), "drawable", getPackageName());
                img.setImageResource(imagenID);
            }
        }
    }


    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                imagenMovida = (ImageView) view; //Imagen movida
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
               view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        View draggedView;

        ImageView dropped; //Imagen seleccionada
        ImageView dropTarget; //Imagen puesta


        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    draggedView = (View) event.getLocalState();
                    dropped = (ImageView) draggedView;
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    dropTarget = (ImageView) v; //Casilla donde se colocara la ficha
                    String[] posicion = dropTarget.getTag().toString().split("-"); //Cortamos el tag de la imagen para obtener la coordenada x e y (ej (0-0))
                    int x = Integer.parseInt(posicion[0]); //Posicion x ej
                    int y = Integer.parseInt(posicion[1]); //Posicion y ej(0)
                    String imagen = tableroPiezas.getPiezas()[x][y].getImagen(); //Nombre de la pieza

                    if(imagen.equals("v")){ //Si hay un hueco vacio
                        tablero[x][y] = dropped.getTag().toString(); //Actualizas tablero
                        int imagenID = getResources().getIdentifier(dropped.getTag().toString(), "drawable", getPackageName()); //Recupera ID
                        dropTarget.setImageResource(imagenID); //Añade la imagen
                        dropped.setVisibility(View.INVISIBLE); //Oculta la pieza
                    }
                    else {
                        if (!imagen.equalsIgnoreCase(dropped.getTag().toString())){ //Si las piezas son distintas
                            tablero[x][y] = dropped.getTag().toString(); //Actualiza tablero
                            verPieza(imagen); //Muestro la piza antigua
                            dropped.setVisibility(View.INVISIBLE); //Oculto la pieza nueva
                        }
                        else {
                            Toast.makeText(CrearJugadas.this, "Es la misma pieza", Toast.LENGTH_SHORT).show();
                        }
                    }
                    tableroPiezas.cargarTableroJugada(tablero); //Cargar el tablero
                    repintarTablero();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
