package tema5.resueltos;

// AWT -> Abstract Windowing Toolkit
// Swing -> J

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Solución ejercicio 5a.4
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class Ventana5b7 extends JFrame {

	/** Método de prueba de la ventana del ejercicio 5a.4
	 * @param args
	 */
	public static void main(String[] args) {
//		JFrame ventana = new JFrame( "Prueba" );	
		Ventana5b7 vent = new Ventana5b7();
		vent.setVisible( true );
//		Ventana5a4 vent2 = new Ventana5a4();
//		vent2.setVisible( true );
		System.out.println( "Fin de programa" );
	}
	
	// No static 
	
	private JLabel lSuperior;
	
	public Ventana5b7() {
		// 0.- Configuración
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 600, 400 );
		setTitle( "Ventana de prueba" );
		setAlwaysOnTop(true);
		setLocationRelativeTo( null );
		// 1. Crear contenedores
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		JPanel pDerecho = new JPanel( new GridLayout( 4, 3 ) );
		JPanel pIzquierdo = new JPanel();
		JPanel pIzq1 = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pIzq2 = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pIzq3 = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		// 2. Crear componentes
		lSuperior = new JLabel( "Ventana super útil" );
		JButton bCancelar = new JButton( "¡Cancela!");
		JButton bAdelante = new JButton( "¡A por ello campeón!" );
		JTextArea taTexto = new JTextArea( 5, 10 );
		JButton[] bDerecha = new JButton[12];
		String botones = "123456789<0*";
		for (int i=0; i<botones.length(); i++) {
			bDerecha[i] = new JButton( "" + botones.charAt(i) );
		}
		JLabel lLogoUni = new JLabel( new ImageIcon( "src/tema5/ejercicios/UD-blue-peq.png" ) );
		String[] arrayOpciones = { "Creativx", "Organizadx", "Disciplinadx", "Dinámicx" };
		JComboBox<String> cbTipo = new JComboBox<>( arrayOpciones );
		JLabel lNombre = new JLabel( "Nombre:" );
		final JTextField tfNombre = new JTextField( "<Nombre>", 15 );
		JLabel lCodPostal = new JLabel( "Cod.Postal:" );
		JTextField tfCodPostal = new JTextField( 8 );
		JCheckBox checkBoxUrgente = new JCheckBox( "Urgente", true ); 
		// 3. Formato de contenedores
		this.setLayout( new BorderLayout() ); // Por defecto la ventana tiene borderlayout
		pInferior.setLayout( new FlowLayout( FlowLayout.RIGHT ) ); // Por defecto
		pInferior.setBackground( Color.PINK );
		pSuperior.setLayout( new FlowLayout() );
		pSuperior.setBackground( Color.CYAN );
		JScrollPane spCentral = new JScrollPane( taTexto );
		pIzquierdo.setLayout( new BoxLayout( pIzquierdo, BoxLayout.Y_AXIS ));
		this.setMinimumSize( new Dimension( 500, 250 ) );
		// 4. Formato de componentes
		bCancelar.setSize( 100, 40 );
		lSuperior.setBackground( Color.YELLOW );
		lSuperior.setOpaque( true );
		lSuperior.setFont( new Font( "Arial", Font.BOLD, 24 ));
		bCancelar.setBackground( Color.RED );
		bCancelar.setForeground( new Color( 255, 255, 255, 200 ) );
		bAdelante.setBackground( Color.GREEN );
		bAdelante.setEnabled( false );
		cbTipo.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq1.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq2.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq3.setMaximumSize( new Dimension( 300, 35 ) );
		// bAdelante.setVisible( false );
		// 5. Añadir componentes a contenedores
		this.add( pSuperior, BorderLayout.NORTH );
		pSuperior.add( lSuperior );
		this.add( pInferior, BorderLayout.SOUTH );
//		this.add( bCancelar, BorderLayout.SOUTH );
//		this.add( bAdelante, BorderLayout.SOUTH );
		pInferior.add( bCancelar );
		pInferior.add( bAdelante );
		this.add( spCentral, BorderLayout.CENTER );
		this.add( pDerecho, BorderLayout.EAST );
		for (JButton boton : bDerecha) {
			pDerecho.add( boton );
		}
		this.add( pIzquierdo, BorderLayout.WEST );
		pIzquierdo.add( lLogoUni );
		pIzquierdo.add( cbTipo );
		pIzquierdo.add( pIzq1 );
		pIzquierdo.add( pIzq2 );
		pIzquierdo.add( pIzq3 );
		pIzq1.add( lNombre );
		pIzq1.add( tfNombre );
		pIzq2.add( lCodPostal );
		pIzq2.add( tfCodPostal );
		pIzq3.add( checkBoxUrgente );
		// 6.- Preparación de eventos de la ventana
		// Manera 1 - clase externa
		// bCancelar.addActionListener( new GestorBotonCancelar( lSuperior ) );
		// Manera 2 - clase interna
		// bCancelar.addActionListener( new GestorBotonCancelarInterna() );
		// Manera 3 - clase interna anónima
		// Si la clase se instancia una sola vez...
		// ... se puede hacer anónima
		bCancelar.addActionListener( new ActionListener() {
			// Crea un atributo oculto con el mismo nombre que tfNombre
			// Copia el valor de tfNombre a ese atributo
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println( "hola");
				lSuperior.setText( "Ventana cancelada" );
				tfNombre.setText( "" );  // Funciona pero ojo que está pasando en una copia
			}
		});
		// Qué pasa si hay cambio de variable?
		// tfNombre.setText( "h" );
		// tfNombre = new JTextField();
		bAdelante.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lSuperior.setText( "Ventana confirmada" );
			}
		} );
		tfNombre.addFocusListener( new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println( "lost" );
				if (tfNombre.getText().equals("") || tfNombre.getText().equals("<Nombre>")) {
					bAdelante.setEnabled( false );
				} else {
					bAdelante.setEnabled( true );
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println( "gain" );
			}
		});
	}
	
	class GestorBotonCancelarInterna implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println( "Acción en botón" );
			lSuperior.setText( "Ventana cancelada" );
		}
	}
	
	
}

class GestorBotonCancelar implements ActionListener {
	JLabel label;
	public GestorBotonCancelar( JLabel label ) {
		this.label = label;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( "Acción en botón" );
		label.setText( "Ventana cancelada" );
	}
}

