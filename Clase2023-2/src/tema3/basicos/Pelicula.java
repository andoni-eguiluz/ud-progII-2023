package tema3.basicos;

/** Clase Pelicula para probar Java Collections sobre una clase que programamos nosotros
 * Obsérvense las características que debemos añadir para incluirla en:
 * - Una búsqueda de lista
 * - Un HashSet o como clave de un HashMap
 * - Un TreeSet o como clave de un TreeMap
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Pelicula {
	private String nombre;
	public Pelicula( String nombre ) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return nombre;
	}
}
