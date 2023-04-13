

import java.util.*;


 public class GameOfFifteen {
	 
	 
     private int[] tablero;
     private int tamano;
     private int vacio;
     private static int nodoz=0;
     private int f;

     public GameOfFifteen(int[] tablero) {
         this.tablero = tablero;
         this.tamano = (int) Math.sqrt(tablero.length);
         for (int i = 0; i < tablero.length; i++) {
             if (tablero[i] == 0) {
                 this.vacio = i;
                 break;
             }
         }
         this.f = getHeuristica();
     }

     public int getHeuristica() {
    	    int distancia = 0;
    	    for (int i = 0; i < tablero.length; i++) {
    	        if (tablero[i] == 0) continue;
    	        int filaActual = i / tamano;
    	        int columnaActual = i % tamano;
    	        int filaObjetivo = (tablero[i] - 1) / tamano;
    	        int columnaObjetivo = (tablero[i] - 1) % tamano;
    	        int distanciaFila = filaActual - filaObjetivo;
    	        int distanciaColumna = columnaActual - columnaObjetivo;
    	        distancia += (distanciaFila < 0 ? -distanciaFila : distanciaFila) + (distanciaColumna < 0 ? -distanciaColumna : distanciaColumna);
    	    }
    	    return distancia;
    	}

     public boolean esMeta() {
         for (int i = 0; i < tablero.length - 1; i++) {
             if (tablero[i] != i + 1) {
                 return false;
             }
         }
         return true;
     }

     public List<GameOfFifteen> expandir() {
    	    List<GameOfFifteen> sucesores = new ArrayList<>(4);
    	    int filaVacio = vacio / tamano;
    	    int columnaVacio = vacio % tamano;
    	    int[] nuevoTablero = tablero.clone();
    	    if (filaVacio > 0) {
    	        nuevoTablero[vacio] = nuevoTablero[vacio - tamano];
    	        nuevoTablero[vacio - tamano] = 0;
    	        sucesores.add(new GameOfFifteen(nuevoTablero));
    	        nuevoTablero = tablero.clone(); // resetear el tablero
    	        nodoz++;
    	    }
    	    if (filaVacio < tamano - 1) {
    	        nuevoTablero[vacio] = nuevoTablero[vacio + tamano];
    	        nuevoTablero[vacio + tamano] = 0;
    	        sucesores.add(new GameOfFifteen(nuevoTablero));
    	        nuevoTablero = tablero.clone();
    	        nodoz++;
    	    }
    	    if (columnaVacio > 0) {
    	        nuevoTablero[vacio] = nuevoTablero[vacio - 1];
    	        nuevoTablero[vacio - 1] = 0;
    	        sucesores.add(new GameOfFifteen(nuevoTablero));
    	        nuevoTablero = tablero.clone();
    	        nodoz++;
    	    }
    	    if (columnaVacio < tamano - 1) {
    	        nuevoTablero[vacio] = nuevoTablero[vacio + 1];
    	        nuevoTablero[vacio + 1] = 0;
    	        sucesores.add(new GameOfFifteen(nuevoTablero));
    	        nodoz++;
    	    }
    	    return sucesores;
    	}

     public int getF() {
         return f;
     }

     public void setF(int f) {
         this.f = f;
     }

     public void imprimir() {
    	    for (int i = 0; i < tablero.length; i++) {
    	        System.out.print(tablero[i] + " ");
    	        if ((i + 1) % 3 == 0) {
    	            System.out.println();
    	        }
    	    }
    	}
     
     
     public static void main(String[] args) {
    	    int[] tablero = {2, 3, 0, 1, 4, 6, 7, 5, 8};
    	    GameOfFifteen juego = new GameOfFifteen(tablero);
    	    System.out.println("Tablero inicial:");
    	    juego.imprimir();
    	    JuegoDel15Solver solver = new JuegoDel15Solver(juego);
    	    GameOfFifteen solucion = solver.resolver();
    	    if (solucion == null) {
    	        System.out.println("No se encontró solución");
    	    } else {
    	        System.out.println("Solución encontrada:");
    	        solucion.imprimir();
    	        System.out.println("Nodoz o conteo de movimientos: "+  nodoz);
    	        
    	    }
    	}
     
 }