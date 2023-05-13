package tema5.hilos.ejemplos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** Prueba de problemas de Swing sin usar hilos (¿cómo se cambiaría para que funcione?)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaSwingHilos {
	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		v.setSize( 320, 200 );
		JButton bAceptar = new JButton( "Aceptar" );
		v.add( bAceptar );
		v.setVisible( true );
		bAceptar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i=0;i<10;i++) {  // Esto va a traer problemas...  :-)
						System.out.println( "Estoy trabajando... " + i + " segundos");
						Thread.sleep( 1000 );
					}
				} catch (Exception ex) {}
			}
		});
	}
}
