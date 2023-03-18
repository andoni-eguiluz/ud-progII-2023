package tema2b.basicos;

import java.util.ArrayList;

/** Ejemplo para entender el concepto de interfaces
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoInterfaces {

	public static void main(String[] args) {
		ArrayList<Mecanismo> listaMecs = new ArrayList<>();  // arraylist polimórfico
		listaMecs.add( new Sensor( "Temp" ) );
		listaMecs.add( new Sensor( "Luz" ) );
		listaMecs.add( new Blindaje( "Carcasa", 10 ) );
		listaMecs.add( new Micro( "Intel Core 2 Quad Q6600", 2.4*1024 ) );
		System.out.println( listaMecs );
		// ¿Cómo añado un test de reparación electrónica a mi lista de mecanismos? ¿Con muchos ifs o con unos pocos?
		for (int i=0; i<listaMecs.size(); i++) {
			Mecanismo m = listaMecs.get(i);
			System.out.println( m.getNombre() + " - " + m.toString() );
			if (m instanceof Reparable) {
				Reparable r = (Reparable) m;
				if (r.estaEstropeado()) {
					r.reparar();
				}
			}
			// !exp-lógica
			if (m instanceof Sustituible) {
				System.out.println( "Es sustituible: " + m );
			} else {
				System.out.println( "No es sustituible: " + m );
			}
			if (m instanceof Reparable) {
				System.out.println( "Es reparable: " + m );
			}
			if (m instanceof Vendible) {
				System.out.println( "Es vendible: " + m );
			}
		}
		// Crear lista polimórfica con interfaces
		ArrayList<Reparable> lReparables = new ArrayList<>();
		for (int i=0; i<listaMecs.size(); i++) {
			Mecanismo m = listaMecs.get(i);
			if (m instanceof Reparable) {
				lReparables.add( (Reparable) m );
			}
		}
		System.out.println( lReparables );
	}

}

interface Reparable {
	/** Comprueba si el elemento está estropeado o no
	 * @return	Devuelve true si está estropeado, false en caso contrario
	 */
	boolean estaEstropeado();  // public y abstract
	
	/** Repara el elemento
	 */
	void reparar();  // public y abstract
}

class Rara implements Sustituible {
	@Override
	public boolean estaEstropeado() {
		return false;
	}
	@Override
	public void reparar() {
	}
	@Override
	public void vender() {
	}
	@Override
	public void sustituir() {
	}
}

interface Vendible {
	void vender();
	void reparar();
}

interface Sustituible extends Reparable, Vendible {
	// vender()
	// reparar()
	// estaEstropeado()
	void sustituir();
}

abstract class Mecanismo {
	protected String nombre;
	public Mecanismo( String nombre ) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
}

class Sensor extends Mecanismo implements Reparable {
	double medicion;
	public Sensor( String nombre ) {
		super( nombre );
		medicion = Math.random();  // Simulamos la medición con un aleatorio
	}
	public double getMedicion() { return medicion; }
	@Override
	public String toString() { 
		return "Sensor " + nombre + "=" + String.format( "%4.2f", getMedicion() ); 
	}
	@Override
	public boolean estaEstropeado() {
		double estadReparacion = Math.random();
		if (estadReparacion<=0.01) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void reparar() {
		System.out.println( "Reparando " + toString() );
	}
}

class Blindaje extends Mecanismo {
	int dureza;
	public Blindaje( String nombre, int dureza ) {
		super( nombre );
		this.dureza = dureza;
	}
	public int getDureza() {
		return dureza;
	}
	public void setDureza(int dureza) {
		this.dureza = dureza;
	}
	@Override
	public String toString() { 
		return "Blindaje con dureza " + dureza; 
	}
}

class Micro extends Mecanismo implements Reparable, Sustituible {
	double mHz;
	public Micro( String nombre, double mHz ) {
		super( nombre );
		this.mHz = mHz;
	}
	public double getmHz() {
		return mHz;
	}
	@Override
	public String toString() { 
		return "Micro a " + getmHz() + " MHz"; 
	}
	public boolean estaEstropeado() {
		double estadReparacion = Math.random();
		if (estadReparacion<=0.001) {
			return true;
		} else {
			return false;
		}
	}
	public void reparar() {
		System.out.println( "Reparando " + toString() );
	}
	@Override
	public void vender() {
	}
	@Override
	public void sustituir() {
	}
}

