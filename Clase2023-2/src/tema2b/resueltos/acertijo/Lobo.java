package tema2b.resueltos.acertijo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Lobo extends Circulo implements Draggable {
	
	//================= Parte static
	
	/** Tamaño visual del lobo (radio del círculo) */
	public static final int RADIO = 60;
	/** Ruta de la imagen del lobo */
	public static final String IMAGEN = "/tema2b/ejercicios/acertijo/img/lobo.png";
	
	//================= Parte no static

	/** Crea un nuevo lobo
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del lobo
	 */
	public Lobo(int x, int y, VentanaGrafica ventana) {
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Lobo)) return false;
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Lobo " + super.toString();
	}

}
