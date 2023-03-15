package tema2.basicos;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/** Ejemplo de clases envoltorio (wrappers)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploWrappers {
	
	public static void main(String[] args) {
		// Object polimorfismo total
		Object o = new String("A");  // "A"
		System.out.println( o.toString() );
		o = new Point(2,5);
		System.out.println( o.toString() );
		o = new Color( 10, 5, 3 );
		System.out.println( o );
		
		// Contenedores de Objects, polimorfismo total
		Object[] ao = new Object[5];
		ao[0] = "A";
		ao[1] = new Point(1,4);
		
		ArrayList<Object> alo = new ArrayList<>();
		alo.add( "A" );
		alo.add( new Point(1,4) );
		
		// Object guarda de todo
		// ¿Y los primitivos?
		// Con wrappers
		
		alo.add( new Integer(5) );
		alo.add( new Double(3.0) );
		alo.add( new Boolean(false) );
		alo.add( new Character('a') );
		
		o = new Integer(5);
		ao[1] = new Integer(5);
		
		System.out.println( ao );
		System.out.println( alo );
		System.out.println( ao[1] );
		
		int total = 0;
		// Se puede recorrer 
		for (Object object : alo) {
			// Y si queremos especializar, elegimos por tipo
			if (object instanceof Integer) {
				total += ((Integer)object).intValue();
			}
			System.out.println( object.toString() );
		}
		System.out.println( total );
		
		float f = 5.7f;
		int i2 = Math.round(f);
		
		// Ayuditas
		// Unboxing automático - conversión automática de wrapper al prim
		i2 = ((Integer)ao[1]).intValue(); // sacar el int del Integer
		i2 = ((Integer)ao[1]); //.intValue() automático
		
		Integer objetoInt = new Integer(7);
		int i3 = i2 + objetoInt; //.intValue() unboxing automático
		Integer i4 = i3; // new Integer(i3);  boxing automático
		System.out.println( i4 );
		
		alo.add( 7 );  // new Integer(7) boxing automático
		alo.add( 8.5 );
		
		System.out.println( alo );
		for (Object o2 : alo) {
			System.out.println( o2.getClass() );
		}
		
		// Wrappers - qué más?
		// Algunas constantes
		System.out.println( Integer.MAX_VALUE );
		// Conversores
		String dato = "17";
		int otroi = Integer.parseInt( dato );
		System.out.println( otroi );
	}
	
}
