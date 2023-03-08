package tema1.resueltos.ej11;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Bola {
	// Parte static
	private static final Random random = new Random();  // Generador de aleatorios (1 para todas las bolas)
	private static final Color[] colores = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.BLACK };  // Array de colores aleatorios de la bola
	// Otra manera de inicializar los estáticos (a veces no se puede inicializar en la misma línea de la declaración, o hay que hacer más cosas):
	//	static {
	//		random = new Random(); 
	//		colores = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.BLACK };  // Array de colores aleatorios de la bola
	//	}
	
	// Parte no static
	
	private double x;          // Coordenada x del centro de la bola
	private double y;          // Coordenada y del centro de la bola
	private double antX;       // Coordenada x del centro en el movimiento anterior (método mover)
	private double antY;       // Coordenada y del centro en el movimiento anterior (método mover)
	private double radio;      // Píxels de radio de la bola
	private double velX;       // Velocidad horizontal de la bola en píxels / segundo 
	private double velY;       // Velocidad vertical de la bola en píxels / segundo
	private Color colorBorde;  // Color del borde de la bola
	private Color colorFondo;  // Color del fondo de la bola
	
	/** Crea una bola nueva sin movimiento (con velocidad 0)
	 * @param x	Coordenada x del centro
	 * @param y	Coordenada x del centro
	 * @param radio	Píxels de radio
	 * @param color	Color de borde
	 * @param colorFondo	Color de fondo
	 */
	public Bola(double x, double y, double radio, Color color, Color colorFondo) {
		this( x, y, radio, 0.0, 0.0, color, colorFondo );
	}
	
	/** Crea una nueva bola de radio 10, borde azul y fondo blanco
	 * @param xCentro	coordenada x del centro
	 * @param yCentro	coordenada y del centro
	 */
	public Bola(int xCentro, int yCentro) {
		this( xCentro, yCentro, 10, Color.BLUE, new Color(255,255,255) );
	}

	/** Crea una nueva bola en las coordenadas 0,0 de radio 10, borde azul y fondo blanco
	 */
	public Bola() {
		this( 0, 0, 10, Color.BLUE, new Color(255,255,255) );
	}
	
	/** Crea una bola nueva
	 * @param x	Coordenada x del centro
	 * @param y	Coordenada x del centro
	 * @param radio	Píxels de radio
	 * @param velX	Velocidad horizontal en píxels/segundo (positivo hacia la derecha, negativo hacia la izquierda)
	 * @param velY	Velocidad vertical en píxels/segundo (positivo hacia la derecha, negativo hacia la izquierda)
	 * @param color	Color de borde
	 * @param colorFondo	Color de fondo
	 */
	public Bola(double x, double y, double radio, double velX, double velY, Color color, Color colorFondo) {
		super();
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velX = velX;
		this.velY = velY;
		this.colorBorde = color;
		this.colorFondo = colorFondo;
	}
	
	/** Crea una bola aleatoria 
	 * Toda la bola dentro de la ventana recibida
	 * Radio entre 10 y 30 pixels
	 * Velocidad x,y entre 100 y 300 pixels / seg, positiva o negativa
	 * Color de borde y fondo aleatorios entre los colores amarillo, rojo, verde, azul, cyan, magenta y negro
	 * @param v	Ventana de referencia para la creación (se toma su tamaño)
	 */
	public Bola(VentanaGrafica v) {
		radio = random.nextInt(21) + 10;
		x = random.nextInt( (int) Math.round(v.getAnchura()-radio-radio) ) + radio;
		y = random.nextInt( (int) Math.round(v.getAltura()-radio-radio) ) + radio;
		velX = random.nextInt(201) + 100;
		velY = random.nextInt(201) + 100;
		if (random.nextBoolean() ) {
			velX = -velX;
		}
		if (random.nextBoolean()) {
			velY = -velY;
		}
		colorBorde = colores[ random.nextInt(colores.length) ];
		colorFondo = colores[ random.nextInt(colores.length) ];
	}

	/** Devuelve la coordenada x del centro de la bola
	 * @return	Coordenada horizontal
	 */
	public double getX() {
		return x;
	}
	
	/** Modifica la coordenada x del centro de la bola
	 * @param x	Nueva coordenada horizontal
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/** Devuelve la coordenada y del centro de la bola
	 * @return	Coordenada vertical
	 */
	public double getY() {
		return y;
	}
	
	/** Modifica la coordenada y del centro de la bola
	 * @param y	Nueva coordenada vertical
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/** Devuelve el radio de la bola
	 * @return	Radio en píxels
	 */
	public double getRadio() {
		return radio;
	}
	
	/** Modifica el radio de la bola
	 * @param radio	Nuevo radio en píxels
	 */
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	/** Devuelve la velocidad x de la bola
	 * @return	Velocidad horizontal, en píxels/segundo
	 */
	public double getVelX() {
		return velX;
	}
	
	/** Modifica la velocidad x de la bola
	 * @param velX	Nueva velocidad horizontal, en píxels/segundo
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	/** Devuelve la velocidad y de la bola
	 * @return	Velocidad vertical, en píxels/segundo
	 */
	public double getVelY() {
		return velY;
	}
	
	/** Modifica la velocidad y de la bola
	 * @param velY	Nueva velocidad vertical, en píxels/segundo
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	/** Modifica la velocidad de la bola
	 * @param velX	Nueva velocidad x en píxeles/segundo
	 * @param velY	Nueva velocidad y en píxeles/segundo
	 */
	public void setVelXY( double velX, double velY ) {
		setVelX( velX );
		setVelY( velY );
	}
	
	/** Modifica la velocidad de la bola
	 * @param p	Nueva velocidad en forma de punto x,y - ambos en píxeles/segundo
	 */
	public void setVelXY( Point2D p ) {
		setVelXY( p.getX(), p.getY() );
	}
	
	/** Devuelve el color de borde de la bola
	 * @return	Color de borde
	 */
	public Color getColor() {
		return colorBorde;
	}
	
	/** Modifica el color de borde de la bola
	 * @param color	Nuevo color de borde
	 */
	public void setColor(Color color) {
		this.colorBorde = color;
	}
	
	/** Devuelve el color de fondo de la bola
	 * @return	Color de fondo
	 */
	public Color getColorFondo() {
		return colorFondo;
	}
	
	/** Modifica el color de fondo de la bola
	 * @param colorFondo	Nuevo color de fondo
	 */
	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}

	/** Dibuja la bola
	 * @param v	Ventana en la que dibujar la bola
	 */
	public void dibujar( VentanaGrafica v ) {
		v.dibujaCirculo( x, y, radio, 2.0f, colorBorde, colorFondo );
	}
	
	/** Borra la bola, pintándola en blanco (se borra si el fondo es blanco)
	 * @param v	Ventana en la que borrar la bola
	 */
	public void borrar( VentanaGrafica v ) {
		v.dibujaCirculo( x, y, radio, 2.0f, Color.WHITE, Color.WHITE );
	}
	
	/** Mueve la bola un tiempo indicado
	 * @param milis	Tiempo de movimiento, en milisegundos
	 */
	public void mover( double milis ) {
		antX = x;
		antY = y;
		x += velX*milis/1000;
		y += velY*milis/1000;
	}
	
	/** Determina si la bola choca con el borde vertical del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si la bola toca con el borde inferior o superior, false en caso contrario
	 */
	public boolean chocaBordeVertical( VentanaGrafica v ) {
		return y-radio <= 0 || y+radio >= v.getAltura();
	}
	
	/** Determina si la bola choca con el borde horizontal del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si la bola toca con el borde izquierdo o derecho, false en caso contrario
	 */
	public boolean chocaBordeHorizontal( VentanaGrafica v ) {
		return x-radio <= 0 || x+radio >= v.getAnchura();
	}
	
	/** Comprueba si hay choque entre bolas
	 * @param bola2	Bola con la que comprobar choque
	 * @return	true si se tocan esta bola y bola2, false en caso contrario
	 */
	public boolean chocaCon( Bola bola2 ) {
		double distCentros = Math.sqrt( (x-bola2.x)*(x-bola2.x) + (y-bola2.y)*(y-bola2.y) );
		return (radio + bola2.radio) >= distCentros;
	}
	
	/** Calcula si hay choque con un bloque
	 * @param bloque	Bloque con el que comprobar
	 * @return	true si hay choque con ese bloque, false en caso contrario
	 */
	public boolean chocaCon( Bloque bloque ) {
		return bloque.chocaCon( this );  // Codificado en la clase Bloque
	}
	
	/** Informa si la bola contiene un punto de la ventana
	 * @param punto	Punto a consultar
	 * @return	true si ese punto está dentro de la bola (incluyendo su borde), false si no lo está
	 */
	public boolean contienePunto( Point punto ) {
		double distCentroAPunto = Math.sqrt( (x-punto.x)*(x-punto.x) + (y-punto.y)*(y-punto.y) );
		return distCentroAPunto <= radio;
	}
	
	/** Devuelve el impacto de choque entre dos bolas
	 * @param pelota2	Bola con la que probar el choque
	 * @return	Devuelve null si no chocan, un vector con forma de punto indicando el ángulo y amplitud del choque sobre la pelota en curso
	 */
	public Polar vectorChoqueConObjeto( Bola pelota2 ) {
		Polar ret = Polar.crearPolarDesdeXY( pelota2.x-x, pelota2.y-y );
		double moduloChoque = radio + pelota2.radio - ret.getModulo();
		if (moduloChoque < 0) return null;  // No hay choque
		ret.setModulo( moduloChoque );
		return ret;
	}	
	
	/** Calcula el área de la pelota, partiendo de su información de radio
	 * @return	Área de la pelota
	 */
	public double getArea() {
		return Math.PI*radio*radio;
	}

	/** Calcula el volumen de la esfera que correspondería a la pelota, partiendo de su información de radio
	 * @return	Volumen de la pelota suponiendo una esfera perfecta
	 */
	public double getVolumen() {
		return 4.0/3*Math.PI*radio*radio*radio;
	}

	/** Devuelve el avance horizontal del último movimiento (método mover)
	 * @return	Diferencia entre la posición x actual y la anterior
	 */
	public double getAvanceX() {
		return x - antX;
	}
	
	/** Devuelve el avance vertical del último movimiento (método mover)
	 * @return	Diferencia entre la posición y actual y la anterior
	 */
	public double getAvanceY() {
		return y - antY;
	}
	
	/** Devuelve el módulo de la velocidad
	 * @return	Módulo de velocidad (raíz cuadrada de la suma de cuadrados de las velocidades ortogonales vx y vy)
	 */
	public double getVelocidad() {
		return Math.sqrt( velX * velX + velY * velY );
	}
	
	/** Devuelve el cálculo de energía de la bola
	 * @return	Energía calculada con la fórmula general de energía, entendiendo el volumen de la esfera correspondiente como masa: 0,5 x (m * v^2)
	 */
	public double getEnergia() {
		return 0.5 * getVolumen() * getVelocidad()*getVelocidad();
	}

	@Override
	public boolean equals(Object obj) {
		Bola bola2 = (Bola) obj;
		return x==bola2.x&& y==bola2.y && radio==bola2.radio;
	}

	@Override
	public String toString() {
		return String.format( "[Bola (%.2f,%.2f) r=%.1f v=(%.4f,%.4f)]", x, y, radio, velX, velY );
	}
	
	
}
