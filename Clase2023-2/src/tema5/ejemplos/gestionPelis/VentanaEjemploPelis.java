package tema5.ejemplos.gestionPelis;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/** Ejemplo de ventana de pelis preparado para meter eventos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaEjemploPelis extends JFrame {

	private static ArrayList<Peli> listaPelis = new ArrayList<Peli>();
	
	// Main de prueba
	public static void main(String[] args) {
		initDatosEjemplo();
		VentanaEjemploPelis vent = new VentanaEjemploPelis();
		vent.setVisible( true );
	}

	private static void initDatosEjemplo() {
		listaPelis.add( new Peli( "Avatar", 2_847_246_203L, "avatar.jpg" ) );
		listaPelis.add( new Peli( "Avengers: Endgame", 2_797_501_328L, "endgame.jpg" ) );
		listaPelis.add( new Peli( "Titanic", 2_194_439_542L, "titanic.jpg" ) );
		listaPelis.add( new Peli( "Star Wars: The Force Awakens", 2_068_223_624L, "avatar.jpg" ) );
	}
	
	// No static 
	
	public VentanaEjemploPelis() {
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 800, 600 );
		// this.setLocationRelativeTo( null );

		// Crear contenedores
		JPanel pSuperior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pCentralIzq = new JPanel();
		JPanel pInferior = new JPanel();

		// Crear componentes
		JLabel lTitulo = new JLabel( "Catálogo de películas" );
		JButton bNueva = new JButton( "Nueva peli" );
		JButton bEditar = new JButton( "Editar peli" );
		JButton bConfirmar = new JButton( "Confirmar" );
		JButton bCancelar = new JButton( "Cancelar" );
		JLabel lUsuario = new JLabel( "Película:" );
		JLabel lPass = new JLabel( "Ingresos brutos:" );
		JTextField tfNombrePeli = new JTextField( 15 );
		JTextField tfIngresosPeli = new JTextField( 8 );
		JLabel lFoto = new JLabel( "Foto" ); // new JLabel( new ImageIcon( "src/tema789/ejemploPelis/endgame.jpg" ) );
		// JLabelGrafico lFoto = new JLabelGrafico( "endgame.jpg", 600, 400 );  // Foto escalable
		JTextArea taComentarios = new JTextArea();

		// Formato a contenedores
		pSuperior.setBackground ( Color.CYAN );
		pInferior.setBackground( Color.PINK );
		pInferior.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pCentral.setLayout( new BorderLayout() );
		pCentralIzq.setLayout( new BoxLayout( pCentralIzq, BoxLayout.Y_AXIS ) );

		// Formato de componentes
		lTitulo.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		bConfirmar.setEnabled( false );
		bCancelar.setEnabled( false );
		// tfNombrePeli.setMaximumSize( new Dimension( 1000, 25 ) );   // Si no se quiere que escalen en alto más de una altura
		// tfIngresosPeli.setMaximumSize( new Dimension( 1000, 25 ) );
		lFoto.setPreferredSize( new Dimension( 200, 400 ) );  // Para que la foto ocupe 400 píxeles de alto y 200 de ancho

		// Añadiendo componentes a contenedores
		add( pSuperior, BorderLayout.NORTH );
		add( pCentral, BorderLayout.CENTER );
		add( pInferior, BorderLayout.SOUTH );
		pSuperior.add( lTitulo );
		pInferior.add( bNueva );
		pInferior.add( bEditar );
		pInferior.add( bConfirmar );
		pInferior.add( bCancelar );
		pCentral.add( pCentralIzq, BorderLayout.WEST );
		pCentralIzq.add( lUsuario );
		pCentralIzq.add( tfNombrePeli );
		pCentralIzq.add( lPass );
		pCentralIzq.add( tfIngresosPeli );
		pCentralIzq.add( lFoto );
		pCentral.add( taComentarios, BorderLayout.CENTER );
	}
}
