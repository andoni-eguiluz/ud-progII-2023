package tema3.basicos;

import java.util.*;

/** Clase de ejemplo de uso de Java Collections
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploJC {
	public static void main(String[] args) {
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
		
		ArrayList<String> l = new ArrayList<>();
		for (String peli : peliculas) {
			l.add( peli );
		}
		System.out.println( l );
		System.out.println( l.get(0) );
		// l.add( 25, "prueba" );
		if (l.contains( "Elvis" )) {
			System.out.println( "Elvis está en la lista" );
		}
		
		LinkedList<String> l2 = new LinkedList<>();
		for (String peli : peliculas) {
			l2.add( peli );
		}
		System.out.println( l2 );
		System.out.println( l2.get(0) );
		// l.add( 25, "prueba" );
		if (l2.contains( "Elvis" )) {
			System.out.println( "Elvis está en la lista" );
		}
		
		List<String> lista = l2; // new List<>();
		Collection<String> coleccion = l2;
		
		HashSet<String> set1 = new HashSet<>();
		for (String peli : peliculas) {
			set1.add( peli );
		}
		System.out.println( set1 );
//		System.out.println( set1.get(0) );
		// l.add( 25, "prueba" );
		if (set1.contains( "Elvis" )) {
			System.out.println( "Elvis está en la lista" );
		}
		System.out.println( "Elvis".hashCode() );
		System.out.println( "a".hashCode() );

		TreeSet<String> set2 = new TreeSet<>();
		for (String peli : peliculas) {
			set2.add( peli );
		}
		System.out.println( set2 );
		if (set2.contains( "Elvis" )) {
			System.out.println( "Elvis está en la lista" );
		}
		for (String valor : set2) {
			System.out.println( "Elemento " + valor );
		}
		
		// Mapa: contemos películas
		// put(k,v) / v <- get(k) / containsKey(k)  / remove / replace ...
		// 1.- Definir mapa
		HashMap<String,Integer> mapa1 = new HashMap<>();
		// 2.- Cargar datos en el mapa
		for (String peli : peliculas) {
			if (mapa1.containsKey(peli)) {
				// int votaciones = mapa1.get( peli );  // unboxing
				// mapa1.replace( peli, votaciones+1 );  // boxing
				mapa1.replace( peli, mapa1.get(peli) + 1 );
			} else {
				mapa1.put( peli, 1 );  // boxing automático
				// mapa1.put( peli, new Integer(1) );
			}
		}
		System.out.println( mapa1 );
		// 3.- Recorrer mapa para buscar ganadora
		int maxVotos = 0;
		String nomPeliMax = "";
		for (String clave : mapa1.keySet()) {
			if (mapa1.get(clave) > maxVotos) {
				nomPeliMax = clave;
				maxVotos = mapa1.get(clave);
			}
		}
		System.out.println( "Gana " + nomPeliMax + " con " + maxVotos );

		// Mapa: contemos películas
		// put(k,v) / v <- get(k) / containsKey(k)  / remove / replace ...
		// 1.- Definir mapa
		TreeMap<String,Contador> mapa2 = new TreeMap<>();
		// 2.- Cargar datos en el mapa
		for (String peli : peliculas) {
			if (mapa2.containsKey(peli)) {
				mapa2.get(peli).inc();
			} else {
				mapa2.put( peli, new Contador() );  // boxing automático
				// mapa1.put( peli, new Integer(1) );
			}
		}
		System.out.println( mapa2 );
		
		// Ejercicios:
		// 1.- Sacar de cada peli la lista de votantes (cogiendo índice de cada voto: 0, 1, 2, 3...) 
		// Por ejemplo a "Elvis" la han votado la lista de votantes (0, 6, 9, 12)
		HashMap<String,ArrayList<Integer>> mapaVotantes; // etc.

		// 2.- Hacer todo lo mismo que hemos probado en este ejemplo
		// con una clase Pelicula en lugar de con Strings
		
	}
}
