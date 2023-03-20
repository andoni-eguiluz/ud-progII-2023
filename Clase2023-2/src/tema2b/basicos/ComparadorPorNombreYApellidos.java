package tema2b.basicos;

import java.util.Comparator;

public class ComparadorPorNombreYApellidos implements Comparator<Persona> {

	@Override
	public int compare(Persona p1, Persona p2) {
		// Devuelve negativo si o1<o2, 0 si o1==o2, positivo si o2>o1
		// Solo un criterio:
		// return o1.getNombre().compareTo(o2.getNombre());
		int compNombres = p1.getNombre().toUpperCase().compareTo(p2.getNombre().toUpperCase() );
		if (compNombres!=0) {
			return compNombres;
		} else {
			return p1.getApellidos().toUpperCase().compareTo( p2.getApellidos().toUpperCase() );
		}
	}

}
