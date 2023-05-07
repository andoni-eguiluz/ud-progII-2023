package tema5.hilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Clase de ejemplo de menú de juego para llamar con un hilo a la ventana de juego
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class MenuJuego extends JFrame {

	public static void main(String[] args) {
		MenuJuego mj = new MenuJuego();
		mj.setVisible( true );
		System.out.println( "Fin main" );
	}
	
	public MenuJuego() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Menú de juego" );
		setSize( 300, 300 );
		JButton bConfig = new JButton( "Config" );
		JButton bJugar = new JButton( "Jugar" );
		JButton bSalir = new JButton( "Salir" );
		getContentPane().setLayout( new GridLayout( 3, 1 ) );
		getContentPane().add( bConfig );   // add( bConfig );
		getContentPane().add( bJugar );
		getContentPane().add( bSalir );
		// Eventos
		bJugar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuJuego.this.setVisible( false );
				// Opción de botón enabled - bJugar.setEnabled( false );

				// No funcionaría (Swing no puede ejecutar un bucle de tiempo real si tiene que seguir dibujando):
				// JuegoClicker mgt = new JuegoClicker();
				// mgt.gameLoop();
				
				// Crear un hilo 1 - heredando de Thread
				// Thread hilo = new MiHilo();
				// hilo.run();  // No magia
				// hilo.start();  // MAGIA!!
				
				// Crear un hilo 2 - implementando Runnable
				Runnable miRunnable = new MiRunnable();
				Thread hilo = new Thread( miRunnable );
				hilo.start();
				System.out.println( "Fin llamada game loop");
			}
		});
		bSalir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				juego.acabar();
				// Opción de botón enabled - bJugar.setEnabled( true );
			}
		});
	}

	private JuegoClicker juego;
	
	class MiRunnable implements Runnable {
		@Override
		public void run() {
			juego = new JuegoClicker( MenuJuego.this );
			juego.gameLoop();
		}
	}
	
}

// Ejemplo de creación de hilo con clase externa que hereda de Thread (método 1)
//class MiHilo extends Thread {
//	@Override
//	public void run() {
//		JuegoClicker mgt = new JuegoClicker( null );
//		mgt.gameLoop();
//	}
//}