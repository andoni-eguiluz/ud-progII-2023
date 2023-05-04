package tema5.resueltos;

// AWT -> Abstract Windowing Toolkit
// Swing -> J

import java.awt.*;
import javax.swing.*;

/** Solución ejercicio 5a.4
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class Ventana5a4 extends JFrame {

	/** Método de prueba de la ventana del ejercicio 5a.4
	 * @param args
	 */
	public static void main(String[] args) {
//		JFrame ventana = new JFrame( "Prueba" );	
		Ventana5a4 vent = new Ventana5a4();
		vent.setVisible( true );
//		Ventana5a4 vent2 = new Ventana5a4();
//		vent2.setVisible( true );
		System.out.println( "Fin de programa" );
	}
	
	// No static 
	public Ventana5a4() {
		// 0.- Configuración
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 600, 400 );
		setTitle( "Ventana de prueba" );
		setAlwaysOnTop(true);
		setLocationRelativeTo( null );
		// 1. Crear contenedores
		JPanel pInferior = new JPanel();
		// 2. Crear componentes
		JLabel lSuperior = new JLabel( "Ventana super útil" );
		JButton bCancelar = new JButton( "¡Cancela!");
		JButton bAdelante = new JButton( "¡A por ello!" );
		// 3. Formato de contenedores
		this.setLayout( new BorderLayout() ); // Por defecto la ventana tiene borderlayout
		pInferior.setLayout( new FlowLayout( FlowLayout.RIGHT ) ); // Por defecto
		pInferior.setBackground( Color.PINK );
		// 4. Formato de componentes
		bCancelar.setSize( 100, 40 );
		// 5. Añadir componentes a contenedores
		this.add( lSuperior, BorderLayout.NORTH );
		this.add( pInferior, BorderLayout.SOUTH );
//		this.add( bCancelar, BorderLayout.SOUTH );
//		this.add( bAdelante, BorderLayout.SOUTH );
		pInferior.add( bCancelar );
		pInferior.add( bAdelante );
	}
}