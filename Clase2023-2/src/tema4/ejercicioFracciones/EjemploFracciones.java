package tema4.ejercicioFracciones;

import java.util.ArrayList;
import java.util.Random;

/**  Ejemplo de gestión de fracciones sin gestión de excepciones
 * Modificarlo para gestionar la excepción de división por cero
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploFracciones {

		private static Random random = new Random();
	// Crea una fracción aleatoria de numerador y denominador entre -10 y 10
	private static Fraccion creaFraccionAleatoria() {
		return new Fraccion( random.nextInt(21)-10, random.nextInt(21)-10 ).simplifica();
	}
	
	// Crea un grupo de fracciones con n fracciones aleatorias
	private static ArrayList<Fraccion> crearGrupoFracciones( int n ) {
		ArrayList<Fraccion> ret = new ArrayList<>();
		for (int i=0; i<n; i++) {
			ret.add( creaFraccionAleatoria() );
		}
		return ret;
	}

	private static void visualizarFracciones( ArrayList<Fraccion> listaFracs ) {
		System.out.print( "Fracciones: { ");
		for (Fraccion f : listaFracs) {
			System.out.print( f + " " );
		}
		System.out.println( "}" );
		System.out.print( "Valores: { ");
		for (Fraccion f : listaFracs) {
			System.out.print( f.calcular() + " " );
		}
		System.out.println( "}" );
	}
	// Suma todas las fracciones indicadas y devuelve la fracción resultado
	private static Fraccion sumaFracciones( ArrayList<Fraccion> listaFracs ) {
		Fraccion res = new Fraccion( 0, 1 );
		for (Fraccion f : listaFracs) {
			res = Fraccion.suma( res, f );
		}
		return res.simplifica();
	}
	// Resta de cero todas las fracciones indicadas y devuelve la fracción resultado
	private static Fraccion restaFracciones( ArrayList<Fraccion> listaFracs ) {
		Fraccion res = new Fraccion( 0, 1 );
		for (Fraccion f : listaFracs) {
			res = Fraccion.resta( res, f );
		}
		return res.simplifica();
	}
	// Multiplica todas las fracciones indicadas y devuelve la fracción resultado
	private static Fraccion multiplicaFracciones( ArrayList<Fraccion> listaFracs ) {
		Fraccion res = new Fraccion( 1, 1 );
		for (Fraccion f : listaFracs) {
			res = Fraccion.multiplica( res, f );
		}
		return res.simplifica();
	}
	// Divide todas las fracciones indicadas y devuelve la fracción resultado
	private static Fraccion divideFracciones( ArrayList<Fraccion> listaFracs ) {
		Fraccion res = listaFracs.get(0);
		for (int i=1; i<listaFracs.size(); i++) {
			Fraccion f = listaFracs.get(i);
			res = Fraccion.divide( res, f );
		}
		return res.simplifica();
	}
	private static void operarConFracciones( ArrayList<Fraccion> listaFracs ) {
		visualizarFracciones( listaFracs );
		System.out.println( "Suma de fracciones = " + sumaFracciones( listaFracs ).toStringConValor() );
		System.out.println( "Resta de fracciones = " + restaFracciones( listaFracs ).toStringConValor() );
		System.out.println( "Multiplicación de fracciones = " + multiplicaFracciones( listaFracs ).toStringConValor() );
		System.out.println( "División de fracciones = " + divideFracciones( listaFracs ).toStringConValor() );
	}
	
	public static void main(String[] args) {
		ArrayList<Fraccion> listaFracs = crearGrupoFracciones( 10 );
		operarConFracciones( listaFracs );
	}

}
