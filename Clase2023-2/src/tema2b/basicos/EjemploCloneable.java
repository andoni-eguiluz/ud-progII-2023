package tema2b.basicos;

/** Ejemplo de uso del interfaz cloneable
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploCloneable {
	// Lo del CloneNotSupportedException lo comentaremos en el tema 6
	public static void main(String[] args) throws CloneNotSupportedException {
		A a1 = new A( 1 );
		A a2 = (A) a1.clone();
		System.out.println( "Objeto inicial " + a1 + " y objeto clonado " + a2 );
	}
}

// Clase cloneable (se pueden duplicar objetos llamando al método clone)
class A implements Cloneable {
	int atrib1;
	public A( int atrib1 ) {
		this.atrib1 = atrib1;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		A a = new A( this.atrib1 );
		return a;
	}
	@Override
	public String toString() {
		return "A " + atrib1;
	}
}