package tema5.ejemplos.conYSinEventos;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.JOptionPane;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Demo de cómo se programaría una interacción SIN gestión de eventos (polling)
 * Controlando directamente la pulsación de ratón desde el hilo principal  (un bucle de tiempo real que solo controla eventos)
 * (ver método run())
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class InteraccionDibujadoSinEventos {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		InteraccionDibujadoSinEventos mgt = new InteraccionDibujadoSinEventos();
		mgt.run();
	}
	
	// =================== Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;   // Píxels de ancho
	public static final int ALTURA_VENTANA = 800;     // Píxels de alto
	public static final int ANCHURA_ELEMENTO = 100;   // Píxels de ancho por defecto de un elemento nuevo
	public static final int ALTURA_ELEMENTO = 50;    // Píxels de alto por defecto de un elemento nuevo
	
	// =================== Parte no static
	
	VentanaGrafica ventana;
	ArrayList<Elemento> listaEltos;
	
	/** Crea el gestor de elementos con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public InteraccionDibujadoSinEventos() {
		ventana = new VentanaGrafica( ANCHURA_VENTANA, ALTURA_VENTANA, "Ejemplo de gestión visual controlando ratón directamente" );
		listaEltos = new ArrayList<>();
	}
	
	/** Devuelve la lista de elementos actual
	 * @return	Lista de elementos en la ventana
	 */
	public ArrayList<Elemento> getListaEltos() {
		return listaEltos;
	}

	/** Finaliza la ventana gráfica
	 */
	public void fin() {
		ventana.acaba();
	}
			
	/** Ejecuta el gestor de tareas, lanzando el proceso de ejecución principal
	 */
	public void run() {
		initDatos();
		interaccion();
		fin();
	}

	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		Elemento elto = new Elemento( 100, 100, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
		elto.setTexto( "Ada Byron" );
		Elemento elto2 = new Elemento( 300, 200, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
		elto2.setTexto( "Alan Turing" );
		elto2.setFondo( Color.CYAN );
		listaEltos.add( elto );
		listaEltos.add( elto2 );
	}
	
	// Proceso de gestión de interacción de ratón por parte del usuario - bucle permanentemente consultando ratón
	private void interaccion() {
		dibujaTodo();
		ventana.setMensaje( "Drag en elemento lo mueve, Drag fuera crear elemento nuevo, Click crea elemento estándar, Alt+click borra elemento" );
		while (!ventana.estaCerrada()) {
			Point p = ventana.getRatonPulsado();
			if (p!=null) {
				boolean pulsadoElemento = false;
				for (int i=listaEltos.size()-1; i>=0; i--) {  // Recorre al revés para detectar primero los elementos más ontop
					Elemento o = listaEltos.get(i);
					if (o.contienePunto( p.x, p.y )) {
						pulsacionEnElemento( o, p );
						pulsadoElemento = true;
						break;
					}
				}
				if (!pulsadoElemento) {  // Si no se ha pulsado elemento, vemos si se quiere crear un nuevo evento
					pulsacionEnEspacio( p );
				}
			}
			ventana.espera( 20 );
		}
	}
	
	// Acción de pulsación en espacio libre
	private void pulsacionEnEspacio( Point p ) {
		Point p2 = ventana.getRatonPulsado();
		Point pFin = p2;
		while (p2!=null) { // Espera hasta suelta de ratón
			ventana.espera( 20 ); // Pequeña espera
			pFin = p2;
			p2 = ventana.getRatonPulsado();
		}
		if (pFin==null || p.equals(pFin)) {  // Click (punto sin cambio) en espacio libre = creación de nuevo elemento con tamaño estándar
			pideTextoYCreaElemento( p, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
		} else {  // Drag en espacio libre = creación de nuevo elemento con tamaño del drag
			int ancho = Math.abs( p.x - pFin.x );
			int alto = Math.abs( p.y - pFin.y );
			if (ancho>20 && alto>20) {  // Si el drag tiene la amplitud suficiente (mínima 20x20 píxels)
				pideTextoYCreaElemento( p, ancho, alto );
			}
		}
	}
		// Pide texto al usuario y crea el elemento en el punto y tamaño indicados
		private void pideTextoYCreaElemento( Point p, int ancho, int alto ) {
			String texto = JOptionPane.showInputDialog( ventana.getJFrame(), "Texto de elemento nuevo" );
			if (texto!=null && !texto.isEmpty()) {
				Elemento nuevo = new Elemento( p.x, p.y, ancho, alto );
				nuevo.setTexto( texto );
				listaEltos.add( nuevo );
				dibujaTodo();
			}
		}
	
	// Acción si se pulsa un objeto con el ratón
	private void pulsacionEnElemento( Elemento elemPulsado, Point p ) {
		Point p2 = ventana.getRatonPulsado();
		while (p2!=null && p.equals(p2)) { // Espera hasta suelta de ratón o movimiento de coordenadas
			ventana.espera( 20 ); // Pequeña espera
			p2 = ventana.getRatonPulsado();
		}
		if (p2==null) { // Click
			if (ventana.isTeclaPulsada( KeyEvent.VK_ALT )) {  // Alt + click = borrado de elemento
				listaEltos.remove( elemPulsado );
			} else {  // Click = edición de elemento
				Color cNuevo = ventana.eligeColor( "Elige nuevo color de fondo" );
				if (cNuevo!=null) {
					elemPulsado.setFondo( cNuevo );
					dibujaTodo();
				}
				String texto = JOptionPane.showInputDialog( ventana.getJFrame(), "Cambia el texto del elemento:", elemPulsado.getTexto() );
				if (texto!=null && !texto.isEmpty()) {
					elemPulsado.setTexto( texto );
				}
			}
			dibujaTodo();
		} else { // Drag = está habiendo movimiento de elemento
			// Visualizar el drag hasta que se suelte el ratón
			Point pActual = new Point( p.x, p.y );
			while (p2!=null) {
				int difX = p2.x - pActual.x;
				int difY = p2.y - pActual.y;
				elemPulsado.mover( difX, difY );
				dibujaTodo( elemPulsado );  // Dibuja el objeto moviéndose "encima" para que se vea siempre mientas se hace el drag
				ventana.espera( 20 ); // Pequeña espera
				pActual = p2;
				p2 = ventana.getRatonPulsado();
			}
			finDragElemento( elemPulsado );
		}
	}
	
		private void finDragElemento( Elemento elto ) {
			dibujaTodo();
		}
	
	// Dibuja todo
	private void dibujaTodo( ) {
		dibujaTodo( null );
	}
	
	// Dibuja todo y el objeto indicado lo dibuja el último (para que quede por encima)
	private void dibujaTodo( Elemento eltoFinal ) {
		ventana.borra();
		for (Elemento elto : listaEltos) {
			if (elto!=eltoFinal) {
				elto.dibujar( ventana );
			}
		}
		if (eltoFinal!=null) {
			eltoFinal.dibujar( ventana );
		}
		ventana.repaint();  // Dibuja por doble buffer (evitando el parpadeo)
	}

}
