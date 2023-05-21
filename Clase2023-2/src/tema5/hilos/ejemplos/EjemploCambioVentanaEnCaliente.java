package tema5.hilos.ejemplos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ejemplo de cambio de ventana en caliente (revalidate)
 * @author andoni.eguiluz at deusto.es
 */
@SuppressWarnings("serial")
public class EjemploCambioVentanaEnCaliente extends JFrame {
	
	public static void main(String[] args) {
		EjemploCambioVentanaEnCaliente tt = new EjemploCambioVentanaEnCaliente();
		tt.setVisible( true );
	}
	
	JPanel pCentro = new JPanel();
	JCheckBox cbConRevalidate = new JCheckBox( "Usar revalidate()", true );
	int numBotones = 0;
	
	public EjemploCambioVentanaEnCaliente() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 800, 640 );
		JPanel pSuperior = new JPanel();
		JButton bPrueba = new JButton( "Púlsame!" );
		pSuperior.add( cbConRevalidate );
		getContentPane().add( pSuperior, BorderLayout.NORTH );
		getContentPane().add( bPrueba, BorderLayout.SOUTH );
		getContentPane().add( pCentro, BorderLayout.CENTER );
		bPrueba.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numBotones++;
				JButton bNuevo = new JButton( "Botón nuevo " + numBotones );
				pCentro.add( bNuevo );
				if (cbConRevalidate.isSelected()) {
					pCentro.revalidate();  // Fundamental hacer esto cuando se cambia la estructura de un contenedor "en caliente"
					pCentro.repaint();  // En algunas ocasiones es necesario hacer también repaint() (no siempre)
				}
			}
		});
	}
}
