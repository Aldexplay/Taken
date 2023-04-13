public class JuegoDel15Solver {
	
	private int[] tablero;
	private int valHeu;
	private int vacio;
    
	public JuegoDel15Solver(int[] tablero, int valHeu, int vacio) {
		this.tablero = tablero;
		this.valHeu = valHeu;
		this.vacio = vacio;
	}

	public int[] getTablero() {
		return tablero;
	}

	public void setTablero(int[] tablero) {
		this.tablero = tablero;
	}

	public int getVacio() {
		return vacio;
	}

	public void setVacio(int vacio) {
		this.vacio = vacio;
	}

	public int getValHeu() {
		return valHeu;
	}

	public void setValHeu(int valHeu) {
		this.valHeu = valHeu;
	}
}