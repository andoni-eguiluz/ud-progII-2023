package tema2.ejemplos.juegoBolas;

import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase que permite crear un grupo variable de pelotas y dibujarlo en pantalla
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GrupoPelotas {
	private Pelota[] pelotas = null;  // Array de pelotas
	private int numPelotas = 0;    // Número actual de pelotas
	private static final int NUM_MAX_POR_DEFECTO = 10;
	
	/** Crea un grupo de pelotas del tamaño máximo indicado
	 * @param numMax	Número máximo de pelotas que tendrá ese grupo
	 */
	public GrupoPelotas( int numMax ) {
		pelotas = new Pelota[numMax];
		numPelotas = 0;
	}

	/** Crea un grupo de pelotas de tamaño 10
	 */
	public GrupoPelotas() {
		pelotas = new Pelota[NUM_MAX_POR_DEFECTO];
	}
	
	/** Devuelve el número de las pelotas actualmente en el grupo
	 * @return	Número de pelotas (entre 0 y núm máximo)
	 */
	public int getNumPelotas() {
		return numPelotas;
	}
	
	/** Añade una pelota al grupo
	 * @param pelota	Nueva pelota a añadir
	 * @return	true si el añadido es correcto, false si no cabe (el grupo está lleno)
	 */
	public boolean anyadePelota( Pelota pelota ) {
		if (numPelotas==pelotas.length) {
			return false;
		}
		pelotas[numPelotas] = pelota;
		numPelotas++;
		return true;
	}
	
	/** Borra una pelota del grupo
	 * @param numPelota	Índice de la pelota a borrar. Debe estar en el rango 0 a (n-1) siendo n el número de pelotas existentes.
	 */
	public void borraPelota( int numPelota ) {
		// Al borrar una pelota se desplazan las siguientes
		// Por ejemplo si borramos p
		// [ 0 | 1 | ... | p |p+1|p+2| ... | n-1 | ... ]
		// Quedarán
		// [ 0 | 1 | ... |p+1|p+2| ... | n-1 | ...     ]
		for (int i=numPelota+1; i<numPelotas; i++) {
			pelotas[i-1] = pelotas[i];
		}
		numPelotas--;
	}
	
	/** Borra una pelota del grupo
	 * @param pelota	Pelota a borrar. Si no está en el grupo, no se hace nada
	 */
	public void borraPelota( Pelota pelota ) {
		int posi = buscaPelota( pelota );
		if (posi!=-1) {
			borraPelota( posi );
		}
	}
	
	/** Devuelve una pelota del grupo
	 * @param numPelota	Índice de la pelota a devolver, de 0 a n-1
	 * @return	Pelota correspondiente (la devuelve pero no la quita del grupo)
	 */
	public Pelota getPelota( int numPelota ) {
		return pelotas[numPelota];
	}
	
	/** Busca una pelota en el grupo
	 * @param pelota	Pelota a buscar (mismo objeto)
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaPelota( Pelota pelota ) {
		for (int i=0; i<pelotas.length; i++) {
			if (pelota==pelotas[i]) return i;
		}
		return -1;
	}
	
	// Añadido para poder buscar pelotas iguales siendo distintos objetos
	/** Busca una pelota en el grupo
	 * @param pelota	Pelota a buscar, de acuerdo a sus ATRIBUTOS
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaPelotaEquals( Pelota pelota ) {
		for (int i=0; i<numPelotas; i++) {
			if (pelota.equals(pelotas[i])) return i;
		}
		return -1;
	}
	
	/** Busca un punto en todas las pelotas para ver si está dentro de alguna
	 * @param p	Punto de búsqueda
	 * @return	La pelota dentro de la que está ese punto, o null si no está en ninguna
	 */
	public Pelota buscaPuntoEnPelotas( Point p ) {
		for (int i=0; i<numPelotas; i++) {
			Pelota pelota = pelotas[i];
			if (pelota.contieneA( p )) {
				return pelota;
			}
		}
		return null;
	}
	
	/** Dibuja todas las pelotas del grupo
	 * @param v	Ventana en la que dibujar
	 */
	public void dibuja( VentanaGrafica v ) {
		// for :
		// podría hacer otras cosas
		// for (int i=0; i<numPelotas; i = i + 2)
		for (int i=0; i<numPelotas; i++) {
			pelotas[i].dibuja( v );
		}
	}

	// Devuelve el grupo de pelotas en formato [pelota1|pelota2|...]  (solo devuelve el grupo con el tamaño que tenga)
	@Override
	public String toString() {
		String ret = "[";
		for (int i=0; i<numPelotas; i++) {
			ret += pelotas[i];
			if (i<numPelotas-1) ret += "|";
		}
		return ret + "]";
	}
	
	/* Posible prueba de clase
	public static void main(String[] args) {
		GrupoPelotas g = new GrupoPelotas(2);
		System.out.println( g.anyadePelota( new Pelota() ) );
		System.out.println( g.anyadePelota( new Pelota() ) );
		System.out.println( g.anyadePelota( new Pelota() ) );
		System.out.println( g );
		g.borraPelota(1);
		System.out.println( g );
	}
	*/
}
