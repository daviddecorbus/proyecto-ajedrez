package com.telefonica.first.tableroprueba;

import static com.telefonica.first.tableroprueba.TableroEjercicio.ejercicio;
import static com.telefonica.first.tableroprueba.TableroEjercicio.turnoColor;
import static com.telefonica.first.tableroprueba.TableroEjercicio.turnoResaltado;

public class Tablero {
    private String[][] tablero = new String[8][8];
    private String [] piezasRecibidas = new String[64];
    private Pieza[][] piezas = new Pieza[8][8];

    /**
     * Tablero vacio
     */
    public Tablero() {
    }

    /**
     * Tablero con un constructor
     *
     * @param piezasRecibidas piezas
     */
    public Tablero(Pieza[][] piezasRecibidas) {
        this.piezas = piezasRecibidas;
    }

    /**
     * Devuelve el array multidimensional con las piezas
     *
     * @return el array multidimensional con las piezas
     */
    public Pieza[][] getPiezas() {
        return piezas;
    }


    /**
     * Cambia el array multidimensional
     *
     * @param piezas array multidimensional
     */
    public void setPiezas(Pieza[][] piezas) {
        this.piezas = piezas;
    }


    /**
     * Des selecciona todas las piezas
     */
    public void desresaltarTodos() {
        turnoResaltado = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piezas[i][j].setResaltado(false);
            }
        }
    }

    private void convertirArray(){
        int cantidad=0;

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(cantidad==piezasRecibidas.length) break;
                tablero[i][j]=piezasRecibidas[cantidad];
                cantidad++;
            }
        }
    }


    /**
     * Permite cargar el tablero para crear jugadas
     * @param tablero tablero
     */
    public void cargarTableroJugada(String [][] tablero) {
        this.tablero = tablero;
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(tablero[i][j].equalsIgnoreCase("br")){
                    piezas[i][j]= new Torre("br","brr","negro", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("wr")) {
                    piezas[i][j] = new Torre("wr", "wrr", "blanco", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("bn")) {
                    piezas[i][j] = new Caballo("bn", "bnr", "negro", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("wn")) {
                    piezas[i][j] = new Caballo("wn", "wnr", "blanco", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("bb")) {
                    piezas[i][j] = new Alfil("bb", "bbr", "negro", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("wb")) {
                    piezas[i][j] = new Alfil("wb", "wbr", "blanco", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("bq")) {
                    piezas[i][j] = new Reina("bq", "bqr", "negro", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("wq")) {
                    piezas[i][j] = new Reina("wq", "wqr", "blanco", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("bk")) {
                    piezas[i][j] = new Rey("bk", "bkr", "negro", i, j,false, "bkj");
                }else if(tablero[i][j].equalsIgnoreCase("wk")) {
                    piezas[i][j] = new Rey("wk", "wkr", "blanco", i, j,false, "wkj");
                }else if(tablero[i][j].equalsIgnoreCase("bp")) {
                    piezas[i][j] = new Peon("bp", "bpr", "negro", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("wp")) {
                    piezas[i][j] = new Peon("wp", "wpr", "blanco", i, j);
                }else if(tablero[i][j].equalsIgnoreCase("v")) {
                    piezas[i][j] = new Vacio("v", "vr", "neutro", i, j);
                }
            }
        }
    }
    /**
     * Carga el tablero
     *
     * @param tablero Tablero a cargar
     * @param guia    La imagen de la guia si tiene
     */

    public void cargarTablero(String[][] tablero, String guia) {
        // convertirArray();
        this.tablero = tablero;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (guia.equalsIgnoreCase("vr")) {

                    if (tablero[i][j].equalsIgnoreCase("br")) {
                        piezas[i][j] = new Torre("br", "brr", "negro", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("wr")) {
                        piezas[i][j] = new Torre("wr", "wrr", "blanco", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("bn")) {
                        piezas[i][j] = new Caballo("bn", "bnr", "negro", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("wn")) {
                        piezas[i][j] = new Caballo("wn", "wnr", "blanco", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("bb")) {
                        piezas[i][j] = new Alfil("bb", "bbr", "negro", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("wb")) {
                        piezas[i][j] = new Alfil("wb", "wbr", "blanco", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("bq")) {
                        piezas[i][j] = new Reina("bq", "bqr", "negro", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("wq")) {
                        piezas[i][j] = new Reina("wq", "wqr", "blanco", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("bk")) {
                        piezas[i][j] = new Rey("bk", "bkr", "negro", i, j, false, "bkj");
                        TableroEjercicio.xReyNegro = i;//actualiza la posición de los reyes
                        TableroEjercicio.yReyNegro = j;
                    } else if (tablero[i][j].equalsIgnoreCase("wk")) {
                        piezas[i][j] = new Rey("wk", "wkr", "blanco", i, j, false, "wkj");
                        TableroEjercicio.xReyBlanco = i;
                        TableroEjercicio.yReyBlanco = j;
                    } else if (tablero[i][j].equalsIgnoreCase("bp")) {
                        piezas[i][j] = new Peon("bp", "bpr", "negro", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("wp")) {
                        piezas[i][j] = new Peon("wp", "wpr", "blanco", i, j);
                    } else if (tablero[i][j].equalsIgnoreCase("v")) {
                        piezas[i][j] = new Vacio("v", "vr", "neutro", i, j);
                    }
                } else {
                    if (turnoColor.equalsIgnoreCase("blanco")&& ejercicio.getColorInicio().equalsIgnoreCase("blanco")) {
                        if (tablero[i][j].equalsIgnoreCase("br")) {
                            piezas[i][j] = new Torre("br", "br", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wr")) {
                            piezas[i][j] = new Torre("wr", "wrr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bn")) {
                            piezas[i][j] = new Caballo("bn", "bn", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wn")) {
                            piezas[i][j] = new Caballo("wn", "wnr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bb")) {
                            piezas[i][j] = new Alfil("bb", "bb", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wb")) {
                            piezas[i][j] = new Alfil("wb", "wbr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bq")) {
                            piezas[i][j] = new Reina("bq", "bq", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wq")) {
                            piezas[i][j] = new Reina("wq", "wqr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bk")) {
                            piezas[i][j] = new Rey("bk", "bk", "negro", i, j, false, "bkj");
                            TableroEjercicio.xReyNegro = i;     //actualiza la posición de los reyes
                            TableroEjercicio.yReyNegro = j;
                        } else if (tablero[i][j].equalsIgnoreCase("wk")) {
                            piezas[i][j] = new Rey("wk", "wkr", "blanco", i, j, false, "wkj");
                            TableroEjercicio.xReyBlanco = i;
                            TableroEjercicio.yReyBlanco = j;
                        } else if (tablero[i][j].equalsIgnoreCase("bp")) {
                            piezas[i][j] = new Peon("bp", "bp", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wp")) {
                            piezas[i][j] = new Peon("wp", "wpr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("v")) {
                            piezas[i][j] = new Vacio("v","v", "neutro", i, j);
                        }
                    } else if (turnoColor.equalsIgnoreCase("negro")&& ejercicio.getColorInicio().equalsIgnoreCase("blanco")) {
                        if (tablero[i][j].equalsIgnoreCase("br")) {
                            piezas[i][j] = new Torre("br", "brm", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wr")) {
                            piezas[i][j] = new Torre("wr", "wr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bn")) {
                            piezas[i][j] = new Caballo("bn", "bnm", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wn")) {
                            piezas[i][j] = new Caballo("wn", "wn", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bb")) {
                            piezas[i][j] = new Alfil("bb", "bbm", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wb")) {
                            piezas[i][j] = new Alfil("wb", "wb", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bq")) {
                            piezas[i][j] = new Reina("bq", "bqm", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wq")) {
                            piezas[i][j] = new Reina("wq", "wq", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bk")) {
                            piezas[i][j] = new Rey("bk", "bkm", "negro", i, j, false, "bkj");
                            TableroEjercicio.xReyNegro = i;     //actualiza la posición de los reyes
                            TableroEjercicio.yReyNegro = j;
                        } else if (tablero[i][j].equalsIgnoreCase("wk")) {
                            piezas[i][j] = new Rey("wk", "wk", "blanco", i, j, false, "wkj");
                            TableroEjercicio.xReyBlanco = i;
                            TableroEjercicio.yReyBlanco = j;
                        } else if (tablero[i][j].equalsIgnoreCase("bp")) {
                            piezas[i][j] = new Peon("bp", "bpm", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wp")) {
                            piezas[i][j] = new Peon("wp", "wp", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("v")) {
                            piezas[i][j] = new Vacio("v","vm", "neutro", i, j);
                        }
                    } else if (turnoColor.equalsIgnoreCase("blanco") && ejercicio.getColorInicio().equalsIgnoreCase("negro")) {
                        if (tablero[i][j].equalsIgnoreCase("br")) {
                            piezas[i][j] = new Torre("br", "br", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wr")) {
                            piezas[i][j] = new Torre("wr", "wrm", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bn")) {
                            piezas[i][j] = new Caballo("bn", "bn", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wn")) {
                            piezas[i][j] = new Caballo("wn", "wnm", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bb")) {
                            piezas[i][j] = new Alfil("bb", "bb", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wb")) {
                            piezas[i][j] = new Alfil("wb", "wbm", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bq")) {
                            piezas[i][j] = new Reina("bq", "bq", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wq")) {
                            piezas[i][j] = new Reina("wq", "wqm", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bk")) {
                            piezas[i][j] = new Rey("bk", "bk", "negro", i, j, false, "bkj");
                            TableroEjercicio.xReyNegro = i;//actualiza la posición de los reyes
                            TableroEjercicio.yReyNegro = j;
                        } else if (tablero[i][j].equalsIgnoreCase("wk")) {
                            piezas[i][j] = new Rey("wk", "wkm", "blanco", i, j, false, "wkj");
                            TableroEjercicio.xReyBlanco = i;
                            TableroEjercicio.yReyBlanco = j;
                        } else if (tablero[i][j].equalsIgnoreCase("bp")) {  //EN ESTA FILA ESTÁ LA PRUEBA DE IMAGEN MORADA. CUANDO SE CARGUEN TODAS LAS IMAGENES
                            //HABRÁ QUE CAMBIAR TODA LA CARGA DE IMAGENES DEPENDIENDO DEL COLOR QUE EMPIECE
                            piezas[i][j] = new Peon("bp", "bp", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wp")) {
                            piezas[i][j] = new Peon("wp", "wpm", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("v")) {
                            piezas[i][j] = new Vacio("v", "vm", "neutro", i, j);
                        }
                    } else if (turnoColor.equalsIgnoreCase("negro") && ejercicio.getColorInicio().equalsIgnoreCase("negro")) {
                        if (tablero[i][j].equalsIgnoreCase("br")) {
                            piezas[i][j] = new Torre("br", "brr", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wr")) {
                            piezas[i][j] = new Torre("wr", "wr", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bn")) {
                            piezas[i][j] = new Caballo("bn", "bnr", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wn")) {
                            piezas[i][j] = new Caballo("wn", "wn", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bb")) {
                            piezas[i][j] = new Alfil("bb", "bbr", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wb")) {
                            piezas[i][j] = new Alfil("wb", "wb", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bq")) {
                            piezas[i][j] = new Reina("bq", "bqr", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wq")) {
                            piezas[i][j] = new Reina("wq", "wq", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("bk")) {
                            piezas[i][j] = new Rey("bk", "bkr", "negro", i, j, false, "bkj");
                            TableroEjercicio.xReyNegro = i;//actualiza la posición de los reyes
                            TableroEjercicio.yReyNegro = j;
                        } else if (tablero[i][j].equalsIgnoreCase("wk")) {
                            piezas[i][j] = new Rey("wk", "wk", "blanco", i, j, false, "wkj");
                            TableroEjercicio.xReyBlanco = i;
                            TableroEjercicio.yReyBlanco = j;
                        } else if (tablero[i][j].equalsIgnoreCase("bp")) {  //EN ESTA FILA ESTÁ LA PRUEBA DE IMAGEN MORADA. CUANDO SE CARGUEN TODAS LAS IMAGENES
                            //HABRÁ QUE CAMBIAR TODA LA CARGA DE IMAGENES DEPENDIENDO DEL COLOR QUE EMPIECE
                            piezas[i][j] = new Peon("bp", "bpr", "negro", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("wp")) {
                            piezas[i][j] = new Peon("wp", "wp", "blanco", i, j);
                        } else if (tablero[i][j].equalsIgnoreCase("v")) {
                            piezas[i][j] = new Vacio("v", "v", "neutro", i, j);
                        }
                    }
                }
            }
        }
    }
}






