package tema5.hilos.ejemplos;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Date;

/** Clase con visualización de los dos hilos, el de main y el de Swing, en el mismo textarea
 * @author andoni.eguiluz at deusto.es
 */
@SuppressWarnings("serial")
public class VentanaIdeaDeHilos extends JFrame {
	
	public static void main(String[] args) {
		VentanaIdeaDeHilos v = new VentanaIdeaDeHilos();
		v.setVisible(true);
		v.fin = false;
		while (!v.fin) { // Bucle infinito hasta fin=true (se hace al cerrar la ventana)
			double r = v.procesoIntenso();
			v.area.append( r + "\n" );   // Y visualizo el resultado
		}
		v.dispose();
	}
	
	private boolean fin;
	private JPanel panelBoton;
	private JTextArea area;
	private JButton boton;

	public VentanaIdeaDeHilos() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize(600,400);
		this.setTitle("Ejemplo Sencillo para entender hilos");
		
		area = new JTextArea( 20, 50 );
		panelBoton = new JPanel();
		boton = new JButton("Pulsa Aquí");

		panelBoton.add(boton);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(area),"Center");
		this.getContentPane().add(panelBoton,"South");

		// Eventos
		boton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				area.append( "Botón Pulsado: " + new Date() + "\n" );  // Mensaje a la textarea
				
				// Y qué pasa si tarda mucho tiempo algo...?
				// try { Thread.sleep( 100000 ); } catch (InterruptedException e2) {}
				
				// Swing no se corta con las excepciones. Las muestra y sigue pintando
				// throw new RuntimeException();  
			}
		} );
		
		addWindowListener( new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				// Si no se hiciera esto el programa no acabaría nunca
				fin = true;
			}
		} );

	}
	
	private double procesoIntenso() {
		double r = 3.0;
		// Hago algo que tarda mucho tiempo
		try { Thread.sleep(2000); } catch (InterruptedException e) {}  // Simulado con un sleep
		r = (new java.util.Random()).nextDouble()*100;
		return r;
	}
	
}
