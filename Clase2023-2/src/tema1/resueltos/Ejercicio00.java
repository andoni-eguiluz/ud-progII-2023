package tema1.resueltos;

/** Resolución de algunos apartados del ejercicio 1.0.
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Ejercicio00 {

	public static void main(String[] args) {
		ejercicioATablaMultiplicar();  // 1.0.c
		System.out.println();
		ejercicioBCDArrayNombres();  // 1.0.f-g-h
		System.out.println();
		ejercicio1_0_d();
	}

	// Ejercicio 1.0.c
	private static void ejercicioATablaMultiplicar() {
		cabecera();
		for (int i=0; i<10; i++) {
			System.out.print( "Tabla de " + i ); 
			for (int j=0; j<10; j++) {
				String espacios = " ";
				if (i*j<10) espacios = "  ";
				System.out.print( espacios + i*j ); 
			}
			System.out.println();
		}
	}
		// Saca a consola la cabecera de la tabla de multiplicar
		private static void cabecera() {
			System.out.print( "          " );
			for (int j=0; j<10; j++) {
				System.out.print( "  " + j ); 
			}
			System.out.println();
			System.out.println( "           -----------------------------" );
		}

	// Ejercicios 1.0.f. / 1.0.g. / 1.0.h.
	private static void ejercicioBCDArrayNombres() {
		String[] nombres = { "Andoni", "Sonia", "Mateo", "Aitziber", "Alberto", "Marta" };
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
		String nombreMenor = "zzz";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMenor)<0) {
				nombreMenor = nombres[i];
			}
		}
		System.out.println( "Nombre menor alfabético: " + nombreMenor );
		String nombreMayor = "A";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMayor)>0) {
				nombreMayor = nombres[i];
			}
		}
		System.out.println( "Nombre mayor alfabético: " + nombreMayor );
		ordenarNombres( nombres );
		System.out.println( "Lista ordenada:");
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
	}
	
		// Algoritmo de ordenación bubble sort
		private static void ordenarNombres( String[] nombres ) {
			for (int j=1; j<nombres.length; j++) {
				for (int i=0; i<nombres.length-j; i++) {
					if (nombres[i].compareTo(nombres[i+1])>0) {
						String temp = nombres[i];
						nombres[i] = nombres[i+1];
						nombres[i+1] = temp;
					}
				}
			}
		}
	
	// Ejercicio 1.0.d
	private static void ejercicio1_0_d() {
		System.out.println( "Lista de números primos:" );
		for (int i=1; i<=100; i++) {
			if (primo(i)) {
				System.out.println( i );
			}
		}
	}
	private static boolean primo( int num ) {
		boolean esPrimo = true;
		for (int divisor=2; divisor<num; divisor++) {  // Un número es primo si no es divisible por ninguno de 2 al num-1
			if (num % divisor == 0) {
				esPrimo = false;
				break;
			}
		}
		return esPrimo;
	}
	

}
