package tema5.ejemplos.clasesInternas;

import java.util.Random;

/** Ejemplo de clase externa con clases interna y anónima  (ver PruebaClaseInterna - contiene el main)
 * @author andoni.eguiluz at deusto.es
 */
public class ClaseExterna {
	private int ce;  // Atributo clase externa
	public int getDatoExterno() { return ce; }
	public void setDatoExterno( int ce ) { this.ce = ce; }
	@Override
	public String toString() {
		return "CE - dato " + ce;
	}
	
	// CLASE INTERNA!!!  (ojo no tiene nada que ver con la herencia aunque se pueden "usar" los atributos y los métodos)
	public class ClaseInterna {
		private int ci; // Atributo clase interna
		public int getDatoInterno() { return ci; }
		public void setDatoInterno( int ci ) { this.ci = ci; }
		@Override
		public String toString() { 
			return "CI - dato interno " + ci + " / dato externo = " + ce;  // Hay acceso a los dos datos, internos y externos!
		}
	}
	
	// Método que crea una instancia de clase interna anónima
	public void creaClaseAnonima() {
		
		// Caso 1: heredando una clase. Por ejemplo 
		Random oAnonimo1 = new Random() { // El constructor puede tener parámetros si existe
			// NO puede definirse constructor (no hay nombre con el que definirlo)
			@Override  // Por ejemplo redefino un método de Random en su clase hija
			public int nextInt(int bound) {  // Calcula un aleatorio de 0 al bound INCLUSIVE (normalmente es exclusive)
				return super.nextInt(bound+1);
			}
		};
		System.out.println( "Random 0 a 1 = " + oAnonimo1.nextInt(1) ); // Calcula un random de 0 a 1

		// Caso 2: implementando un interfaz. Por ejemplo
		Runnable oAnonimo2 = new Runnable() { // El constructor al ser interfaz no puede tener parámetros
			// NO puede definirse constructor (no hay nombre con el que definirlo)
			public void run() {
				System.out.println( "Hago algo" );
			}
		};
		oAnonimo2.run();
		
		// En cualquiera de los casos pueden utilizarse atributos de la clase externa y de la interna...
		// pero NO variables locales (excepto final)  (¿por qué?)
		// (OBSERVA EL FLUJO DE CONTROL DE ESTE MÉTODO COMPLETO!)
		int varLocal = 0;
		final int varLocal2 = 5;
		varLocal += 2;
		Runnable oAnonimo3 = new Runnable() {
			int at = 2; // Atributo interno
			public void run() {
				System.out.println( "Atributo externo = " + ce );
				System.out.println( "Atributo interno = " + at );
				// No se puede acceder a varLocal  (prueba a descomentar esta línea)
				// System.out.println( "Variable local = " + varLocal );
				// Pero sí a varLocal2
				System.out.println( "Variable local (final) = " + varLocal2 );
			}
		};
		varLocal += 2;
		oAnonimo3.run(); 
			// Qué valor saldría si varLocal? Y cuál si se pudiera usar FUERA de este ámbito?
			// Qué valor sale de varLocal2? ¿Por qué tiene que ser final?
	}
}
