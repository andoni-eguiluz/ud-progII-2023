package tema5.ejemplos.layouts;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Ejemplo de layouts sencillos cambiando el layout "en caliente" con botones
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaConDiferentesLayouts extends JFrame {
	
	public VentanaConDiferentesLayouts() {
		super( "Ejemplos de layouts más típicos - Pulsa para cambiar de layout" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JButton boton1 = new JButton("BorderLayout");
		JButton boton2 = new JButton("FlowLayout");
		JButton boton3 = new JButton("GridLayout");
		JButton boton4 = new JButton("BoxLayout V");
		JButton boton5 = new JButton("BoxLayout H");

		getContentPane().setLayout(new GridLayout(3,2));  // Empieza con GridLayout
		// this.setLayout ... esto sería lo mismo. En el JFrame la ventana hace referencia a su panel principal (getContentPane())
		
		getContentPane().add(boton1);
		getContentPane().add(boton2);
		getContentPane().add(boton3);
		getContentPane().add(boton4);
		getContentPane().add(boton5);

		this.setSize(800,600);
		// this.setLocation( 2900, 100 );  // Si se quiere cambiar la posición
		
		boton1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle( "Con layout BorderLayout" );
				getContentPane().setLayout( new BorderLayout() );
				// El BorderLayout necesita que los componentes se identifiquen en posición
				getContentPane().add( boton1, BorderLayout.NORTH );
				getContentPane().add( boton2, BorderLayout.WEST );
				getContentPane().add( boton3, BorderLayout.CENTER );
				getContentPane().add( boton4, BorderLayout.EAST );
				getContentPane().add( boton5, BorderLayout.SOUTH );
				getContentPane().revalidate();  // El cambio de estructura en caliente necesita un revalidate()
				// Si quisiéramos quitar componentes para añadir otros tenemos métodos de eliminación:
				// getContentPane().remove( componente );  // Quita un componente particular
				// getContentPane().removeAll();  // Quita todos
			}
		});
		boton2.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle( "Con layout FlowLayout" );
				getContentPane().setLayout( new FlowLayout() );
				getContentPane().revalidate();  // El cambio de estructura en caliente necesita un revalidate()
			}
		});
		boton3.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle( "Con layout GridLayout (3,2)" );
				getContentPane().setLayout(new GridLayout(3,2));
				getContentPane().revalidate();  // El cambio de estructura en caliente necesita un revalidate()
			}
		});
		boton4.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle( "Con layout BoxLayout (Y_AXIS)" );
				getContentPane().setLayout( new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );
				getContentPane().revalidate();  // El cambio de estructura en caliente necesita un revalidate()
			}
		});
		boton5.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle( "Con layout BoxLayout (X_AXIS)" );
				getContentPane().setLayout( new BoxLayout(getContentPane(), BoxLayout.X_AXIS) );
				getContentPane().revalidate();  // El cambio de estructura en caliente necesita un revalidate()
			}
		});
	}
	public static void main(String[] args)
	{
		JFrame v = new VentanaConDiferentesLayouts();
		v.setVisible(true);
	}
}
