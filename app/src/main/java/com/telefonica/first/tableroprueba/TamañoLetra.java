package com.telefonica.first.tableroprueba;

/**
 * Created by David Garrido on 13/05/2017.
 */

public class TamañoLetra {

    public static int tamañoLetra(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){
            return 16;
        }else if(relacion>2 && relacion<=2.3){
            return 22;
        }else if(relacion>2.3 && relacion<=2.8){
            return 28;
        }else if(relacion>2.8 && relacion<=3.3){
            return 30;
        }else{
            return 32;
        }
    }

}
