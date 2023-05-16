package tema5.resueltos.ej5b8;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** Ventana principal del ejercicio 5b.8
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JComboBox<DiaSemana> cbDiasSemana;
	private JTextArea taEjercicioDia;
	private DatosEjercicio datos;
	
	public VentanaPrincipal() {
		// Inicialización de datos
		datos = new DatosEjercicio();
		datos.cargaDeFichero();
		
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Planificación ejercicio semanal" );
		setSize( 600, 400 );
		setLocationRelativeTo( null );
		
		// 1. Crear contenedores
		JPanel pSuperior = new JPanel();
		JPanel pInferior = new JPanel();
		
		// 2. Crear componentes
		cbDiasSemana = new JComboBox<>( DiaSemana.values() );
		taEjercicioDia = new JTextArea();
		JButton bSalir = new JButton( "Salir" );
		JButton bEditar = new JButton( "Editar" );
		
		// 3. Formato de contenedores
		
		// 4. Formato de componentes
		taEjercicioDia.setEditable( false );
		refrescarDatos();
		
		// 5. Añadir componentes a contenedores
		getContentPane().add( pSuperior, BorderLayout.NORTH );
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		getContentPane().add( new JScrollPane( taEjercicioDia ), BorderLayout.CENTER );
		pSuperior.add( cbDiasSemana );
		pInferior.add( bSalir );
		pInferior.add( bEditar );
		
		// 6. Añadir eventos
		bSalir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bEditar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DiaSemana dia = (DiaSemana) cbDiasSemana.getSelectedItem();
				VentanaEdicion vEdi = new VentanaEdicion( VentanaPrincipal.this, dia, datos );
				vEdi.setLocation( getLocation() );
				vEdi.setSize( getSize() );
				vEdi.setVisible( true );
				setVisible( false );
			}
		});
		cbDiasSemana.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarDatos();
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				datos.guardaEnFichero();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				refrescarDatos();
				cbDiasSemana.requestFocus();
			}
		});
	}

	private void refrescarDatos() {
		taEjercicioDia.setText( datos.getDatosEjercicio( (DiaSemana) cbDiasSemana.getSelectedItem() ) );
	}
	
}