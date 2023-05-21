package tema5.hilos.ejemplos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/** Ejemplo de cómo se puede pasar de programación secuencial a programación orientada a eventos,
 * a veces incluyendo hilos.
 * Esta clase parte de PasoDeMainAVentanasEHilos1 que hace varios pasos en consola
 * utilizando ventanas, eventos e hilos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PasoDeMainAVentanasEHilos2 {
	// Ejemplo: cálculo de números primos
	public static void main(String[] args) {
		// Paso 1: pide valor máximo
		// Paso 2: validación de valor
		// Paso 3: se calculan y muestran los primos (supongamos que tardando un tiempo)
		// Paso 4: se pide info de si se vuelve a calcular o se acaba
		
		// Paso1 - se activa una ventana que pide el valor máximo
		VentanaPaso1 v = new VentanaPaso1();
		v.setVisible( true );
	}
	
	private static boolean esPrimo( int num ) {
		for (int i=2; i<=num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}


	@SuppressWarnings("serial")
	private static class VentanaPaso1 extends JFrame {
		private int valorMaximo = 0;
		private JTextField tfValorMax;
		public VentanaPaso1() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			pack();
			
			// Paso 1: pide valor máximo - EN VENTANA
			JLabel lSuperior = new JLabel( "Introduce valor máximo para cálculo de primos y pulsa <Enter>:" );
			add( lSuperior, BorderLayout.NORTH );
			tfValorMax = new JTextField( "", 20 );
			add (tfValorMax, BorderLayout.SOUTH );
			setLocationRelativeTo( null );  // Centra en escritorio
			pack();  // Tamaño mínimo necesario de la ventana
			
			// Paso 2: validación de valor - EN EVENTO
			tfValorMax.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						valorMaximo = Integer.parseInt( tfValorMax.getText() );
						if (valorMaximo>5) {
							// Seguir - cambio de ventana para seguir siguiente paso
							// (Paso de datos necesarios entre ventanas - o también podría ser con atributos)
							VentanaPaso2 v = new VentanaPaso2( valorMaximo, VentanaPaso1.this );
							VentanaPaso1.this.setVisible( false );
							v.setVisible( true );
						} else {
							JOptionPane.showMessageDialog( VentanaPaso1.this,
								"Error: valor debe ser mayor de 5" );
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog( VentanaPaso1.this,
							"Error: valor debe ser numérico entero" );
					}
				}
			});
		}
	}

	@SuppressWarnings("serial")
	private static class VentanaPaso2 extends JFrame {
		private JTextArea taPrimos;
		private JButton bAcabar;
		private JButton bVolver;
		private Thread hilo;
		public VentanaPaso2( int valorMaximo, JFrame ventDeVuelta ) {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			pack();
			
			// Paso 3: se calculan y muestran los primos (supongamos que tardando un tiempo)
			JLabel lSuperior = new JLabel( "Primos menores o iguales al indicado:" );
			add( lSuperior, BorderLayout.NORTH );
			taPrimos = new JTextArea( 10, 40 );
			taPrimos.setFont( new Font( "Arial", Font.PLAIN, 24 ) );
			add( new JScrollPane( taPrimos ), BorderLayout.CENTER );
			bAcabar = new JButton( "Acabar" );
			bVolver = new JButton( "Nuevo cálculo" );
			bAcabar.setEnabled( false );
			bVolver.setEnabled( false );
			JPanel pInferior = new JPanel();
			pInferior.add( bAcabar );
			pInferior.add( bVolver );
			add( pInferior, BorderLayout.SOUTH );
			setLocationRelativeTo( null );  // Centra en escritorio
			pack();  // Tamaño mínimo necesario de la ventana
			
			// Hace falta un hilo (ya que tarda un tiempo)
			// Se lanza por evento - en cuanto empieza la ventana en este caso
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					hilo = new Thread( new Runnable() {
						@Override
						public void run() {
							for (int i=1; i<=valorMaximo; i++) {
								if (esPrimo(i)) {
									taPrimos.append( "Primo: " + i + "\n" );
									taPrimos.setSelectionStart( taPrimos.getText().length() );  // Para ir bajando el scrollpane
									try {  // Pausa de medio segundo
										Thread.sleep( 500 );
									} catch (InterruptedException e) {
										return;  // Si hay interrupción
									}
								}
							}
							
							// Paso 4: se pide info de si se vuelve a calcular o se acaba
							// En eventos de botón
							taPrimos.append( "Pulsa botón para acabar o volver a calcular" );
							taPrimos.setSelectionStart( taPrimos.getText().length() );
							bAcabar.setEnabled( true );
							bVolver.setEnabled( true );
						}
					} );
					hilo.start();
				}
				@Override
				public void windowClosed(WindowEvent e) {
					// Interrupción de hilo y sacar ventana llamadora si el usuario cierra
					if (hilo!=null) {
						hilo.interrupt();
					}
					if (ventDeVuelta!=null) {
						ventDeVuelta.setVisible( true );
					}
				}
			});
			
			// Eventos de botón de paso 4
			bAcabar.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if (ventDeVuelta!=null) {
						ventDeVuelta.dispose();
					}
				}
			});
			bVolver.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					if (ventDeVuelta!=null) {
						ventDeVuelta.setVisible( true );
					}
				}
			});
		}
		
	}
	
	
}
