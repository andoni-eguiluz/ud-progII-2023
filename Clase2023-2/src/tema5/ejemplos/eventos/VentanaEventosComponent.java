package tema5.ejemplos.eventos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ventana de prueba con algunos elementos básicos con eventos de componente
 * Obsérvese cómo la imagen siempre está centrada, gracias a ese evento
 */
@SuppressWarnings("serial")
public class VentanaEventosComponent extends JFrame {
	
	/** Prueba de la clase
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaEventosComponent v = new VentanaEventosComponent();
		v.setVisible( true );  
		// Swing lanza un gestor de eventos
		// Swing lanza un NUEVO HILO de ejecución
	}

	// NO STATIC
	
	private JTextArea taTexto;
	private JPanel pCentral;
	private JLabel lFoto;
	public VentanaEventosComponent() {
		// Formato de la ventana
		this.setTitle( "Mi ventana" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 640, 480 );
		// setLocation( 3000, 100 );  // Cambiar posición en pantalla
		
		// Crear componentes
		taTexto = new JTextArea( "", 5, 40 );
		lFoto = new JLabel( new ImageIcon( VentanaEventosComponent.class.getResource( "/tema3/ejemplos/runner/img/asteroid.png") ) ); // Opción 1: recurso (ventaja: se puede empaquetar en un jar)
		// JLabel lFoto = new JLabel( new ImageIcon("src/tema3/img/balon.png") );  // Opción 2: fichero - solo funciona si existe el fichero en el disco en la ruta correspondiente
		JButton bBorrar = new JButton( "Borrar texto" );
		JButton bAceptar = new JButton( "Acabar" );
		
		// Crear contenedores
		JPanel pInferior = new JPanel();
		pCentral = new JPanel();
		
		// Asignar formatos (layouts)
		pCentral.setLayout( null );  // Nulo para mover la imagen libremente
		pCentral.setBackground( Color.YELLOW );
		pCentral.setBorder( BorderFactory.createLineBorder( Color.BLUE, 2 ) );
		
		// Asignar componentes a contenedores
		getContentPane().add( new JScrollPane(taTexto), BorderLayout.NORTH );
		pCentral.add( lFoto );
		getContentPane().add( pCentral, BorderLayout.CENTER );
		pCentral.setPreferredSize( new Dimension( 600, 400 ) );
		lFoto.setSize( 50, 50 );  // Tamaño de la foto: OJO - si no se indica la foto no se ve (por defecto el tamaño es 0 y el layout de este panel es nulo)
		pInferior.add( bAceptar );
		pInferior.add( bBorrar );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		
		// Asociar gestores de eventos a componentes
		pCentral.addComponentListener( new ComponentAdapter() {  // Todos los Listener con >1 método tienen clase asociada Adapter con código vacío
			@Override
			public void componentResized(ComponentEvent e) {
				// System.out.println( "Resized " + pCentral.getWidth() + " , " + pCentral.getHeight() );
				lFoto.setLocation( pCentral.getWidth()/2 - lFoto.getWidth()/2, pCentral.getHeight()/2 - lFoto.getHeight()/2);
			}
		});
	}
	
}
