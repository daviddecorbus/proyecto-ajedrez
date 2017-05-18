package com.telefonica.first.tableroprueba;


public class TamañoLetra {

    public static int tamañoLetra(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){
            return 14;
        }else if(relacion>2 && relacion<=2.3){
            return 20;
        }else if(relacion>2.3 && relacion<=2.8){
            return 22;
        }else if(relacion>2.8 && relacion<=3.3){
            return 24;
        }else{
            return 32;
        }
    }
    public static int tamañoLetraLogin(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 26;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 40;
        }else if(relacion>2.3 && relacion<=2.8){
            return 46;
        }else if(relacion>2.8 && relacion<=3.3){
            return 48;
        }else{
            return 50;
        }
    }
    public static int tamañoLetraEmailPassword(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 20;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 25;
        }else if(relacion>2.3 && relacion<=2.8){
            return 30;
        }else if(relacion>2.8 && relacion<=3.3){
            return 34;
        }else{
            return 36;
        }
    }
    public static int tamañoLetraEditText(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 14;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 16;
        }else if(relacion>2.3 && relacion<=2.8){
            return 20;
        }else if(relacion>2.8 && relacion<=3.3){
            return 24;
        }else{
            return 28;
        }
    }
    public static int tamañoLetraBoton(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 14;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 16;
        }else if(relacion>2.3 && relacion<=2.8){
            return 20;
        }else if(relacion>2.8 && relacion<=3.3){
            return 24;
        }else{
            return 28;
        }
    }
    public static int tamañoAnchoBoton(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 250;
        }else if(relacion>2 && relacion<=2.3){//htc y xperia z
            return 420;
        }else if(relacion>2.3 && relacion<=2.8){
            return 20*9;
        }else if(relacion>2.8 && relacion<=3.3){
            return 24*9;
        }else{
            return 28*9;
        }
    }
    public static int tamañoAltoBoton(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 50;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 120;
        }else if(relacion>2.3 && relacion<=2.8){
            return 140;
        }else if(relacion>2.8 && relacion<=3.3){
            return 160;
        }else{
            return 80;
        }
    }

    public static int margenesLinearIzquierda(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 40;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 80;
        }else if(relacion>2.3 && relacion<=2.8){
            return 100;
        }else if(relacion>2.8 && relacion<=3.3){
            return 100;
        }else{
            return 25;
        }
    }
    public static int margenesLinearTop(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 40;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 265;
        }else if(relacion>2.3 && relacion<=2.8){
            return 100;
        }else if(relacion>2.8 && relacion<=3.3){
            return 100;
        }else{
            return 250;
        }
    }
    public static int tamañoLetraListadoMenu(float ancho, float dpi){
        float relacion = ancho/dpi;
        System.out.println("el ancho dentro de tamañoLetra es "+ancho + " dpi es "+dpi +"la relacion es "+relacion);
        if(relacion<=2){//auchan
            return 20;
        }else if(relacion>2 && relacion<=2.3){//htc
            return 22;
        }else if(relacion>2.3 && relacion<=2.8){
            return 24;
        }else if(relacion>2.8 && relacion<=3.3){
            return 26;
        }else{
            return 36;
        }
    }
}
