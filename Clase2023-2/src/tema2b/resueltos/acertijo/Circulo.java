package tema2b.resueltos.acertijo;

import java.awt.Color;
import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Objeto genérico de acertijo con base de dibujado en forma de círculo
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public abstract class Circulo extends ObjetoAcertijo {

	//================= Parte static
	
	/** Color del círculo */
	public static final Color COLOR_CIRCULO = Color.MAGENTA;
	public static final float GROSOR_CIRCULO = 2.0f;
	
	//================= Parte no static
	
	int radio;  // Radio en píxels
	
	/** Crea un nuevo rectángulo
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del objeto
	 * @param radio	Radio del círculo (en píxels)
	 */
	public Circulo(int x, int y, VentanaGrafica ventana, int radio ) {
		super(x, y, ventana);
		this.radio = radio;
	}
	
	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	@Override
	public void dibujar() {
		ventana.dibujaCirculo( x, y, radio, GROSOR_CIRCULO, COLOR_CIRCULO );
	}

	@Override
	public boolean contienePunto(int x, int y) {
		double dist = Math.sqrt( (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y) );
		return dist<=radio;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Circulo)) return false;
		Circulo c2 = (Circulo) obj;
		return super.equals(obj) && radio==c2.radio;
	}
	
	@Override
	public String toString() {
		return super.toString() + " <" + radio + ">";
	}
	
}