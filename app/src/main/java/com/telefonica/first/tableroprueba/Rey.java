package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.MainActivity.colorPiezaSeleccionada;
import static com.telefonica.first.tableroprueba.MainActivity.resaltado;
import static com.telefonica.first.tableroprueba.MainActivity.tableroPiezas;

/**
 * Created by David Garrido on 06/05/2017.
 */

public class Rey extends Pieza {
public boolean jaque;
public  String imagenJaque;
    public Rey(String imagen, String imagenResaltada, String color, int x, int y,boolean jaque,String imagenJaque) {
        super(imagen, imagenResaltada, color, x, y);
        this.jaque = jaque;
        this.imagenJaque = imagenJaque;
    }

    @Override
    public void movimiento() {
        System.out.println("soy un rey de color " + this.getColor());
        int x = this.getX();
        int y = this.getY();
        this.setResaltado(true);
        resaltado = true;
        if (colorPiezaSeleccionada.equalsIgnoreCase("blanco")) {
            //movimiento arriba

            if (x - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x - 1, y)) {
                        tableroPiezas.getPiezas()[x - 1][y].setResaltado(true);
                    }
                }
            }
            // abajo
            if (x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x + 1, y)) {
                        tableroPiezas.getPiezas()[x + 1][y].setResaltado(true);
                    }
                }
            }
            // derecha
            if (y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x][y + 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x, y + 1)) {
                        tableroPiezas.getPiezas()[x][y + 1].setResaltado(true);
                    }
                }
            }
            //izquierda
            if (y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x][y - 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x, y - 1)) {
                        tableroPiezas.getPiezas()[x][y - 1].setResaltado(true);
                    }
                }
            }
            //abajo-derecha
            if (x + 1 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x + 1, y + 1)) {
                        tableroPiezas.getPiezas()[x + 1][y + 1].setResaltado(true);
                    }
                }
            }
            //abajo-izquierda
            if (x + 1 < 8 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x + 1, y - 1)) {
                        tableroPiezas.getPiezas()[x + 1][y - 1].setResaltado(true);
                    }
                }
            }
            //arriba-derecha
            if (x - 1 > -1 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x - 1, y + 1)) {
                        tableroPiezas.getPiezas()[x - 1][y + 1].setResaltado(true);
                    }
                }
            }
            //arriba-izquierda
            if (x - 1 > -1 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("negro")) {
                    if (revisarTablero(x - 1, y - 1)) {
                        tableroPiezas.getPiezas()[x - 1][y - 1].setResaltado(true);
                    }

                }
            }
        }else if(colorPiezaSeleccionada.equalsIgnoreCase("negro")){
            //movimiento arriba

            if (x - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x - 1, y)) {
                        tableroPiezas.getPiezas()[x - 1][y].setResaltado(true);
                    }
                }
            }
            // abajo
            if (x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x + 1, y)) {
                        tableroPiezas.getPiezas()[x + 1][y].setResaltado(true);
                    }
                }
            }
            // derecha
            if (y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x][y + 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x, y + 1)) {
                        tableroPiezas.getPiezas()[x][y + 1].setResaltado(true);
                    }
                }
            }
            //izquierda
            if (y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x][y - 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x, y - 1)) {
                        tableroPiezas.getPiezas()[x][y - 1].setResaltado(true);
                    }
                }
            }
            //abajo-derecha
            if (x + 1 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x + 1, y + 1)) {
                        tableroPiezas.getPiezas()[x + 1][y + 1].setResaltado(true);
                    }
                }
            }
            //abajo-izquierda
            if (x + 1 < 8 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x + 1, y - 1)) {
                        tableroPiezas.getPiezas()[x + 1][y - 1].setResaltado(true);
                    }
                }
            }
            //arriba-derecha
            if (x - 1 > -1 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x - 1, y + 1)) {
                        tableroPiezas.getPiezas()[x - 1][y + 1].setResaltado(true);
                    }
                }
            }
            //arriba-izquierda
            if (x - 1 > -1 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("neutro") || tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("blanco")) {
                    if (revisarTablero(x - 1, y - 1)) {
                        tableroPiezas.getPiezas()[x - 1][y - 1].setResaltado(true);
                    }

                }
            }
        }
    }
//método para bloquear los movimientos del rey  a las casillas donde le hacen jaque
    public static boolean revisarTablero(int x, int y) {
        int y1 = y;
        int y2 = y;
        int y3 = y;
        if (colorPiezaSeleccionada.equalsIgnoreCase("blanco")) {
            //revisa si hay un peón que le haga jaque
            if (x - 1 > -1 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("bp")) {
                    return false;
                }
            }
            if (x - 1 > -1 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("bp")) {
                    return false;
                }
            }
            //revisa si hay un caballo que le haga jaque

            if (x - 2 >= 0 && y - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 2][y - 1].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (x - 2 >= 0 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 2][y + 1].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (x + 2 < 8 && y - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x + 2][y - 1].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (x + 2 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 2][y + 1].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (y - 2 >= 0 && x - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 1][y - 2].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (y - 2 >= 0 && x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y - 2].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (y + 2 < 8 && x - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 1][y + 2].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            if (y + 2 < 8 && x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 2].getImagen().equalsIgnoreCase("bn")) {
                    return false;
                }
            }
            //revisamos que no este el rey negro
            if (x - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (x - 1 > -1 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (x - 1 > -1 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (x + 1 < 8 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (x + 1 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x][y - 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }
            if (y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x][y + 1].getImagen().equalsIgnoreCase("bk")) {
                    return false;
                }
            }

            //para revisar que no le hagan jaque ni torre ni reina
            for (int i = (x - 1); i > -1; i--) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk")))) {

                    i = -1;

                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {

                    return false;
                }
            }
            for (int i = (x + 1); i < 8; i++) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk")))) {
                    i = 8;

                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
            for (int i = (y - 1); i > -1; i--) {
                if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk")))) {
                    i = -1;

                } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
            for (int i = (y + 1); i < 8; i++) {
                if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk")))) {
                    i = 8;

                } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
            //revisar que no le hagan jaque ni alfíl ni reina
            for (int i = (x + 1); i < 8; i++) {
                y++;
                if (y < 8) {
                    if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                            || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                            || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][x].getImagen().equalsIgnoreCase("bk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {
                        return false;
                    }
                }
            }
            for (int i = (x + 1); i < 8; i++) {
                y1--;
                if (y1 > -1) {
                    if ((tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wk"))
                            || (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bp")
                            || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bq")) {
                        return false;
                    }
                }
            }
            for (int i = (x - 1); i > -1; i--) {
                y2++;
                if (y2 < 8) {
                    if ((tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wk"))
                            || (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bp")
                            || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bq")) {
                        return false;
                    }
                }
            }
            for (int i = (x - 1); i > -1; i--) {
                y3--;
                if (y3 > -1) {
                    if ((tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wk"))
                            || (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bp")
                            || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bk")))) {
                        i = -1;
                    } else if (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bq")) {
                        return false;
                    }
                }
            }


        }else if(colorPiezaSeleccionada.equalsIgnoreCase("negro")){
            //revisa si hay un peón que le haga jaque
            if (x + 1 < 8 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("wp")) {
                    return false;
                }
            }
            if (x + 1 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("wp")) {
                    return false;
                }
            }
            //revisa si hay un caballo que le haga jaque

            if (x - 2 >= 0 && y - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 2][y - 1].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (x - 2 >= 0 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 2][y + 1].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (x + 2 < 8 && y - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x + 2][y - 1].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (x + 2 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 2][y + 1].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (y - 2 >= 0 && x - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 1][y - 2].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (y - 2 >= 0 && x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y - 2].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (y + 2 < 8 && x - 1 >= 0) {
                if (tableroPiezas.getPiezas()[x - 1][y + 2].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            if (y + 2 < 8 && x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 2].getImagen().equalsIgnoreCase("wn")) {
                    return false;
                }
            }
            //revisamos que no este el rey negro
            if (x - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (x - 1 > -1 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (x - 1 > -1 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (x + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (x + 1 < 8 && y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (x + 1 < 8 && y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (y - 1 > -1) {
                if (tableroPiezas.getPiezas()[x][y - 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            if (y + 1 < 8) {
                if (tableroPiezas.getPiezas()[x][y + 1].getImagen().equalsIgnoreCase("wk")) {
                    return false;
                }
            }
            //para revisar que no le hagan jaque ni torre ni reina
            for (int i = (x - 1); i > -1; i--) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk")))) {

                    i = -1;

                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {

                    return false;
                }
            }
            for (int i = (x + 1); i < 8; i++) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk")))) {
                    i = 8;

                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
            for (int i = (y - 1); i > -1; i--) {
                if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk")))) {
                    i = -1;

                } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
            for (int i = (y + 1); i < 8; i++) {
                if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk")))) {
                    i = 8;

                } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
            //revisar que no le hagan jaque ni alfíl ni reina
            for (int i = (x + 1); i < 8; i++) {
                y++;
                if (y < 8) {
                    if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                            || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                            || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][x].getImagen().equalsIgnoreCase("wk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {
                        return false;
                    }
                }
            }
            for (int i = (x + 1); i < 8; i++) {
                y1--;
                if (y1 > -1) {
                    if ((tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bk"))
                            || (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wp")
                            || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wq")) {
                        return false;
                    }
                }
            }
            for (int i = (x - 1); i > -1; i--) {
                y2++;
                if (y2 < 8) {
                    if ((tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bk"))
                            || (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wp")
                            || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wk")))) {
                        i = 8;
                    } else if (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wq")) {
                        return false;
                    }
                }
            }
            for (int i = (x - 1); i > -1; i--) {
                y3--;
                if (y3 > -1) {
                    if ((tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bk"))
                            || (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wp")
                            || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wk")))) {
                        i = -1;
                    } else if (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wq")) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
    //método para revisar el jaque del rey blanco
public static boolean revisarJaqueBlanco(int x, int y) {
    int y1 = y;
    int y2 = y;
    int y3 = y;

        //revisa si hay un peón que le haga jaque
        if (x - 1 > -1 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("bp")) {
                return false;
            }
        }
        if (x - 1 > -1 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("bp")) {
                return false;
            }
        }
        //revisa si hay un caballo que le haga jaque

        if (x - 2 >= 0 && y - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 2][y - 1].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (x - 2 >= 0 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x - 2][y + 1].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (x + 2 < 8 && y - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x + 2][y - 1].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (x + 2 < 8 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 2][y + 1].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (y - 2 >= 0 && x - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 1][y - 2].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (y - 2 >= 0 && x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y - 2].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (y + 2 < 8 && x - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 1][y + 2].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        if (y + 2 < 8 && x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y + 2].getImagen().equalsIgnoreCase("bn")) {
                return false;
            }
        }
        //revisamos que no este el rey negro
        if (x - 1 > -1) {
            if (tableroPiezas.getPiezas()[x - 1][y].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (x - 1 > -1 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (x - 1 > -1 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (x + 1 < 8 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (x + 1 < 8 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x][y - 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }
        if (y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x][y + 1].getImagen().equalsIgnoreCase("bk")) {
                return false;
            }
        }

        //para revisar que no le hagan jaque ni torre ni reina
        for (int i = (x - 1); i > -1; i--) {
            if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                    || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                    || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk")))) {

                i = -1;

            } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {

                return false;
            }
        }
        for (int i = (x + 1); i < 8; i++) {
            if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                    || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                    || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk")))) {
                i = 8;

            } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {
                return false;
            }
        }
        for (int i = (y - 1); i > -1; i--) {
            if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk"))
                    || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bp")
                    || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk")))) {
                i = -1;

            } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bq")) {
                return false;
            }
        }
        for (int i = (y + 1); i < 8; i++) {
            if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk"))
                    || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bp")
                    || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk")))) {
                i = 8;

            } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("br") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bq")) {
                return false;
            }
        }
        //revisar que no le hagan jaque ni alfíl ni reina
        for (int i = (x + 1); i < 8; i++) {
            y++;
            if (y < 8) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][x].getImagen().equalsIgnoreCase("bk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
        }
        for (int i = (x + 1); i < 8; i++) {
            y1--;
            if (y1 > -1) {
                if ((tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
        }
        for (int i = (x - 1); i > -1; i--) {
            y2++;
            if (y2 < 8) {
                if ((tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
        }
        for (int i = (x - 1); i > -1; i--) {
            y3--;
            if (y3 > -1) {
                if ((tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("blanco") && !tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wk"))
                        || (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("negro") && (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bp")
                        || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bn") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bk")))) {
                    i = -1;
                } else if (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bb") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bq")) {
                    return false;
                }
            }
        }
    return true;
    }
//método para revisar el jaque del rey negro
public static boolean revisarJaqueNegro(int x, int y) {
    int y1 = y;
    int y2 = y;
    int y3 = y;


        //revisa si hay un peón que le haga jaque
        if (x + 1 < 8 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("wp")) {
                return false;
            }
        }
        if (x + 1 < 8 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("wp")) {
                return false;
            }
        }
        //revisa si hay un caballo que le haga jaque

        if (x - 2 >= 0 && y - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 2][y - 1].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (x - 2 >= 0 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x - 2][y + 1].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (x + 2 < 8 && y - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x + 2][y - 1].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (x + 2 < 8 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 2][y + 1].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (y - 2 >= 0 && x - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 1][y - 2].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (y - 2 >= 0 && x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y - 2].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (y + 2 < 8 && x - 1 >= 0) {
            if (tableroPiezas.getPiezas()[x - 1][y + 2].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        if (y + 2 < 8 && x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y + 2].getImagen().equalsIgnoreCase("wn")) {
                return false;
            }
        }
        //revisamos que no este el rey negro
        if (x - 1 > -1) {
            if (tableroPiezas.getPiezas()[x - 1][y].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (x - 1 > -1 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x - 1][y - 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (x - 1 > -1 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x - 1][y + 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (x + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (x + 1 < 8 && y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x + 1][y - 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (x + 1 < 8 && y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x + 1][y + 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (y - 1 > -1) {
            if (tableroPiezas.getPiezas()[x][y - 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        if (y + 1 < 8) {
            if (tableroPiezas.getPiezas()[x][y + 1].getImagen().equalsIgnoreCase("wk")) {
                return false;
            }
        }
        //para revisar que no le hagan jaque ni torre ni reina
        for (int i = (x - 1); i > -1; i--) {
            if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                    || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                    || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk")))) {

                i = -1;

            } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {

                return false;
            }
        }
        for (int i = (x + 1); i < 8; i++) {
            if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                    || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                    || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wk")))) {
                i = 8;

            } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {
                return false;
            }
        }
        for (int i = (y - 1); i > -1; i--) {
            if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk"))
                    || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wp")
                    || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk")))) {
                i = -1;

            } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wq")) {
                return false;
            }
        }
        for (int i = (y + 1); i < 8; i++) {
            if ((tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("bk"))
                    || (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wp")
                    || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wk")))) {
                i = 8;

            } else if (tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wr") || tableroPiezas.getPiezas()[x][i].getImagen().equalsIgnoreCase("wq")) {
                return false;
            }
        }
        //revisar que no le hagan jaque ni alfíl ni reina
        for (int i = (x + 1); i < 8; i++) {
            y++;
            if (y < 8) {
                if ((tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][x].getImagen().equalsIgnoreCase("wk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
        }
        for (int i = (x + 1); i < 8; i++) {
            y1--;
            if (y1 > -1) {
                if ((tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y1].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
        }
        for (int i = (x - 1); i > -1; i--) {
            y2++;
            if (y2 < 8) {
                if ((tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wk")))) {
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y2].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
        }
        for (int i = (x - 1); i > -1; i--) {
            y3--;
            if (y3 > -1) {
                if ((tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("negro") && !tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("bk"))
                        || (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("blanco") && (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wp")
                        || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wn") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wk")))) {
                    i = -1;
                } else if (tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wb") || tableroPiezas.getPiezas()[i][y3].getImagen().equalsIgnoreCase("wq")) {
                    return false;
                }
            }
        }
        return true;
}
   ///////
    public boolean isJaque() {
        return jaque;
    }

    public void setJaque(boolean jaque) {
        this.jaque = jaque;
    }

    public String getImagenJaque() {
        return imagenJaque;
    }

    public void setImagenJaque(String imagenJaque) {
        this.imagenJaque = imagenJaque;
    }
}