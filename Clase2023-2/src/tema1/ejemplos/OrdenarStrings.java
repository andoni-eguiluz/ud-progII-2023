package tema1.ejemplos;

/** Ejemplo de ordenación de array de strings por método bubble sort
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class OrdenarStrings {

	public static void main( String[] pars ) {
		java.lang.System.out.println( "Inicio de programa" );
		// String[] array1 = new String[5];
		String[] array1 = { "x", "b", "c", "hola", "bb" };  // Llamando al constructor new String[5]
		System.out.println( java.util.Arrays.toString( array1 ) );
		ordenar( array1 );
		// Ejemplo de foreach para recorrer un array
		for (String elto : array1) {
			System.out.print( elto + " " );
		}
	}
	
	public static void ordenar( String[] array ) {
		// Tengo 2 arrays o tengo 1?
		for (int j=0; j<array.length-1; j++) {
			for (int i=0; i<array.length-1; i+=1) { // TODO ¿Se puede optimizar este bucle? (que haga las comparaciones necesarias, no más)
				// System.out.println( "i = " + i );
				// el1.compareTo(el2) -->   comparar el1 con el2 --> si el1< negativo, si el> positivo, si= 0
				if (array[i].compareTo(array[i+1]) > 0) {  // array[i] > array[i+1]
					// System.out.println( "Dos elementos mal ordenados " + i );
					// Intercambiar array[i] con array[i+1]
					String temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
			System.out.println( java.util.Arrays.toString( array ) );
		}
	}
	
}
