package tema5.hilos.otrosEjemplos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** Clase que usa un hilo para actualizar un label añadiendo letras
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploHilos2 {
	private static JLabel label;
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		label = new JLabel("Letras: ");
		f.add( label, BorderLayout.NORTH );
		JButton boton = new JButton( "Pulsa" );
		f.add( boton, BorderLayout.SOUTH );
		boton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread t = new Thread() {
					public void run() {
						for (char c='a'; c<='z'; c = (char) (c+1)) {
							label.setText( label.getText() + c );
							try { Thread.sleep(500); } catch (InterruptedException e) {}
						}
					}
				};
				t.start();
			}
		});
		f.pack();
		f.setVisible( true );
	}
}
