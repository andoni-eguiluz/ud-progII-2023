package tema3.resueltos.ejercicios3b1_2;

import java.util.Comparator;

/** Comparador de enteros inverso
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ComparaAlReves implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return -o1.compareTo(o2);
	}

}
