package tema2.basicos.ejemplo;

import java.util.ArrayList;

public class TestHerencia {
	public static void main(String[] args) {
		prueba();
	}
	private static void prueba() {
		Esfera e1 = new Esfera( 100, 100, 50 );
		System.out.println( e1.toString() );
		Planeta p1 = new Planeta( "Tierra", 200, 200, 50 );
		// ¿error en el radio?
		p1.setRadio( 3 );
		System.out.println( p1 );
		Planeta p2 = new Planeta( "Marte", 400, 400, 2 );
		System.out.println( p2 );
		// Polimorfismo de código
		
		// Polimorfismo de datos
//		Planeta p3 = new Esfera( 100, 100, 50 );
		Esfera e2 = new Planeta( "Plutón", 250, 200, 100 );
		Object o = p2;
		
		ArrayList<Planeta> lP = new ArrayList<>();
		lP.add( p2 );
		
		ArrayList<Esfera> lE = new ArrayList<>();
		lE.add( p2 );
	}
}
