package tema5.ejemplos.layouts;

import java.awt.*;
import javax.swing.*;

/** Ejemplo de GridBagLayout
 * Basado en GridBagLayoutDemo.java de Java Tutorials, pero modificado para
 * adaptarlo a un caso más habitual (formulario tabulado)
 * 
 * GridBagLayout es un layout elaborado que permite hacer ventanas tan complejas como se quiera
 * Se basa en definir una especie de matriz donde se puede configurar alturas y anchuras de celdas
 * como se desee, y también se puede hacer que un componente ocupe varias celdas contiguas
 * @author andoni.eguiluz at deusto.es
 */
public class EjemploGridBagLayout {

	/** Método principal
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread: creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame( "Ejemplo GridBagLayout" );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLocation( 2000, 100 );  // Si se quiere poner en otra posición

		//Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	final static boolean RIGHT_TO_LEFT = false;  // Si se quiere hacer de derecha a izquierda poner true

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints(); // Objeto principal de gridbag - es el que configura cada componente
		c.fill = GridBagConstraints.HORIZONTAL;  // Rellenar el área horizontalmente por completo
		c.weighty = 0.0;   // Valor por defecto (ver último componente)

		JLabel lNombreAps = new JLabel("Nombre y apellidos:");
		c.weightx = 0.0; // Si hay espacio horizontal sobrante, esta columna no lo coge
		c.gridy = 0; c.gridx = 0;  // (0,0) Posición de la tabla imaginaria donde poner el componente
		pane.add( lNombreAps, c);
		
		JTextField tfNombre = new JTextField( 10 );
		c.weightx = 1.0; // Si hay espacio horizontal sobrante, esta columna lo coge
		c.gridy = 0; c.gridx = 1;  // Posición (0,1)
		pane.add( tfNombre, c);

		JTextField tfApellidos = new JTextField( 30 );
		c.weightx = 2.0; // Si hay espacio horizontal sobrante, estas columnas lo cogen más
		c.gridwidth = 2;  // Va a ocupar 2 columnas de ancho
		c.gridy = 0; c.gridx = 2;  // Posición (0,2) y (0,3) (al ocupar dos columnas)
		pane.add( tfApellidos, c );

		JLabel lDireccion = new JLabel("Dirección:");
		c.weightx = 0.0; // Coherencia con la columna
		c.gridwidth = 1;  // Restaurar la 1 columna de ancho
		c.gridy = 1; c.gridx = 0;  // (1,0)
		pane.add( lDireccion, c);

		JTextField tfDireccion = new JTextField();
		c.weightx = 1.0;
		// c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;  // Va a ocupar 3 columnas de ancho
		c.gridy = 1; c.gridx = 1;  // Posición (1,1) a (1,3) (al ocupar 3 columnas)
		pane.add( tfDireccion, c );

		JLabel lMensaje = new JLabel( "Mensaje de línea completa" );
		c.weightx = 1.0;
		lMensaje.setOpaque( true );
		lMensaje.setBackground( Color.LIGHT_GRAY );  // Para diferenciar el tamaño del mensaje con el color de fondo
		c.ipady = 40;      // Píxels a añadir a la altura mínima del componente (40 píxels más alto)
		c.gridwidth = 4;  // Va a ocupar las 4 columnas de ancho
		c.gridy = 2; c.gridx = 0; // En la posición (2,0) - hasta la (2,3) (al ocupar 4 columnas)
		pane.add( lMensaje, c );

		JButton bAceptar = new JButton( "Aceptar" );
		c.weightx = 1.0;
		c.weighty = 1.0;   // Si hay espacio vertical sobrante, lo coge todo esta fila
		c.gridwidth = 1;   // Restaurar 1 columna
		c.ipady = 0;       // quitar la altura extra
		c.anchor = GridBagConstraints.PAGE_END; // Ancla la línea al final del contenedor
		c.insets = new Insets(10,0,0,0);  // Padding (espacio) de 10 píxels arriba y 0 en las otras direcciones
		c.gridy = 3; c.gridx = 3;  // Posición (3,3)
		pane.add( bAceptar, c );
	}

}