package tema2b.resueltos.acertijo;

/** Comportamiento de objetos que pueden ser comidos por otro objeto
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public interface Comible {
	/** Comprueba si el objeto puede ser comido por otro
	 * @param o	objeto a comprobar
	 * @return	true si ese objeto o puede comer al objeto en curso, false en caso contrario
	 */
	public boolean puedeSerComidoPor( ObjetoAcertijo o );
}
