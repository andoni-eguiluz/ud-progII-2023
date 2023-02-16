package tema1.basicos;

import java.awt.Point;
import java.util.Date;

// import java.lang.*;  - se hace por defecto
public class PrimitivosYObjetos {
	
	static Point p;
	
	public static void main(String[] args) {
		primitivos();
		objetos();
	}
	
	// Trabajo por valor
	private static void primitivos() {
		int movil = 666777555;
		int movil2 = 666777555;
		int movil3 = movil;
		if (movil==movil2) {
			System.out.println( "M1 = M2" );
		}
		if (movil==movil3) {
			System.out.println( "M1 = M3" );
		}
		compruebaMovil(movil);
		System.out.println( movil );
	}
	
	private static void compruebaMovil( int m ) {
		// ...
		m = 0;
	}
	
	private static void objetos() {
		java.lang.String string23 = new String("hola");
		Date fecha = new Date();
		Point punto = new Point( 4, 5 );
		Point punto2 = new Point( 4, 5 );
		Point punto3 = punto;
		System.out.println( punto + " - " + punto2 + " - " + punto3 );
		if (punto==punto3) {
			System.out.println( "P1 = P3" );
		}
		if (punto==punto2) {
			System.out.println( "P1 = P2" );
		}
		// if (punto.x == punto2.x && punto.y == punto2.y) {
		if (punto.equals(punto2)) {
			System.out.println( "P1 contiene lo mismo que P2" );
		}
		p = punto2;
		System.out.println( punto2 );
		compruebaPunto( punto2 );
		System.out.println( punto2 );
		String miString = "hola";
		cambiaString(miString);
		System.out.println( miString );
	}
	
	private static void cambiaString( String s ) {
		System.out.println( s.replaceAll("h", "j") );
	}
	
	
	private static void compruebaPunto( Point p ) {
		//
		// p = null;
		p.x = 0;
		p.y = 0;
	}
	
}
