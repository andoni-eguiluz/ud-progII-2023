package tema5.ejemplos.layouts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Ejemplo de animación de un JLabel en una ventana (layout nulo) con escuchadores en clases externas
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")  // Evita el warning de Serializable
public class EjemploAnimacionBalonJLabel extends JFrame {

	/** Método principal de la clase
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		EjemploAnimacionBalonJLabel v = new EjemploAnimacionBalonJLabel();
		v.setVisible( true );
		System.out.println( v.pFondo.getHeight() );
		// Probar 4 diferentes movimientos:
		v.mueveElBalon1();  // Versión 1 - movimiento horizontal
		v.mueveElBalon2();  // Versión 2 - movimiento horizontal con velocidad decreciente
		v.mueveElBalon3();  // Versión 3 - movimiento horizontal y vertical con rebote
		v.mueveElBalon4();  // Versión 4 - movimiento con saltos con ratón
	}

	// No static
	
	JPanel pFondo;  // Panel de fondo
	JLabel lBalon;  // JLabel a mover en ese panel
	public EjemploAnimacionBalonJLabel() {
		setIconImage( new ImageIcon( "/src/tema3/img/balon.png").getImage() );
		// Configuración de ventana
		setSize( 1000, 800 );
		//setLocation( 2000, 50 );  // Si queremos cambiar la posición
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Crear comps y conts
		// (posibilidad 1) Acceder como fichero
		// lBalon = new JLabel( new ImageIcon( "src/img/balon.png" ));
		// (posibilidad 2) Acceder como recurso
		lBalon = new JLabel( new ImageIcon( getClass().getResource("/tema2/resueltos/pong/balon.png") ) );
		pFondo = new JPanel();
		// Asignar layouts - el layout nulo permite mover libremente sus componentes
		pFondo.setLayout( null );
		// Configuración de componentes
		lBalon.setBounds( 0, 500, 60, 60 );  // ATENCIÓN! Si no se pone bounds el valón no se ve (con layout nulo el contenedor no ajusta a sus componentes)
		// Un JLabel gráfico no da mucho "juego", solo posición y tamaño (ver JLabelGraficoAjustado)
		// lBalon.setLocation( 0, 500 );  // Es lo mismo bounds que location + size
		// lBalon.setSize( 60, 60 );
		// lBalon.setOpaque( true );  // si quisiéramos que tuviera fondo (normalmente son preferibles imágenes transparentes png)
		// lBalon.setBorder( BorderFactory.createLineBorder( Color.red, 2 ));  // si queremos ponerle el borde
		// Añadir comps a contenedores
		pFondo.add( lBalon ); // layout nulo
		getContentPane().add( pFondo, BorderLayout.CENTER ); // layout por defecto - JFrame - BorderLayout 
	}
	
	// Versión 1 - movimiento sencillo solo en x (horizontal)
	public void mueveElBalon1() {
		System.out.println( "Prueba 1 - movimiento sencillo");
		int xBalon = 0;
		int xFinBalon = 700;
		int yBalon = 500;
		for (xBalon = 0; xBalon < xFinBalon; xBalon++) {
			try { // Pausa de unos pocos msgs
				Thread.sleep( 5 );
			} catch (InterruptedException e) { }
			lBalon.setLocation( xBalon, yBalon );
		}
	}
	
	// Versión 2 - Con velocidad (decreciente)
	public void mueveElBalon2() {
		System.out.println( "Prueba 2 - velocidad decreciente");
		double xBalon = 0;
		double yBalon = 500;
		double velX = 500;  // px/seg
		int msgEspera = 20;  // aprox 1000/msgEspera fps
		long miTiempo = System.currentTimeMillis();
		while (velX>0) {
			try { Thread.sleep( msgEspera );
			} catch (InterruptedException e) { }
			// s = v * t
			xBalon += (velX * msgEspera / 1000.0);  // Cálculo físico del espacio en función de la velocidad
			lBalon.setLocation( (int)xBalon, (int)yBalon );
			if (velX>=0) velX -= 5;  // Decrecimiento de la velocidad
		}
		System.out.println( "Tiempo de animación: " + (System.currentTimeMillis() - miTiempo));
	}

	// Versión 3 - con velocidad vertical y horizontal
	public void mueveElBalon3() {
		System.out.println( "Prueba 3 - velocidad vertical y horizontal");
		double xBalon = 0;
		double yBalon = 500;
		double xIniBalon = 0;
		double xFinBalon = 700;
		final int ySuelo = 500;
		double velX = 300;  // px/seg
		double velY = -500;  // px/seg
		final double G = 980.0; // px/sg2
		int msgEspera = 20;  // aprox 1000/msgEspera fps
		long miTiempo = System.currentTimeMillis();
		while (System.currentTimeMillis() - miTiempo < 10000) {  // Para a los 10 segundos
			try { Thread.sleep( msgEspera );
			} catch (InterruptedException e) { }
			// v = v + a * t
			velY += (G * msgEspera / 1000.0); 
			// s = v * t
			xBalon += (velX * msgEspera / 1000.0);
			yBalon += (velY * msgEspera / 1000.0);
			if (yBalon > ySuelo) {
				yBalon = ySuelo;
				System.out.println( velY );
				velY = -velY * 0.8;
			}
			if (xBalon > xFinBalon || xBalon < xIniBalon) {
				velX = -velX;
			}
			lBalon.setLocation( (int)xBalon, (int)yBalon );
		}
		System.out.println( "Tiempo de animación: " + (System.currentTimeMillis() - miTiempo));
	}

	// Versión 4 - Con saltos controlados por ratón
	public static double velY = 0;  // px/seg  // Lo definimos static porque si no no puede accederse desde fuera del objeto (hay mejores maneras)
	boolean sigueJuego = true;
	boolean pausa = false;
	// Con eventos
	// 1.- Al hacer click el balón salte
	public void mueveElBalon4() {
		System.out.println( "Prueba 4 - salta con click");
		// Escuchadores (normalmente se hace en el constructor)
		// 1.- Ratón  (balón salta con click)
		MouseListener ml = new MiMouseListener();
		pFondo.addMouseListener( ml );
		// 2.- Teclado  (ventana acaba con escape)
		KeyListener kl = new MiKeyListener( this );  // Foco -> escucha el componente con foco
		pFondo.addKeyListener( kl );
		pFondo.requestFocus(); // El fondo toma el foco
		// 3.- Botón de pausa
		JButton bPausa = new JButton("Pausa");
		getContentPane().add( bPausa, BorderLayout.NORTH );
		ActionListener al = new MiActionListener( this );
		bPausa.addActionListener( al );
		bPausa.addKeyListener( kl );  // Si se pulsa el botón el foco lo toma el botón
		getContentPane().validate();
		// Configuración y ejecución del bucle de juego
		double xBalon = 0;
		double yBalon = 500;
		double xIniBalon = 0;
		double xFinBalon = 700;
		final int ySuelo = 500;
		double velX = 300;  // px/seg
		final double G = 980.0; // px/sg2
		int msgEspera = 20;  // aprox 1000/msgEspera fps
		long miTiempo = System.currentTimeMillis();
		// TODO Si se cierra la ventana con la x el programa nunca acaba ¿cómo se podría hacer para que acabara...?    (WindowListener?)
		while (sigueJuego) {
			try { Thread.sleep( msgEspera );
			} catch (InterruptedException e) { }
			if (pausa) {
				miTiempo = System.currentTimeMillis();
			} else {
				// v = v + a * t
				velY += (G * msgEspera / 1000.0); 
				// s = v * t
				xBalon += (velX * msgEspera / 1000.0);
				yBalon += (velY * msgEspera / 1000.0);
				if (yBalon > ySuelo) {
					yBalon = ySuelo;
					velY = -velY * 0.8;
				}
				if (xBalon > xFinBalon || xBalon < xIniBalon) {
					velX = -velX;
				}
				lBalon.setLocation( (int)xBalon, (int)yBalon );
			}
		}
		System.out.println( "Tiempo de animación: " + (System.currentTimeMillis() - miTiempo));
		dispose();
	}	
	
}

// Clases utilitarias externas

class MiMouseListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent arg0) {
		EjemploAnimacionBalonJLabel.velY += -300;  // Suma -300 a la velocidad vertical
	}
}

class MiKeyListener extends KeyAdapter {
	EjemploAnimacionBalonJLabel v;
	public MiKeyListener(EjemploAnimacionBalonJLabel v) {
		this.v = v;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			v.sigueJuego = false;
	}
}

class MiActionListener implements ActionListener {
	EjemploAnimacionBalonJLabel v;
	public MiActionListener(EjemploAnimacionBalonJLabel v) {
		this.v = v;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		v.pausa = !v.pausa;  // invertir true-false
	}
}
