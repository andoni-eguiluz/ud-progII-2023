package tema5.ejemplos.conYSinEventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.ventanas.componentes.PanelGrafico;

/** Demo de interacción de ratón CON gestión de eventos de Swing
 * Controlando el ratón desde eventos
 * Utiliza un componente modificado para dibujado persistente (PanelGrafico)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class InteraccionDibujadoConEventos {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		InteraccionDibujadoConEventos mgt = new InteraccionDibujadoConEventos();
		mgt.run();
	}
	
	// =================== Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;   // Píxels de ancho
	public static final int ALTURA_VENTANA = 800;     // Píxels de alto
	public static final int ANCHURA_ELEMENTO = 100;   // Píxels de ancho por defecto de un elemento nuevo
	public static final int ALTURA_ELEMENTO = 50;    // Píxels de alto por defecto de un elemento nuevo
	
	// =================== Parte no static
	
	JFrame ventana;
	JLabel lMensaje;
	PanelGrafico panel;
	ArrayList<Elemento> listaEltos;
	
	/** Crea el gestor de elementos con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public InteraccionDibujadoConEventos() {
		ventana = new JFrame( "Ejemplo de gestión visual con eventos de Swing en el panel" );
		ventana.setSize( ANCHURA_VENTANA, ALTURA_VENTANA  );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		listaEltos = new ArrayList<>();
	}
	
	/** Devuelve la lista de elementos actual
	 * @return	Lista de elementos en la ventana
	 */
	public ArrayList<Elemento> getListaEltos() {
		return listaEltos;
	}

	/** Ejecuta el gestor de tareas, lanzando el proceso de ejecución principal
	 */
	public void run() {
		initDatos();
		interaccion();
		System.out.println( "Se acabó el main" );
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
		JPanel panelInf = new JPanel();
		lMensaje = new JLabel( " " );
		panelInf.add( lMensaje );
		ventana.add( panelInf, BorderLayout.SOUTH );
		panel = new PanelGrafico( 3000, 2000, Color.WHITE );
		ventana.add( panel, BorderLayout.CENTER );
	}
	
	// Proceso de gestión de creación de eventos para interacción de ratón por parte del usuario
	private void interaccion() {
		dibujaTodo();
		lMensaje.setText( "Drag en elemento lo mueve, Drag fuera crear elemento nuevo, Click crea elemento estándar, Alt+click borra elemento" );
		MouseAdapter ma = new MouseAdapter() {
			Point pInicial = null;
			Point pAnterior = null;
			Elemento eltoEnPressed = null;
			@Override
			public void mousePressed(MouseEvent e) {
				pInicial = e.getPoint();
				pAnterior = e.getPoint();
				eltoEnPressed = detectarElemento( e.getPoint() );
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (eltoEnPressed==null) {
					clickEnEspacio( e.getPoint() );
				} else {
					clickEnElemento( eltoEnPressed, e.isAltDown() );
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				if (eltoEnPressed==null) {
					dragEnEspacio( pInicial, e.getPoint() );
				} else {
					dragEnElemento( eltoEnPressed, pAnterior, e.getPoint() );
				}
				pAnterior = e.getPoint();  // Actualiza punto del drag
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				finDragElemento( eltoEnPressed, pInicial, pAnterior );
			}
		};
		panel.addMouseListener( ma );
		panel.addMouseMotionListener( ma );
		ventana.setVisible( true );
	}
	
		// Comprueba si hay un elemento en el punto indicado, null si no lo hay
		private Elemento detectarElemento( Point p ) {
			for (int i=listaEltos.size()-1; i>=0; i--) {  // Recorre al revés para detectar primero los elementos más ontop
				Elemento elto = listaEltos.get(i);
				if (elto.contienePunto( p.x, p.y )) {
					return elto;
				}
			}
			return null;
		}
		
	// Click de elemento
	private void clickEnElemento( Elemento elto, boolean altPulsado ) {
		if (altPulsado) {  // Alt + click = borrado de elemento
			listaEltos.remove( elto );
		} else {  // Click = edición de elemento
			Color cNuevo = panel.eligeColor( "Elige nuevo color de fondo" );
			if (cNuevo!=null) {
				elto.setFondo( cNuevo );
				dibujaTodo();
			}
			String texto = JOptionPane.showInputDialog( ventana, "Cambia el texto del elemento:", elto.getTexto() );
			if (texto!=null && !texto.isEmpty()) {
				elto.setTexto( texto );
			}
		}
		dibujaTodo();
	}
	
	private void dragEnElemento(Elemento elto, Point pAnterior, Point pActual) {
		// Visualizar el drag hasta que se suelte el ratón
		int difX = pActual.x - pAnterior.x;
		int difY = pActual.y - pAnterior.y;
		elto.mover( difX, difY );
		dibujaTodo( elto );  // Dibuja el objeto moviéndose "encima" para que se vea siempre mientas se hace el drag
	}

	private void finDragElemento( Elemento elto, Point pIni, Point pFin ) {
		if (elto!=null) {  // Ha habido drag de elemento: redibujar
			dibujaTodo();
		} else {  // Ha habido drag sobre espacio: creación de nuevo elemento con tamaño del drag
			int ancho = Math.abs( pFin.x - pIni.x );
			int alto = Math.abs( pFin.y - pIni.y );
			if (ancho>20 && alto>20) {  // Si el drag tiene la amplitud suficiente (mínima 20x20 píxels)
				pideTextoYCreaElemento( pIni, ancho, alto );
			}
		}
	}
		// Pide texto al usuario y crea el elemento en el punto y tamaño indicados
		private void pideTextoYCreaElemento( Point p, int ancho, int alto ) {
			String texto = JOptionPane.showInputDialog( ventana, "Texto de elemento nuevo" );
			if (texto!=null && !texto.isEmpty()) {
				Elemento nuevo = new Elemento( p.x, p.y, ancho, alto );
				nuevo.setTexto( texto );
				listaEltos.add( nuevo );
				dibujaTodo();
			}
		}

	private void clickEnEspacio( Point p ) {
		// Acción de pulsación en espacio libre
		pideTextoYCreaElemento( p, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
	}
	
	private void dragEnEspacio( Point pInicial, Point pActual ) {
		// Estamos en drag en espacio - dibujamos un rectangulito para feedback visual
		int x = pInicial.x;
		int y = pInicial.y;
		int anc = pActual.x - pInicial.x;
		int alt = pActual.y - pInicial.y;
		if (anc<0) {  // Invertir en x
			x = pActual.x;
			anc = -anc;
		}
		if (alt<0) {  // Invertir en y
			y = pActual.y;
			alt = -alt;
		}
		dibujaTodo();
		panel.dibujaRect( x, y, anc, alt, 1f, Color.MAGENTA );
		panel.repaint();
	}
	
	
	// Dibuja todo
	private void dibujaTodo( ) {
		dibujaTodo( null );
	}
	
	// Dibuja todo y el objeto indicado lo dibuja el último (para que quede por encima)
	private void dibujaTodo( Elemento eltoFinal ) {
		panel.borra();
		for (Elemento elto : listaEltos) {
			if (elto!=eltoFinal) {
				elto.dibujar( panel );
			}
		}
		if (eltoFinal!=null) {
			eltoFinal.dibujar( panel );
		}
		panel.repaint();  // Dibuja por doble buffer (evitando el parpadeo)
	}

}
