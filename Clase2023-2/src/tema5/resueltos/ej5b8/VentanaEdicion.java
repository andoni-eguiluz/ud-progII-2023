package tema5.resueltos.ej5b8;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** Ventana principal del ejercicio 5b.8
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaEdicion extends JFrame {

	private JTextArea taEjercicioDia;
	private JTextField tfHoraInicio;
	private JTextField tfHoraFin;
	
	private DiaSemana dia;
	private DatosEjercicio datos;
	private VentanaPrincipal ventAnterior;
	
	public VentanaEdicion( VentanaPrincipal ventAnterior, DiaSemana dia, DatosEjercicio datos ) {
		this.dia = dia;
		this.datos = datos;
		this.ventAnterior = ventAnterior;
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Edición ejercicio " + dia );
		setSize( 500, 450 );
		
		// 1. Crear contenedores
		JPanel pIzquierdo = new JPanel();
		JPanel pInferior = new JPanel();
		
		// 2. Crear componentes
		tfHoraInicio = new JTextField( 5 );
		tfHoraFin = new JTextField( 5 );
		taEjercicioDia = new JTextArea();
		JButton bCancelar = new JButton( "Cancelar" );
		JButton bAceptar = new JButton( "Aceptar" );
		
		// 3. Formato de contenedores
		pIzquierdo.setLayout( new BoxLayout( pIzquierdo, BoxLayout.Y_AXIS ) );
		
		// 4. Formato de componentes
		tfHoraInicio.setMaximumSize( new Dimension( 200, 30 ) );
		tfHoraFin.setMaximumSize( new Dimension( 200, 30 ) );
		taEjercicioDia.setText( datos.getDatosEjercicio( dia ) );
		tfHoraInicio.setText( datos.getHoraIni( dia ) );
		tfHoraFin.setText( datos.getHoraFin( dia ) );
		
		// 5. Añadir componentes a contenedores
		getContentPane().add( pIzquierdo, BorderLayout.WEST );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		getContentPane().add( new JScrollPane( taEjercicioDia ), BorderLayout.CENTER );
		pIzquierdo.add( new JLabel( "Hora inicio: " ) );
		pIzquierdo.add( tfHoraInicio );
		pIzquierdo.add( new JLabel( "Hora fin: " ) );
		pIzquierdo.add( tfHoraFin );
		pInferior.add( bCancelar );
		pInferior.add( bAceptar );
		
		// 6. Añadir eventos
		bCancelar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bAceptar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				datos.setDatosEjercicio( dia, taEjercicioDia.getText() );
				datos.setHoraIni( dia, tfHoraInicio.getText() );
				datos.setHoraFin( dia, tfHoraFin.getText() );
				dispose();
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ventAnterior.setVisible( true );
			}
		});
	}

}