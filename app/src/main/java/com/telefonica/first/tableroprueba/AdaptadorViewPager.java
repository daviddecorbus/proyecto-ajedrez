package com.telefonica.first.tableroprueba;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdaptadorViewPager extends FragmentPagerAdapter {

    // en esta variable almacenaremos el nº de secciones
    private int numeroDeSecciones;
    //en esta variable almacenaremos los titulos de las secciones
    private String [] titulosSeccion;

    public AdaptadorViewPager(FragmentManager fm, int numeroDeSecciones,String[] titulosSeccion) {
        super(fm);
        this.numeroDeSecciones=numeroDeSecciones;
        this.titulosSeccion = titulosSeccion;
    }


    @Override
    public Fragment getItem(int position) {

        Bundle datos = new Bundle();
        // recibimos la posición por parámetro y en función de ella devolvemos el Fragment correspondiente a esa sección.
        switch (position) {


            case 0: // siempre empieza desde 0
             return new Login();

            case 1:
                return new Registro();

            // si la posición recibida no se corresponde a ninguna sección
            default:
                return null;
        }

    }


    /*  getCount() devuelve el nº de secciones, dato que recibiremos cuando instanciemos el adaptador
        desde nuestra actividad principal */
    @Override
    public int getCount() {
        return numeroDeSecciones;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titulosSeccion[position];
    }



}