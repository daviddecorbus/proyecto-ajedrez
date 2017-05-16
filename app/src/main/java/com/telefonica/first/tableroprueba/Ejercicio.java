package com.telefonica.first.tableroprueba;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    private String [][] tablero = new String [8][8];
    private int cantidadMovimientos;
    private String colorInicio;
    private String [] movimientos;
    private String textoEjercicio;
    private String estado;
    private int nivel;
    private int id;
    private int id_nivel;

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ejercicio(String[][] tablero, int cantidadMovimientos, String colorInicio, String[] movimientos, String textoEjercicio, int nivel, int id,String estado,int id_nivel) {

        this.tablero = tablero;
        this.cantidadMovimientos = cantidadMovimientos;
        this.colorInicio = colorInicio;
        this.movimientos = movimientos;
        this.textoEjercicio = textoEjercicio;
        this.estado = estado;
        this.nivel = nivel;
        this.id = id;
        this.id_nivel = id_nivel;
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

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
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


    @Override
    public String toString(){
        return "" + nivel;
    }

}


