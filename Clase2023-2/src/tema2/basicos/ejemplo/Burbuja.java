package tema2.basicos.ejemplo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de ejemplo de herencia - permite crear planetas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Burbuja extends Esfera {

	private double pixelsPorSegundo = -15.0;  // Valor por defecto: sube 15 píxeles por segundo
	
	// Constructores (pueden ser varios):
	
	/** Crea una nueva burbuja
	 * @param xCentro	Coordenada x de su centro en píxeles
	 * @param yCentro	Coordenada y de su centro en píxeles
	 * @param radio	Radio en píxeles
	 */
	public Burbuja( double xCentro, double yCentro, double radio) {
		super( xCentro, yCentro, radio );
	}

	public double getPixelsPorSegundo() {
		return pixelsPorSegundo;
	}

	public void setPixelsPorSegundo(double pixelsPorSegundo) {
		this.pixelsPorSegundo = pixelsPorSegundo;
	}

	@Override
	public String toString() {
		return "Burbuja " + super.toString();  // Reutilización de método madre!  No es obligatoria, solo si nos va bien
	}
	
	@Override
	public void dibujar(VentanaGrafica v) {
		v.dibujaImagen( "bubble.png", getxCentro(), getyCentro(), (int)(getRadio()*2), (int)(getRadio()*2), 1.0, 0.0, 1.0f );
		super.dibujar(v);
	}

	/** Realiza la animación de la burbuja (subir)
	 * @param milis	Milisegundos transcurridos desde la última animación
	 */
	public void animar( long milis ) {
		setyCentro( getyCentro() + milis * pixelsPorSegundo / 1000.0 );  // s = s0 + v * t
	}
	
}
