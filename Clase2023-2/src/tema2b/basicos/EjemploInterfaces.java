package tema2b.basicos;

import java.util.ArrayList;

/** Clase de ejemplo de "juguete" de interfaces y clases que los implementan (en EjemploClasesDeInterfaces)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploInterfaces {
	public static void main(String[] args) {
		Fregable f;
		//new Fregable();
		Fregable[] arrayF;
		ArrayList<Fregable> listaF;
		listaF = new ArrayList<>();
		listaF.add( new Vaso() );
		listaF.add( new CopaChampan() );
		listaF.add( new Tenedor() );
		// NO listaF.add( "Tenedor" );
		for (Fregable freg : listaF) {
			freg.fregar();
		}
	}
}

interface Fregable {
	void fregar();  // por defecto public abstract 
}

interface EsFragil {
	boolean estaRoto();
}

interface SirveParaComer {
	void usaParaComer( String comida );
	void guardaEnCajon( String cajon );
}

interface Lavajilleable extends Fregable {
	// void fregar();
	void meterEnLavavajillas();
}