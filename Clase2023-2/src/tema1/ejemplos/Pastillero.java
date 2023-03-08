package tema1.ejemplos;
import java.util.Scanner;

/** Ejemplo de contenedor: Pastillero que contiene pastillas
 */
public class Pastillero {
	
	/** Método de prueba de pastillero - pastillas
	 * @param args
	 */
	public static void main(String[] args) {
		Pastilla pas1 = new Pastilla( "Termalgin", "Paracetamol", "mgr", 500 );
		Pastilla pas2 = new Pastilla( "Dalsy", "Ibuprofeno", "ml", 20 );
		System.out.println( "Pastilla 1 = " + pas1 );
		System.out.println( "Pastilla 2 = " + pas2 );
		Pastillero p = new Pastillero();
		p.anyadir( pas1 );
		p.anyadir( pas2 );
		System.out.println( "  Pastillero: " + p );
		
		// Cómo meter desde teclado:
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Introduce nueva pastilla. Nombre comercial del medicamento: ");
		String medic = scanner.nextLine();
		System.out.print( "Nombre compuesto activo: ");
		String compuesto = scanner.nextLine();
		String unidad = "";
		do {
			System.out.print( "Unidad (mgr, ud o ml): ");
			unidad = scanner.nextLine();
		} while (!unidad.equals("mgr") && !unidad.equals("ud") && !unidad.equals("ml"));
		System.out.print( "Cantidad de " + unidad + " en la pastilla: ");
		int cantidad = scanner.nextInt();
		Pastilla pastNueva = new Pastilla( medic, compuesto, unidad, cantidad );
		p.anyadir( pastNueva );
		System.out.println( "Tras meter... " + pastNueva );
		System.out.println( "  Pastillero: " + p );
		
		System.out.println();
		System.out.println( "Y ahora vaciamos el pastillero:" );
		// Vaciar pastillero
		while (p.getNumPastillas()>0) {
			pas1 = p.coger(); System.out.println( "Cojo " + pas1 );
			System.out.println( "  Pastillero: " + p );
		}
		
		scanner.close();  // Cierre de la entrada
	}
	
	
	private Pastilla[] misPastillas;
	private int numPastillas;
	private final int TAM_MAX_PASTILLERO = 100;
	/** Crea un pastillero vacío
	 */
	public Pastillero() {
		misPastillas = new Pastilla[TAM_MAX_PASTILLERO];
		numPastillas = 0;
	}
	/** Añade una pastilla nueva al pastillero
	 * ES ERRONEO CUANDO EL PASTILLERO ESTA LLENO ({@link #estaLleno()}
	 * @param pas	Pastilla a añadir
	 */
	public void anyadir( Pastilla pas ) {
		misPastillas[numPastillas] = pas;
		numPastillas++;
	}
	
	/** Informa sobre el llenado del pastillero
	 * @return	true si está lleno (y no se pueden meter más pastillas), false en caso contrario
	 */
	public boolean estaLleno() {
		return (numPastillas >= TAM_MAX_PASTILLERO);
	}
	
	/** Informa sobre el número de pastillas en el pastillero
	 * @return	número de pastillas, 0 si está vacío
	 */
	public int getNumPastillas() {
		return numPastillas;
	}
	
	/** Devuelve la última pastilla que hayamos metido en el pastillero
	 * pero si está vacío devuelve null
	 * @return	la última pastilla o null
	 */
	public Pastilla coger() {
		// Posible error: coger si no hay pastillas (numPastillas = 0)
		if (numPastillas == 0)
			return null;
		else {
			numPastillas--;
			return misPastillas[numPastillas];
		}
	}
	public String toString() {
		String ret = "{ ";
		for (int i=0; i<numPastillas; i++) {
			if (i>0) ret += ", ";
			ret += misPastillas[i].toString();
		}
		return ret + " }";
	}
	
}
