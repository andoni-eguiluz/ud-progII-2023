package tema2.ejemplos.juegoBolas;

import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase que permite crear un grupo variable de pelotas y dibujarlo en pantalla
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GrupoOJ {
	private ObjetoJuego[] objetosJuego = null;  // Array de pelotas
	private int numObjetoJuegos = 0;    // Número actual de pelotas
	private static final int NUM_MAX_POR_DEFECTO = 10;
	
	/** Crea un grupo de pelotas del tamaño máximo indicado
	 * @param numMax	Número máximo de pelotas que tendrá ese grupo
	 */
	public GrupoOJ( int numMax ) {
		objetosJuego = new ObjetoJuego[numMax];
		numObjetoJuegos = 0;
	}

	/** Crea un grupo de pelotas de tamaño 10
	 */
	public GrupoOJ() {
		objetosJuego = new ObjetoJuego[NUM_MAX_POR_DEFECTO];
	}
	
	/** Devuelve el número de las pelotas actualmente en el grupo
	 * @return	Número de pelotas (entre 0 y núm máximo)
	 */
	public int getNumObjetoJuegos() {
		return numObjetoJuegos;
	}
	
	/** Añade una pelota al grupo
	 * @param pelota	Nueva pelota a añadir
	 * @return	true si el añadido es correcto, false si no cabe (el grupo está lleno)
	 */
	public boolean anyadeObjetoJuego( ObjetoJuego pelota ) {
		if (numObjetoJuegos==objetosJuego.length) {
			return false;
		}
		objetosJuego[numObjetoJuegos] = pelota;
		numObjetoJuegos++;
		return true;
	}
	
	/** Borra una pelota del grupo
	 * @param numObjetoJuego	Índice de la pelota a borrar. Debe estar en el rango 0 a (n-1) siendo n el número de pelotas existentes.
	 */
	public void borraObjetoJuego( int numObjetoJuego ) {
		// Al borrar una pelota se desplazan las siguientes
		// Por ejemplo si borramos p
		// [ 0 | 1 | ... | p |p+1|p+2| ... | n-1 | ... ]
		// Quedarán
		// [ 0 | 1 | ... |p+1|p+2| ... | n-1 | ...     ]
		for (int i=numObjetoJuego+1; i<numObjetoJuegos; i++) {
			objetosJuego[i-1] = objetosJuego[i];
		}
		numObjetoJuegos--;
	}
	
	/** Borra una pelota del grupo
	 * @param pelota	ObjetoJuego a borrar. Si no está en el grupo, no se hace nada
	 */
	public void borraObjetoJuego( ObjetoJuego pelota ) {
		int posi = buscaObjetoJuego( pelota );
		if (posi!=-1) {
			borraObjetoJuego( posi );
		}
	}
	
	/** Devuelve una pelota del grupo
	 * @param numObjetoJuego	Índice de la pelota a devolver, de 0 a n-1
	 * @return	ObjetoJuego correspondiente (la devuelve pero no la quita del grupo)
	 */
	public ObjetoJuego getObjetoJuego( int numObjetoJuego ) {
		return objetosJuego[numObjetoJuego];
	}
	
	/** Busca una pelota en el grupo
	 * @param pelota	ObjetoJuego a buscar (mismo objeto)
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaObjetoJuego( ObjetoJuego pelota ) {
		for (int i=0; i<objetosJuego.length; i++) {
			if (pelota==objetosJuego[i]) return i;
		}
		return -1;
	}
	
	// Añadido para poder buscar pelotas iguales siendo distintos objetos
	/** Busca una pelota en el grupo
	 * @param pelota	ObjetoJuego a buscar, de acuerdo a sus ATRIBUTOS
	 * @return	Posición donde está la pelota, -1 si no se encuentra
	 */
	public int buscaObjetoJuegoEquals( ObjetoJuego pelota ) {
		for (int i=0; i<numObjetoJuegos; i++) {
			if (pelota.equals(objetosJuego[i])) return i;
		}
		return -1;
	}
	
	/** Busca un punto en todas las pelotas para ver si está dentro de alguna
	 * @param p	Punto de búsqueda
	 * @return	La pelota dentro de la que está ese punto, o null si no está en ninguna
	 */
	public ObjetoJuego buscaPuntoEnObjetoJuegos( Point p ) {
		for (int i=0; i<numObjetoJuegos; i++) {
			ObjetoJuego objetoJuego = objetosJuego[i];
			if (objetoJuego.contieneA( p )) {
				return objetoJuego;
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
		// for (int i=0; i<numObjetoJuegos; i = i + 2)
		for (int i=0; i<numObjetoJuegos; i++) {
			objetosJuego[i].dibuja( v );
		}
	}

	// Devuelve el grupo de pelotas en formato [pelota1|pelota2|...]  (solo devuelve el grupo con el tamaño que tenga)
	@Override
	public String toString() {
		String ret = "[";
		for (int i=0; i<numObjetoJuegos; i++) {
			ret += objetosJuego[i];
			if (i<numObjetoJuegos-1) ret += "|";
		}
		return ret + "]";
	}
	
	/* Posible prueba de clase
	public static void main(String[] args) {
		GrupoObjetoJuegos g = new GrupoObjetoJuegos(2);
		System.out.println( g.anyadeObjetoJuego( new ObjetoJuego() ) );
		System.out.println( g.anyadeObjetoJuego( new ObjetoJuego() ) );
		System.out.println( g.anyadeObjetoJuego( new ObjetoJuego() ) );
		System.out.println( g );
		g.borraObjetoJuego(1);
		System.out.println( g );
	}
	*/
}
