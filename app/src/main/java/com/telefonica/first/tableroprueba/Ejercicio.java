package com.telefonica.first.tableroprueba;

/**
 * Created by David Garrido on 10/05/2017.
 */

public class Ejercicio {
    private String [][] tablero = new String [8][8];
    private int cantidadMovimientos;
    private String colorInicio;
    private String [] movimientos;
    private String textoEjercicio;
    private String estado;



    public Ejercicio(String[][] tablero, int cantidadMovimientos, String colorInicio, String[] movimientos, String textoEjercicio) {

        this.tablero = tablero;
        this.cantidadMovimientos = cantidadMovimientos;
        this.colorInicio = colorInicio;
        this.movimientos = movimientos;
        this.textoEjercicio = textoEjercicio;
        this.estado = "sin empezar";
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public void setCantidadMovimientos(int cantidadMovimientos) {
        this.cantidadMovimientos = cantidadMovimientos;
    }

    public String[] getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(String[] movimientos) {
        this.movimientos = movimientos;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public String getColorInicio() {
        return colorInicio;
    }

    public void setColorInicio(String colorInicio) {
        this.colorInicio = colorInicio;
    }

    public String getTextoEjercicio() {
        return textoEjercicio;
    }

    public void setTextoEjercicio(String textoEjercicio) {
        this.textoEjercicio = textoEjercicio;
    }


}


