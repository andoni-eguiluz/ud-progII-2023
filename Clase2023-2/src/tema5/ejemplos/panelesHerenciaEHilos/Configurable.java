package tema5.ejemplos.panelesHerenciaEHilos;

/** Interfaz para los productos que puedan configurarse
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public interface Configurable {
	/** Permite al usuario configurar interactivamente el servicio
	 */
	public void configurar();
	/** Informa si está o no configurado
	 * @return	true si ya está configurado, false en caso contrario
	 */
	public boolean estaConfigurado();
}
