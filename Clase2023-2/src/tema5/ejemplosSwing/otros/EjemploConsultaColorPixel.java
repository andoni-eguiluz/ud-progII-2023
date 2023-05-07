package tema5.ejemplosSwing.otros;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ejemplo de consulta de color de un pixel cualquiera en la ventana
 * (por ejemplo puede usarse para ver si un click se ha hecho en un objeto gráfico o en el fondo)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploConsultaColorPixel {

	private static Robot r; // Robot de awt utilizado para gestión programática de pantalla y de ratón y teclado
	private static Color fondo;  // Color de fondo (sacado con el robot)

	public static void main(String[] args) {
		try {
			r = new Robot();  
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		JFrame v = new JFrame();
		v.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JLabel coche = new JLabel( new ImageIcon( EjemploConsultaColorPixel.class.getResource( "/tema3/ejemplos/runner/img/nave.png" ) ) );
		v.add( coche, BorderLayout.CENTER );
		v.setSize( 600, 400 );
		coche.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			    Color c = r.getPixelColor( coche.getLocationOnScreen().x + e.getX(), coche.getLocationOnScreen().y + e.getY() );
				// System.out.println( "Color = " + c.getRed() + " " + c.getGreen() + " " + c.getBlue() );
				Graphics2D g2 = (Graphics2D) v.getContentPane().getGraphics();
				g2.setColor( c );
				g2.fillRect( 450, 50, 50, 50 );
				if (c.equals(fondo)) System.out.println( "Se mueve fuera de la imagen" );
				else System.out.println( "Se mueve dentro de la imagen" );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		v.addWindowListener( new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	    fondo = v.getContentPane().getBackground();
		System.out.println( fondo.getRed() + " " + fondo.getGreen() + " " + fondo.getBlue() );
		v.setVisible( true );
	}

}
