package tema3.basicos;

import java.util.*;

/** Clase de ejemplo de uso de Java Collections
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploJC {
	public static void main(String[] args) {
		pruebasJCconStrings();
		pruebasJCconClasesPropias();
	}

	private static void pruebasJCconClasesPropias() {
		// TODO igual que pruebasJCconStrings pero con clase Pelicula que contenga nombre de película
		Pelicula[] peliculas = {
			new Pelicula("The whale"), new Pelicula("Todo a la vez en todas partes"), new Pelicula("Elvis"),
			new Pelicula("Avatar 2"), new Pelicula("Pinocho"),
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Elvis"), new Pelicula("Avatar 2"),
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Elvis"), new Pelicula("Avatar 2"),
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Elvis"), 
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Avatar 2"),
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Avatar 2"),
			new Pelicula("Todo a la vez en todas partes"), new Pelicula("Avatar 2"),
			new Pelicula("Todo a la vez en todas partes"), 
		};
		System.out.println();
		// 1.- Búsqueda en arraylist
		ArrayList<Pelicula> arraylist = new ArrayList<>();
		for (Pelicula p : peliculas) {
			arraylist.add( p );
		}
		System.out.println( "ArrayList: " + arraylist );
		if (arraylist.contains( new Pelicula("Elvis") )) {
			System.out.println( "Elvis está en la lista" );
		} else {
			System.out.println( "Elvis NO está en la lista" );
		}
		// 2.- Inserción en un HashSet (pasaría lo mismo como clave en un HashMap)
		HashSet<Pelicula> s1 = new HashSet<>();
		for (Pelicula p : peliculas) {
			s1.add( p );
		}
		System.out.println( "HashSet: " + s1 );
		System.out.println( (new Pelicula("Elvis")).hashCode() );  // Las tripas del hashset
		System.out.println( (new Pelicula("Elvis")).hashCode() );  // A ver otra vez...??
		if (s1.contains( new Pelicula("Elvis") )) {
			System.out.println( "Elvis está en el set" );
		} else {
			System.out.println( "Elvis NO está en el set");
		}
		// 3.- Inserción en un TreeSet (pasaría lo mismo como clave en un TreeMap)
		TreeSet<Pelicula> s2 = new TreeSet<>();
		for (Pelicula p : peliculas) {
			s2.add( p );
		}
		System.out.println( "TreeSet: " + s2 );
		if (s2.contains( new Pelicula("Elvis") )) {
			System.out.println( "Elvis está en el set" );
		} else {
			System.out.println( "Elvis NO está en el set");
		}
		
		// 4.- Mapas con clave Pelicula (no funcionarán si no funcionan los sets)
		HashMap<Pelicula,Integer> mapa = new HashMap<>();
		for (Pelicula peli : peliculas) {
			if (mapa.containsKey(peli)) {
				mapa.replace( peli, mapa.get(peli) + 1 );
			} else {
				mapa.put( peli, Integer.valueOf(1) );
			}
		}
		System.out.println( "HashMap: " + mapa );
		
		TreeMap<Pelicula,Contador> mapa2 = new TreeMap<>();
		for (Pelicula peli : peliculas) {
			if (!mapa2.containsKey(peli)) {
				mapa2.put( peli, new Contador() );
			} else {
				mapa2.get( peli ).inc();
			}
		}
		System.out.println( "TreeMap: " + mapa2 );
	}
	
	private static void pruebasJCconStrings() {
		String[] peliculas = {
			"The whale", "Todo a la vez en todas partes", "Elvis", "Avatar 2", "Pinocho",
			"Todo a la vez en todas partes", "Elvis", "Avatar 2",
			"Todo a la vez en todas partes", "Elvis", "Avatar 2",
			"Todo a la vez en todas partes", "Elvis", 
			"Todo a la vez en todas partes", "Avatar 2",
			"Todo a la vez en todas partes", "Avatar 2",
			"Todo a la vez en todas partes", "Avatar 2",
			"Todo a la vez en todas partes", 
		};
		
		// ¿Tipos de listas? 2 clases implementan listas:
		// 1.- ArrayList - implementada con un array, sin tamaño fijo, puede crecer, insertar/borrar, con repetición
		//     acceso por INDICE - 0, ... n-1
		ArrayList<String> arraylist = new ArrayList<>();
		for (String p : peliculas) {
			arraylist.add( p );
		}
		System.out.println( arraylist );
		if (arraylist.contains( "Elvis" )) {
			System.out.println( "Elvis está en la lista" );
		}
		
		// 2.- LinkedList. Internamente es una lista enlazada
		LinkedList<String> linkedlist = new LinkedList<>();
		for (String p : peliculas) {
			linkedlist.add( p );
		}
		System.out.println( linkedlist );
		if (linkedlist.contains( "Elvis")) {
			System.out.println( "Elvis está en la lista" );
		}
		
		// Mejor LinkedList cuando solo se inserta/borra en los extremos. En cualquier otro caso, mejor ArrayList.
		
		List<String> lista = arraylist; // No se puede instanciar, es interface new List<>();
		// Hereda de Collection (toda List es una Collection)
		Collection<String> coleccion = linkedlist; // Interfaz de colecciones lineales de elementos: listas y ...

		
		// SETS - colecciones pero no listas
		Set<String> set = null;  // Interfaz
		// Estructura es lineal, pero NO admite repetición, NO TIENE ÍNDICES (no se puede sacar de "posición")
		// Sí se puede recorrer  (también implementa Iterable)

		// Implementación 1: hash (sin orden)
		HashSet<String> s1 = new HashSet<>();
		for (String p : peliculas) {
			s1.add( p );
		}
		System.out.println( s1 );
		System.out.println( "Elvis".hashCode() );  // Las tripas del hashset

		// Implementación 2: tree (con orden natural)
		TreeSet<String> s2 = new TreeSet<>();
		for (String p : peliculas) {
			s2.add( p );
		}
		System.out.println( s2 );
		if (s2.contains( "Elvis")) {
			System.out.println( "Elvis está en la lista" );
		}
		for (String dato : s2) {
			System.out.println( "Dato " + dato );
		}
		
		// MAPAS
		// Operaciones: put para meter / get para coger / containsKey si existe clave
		// put(k,v) / v <- get(k) / containsKey(k)  / remove / replace ...
		// Clave?  (por qué busco)
		// Dato/valor? (qué es lo que hay asociado a la clave)
		// Contar votos -> Clave = nombre de la peli / Valor = Nº de votos
		HashMap<String,Integer> mapa = new HashMap<>();
		// Tarea: contar votos
		for (String peli : peliculas) {
			if (mapa.containsKey(peli)) {
				/* Todo esto hace java...
				Integer numVotos = mapa.get( peli );
				int numVotosInt = numVotos.intValue();  // unbox
				numVotosInt = numVotosInt + 1;
				Integer numVotos2 = Integer.valueOf( numVotosInt );  // box
				// mapa.put( peli, numVotos2 ); igual que el replace
				mapa.replace( peli, numVotos2 );
				*/
				// para esta sentencia:
				mapa.replace( peli, mapa.get(peli) + 1 );  // Java hace automáticamente boxing y unboxing
			} else {
				mapa.put( peli, Integer.valueOf(1) );  // o new Integer(1) aunque es más recomendable el valueOf porque no crea nuevos objetos si ya existían
			}
			System.out.println( "  evolución mapa: " + mapa );
		}
		System.out.println( mapa );
		
		// Rehacer lo mismo con contadores mutables
		// 1.- Crear mapa
		TreeMap<String,Contador> mapa2 = new TreeMap<>();
		// 2.- Procesarlo (generar y recalcular contadores)
		for (String peli : peliculas) {
			if (!mapa2.containsKey(peli)) {
				mapa2.put( peli, new Contador() );
			} else {
				mapa2.get( peli ).inc();
			}
		}
		System.out.println( mapa2 );
		// 3.- Recorrerlo (para buscar máximo)
		int maxVotos = 0;
		String peliMax = "";
		for (String clave : mapa2.keySet()) {
			System.out.println( " Película " + clave + " votos " + mapa2.get(clave) );
			if (maxVotos < mapa2.get(clave).get()) {
				maxVotos = mapa2.get(clave).get();
				peliMax = clave;
			}
		}
		System.out.println( "Ganadora: " + peliMax + " con " + maxVotos );
		
		// Ejercicio: sacar de cada peli los "jueces" que la han votado
		Map<String,ArrayList<Integer>> mapaVotos = new TreeMap<>();
		for (int i=0; i<peliculas.length; i++) {
			String peli = peliculas[i];
			if (!mapaVotos.containsKey(peli)) {
				mapaVotos.put( peli, new ArrayList<>() );
			}
			mapaVotos.get( peli ).add( i );  // Añade el "juez"
		}
		for (String peli : mapaVotos.keySet()) {
			System.out.println( "Película " + peli + " votada por: " + mapaVotos.get(peli) );
		}
	}
}
