package tema1.resueltos.ej11;

import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class GrupoBolas {
	private ArrayList<Bola> lBolas;
	
	/** Crea un nuevo grupo de bolas
	 */
	public GrupoBolas() {
		lBolas = new ArrayList<>();
	}
	
	/** Devuelve una bola del grupo
	 * @param indice	Posición de la bola (de 0 a size-1)
	 * @return	bola de esa posición
	 */
	public Bola get( int indice ) {
		return lBolas.get(indice);
	}
	
	/** Devuelve el número de bolas guardadas
	 * @return	Número de bolas del grupo
	 */
	public int size() {
		return lBolas.size();
	}
	
	/** Añade una bola nueva al grupo
	 * @param bola	Bola a añadir
	 */
	public void add( Bola bola ) {
		lBolas.add( bola );
	}
	
	/** Elimina una bola del grupo
	 * @param indice	Posición de la bola que queremos borrar
	 */
	public void remove( int indice ) {
		lBolas.remove( indice );
	}
	
	/** Busca una bola en el gruop
	 * @param bola	Bola a buscar
	 * @return	Si se encuentra una bola igual a esta (equals), se devuelve su posición de índice. Si no se encuentra, se devuelve -1
	 */
	public int buscar( Bola bola ) {
		return lBolas.indexOf( bola );
	}
	
	/** Devuelve el cálculo de energía del grupo de bolas
	 * @return	Energía sumada de todas las bolas del grupo
	 */
	public double getEnergia() {
		double energia = 0;
		for (Bola bola : lBolas) energia += bola.getEnergia();
		return energia;
	}
	
	/** Borra en la ventana gráfica todas las bolas del grupo (las pinta de blanco)
	 * @param v	Ventana en la que borrar
	 */
	public void borrar( VentanaGrafica v ) {
		for (Bola bola : lBolas) {
			bola.borrar( v );
		}
	}
	
	/** Dibuja todas las bolas del grupo
	 * @param v	Ventana en la que dibujar
	 */
	public void dibujar( VentanaGrafica v ) {
		for (Bola bola : lBolas) {
			bola.dibujar(v);
		}
	}

	/** Mueve todas las bolas del grupo
	 * @param milis	Milisegundos de tiempo a mover
	 */
	public void mover( int milis ) {
		for (Bola bola : lBolas) {
			bola.mover( milis );
		}
	}
	
	/** Calcula los choques con los bordes de todas las bolas y realiza los rebotes si proceden corrigiendo correctamente el punto de rebote
	 * @param v	Ventana en la que comprobar choques con bordes
	 * @param milisEntreFrames	Milisegundos del último movimiento
	 */
	public void choqueBordes( VentanaGrafica v, double milisEntreFrames ) {
		for (Bola bola : lBolas) {
			if (bola.chocaBordeVertical(v) || bola.chocaBordeHorizontal(v)) {
				Fisica.calcChoqueConBorde(v, bola, milisEntreFrames );
			}
		}
	}
	
	/** Calcula los choques entre bolas del grupo y realiza los rebotes entre ellas si proceden
	 * @param v	Ventana en la que comprobar choques
	 * @param milis	Milisegundos del último movimiento
	 */
	public void choqueEntreBolas( VentanaGrafica v, int milis ) {
		for (int i=0; i<lBolas.size(); i++) {
			Bola bola1 = lBolas.get(i);
			for (int j=i+1; j<lBolas.size(); j++) {
				Bola bola2 = lBolas.get(j);
				Fisica.calcChoqueEntreObjetos( v, bola1, bola2, milis*1.0, false );
			}
		}
	}
	
	/** Calcula los choques entre cada bola del grupo y todos los bloques, considerando posibles choques múltiples con varios bloques
	 * @param v	Ventana en la que comprobar choques
	 * @param milis	Milisegundos del último movimiento
	 * @param gBloques	Grupo de bloques a comprobar
	 */
	public void choqueConBloques( VentanaGrafica v, int milis, GrupoBloques gBloques ) {
		for (int i=0; i<lBolas.size(); i++) {
			Bola bola = lBolas.get(i);
			ArrayList<Bloque> lChoques = new ArrayList<>();
			for (int j=0; j<gBloques.size(); j++) {
				Bloque bloque = gBloques.get(j);
				if (bloque.vectorChoqueConObjeto( bola )!=null) {
					lChoques.add( bloque );
				}
			}
			// Resolver choque con bloques múltiples
			Fisica.calcChoqueEntreObjetos( v, bola, lChoques, milis*1.0 );
		}
	}
	
	
	/** Corrige posiciones de las bolas que puedan estar incrustadas en los bordes tras rebotes
	 * @param v	Ventana en la que comprobar choques
	 */
	public void correccionBordes( VentanaGrafica v ) {
		for (Bola bola : lBolas) {
			if (bola.chocaBordeVertical(v)) {
				if (bola.getY()-bola.getRadio() <= 0) {  // Arriba
					bola.setY( bola.getRadio() + 0.00001 );
				} else {  // Abajo
					bola.setY( v.getAltura() - bola.getRadio() - 0.00001 );
				}
			}
			if (bola.chocaBordeHorizontal(v)) {
				if (bola.getX()-bola.getRadio() <= 0) {  // Izquierda
					bola.setX( bola.getRadio() + 0.00001 );
				} else {  // Derecha
					bola.setX( v.getAnchura() - bola.getRadio() - 0.00001 );
				}
			}
		}
	}
	
}
