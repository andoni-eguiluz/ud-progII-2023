package tema2b.resueltos.brickbreaker;

/** Clase para gestionar una puntuación
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Puntuacion {
	private int puntos;
	public Puntuacion() {
		puntos = 0;
	}
	public int getPuntos() {
		return puntos;
	}
	public void incPuntos( int incrementoPuntos ) {
		puntos += incrementoPuntos;
	}
	
	@Override
	public String toString() {
		return "" + puntos;
	}
}
