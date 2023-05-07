package tema5.ejemplos.conYSinEventos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/** Elemento visual para ejemplo de dibujado con eventos. Basado en un JLabel
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ElementoLabel extends JLabel {
	
	// STATIC 
	private static final long serialVersionUID = 1L;
	
	public static final Color COLOR_FONDO = Color.YELLOW;  // Color de fondo por defecto
	public static final Color COLOR_BORDE = Color.BLUE; // Color visual de la caja
	public static final float GROSOR = 2.0f;  // Grosor visual del borde del slot
	public static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	public static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	// NO STATIC
		
	/** Crea un nuevo elemento visual con color de fondo {@link #COLOR_FONDO}
	 * @param x	Posición x de la esquina superior izquierda
	 * @param y	Posición y de la esquina superior izquierda
	 * @param anchura	Anchura en píxels
	 * @param altura	Altura en píxels
	 */
	public ElementoLabel(int x, int y, int anchura, int altura) {
		setHorizontalAlignment( JLabel.CENTER );
		setLocation( x, y );
		setSize( anchura, altura );
		setBackground( COLOR_FONDO );
		setOpaque( true );
		setText( "" );
	}
	
	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 */
	public void setX(int x) {
		setLocation( x, getY() );
	}

	/** Modifica la posición de este objeto
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setY(int y) {
		setLocation( getX(), y );
	}

	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setXY(int x, int y) {
		setLocation( x, y );
	}

	/** Modifica la posición de este objeto
	 * @param x	Incremento de posición horizontal en píxels
	 * @param y	Incremento de posición vertical en píxels
	 */
	public void mover(int incX, int incY) {
		setLocation( getX() + incX, getY() + incY );
	}

	/** Devuelve la altura de este objeto
	 * @return	Altura en píxels
	 */
	public int getAltura() {
		return getHeight();
	}

	/** Modifica la altura de este objeto
	 * @param y	Nueva altura en píxels
	 */
	public void setAltura(int altura) {
		setSize( getWidth(), altura );
	}
	
	/** Devuelve la anchura de este objeto
	 * @return	Anchura en píxels
	 */
	public int getAnchura() {
		return getWidth();
	}

	/** Modifica la anchura de este objeto
	 * @param y	Nueva anchura en píxels
	 */
	public void setAnchura(int anchura) {
		setSize( anchura, getHeight() );
	}
	
	/** Devuelve el color de este elemento
	 * @return	Color de fondo
	 */
	public Color getFondo() {
		return getBackground();
	}

	/**	Cambia el color
	 * @param fondo	Nuevo color de fondo
	 */
	public void setFondo(Color fondo) {
		setBackground( fondo );
	}
	
	/** Devuelve el texto de este elemento
	 * @return	Texto existente
	 */
	public String getTexto() {
		return getText();
	}

	/**	Cambia el texto
	 * @param texto	Nuevo texto
	 */
	public void setTexto(String texto) {
		setText( texto );
	}

	/** Comprueba si el objeto contiene un punto de la ventana
	 * @param x	Posición x del punto
	 * @param y	Posición y del punto
	 * @return	true si esa posición está dentro del objeto, false en caso contrario
	 */
	public boolean contienePunto(int x, int y) {
		if (x<getX()) return false;
		else if (y<getY()) return false;
		else if (x>getX()+getWidth()) return false;
		else if (y>getY()+getHeight()) return false;
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElementoLabel)) {
			return false;
		}
		ElementoLabel o2 = (ElementoLabel) obj;  // Miro este obj como lo que realmente es: un Elemento
		return getX()==o2.getX() && getY()==o2.getY() && getWidth()==o2.getWidth() && getHeight()==o2.getHeight();
	}
	
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ") [" + getAnchura() + "," + getAltura() + "]";
	}
		
}
