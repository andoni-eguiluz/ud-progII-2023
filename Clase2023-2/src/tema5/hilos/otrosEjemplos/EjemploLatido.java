package tema5.hilos.otrosEjemplos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.ventanas.componentes.JLabelGrafico;

/** Latido de corazón con hilo de aumentar y decrementar gráfico (partiendo de JLabelGrafico)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploLatido {
	private static JLabelGrafico lCorazon;
	private static JFrame f;
	private static Object resp;
	private static Latido hilo;
	
	public static void main(String[] args) {
		resp = JOptionPane.showInputDialog( null, "¿Qué quieres que pase con el latido al cerrar la ventana?", "Configuración", JOptionPane.QUESTION_MESSAGE, null, 
			new String[] { "Que acabe", "Que siga latiendo 3 segundos", "Que no acabe nunca" }, "Que acabe" );
		if (resp==null) return;  // Acaba directamente
		f = new JFrame();
		f.setSize( 400, 300 );
		if (resp.equals("Que siga latiendo 3 segundos")) {  // Si queremos que siga viéndose hay que controlar la ventana de forma programática al pulsar el icono de cierre:
			f.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );	// Que no se cierre de forma automática (la tendremos que cerrar por código)
		} else {  // Si no que se cierre de forma automática
			f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		}
		JPanel pCentral = new JPanel(); 
		pCentral.setLayout( null );
		lCorazon = new JLabelGrafico( "src/tema5/hilos/otrosEjemplos/heart.png", 200, 200, 0.0, 1.0f );
		lCorazon.setLocation( 0, 0 );
		pCentral.add( lCorazon );
		JButton bLatir = new JButton( "Latir!" );
		f.getContentPane().add( pCentral, BorderLayout.CENTER );
		f.getContentPane().add( bLatir, BorderLayout.SOUTH );
		bLatir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hilo = new Latido();
				hilo.start();
			}
		});
		f.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!resp.equals("Que no acabe nunca")) {  // Si no acaba el hilo sigue ejecutándose sin visualizar nada
					if (hilo!=null) {
						hilo.interrupt();
					} else if (resp.equals("Que siga latiendo 3 segundos")) {  // Si no hay hilo es que no estaba latiendo: cerrar la ventana
 						f.dispose();
					}
				} else {  // Si se quiere hilo infinito
					if (hilo!=null) {  // Si además está latiendo sacar mensaje de aviso
						JOptionPane.showMessageDialog( null, "Lato hasta la eternidad..." );
					}
				}
			}
		});
		f.setVisible( true );
	}
	
	private static class Latido extends Thread {
		boolean creciendo = false;
		@Override
		public void run() {
			while (!Thread.interrupted()) { // Corta si se interrumpe
				zoom();
				try {
					Thread.sleep( 20 );
				} catch (InterruptedException e) {
					break; // Corta si se interrumpe en un sleep
				}
			}
			if (resp.equals("Que siga latiendo 3 segundos")) {  // Si queremos que siga viéndose hay que controlar la ventana de forma programática al pulsar el icono de cierre:
				long tiempoFin = System.currentTimeMillis();
				while (System.currentTimeMillis() - tiempoFin < 3000) {
					zoom();
					try {
						Thread.sleep( 20 );
					} catch (InterruptedException e) {  // Ahora sin cortar, estos 3 segundos sí o sí
					}
				}
				f.dispose(); // Cerrar la ventana
			}
		}
		// Hace el zoom creciente o decreciente poco a poco
		private void zoom() {
			if (creciendo) {
				lCorazon.setZoom( lCorazon.getZoom()*1.03 );
				if (lCorazon.getZoom()>1.5) creciendo = false;
			} else {
				lCorazon.setZoom( lCorazon.getZoom()*0.95 );
				if (lCorazon.getZoom()<0.7) creciendo = true;
			}
		}
	}
}
