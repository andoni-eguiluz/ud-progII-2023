package tema2b.basicos;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/** Ejemplo de uso de interfaz comparable para ordenar cualquier array que tenga objetos que implementen ese interfaz
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploComparable {
	public static void main(String[] args) {
		Date[] a1 = new Date[3];
		a1[0] = new GregorianCalendar( 2020,  2, 19 ).getTime();  // Ojo: el mes empieza en 0
		a1[1] = new GregorianCalendar( 2019, 11, 31 ).getTime();
		a1[2] = new GregorianCalendar( 2020,  0, 01 ).getTime();
		Double[] a2 = new Double[5];
		a2[0] = 7.5;
		a2[1] = 12.5;
		a2[2] = 1.3;
		a2[3] = 3.9;
		a2[4] = 4.0;
		System.out.println( "Array 1: " + Arrays.toString( a1 ) );
		System.out.println( "Array 2: " + Arrays.toString( a2 ) );
		ordenaArray( a1 );
		ordenaArray( a2 );
		System.out.println( "Tras ordenar:" );
		System.out.println( "Array 1: " + Arrays.toString( a1 ) );
		System.out.println( "Array 2: " + Arrays.toString( a2 ) );
	}

	// Método de ordenación genérico utilizando un interfaz (Comparable)
	// (tanto Date como Double implementan Comparable)
	private static void ordenaArray( Comparable[] v ) {
		for (int i=0; i<v.length-1; i++) {
			for (int j=1; j<v.length-i; j++) {
				if (v[j-1].compareTo(v[j])>0) {
					Comparable temp = v[j];
					v[j] = v[j-1]; 
					v[j-1] = temp;
				}
			}
		}
	}

}
