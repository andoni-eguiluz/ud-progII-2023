package utils.ventanas.componentes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Etiqueta gráfica para mostrar imágenes ajustadas al JLabel que se pueden mostrar de izquierda a derecha
 */
public class JLabelMostradoParcialmente extends JLabel {
	
	/** Método de prueba de label con ocultado lateral
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame( "Prueba Label mostrado progresivamente" );
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		f.getContentPane().setLayout( null );  // Layout nulo para colocar en cualquier lugar los componentes
		f.setSize( 600, 400 );
		JLabelMostradoParcialmente labelGrafico = new JLabelMostradoParcialmente( "/utils/ventanas/ventanaBitmap/img/sonic.png", 400, 140 );
		labelGrafico.setLocation( 100, 100 );
		f.add( labelGrafico );
		f.setVisible( true );
		try { Thread.sleep( 1000 ); } catch (Exception e) {}  // Espera 1 segundo
		for (int porc=0; porc<=100; porc++ ) { // Muestra progresivamente de nada a todo
			labelGrafico.setPorcAMostrar( porc*0.01 );
			try { Thread.sleep( 40 ); } catch (Exception e) {}  // Espera entre cambio y cambio
		}
	}

	// No static
	
	// la posición X,Y se hereda de JLabel
	protected int anchuraObjeto;   // Anchura definida del objeto en pixels
	protected int alturaObjeto;    // Altura definida del objeto en pixels
	protected BufferedImage imagenObjeto;  // imagen para el escalado
	public double porcAMostrar = 1.0; // de 0 a 1  - 0 no muestra nada, 1 muestra todo
	
	private static final long serialVersionUID = 1L;  // para serializar

	/** Crea una nueva etiqueta con mostrado lateral. Inicialmente mostrada por completo<br>
	 * Si no existe el fichero de imagen, se crea un rectángulo blanco con borde rojo
	 * @param nombreImagenObjeto	Nombre fichero donde está la imagen del objeto. Puede ser también un nombre de recurso desde el paquete de esta clase.
	 * @param anchura	Anchura del gráfico en píxels (si es <= 0 ocupa todo el ancho de la imagen original)
	 * @param altura	Altura del gráfico en píxels (si es <= 0 ocupa todo el alto de la imagen original)
	 */
	public JLabelMostradoParcialmente( String nombreImagenObjeto, int anchura, int altura ) {
		setName( nombreImagenObjeto );
		setImagen( nombreImagenObjeto ); // Cargamos el icono
		setSize( anchura, altura );
	}
	
	/** Indica cuánto se muestra del label de izquierda a derecha, redibujando la imagen
	 * @param porc	Porcentaje a mostrar, entre 0.0 = nada y 1.0 = todo
	 */
	public void setPorcAMostrar( double porc ) {
		porcAMostrar = porc;
		repaint();
	}
	
	@Override
	public void setSize(int anchura, int altura) {
        if (anchura <= 0 && imagenObjeto!=null) anchura = imagenObjeto.getWidth();
        if (altura <= 0 && imagenObjeto!=null) altura = imagenObjeto.getHeight();
		anchuraObjeto = anchura;
		alturaObjeto = altura;
    	super.setSize( anchura, altura );
		setMinimumSize( new Dimension( anchura, altura ) );
    	setPreferredSize( new Dimension( anchura, altura ) );
		setMaximumSize( new Dimension( anchura, altura ) );
	}
	
	/** Cambia la imagen del objeto
	 * @param nomImagenObjeto	Nombre fichero donde está la imagen del objeto. Puede ser también un nombre de recurso desde el paquete de esta clase.
	 */
	public void setImagen( String nomImagenObjeto ) {
		File f = new File(nomImagenObjeto);
        URL imgURL = null;
        try {
        	// Intento 1: desde fichero
        	imgURL = f.toURI().toURL();
			imagenObjeto = ImageIO.read(imgURL);
        } catch (Exception e) {
        	// Intento 2: desde paquete (recurso)
			try {
	    		imgURL = JLabelMostradoParcialmente.class.getResource( nomImagenObjeto ).toURI().toURL();
				imagenObjeto = ImageIO.read(imgURL);
			} catch (Exception e2) {  
				// Intento 3: Mirar si está en el paquete de la clase llamadora
				StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
				for (int i=1; i<stElements.length; i++) {
					StackTraceElement ste = stElements[i];
					if ( !ste.getClassName().endsWith("JLabelMostradoParcialmente") ) {  // Busca la clase llamadora y busca ahí el recurso
						try {
							Class<?> c = Class.forName( ste.getClassName() );
				    		imgURL = c.getResource( nomImagenObjeto ).toURI().toURL();
							imagenObjeto = ImageIO.read(imgURL);
							break;
						} catch (Exception e3) {
							// Sigue intentando
						}
					}
				}
			}
        }  
        if (imgURL == null || imagenObjeto == null) {  // Con cualquier error de carga, la imagen queda nula
        	imgURL = null;
        	imagenObjeto = null;
        }
        if (imagenObjeto==null) { // Si es nula, se pone un texto de error con el nombre sobre fondo rojo
			setOpaque( true );
			setBackground( Color.red );
			setForeground( Color.blue );
	    	setBorder( BorderFactory.createLineBorder( Color.blue ));
	    	setText( nomImagenObjeto );
        }
        repaint();
	}
	
	/** Actualiza la posición del objeto
	 * @param x	Coordenada x (doble) - se redondea al píxel más cercano
	 * @param y	Coordenada y (doble) - se redondea al píxel más cercano
	 */
	public void setLocation( double x, double y ) {
		setLocation( (int)Math.round(x), (int)Math.round(y) );
	}
	
	// Dibuja este componente de una forma no habitual
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagenObjeto!=null) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
	        g2.drawImage( imagenObjeto, 0, 0, getWidth(), getHeight(), null);  // Dibujar la imagen escalada al tamaño del label
	        g2.setColor( getBackground() );  // Poner el color del fondo
	        int xATapar = getWidth() - (int) (getWidth() * (1- porcAMostrar));
	        g2.fillRect( xATapar, 0, getWidth(), getHeight() );
		}
	}

}
