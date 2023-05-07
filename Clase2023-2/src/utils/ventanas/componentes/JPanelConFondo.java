package utils.ventanas.componentes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/** Panel con fondo gráfico
 * Con prueba (main) de cambio de modo gráfico para ver como afecta, y botón personalizado
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class JPanelConFondo extends JPanel {
	private static final long serialVersionUID = 1L;
	// Atributos para cambios de dibujado de gráfico
	private static boolean render = true;
	private static boolean antialias = true;
	private static boolean interpolacion = true;
	private static JFrame prueba;
	
	/** Método de prueba del panel
	 * @param args
	 */
	public static void main(String[] args) {
		prueba = new JFrame();
		prueba.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		prueba.setSize( 600, 500 );
		JPanel panelFondo = new JPanelConFondo( "/tema3/resueltos/pong/balon.png" );
		panelFondo.setLayout( new BorderLayout() );
		JButton botonPrueba = new JButton( "Botón de prueba encima de panel con imagen" );
		panelFondo.add( botonPrueba, "North" );
		prueba.getContentPane().add( panelFondo, "Center" );
		JPanel pBotonera = new JPanel();
		JToggleButton bInterp = new JToggleButton( "Interpolación", true );
		bInterp.setMnemonic( KeyEvent.VK_I );  // Se activa con click o con Alt+I
		pBotonera.add(bInterp);
		JToggleButton bRender = new JToggleButton( "Render", true );
		bRender.setMnemonic( KeyEvent.VK_R );  // Se activa con click o con Alt+R
		pBotonera.add(bRender);
		JToggleButton bAntialias = new JToggleButton( "Antialias", true );
		bAntialias.setMnemonic( KeyEvent.VK_A );  // Se activa con click o con Alt+A
		pBotonera.add(bAntialias);
		MiBoton botonPersonalizado = new MiBoton( "Prueba" );
		pBotonera.add(botonPersonalizado);
		panelFondo.add( pBotonera, "South" );
		// Eventos
		bInterp.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				interpolacion = !interpolacion;
				prueba.repaint();
			}
		});
		bRender.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				render = !render;
				prueba.repaint();
			}
		});
		bAntialias.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				antialias = !antialias;
				prueba.repaint();
			}
		});
		botonPersonalizado.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics2D g2 = (Graphics2D) botonPersonalizado.getGraphics();
				g2.setColor( Color.red );
				g2.drawOval( 0, 0, botonPersonalizado.getWidth(), botonPersonalizado.getHeight() );
			}
		});
		prueba.setVisible( true );
	}
	
	private BufferedImage imagenOriginal;
	public JPanelConFondo( String nombreImagenFondo ) {
		URL imgURL = getClass().getResource(nombreImagenFondo);
		try {
			imagenOriginal = ImageIO.read( imgURL );
		} catch (IOException e) {
		}
	}
	protected void paintComponent(Graphics g) {
		System.out.println( "Llamando pc");
		//super.paintComponent(g);  en vez de esto...
		Rectangle espacio = g.getClipBounds();  // espacio de dibujado del panel
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Código para que el dibujado se reescale al área disponible
		if (interpolacion) g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		if (render) g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		if (antialias) g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		// Dibujado
		g2.drawImage(imagenOriginal, 0, 0, (int)espacio.getWidth(), 
		             (int)espacio.getHeight(), null);
		g2.setColor( Color.blue );
		g2.setStroke( new BasicStroke( 5.7f ));
		g2.drawArc( 0, 0, 150, 150, -45, -90 );
	}
	/** Botón personalizado con una cruz roja encima
	 * @author andoni.eguiluz at ingenieria.deusto.es
	 */
	@SuppressWarnings("serial")
	private static class MiBoton extends JButton {
		public MiBoton( String texto ) {
			super(texto);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor( Color.red );
			g2.setStroke( new BasicStroke( 2.5f ) );
			g2.drawLine( 0, 0, getWidth(), getHeight());
			g2.drawLine( 0, getHeight(), getWidth(), 0);
		}
	}
	
}
