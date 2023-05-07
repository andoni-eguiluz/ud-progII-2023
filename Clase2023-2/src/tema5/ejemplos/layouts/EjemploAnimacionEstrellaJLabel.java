package tema5.ejemplos.layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utils.ventanas.componentes.JLabelGrafico;

/** Ejemplo de animación de JLabel en ventana (layout nulo) con escuchadores en clases internas
 * @author andoni.eguiluz at deusto.es
 */
@SuppressWarnings("serial")
public class EjemploAnimacionEstrellaJLabel extends JFrame {
	
	public static void main(String[] args) {
		EjemploAnimacionEstrellaJLabel v = new EjemploAnimacionEstrellaJLabel();
		v.setVisible( true );
		v.mueveLaEstrella();
	}
	
	
	JLabel lEstrella;
	JPanel pEscenario;
	public EjemploAnimacionEstrellaJLabel() {
		setTitle( "Ejemplo animación de rebote" );
		setSize( 1000, 800 );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		lEstrella = new JLabelGrafico( "/tema2b/ejemplos/runner/img/star.png", 200, 200 );
		pEscenario = new JPanel();
		pEscenario.setLayout(null);
		lEstrella.setBounds( 0, 500, 300, 300 );
		pEscenario.add( lEstrella );
		getContentPane().add( pEscenario, BorderLayout.CENTER );
	}
	
	// Clase interna para definir una estrella que tiene posición y un JLabel gráfico
	private class Estrella {
		double x,y,velX,velY;
		JLabel grafico;
		static final double G = 980.0;  // px/sg2
		Estrella(JLabel grafico) { this.grafico = grafico; }
		void setPos( double x, double y ) { this.x = x; this.y = y; }
		void setVel( double velX, double velY ) { this.velX = velX; this.velY = velY; }
		// Actualiza la posición en función de la física de movimiento con velocidad y gravedad
		void actualizaFisica(double tiempo) {
			velY += (G * tiempo);  // v = v + a*t
			x += (velX * tiempo);  // s = s + v*t
			y += (velY * tiempo);
		}
		void actualizaGrafico() { grafico.setLocation( (int)x, (int)y ); }
	}

	// Movimiento
	// Con solo un % de restitución al botar (se pierde velocidad, no solo se invierte)
	// Añadiendo <Esc> para acabar y click para saltar (si está abajo)
	// Añadiendo botón de pausa
	// Añadiendo cierre de bucle de juego cuando se cierra la ventana
		boolean sigueJuego = true;
		boolean pulsadoSalto = true;
		boolean pausa = false;
	private void mueveLaEstrella() {
		Estrella c = new Estrella(lEstrella);
		// Añade eventos
		// 1. Escuchador de botón
		JButton bPausa = new JButton("Pausa");
		getContentPane().add( bPausa, BorderLayout.NORTH );
		ActionListener al = new MiActionListener();
		bPausa.addActionListener( al );
		// 2. Escuchador de teclado
		KeyListener kl = new MiKeyListener();
		pEscenario.addKeyListener( kl );
		bPausa.addKeyListener( kl );
		// 3. Escuchador de ratón
		MouseListener ml = new MiMouseListener();
		pEscenario.addMouseListener( ml );
		pEscenario.requestFocus();  // Para que el foco de teclado esté en el panel
		// 4. Escuchador de ventana
		WindowListener wl = new MiWindowListener();
		addWindowListener( wl );
		// 5. Escuchador de movimiento de ratón (sigue el Estrella al ratón en horizontal)
		MouseMotionListener mml = (new MouseMotionListener() {
			Estrella c;
			MouseMotionListener setEstrella( Estrella c) { this.c = c; return this; }
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println( e.getX() );
				c.velX = e.getX()-150 - c.x;
				if (c.velX > 300) c.velX = 300;
				else if (c.velX < -300) c.velX = -300;   // Limitar la velocidad horizontal
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		}).setEstrella( c );
		pEscenario.addMouseMotionListener( mml );
		// Bucle de juego:
		c.setPos( 0,  500 );
		c.setVel( 100, -1000 );
		double limX = 750;
		double limY = 500;
		int msgEspera = 1;
		long milis = System.currentTimeMillis();
		while (sigueJuego) {  // Va rebotando con vel y y gravedad
			try { Thread.sleep(msgEspera); } catch (InterruptedException e) { }
			if (pausa) {
				milis = System.currentTimeMillis();
			} else {
				c.actualizaFisica((System.currentTimeMillis()-milis) / 1000.0);
				milis = System.currentTimeMillis();
				if (c.y > limY) {
					c.y = limY;
					c.velY = -c.velY * 0.8;  // Rebota con 80% de restitución
					System.out.println( c.velY );
				}
				if (c.y >= limY-2 && pulsadoSalto) {  // Si está en el suelo o casi y se salta
					c.velY = -1000;
					pulsadoSalto = false;
				}
				if (c.x<0) { c.x = 0; c.velX = 100; }
				if (c.x>limX) { c.x = limX; c.velX = -100; }
				c.actualizaGrafico();
			}
		}
		// Si acaba cierra la ventana (se sale con escape... o con el icono de cierre)
		// Ojo - hay que quitar el dispose on close al poner el window listener,
		// porque si no al hacer click en el botón de cierre de la ventana,
		// los dos dispose() interfieren entre sí y este main se queda frito sin error
		dispose();
	}

	
	class MiKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				sigueJuego = false;
		}
	}

	class MiMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			pulsadoSalto = true;
		}
	}

	class MiActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pausa = !pausa;
		}
	}

	class MiWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.out.println( "FINAL");
			sigueJuego = false;
		}
	}
}