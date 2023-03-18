package tema2b.basicos.ejemplo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de ejemplo de herencia - permite crear esferas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Esfera {  // Java me hace extends Object {
	private double xCentro;
	private double yCentro;
	private double radio = 50;
	
	/** Crea una nueva esfera
	 * @param xCentro	Coordenada x de su centro en píxeles
	 * @param yCentro	Coordenada y de su centro en píxeles
	 * @param radio	Radio en píxeles
	 */
	public Esfera(double xCentro, double yCentro, double radio) {
		super();
		// System.out.println( "Constructor Esfera"); // Si se quiere observar el orden de llamada de constructores 
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		setRadio( radio );
	}
	
	public double getxCentro() {
		return xCentro;
	}
	public void setxCentro(double xCentro) {
		this.xCentro = xCentro;
	}
	
	public double getyCentro() {
		return yCentro;
	}
	public void setyCentro(double yCentro) {
		this.yCentro = yCentro;
	}
	
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	@Override
	public String toString() {
		return String.format( "(%.1f,%.1f)R%.1f", xCentro, yCentro, radio );
	}

	public void dibujar( VentanaGrafica v ) {
		v.dibujaCirculo( xCentro, yCentro, radio, 2.0f );
	}
	
}
