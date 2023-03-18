package tema2b.ejemplos.ofertas;

/** Interfaz para objetos que necesitan operación manual en su proceso
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public interface NecesitaOperacion {
	/** Devuelve el coste de la operación manual necesitada en el proceso
	 * @return	Coste de la operación en euros
	 */
	public double getCosteOperacion();
}
