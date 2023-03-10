package tema1.resueltos.ej13;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Circulo {

	private static float grosor = 1.0f; // grosor del círculo, en píxeles
	public static final int RADIO_MINIMO = 20; // Radio mínimo de círculo aleatorio
	public static final int RADIO_MAXIMO = 40; // Radio máximo de círculo aleatorio
	public static final int X_MAXIMA = 500; // X máxima de círculo aleatorio
	public static final int Y_MAXIMA = 300; // Y máxima de círculo aleatorio
	public static final int VEL_MINIMA = 100; // vel. mínima de círculo aleatorio (tanto en horizontal como en vertical)
	public static final int VEL_MAXIMA = 300; // vel. máxima de círculo aleatorio (tanto en horizontal como en vertical)
	private static final Random random = new Random();

	public static long tiempoInicioDesaparicion = 1500;  // msg en los que el fondo empieza a desaparecer
	public static long tiempoDesaparicion = 2000;  // msg en los que el fondo ya es transparente
	
	public static float getGrosor() {
		return grosor;
	}

	public static void setGrosor(float grosor) {
		Circulo.grosor = grosor;
	}

	
	// NO STATIC
	
	private int radioEnPixels;  // radio del círculo, en píxeles
	private int xCentro;
	private int yCentro;
	private Color colorBorde;
	private Color colorRelleno;
	private long tiempoCreacionMs;

	// Versión 3 - movimiento de los círculos
	private double velX;        // Velocidad horizontal del círculo en píxels / segundo 
	private double velY;        // Velocidad vertical del círculo en píxels / segundo
	
	/** Crea un círculo nuevo
	 * @param radioEnPixels	Radio del círculo en píxeles. Debe ser positivo
	 * @param xCentro	Coordenada x del centro del círculo, en píxeles (de izquierda a derecha)
	 * @param yCentro	Coordenada y del centro del círculo, en píxeles (de arriba abajo)
	 * @param colorBorde	Color del borde
	 * @param colorRelleno	Color de rellenoe
	 */
	public Circulo(int radioEnPixels, int xCentro, int yCentro, Color colorBorde, Color colorRelleno) {
		this.radioEnPixels = radioEnPixels;
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		this.colorBorde = colorBorde;
		this.colorRelleno = colorRelleno;
		tiempoCreacionMs = System.currentTimeMillis();
	}
	
	/** Crea un círculo nuevo
	 * @param radioEnPixels	Radio del círculo en píxeles. Debe ser positivo
	 * @param centro	Coordenadas del centro del círculo, en píxeles
	 * @param colorBorde	Color del borde
	 * @param colorRelleno	Color de rellenoe
	 */
	public Circulo(int radioEnPixels, Point centro, Color colorBorde, Color colorRelleno) {
		this( radioEnPixels, centro.x, centro.y, colorBorde, colorRelleno );  // Llamada a código de otro constructor (reutilización)
// Así no hace falta repetir código:
//		this.radioEnPixels = radioEnPixels;
//		this.xCentro = centro.x;
//		this.yCentro = centro.y;
//		this.colorBorde = colorBorde;
//		this.colorRelleno = colorRelleno;
//		tiempoCreacionMs = System.currentTimeMillis();
	}
	
	/** Crea un círculo nuevo de color azul y relleno amarillo
	 * @param radioEnPixels	Radio del círculo en píxeles. Debe ser positivo
	 * @param xCentro	Coordenada x del centro del círculo, en píxeles (de izquierda a derecha)
	 * @param yCentro	Coordenada y del centro del círculo, en píxeles (de arriba abajo)
	 */
	public Circulo(int radioEnPixels, int xCentro, int yCentro) {
		this( radioEnPixels, xCentro, yCentro, Color.BLUE, Color.YELLOW );
//		this.radioEnPixels = radioEnPixels;
//		this.xCentro = xCentro;
//		this.yCentro = yCentro;
//		colorBorde = Color.BLUE;
//		colorRelleno = Color.YELLOW;
//		tiempoCreacionMs = System.currentTimeMillis();
	}
	
	/** Crea un círculo nuevo de coordenadas, radio, color y velocidad aleatorios.
	 * El radio estará entre {@link #RADIO_MINIMO} y {@link #RADIO_MAXIMO} pixels
	 * El centro tendrá una x entre 0 y {@link #X_MAXIMA}, y entre 0 y {@link #Y_MAXIMA} 
	 * La velocidad será entre {@link #VEL_MINIMA} y {@value #VEL_MAXIMA} en cada uno de los dos ejes
	 */
	public Circulo() {
		radioEnPixels = RADIO_MINIMO + random.nextInt( RADIO_MAXIMO-RADIO_MINIMO+1 ); // 0-10 + 10
		xCentro = random.nextInt( X_MAXIMA );
		yCentro = random.nextInt( Y_MAXIMA );
		colorBorde = new Color( random.nextInt(256), random.nextInt(256), random.nextInt(256));
		Color[] colores = { Color.BLUE, Color.PINK, Color.CYAN, Color.GREEN };
		colorRelleno = colores[ random.nextInt( colores.length ) ];
		tiempoCreacionMs = System.currentTimeMillis();
		// Velocidades aleatorias
		velX = random.nextInt(VEL_MAXIMA-VEL_MINIMA+1) + VEL_MINIMA;
		velY = random.nextInt(VEL_MAXIMA-VEL_MINIMA+1) + VEL_MINIMA;
	}

	/** Crea un círculo nuevo de coordenadas, radio, color y velocidades aleatorias.
	 * El radio estará entre {@link #RADIO_MINIMO} y {@link #RADIO_MAXIMO} pixels
	 * La posición será una coordenada válida para la ventana indicada 
	 * La velocidad será entre {@link #VEL_MINIMA} y {@value #VEL_MAXIMA} en cada uno de los dos ejes
	 * @param ventana	Ventana que marca el espacio visible en el que localizar al círculo
	 */
	public Circulo( VentanaGrafica ventana ) {
		radioEnPixels = RADIO_MINIMO + random.nextInt( RADIO_MAXIMO-RADIO_MINIMO+1 ); // 0-10 + 10
		xCentro = random.nextInt( ventana.getAnchura() - radioEnPixels*2 ) + radioEnPixels ;
		yCentro = random.nextInt( ventana.getAltura() - radioEnPixels*2 ) + radioEnPixels;
		colorBorde = new Color( random.nextInt(256), random.nextInt(256), random.nextInt(256) );  // Una manera de generar un color aleatorio cualquiera
		Color[] colores = { Color.BLUE, Color.PINK, Color.CYAN, Color.GREEN };  // Otra manera, partiendo de una lista cerrada
		colorRelleno = colores[ random.nextInt( colores.length ) ];
		tiempoCreacionMs = System.currentTimeMillis();
		// Velocidades aleatorias
		velX = random.nextInt(VEL_MAXIMA-VEL_MINIMA+1) + VEL_MINIMA;
		velY = random.nextInt(VEL_MAXIMA-VEL_MINIMA+1) + VEL_MINIMA;
	}
	
	public int getxCentro() {
		return xCentro;
	}

	public void setxCentro(int xCentro) {
		this.xCentro = xCentro;
	}

	public int getyCentro() {
		return yCentro;
	}

	public void setyCentro(int yCentro) {
		this.yCentro = yCentro;
	}

	public Color getColorBorde() {
		return colorBorde;
	}

	public void setColorBorde(Color colorBorde) {
		this.colorBorde = colorBorde;
	}

	public Color getColorRelleno() {
		return colorRelleno;
	}

	public void setColorRelleno(Color colorRelleno) {
		this.colorRelleno = colorRelleno;
	}

	public int getRadioEnPixels() {
		return radioEnPixels;
	}

	
	// Versión 3 - movimiento de los círculos

	/** Devuelve la velocidad x del círculo
	 * @return	Velocidad horizontal, en píxels/segundo
	 */
	public double getVelX() {
		return velX;
	}
	
	/** Modifica la velocidad x del círculo
	 * @param velX	Nueva velocidad horizontal, en píxels/segundo
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	/** Devuelve la velocidad y del círculo
	 * @return	Velocidad vertical, en píxels/segundo
	 */
	public double getVelY() {
		return velY;
	}
	
	/** Modifica la velocidad y del círculo
	 * @param velY	Nueva velocidad vertical, en píxels/segundo
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	/** Modifica la velocidad del círculo
	 * @param velX	Nueva velocidad x en píxeles/segundo
	 * @param velY	Nueva velocidad y en píxeles/segundo
	 */
	public void setVelXY( double velX, double velY ) {
		setVelX( velX );
		setVelY( velY );
	}
	
	/** Modifica la velocidad del círculo
	 * @param p	Nueva velocidad en forma de punto x,y - ambos en píxeles/segundo
	 */
	public void setVelXY( Point2D p ) {
		setVelXY( p.getX(), p.getY() );
	}
	
	
	/** Devuelve el momento exacto en el que se creó el objeto
	 * @return	Tiempo en milisegundos
	 */
	public long getTiempoCreacionMs() {
		return tiempoCreacionMs;
	}

	/** Convierte el círculo en string en formato "(x,y) - radio"
	 */
	public String toString() {
		return String.format( "(%d,%d) - %d", xCentro, yCentro, radioEnPixels );
	}

	/** Devuelve el tiempo que este círculo lleva de vida desde que se creó hasta este momento
	 * @return	Número de milisegundos transcurridos
	 */
	public long getTiempoVida() {
		return System.currentTimeMillis() - tiempoCreacionMs;
	}

	/** Dibuja el círculo, con un color de fondo que va desapareciendo desde el milisegundo {@link #tiempoInicioDesaparicion} hasta el {@value #tiempoDesaparicion}
	 * @param v	Ventana en la que dibujar el círculo
	 */
	public void dibujar( VentanaGrafica v ) {
		Color colorFondoConTransparencia = colorRelleno;
		int opacidad = 255;
		if (getTiempoVida() > tiempoInicioDesaparicion) {
			long maxDesaparicion = tiempoDesaparicion - tiempoInicioDesaparicion;
			long miDesaparicion = getTiempoVida() - tiempoInicioDesaparicion;
			if (miDesaparicion > maxDesaparicion) miDesaparicion = maxDesaparicion;
			opacidad = 255 - (int) (miDesaparicion * 255 / maxDesaparicion);   // Convertir a escala 255,0 (255 es opaco, 0 transparente)
			colorFondoConTransparencia = new Color( colorRelleno.getRed(), colorRelleno.getGreen(), colorRelleno.getBlue(), opacidad );
		}
		v.dibujaCirculo( xCentro, yCentro, radioEnPixels, 2.0f, colorBorde, colorFondoConTransparencia );
		// v3
		v.dibujaImagen( "dr_strange.png", xCentro, yCentro, (int)radioEnPixels*2, (int)radioEnPixels*2, 1.0, 0.0, opacidad/255f );
	}

	/** Informa si el círculo contiene un punto de la ventana
	 * @param punto	Punto a consultar
	 * @return	true si ese punto está dentro del círculo (incluyendo su borde), false si no lo está
	 */
	public boolean contienePunto( Point punto ) {
		double distCentroAPunto = Math.sqrt( (xCentro-punto.x)*(xCentro-punto.x) + (yCentro-punto.y)*(yCentro-punto.y) );
		return distCentroAPunto <= radioEnPixels;
	}
	
	/** Mueve el círculo un tiempo indicado
	 * @param milis	Tiempo de movimiento, en milisegundos
	 */
	public void mover( double milis ) {
		xCentro += velX*milis/1000;
		yCentro += velY*milis/1000;
	}
	
	/** Determina si el círculo choca con el borde vertical del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si el círculo toca con el borde inferior o superior, false en caso contrario
	 */
	public boolean chocaBordeVertical( VentanaGrafica v ) {
		return yCentro-radioEnPixels <= 0 || yCentro+radioEnPixels >= v.getAltura();
	}
	
	/** Determina si el círculo choca con el borde horizontal del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si el círculo toca con el borde izquierdo o derecho, false en caso contrario
	 */
	public boolean chocaBordeHorizontal( VentanaGrafica v ) {
		return xCentro-radioEnPixels <= 0 || xCentro+radioEnPixels >= v.getAnchura();
	}
	

}
