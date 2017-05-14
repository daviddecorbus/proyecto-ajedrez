package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.TableroEjercicio.colorPiezaSeleccionada;
import static com.telefonica.first.tableroprueba.TableroEjercicio.tableroPiezas;
import static com.telefonica.first.tableroprueba.TableroEjercicio.resaltado;

public class Peon extends Pieza {
    public Peon(String imagen, String imagenResaltada, String color, int x, int y) {
        super(imagen, imagenResaltada, color, x, y);
    }

    @Override
    public void movimiento() {
        System.out.println("soy un peon de color " + this.getColor());
        int x = this.getX();
        int y = this.getY();
        this.setResaltado(true);
        resaltado = true;
        if (colorPiezaSeleccionada.equalsIgnoreCase("blanco")) {

            if (x > 0) {
                if (tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("negro") || tableroPiezas.getPiezas()[x - 1][y].getColor().equalsIgnoreCase("blanco")) {

                } else {
                    tableroPiezas.getPiezas()[x - 1][y].setResaltado(true);
                    if(x-2>-1) {
                        if (tableroPiezas.getPiezas()[x - 2][y].getColor().equalsIgnoreCase("neutro") && x == 6) {
                            tableroPiezas.getPiezas()[x - 2][y].setResaltado(true);
                        }
                    }
                }
                if (y > 0 && y < 7) {
                    if (tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("negro")) {
                        tableroPiezas.getPiezas()[x - 1][y - 1].setResaltado(true);
                    }
                    if (tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("negro")) {
                        tableroPiezas.getPiezas()[x - 1][y + 1].setResaltado(true);
                    }
                } else if (y == 0) {
                    if (tableroPiezas.getPiezas()[x - 1][y + 1].getColor().equalsIgnoreCase("negro")) {
                        tableroPiezas.getPiezas()[x - 1][y + 1].setResaltado(true);
                    }
                } else if (y == 7) {
                    if (tableroPiezas.getPiezas()[x - 1][y - 1].getColor().equalsIgnoreCase("negro")) {
                        tableroPiezas.getPiezas()[x - 1][y - 1].setResaltado(true);
                    }
                }
            }
        }else if(colorPiezaSeleccionada.equalsIgnoreCase("negro")){
            if (x <7) {
                if (tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("negro") || tableroPiezas.getPiezas()[x + 1][y].getColor().equalsIgnoreCase("blanco")) {

                } else {
                    tableroPiezas.getPiezas()[x + 1][y].setResaltado(true);
                    if(x+2<8) {
                        if (tableroPiezas.getPiezas()[x + 2][y].getColor().equalsIgnoreCase("neutro") && x == 1) {
                            tableroPiezas.getPiezas()[x + 2][y].setResaltado(true);
                        }
                    }
                }
                if (y > 0 && y < 7) {
                    if (tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("blanco")) {
                        tableroPiezas.getPiezas()[x + 1][y - 1].setResaltado(true);
                    }
                    if (tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("blanco")) {
                        tableroPiezas.getPiezas()[x + 1][y + 1].setResaltado(true);
                    }
                } else if (y == 0) {
                    if (tableroPiezas.getPiezas()[x + 1][y + 1].getColor().equalsIgnoreCase("blanco")) {
                        tableroPiezas.getPiezas()[x + 1][y + 1].setResaltado(true);
                    }
                } else if (y == 7) {
                    if (tableroPiezas.getPiezas()[x + 1][y - 1].getColor().equalsIgnoreCase("blanco")) {
                        tableroPiezas.getPiezas()[x + 1][y - 1].setResaltado(true);
                    }
                }
            }
        }
    }

public static void convertirPeon(int x,int y,String imagen){
   if(tableroPiezas.getPiezas()[x][y ].getColor().equalsIgnoreCase("blanco")){
       switch (imagen) {
           case "wq":
               tableroPiezas.getPiezas()[x][y] = new Reina("wq", "wqr", "blanco", x, y);
               break;
           case "wb":
               tableroPiezas.getPiezas()[x][y] = new Alfil("wb", "wbr", "blanco", x, y);
               break;
           case "wr":
               tableroPiezas.getPiezas()[x][y] = new Torre("wr", "wrr", "blanco", x, y);
               break;
           case "wn":
               tableroPiezas.getPiezas()[x][y] = new Caballo("wn", "wnr", "blanco", x, y);
               break;
       }
   }
   else {
       switch (imagen){
           case "bq":
               tableroPiezas.getPiezas()[x][y] = new Reina("bq", "bqr", "negro", x, y);
               break;
           case "bb":
               tableroPiezas.getPiezas()[x][y] = new Alfil("bb", "bbr", "negro", x, y);
               break;
           case "br":
               tableroPiezas.getPiezas()[x][y] = new Torre("br", "brr", "negro", x, y);
               break;
           case "bn":
               tableroPiezas.getPiezas()[x][y] = new Caballo("bn", "bnr", "negro", x, y);
               break;
       }

    }
}

}
