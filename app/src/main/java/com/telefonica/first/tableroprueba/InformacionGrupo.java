package com.telefonica.first.tableroprueba;


import java.util.ArrayList;

public class InformacionGrupo {

    private String nombre;
    private ArrayList<ChildInfo> submenus = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<ChildInfo> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<ChildInfo> submenus) {
        this.submenus = submenus;
    }

}