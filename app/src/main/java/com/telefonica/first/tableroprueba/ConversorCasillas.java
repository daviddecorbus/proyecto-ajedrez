package com.telefonica.first.tableroprueba;

/**
 * Created by David Garrido on 13/05/2017.
 */

public class ConversorCasillas {

    public static String conversor (String movimientos){
        String movimientosFinales="";
        String [] cadenaMovimientos = movimientos.split("/");

        for (int i = 0; i < cadenaMovimientos.length ; i++) {
            String [] cadena =cadenaMovimientos[i].split("-");
            String [] cadena2 = {cadena[1],cadena[0],cadena[3],cadena[2]};
            System.out.println("cadena movimientos "+cadena[0]+" "+cadena[1]+" "+cadena[2]+" "+cadena[3]+" ");
            System.out.println("cadena2 movimientos "+cadena2[0]+" "+cadena2[1]+" "+cadena2[2]+" "+cadena2[3]+" ");
            for (int j = 0; j < 4 ; j++) {

                if (j==0) {
                    switch (cadena2[j]) {
                        case "8":
                            movimientosFinales = movimientosFinales + "0";
                            break;
                        case "7":
                            movimientosFinales = movimientosFinales + "1";
                            break;
                        case "6":
                            movimientosFinales = movimientosFinales + "2";
                            break;
                        case "5":
                            movimientosFinales = movimientosFinales + "3";
                            break;
                        case "4":
                            movimientosFinales = movimientosFinales + "4";
                            break;
                        case "3":
                            movimientosFinales = movimientosFinales + "5";
                            break;
                        case "2":
                            movimientosFinales = movimientosFinales + "6";
                            break;
                        case "1":
                            movimientosFinales = movimientosFinales + "7";
                            break;
                        default:
                            movimientosFinales = movimientosFinales + "0";
                            break;
                    }
                }
                if (j==1 || j==3) {

                    switch (cadena2[j]) {
                        case "a":
                            movimientosFinales = movimientosFinales + "-0";
                            break;
                        case "b":
                            movimientosFinales = movimientosFinales + "-1";
                            break;
                        case "c":
                            movimientosFinales = movimientosFinales + "-2";
                            break;
                        case "d":
                            movimientosFinales = movimientosFinales + "-3";
                            break;
                        case "e":
                            movimientosFinales = movimientosFinales + "-4";
                            break;
                        case "f":
                            movimientosFinales = movimientosFinales + "-5";
                            break;
                        case "g":
                            movimientosFinales = movimientosFinales + "-6";
                            break;
                        case "h":
                            movimientosFinales = movimientosFinales + "-7";
                            break;
                        default:
                            movimientosFinales = movimientosFinales + "-0";
                            break;
                    }
                }
                if (j==2) {
                    switch (cadena2[j]) {
                        case "8":
                            movimientosFinales = movimientosFinales + "-0";
                            break;
                        case "7":
                            movimientosFinales = movimientosFinales + "-1";
                            break;
                        case "6":
                            movimientosFinales = movimientosFinales + "-2";
                            break;
                        case "5":
                            movimientosFinales = movimientosFinales + "-3";
                            break;
                        case "4":
                            movimientosFinales = movimientosFinales + "-4";
                            break;
                        case "3":
                            movimientosFinales = movimientosFinales + "-5";
                            break;
                        case "2":
                            movimientosFinales = movimientosFinales + "-6";
                            break;
                        case "1":
                            movimientosFinales = movimientosFinales + "-7";
                            break;
                        default:
                            movimientosFinales = movimientosFinales + "-0";
                            break;
                    }
                }

            }
            if ((i+1)!= cadenaMovimientos.length){
                movimientosFinales = movimientosFinales + "/";
            }
        }
        System.out.println("movimientosFinales "+movimientosFinales);
        return movimientosFinales;
    }
}
