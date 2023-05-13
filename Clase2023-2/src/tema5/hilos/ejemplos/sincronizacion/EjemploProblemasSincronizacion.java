package tema5.hilos.ejemplos.sincronizacion;

/** Ejemplo de problema de sincronización en datos compartidos
 * @author andoni.eguiluz at ingenieria.deusto.es
 *
 */
public class EjemploProblemasSincronizacion implements Runnable {
	private static Contador miContador = new Contador();
	@Override
	public void run() {
		for (int i = 0; i<1000000; i++) {
			miContador.inc();
			miContador.dec();
		}
	}
	public static void main( String[] s ) {
		 Thread t1 = new Thread( new EjemploProblemasSincronizacion() );
		 Thread t2 = new Thread( new EjemploProblemasSincronizacion() );
		 Thread t3 = new Thread( new EjemploProblemasSincronizacion() );
		 System.out.println( "Contador = " + miContador.getContador() );  // Contador = 0
		 long tiempo = System.currentTimeMillis();
		 t1.start();
		 t2.start();
		 t3.start();
		 try {
			 t1.join();
			 t2.join();
			 t3.join();
		 } catch (InterruptedException e) {
		 }
		 System.out.println( "Contador = " + miContador.getContador() );  // Debería ser cero pero... 
		 System.out.println( "Tiempo transcurrido: " + (System.currentTimeMillis() - tiempo) + " msgs." );
	}
	
	private static class Contador {
	    private int miContador = 0;
	    public void inc() {  
	    	// Haciendo miContador++ valdría 
	    	// pero se ve más claro el problema si se hace en pasos:
	    	int d = miContador;
	    	d++;
	        miContador = d;
	    }

	    public void dec() {
	    	// Haciendo miContador-- valdría
	    	int d = miContador;
	    	d--;
	        miContador = d;
	    }
	    public int getContador() {
	        return miContador;
	    }
	}


}
