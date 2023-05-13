package tema5.hilos;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.BadLocationException;

/** Ejemplo de problemas con Swing cuando se actualizan componentes directamente desde hilos
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class HilosConProblemasConSwing extends JFrame {

	// TODO 1. Ver qué pasa si se borra
	private static boolean BORRAR = true;
	
	/** Lanza la ventana de ejemplo
	 * @param args
	 */
	public static void main(String[] args) {
		HilosConProblemasConSwing vent = new HilosConProblemasConSwing();
		vent.setVisible( true );
	}
	
	private JTextArea taTexto;
	private boolean pausa;
	
	// No static 
	public HilosConProblemasConSwing() {
		// Inicialización de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Pulsa <Esc> para pausar la generación de letras" );
		setSize( 800, 600 );
		setLocationRelativeTo( null );
		taTexto = new JTextArea();
		taTexto.setFont( new Font( "Arial", Font.PLAIN, 24 ) );
		JScrollPane spCentral = new JScrollPane( taTexto );
		getContentPane().add( spCentral, BorderLayout.CENTER );
		
		// Pausa con la tecla Esc
		taTexto.addKeyListener( new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					pausa = !pausa;
				}
			}
		});

		// Pelea de hilos con swing: generar y borrar texto en un JTextArea
		Thread hiloAnyade = new Thread() {
			public void run() {
				char letra = 'a';
				while (true) {
					try {
						sleep(10);
					} catch (InterruptedException e) {}
					if (pausa) {
						continue;
					}
					taTexto.append( "" + letra );
					letra = (char) (letra+1);
					if (letra > 'z') {
						letra = 'a';
						// Acceso directo al componente desde el hilo
						taTexto.append( "\n" );
						// TODO 3. ¿Lo soluciona? Probar si se hace el acceso desde Swing
//						SwingUtilities.invokeLater( new Runnable() {
//							@Override
//							public void run() {
//								taTexto.append( "\n" );
//							}
//						});
					}
				}
			}
		};
		hiloAnyade.start();
		
		if (BORRAR) {
			Thread hiloBorra = new Thread() {
				public void run() {
					try {
						sleep(1000);
					} catch (InterruptedException e) {}
					while (true) {
						try {
							sleep(10);
						} catch (InterruptedException e) {}
						if (pausa) {
							continue;
						}
						// Quitar el primer carácter si hay algún carácter
						if (taTexto.getText().length()>0) {
							// Acceso directo al componente desde el hilo
							taTexto.replaceRange( "", 0, 1 );
							// TODO 2. ¿Lo soluciona? Probar si se hace el acceso desde Swing
//							SwingUtilities.invokeLater( new Runnable() {
//								@Override
//								public void run() {
//									taTexto.replaceRange( "", 0, 1 );
//								}
//							});
						}
					}
				}
			};
			hiloBorra.start();
		}
	}

}