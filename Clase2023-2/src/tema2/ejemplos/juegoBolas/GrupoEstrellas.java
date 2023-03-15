package tema2.ejemplos.juegoBolas;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase que permite crear un grupo variable de estrellas y dibujarlo en pantalla
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GrupoEstrellas {
	private Estrella[] estrellas = null;  // Array de estrellas
	private int numEstrellas = 0;    // Número actual de estrellas
	private static final int NUM_MAX_POR_DEFECTO = 10;
	
	/** Crea un grupo de estrellas del tamaño máximo indicado
	 * @param numMax	Número máximo de estrellas que tendrá ese grupo
	 */
	public GrupoEstrellas( int numMax ) {
		estrellas = new Estrella[numMax];
		numEstrellas = 0;
	}

	/** Crea un grupo de estrellas de tamaño 10
	 */
	public GrupoEstrellas() {
		estrellas = new Estrella[NUM_MAX_POR_DEFECTO];
	}
	
	/** Devuelve el número de las estrellas actualmente en el grupo
	 * @return	Número de estrellas (entre 0 y núm máximo)
	 */
	public int getNumEstrellas() {
		return numEstrellas;
	}
	
	/** Añade una estrella al grupo
	 * @param estrella	Nueva estrella a añadir
	 * @return	true si el añadido es correcto, false si no cabe (el grupo está lleno)
	 */
	public boolean anyadeEstrella( Estrella estrella ) {
		if (numEstrellas==estrellas.length) {
			return false;
		}
		estrellas[numEstrellas] = estrella;
		numEstrellas++;
		return true;
	}
	
	/** Borra una estrella del grupo
	 * @param numEstrella	Índice de la estrella a borrar. Debe estar en el rango 0 a (n-1) siendo n el número de estrellas existentes.
	 */
	public void borraEstrella( int numEstrella ) {
		// Al borrar una estrella se desplazan las siguientes
		// Por ejemplo si borramos p
		// [ 0 | 1 | ... | p |p+1|p+2| ... | n-1 | ... ]
		// Quedarán
		// [ 0 | 1 | ... |p+1|p+2| ... | n-1 | ...     ]
		for (int i=numEstrella+1; i<numEstrellas; i++) {
			estrellas[i-1] = estrellas[i];
		}
		numEstrellas--;
	}
	
	/** Borra una estrella del grupo
	 * @param estrella	Estrella a borrar. Si no está en el grupo, no se hace nada
	 */
	public void borraEstrella( Estrella estrella ) {
		int posi = buscaEstrella( estrella );
		if (posi!=-1) {   // Si se encuentra estrella, se borra
			borraEstrella( posi );
		}
	}
	
	/** Devuelve una estrella del grupo
	 * @param numEstrella	Índice de la estrella, de 0 a n-1
	 * @return	Estrella correspondiente (la devuelve pero no la quita del grupo)
	 */
	public Estrella getEstrella( int numEstrella ) {
		return estrellas[numEstrella];
	}
	
	/** Busca una estrella en el grupo
	 * @param estrella	Estrella a buscar (mismo objeto)
	 * @return	Posición donde está la estrella, -1 si no se encuentra
	 */
	public int buscaEstrella( Estrella estrella ) {
		for (int i=0; i<estrellas.length; i++) {
			if (estrella==estrellas[i]) return i;
		}
		return -1;
	}
	
	// Añadido para poder buscar estrellas iguales siendo distintos objetos
	/** Busca una estrella en el grupo
	 * @param estrella	Estrella a buscar, de acuerdo a sus ATRIBUTOS
	 * @return	Posición donde está la estrella, -1 si no se encuentra
	 */
	public int buscaEstrellaEquals( Estrella estrella ) {
		for (int i=0; i<numEstrellas; i++) {
			if (estrella.equals(estrellas[i])) return i;
		}
		return -1;
	}
	
	/** Dibuja todas las estrellas del grupo
	 * @param v	Ventana en la que dibujar
	 */
	public void dibuja( VentanaGrafica v ) {
		// for :
		// podría hacer otras cosas
		// for (int i=0; i<numEstrellas; i = i + 2)
		for (int i=0; i<numEstrellas; i++) {
			estrellas[i].dibuja( v );
		}
	}

	// Devuelve el grupo de estrellas en formato [estrella1|estrella2|...]  (solo devuelve el grupo con el tamaño que tenga)
	@Override
	public String toString() {
		String ret = "[";
		for (int i=0; i<numEstrellas; i++) {
			ret += estrellas[i];
			if (i<numEstrellas-1) ret += "|";
		}
		return ret + "]";
	}
	
	/* Posible prueba de clase
	public static void main(String[] args) {
		GrupoEstrellas g = new GrupoEstrellas(2);
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g.anyadeEstrella( new Estrella() ) );
		System.out.println( g );
		g.borraEstrella(1);
		System.out.println( g );
	}
	*/
}
