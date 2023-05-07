package tema5.hilos;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

/** Pequeño juego clicker con eventos de interacción de ratón de Swing
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class JuegoClicker {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		JuegoClicker mgt = new JuegoClicker( null );
		mgt.gameLoop();
	}
	
	// =================== Constantes y variables static
	
	public static final int ANCHURA_VENTANA = 1000;   // Píxels de ancho
	public static final int ALTURA_VENTANA = 800;     // Píxels de alto
	private static final long TIEMPO_POR_FRAME = 20;  // Msgs de tiempo entre fotogramas
	private static final Random random = new Random();  // Generador de aleatorios

	private static final String[] CLICKERS = { "img/eco.png", "img/yes.png", "img/no.png" };   // Clickers que aparecen
	private static final double[] PROB_CLICKERS = { 0.1, 0.55, 1.0 };  // Probabilidad de cada uno (incremental)
	private static final int PUNTOS_CLICK_ESPACIO = -10;  // Puntuación por click en espacio
	private static final double PUNTOS_CLICK_ECO = 100;   // Puntuación base por click en bonus
	private static final double PUNTOS_CLICK_YES = 10;    // Puntuación base por click en yes
	private static final int PUNTOS_CLICK_NO = -100;      // Puntuación por click en no
	
	public static int anchuraClicker = 100;             // Píxels de ancho por defecto de un elemento nuevo
	public static int alturaClicker = 100;              // Píxels de alto por defecto de un elemento nuevo
	private static double probNuevoClicker = 0.05;      // 5% = Probabilidad inicial de nuevo clicker cada fotograma
	private static long tiempoNuevoClicker = 3000;      // Tiempo inicial de clicker en pantalla (milisegundos)
	private static long tiempoJuegoProgreso = 30000;    // Tiempo de juego para cambio de progreso (milisegundos)
	
	// =================== Parte no static
	
	private JFrame ventana;       // Ventana de juego
	private JLabel lMensaje;      // Línea de mensaje
	private JLabel lMensajePunts; // Línea de mensaje de puntuación
	private JPanel panel;         // Panel principal de juego
	private boolean enJuego;      // Variable lógica de juego en curso
	private long tiempoInicioJuego;   // Tiempo de juego (milisegundos)
	private int nivelActual;      // Nivel actual de juego (en función de progreso)
	private int puntos;           // Puntos de juego
	private int numFallos;        // Contador de fallos (3 fallos acaban el juego)
	private ArrayList<ElementoClicker> listaClickers;  // Lista de clickers activos
	
	// ArrayList<ElementoLabel> listaEltos;   No hace falta porque se guarda en el propio panel - panel.getComponents()
	
	private JFrame ventanaLlamadora;
	
	/** Crea el gestor de elementos con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 * @param ventanaAVolver	Ventana que se visibiliza al acabar el juego
	 */
	public JuegoClicker( JFrame ventanaAVolver ) {
		ventanaLlamadora = ventanaAVolver;
		ventana = new JFrame( "Clicker Deusto" );
		// ventana.setLocation( 2000, 0 ); 
		ventana.setSize( ANCHURA_VENTANA, ALTURA_VENTANA  );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				acabar();
				// Visibiliza la ventana de menú
				if (ventanaLlamadora!=null) {
					ventanaLlamadora.setVisible( true );
				}
			}
		});
	}
	
	/** Ejecuta el gestor de tareas, lanzando el proceso de ejecución principal
	 */
	public void gameLoop() {
		initDatos();
		initEventosInteraccion();
		enJuego = true;
		while (enJuego) {
			// Lógica
			anyadirNuevoClicker();
			quitarClickersDesaparecidos();
			progreso();
			// Pintado
			panel.repaint();
			// Ciclo de espera de x milisegundos
			try {
				Thread.sleep( TIEMPO_POR_FRAME );
			} catch (InterruptedException e) {}
			// System.out.println( "Game loop " + System.currentTimeMillis() );
		}
	}
	
	public void acabar() {
		enJuego = false;
		ventana.dispose();
	}
	
	// Lógica de añadir nuevo clicker (cada x tiempo)
	private void anyadirNuevoClicker() {
		if (random.nextDouble() < probNuevoClicker) {  // Posibilidad de clicker cada fotograma.
			double queClicker = random.nextDouble();
			for (int i=0; i<PROB_CLICKERS.length; i++) {
				if (queClicker <= PROB_CLICKERS[i]) {
					int xRandom = random.nextInt( panel.getWidth() - anchuraClicker );
					int yRandom = random.nextInt( panel.getHeight() - alturaClicker );
					ElementoClicker elto = new ElementoClicker( tiempoNuevoClicker, CLICKERS[i], xRandom, yRandom, anchuraClicker, alturaClicker, 0.0, 1.0f );
					listaClickers.add( elto );
					asignaEventos( elto );
					panel.add( elto );
					break;
				}
			}
		}
	}
	
		// Proceso de gestión de creación de eventos para interacción de ratón en el label
		private void asignaEventos( ElementoClicker elto ) {
			elto.addMouseListener( new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickEnElemento( elto, e.getPoint() );
				}
			} );
		}
	

	// Lógica de quitar clickers
	private void quitarClickersDesaparecidos() {
		// Recorrido de lista para quitar los clickers desaparecidos
		// Se hace con un iterador porque así se puede borrar el elemento que se recorre - en un for each no se puede (sí se puede haciendo un for con índice)
		// Así no se podría - ver que la línea (*) provoca un error ConcurrentModificationException
		// for (Clicker clicker : listaClickers) {
		// 	if (clicker.desaparecido()) {
		// 		listaClickers.remove( clicker );  // (*)
		// 	}
		// }
		// Con iterador sí se puede borrar (solo el último elemento iterado)
		Iterator<ElementoClicker> it = listaClickers.iterator();
		while (it.hasNext()) {
			ElementoClicker clicker = it.next();
			if (clicker.desaparecido()) {
				panel.remove( clicker );
				it.remove();
			}
			
		}
		// También se podría hacer iterando por índice al revés, así no hay problema con los índices
		// for (int i=listaClickers.size()-1;i>=0;i--) {
		// 	Clicker clicker = listaClickers.get(i);
		// 	if (clicker.desaparecido()) {
		// 		panel.remove( clicker );
		// 		listaClickers.remove( i );
		// 	}
		// }
	}
	
	// Lógica de progreso (dificultad) de juego
	private void progreso() {
		if (numFallos>=3) {  // Con 3 fallos se acaba el juego
			acabar();
		}
		long tiempo = System.currentTimeMillis() - tiempoInicioJuego;
		int nivel = (int) (tiempo / tiempoJuegoProgreso + 1);
		if (nivel > nivelActual) {  // Cambio de nivel
			nivelActual++;
			PROB_CLICKERS[0] = PROB_CLICKERS[0] * 0.95;  // Menos probabilidad de bonus
			PROB_CLICKERS[1] = PROB_CLICKERS[1] * 0.95;  // Menos probabilidad de yes
			if (anchuraClicker > 5 && alturaClicker > 5) {  // 5 pixels es el mínimo
				anchuraClicker = (int) Math.round( anchuraClicker * 0.9 );  // Más pequeño el clicker
				alturaClicker = (int) Math.round( alturaClicker * 0.9 );  // Más pequeño el clicker
			}
			mensaje();
		}
	}

	// Mensaje de puntuación y nivel
	private void mensaje() {
		long tiempo = System.currentTimeMillis() - tiempoInicioJuego;
		long mins = tiempo / 60000L;
		long segs = (tiempo % 60000L) / 1000L;
		lMensajePunts.setText( "Tiempo de juego " + String.format( "%02d:%02d", mins, segs ) + " - nivel " + nivelActual + " - " + puntos + " puntos" );
	}
	
	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		listaClickers = new ArrayList<>();
		panel = new JPanel();
		panel.setLayout( null );  // Layout nulo para posicionar de forma directa en el panel sus componentes
		JPanel panelInf = new JPanel();
		lMensaje = new JLabel( " " );
		lMensaje.setFont( new Font( "Arial", Font.BOLD, 20 ) );
		panelInf.add( lMensaje );
		JPanel panelSup = new JPanel();
		lMensajePunts = new JLabel( " " );
		lMensajePunts.setFont( new Font( "Arial", Font.BOLD, 20 ) );
		lMensajePunts.setForeground( Color.BLUE );
		panelSup.add( lMensajePunts );
		ventana.add( panelInf, BorderLayout.SOUTH );
		ventana.add( panelSup, BorderLayout.NORTH );
		ventana.add( panel, BorderLayout.CENTER );
		tiempoInicioJuego = System.currentTimeMillis();  // Marca inicio de tiempo de juego
		nivelActual = 1;
		puntos = 0;
		numFallos = 0;
	}
	
	// Proceso de gestión de creación de eventos para interacción de ratón en el panel
	// Solo se activará esta interacción si no ocurre sobre los labels
	private void initEventosInteraccion() {
		lMensaje.setText( "Haz click en los elementos correctos lo antes posible para ganar puntos!" );
		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEnEspacio( e.getPoint() );
			}
		};
		panel.addMouseListener( ma );
		panel.addMouseMotionListener( ma );
		ventana.setVisible( true );
	}
	
	// Mecánicas de interacción
	
	// Click de elemento
	private void clickEnElemento( ElementoClicker elto, Point pto ) {
		if (elto.contienePunto( pto.x, pto.y )) {
			if (!elto.tieneClickCorrecto() && !elto.tieneClickIncorrecto()) {  // Si ya tenía click se desecha (no se puede clicar dos veces)
				// System.out.println( "Click en " + elto.getName() );
				// TODO ¿cómo mejorar esta alternativa con herencia?
				if (elto.getName().contains("eco")) {
					puntos += (int) Math.round( PUNTOS_CLICK_ECO * elto.getVidaRestante() );	
					elto.setClickCorrecto( true );
				} else if (elto.getName().contains("yes")) {
					puntos += (int) Math.round( PUNTOS_CLICK_YES * elto.getVidaRestante() );	
					elto.setClickCorrecto( true );
				} else if (elto.getName().contains("no")) {
					puntos += (int) Math.round( PUNTOS_CLICK_NO * elto.getVidaRestante() );	
					elto.setClickCorrecto( false );
					numFallos++;
				}
				panel.remove( elto ); panel.add( elto );  // Envía el elemento al fondo para que si hay solape el siguiente click lo coja el otro elemento (el orden en el panel determina el orden de dibujado y de gestión de evento de ratón)
				mensaje();
			}
		} else {
			clickEnEspacio( pto );
		}
	}
	
	private void clickEnEspacio( Point pto ) {
		// Acción de pulsación en espacio libre
		// System.out.println( "Click en espacio" );
		puntos += PUNTOS_CLICK_ESPACIO;
		mensaje();
	}
	
	
	@SuppressWarnings("unused")  // Hay muchos métodos que en este minijuego no se usan - evitamos los warnings
	/** Elemento visual para clicker. Basado en un JLabel
	 * Permite imagen con escala, rotación y transparencia
	 * Clase interna estática - es igual que si fuera una clase externa pero tiene "dependencia" de esta clase
	 * (está orientada a ser usada con la clase externa - puede hasta declararse private para que no pueda usarse fuera)
	 * @author andoni.eguiluz at ingenieria.deusto.es
	 */
	private static class ElementoClicker extends JLabel {
		
		// STATIC 
		private static final long serialVersionUID = 1L;
		private static final HashMap<String,BufferedImage> mapaImagenes = new HashMap<>(); // Mapa de imágenes (para no cargar de fichero más que una vez cada imagen)
		private static BufferedImage IMAGEN_CLICK_CORRECTO;
		private static BufferedImage IMAGEN_CLICK_INCORRECTO;
		static {
			try {
	    		URL imgURL = ElementoClicker.class.getResource( "img/ok.png" ).toURI().toURL();
				IMAGEN_CLICK_CORRECTO = ImageIO.read(imgURL);
	    		imgURL = ElementoClicker.class.getResource( "img/wrong.png" ).toURI().toURL();
				IMAGEN_CLICK_INCORRECTO = ImageIO.read(imgURL);
			} catch (Exception e) {  
				e.printStackTrace();
			}
		}
		// NO STATIC

		// La posición X,Y se hereda de JLabel
		protected long tiempoInicio;     // Tiempo de inicio del clicker (en msgs)
		protected long cicloDibujado;    // Tiempo de ciclo de dibujado del clicker (en msgs)
		protected int anchuraObjeto;     // Anchura definida del objeto en pixels
		protected int alturaObjeto;      // Altura definida del objeto en pixels
		protected double radsRotacion;   // Rotación del objeto en radianes
		protected float opacidad;        // Opacidad del objeto (0.0f a 0.1f)
		protected double zoom = 1.0;     // 1.0 = 100% zoom  (0.1 = 10%, 10.0 = 1000% ...)
		protected BufferedImage imagenObjeto;  // imagen para el escalado
		protected boolean clickCorrecto;   // Info de que se ha hecho click de forma correcta
		protected boolean clickIncorrecto; // Info de que se ha hecho click de forma incorrecta
				
		/** Crea un nuevo JLabel gráfico.<br>
		 * Si no existe el fichero de imagen, se crea un rectángulo blanco con borde rojo
		 * @param cicloDibujado	Tiempo de ciclo de dibujado en milisegundos (tiempo que el clicker está en pantalla). Durante este tiempo el clicker se dibuja progresivo desde transparente a opaco y vuelta a transparente
		 * @param nombreImagenObjeto	Nombre fichero donde está la imagen del objeto. Puede ser también un nombre de recurso desde el paquete de esta clase.
		 * @param x	Posición x de la esquina superior izquierda
		 * @param y	Posición y de la esquina superior izquierda
		 * @param anchura	Anchura del gráfico en píxels (si es <= 0 ocupa todo el ancho de la imagen original)
		 * @param altura	Altura del gráfico en píxels (si es <= 0 ocupa todo el alto de la imagen original)
		 * @param rotacion	Rotación del objeto (en radianes)
		 * @param opacidad	Opacidad del objeto (0.0f transparente a 1.0f opaco)
		 */
		public ElementoClicker( long cicloDibujado, String nombreImagenObjeto, int x, int y, int anchura, int altura, double rotacion, float opacidad ) {
			setHorizontalAlignment( JLabel.CENTER );
			setLocation( x, y );
			setText( "" );
			setName( nombreImagenObjeto );
			setImagen( nombreImagenObjeto ); // Cargamos el icono
			setSize( anchura, altura );
			setRotacion(rotacion);
			setOpacidad(opacidad);
			tiempoInicio = System.currentTimeMillis();  // Tiempo de lanzamiento del clicker
			this.cicloDibujado = cicloDibujado;
			clickCorrecto = false;
			clickIncorrecto = false;
		}
		
		/** Informa si el clicker ha acabado ya su ciclo de dibujado
		 * @return	true si ya lo ha acabado (ha pasado el tiempo de ciclo), false en caso contrario
		 */
		public boolean desaparecido() {
			return System.currentTimeMillis() > (tiempoInicio + cicloDibujado);
		}
		
		/** Activa la información de que se ha hecho un click
		 * @param correcto	true si el click que se ha hecho es correcto, false si es incorrecto
		 */
		public void setClickCorrecto( boolean correcto ) {
			if (correcto) clickCorrecto = true; else clickIncorrecto = true;
		}
		
		/** Informa si se ha hecho un click correcto
		 * @return	true si se ha hecho, false si no se ha hecho
		 */
		public boolean tieneClickCorrecto() {
			return clickCorrecto;
		}
		
		/** Informa si se ha hecho un click incorrecto
		 * @return	true si se ha hecho, false si no se ha hecho
		 */
		public boolean tieneClickIncorrecto() {
			return clickIncorrecto;
		}
		
		/** Devuelve el tiempo de vida de este clicker desde su creación
		 * @return	Tiempo de vida del clicker en milisegundos
		 */
		public long getTiempoVida() {
			return System.currentTimeMillis() - tiempoInicio;
		}
		
		/** Devuelve el porcentaje de vida restante de este clicker
		 * @return	Porcentaje restante de vida del clicker normalizado 0-1
		 */
		public double getVidaRestante() {
			return (cicloDibujado - getTiempoVida()) * 1.0 / cicloDibujado;
		}
		
		/** Modifica la posición de este objeto
		 * @param x	Nueva posición horizontal en píxels
		 */
		public void setX(int x) {
			setLocation( x, getY() );
		}

		/** Modifica la posición de este objeto
		 * @param y	Nueva posición vertical en píxels
		 */
		public void setY(int y) {
			setLocation( getX(), y );
		}

		/** Modifica la posición de este objeto
		 * @param x	Incremento de posición horizontal en píxels
		 * @param y	Incremento de posición vertical en píxels
		 */
		public void mover(int incX, int incY) {
			setLocation( getX() + incX, getY() + incY );
		}

		/** Modifica la altura de este objeto
		 * @param y	Nueva altura en píxels
		 */
		public void setAltura(int altura) {
			setSize( getWidth(), altura );
		}
		
		/** Modifica la anchura de este objeto
		 * @param y	Nueva anchura en píxels
		 */
		public void setAnchura(int anchura) {
			setSize( anchura, getHeight() );
		}
		
		/** Comprueba si el objeto gráfico del clicker corresponde a una coordenada interior
		 * @param x	Posición x del punto
		 * @param y	Posición y del punto
		 * @return	true si esa posición está dentro del objeto visual (se supone un círculo), false en caso contrario
		 */
		public boolean contienePunto(int x, int y) {
			double dist = Math.sqrt( (x-getWidth()/2)*(x-getWidth()/2) + (y-getHeight()/2)*(y-getHeight()/2) );
			return dist <= Math.min( getWidth(), getHeight() ) / 2;
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
			if (mapaImagenes.containsKey(nomImagenObjeto)) {  // Si ya se ha cargado antes, se reutiliza
				imagenObjeto = mapaImagenes.get(nomImagenObjeto);
			} else {  // Si no, se busca en fichero o en recurso
				File f = new File(nomImagenObjeto);
		        URL imgURL = null;
		        try {
		        	// Intento 1: desde fichero
		        	imgURL = f.toURI().toURL();
					imagenObjeto = ImageIO.read(imgURL);
		        } catch (Exception e) {
		        	// Intento 2: desde paquete (recurso)
					try {
			    		imgURL = ElementoClicker.class.getResource( nomImagenObjeto ).toURI().toURL();
						imagenObjeto = ImageIO.read(imgURL);
					} catch (Exception e2) {  
						// Intento 3: Mirar si está en el paquete de la clase llamadora
						StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
						for (int i=1; i<stElements.length; i++) {
							StackTraceElement ste = stElements[i];
							if ( !ste.getClassName().endsWith("JLabelGrafico") ) {  // Busca la clase llamadora a JLabelGrafico y busca ahí el recurso
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
		        } else {
		        	mapaImagenes.put( nomImagenObjeto, imagenObjeto );
		        }
			}
	        repaint();
		}
		
		/** Devuelve la anchura del rectángulo gráfico del objeto
		 * @return	Anchura
		 */
		public int getAnchuraObjeto() {
			return anchuraObjeto;
		}
		
		/** Devuelve la altura del rectángulo gráfico del objeto
		 * @return	Altura
		 */
		public int getAlturaObjeto() {
			return alturaObjeto;
		}
		
		/** Devuelve la rotación del objeto
		 * @return	Rotación actual del objeto en radianes
		 */
		public double getRotacion() {
			return radsRotacion;
		}

		/** Modifica la rotación del objeto
		 * @param rotacion	Nueva rotación del objeto (en radianes)
		 */
		public void setRotacion( double rotacion ) {
			radsRotacion = rotacion;
			repaint(); // Si no repintamos aquí Swing no sabe que ha cambiado el dibujo
		}
		
		/** Devuelve la opacidad del objeto
		 * @return	Opacidad del objeto (0.0f transparente a 1.0f opaco)
		 */
		public float getOpacidad() {
			return opacidad;
		}

		/** Modifica la opacidad del objeto
		 * @param opacidad	Opacidad del objeto (0.0f transparente a 1.0f opaco)
		 */
		public void setOpacidad(float opacidad) {
			if (opacidad<0.0f || opacidad>1.0f) return; // No se cambia si el valor es inválido
			this.opacidad = opacidad;
			repaint(); // Si no repintamos aquí Swing no sabe que ha cambiado el dibujo
		}
		
		/** Cambia el zoom por el zoom indicado
		 * @param zoom	Valor nuevo de zoom, positivo (0.1 = 10%, 1.0 = 100%, 2.0 = 200%...)
		 */
		public void setZoom( double zoom ) {
			if (zoom>0.0) {
				this.zoom = zoom;
				repaint();
			}
		}
		
		/** Devuelve el zoom actual
		 * @return	Zoom actual
		 */
		public double getZoom() {
			return zoom;
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
			// Cálculo de opacidad previo a cada dibujado
			if (desaparecido()) {
				setOpacidad( 0.0f );
			} else if (System.currentTimeMillis() < tiempoInicio + cicloDibujado/2 ) {  // Medio ciclo inicial (transparente a opaco) 
				float progreso = 1.0f * (System.currentTimeMillis() - tiempoInicio) / (cicloDibujado/2);
				setOpacidad( progreso );
			} else {  // Medio ciclo final (opaco a transparente)
				float progreso = 1.0f * (System.currentTimeMillis() - tiempoInicio - cicloDibujado/2) / (cicloDibujado/2);
				setOpacidad( 1.0f - progreso );
			}
			// Dibujado
			if (imagenObjeto!=null) {
				Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
				// Zoom
		        int anchoDibujado = (int)Math.round(anchuraObjeto*zoom);  // Calcular las coordenadas de dibujado con el zoom, siempre centrado en el label
		        int altoDibujado = (int)Math.round(alturaObjeto*zoom);
		        int difAncho = (getWidth() - anchoDibujado) / 2;  // Offset x para centrar
		        int difAlto = (getHeight() - altoDibujado) / 2;     // Offset y para centrar
				// Rotación
				g2.rotate( radsRotacion, getWidth()/2, getHeight()/2 );  // Incorporar al gráfico la rotación definida
				// Transparencia
				g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, opacidad ) ); // Incorporar la transparencia definida
		        g2.drawImage(imagenObjeto, difAncho, difAlto, anchoDibujado, altoDibujado, null);  // Dibujar la imagen con el tamaño calculado tras aplicar el zoom
		        // Deshacer rotación
		        g2.rotate( Math.PI*2-radsRotacion, getWidth()/2, getHeight()/2 );
		        // Deshacer transparencia
				g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
		        // Dibujar correcto o incorrecto
		        if (tieneClickCorrecto()) {
		        	g2.drawImage( IMAGEN_CLICK_CORRECTO, difAncho, difAlto, anchoDibujado, altoDibujado, null);
		        } else if (tieneClickIncorrecto()) {
		        	g2.drawImage( IMAGEN_CLICK_INCORRECTO, difAncho, difAlto, anchoDibujado, altoDibujado, null);
		        }
			}
			super.paintComponent(g);
		}

	}
	
	
}
