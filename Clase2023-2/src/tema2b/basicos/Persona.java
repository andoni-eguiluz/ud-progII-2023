package tema2b.basicos;

import java.util.ArrayList;
import java.util.Arrays;

public class Persona extends Object implements Comparable<Persona> { // implements Comparable {
	
	/** Método de prueba de persona y ordenación de personas
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		Persona p1 = new Persona( 11111111, 'a', "María", "pérez" );
		Persona p2 = new Persona( 22222222, 'c', "Luis", "Gómez" );
		Persona p3 = new Persona( 33333333, 'j', "María", "Ruiz" );
		ArrayList<Persona> lista = new ArrayList<>();
		lista.add( p3 );
		lista.add( p2 );
		lista.add( p1 );
		// Persona[] aP = { p1, p2, p3 };
		// ArrayList<Persona> l2 = new ArrayList<>( Arrays.asList( p3, p2, p1 ) );
		System.out.println( lista );
		lista.sort( null );
		System.out.println( lista );
		System.out.println( "a".compareTo( "c" ));
		// Cómo ordeno por nombre? Necesito un comparator
		lista.sort( new ComparadorPorNombreYApellidos() );
		System.out.println( lista );
	}
	
	@Override
	public int compareTo(Persona persona2) {
		// Devolver negativo, 0 o positivo en función de this < persona2, this == persona2, this > persona2
		// Manera 1 - la esperable
		// System.out.println( " Está comparando " + this + " con " + persona2 );
//		if (this.dniNum < persona2.dniNum) {
//			return -1;
//		} else if (this.dniNum == persona2.dniNum) {
//			return 0;
//		} else {
//			return +1;
//		}
		// Manera 2: la más rápida pero menos obvia
		return this.dniNum - persona2.dniNum;
	}
	
//	Interfaz Comparable SIN genericidad
//	@Override
//	public int compareTo(Object o) {
//		// TODO Auto-generated method stub
//		return 0;  // negativo, 0 positivo dep. de la comparacións
//	}
	
	@Override
	public String toString() {
		return "" + dniNum + dniletra + ": " + nombre + " " + apellidos;
	}



	private int dniNum;
	private char dniletra;
	private String nombre;
	private String apellidos;
	public Persona(int dniNum, char dniletra, String nombre, String apellidos) {
		super();
		this.dniNum = dniNum;
		this.dniletra = dniletra;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	public int getDniNum() {
		return dniNum;
	}
	public void setDniNum(int dniNum) {
		this.dniNum = dniNum;
	}
	public char getDniletra() {
		return dniletra;
	}
	public void setDniletra(char dniletra) {
		this.dniletra = dniletra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
}
