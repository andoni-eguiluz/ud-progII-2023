package tema5.ejemplos.eventos;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;  // Eventos

/** Clase de ejemplo de evento de Window para cierre
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")  // Evita el warning de serialización (no vamos a guardar esta ventana, no tiene mucho sentido serializar ventanas aunque se podría)
public class VentanaConEventoDeWindow extends JFrame {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		VentanaConEventoDeWindow vent = new VentanaConEventoDeWindow( "Ventana con cierre selectivo" );
		vent.setVisible( true );
	}

	
	// PARTE NO STATIC - Ventana
	
	private JLabel lMensaje;  // Label de mensaje
	private JTextArea taDatos; // Área de texto de datos
	
	public VentanaConEventoDeWindow( String titulo ) {
		super( titulo );  // Llama al constructor original de JFrame (clase padre)
		
		// 1.- Configuración de la ventana
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );   // No hace nada cuando el usuario pulsa cierre

		// 2.- Creación de contenedores (paneles) y componentes
		JPanel panelInferior = new JPanel();  // Por defecto en un panel FlowLayout
		taDatos = new JTextArea( 10, 30 );  // 30 "columnas" (caracteres aproximados de ancho), 10 filas
		lMensaje = new JLabel( "Pulsa botones inferiores para actuar", JLabel.CENTER );
		JButton bSalir = new JButton( "Salir" );
		
		// 3.- Asignación de componentes a contenedores
		JScrollPane spCentral = new JScrollPane( taDatos );
		this.add( spCentral, BorderLayout.CENTER );
		this.add( lMensaje, BorderLayout.NORTH );
		panelInferior.add( bSalir );
		this.add( panelInferior, BorderLayout.SOUTH );

		// 4.- Decoraciones
		this.pack(); // Redefinir el tamaño para que ajuste al mínimo necesario para todos los componentes
		// this.setLocationRelativeTo(null);  // Si se quiere que se centre en la ventana
		
		// 5.- Eventos
		bSalir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.addWindowListener( new WindowAdapter() {
			int primeraVez = 0;
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println( "Activated" );
				if (primeraVez==0) {
					taDatos.setText( "Arranco por primera vez!" );
					primeraVez++;
				} else {
					taDatos.append( "\nVuelvo de otra ventana! (" + primeraVez + " cambios)" );
					primeraVez++;
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println( "Opened" );
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "Closing" );
				if (primeraVez<=1) {
					JOptionPane.showMessageDialog( VentanaConEventoDeWindow.this, "Antes de salir prueba a cambiar de ventana" );
					// No cierra
				} else {
					dispose();  // Sí cierra
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println( "Closed" );
			}
		});
	}

}