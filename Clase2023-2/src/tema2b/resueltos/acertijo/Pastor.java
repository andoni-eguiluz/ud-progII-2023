package tema2b.resueltos.acertijo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Pastor extends Circulo implements Draggable {
	
	//================= Parte static
	
	/** Tamaño visual del pastor (radio del círculo) */
	public static final int RADIO = 75;
	/** Ruta de la imagen del pastor */
	public static final String IMAGEN = "/tema2b/ejercicios/acertijo/img/pastor.png";
	
	//================= Parte no static

	/** Crea un nuevo pastor
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del pastor
	 */
	public Pastor(int x, int y, VentanaGrafica ventana ) {
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
		if (!(obj instanceof Pastor)) return false;
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Pastor " + super.toString();
	}

}
