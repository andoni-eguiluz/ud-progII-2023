package tema1.clases;

import java.util.Date;

/** Ejemplo de clase para crear juegos con anotaciones sobre las clases y objetos en Java
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Juego {
	
	// STATIC
	private static int numTotalHorasJugadas;
	
	public static int getNumTotalHorasJugadas() {
		return numTotalHorasJugadas;
	}

	
	// NO STATIC 
	
	private String nombre = "default";
	private Genero genero;
	private int edadMinimaRecomendada;
	private Date fechaCompra;
	private int numHorasJugadas;
	private double precioFinal;
	private double precioSinIva;
	private double iva;  // Supongamos que el iva es un 21%
	
	/** Crea un nuevo juego
	 * @param nombre	Nombre de ese juego
	 */
	public Juego( String nombre ) {
		// 1.- CREA UN NUEVO OBJETO DE ESTA CLASE (asigna memoria)
		// 2.- Se asigna la REFERENCIA de ese espacio a la variable this
		// this se asigna al objeto en curso
		// 2bis.- Se llama al constructor del padre (super)
		// 3.- Nuestro trabajo - inicializar:
		this.nombre = nombre;
		// 4.- DEVUELVE LA REFERENCIA this
	}
	
	/** Crea un nuevo juego con nombre y horas jugadas. Si el nuevo dato de horas es incorrecto, 
	 * se muestra mensaje de error y el valor de horas no se cambia
	 * @param nombre	Nombre de ese juego
	 * @param numHorasJugadas	Número nuevo de horas jugadas. Debe ser positivo o cero.
	 */
	public Juego( String nombre, int numHorasJugadas ) {
		this.nombre = nombre;
		this.setNumHorasJugadas(numHorasJugadas);
		// Esto mejor hacerlo en el set que repetirlo también aquí:
		//		if (numHorasJugadas < 0) {
		//			System.err.println( "Error: número de horas " + numHorasJugadas + " es negativo" );
		//		} else {
		//			this.numHorasJugadas = numHorasJugadas;
		//		}
	}
	
	public Juego(String nombre, Genero genero, int edadMinimaRecomendada, Date fechaCompra, int numHorasJugadas,
			double precioFinal) {
		this( nombre, numHorasJugadas );
		// this.nombre = nombre;
		// this.setNumHorasJugadas(numHorasJugadas);
		this.genero = genero;
		this.edadMinimaRecomendada = edadMinimaRecomendada;
		this.fechaCompra = fechaCompra;
		this.precioFinal = precioFinal;
	}

	/** Devuelve el nombre del juego
	 * @return	Nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/** Devuelve el número de horas jugadas a este juego
	 * @return	Número entero de horas (>= 0)
	 */
	public int getNumHorasJugadas() {
		return this.numHorasJugadas;
	}
	
	/** Modifica el número de horas jugadas. Si el nuevo dato es incorrecto, 
	 * se muestra mensaje de error y el valor no se cambia
	 * @param nhoras	Número nuevo de horas jugadas. Debe ser positivo o cero.
	 */
	public void setNumHorasJugadas( int numHorasJugadas ) {
		// this se asigna al objeto en curso
		if (numHorasJugadas < 0) {
			System.err.println( "Error: número de horas " + numHorasJugadas + " es negativo" );
		} else {
			numTotalHorasJugadas -= this.numHorasJugadas;
			this.numHorasJugadas = numHorasJugadas;
			numTotalHorasJugadas += this.numHorasJugadas;
		}
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getEdadMinimaRecomendada() {
		return edadMinimaRecomendada;
	}

	public void setEdadMinimaRecomendada(int edadMinimaRecomendada) {
		this.edadMinimaRecomendada = edadMinimaRecomendada;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	/** Modifica el precio final del juego, cambiando también el precio base y el iva
	 * @param precioFinal	Precio final (iva incluido)
	 */
	public void setPrecioFinal(double precioFinal) {
		// TODO Corregir para el precio no negativo
		this.precioFinal = precioFinal;
		this.precioSinIva = precioFinal / 121 * 100;
		this.iva = precioFinal / 121 * 21;
	}

	public double getPrecioSinIva() {
		return precioSinIva;
	}

	public void setPrecioSinIva(double precioSinIva) {
		// TODO  Corregir para que los precios tengan coherencia
		this.precioSinIva = precioSinIva;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		// TODO  Corregir para que los precios tengan coherencia
		this.iva = iva;
	}

	@Override  // Herencia - lo entenderemos mejor
	public boolean equals(Object j) {  // Object en vez de Juego?  Lo entenderemos con herencia
		Juego juego2 = (Juego) j;
		// return this.nombre.equals(juego2.nombre) && this.numHorasJugadas==juego2.numHorasJugadas;
		boolean ret = this.nombre.equals(juego2.nombre);
		boolean ret2 = this.numHorasJugadas == juego2.numHorasJugadas;
		return ret && ret2;
	}
	
	@Override
	public String toString() {
		return getNombre() 
				+ ((genero==null) ? " " : (" [" + genero + "] ")) 
				+ String.format( "%.2f", precioFinal );
	}
	
}
