package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.TableroEjercicio.colorPiezaSeleccionada;
import static com.telefonica.first.tableroprueba.TableroEjercicio.resaltado;
import static com.telefonica.first.tableroprueba.TableroEjercicio.tableroPiezas;


public class Torre extends Pieza {
    public Torre(String imagen, String imagenResaltada, String color, int x, int y) {
        super(imagen, imagenResaltada, color, x, y);
    }

    @Override
    public void movimiento() {
        System.out.println("soy una torre de color " + this.getColor());
        int x = this.getX();
        int y = this.getY();
        this.setResaltado(true);
        resaltado = true;
        //movimiento vertical abajo
        for (int i = (x+1); i < 8; i++) {
        if (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("neutro")) {
            tableroPiezas.getPiezas()[i][y].setResaltado(true);
        }else if(!tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
            tableroPiezas.getPiezas()[i][y].setResaltado(true);
            i=8;
        }else if(tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
            i=8;
        }
        }
        //movimiento vertical arriba
        for (int i = (x-1); i >-1; i--) {
            if (tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase("neutro")) {
                tableroPiezas.getPiezas()[i][y].setResaltado(true);
            }else if(!tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                tableroPiezas.getPiezas()[i][y].setResaltado(true);
                i=-1;
            }else if(tableroPiezas.getPiezas()[i][y].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                i=-1;
            }
        }
        //movimiento horizontal derecha
        for (int i = (y+1); i < 8; i++) {
            if (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("neutro")) {
                tableroPiezas.getPiezas()[x][i].setResaltado(true);
            }else if(!tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                tableroPiezas.getPiezas()[x][i].setResaltado(true);
                i=8;
            }else if(tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                i=8;
            }
        }
        //movimiento horizontal izquierda
        for (int i = (y-1); i >-1; i--) {
            if (tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase("neutro")) {
                tableroPiezas.getPiezas()[x][i].setResaltado(true);
            }else if(!tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                tableroPiezas.getPiezas()[x][i].setResaltado(true);
                i=-1;
            }else if(tableroPiezas.getPiezas()[x][i].getColor().equalsIgnoreCase(colorPiezaSeleccionada)){
                i=-1;
            }
        }
    }
}
