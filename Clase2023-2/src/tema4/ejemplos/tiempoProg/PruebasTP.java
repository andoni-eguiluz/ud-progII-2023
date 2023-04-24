package tema4.ejemplos.tiempoProg;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/** Pruebas de las clases TiempoProg y GestorTiemposProg
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebasTP {
	public static void main(String[] args) {
		Date ahora = new Date();
		TiempoProg tp = new TiempoProg( ahora, 10 );
		TiempoProg tp2 = new TiempoProg( ahora, 10 );
		System.out.println( tp.hashCode() );
		System.out.println( tp2.hashCode() );
		System.out.println( tp.equals( tp2 ) );
		HashSet<TiempoProg> set = new HashSet<>();
		set.add( tp );
		set.add( tp2 );
		System.out.println( set );
		HashMap<TiempoProg, String> mapa;
		TiempoProg tp3 = new TiempoProg( ahora, 5 );
		TreeSet<TiempoProg> tset = new TreeSet<>();
		tset.add( tp );
		tset.add( tp2 );
		tset.add( tp3 );
		System.out.println( tset );
		
		// Pruebas de unos pocos datos y guardarlos en fichero
		GestorTiemposProg gestor = new GestorTiemposProg();
		TiempoProg tp1 = new TiempoProg( new Date(), 100 );
		gestor.addTiempo( tp1 );
		TiempoProg tp2b = new TiempoProg( new Date(), 25 );
		gestor.addTiempo( tp2b );
		try {
			gestor.guardarFichero( true, "tiempos.dat" );
			System.out.println( "Fichero guardado" );
			System.out.println( "Datos guardados: " + gestor.getListaTiempos() );
			gestor.addTiempo( null ); // Cambiamos lo datos a ver si al cargar se restauran
			System.out.println( "Datos cambiados: " + gestor.getListaTiempos() );
			gestor.cargarFichero( true, "tiempos.dat" );
			System.out.println( "Fichero cargado" );
			System.out.println( "Datos restaurados: " + gestor.getListaTiempos() );
			System.out.println( gestor.getListaTiempos() );
			
			gestor.guardarFichero( false, "tiempos.txt" );
			gestor.guardarFichero( false, "tiempos.txt" );
			// ...
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println( "Ha habido un error en el guardado de fichero" );
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "Ha habido un error indeterminado" );
			
		}
	}
}
