 import java.util.*;

 public class GameOfFifteen {

     private static int nodosVisitados = 0;
     
     public static void main(String[] args) {
    	List<JuegoDel15Solver> tableros = new ArrayList<>();
 	    int[] tablero = {3, 2, 4, 1, 6, 0, 7, 5, 8};
 	    int vacio, i = 0;
 	    System.out.println("Tablero inicial:");
 	    imprimir(tablero);
 	    vacio = espacioVacio(tablero);
 	    tableros.addAll(expandir(tablero, vacio));
 	    while(!esMeta(tablero)&&i<tableros.size()) {
 	    	vacio = espacioVacio(tablero);
 	 	    tableros.addAll(expandir(tablero, vacio));
 	 	    tablero = tableros.get(i++).getTablero();
 	    }
 	    if(esMeta(tablero)){
 	    	System.out.println("Solucion encontrada:");
 	    	imprimir(tablero);
 	    	System.out.println("Nodos visitados: "+nodosVisitados);
 	    }else {
 	    	System.out.println("Solucion NO encontrada:");
 	    }
     }

     public static int espacioVacio(int[] tablero) {
    	 int i;
         for ( i = 0; i < tablero.length; i++) {
             if (tablero[i] == 0) {
                 break;
             }
         }
		return i;
     }

     public static int getHeuristica(int[] tablero) {
         int distancia = 0;
         int tamano = (int)Math.sqrt(tablero.length);
         for (int i = 0; i < tablero.length; i++) {
             if (tablero[i] == 0) continue;
             int filaActual = i / tamano;
             int columnaActual = i % tamano;
             int filaObjetivo = (tablero[i] - 1) / tamano;
             int columnaObjetivo = (tablero[i] - 1) % tamano;
             distancia += Math.abs(filaActual - filaObjetivo) + Math.abs(columnaActual - columnaObjetivo);
         }
         return distancia;
     }

     public static boolean esMeta(int[] tablero) {
         for (int i = 0; i < tablero.length - 1; i++) {
             if (tablero[i] != i + 1) {
                 return false;
             }
         }
         return true;
     }

     public static List<JuegoDel15Solver> expandir(int[] tablero, int vacio) {
    	 int tamano = (int)Math.sqrt(tablero.length);
         List<JuegoDel15Solver> sucesores = new ArrayList<>();
         int filaVacio = vacio / tamano;
         int columnaVacio = vacio % tamano;
         if (filaVacio > 0) {
             int[] nuevoTablero = tablero.clone();
             nuevoTablero[vacio] = nuevoTablero[vacio - tamano];
             nuevoTablero[vacio - tamano] = 0;
             sucesores.add(new JuegoDel15Solver(nuevoTablero, getHeuristica(nuevoTablero),  vacio));
             nodosVisitados++;
         }
         if (filaVacio < tamano - 1) {
             int[] nuevoTablero = tablero.clone();
             nuevoTablero[vacio] = nuevoTablero[vacio + tamano];
             nuevoTablero[vacio + tamano] = 0;
             sucesores.add(new JuegoDel15Solver(nuevoTablero, getHeuristica(nuevoTablero), vacio));
             nodosVisitados++;
         }
         if (columnaVacio > 0) {
             int[] nuevoTablero = tablero.clone();
             nuevoTablero[vacio] = nuevoTablero[vacio - 1];
             nuevoTablero[vacio - 1] = 0;
             sucesores.add(new JuegoDel15Solver(nuevoTablero, getHeuristica(nuevoTablero), vacio));
             nodosVisitados++;
         }
         if (columnaVacio < tamano - 1) {
             int[] nuevoTablero = tablero.clone();
             nuevoTablero[vacio] = nuevoTablero[vacio + 1];
             nuevoTablero[vacio + 1] = 0;
             sucesores.add(new JuegoDel15Solver(nuevoTablero, getHeuristica(nuevoTablero), vacio));
             nodosVisitados++;
         }
         return sucesores;
     }

     public static void imprimir(int[] tablero) {
    	 for (int i = 0; i < tablero.length; i++) {
    		 System.out.print(tablero[i] + " ");
    		 if ((i + 1) % 3 == 0) {
    			 System.out.println();
    		 }
    	 }
     }
 }
 
 