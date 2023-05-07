package tema5.ejemplos.conYSinEventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** Demo de interacción de ratón CON gestión de eventos de Swing
 * Controlando el ratón desde eventos del panel y también desde eventos de cada componente
 * En lugar de dibujar directamente, se usan componentes modificados (labels) sobre un panel contenedor
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class InteraccionComponentesConEventos2 {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		InteraccionComponentesConEventos2 mgt = new InteraccionComponentesConEventos2();
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
	JPanel panel;
	// ArrayList<ElementoLabel> listaEltos;   No hace falta porque se guarda en el propio panel - panel.getComponents()
	
	/** Crea el gestor de elementos con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public InteraccionComponentesConEventos2() {
		ventana = new JFrame( "Ejemplo de gestión visual con labels con sus eventos de mouse" );
		ventana.setSize( ANCHURA_VENTANA, ALTURA_VENTANA  );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
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
		ElementoLabel elto = new ElementoLabel( 100, 100, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
		elto.setTexto( "Ada Byron" );
		ElementoLabel elto2 = new ElementoLabel( 300, 200, ANCHURA_ELEMENTO, ALTURA_ELEMENTO );
		elto2.setTexto( "Alan Turing" );
		elto2.setFondo( Color.CYAN );
		asignaEventos( elto );
		asignaEventos( elto2 );
		panel = new JPanel();
		panel.setLayout( null );  // Layout nulo para posicionar de forma directa en el panel sus componentes
		panel.add( elto );
		panel.add( elto2, 0 );  // El componente 0 es el que se muestra por delante
		JPanel panelInf = new JPanel();
		lMensaje = new JLabel( " " );
		panelInf.add( lMensaje );
		ventana.add( panelInf, BorderLayout.SOUTH );
		ventana.add( panel, BorderLayout.CENTER );
	}
	
		// Proceso de gestión de creación de eventos para interacción de ratón en el label
		// (Obsérvese que ya no hace falta detectar cuándo el click está "dentro" del label porque lo hace swing)
		private void asignaEventos( ElementoLabel elto ) {
			MouseAdapter ma = new MouseAdapter() {
				Point pInicial = null;
				Point pAnterior = null;
				@Override
				public void mousePressed(MouseEvent e) {
					pInicial = e.getLocationOnScreen();  // Como vamos a mover hacemos coordenadas absolutas
					pAnterior = e.getLocationOnScreen(); // Idem
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					clickEnElemento( elto, e.isAltDown() );
				}
				@Override
				public void mouseDragged(MouseEvent e) {
					dragEnElemento( elto, pAnterior, e.getLocationOnScreen() );
					pAnterior = e.getLocationOnScreen();  // Actualiza punto del drag
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					finDragElemento( elto, pInicial, pAnterior );
				}
			};
			elto.addMouseListener( ma );
			elto.addMouseMotionListener( ma );
		}
	
	// Proceso de gestión de creación de eventos para interacción de ratón en el panel
	// Solo se activará esta interacción si no ocurre sobre los labels
	private void interaccion() {
		lMensaje.setText( "Drag en elemento lo mueve, Drag fuera crear elemento nuevo, Click crea elemento estándar, Alt+click borra elemento" );
		MouseAdapter ma = new MouseAdapter() {
			Point pInicial = null;
			Point pAnterior = null;
			@Override
			public void mousePressed(MouseEvent e) {
				pInicial = e.getPoint();
				pAnterior = e.getPoint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEnEspacio( e.getPoint() );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				dragEnEspacio( pInicial, e.getPoint() );
				pAnterior = e.getPoint();  // Actualiza punto del drag
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				finDragElemento( null, pInicial, pAnterior );
			}
		};
		panel.addMouseListener( ma );
		panel.addMouseMotionListener( ma );
		ventana.setVisible( true );
	}
	
	// Click de elemento
	private void clickEnElemento( ElementoLabel elto, boolean altPulsado ) {
		if (altPulsado) {  // Alt + click = borrado de elemento
			panel.remove( elto );
			panel.repaint();
		} else {  // Click = edición de elemento
			Color cNuevo = eligeColor( "Elige nuevo color de fondo" );
			if (cNuevo!=null) {
				elto.setFondo( cNuevo );
			}
			String texto = JOptionPane.showInputDialog( ventana, "Cambia el texto del elemento:", elto.getTexto() );
			if (texto!=null && !texto.isEmpty()) {
				elto.setTexto( texto );
			}
		}
	}
	
		private Color eligeColor( String mens ) {
			JColorChooser jcc = new JColorChooser();
			int resp = JOptionPane.showConfirmDialog( ventana, jcc, mens, JOptionPane.OK_CANCEL_OPTION );
			return (resp==JOptionPane.OK_OPTION) ? jcc.getColor() : null;
		}

	
	private void dragEnElemento(ElementoLabel elto, Point pAnterior, Point pActual) {
		// Visualizar el drag hasta que se suelte el ratón
		int difX = pActual.x - pAnterior.x;
		int difY = pActual.y - pAnterior.y;
		elto.mover( difX, difY );
	}

	private void finDragElemento( ElementoLabel elto, Point pIni, Point pFin ) {
		if (elto==null) { // Ha habido drag sobre espacio: creación de nuevo elemento con tamaño del drag
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
				ElementoLabel nuevo = new ElementoLabel( p.x, p.y, ancho, alto );
				nuevo.setTexto( texto );
				panel.add( nuevo, 0 );
				asignaEventos( nuevo );
				panel.repaint();
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
		Graphics graphics = panel.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect( x, y, anc, alt );
		graphics.setColor(Color.MAGENTA);
		graphics.drawRect( x, y, anc, alt );
		// Observa cómo el dibujado es temporal, no se redibuja, y tapa otros elementos que pueda haber
	}
	
}
