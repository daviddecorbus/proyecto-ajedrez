

package com.telefonica.first.tableroprueba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.telefonica.first.tableroprueba.TamañoLetra.tamañoLetra;

public class TableroEjercicio extends AppCompatActivity {
    private Locale lenguageActual;
    private static final int CONFIGURACION = 1;
    private FrameLayout peonCambioNegras; //Peon Cambio Negras
    private FrameLayout peonCambioBlancas; //Peon Cambio Blancas
    private String[][] tablero; //Un Array Multidimensional con el nombre de las piezas
    protected static int ancho = 0; //Ancho de la Pantalla
    protected static int largo = 0; //Largo de la Pantalla
    protected static int dpi=0;
    protected static Tablero tableroPiezas = new Tablero(); //Tablero
    protected static boolean resaltado = false; //Si la pieza esta resaltada
    protected static String colorPiezaSeleccionada;
    private String idFicha; //Identificador de la pieza
    private String idFicha2; //identificador de segunda
    private int xFichaInicial; //Posicion X de la Pieza Inicial
    private int yFichaInicial; //Posicion Y de la Pieza Ininicial
    private TableLayout tableroVisual; //Tablero Visual
    private ImageView recuadro; //Recuadro del tablero
    protected static String turnoColor = "blanco";
    protected static int xReyNegro; //para saber en que posición esta el rey negro y poder revisar las situaciones de jaque
    protected static int yReyNegro;
    protected static int xReyBlanco;//para saber en que posición esta el rey blanco y poder revisar las situaciones de jaque
    protected static int yReyBlanco;
    protected static boolean turnoResaltado = false;
    private String nombreGuia = "vr"; //Nombre de la guia (vr) con guia (vr2) sin guia
    private int fichaNueva [] = new int[2];
    private boolean sonido; //Sonido
    protected static Ejercicio ejercicio;
    private String movimiento;
    private String idioma;
    private int contadorMovimientos=0;
    protected int ayuda=1;
    protected String estadoInicial;
    private int id;
    protected static String correo;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        //  super.attachBaseContext(Lenguaje.onAttach(base,idioma));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resaltado = false;
        setContentView(R.layout.activity_main); //Carga el Layout
        //Oculta los fragment
        peonCambioNegras = (FrameLayout) findViewById(R.id.peonCambioNegras);
        peonCambioNegras.setVisibility(View.INVISIBLE);
        peonCambioBlancas = (FrameLayout) findViewById(R.id.peonCambioBlancas);
        peonCambioBlancas.setVisibility(View.INVISIBLE);
        tableroVisual = (TableLayout) findViewById(R.id.tablero); //Inicializa el tablero visual
        recuadro = (ImageView) findViewById(R.id.imagerojo); //Cargar el recuadro del tablero
        recuperarDatos(); //Recupera los datos
        Ejercicio ejer = (Ejercicio) getIntent().getSerializableExtra("ejercicio");
        ejercicio = ejer;
       cargarEjercicio(ejercicio); //Recupera los datos del tablero
        tamañoTabla(); // Ajusta el tablero
        pintarTablero(); //Pinta las piezas
    }

    public void fichasRecambio(View v) {
        ImageView imagen = (ImageView) v;
        tablero[fichaNueva[0]][fichaNueva[1]] = imagen.getTag().toString();
        Peon.convertirPeon(fichaNueva[0],fichaNueva[1],imagen.getTag().toString());
        if (peonCambioBlancas.getVisibility() == View.VISIBLE) {
            peonCambioBlancas.setVisibility(View.INVISIBLE);
        }else {
            peonCambioNegras.setVisibility(View.INVISIBLE);
        }
        activarTablero(true);
        pintarTablero(); //Pinta el tablero
    }

    /**
     * Recupera los datos guardados en las preferencias
     */
    private void recuperarDatos(){
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this); //Inicializa las preferencias
        idioma = pre.getString("idioma", "es");
        setTitle(getString(R.string.ejercicio));
        boolean coordenadas = pre.getBoolean("coordenadas", false  );
        boolean guia = pre.getBoolean("guia", true);
        sonido = pre.getBoolean("sonido", true);
        nombreGuia = (guia) ? "vr" : "vr2"; //Si tiene guia o no

        String colorTablero = pre.getString("tableroColores", "ic_azul_claro"); //Obtiene el valor de la preferencia tableroColores
        cargarFondoTablero(colorTablero,coordenadas); //Carga el fondo del tablero a partir del valor obtenido de las preferencias

    }

    /**
     * Permite cambiar el fondo al tablero
     * @param colorTablero Color del tablero (Se saca de las preferencias)
     */
    private void cargarFondoTablero(String colorTablero,boolean coordenadas){
        recuadro.setImageResource(R.drawable.rojo);
        switch (colorTablero){
            case "ic_azul_claro":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAzulClaro)));
                recuadro.setImageResource(R.drawable.borde_azul_claro);
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tablero_azul_claro2);
                else tableroVisual.setBackgroundResource(R.drawable.tablero_azul_claro);
                break;
            case "ic_azul_oscuro":
                recuadro.setImageResource(R.drawable.borde_azul_oscuro);
                tableroVisual.setBackgroundResource(R.drawable.tableroazuloscuro);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAzulOscuro)));

                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tableroazuloscuro2);
                else tableroVisual.setBackgroundResource(R.drawable.tableroazuloscuro);
                break;
            case "ic_azul_turquesa":
                recuadro.setImageResource(R.drawable.borde_azul_turquesa);
                tableroVisual.setBackgroundResource(R.drawable.tableroazulturquesa);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorturquesa)));
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tableroazulturquesa2);
                else tableroVisual.setBackgroundResource(R.drawable.tableroazulturquesa);
                break;
            case "ic_amarillo":
                recuadro.setImageResource(R.drawable.borde_amarillo);
                tableroVisual.setBackgroundResource(R.drawable.tableroamarillo);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAmarillo)));
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tableroamarillo2);
                else tableroVisual.setBackgroundResource(R.drawable.tableroamarillo);
                break;
            case "ic_morado":
                recuadro.setImageResource(R.drawable.borde_morado);
                tableroVisual.setBackgroundResource(R.drawable.tableromorado);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMorado)));
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tableromorado2);
                else tableroVisual.setBackgroundResource(R.drawable.tableromorado);
                break;
            case "ic_naranja":
                recuadro.setImageResource(R.drawable.borde_naranja);
                tableroVisual.setBackgroundResource(R.drawable.tableronaranja);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorNaranja)));
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tableronaranja2);
                else tableroVisual.setBackgroundResource(R.drawable.tableronaranja);
                break;

            case "ic_rosa_oscuro":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRosa)));
                tableroVisual.setBackgroundResource(R.drawable.tablerorosaoscuro);
                recuadro.setImageResource(R.drawable.borde_rosa_oscuro);
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tablerorosaoscuro2);
                else tableroVisual.setBackgroundResource(R.drawable.tablerorosaoscuro);

                break;
            case "ic_rosa":
                recuadro.setImageResource(R.drawable.borde_rosa);
                tableroVisual.setBackgroundResource(R.drawable.tablero_rosa);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRosaPalo)));
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tablero_rosa2);
                else tableroVisual.setBackgroundResource(R.drawable.tablero_rosa);

                break;

            default:
                break;

        }
    }

    /**
     * Rellena el Array Multidimensional con las piezas
     */
    public void cargarEjercicio(Ejercicio ejercicio){
        activarTablero(true);
        turnoResaltado=false;
        estadoInicial= ejercicio.getEstado();
        id = ejercicio.getId();
        turnoColor = ejercicio.getColorInicio();
        TextView texto = (TextView) findViewById(R.id.textoEjercicio);
        texto.setText(ejercicio.getTextoEjercicio());
        tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia); //Cargar el tablero
        if(estadoInicial.equalsIgnoreCase("sin-empezar")){
            grabarPartida();
        }
       }

    /**
     * Ajusta el tamaño de la tabla
     */
    public void tamañoTabla() { //define el tamaño de la tabla y de las celdas
        int lado = ancho - 40; //Lado
        TextView texto = (TextView) findViewById(R.id.textoEjercicio);
        texto.setTextSize(tamañoLetra(ancho,dpi));

        tableroVisual.getLayoutParams().height = lado; //Le pone una altura al tablero
        tableroVisual.getLayoutParams().width = lado; //Le pone una anchura al tablero
        recuadro.getLayoutParams().height = ancho; //Altura del recuadro del tablero
        recuadro.getLayoutParams().width = ancho; //Anchiura del recuadro del tablero
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                String imageId = "image" + (i) + "." + (j); //Obtiene el id de la imagen en formato cadena
                int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
                ImageView img = ((ImageView) findViewById(resID)); //Inicializa la imagen con ese id
                img.getLayoutParams().height = (ancho - 40) / 8; //Le da altura
                img.getLayoutParams().width = (ancho - 40) / 8; //Le da anchura
            }
        }
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
                if (tableroPiezas.getPiezas()[i][j].isJaque()){ //revisa si la pieza está en jaque (sólo funcionará si es rey)
                    int imagenID = getResources().getIdentifier(tableroPiezas.getPiezas()[i][j].getImagenJaque(), "drawable", getPackageName());
                    img.setImageResource(imagenID);
                }else if (tableroPiezas.getPiezas()[i][j].isResaltado()) { //Si la pieza esta Resaltada
                        int imagenID = getResources().getIdentifier(tableroPiezas.getPiezas()[i][j].getImagenResaltada(), "drawable", getPackageName());
                        img.setImageResource(imagenID);
                } else {
                    int imagenID = getResources().getIdentifier(tableroPiezas.getPiezas()[i][j].getImagen(), "drawable", getPackageName());
                    img.setImageResource(imagenID);
                }

            }
        }
    }

    /**
     * Desactiva el tablero
     */
    public void activarTablero(boolean activar) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String imageId = "image" + (i) + "." + (j); //Obtiene el id de la imagen en formato cadena
                int resID = getResources().getIdentifier(imageId, "id", getPackageName()); //Obtiene el id de la imagen
                ImageView img = ((ImageView) findViewById(resID)); //Inicializa la imagen con ese id
                img.setEnabled(activar);
            }
        }
    }

    /**
     * Cuando pulsas en una casilla
     * @param v Vista
     */
    public void toque(View v) {
        System.out.println("valor de resaltado es " + resaltado);
        ImageView imagen = (ImageView) v; //Imagen Actual
        System.out.println("resID: " + imagen.getTag());
        String[] posicion = imagen.getTag().toString().split("-"); //Cortamos el tag de la imagen para obtener la coordenada x e y (ej (0-0))
        int x = Integer.parseInt(posicion[0]); //Posicion x ej(0)
        int y = Integer.parseInt(posicion[1]); //Posicion y ej(0)

        if (!resaltado && tableroPiezas.getPiezas()[x][y].getColor().equalsIgnoreCase(turnoColor) && !turnoResaltado) { //Si la pieza no esta resaltada y es su turno se
            // resalta y te marca los movimientos posibles
            if (!tableroPiezas.getPiezas()[x][y].getColor().equalsIgnoreCase("neutro")) { //Si el color de la pieza es blanco o negro
                colorPiezaSeleccionada = tableroPiezas.getPiezas()[x][y].getColor();//guarda el color de la pieza seleccionada
                tableroPiezas.getPiezas()[x][y].movimiento(); //rasalta las casillas a las que puede mover
                idFicha = tableroPiezas.getPiezas()[x][y].getImagen(); //Guarda el id de la pieza
                xFichaInicial = x; //Posicion inicial x
                yFichaInicial = y; //Posicion inicia y
                turnoResaltado = true;// guarda si hay alguna pieza seleccionada
                pintarTablero(); //Pinta el tablero
            }
        }else if(resaltado && tableroPiezas.getPiezas()[x][y].getColor().equalsIgnoreCase(turnoColor) && turnoResaltado){//si la pieza está seleccionada
            if(tableroPiezas.getPiezas()[x][y].isResaltado()){// si la pieza está seleccionada la des-selecciona
                tableroPiezas.desresaltarTodos(); //Le quita el resaltado
                resaltado = false;
                pintarTablero();
            }else{      //si tocamos una pieza del mismo color que no está seleccionada carga posibles movimientos en la nueva piezza
                System.out.println("estamos en el que queremos");
                tableroPiezas.desresaltarTodos(); //Lle quita el resaltado
                colorPiezaSeleccionada = tableroPiezas.getPiezas()[x][y].getColor();//guardamos el color de la pieza seleccionada
                tableroPiezas.getPiezas()[x][y].movimiento(); //Hace su movimiento

                idFicha = tableroPiezas.getPiezas()[x][y].getImagen(); //Guarda el id de la pieza
                xFichaInicial = x; //Posicion inicial x
                yFichaInicial = y; //Posicion inicia y
                turnoResaltado = true;
                pintarTablero(); //Pinta el tablero
            }
        }else{ //Si la pieza esta resaltada
            System.out.println("estamos en el resaltado true");
            if(tableroPiezas.getPiezas()[x][y].isResaltado()){ //Si la pieza esta resaltada
                idFicha2 =  ejercicio.getTablero()[x][y]; //guarda el id de la pieza comida por si el movimiento es malo poder recuperarla
                ejercicio.getTablero()[x][y]= idFicha; //Le pasa el id de la pieza al tablero

                ejercicio.getTablero()[xFichaInicial][yFichaInicial] = "v"; //Le pasa el nombre de la pieza
                tableroPiezas.desresaltarTodos(); //Lle quita el resaltado
                resaltado = false;

                tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia); //Carga el tablero

                if(!Rey.revisarJaqueBlanco(xReyBlanco,yReyBlanco)){ //revisamos si el rey blanco en jaque
                    tableroPiezas.getPiezas()[xReyBlanco][yReyBlanco].setJaque(true);
                }
                if(!Rey.revisarJaqueNegro(xReyNegro,yReyNegro)){ //revisamos si el rey negro queda en jaque
                    tableroPiezas.getPiezas()[xReyNegro][yReyNegro].setJaque(true);
                }
                if(tableroPiezas.getPiezas()[x][y].getImagen().equalsIgnoreCase("bp") && x == 7){
                    // tablero[x][y] = "bq";
                    //Peon.convertirPeon(x,y);
                    fichaNueva[0] = x;
                    fichaNueva[1] = y;
                    peonCambioNegras.setVisibility(View.VISIBLE);
                    activarTablero(false);
                }
                if(tableroPiezas.getPiezas()[x][y].getImagen().equalsIgnoreCase("wp") && x == 0){
                    //tablero[x][y] = "wq";
                    //Peon.convertirPeon(x,y);
                }
                pintarTablero(); //Pinta el tablero
                movimiento= xFichaInicial+"-"+yFichaInicial+"-"+x+"-"+y;
                System.out.println("el movimiento es: "+movimiento);
                if(movimiento.equalsIgnoreCase(ejercicio.getMovimientos()[contadorMovimientos])){
                    if(turnoColor.equalsIgnoreCase("blanco")){
                        turnoColor = "negro";
                    }else{
                        turnoColor = "blanco";
                    }
                    contadorMovimientos++;
                    if(contadorMovimientos==ejercicio.getCantidadMovimientos()){
                        crearAlerta(getString(R.string.finalizado),0);
                        estadoInicial="terminada";
                        grabarPartida();
                        activarTablero(false);
                    }else {
                        crearAlerta(getString(R.string.continua),0);

                        movimientoRobot();
                    }
                }else{

                    ejercicio.getTablero()[xFichaInicial][yFichaInicial]= idFicha; //Le pasa el id de la pieza al tablero
                    ejercicio.getTablero()[x][y] = idFicha2; //Le pasa el nombre de la pieza
                    tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia); //Carga el tablero
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pintarTablero(); //Pinta el tablero
                        }
                    }, 1000);
                    crearAlerta(getString(R.string.error),1);
                }
            }
        }
    }
    public void movimientoRobot(){
        movimiento = ejercicio.getMovimientos()[contadorMovimientos];
        String[] cadena = movimiento.split("-");
        xFichaInicial = Integer.parseInt(cadena[0]);
        yFichaInicial = Integer.parseInt(cadena[1]);
        int x = Integer.parseInt(cadena[2]);
        int y = Integer.parseInt(cadena[3]);
        contadorMovimientos++;
        idFicha = tableroPiezas.getPiezas()[xFichaInicial][yFichaInicial].getImagen(); //Guarda el id de la pieza
        ejercicio.getTablero()[x][y]= idFicha; //Le pasa el id de la pieza al tablero
        ejercicio.getTablero()[xFichaInicial][yFichaInicial] = "v"; //Le pasa el nombre de la pieza

        if(!Rey.revisarJaqueBlanco(xReyBlanco,yReyBlanco)){ //revisamos si el rey blanco en jaque
            tableroPiezas.getPiezas()[xReyBlanco][yReyBlanco].setJaque(true);
        }
        if(!Rey.revisarJaqueNegro(xReyNegro,yReyNegro)){ //revisamos si el rey negro queda en jaque
            tableroPiezas.getPiezas()[xReyNegro][yReyNegro].setJaque(true);
        }
        tableroPiezas.cargarTablero(ejercicio.getTablero(),"v"); //Carga el tablero de sin ayuda obligado para que coja las imagenes con marco morado
        tableroPiezas.getPiezas()[x][y].setResaltado(true);
        tableroPiezas.getPiezas()[xFichaInicial][yFichaInicial].setResaltado(true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(turnoColor.equalsIgnoreCase("blanco")){
                    turnoColor = "negro";
                }else{
                    turnoColor = "blanco";
                }

                pintarTablero(); //Pinta el tablero
                tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia);//volvemos a cargar las imagenes normales
            }
        }, 1000);
    }

    /**
     * Resultado devuelto de la configuacion
     * @param requestCode Pedido
     * @param resultCode Resultado
     * @param data Datos
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CONFIGURACION:
                if(resultCode == Activity.RESULT_OK){
                    //Devuelve valores de Preferencia
                   // boolean guia = data.getExtras().getBoolean("guiaActivada");
                    recuperarDatos();
                    tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia); //Carga el tablero
                }
                break;

        }
    }

    /**
     * Menu de Opciones
     * @param menu Menu
     * @return true o false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivityForResult(intent,CONFIGURACION);
            return true;
        }
        else if (id == R.id.action_help){
            if(contadorMovimientos<ejercicio.getCantidadMovimientos() && turnoColor.equalsIgnoreCase(ejercicio.getColorInicio())){
                ayuda++;
                movimiento = ejercicio.getMovimientos()[contadorMovimientos];
                String[] cadena = movimiento.split("-");
                int x = Integer.parseInt(cadena[0]);
                int y = Integer.parseInt(cadena[1]);
                colorPiezaSeleccionada = tableroPiezas.getPiezas()[x][y].getColor();//guarda el color de la pieza seleccionada
                tableroPiezas.getPiezas()[x][y].movimiento(); //rasalta las casillas a las que puede mover
                idFicha = tableroPiezas.getPiezas()[x][y].getImagen(); //Guarda el id de la pieza
                xFichaInicial = x; //Posicion inicial x
                yFichaInicial = y; //Posicion inicia y
                turnoResaltado = true;// guarda si hay alguna pieza seleccionada
                pintarTablero(); //Pinta el tablero
                //tableroPiezas.cargarTablero(ejercicio.getTablero(), nombreGuia);
                //tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia);//volvemos a cargar las imagenes normales
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Crea una Alerta Personalizada
     * @param texto Texto
     * @param color Color
     */
    private void crearAlerta(String texto,int color)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = null;
        if(color == 0)
            layout = inflater.inflate(R.layout.toast,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

        else
            layout = inflater.inflate(R.layout.toast2, (ViewGroup) findViewById(R.id.toast_layout_root));



        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(texto);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0,100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();


    }

    /**
     * Grabar una partida en la Base de Datos
     */
    private void grabarPartida(){
        GrabarPartida com = new GrabarPartida();
        String parametro = "correo=" + correo + "&estado=" + estadoInicial + "&idejercicio=" + ejercicio.getId();
        com.execute("http://caissamaister.esy.es/grabarPartida.php",parametro);
    }

    /**
     * Tecla de Retroceso
     */
    @Override
   public void onBackPressed() {
    }
    public void volver(View v){
        if(!estadoInicial.equalsIgnoreCase("terminada")){
            estadoInicial = "empezada";
            grabarPartida();
        }
        finish();
    }



}