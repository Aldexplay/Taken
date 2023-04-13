
import java.util.Comparator;
import java.util.PriorityQueue;


public class JuegoDel15Solver {
	String[] args = {"-Xmx2G"};



    private PriorityQueue<GameOfFifteen> colaPrioridad;

    public JuegoDel15Solver(GameOfFifteen estadoInicial) {
        colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(GameOfFifteen::getF));
        colaPrioridad.add(estadoInicial);
        

    }

    public GameOfFifteen resolver() {
        while (!colaPrioridad.isEmpty()) {
        	
        	PriorityQueue<GameOfFifteen> abierta = new PriorityQueue<>();
        	GameOfFifteen actual = colaPrioridad.poll();
            if (actual.esMeta()) {
                return actual;
            }
            for (GameOfFifteen sucesor : actual.expandir()) {
                sucesor.setF(sucesor.getHeuristica());
                colaPrioridad.add(sucesor);
            }
            
        }
        return null;
    }
    
    }
    
