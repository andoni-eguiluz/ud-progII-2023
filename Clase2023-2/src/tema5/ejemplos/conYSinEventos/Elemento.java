package tema5.ejemplos.conYSinEventos;

import java.awt.Color;
import java.awt.Font;

import utils.ventanas.componentes.PanelGrafico;
import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Elemento visual para ejemplo de dibujado con y sin eventos. Se dibuja como un rectángulo con un texto
 * @author andoni.eguiluz at ingenieria.deusto.es
 *
 */
public class Elemento {
	
	// STATIC 
	
	public static final Color COLOR_FONDO = Color.YELLOW;  // Color de fondo por defecto
	public static final Color COLOR_BORDE = Color.BLUE; // Color visual de la caja
	public static final float GROSOR = 2.0f;  // Grosor visual del borde del slot
	public static final Color COLOR_TEXTO = Color.BLACK;   // Color visual del texto interior de la caja
	public static final Font TIPO_TEXTO = new Font( "Arial", Font.PLAIN, 12 ); // Tipografía de los textos que se muestran
	
	// NO STATIC
	
	protected int x;        // Posición horizontal en su panel-ventana
	protected int y;        // Posición vertical en su panel-ventana
	protected int altura;   // Tamaño visual del elemento (vertical)
	protected int anchura;  // Tamaño visual del elemento (horizontal)
	protected Color fondo;  // Color de fondo
	protected String texto; // Texto del elemento
	
	/** Crea un nuevo elemento visual con color de fondo {@link #COLOR_FONDO}
	 * @param x	Posición x de la esquina superior izquierda
	 * @param y	Posición y de la esquina superior izquierda
	 * @param anchura	Anchura en píxels
	 * @param altura	Altura en píxels
	 */
	public Elemento(int x, int y, int anchura, int altura) {
		this.x = x;
		this.y = y;
		this.anchura = anchura;
		this.altura = altura;
		fondo = COLOR_FONDO;
	}

	/** Devuelve la posición de este objeto
	 * @return	Posición horizontal en píxels
	 */
	public int getX() {
		return x;
	}

	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 */
	public void setX(int x) {
		this.x = x;
	}

	/** Devuelve la posición de este objeto
	 * @return	Posición vertical en píxels
	 */
	public int getY() {
		return y;
	}

	/** Modifica la posición de este objeto
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setY(int y) {
		this.y = y;
	}

	/** Modifica la posición de este objeto
	 * @param x	Nueva posición horizontal en píxels
	 * @param y	Nueva posición vertical en píxels
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Modifica la posición de este objeto
	 * @param x	Incremento de posición horizontal en píxels
	 * @param y	Incremento de posición vertical en píxels
	 */
	public void mover(int incX, int incY) {
		this.x += incX;
		this.y += incY;
	}

	/** Devuelve la altura de este objeto
	 * @return	Altura en píxels
	 */
	public int getAltura() {
		return altura;
	}

	/** Modifica la altura de este objeto
	 * @param y	Nueva altura en píxels
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/** Devuelve la anchura de este objeto
	 * @return	Anchura en píxels
	 */
	public int getAnchura() {
		return anchura;
	}

	/** Modifica la anchura de este objeto
	 * @param y	Nueva anchura en píxels
	 */
	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}
	
	/** Devuelve el color de este elemento
	 * @return	Color de fondo
	 */
	public Color getFondo() {
		return fondo;
	}

	/**	Cambia el color
	 * @param fondo	Nuevo color de fondo
	 */
	public void setFondo(Color fondo) {
		this.fondo = fondo;
	}
	
	/** Devuelve el texto de este elemento
	 * @return	Texto existente
	 */
	public String getTexto() {
		return texto;
	}

	/**	Cambia el texto
	 * @param texto	Nuevo texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/** Dibuja el elemento en una ventana gráfica
	 * @param ventana	Ventana gráfica en la que dibujar el elemento
	 */
	public void dibujar( VentanaGrafica ventana ) {
		ventana.dibujaRect( x, y, anchura, altura, GROSOR, COLOR_BORDE, fondo );
		ventana.dibujaTextoCentrado( x, y, anchura, altura, texto, TIPO_TEXTO, COLOR_TEXTO, true );
	}
	
	/** Dibuja el elemento en un panel gráfico
	 * @param ventana	Panel en el que dibujar el elemento
	 */
	public void dibujar( PanelGrafico panel ) {
		panel.dibujaRect( x, y, anchura, altura, GROSOR, COLOR_BORDE, fondo );
		panel.dibujaTextoCentrado( x, y, anchura, altura, texto, TIPO_TEXTO, COLOR_TEXTO, true );
	}
	
	/** Comprueba si el objeto contiene un punto de la ventana
	 * @param x	Posición x del punto
	 * @param y	Posición y del punto
	 * @return	true si esa posición está dentro del objeto, false en caso contrario
	 */
	public boolean contienePunto(int x, int y) {
		if (x<this.x) return false;
		else if (y<this.y) return false;
		else if (x>this.x+anchura) return false;
		else if (y>this.y+altura) return false;
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Elemento)) {
			return false;
		}
		Elemento o2 = (Elemento) obj;  // Miro este obj como lo que realmente es: un Elemento
		return x==o2.x && y==o2.y && anchura==o2.anchura && altura==o2.altura;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ") [" + anchura + "," + altura + "]";
	}
		
}
