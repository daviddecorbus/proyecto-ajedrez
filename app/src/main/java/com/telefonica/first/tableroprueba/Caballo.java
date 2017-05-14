package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.TableroEjercicio.colorPiezaSeleccionada;
import static com.telefonica.first.tableroprueba.TableroEjercicio.resaltado;
import static com.telefonica.first.tableroprueba.TableroEjercicio.tableroPiezas;

public class Caballo extends Pieza {
    public Caballo(String imagen, String imagenResaltada, String color, int x, int y) {
        super(imagen, imagenResaltada, color, x, y);
    }

    @Override
    public void movimiento() {
        System.out.println("soy un caballo de color " + this.getColor());
        int x = this.getX();
        int y = this.getY();
        this.setResaltado(true);
        resaltado = true;

            if (x - 2 >= 0 && y - 1 >= 0) {
                if (!tableroPiezas.getPiezas()[x - 2][y - 1].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x - 2][y - 1].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x - 2][y - 1].setResaltado(true);
                }
            }
            if (x - 2 >= 0 && y + 1 < 8) {
                if (!tableroPiezas.getPiezas()[x - 2][y + 1].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x - 2][y + 1].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x - 2][y + 1].setResaltado(true);
                }
            }
            if (x + 2 < 8 && y - 1 >= 0) {
                if (!tableroPiezas.getPiezas()[x + 2][y - 1].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x + 2][y - 1].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x + 2][y - 1].setResaltado(true);
                }
            }
            if (x + 2 < 8 && y + 1 < 8) {
                if (!tableroPiezas.getPiezas()[x + 2][y + 1].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x + 2][y + 1].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x + 2][y + 1].setResaltado(true);
                }
            }
            if (y - 2 >= 0 && x - 1 >= 0) {
                if (!tableroPiezas.getPiezas()[x - 1][y - 2].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x - 1][y - 2].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x - 1][y - 2].setResaltado(true);
                }
            }
            if (y - 2 >= 0 && x + 1 < 8) {
                if (!tableroPiezas.getPiezas()[x + 1][y - 2].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x + 1][y - 2].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x + 1][y - 2].setResaltado(true);
                }
            }
            if (y + 2 < 8 && x - 1 >= 0) {
                if (!tableroPiezas.getPiezas()[x - 1][y + 2].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x - 1][y + 2].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x - 1][y + 2].setResaltado(true);
                }
            }
            if (y + 2 < 8 && x + 1 < 8) {
                if (!tableroPiezas.getPiezas()[x + 1][y + 2].getColor().equalsIgnoreCase(colorPiezaSeleccionada) || tableroPiezas.getPiezas()[x + 1][y + 2].getColor().equalsIgnoreCase("neutro")) {
                    tableroPiezas.getPiezas()[x + 1][y + 2].setResaltado(true);
                }
            }


    }

}
