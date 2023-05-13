package tema5.hilos.ejemplos.sincronizacion;

import java.util.*;

/** Problema de interacción entre hilos concurrentes sobre la misma estructura de datos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebaInteraccionProblemaHilos {

	private static int tipoProblema = 2; // 1 - string único manipulado por partes  2 - ArrayList de chars
	public static void main(String[] args) {
		Thread hilo1 = new Hilo1();
		Thread hilo2 = new Hilo2();
		hilo1.start();
		hilo2.start();
	}
	
	private static String datoCompartido = "";  // problema 1
	private static ArrayList<Character> listaCompartida = new ArrayList<>(); // problema 2
	
	private static char[] cars = { 'a', 'b', 'c', 'd', 'e', 'f' };
	private static int cont = 0;
	// Mete un carácter en datoCompartido por la derecha
	private static /*synchronized*/ void metecaracter(char car) {
		// System.out.println( "Entra MC");
		if (tipoProblema == 1) {
			String antiguo = datoCompartido + "";
			antiguo = antiguo + car;
			datoCompartido = antiguo;
		} else if (tipoProblema == 2) {
			listaCompartida.add( car );
		}
		// System.out.println( "Sale MC");
	}
	// Saca y visualiza un carácter en datoCompartido por la izquierda
	private static /*synchronized*/ void sacaCaracter() {
		// System.out.println( "Entra SC");
		if (tipoProblema == 1) {
			if (datoCompartido.length()>0) {
				String dato = datoCompartido + "";
				char car = dato.charAt(0);
				System.out.print( car );
				cont++; if (cont==60) { cont = 0; System.out.println(); }
				dato = dato.substring( 1 );
				datoCompartido = dato;
			}
		} else if (tipoProblema == 2) {
			if (!listaCompartida.isEmpty()) {
				char car = listaCompartida.remove( 0 );
				System.out.print( car );
				cont++; if (cont==60) { cont = 0; System.out.println(); }
			}
		}
		// System.out.println( "Sale SC");
	}
	
	// Clase productora de datos
	static class Hilo1 extends Thread {
		public void run() {
			while (true) {
				for (char car : cars) metecaracter( car );
			}
		}
	}

	// Clase consumidora de datos
	static class Hilo2 extends Thread {
		public void run() {
			while (true) {
				sacaCaracter();
			}
		}
	}

}
