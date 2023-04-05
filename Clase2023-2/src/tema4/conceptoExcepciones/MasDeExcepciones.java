package tema4.conceptoExcepciones;

public class MasDeExcepciones {
	public static void main(String[] args) {
		// finally
		// crear una excepción personalizada
		pruebaExc();
		System.out.println( "Final" );
	}
	
	private static void pruebaExc() {
		try {
			calculo( 2, 5 );
			calculo( 5, 2 );
			calculo( 0, 8 );
			calculo( 8, 0 );
		} catch (ArithmeticException e) {
			// Gestión de la excepción
			System.out.println( "Error denominador" );
		} catch (NumeradorCero e) {
			// Gestión de la excepción
			System.out.println( "Error numerador" );
		} finally {
			// Esto se ejecuta siempre
			// - No hay error
			// - Error gestionado
			// - Error no gestionado
			System.out.println( "finally" );
		}
		// Me he recuperado
	}
	
	private static void calculo( int a, int b ) throws NumeradorCero {
		// No debería haber numerador 0
		if (a==0) {
			throw new NumeradorCero();
		}
		int c = a/b;
		System.out.println( c );
	}
}



class NumeradorCero extends Exception {
	public NumeradorCero() {
		super();
	}
	public NumeradorCero( String mens ) {
		super( mens );
	}
}