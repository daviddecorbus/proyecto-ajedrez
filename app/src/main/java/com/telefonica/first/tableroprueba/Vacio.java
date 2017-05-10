package com.telefonica.first.tableroprueba;


public class Vacio extends Pieza {
    public Vacio(String imagen, String imagenResaltada, String color, int x, int y) {
        super(imagen, imagenResaltada, color, x, y);
    }

    @Override
    public void movimiento() {
        System.out.println("soy un hueco vacio de color "+this.getColor());
    }
}
