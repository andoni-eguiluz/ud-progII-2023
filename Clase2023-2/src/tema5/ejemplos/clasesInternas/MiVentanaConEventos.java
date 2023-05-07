package tema5.ejemplos.clasesInternas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** Código ejemplo de una ventana con algunos eventos con clases externas
 * @author andoni.eguiluz at deusto.es
 */
@SuppressWarnings("serial")
public class MiVentanaConEventos extends JFrame {
	protected JTextArea miCuadroDeTexto;
	protected JButton miBoton;
	protected JButton miBoton2;
	
	public MiVentanaConEventos() {
		// Crear contenedores
		JPanel miPanel = new JPanel();
		// Crear componentes
		miCuadroDeTexto = new JTextArea( "Prueba" );
		miBoton = new JButton( "Aceptar");
		miBoton2 = new JButton( "Cancelar");
		JButton miBoton3 = new JButton( "Una cosa" );
		JButton miBoton4 = new JButton( "Otra" );
		// Asociar componentes a contenedores
		this.add( miCuadroDeTexto, BorderLayout.CENTER );
		miPanel.setLayout( new GridLayout( 4, 1 ));
		miPanel.add( miBoton );
		miPanel.add( miBoton2 );
		miPanel.add( miBoton3 );
		miPanel.add( miBoton4 );
		this.add( miPanel, BorderLayout.SOUTH );
		// Formato
		this.setSize( 320, 200 );
		// Gestores de eventos
		MiEscuchadorBoton miEsc = new MiEscuchadorBoton( this );
		MiEscuchadorBoton2 miEsc2 = new MiEscuchadorBoton2();
		miBoton.addActionListener( miEsc );
		miBoton2.addActionListener( miEsc );
		miBoton3.addActionListener( miEsc2 );
		miBoton4.addActionListener( miEsc2 );
		MiEscuchadorRaton miEscR = new MiEscuchadorRaton();
		miCuadroDeTexto.addMouseListener( miEscR );
		miCuadroDeTexto.addFocusListener( new MiEscuchadorFoco() );
		this.addWindowListener( new MiCerradorDeVentana() );
	}
	
	public String getContenidoCuadroTexto() {
		return miCuadroDeTexto.getText();
	}
	
	public static void main( String[] s ) {
		MiVentanaConEventos miVentana = new MiVentanaConEventos();
		miVentana.setVisible( true );
		System.out.println( miVentana.getContenidoCuadroTexto() );
	}
}

class MiEscuchadorBoton implements ActionListener {
	private MiVentanaConEventos miVentana;
	public MiEscuchadorBoton( MiVentanaConEventos v ) {
		miVentana = v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// Se ejecuta con los botones 1 o 2
		if (e.getSource() == miVentana.miBoton) {
			miVentana.miCuadroDeTexto.append( "Botón ACEPTAR pulsado!\n" );
		} else {
			// miVentana.miCuadroDeTexto.append( "Botón CANCELAR pulsado!\n" );
			miVentana.dispose();
		}
	}
}

class MiEscuchadorBoton2 implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( "Botón " + e.getSource() + " pulsado!" );
	}
}

class MiEscuchadorRaton implements MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println( "Click en (" + e.getX() + "," + e.getY() + ")" );
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println( "Press en (" + e.getX() + "," + e.getY() + ")" );
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println( "Release en (" + e.getX() + "," + e.getY() + ")" );
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println( "Enter en (" + e.getX() + "," + e.getY() + ")" );
	}
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println( "Exit en (" + e.getX() + "," + e.getY() + ")" );
	}
}

class MiEscuchadorFoco implements FocusListener {
	@Override
	public void focusGained(FocusEvent e) {
		System.out.println( "Recibo el foco");
	}
	@Override
	public void focusLost(FocusEvent e) {
		System.out.println( "Pierdo el foco");
	}
}

class MiCerradorDeVentana implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Opened" );
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Closing" );
		((MiVentanaConEventos)(e.getSource())).dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Closed" );
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Iconified" );
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Deiconified" );
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Activated" );
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "Deactivated" );
	}
	
}
