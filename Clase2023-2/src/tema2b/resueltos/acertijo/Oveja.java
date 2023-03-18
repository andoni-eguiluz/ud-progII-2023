package tema2b.resueltos.acertijo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Oveja extends Circulo implements Draggable, Comible {
	
	//================= Parte static
	
	/** Tamaño visual de la oveja (radio del círculo) */
	public static final int RADIO = 60;
	/** Ruta de la imagen de la oveja */
	public static final String IMAGEN = "/tema2b/ejercicios/acertijo/img/oveja.png";
	
	//================= Parte no static

	/** Crea una nueva oveja
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana de la oveja
	 */
	public Oveja(int x, int y, VentanaGrafica ventana) {
		super(x, y, ventana, RADIO);
	}
	
	@Override
	public void dibujar() {
		super.dibujar();
		ventana.dibujaImagen( IMAGEN, x, y, radio*2, radio*2, 1.0, 0.0, 1.0f );
	}

	@Override
	public void mover( int x, int y ) {
		setX( x );
		setY( y );
	}
	
	@Override
	public boolean puedeSerComidoPor(ObjetoAcertijo o) {
		return o instanceof Lobo;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Oveja)) return false;
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Oveja " + super.toString();
	}

}
