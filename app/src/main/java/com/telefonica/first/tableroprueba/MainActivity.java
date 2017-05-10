

package com.telefonica.first.tableroprueba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int CONFIGURACION = 1;
    private FrameLayout peonCambioNegras; //Peon Cambio Negras
    private FrameLayout peonCambioBlancas; //Peon Cambio Blancas
    private String[][] tablero; //Un Array Multidimensional con el nombre de las piezas
    private int ancho = 0; //Ancho de la Pantalla
    private int largo = 0; //Largo de la Pantalla
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
    private int contadorMovimientos=0;
    protected int ayuda=1;

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
        crearEjercicio(); //Recupera los datos del tablero
        tableroPiezas.cargarTablero(ejercicio.getTablero(),nombreGuia); //Cargar el tablero
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
                tableroVisual.setBackgroundResource(R.drawable.tableroazuloscuro);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAzulOscuro)));
                break;
            case "ic_azul_turquesa":
                tableroVisual.setBackgroundResource(R.drawable.tableroazulturquesa);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorturquesa)));
                break;
            case "ic_marron":
                tableroVisual.setBackgroundResource(R.drawable.tablero_marron);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMarron)));
                break;
            case "ic_amarillo":
                tableroVisual.setBackgroundResource(R.drawable.tableroamarillo);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAmarillo)));
                break;
            case "ic_morado":
                tableroVisual.setBackgroundResource(R.drawable.tableromorado);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMorado)));
                break;
            case "ic_naranja":
                tableroVisual.setBackgroundResource(R.drawable.tableronaranja);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorNaranja)));
                break;
            case "ic_rosa":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRosa)));
                recuadro.setImageResource(R.drawable.borde_rosa);
                if(coordenadas) tableroVisual.setBackgroundResource(R.drawable.tablero_rosa2);
                else tableroVisual.setBackgroundResource(R.drawable.tablero_rosa);
                break;
            case "ic_rosa_palo":
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRosaPalo)));
                recuadro.setImageResource(R.drawable.borde_rosa);
                break;
            default:
                break;

        }
    }
    /**
     * Rellena el Array Multidimensional con las piezas
     */
    public void crearEjercicio(){
       /* tablero = new String[][]{{"v", "v", "v", "v", "v", "bk", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"bq", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "bn", "wq", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"wp", "wp", "v", "v", "v", "v", "v", "v"},
                {"wk", "wn", "v", "v", "v", "v", "v", "v"}};
        int cantidadMovimientos = 3;
        String [] movimientos = new String[]{"3-0-6-0", "4-2-6-0","4-1-6-2"};
        String colorInicio = "negro";
        String textoEjercicio = "Negras ganan en dos movimientos";
        ejercicio = new Ejercicio(tablero,cantidadMovimientos,colorInicio,movimientos,textoEjercicio);
        turnoColor = colorInicio;
        TextView texto = (TextView) findViewById(R.id.textoEjercicio);
        texto.setText(ejercicio.getTextoEjercicio());*/
        //////
        tablero = new String[][]{{"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "wq", "v", "bp", "wn", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"bk", "v", "v", "wk", "v", "v", "v", "v"}};
        int cantidadMovimientos = 3;
        String [] movimientos = new String[]{"5-4-4-2", "5-3-6-3","5-1-6-1"};
        String colorInicio = "blanco";
        String textoEjercicio = "Blancas ganan en dos movimientos";
        ejercicio = new Ejercicio(tablero,cantidadMovimientos,colorInicio,movimientos,textoEjercicio);
        turnoColor = colorInicio;
        TextView texto = (TextView) findViewById(R.id.textoEjercicio);
        texto.setText(ejercicio.getTextoEjercicio());
    }
    /*
    public void recuperarTablero(){
        tablero = new String[][]{{"br", "v", "v", "v", "bk", "v", "v", "br"},
                {"v", "wp", "bp", "bp", "v", "bp", "v", "v"},
                {"bp", "bb", "bn", "v", "v", "v", "v", "v"},
                {"v", "bp", "v", "v", "v", "v", "wb", "v"},
                {"v", "v", "v", "v", "wp", "v", "bp", "v"},
                {"v", "v", "v", "wb", "v", "v", "wp", "bq"},
                {"wp", "wp", "bp", "v", "wn", "v", "v", "wp"},
                {"br", "wn", "v", "wq", "v", "wr", "v", "wk"}};
        tablero = new String[][]{{"br", "bn", "bb", "bk", "bq", "bb", "bn", "br"},
                {"bp", "bp", "bp", "bp", "bp", "bp", "bp", "bp"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"v", "v", "v", "v", "v", "v", "v", "v"},
                {"wp", "wp", "wp", "wp", "wp", "wp", "wp", "wp"},
                {"wr", "wn", "wb", "wk", "wq", "wb", "wn", "wr"}};
    }*/

    /**
     * Ajusta el tamaño de la tabla
     */
    public void tamañoTabla() { //define el tamaño de la tabla y de las celdas
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); //Pantalla
        ancho = display.getWidth(); //Anchura de la pantalla
        largo = display.getHeight(); //Altura de la pantalla
        int lado = ancho - 40; //Lado
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
                        Toast.makeText(MainActivity.this, "Lo lograste", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Sigue así", Toast.LENGTH_LONG).show();

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

                    Toast.makeText(MainActivity.this, "Puedes hacerlo mejor", Toast.LENGTH_SHORT).show();
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
    public void ayuda(View v){
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
                    tableroPiezas.cargarTablero(tablero,nombreGuia); //Carga el tablero
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
        return super.onOptionsItemSelected(item);
    }
}