package tema1.resueltos.ej11;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Bloque {
	// Parte static
	private static final Random random = new Random();  // Generador de aleatorios (1 para todos los bloques)
	private static final Color[] colores = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.BLACK };  // Array de colores aleatorios del bloque
	
	// Parte no static
	
	private double x;          // Coordenada x del centro del bloque
	private double y;          // Coordenada y del centro del bloque
	private double antX;       // Coordenada x del centro en el movimiento anterior (método mover)
	private double antY;       // Coordenada y del centro en el movimiento anterior (método mover)
	private double ancho;      // Píxels de ancho del bloque
	private double alto;       // Píxels de alto del bloque
	private double velX;       // Velocidad horizontal del bloque en píxels / segundo 
	private double velY;       // Velocidad vertical del bloque en píxels / segundo
	private Color colorBorde;  // Color del borde del bloque
	private Color colorFondo;  // Color del fondo del bloque
	
	/** Crea un bloque nuevo sin movimiento (con velocidad 0)
	 * @param x	Coordenada x del centro
	 * @param y	Coordenada x del centro
	 * @param ancho	Píxels de ancho
	 * @param alto	Píxels de alto
	 * @param color	Color de borde
	 * @param colorFondo	Color de fondo
	 */
	public Bloque(double x, double y, double ancho, double alto, Color color, Color colorFondo) {
		this( x, y, ancho, alto, 0.0, 0.0, color, colorFondo );
	}
	
	/** Crea un bloque nuevo de ancho y alto 10, borde azul y fondo blanco
	 * @param xCentro	coordenada x del centro
	 * @param yCentro	coordenada y del centro
	 */
	public Bloque(int xCentro, int yCentro) {
		this( xCentro, yCentro, 10, 10, Color.BLUE, new Color(255,255,255) );
	}

	/** Crea un bloque nuevo en las coordenadas 0,0 de ancho y alto 10, borde azul y fondo blanco
	 */
	public Bloque() {
		this( 0, 0, 10, 10, Color.BLUE, new Color(255,255,255) );
	}
	
	/** Crea un bloque nuevo
	 * @param x	Coordenada x del centro
	 * @param y	Coordenada x del centro
	 * @param ancho	Píxels de ancho
	 * @param alto	Píxels de alto
	 * @param velX	Velocidad horizontal en píxels/segundo (positivo hacia la derecha, negativo hacia la izquierda)
	 * @param velY	Velocidad vertical en píxels/segundo (positivo hacia la derecha, negativo hacia la izquierda)
	 * @param color	Color de borde
	 * @param colorFondo	Color de fondo
	 */
	public Bloque(double x, double y, double ancho, double alto, double velX, double velY, Color color, Color colorFondo) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velX = velX;
		this.velY = velY;
		this.colorBorde = color;
		this.colorFondo = colorFondo;
	}
	
	/** Crea un bloque aleatorio
	 * Todo el bloque dentro de la ventana recibida
	 * Ancho y alto entre 10 y 50 pixels
	 * Velocidad x,y entre 100 y 300 pixels / seg, positiva o negativa
	 * Color de borde y fondo aleatorios entre los colores amarillo, rojo, verde, azul, cyan, magenta y negro
	 * @param v	Ventana de referencia para la creación (se toma su tamaño)
	 */
	public Bloque(VentanaGrafica v) {
		ancho = random.nextInt(41) + 10;
		alto = random.nextInt(41) + 10;
		x = random.nextInt( (int) Math.round(v.getAnchura()-ancho-ancho) ) + ancho;
		y = random.nextInt( (int) Math.round(v.getAltura()-alto-alto) ) + alto;
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

	/** Devuelve la coordenada x del centro del bloque
	 * @return	Coordenada horizontal
	 */
	public double getX() {
		return x;
	}
	
	/** Modifica la coordenada x del centro del bloque
	 * @param x	Nueva coordenada horizontal
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/** Devuelve la coordenada y del centro del bloque
	 * @return	Coordenada vertical
	 */
	public double getY() {
		return y;
	}
	
	/** Modifica la coordenada y del centro del bloque
	 * @param y	Nueva coordenada vertical
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/** Devuelve la anchura del bloque
	 * @return	Ancho en píxels
	 */
	public double getAncho() {
		return ancho;
	}
	
	/** Modifica la anchura del bloque
	 * @param ancho	Nuevo ancho en píxels
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	
	/** Devuelve la altura del bloque
	 * @return	Alto en píxels
	 */
	public double getAlto() {
		return alto;
	}
	
	/** Modifica la altura del bloque
	 * @param alto	Nuevo alto en píxels
	 */
	public void setAlto(double alto) {
		this.alto = alto;
	}
	
	/** Devuelve la velocidad x del bloque
	 * @return	Velocidad horizontal, en píxels/segundo
	 */
	public double getVelX() {
		return velX;
	}
	
	/** Modifica la velocidad x del bloque
	 * @param velX	Nueva velocidad horizontal, en píxels/segundo
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	/** Devuelve la velocidad y del bloque
	 * @return	Velocidad vertical, en píxels/segundo
	 */
	public double getVelY() {
		return velY;
	}
	
	/** Modifica la velocidad y del bloque
	 * @param velY	Nueva velocidad vertical, en píxels/segundo
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	/** Modifica la velocidad del bloque
	 * @param velX	Nueva velocidad x en píxeles/segundo
	 * @param velY	Nueva velocidad y en píxeles/segundo
	 */
	public void setVelXY( double velX, double velY ) {
		setVelX( velX );
		setVelY( velY );
	}
	
	/** Modifica la velocidad del bloque
	 * @param p	Nueva velocidad en forma de punto x,y - ambos en píxeles/segundo
	 */
	public void setVelXY( Point2D p ) {
		setVelXY( p.getX(), p.getY() );
	}
	
	/** Devuelve el color de borde del bloque
	 * @return	Color de borde
	 */
	public Color getColor() {
		return colorBorde;
	}
	
	/** Modifica el color de borde del bloque
	 * @param color	Nuevo color de borde
	 */
	public void setColor(Color color) {
		this.colorBorde = color;
	}
	
	/** Devuelve el color de fondo del bloque
	 * @return	Color de fondo
	 */
	public Color getColorFondo() {
		return colorFondo;
	}
	
	/** Modifica el color de fondo del bloque
	 * @param colorFondo	Nuevo color de fondo
	 */
	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}

	/** Dibuja el bloque
	 * @param v	Ventana en la que dibujar el bloque
	 */
	public void dibujar( VentanaGrafica v ) {
		v.dibujaRect( x-ancho/2, y-alto/2, ancho, alto, 2.0f, colorBorde, colorFondo );
	}
	
	/** Borra el bloque, pintándolo en blanco (se borra si el fondo es blanco)
	 * @param v	Ventana en la que borrar el bloque
	 */
	public void borrar( VentanaGrafica v ) {
		v.dibujaRect( x-ancho/2, y-alto/2, ancho, alto, 2.0f, Color.WHITE, Color.WHITE );
	}
	
	/** Mueve el bloque un tiempo indicado
	 * @param milis	Tiempo de movimiento, en milisegundos
	 */
	public void mover( double milis ) {
		antX = x;
		antY = y;
		x += velX*milis/1000;
		y += velY*milis/1000;
	}
	
	/** Determina si el bloque choca con el borde vertical del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si el bloque toca con el borde inferior o superior, false en caso contrario
	 */
	public boolean chocaBordeVertical( VentanaGrafica v ) {
		return y-alto/2 <= 0 || y+alto/2 >= v.getAltura();
	}
	
	/** Determina si el bloque choca con el borde horizontal del espacio de dibujado
	 * @param v	Ventana de dibujado
	 * @return	true si el bloque toca con el borde izquierdo o derecho, false en caso contrario
	 */
	public boolean chocaBordeHorizontal( VentanaGrafica v ) {
		return x-ancho/2 <= 0 || x+ancho/2 >= v.getAnchura();
	}
	
	/** Comprueba si hay choque entre bloques
	 * @param bloque2	Bloque con el que comprobar choque
	 * @return	true si se tocan este bloque y bloque2, false en caso contrario
	 */
	public boolean chocaCon( Bloque bloque2 ) {
		double distCentrosX = Math.abs( x-bloque2.x );
		double distCentrosY = Math.abs( y-bloque2.y );
		return distCentrosX <= ancho/2 + bloque2.ancho/2 && distCentrosY <= alto/2 + bloque2.alto/2;
	}
	
	/** Informa si el bloque contiene un punto de la ventana
	 * @param punto	Punto a consultar
	 * @return	true si ese punto está dentro del bloque (incluyendo su borde), false si no lo está
	 */
	public boolean contienePunto( Point punto ) {
		double distX = Math.abs( x-punto.x );
		double distY = Math.abs( y-punto.y );
		return distX <= ancho/2 && distY <= alto/2;
	}
	
	/** Devuelve el impacto de choque entre dos bloques
	 * @param bloque2	Bloque con el que probar el choque
	 * @return	Devuelve null si no chocan, un vector con forma de punto indicando el ángulo y amplitud del choque sobre el bloque en curso
	 */
	public Polar vectorChoqueConObjeto( Bloque bloque2 ) {
		if (chocaCon(bloque2)) {  // Chocan
			double difX = bloque2.x - x;
			double difY = bloque2.y - y;
			boolean solapeHoriz = (x-ancho/2 >= bloque2.x-bloque2.ancho/2 && x+ancho/2 <= bloque2.x+bloque2.ancho/2) ||
					(x-ancho/2 <= bloque2.x-bloque2.ancho/2 && x+ancho/2 >= bloque2.x+bloque2.ancho/2);  // Se solapan en toda su anchura
			boolean solapeVert = (y-alto/2 >= bloque2.y-bloque2.alto/2 && y+alto/2 <= bloque2.y+bloque2.alto/2) ||
					(y-alto/2 <= bloque2.y-bloque2.alto/2 && y+alto/2 >= bloque2.y+bloque2.alto/2);  // Se solapan en toda su anchura
			if (solapeHoriz) {  // Choque vertical
				double moduloChoque = alto/2+bloque2.alto/2 - Math.abs(difY);
				if (difY<0) moduloChoque = -moduloChoque;
				return Polar.crearPolarDesdeXY( 0, moduloChoque );
			} else if (solapeVert) {  // Choque horizontal
				double moduloChoque = ancho/2+bloque2.ancho/2 - Math.abs(difX);
				if (difX<0) moduloChoque = -moduloChoque;
				return Polar.crearPolarDesdeXY( moduloChoque, 0 );
			} else {
				double moduloChoqueX = ancho/2+bloque2.ancho/2 - Math.abs(difX);
				if (difX<0) moduloChoqueX = -moduloChoqueX;
				double moduloChoqueY = alto/2+bloque2.alto/2 - Math.abs(difY);
				if (difY<0) moduloChoqueY = -moduloChoqueY;
				return Polar.crearPolarDesdeXY( moduloChoqueX, moduloChoqueY );
			}
		} else {  // No chocan
			return null;
		}
	}	

	/** Calcula si hay choque con una bola
	 * @param bola	Bola con la que comprobar
	 * @return	true si hay choque con esa bola, false en caso contrario
	 */
	public boolean chocaCon( Bola bola ) {
		return vectorChoqueConObjeto(bola)!=null;
	}
	
	/** Calcula el posible choque entre un bloque y una bola
	 * @param bola	bola que queremos comprobar si choca con el bloque
	 * @return	null si no chocan, vector de choque con forma de punto si chocan
	 */
	public Polar vectorChoqueConObjeto( Bola bola ) {
		double distCentrosX = Math.abs( x-bola.getX() );
		double distCentrosY = Math.abs( y-bola.getY() );
		// Choque horizontal con bloque
		if (bola.getY() >= y-alto/2 &&
			bola.getY() <= y+alto/2) {  // La y de la bola está dentro del bloque...
			if (bola.getX()-bola.getRadio()<=x+ancho/2 &&
				bola.getX()+bola.getRadio()>=x-ancho/2 ) {  // ...y la x lateral de la bola está dentro del bloque
				if (distCentrosX >= 0) {
					return Polar.crearPolarDesdeXY( (x-ancho/2) - (bola.getX()+bola.getRadio()), 0 );
				} else {
					return Polar.crearPolarDesdeXY( (x+ancho/2) - (bola.getX()-bola.getRadio()), 0 );
				}
			}
		}
		// Choque vertical con bloque
		if (bola.getX() >= x-ancho/2 &&
			bola.getX() <= x+ancho/2) {  // La x de la bola está dentro del bloque...
			if (bola.getY()-bola.getRadio()<=y+alto/2 &&
				bola.getY()+bola.getRadio()>=y-alto/2 ) {  // ...y la y borde de la bola está dentro del bloque: choque
				if (distCentrosY >= 0) {
					return Polar.crearPolarDesdeXY( 0, (y-alto/2) - (bola.getY()+bola.getRadio()) );
				} else {
					return Polar.crearPolarDesdeXY( 0, (y+alto/2) - (bola.getY()-bola.getRadio()) );
				}
			}
		}
		// Coche con esquina: calculamos las distancias del centro de la bola a las cuatro esquinas
		double dist1 = Fisica.distancia( bola.getX(), bola.getY(), x+ancho/2, y+alto/2 );
		double dist2 = Fisica.distancia( bola.getX(), bola.getY(), x+ancho/2, y-alto/2 );
		double dist3 = Fisica.distancia( bola.getX(), bola.getY(), x-ancho/2, y+alto/2 );
		double dist4 = Fisica.distancia( bola.getX(), bola.getY(), x-ancho/2, y-alto/2 );
		if (dist1<=bola.getRadio() || dist2<=bola.getRadio() || dist3<=bola.getRadio() || dist4<=bola.getRadio()) { // Si alguna de esas distancias es inferior al radio, hay choque
			if (dist1<=bola.getRadio()) {
				return Polar.crearPolarDesdeXY( (x+ancho/2) - (bola.getX()-bola.getRadio()), (y+alto/2) - (bola.getY()-bola.getRadio()) );
			} else if (dist2<=bola.getRadio()) {
				return Polar.crearPolarDesdeXY( (x+ancho/2) - (bola.getX()-bola.getRadio()), (y-alto/2) - (bola.getY()+bola.getRadio()) );
			} else if (dist3<=bola.getRadio()) {
				return Polar.crearPolarDesdeXY( (x-ancho/2) - (bola.getX()+bola.getRadio()), (y+alto/2) - (bola.getY()-bola.getRadio()) );
			} else {
				return Polar.crearPolarDesdeXY( (x-ancho/2) - (bola.getX()+bola.getRadio()), (y-alto/2) - (bola.getY()+bola.getRadio()) );
			}
		}
		return null;  // Si no hay ninguno de los tres choques, es que no chocan
	}

	
	/** Calcula el área del bloque, partiendo de su información de ancho y alto
	 * @return	Área del bloque
	 */
	public double getArea() {
		return ancho*alto;
	}

	/** Calcula el volumen del prisma que correspondería al bloque, suponiendo que su profundidad es igual que su dimensión más corta
	 * @return	Volumen del bloque suponiendo un bloque tridimensional
	 */
	public double getVolumen() {
		return ancho*alto*Math.min(ancho, alto);
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
		return x - antY;
	}
	
	/** Devuelve el módulo de la velocidad
	 * @return	Módulo de velocidad (raíz cuadrada de la suma de cuadrados de las velocidades ortogonales vx y vy)
	 */
	public double getVelocidad() {
		return Math.sqrt( velX * velX + velY * velY );
	}
	
	/** Devuelve el cálculo de energía del bloque
	 * @return	Energía calculada con la fórmula general de energía, entendiendo el volumen del bloque correspondiente como masa: 0,5 x (m * v^2)
	 */
	public double getEnergia() {
		return 0.5 * getVolumen() * getVelocidad()*getVelocidad();
	}

	@Override
	public boolean equals(Object obj) {
		Bloque bloque2 = (Bloque) obj;
		return x==bloque2.x&& y==bloque2.y && ancho==bloque2.ancho && alto==bloque2.alto;
	}

	@Override
	public String toString() {
		return "[Bloque (" + x + "," + y + ") t=" + ancho + "," + alto + "]";
	}
	
}
