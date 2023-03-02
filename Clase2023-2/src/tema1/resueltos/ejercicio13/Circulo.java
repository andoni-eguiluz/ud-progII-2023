package tema1.resueltos.ejercicio13;

import java.awt.Color;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Circulo {
	// STATIC
	private static float grosor = 2.0f;
	
	private static void setGrosor( float grosor ) {
		Circulo.grosor = grosor;
	}
	
	///  NO STATIC
	private int radio;
	private int xCentro;
	private int yCentro;
	private Color colorBorde;
	private Color colorRelleno;
	private long tiempoCreacion;

	/** Crea un nuevo círculo
	 * @param radio	Radio en píxels
	 * @param xCentro	Coordenada x del centro (0 para la izquierda, crece hacia la derecha)
	 * @param yCentro	Coordenada y del centro (0 para arriba, crece hacia abajo)
	 * @param colorBorde	Color del borde
	 * @param colorRelleno	Color de relleno
	 */
	public Circulo(int radio, int xCentro, int yCentro, Color colorBorde, Color colorRelleno) {
		this.radio = radio;
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		this.colorBorde = colorBorde;
		this.colorRelleno = colorRelleno;
		tiempoCreacion = System.currentTimeMillis();
	}

	/** Crea un nuevo círculo
	 * @param radio	Radio en píxels
	 * @param centro	Coordenada del centro del círculo
	 * @param colorBorde	Color del borde
	 * @param colorRelleno	Color de relleno
	 */
	public Circulo(int radio, Point centro, Color colorBorde, Color colorRelleno) {
		this( radio, centro.x, centro.y, colorBorde, colorRelleno );
//		this.radio = radio;
//		this.xCentro = centro.x;
//		this.yCentro = centro.y;
//		this.colorBorde = colorBorde;
//		this.colorRelleno = colorRelleno;
//		tiempoCreacion = System.currentTimeMillis();
	}
	
	public Circulo() {
		// TODO
	}
	
	public boolean estaDentro( Point punto ) {
		return false;
		// TODO
	}

	public int getRadio() {
		return radio;
	}

	/** Cambia el radio del círculo
	 * @param radio	Nuevo radio, debe ser positivo o cero. Si es negativo no se cambia
	 */
	public void setRadio(int radio) {
		if (radio>=0) {
			this.radio = radio;
		}
	}

	public int getxCentro() {
		return xCentro;
	}

	public void setxCentro(int xCentro) {
		this.xCentro = xCentro;
	}
	
	public int getyCentro() {
		return yCentro;
	}

	public void setyCentro(int yCentro) {
		this.yCentro = yCentro;
	}

	public Color getColorBorde() {
		return colorBorde;
	}

	public void setColorBorde(Color colorBorde) {
		this.colorBorde = colorBorde;
	}

	public Color getColorRelleno() {
		return colorRelleno;
	}

	public void setColorRelleno(Color colorRelleno) {
		this.colorRelleno = colorRelleno;
	}

	public static float getGrosor() {
		return grosor;
	}

	/** Devuelve el tiempo de creación del círculo
	 * @return	Tiempo en milisegundos
	 */
	public long getTiempoCreacion() {
		return tiempoCreacion;
	}

	/** Devuelve el tiempo que el círculo lleva existiendo desde su creación
	 * @return	Tiempo en milis
	 */
	public long getTiempoVida() {
		return System.currentTimeMillis() - tiempoCreacion;
	}


	@Override
	public String toString() {
		return String.format( "(%d,%d) R %d", xCentro, yCentro, radio );
		// return "(" + xCentro + "," + yCentro + ") R " + radio;
	}
	
	public void dibuja( VentanaGrafica vent ) {
		vent.dibujaCirculo( this.getxCentro(), this.getyCentro(), this.getRadio(),
				Circulo.getGrosor(), this.getColorBorde(), this.getColorRelleno() );
	}
	
	
}
