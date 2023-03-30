package tema3.javaCollections.sacaEquipos;

import java.util.*;

/** Clase de ejemplo de sacar equipos de liga de fútbol desde una web (marca).
 * Utilizado para practicar con Java Collections
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class SacaEquiposResuelto {

	public static void main(String[] args) {
		// Saca el código html del calendario de liga que coincide con una parte de los nombres de equipos
		ArrayList<String> l = ProcesaURLs.buscaEnWeb( "https://www.marca.com/futbol/primera-division/calendario.html", 
				"<img src=\"https://e00-marca.uecdn.es/assets/", 
				"iso-8859-15" );
		procesa( l );
		cierre();
	}

	private static ArrayList<String> l1 = new ArrayList<String>();
	private static ArrayList<String> l2 = new ArrayList<String>();
	private static HashSet<String> s3 = new HashSet<String>();
	private static TreeSet<String> s4 = new TreeSet<String>();
	private static HashSet<Equipo> s3b = new HashSet<Equipo>();
	private static TreeSet<Equipo> s4b = new TreeSet<Equipo>();
	private static HashMap<String,Integer> m5 = new HashMap<>();
	private static HashMap<String,Entero> m6 = new HashMap<>();
	private static TreeMap<String,ArrayList<String>> m7 = new TreeMap<>();
	private static HashMap<String,ArrayList<Partido>> m8 = new HashMap<>();

	private static String primerEquipoTarea7 = null;  // Usado para ver los pares de equipos (tarea 7)
	private static String primerEquipoTarea8 = null;  // Usado para ver los pares de equipos (para tarea 8)
	
	// Método para hacer pruebas de Java Collections con los equipos de la liga de fútbol
	private static void pruebasDeJC( String equipo ) {
		// System.out.println( equipo );
		// Resolver los siguientes puntos
		// 1.- Meter todos los equipos tal cual aparecen en una lista y visualizarla
		l1.add( equipo );
		// 2.- Meter todos los equipos tal cual aparecen en una lista SOLO si no estaban ya en ella. Visualizarla
		if (!l2.contains(equipo)) {
			l2.add( equipo );
		}
		// 3.- Meter todos los equipos en un conjunto SIN ORDEN. Visualizarlos
		s3.add( equipo );
		// 3b.- Lo mismo en lugar de con string con una clase Equipo (que contenga el nombre). hashCode + equals
		Equipo eq = new Equipo( equipo );
		// System.out.println( eq + " - " + eq.hashCode() );
		s3b.add( eq );
		// 4.- Meter todos los equipos en un conjunto CON ORDEN (alfabético). Visualizarlos
		s4.add( equipo );
		// 4b.- Lo mismo ordenando AL REVÉS en lugar de con string con una clase Equipo (que contenga el nombre). comparable
		eq = new Equipo( equipo );
		s4b.add( eq );
		// 5.- Usar un mapa para contar el número de veces que aparece cada equipo (Integer)
		if (m5.containsKey(equipo)) {  // Ya había aparecido - hay que incrementar el conteo
			int contAntiguo = m5.get( equipo ).intValue();
			m5.put( equipo, new Integer( contAntiguo + 1 ) );
		} else {  // Primera vez que aparece
			m5.put( equipo, new Integer( 1 ) );
		}
		// 6.- Usar un mapa para contar el número de veces que aparece cada equipo (Entero MUTABLE)
		if (m6.containsKey(equipo)) {  // Ya había aparecido - hay que incrementar el conteo
			m6.get( equipo ).inc();  // Obsérvese como ahora directamente se puede modificar el valor
		} else {  // Primera vez que aparece
			m6.put( equipo, new Entero(1) );
		}
		// 7.- Usar un mapa para sacar una lista de todos los enfrentamientos de cada equipo
		if (primerEquipoTarea7==null) {  // Es el primer equipo del partido
			primerEquipoTarea7 = equipo; 
			// Esperamos a que llegue el segundo
		} else {  // Es el segundo equipo del partido: primerEquipo vs equipo
			// Añadimos el equipo 2 a la lista del primer equipo
			if (m7.containsKey(primerEquipoTarea7)) {
				m7.get( primerEquipoTarea7 ).add( equipo );
			} else {
				m7.put( primerEquipoTarea7, new ArrayList<String>( Arrays.asList( equipo ) ) );
			}
			
			// De otra manera sería así:
			// ArrayList<String> lPartidos = m7.get(primerEquipo);
			// if (lPartidos==null) {
			//	   lPartidos = new ArrayList<String>();
			//	   m7.put(primerEquipo, lPartidos);
			// }
			// lPartidos.add( equipo );
			// Lo mismo m7.get(primerEquipo).add( equipo );
			
			// Añadimos el equipo 1 a la lista del segundo equipo
			if (m7.containsKey(equipo)) {
				m7.get( equipo ).add( primerEquipoTarea7 );
			} else {
				m7.put( equipo, new ArrayList<String>( Arrays.asList( primerEquipoTarea7 ) ) );
			}
			primerEquipoTarea7 = null;  // Y reiniciamos el primer equipo para el siguiente partido
		}
		// 8.- Usar un mapa para sacar una lista de todos los enfrentamientos de cada equipo (con objeto Partido)
		if (primerEquipoTarea8==null) {  // Es el primer equipo del partido
			primerEquipoTarea8 = equipo; 
			// Esperamos a que llegue el segundo
		} else {  // Es el segundo equipo del partido: primerEquipo vs equipo
			Partido partido = new Partido( primerEquipoTarea8, equipo );
			// Añadimos el partido a la lista del primer equipo
			if (m8.containsKey(primerEquipoTarea8)) {
				m8.get( primerEquipoTarea8 ).add( partido );
			} else {
				m8.put( primerEquipoTarea8, new ArrayList<Partido>( Arrays.asList( partido ) ) );
			}
			// Añadimos el partido a la lista del segundo equipo
			if (m8.containsKey(equipo)) {
				m8.get( equipo ).add( partido );
			} else {
				m8.put( equipo, new ArrayList<Partido>( Arrays.asList( partido ) ) );
			}
			primerEquipoTarea8 = null;  // Y reiniciamos el primer equipo para el siguiente partido
		}
	}
	
	private static void cierre() {
		System.out.println( l1.size() + " - " + l1 );
		System.out.println( l2.size() + " - " + l2 );
		System.out.println( "S3: " + s3.size() + " - " + s3 );
		System.out.println( "S3b: " + s3b.size() + " - " + s3b );
		System.out.println( "S4: " + s4.size() + " - " + s4 );
		System.out.println( "S4b: " + s4b.size() + " - " + s4b );
		System.out.println( m5.size() + " - " + m5 );
		System.out.println( m6.size() + " - " + m6 );
		// Los mapas 7 y 8 los vamos a sacar por partes:
		System.out.println( "Mapa de enfrentamientos:" );
		for (String equipo : m7.keySet()) {
			System.out.println( "  Partidos de " + equipo + " (" + m7.get(equipo).size() + "): " + m7.get(equipo) );
		}
		System.out.println( "Mapa de partidos:" );
		for (String equipo : m8.keySet()) {
			System.out.println( "  Partidos de " + equipo + " (" + m8.get(equipo).size() + "): " + m8.get(equipo) );
		}
	}
	
	private static void procesa( ArrayList<String> lHtml ) {
		System.out.println( "Html encontrado:" );
		for (String html : lHtml) {
//			System.out.println( html );
		}
		// Proceso de cada nombre de equipo
		System.out.println();
		System.out.println( "Equipos encontrados:" );
		for (String html : lHtml) {
			String equipo = sacaEquipoDeHtml( html );
//			System.out.println( equipo );
			pruebasDeJC( equipo );
		}
		
	}
	
	private static String sacaEquipoDeHtml( String html ) {
		// Buscar esta parte: alt="Barcelona"/>   (o cualquier otro equipo)
		int posi1 = html.indexOf( "alt=\"" );
		int posi2 = html.indexOf( "\"", posi1+5 );
		// System.out.println( posi1 + " - " + posi2 );
		String equipo = html.substring( posi1+5, posi2 );
		return equipo;
	}

}
