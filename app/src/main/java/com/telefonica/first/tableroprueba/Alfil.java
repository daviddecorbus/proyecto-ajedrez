package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.TableroEjercicio.colorPiezaSeleccionada;
import static com.telefonica.first.tableroprueba.TableroEjercicio.resaltado;
import static com.telefonica.first.tableroprueba.TableroEjercicio.tableroPiezas;

public class Alfil extends Pieza {
    public Alfil(String imagen, String imagenResaltada, String color, int x, int y) {
        super(imagen, imagenResaltada, color, x, y);
    }

    @Override
    public void movimiento() {
        System.out.println("soy una alfil de color "+this.getColor());
        int x = this.getX();
        int y = this.getY();
        int y1 = y;
        int y2 = y;
        int y3 = y;
        this.setResaltado(true);
        resaltado = true;
        //movimiento abajo-derecha
        for (int i = (x+1); i < 8; i++) {
            y++;
            if(y<8) {
                if (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[i][y].setResaltado(true);
                } else if (!tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    tableroPiezas.getPiezas()[i][y].setResaltado(true);
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    i = 8;
                }
            }
        }
        //movimiento abajo-izquierda
        for (int i = (x+1); i < 8; i++) {
            y1--;
            if(y1>-1) {
                if (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[i][y1].setResaltado(true);
                } else if (!tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    tableroPiezas.getPiezas()[i][y1].setResaltado(true);
                    i = 8;
                } else if (tableroPiezas.getPiezas()[i][y1].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    i = 8;
                }
            }
        }
        //movimiento arriba-derecha
        for (int i = (x-1); i > -1; i--) {
            y2++;
            if(y2<8) {
                if (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[i][y2].setResaltado(true);
                } else if (!tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    tableroPiezas.getPiezas()[i][y2].setResaltado(true);
                    i = -1;
                } else if (tableroPiezas.getPiezas()[i][y2].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    i = -1;
                }
            }
        }
      //movimiento arriba-izquierda
        for (int i = (x-1); i > -1; i--) {
            y3--;
            if(y3>-1) {
                if (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[i][y3].setResaltado(true);
                } else if (!tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    tableroPiezas.getPiezas()[i][y3].setResaltado(true);
                    i = -1;
                } else if (tableroPiezas.getPiezas()[i][y3].getColor().equalsIgnoreCase(colorPiezaSeleccionada)) {
                    i = -1;
                }
            }
        }
    }
}
