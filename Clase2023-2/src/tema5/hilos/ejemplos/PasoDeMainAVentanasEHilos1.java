package tema5.hilos.ejemplos;

import java.util.Scanner;

/** Ejemplo de cómo se puede pasar de programación secuencial a programación orientada a eventos,
 * a veces incluyendo hilos.
 * Esta clase es un ejemplo de 4 pasos en secuencia con interacción de usuario por consola.
 * La clase PasoDeMainAVentanasEHilos2 hace lo mismo con ventanas e hilos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PasoDeMainAVentanasEHilos1 {
	// Ejemplo: cálculo de números primos
	public static void main(String[] args) {
		// Paso 1: pide valor máximo
		// Paso 2: validación de valor
		// Paso 3: se calculan y muestran los primos (supongamos que tardando un tiempo)
		// Paso 4: se pide info de si se vuelve a calcular o se acaba
		boolean seguir = true;
		Scanner leerTeclado = new Scanner( System.in );
		do {
			// Paso 1: pide valor máximo
			System.out.print( "Introduce valor máximo para cálculo de primos: " );
			String resp = leerTeclado.nextLine();
			// Paso 2: validación de valor
			boolean valorCorrecto = false;
			int valorMaximo = 0;
			do {
				try {
					valorMaximo = Integer.parseInt( resp );
					if (valorMaximo>5) {
						valorCorrecto = true;  // boolean para seguir siguiente paso
					} else {
						System.out.println( "Error: valor debe ser mayor de 5" );
					}
				} catch (NumberFormatException e) {
					System.out.println( "Error: valor debe ser numérico entero" );
				}
				if (!valorCorrecto) {
					System.out.print( "Introduce valor máximo correcto para cálculo de primos: " );
					resp = leerTeclado.nextLine();
				}
			} while (!valorCorrecto);
			// Paso 3: se calculan y muestran los primos (supongamos que tardando un tiempo)
			System.out.println( "Primos menores o iguales al indicado:" );
			for (int i=1; i<=valorMaximo; i++) {
				if (esPrimo(i)) {
					System.out.println( "Primo: " + i );
					try {  // Pausa de medio segundo
						Thread.sleep( 500 );
					} catch (InterruptedException e) {}
				}
			}
			// Paso 4: se pide info de si se vuelve a calcular o se acaba
			System.out.print( "Si quieres acabar, escribe FIN. Cualquier otra cosa vuelve a empezar: " );
			resp = leerTeclado.nextLine();
			if (resp.equalsIgnoreCase("FIN")) {
				seguir = false;
			}
		} while (seguir);
		leerTeclado.close();
	}
	
	private static boolean esPrimo( int num ) {
		for (int i=2; i<=num/2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
