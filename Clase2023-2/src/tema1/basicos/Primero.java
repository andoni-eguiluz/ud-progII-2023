package tema1.basicos;

import java.util.Date;

/** Primera clase de aprendizaje de Java - saludando al mundo
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Primero {
	
	/** Método principal de prueba de la clase
	 * @param args	Array de strings no utilizado
	 */
	public static void main(String[] args) {
		 /** Comentario de bloque
		  */
		// Comentario de línea
		java.lang.System.out.print( "¡Hola, mundo!" ); // Comentario de línea tras un código (hasta el final de la línea)
		System.out.println( Math.sqrt( 5.0 ) );  // No hay métodos globales, todos están dentro de clases. Las clases son globales
		System.out.println( new Date() );  // Y usaremos algunas clases para crear objetos (lo veremos)
		// Las clases se estructuran en paquetes:
		Date d;  // Variable d de la clase java.util.Date (se puede mencionar "Date" solo porque se ha puesto arriba el import)
		java.sql.Date d2;  // Variable d2 de la clase java.sql.Date (se menciona su nombre completo)
	}
	
}









