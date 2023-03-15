package tema2.basicos;

import java.util.ArrayList;

/** Consideraciones de genericidad por herencia/interfaces
 * en el caso de contenedores: ojo si los tipos no son el mismo!!!
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ConsideracionContenedoresPolimorficos {

	// Jerarquía de herencia: A clase padre. B hija de A, C hija de A.
	
	public static void main(String[] args) {
		polimorfismoDeVariables();
		arraysPolimorficos();
		arrayListsPolimorficos();
	}
	
	private static void polimorfismoDeVariables() {
		pv2( new A() );  // Al método pv2 le puedo pasar una A, con lo cual tanto puedo pasar una A
		pv2( new B() );  // Como un B
		pv2( new C() );  // Como un C
		// Y no hay problemas 
	}
	
		private static void pv2( A a ) {
			// Como lo que se recibe es un A, siempre lo puedo tratar como un A
			a.sacaA();
		}

	// El array genérico puede dar problemas: cuidadín
	private static void arraysPolimorficos() {
		A[] aes = new A[2];
		aes[0] = new B();  // Ok (un B es un A)
		aes[1] = new C();  // Ok (un C es un A)
		B[] bs = new B[2]; // Pero en un array más especializado (array de Bs)
		aes = bs; // Si hago un aliasing peligroso
		aes[1] = new C();  // Error de ejecución (no puedo meter un C en un array de Bs)
		aP2( bs );  // El aliasing a veces no es tan claro, por ejemplo una llamada (esta) hace un aliasing!
	}
	
		// Este método es potencialmente peligroso si se quiere MODIFICAR el contenedor (acceso no es problemático) 
		private static void aP2( A[] arrayGenerico ) {
			// Simplemente de ejemplo
			arrayGenerico[1] = new C();  // Error de ejecución si lo que me pasan no es un array de A (no puedo meter un C en un array de Bs)
		}
	
	// El ArrayList genérico está preparado para evitar esos problemas
	private static void arrayListsPolimorficos() {
		ArrayList<A> aes = new ArrayList<>();
		aes.add( new B() );
		aes.add( new C() );
		ArrayList<B> bs = new ArrayList<>();
		// aes = bs;  // Error de COMPILACIÓN - para evitar el error de ejecución que sí puede pasar en arrays
		aes.add( new C() );  // Esto sería error si se permitiera el aliasing (no puedo meter un C en un array de Bs)
		// alP2( bs );  // Error de COMPILACIÓN - para evitar ese aliasing peligroso
		alP2( aes );  // Esto sí se puede (concordancia estricta de tipos)
		// Moraleja: si queremos pasar un arraylist de un tipo genérico siempre tenemos que definirlo de ese tipo genérico, no de un tipo más especializado
	}
	
		private static void alP2( ArrayList<A> alGenerico ) {
			alGenerico.set( 0, new C() );  // Esto no es error nunca porque no se puede pasar un ArrayList que no sea genérico como parámetro
		}


}

class A {
	int a;
	public void sacaA() { System.out.println("A"); }
}

class B extends A {
	int b;
	@Override
	public void sacaA() { System.out.println("A-B"); }
}

class C extends A {
	int c;
	@Override
	public void sacaA() { System.out.println("A-C"); }
}
