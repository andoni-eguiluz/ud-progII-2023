package tema5.ejemplos.layouts;

import javax.swing.*;

import utils.ventanas.componentes.JLabelGrafico;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.*;

/** Ejemplo GridLayout con interacción para añadir componentes en una fila/columna concreta
 * En el GridLayout no se pueden añadir componentes sueltos, tienen que estar todos. Lo que sí puede es sustutuirse (quitar uno y poner otro en su lugar)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class EjemploGridLayoutInteractivo extends JFrame {
	public static final int NUM_FILAS = 10;
	public static final int NUM_COLS = 10;
	public static void main(String[] args) {
		EjemploGridLayoutInteractivo ventana = new EjemploGridLayoutInteractivo();
		ventana.setVisible( true );
	}
	
	public EjemploGridLayoutInteractivo() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Ejemplo Grid Layout - haz click" );
		setSize( 800, 600 );
		getContentPane().setLayout( new GridLayout( NUM_FILAS, NUM_COLS ));
		for (int i=0; i<NUM_FILAS*NUM_COLS; i++) {
			JPanel panelVacio = new JPanel();
			panelVacio.setBorder( BorderFactory.createLineBorder( Color.black, 1 ) );
			getContentPane().add( panelVacio );  // Mete componentes por defecto (con borde)
		}
		getContentPane().addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEnGrid( e.getPoint() );
			}
		});
	}

	private double rotacion = 0.0; // Para ir rotando poquito a poco
	private void clickEnGrid( Point click ) {
		int col = click.x / (getContentPane().getWidth()/NUM_COLS);
		int fila = click.y / (getContentPane().getHeight()/NUM_FILAS);
		System.out.println( "Click en " + fila + "," + col );
		JLabelGrafico grafico = new JLabelGrafico("/utils/ventanas/ventanaBitmap/img/UD-blue-girable.png", 60, 81, rotacion, 1.0f );  // Crea un componente gráfico nuevo
		grafico.setBorder( BorderFactory.createLineBorder( Color.blue ) );  // Con borde
		getContentPane().remove( fila*NUM_COLS + col );  // Quita el componente que estuviera en esa posición  (se calcula en índice lineal partiendo de fila/columna)
		getContentPane().add( grafico, fila*NUM_COLS + col );  // Mete en su lugar el componente (gráfico) nuevo
		getContentPane().revalidate();  // Reconstruye el panel
		rotacion += 0.2;  // Para ir rotando un poquito el escudo cada vez
	}

}
