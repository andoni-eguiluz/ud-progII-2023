package tema3.basicos;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;
import java.util.*;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de exploración de Java Collections
 * Tiempo de ejecución de las estructuras al tener tamaño MUY GRANDE
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ExploracionJC {

	private static final int TAMANYO = 700_000; 
	private static final int VECES = 1_000;  // Para repetir muchas veces un proceso para poder medir diferencias 
	private static final Font FONT = new Font( "Arial", Font.PLAIN, 24 ); 
	private static final Font FONT_INDS = new Font( "Arial", Font.PLAIN, 14 ); 
	private static long time;  // Para medición de tiempo
	
	public static void main(String[] args) {
		visualHash();
		pruebasTiemposJC();
	}
	
	private static void visualHash() {
		VentanaGrafica vent = new VentanaGrafica( 1200, 600, "Visualización de hashsets" );
		vent.setDibujadoInmediato( false );
		HashSet<String> hashset = new HashSet<>();
		vent.setMensaje( "Pulsa tecla para avanzar" );
		for (int i=0; i<250; i++) {
			if (vent.estaCerrada()) {
				break;
			}
			hashset.add( "val" + i + Math.random() );  // String aleatorio siempre distinto
			if (i < 50) {  // A partir de 50 ya va en automático
				dibujaEstructuraHash( vent, hashset, true );
				vent.espera( 250 );
				while (vent.getCodTeclaQueEstaPulsada()==0) {
					vent.espera( 10 );
				}
			} else {
				vent.setMensaje( " " );
				dibujaEstructuraHash( vent, hashset, false );
				vent.espera( 100 );
			}
		}
		vent.acaba();
	}
		// Método de dibujado simbólico de una estructura de tabla hash de una variable hashset
		// Utiliza reflectividad (el hashset no expone sus atributos, hay que hacer "trampitas" para acceder a ellos)
		private static void dibujaEstructuraHash( VentanaGrafica vent, HashSet<String> hashset, boolean dibujaEstructura ) {
			vent.borra();
			int y = 200;
			int xInicio = 10;
			int xFin = vent.getAnchura() - 10;
			try {
				// Código de acceso interno particular a la estructura de un HashSet
				Field field = hashset.getClass().getDeclaredField("map");
				field.setAccessible( true );
				Object mapa = field.get( hashset );
				Field field2 = mapa.getClass().getDeclaredField("table");
				field2.setAccessible( true );
				Object[] arrayNodosHash = (Object[]) field2.get( mapa );
				int tamanyoHashtable = arrayNodosHash.length;
				double anchoCasilla = 1.0 * (xFin-xInicio) / tamanyoHashtable;
				vent.dibujaTextoCentrado( 5, 5, vent.getAnchura(), 30, "Hash con " + hashset.size() + " elementos, tamaño de tabla " + tamanyoHashtable , FONT, Color.BLACK );
				if (dibujaEstructura) {
					vent.dibujaLinea( xInicio, y-anchoCasilla, xFin, y-anchoCasilla, 1f, Color.GRAY );
					for (int i=0; i<tamanyoHashtable; i++) {
						vent.dibujaTextoCentrado( xInicio + i*anchoCasilla, y-anchoCasilla-30, anchoCasilla, 30, "" + i, FONT_INDS, Color.BLACK );
						vent.dibujaLinea( xInicio + i*anchoCasilla, y-anchoCasilla, xInicio + i*anchoCasilla, y+anchoCasilla, 1f, Color.GRAY );
					}
					vent.dibujaLinea( xInicio + tamanyoHashtable*anchoCasilla, y-anchoCasilla, xInicio + tamanyoHashtable*anchoCasilla, y+anchoCasilla, 1f, Color.GRAY );
				}
				for (int i=0; i<tamanyoHashtable; i++) {
					// System.out.print( " [" + i + "] " + arrayNodosHash[i] );
					int elementos = 0;
					if (arrayNodosHash[i]!=null) {
						Object nodo = arrayNodosHash[i];
						do {
							elementos++;
							Field field3 = nodo.getClass().getDeclaredField("next");
							field3.setAccessible( true );
							nodo = field3.get( nodo );
						} while (nodo!=null);
						// System.out.println( "  colisiones: " + (elementos-1) );
					} else {
						// System.out.println();
					}
					// La posición i tiene "elementos" elementos
					if (elementos > 0) {
						double radio = anchoCasilla/2;
						double saltoY = radio*1.5;
						if (radio < 1) radio = 1;
						double xCentro = xInicio + anchoCasilla*i + anchoCasilla/2;
						vent.dibujaCirculo( xCentro, y, radio, 1f, Color.BLUE );
						for (int j=1; j<elementos; j++) {
							vent.dibujaCirculo( xCentro, y + saltoY*j, radio, 1f, Color.RED );
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vent.repaint();
		}

	private static void pruebasTiemposJC() {
		
		initTiempo();
		ArrayList<String> arraylist = new ArrayList<>();
		for (int i=0; i<TAMANYO; i++) {
			arraylist.add( "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings diferentes en un arraylist" );
		
		initTiempo();
		LinkedList<String> linkedlist = new LinkedList<>();
		for (int i=0; i<TAMANYO; i++) {
			linkedlist.add( "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings diferentes en un linkedlist" );

		initTiempo();
		HashSet<String> hashset = new HashSet<>();
		for (int i=0; i<TAMANYO; i++) {
			hashset.add( "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings diferentes en un hashset" );
		
		initTiempo();
		TreeSet<String> treeset = new TreeSet<>();
		for (int i=0; i<TAMANYO; i++) {
			treeset.add( "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings diferentes en un treeset" );
		
		initTiempo();
		HashSet<String> hashset2 = new HashSet<>();
		for (int i=0; i<TAMANYO; i++) {
			hashset2.add( "Dato" + (i%100) );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings -solo 100 diferentes- en un hashset" );
		
		initTiempo();
		TreeSet<String> treeset2 = new TreeSet<>();
		for (int i=0; i<TAMANYO; i++) {
			treeset2.add( "Dato" + (i%100) );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings -solo 100 diferentes- en un treeset" );

		System.out.println();
		initTiempo();
		arraylist = new ArrayList<>();
		for (int i=0; i<TAMANYO; i++) {
			arraylist.add( 0, "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings en un arraylist insertando por el inicio" );
		
		initTiempo();
		linkedlist = new LinkedList<>();
		for (int i=0; i<TAMANYO; i++) {
			linkedlist.add( 0, "Dato" + i );
		}
		sacaTiempo( "Cargar " + TAMANYO + " strings en un linkedlist insertando por el inicio" );

		System.out.println();
		initTiempo();
		boolean busq = false;
		for (int i=0; i<VECES; i++) {
			busq = busq || arraylist.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un arraylist de tamaño " + TAMANYO + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq || linkedlist.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un linkedlist de tamaño " + TAMANYO + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq || hashset.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un hashset de tamaño " + hashset.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq || treeset.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un treeset de tamaño " + treeset.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq || hashset2.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un hashset de tamaño " + hashset2.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq || treeset2.contains( "Dato no existente" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string no existente en un treeset de tamaño " + treeset2.size() + "? " + busq );
		
		System.out.println();
		int numDatoMedio = TAMANYO/2;
		initTiempo();
		busq = true;
		for (int i=0; i<VECES; i++) {
			busq = busq && arraylist.contains( "Dato" + numDatoMedio );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un arraylist de tamaño " + TAMANYO + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq && linkedlist.contains( "Dato" + numDatoMedio );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un linkedlist de tamaño " + TAMANYO + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq && hashset.contains( "Dato1" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un hashset de tamaño " + hashset.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq && treeset.contains( "Dato1" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un treeset de tamaño " + treeset.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq && hashset2.contains( "Dato1" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un hashset de tamaño " + hashset2.size() + "? " + busq );
		
		initTiempo();
		for (int i=0; i<VECES; i++) {
			busq = busq && treeset2.contains( "Dato1" );
		}
		sacaTiempo( "Buscar " + VECES + " veces un string existente en un treeset de tamaño " + treeset2.size() + "? " + busq );
	}
	
	private static void initTiempo() {
		time = System.nanoTime();
	}
	
	private static void sacaTiempo( String descripcion ) {
		System.out.println( String.format( "Tiempo empleado %s: %,1d nanosegundos.", descripcion, (System.nanoTime() - time) ) );
	}
}
