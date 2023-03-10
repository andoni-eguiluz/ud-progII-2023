package tema1.resueltos.ej11;

import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class GrupoBloques {
	private ArrayList<Bloque> lBloques;
	
	/** Crea un nuevo grupo de bloques
	 */
	public GrupoBloques() {
		lBloques = new ArrayList<>();
	}
	
	/** Devuelve un bloque del grupo
	 * @param indice	Posición del bloque (de 0 a size-1)
	 * @return	bloque de esa posición
	 */
	public Bloque get( int indice ) {
		return lBloques.get(indice);
	}
	
	/** Devuelve el número de bloques guardados
	 * @return	Número de bloques del grupo
	 */
	public int size() {
		return lBloques.size();
	}
	
	/** Añade un bloque nuevo al grupo
	 * @param bola	Bloque a añadir
	 */
	public void add( Bloque bloque ) {
		lBloques.add( bloque );
	}
	
	/** Elimina un bloque del grupo
	 * @param indice	Posición del bloque que queremos borrar
	 */
	public void remove( int indice ) {
		lBloques.remove( indice );
	}
	
	/** Busca un bloque en el gruop
	 * @param bloque	Bloque a buscar
	 * @return	Si se encuentra un bloque igual a este (equals), se devuelve su posición de índice. Si no se encuentra, se devuelve -1
	 */
	public int buscar( Bloque bloque ) {
		return lBloques.indexOf( bloque );
	}
	
	/** Devuelve el cálculo de energía del grupo de bloques
	 * @return	Energía sumada de todos los bloques del grupo
	 */
	public double getEnergia() {
		double energia = 0;
		for (Bloque bloque : lBloques) energia += bloque.getEnergia();
		return energia;
	}
	
	/** Borra en la ventana gráfica todos los bloques del grupo (las pinta de blanco)
	 * @param v	Ventana en la que borrar
	 */
	public void borrar( VentanaGrafica v ) {
		for (Bloque bloque : lBloques) {
			bloque.borrar( v );
		}
	}
	
	/** Dibuja todos los bloques del grupo
	 * @param v	Ventana en la que dibujar
	 */
	public void dibujar( VentanaGrafica v ) {
		for (Bloque bloque : lBloques) {
			bloque.dibujar(v);
		}
	}

	/** Mueve todos los bloques del grupo
	 * @param milis	Milisegundos de tiempo a mover
	 */
	public void mover( int milis ) {
		for (Bloque bloque : lBloques) {
			bloque.mover( milis );
		}
	}
	
	/** Calcula los choques con los bordes de todos los bloques y realiza los rebotes si proceden
	 * @param v	Ventana en la que comprobar choques con bordes
	 * @param milisEntreFrames	Milisegundos del último movimiento
	 */
	public void choqueBordes( VentanaGrafica v, double milisEntreFrames ) {
		for (Bloque bloque : lBloques) {
			if (bloque.chocaBordeHorizontal(v) || bloque.chocaBordeVertical(v)) {
				Fisica.calcChoqueConBorde(v, bloque, milisEntreFrames );
			}
		}
	}
	
	/** Calcula los choques entre bloques del grupo y realiza los rebotes entre ellos si proceden
	 * @param v	Ventana en la que comprobar choques
	 * @param milis	Milisegundos del último movimiento
	 */
	public void choqueEntreBloques( VentanaGrafica v, int milis ) {
		for (int i=0; i<lBloques.size(); i++) {
			Bloque bloque1 = lBloques.get(i);
			for (int j=i+1; j<lBloques.size(); j++) {
				Bloque bloque2 = lBloques.get(j);
				Fisica.calcChoqueEntreObjetos( v, bloque1, bloque2, milis*1.0 );
			}
		}
	}
	
	/** Calcula los choques entre bloques del grupo y todas las bolas
	 * @param v	Ventana en la que comprobar choques
	 * @param milis	Milisegundos del último movimiento
	 * @param gBolas	Grupo de bolas a comprobar
	 */
	public void choqueConBolas( VentanaGrafica v, int milis, GrupoBolas gBolas ) {
		for (int i=0; i<lBloques.size(); i++) {
			Bloque bloque = lBloques.get(i);
			for (int j=0; j<gBolas.size(); j++) {
				Bola bola = gBolas.get(j);
				Fisica.calcChoqueEntreObjetos( v, bloque, bola, milis*1.0 );
			}
		}
	}
	
	/** Corrige posiciones de los bloques que puedan estar incrustados en los bordes tras rebotes
	 * @param v	Ventana en la que comprobar choques
	 */
	public void correccionBordes( VentanaGrafica v ) {
		for (Bloque bloque : lBloques) {
			if (bloque.chocaBordeVertical(v)) {
				if (bloque.getY()-bloque.getAlto()/2 <= 0) {  // Arriba
					bloque.setY( bloque.getAlto()/2 + 0.00001 );
				} else {  // Abajo
					bloque.setY( v.getAltura() - bloque.getAlto()/2 - 0.00001 );
				}
			}
			if (bloque.chocaBordeHorizontal(v)) {
				if (bloque.getX()-bloque.getAncho()/2 <= 0) {  // Izquierda
					bloque.setX( bloque.getAncho()/2 + 0.00001 );
				} else {  // Derecha
					bloque.setX( v.getAnchura() - bloque.getAncho()/2 - 0.00001 );
				}
			}
		}
	}
	
}
