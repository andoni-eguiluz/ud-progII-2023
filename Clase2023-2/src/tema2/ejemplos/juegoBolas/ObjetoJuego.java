package tema2.ejemplos.juegoBolas;

import java.awt.Color;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public abstract class ObjetoJuego {
	protected double x;
	protected double y;
	
	/** Devuelve la coordenada x del centro del objeto de juego, con respecto a la pantalla
	 * @return	Coordenada x en píxels
	 */
	public double getX() {
		return x;
	}
	/** Modifica la coordenada x del centro del objeto de juego, con respecto a la pantalla
	 * @param x	Nueva coordenada x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/** Devuelve la coordenada y del centro del objeto de juego, con respecto a la pantalla
	 * @return	Coordenada y en píxels
	 */
	public double getY() {
		return y;
	}
	/** Modifica la coordenada y del centro del objeto de juego, con respecto a la pantalla
	 * @param y	Nueva coordenada y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/** Dibuja un objeto de juego
	 * @param v	Ventana en la que dibujar
	 */
	public abstract void dibuja( VentanaGrafica v );
	
	
	/** Comprueba si un punto está dentro o no del objeto de juego
	 * @param p	punto a comprobar
	 * @return	true si está dentro, false si no
	 */
	public abstract boolean contieneA( Point p );

	
	/** Método de prueba de conceptos de OO
	 * No relacionado con el juego
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// ObjetoJuego oj = new ObjetoJuego();  // Esto ya no se puede hacer cuando la clase es abstracta
		ObjetoJuego oj = new tema2.ejemplos.juegoBolas.Pelota(10, 10, 10, Color.red);  // Polimorfismo de datos
		System.out.println( oj.getX() );
		System.out.println( oj );  // ¿A qué toString se llama?
		Pelota p = (Pelota) oj;
		System.out.println( p.getRadio() );
		sacaPuntosEstrella(oj);
	}
	private static void sacaPuntosEstrella( ObjetoJuego oj ) {
		if (oj instanceof Estrella) {
			Estrella e = (Estrella) oj;
			System.out.println( e.getPuntos() );
		}
	}

}
