package tema3.javaCollections.sacaEquipos;

/** Clase envoltorio de entero pero MUTABLE (se puede cambiar el entero envuelto en el objeto, no como en Integer)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Entero {
	
	/** Método de prueba de Entero
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO ¿Podríamos crear sets o mapas de Entero?
		// Añadir hashCode - equals (hash) o Comparable (tree)
	}
	
	private int valor;
	/** Crea un nuevo entero mutable
	 * @param valor	Valor de ese entero
	 */
	public Entero(int valor) {
		this.valor = valor;
	}
	/** Devuelve el valor del entero
	 * @return	valor
	 */
	public int getValor() {
		return valor;
	}
	/** Cambia el valor del entero
	 * @param valor	nuevo valor
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/** Incrementa el valor del entero en una unidad
	 */
	public void inc() {
		valor++;
	}
	@Override
	public String toString() {
		return ""+valor;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Entero) {
			return valor == ((Entero)obj).valor;
		} else {
			return false;
		}
	}
	
}
