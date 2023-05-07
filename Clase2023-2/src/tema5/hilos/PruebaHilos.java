package tema5.hilos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class PruebaHilos {
	
	private static boolean ventanaActiva = false;
	
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		vent.setSize( 400, 300 );
		vent.setLocation( 2800, 100 );
		
		vent.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				sacaCaracter( 'm' );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		vent.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ventanaActiva = false;
			}
		});
		vent.setVisible( true );
		ventanaActiva = true;
		
		// Nuevo hilo que también usa la consola - manera 1 - heredando de Thread
		Thread hilo = new Thread() { // run()
			@Override
			public void run() {
				long tiempo = System.currentTimeMillis();
				while (tiempo + 10000 > System.currentTimeMillis()) {
					sacaCaracter( '1' );
					try {
						Thread.sleep( 100 );  // Hace que el hilo en curso AL MENOS se duerma durante 100 msgs
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		hilo.start(); // LANZA UN NUEVO HILO que ejecuta run()
		
		Runnable ejecutable2 = new Runnable() {
			@Override
			public void run() {
				long tiempo = System.currentTimeMillis();
				while (ventanaActiva) {
					sacaCaracter( '2' );
					try {
						Thread.sleep( 100 );  // Hace que el hilo en curso AL MENOS se duerma durante 100 msgs
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread hilo2 = new Thread( ejecutable2 );
		hilo2.start();
		
		long tiempo = System.currentTimeMillis();
		while (tiempo + 10000 > System.currentTimeMillis()) {
			sacaCaracter( '-' );
			try {
				Thread.sleep( 50 );  // Hace que el hilo en curso AL MENOS se duerma durante 100 msgs
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static int contCars = 0;
	private static void sacaCaracter( char caracter ) {
		System.out.print( caracter );
		contCars++;
		if (contCars>=100) {
			System.out.println();
			contCars = 0;
		}
	}
}

class MiHilo extends Thread {
	@Override
	public void run() {
	}
}

class MiHilo2 implements Runnable {
	@Override
	public void run() {
	}
}