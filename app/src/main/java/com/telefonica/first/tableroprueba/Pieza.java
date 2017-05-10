package com.telefonica.first.tableroprueba;

public abstract class Pieza {

    private String imagen;
    private String imagenResaltada;
    private String color;
    private boolean resaltado;
    private int x; //Coordenadas x
    private int y; //Coordenadas y
    public boolean jaque;
    public  String imagenJaque;
    public Pieza( String imagen, String imagenResaltada, String color, int x, int y) {

        this.imagen = imagen;
        this.imagenResaltada = imagenResaltada;
        this.color = color;
        this.resaltado = false;
        this.x = x;
        this.y = y;
        this.jaque = false;

    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagenResaltada() {
        return imagenResaltada;
    }

    public void setImagenResaltada(String imagenResaltada) {
        this.imagenResaltada = imagenResaltada;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isResaltado() {
        return resaltado;
    }

    public void setResaltado(boolean resaltado) {
        this.resaltado = resaltado;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

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

    public abstract void movimiento();

}
