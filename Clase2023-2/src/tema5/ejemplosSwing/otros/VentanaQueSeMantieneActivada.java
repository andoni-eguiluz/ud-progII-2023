package tema5.ejemplosSwing.otros;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Ventana que se vuelve a activar en Windows aunque se conmute de tarea
 * Ver por ejemplo  http://stackoverflow.com/questions/309023/how-to-bring-a-window-to-the-front
 * Los SO protegen parcialmente contra ventanas que se quieren activar solas... a veces hay que usar trucos
 * @author andoni.eguiluz.moran
 */
@SuppressWarnings("serial")
public class VentanaQueSeMantieneActivada extends JFrame {
	Component recibidorDeFoco;
	public VentanaQueSeMantieneActivada() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Me resisto a tu cambio de tarea!" );
		setSize( 400, 200 );
		setLocationRelativeTo( null );
		// setAlwaysOnTop( true );
		JTextField tfPrueba = new JTextField(10);
		getContentPane().add( tfPrueba, BorderLayout.NORTH );
		recibidorDeFoco = tfPrueba;
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				try { Thread.sleep( 1000 ); } catch (Exception ex) {}  // Pausa para permitir que Windows haga de las suyas y después recuperar la activación y el foco
			    setExtendedState(JFrame.ICONIFIED);
			    setExtendedState(JFrame.NORMAL);
			    // setExtendedState( JFrame.MAXIMIZED_BOTH ); // para ventana maximizada 
			    recibidorDeFoco.requestFocus();
			}
		});
	}
	
	public static void main(String[] args) {
		VentanaQueSeMantieneActivada v = new VentanaQueSeMantieneActivada();
		v.setVisible( true );
	}

}
