package tema5.ejercicios;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/** Algoritmo base para solución de ejercicio 9.4
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class CalculoDePi {

	public static void main(String[] args) {
		int pruebaIteraciones = 3000000;
		System.out.println( calculaPI( pruebaIteraciones ) );
		// Con 3000000 iteraciones tarda 10-20 segundos y solo tiene 6 decimales correctos
	}
	
	private static BigDecimal calculaPI( int numIteraciones ) {
		BigDecimal bd = new BigDecimal("1");
		for (int i = 1; i < numIteraciones; i++) {
		    bd = bd.add( (new BigDecimal(Math.pow(-1, i))).divide( new BigDecimal(2 * i + 1), 2000, RoundingMode.HALF_UP ) );
		    // bd = bd.add( new BigDecimal( Math.pow(-1, i) / (2 * i + 1) ) );
		}
		return bd.multiply(BigDecimal.valueOf(4));
	}

}
