package tema5.hilos.ejemplos.sincronizacion;

/** Solución problema sincronización: synchronized
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploProblemasSincronizacion2 implements Runnable {
	private static Contador miContador = new Contador();
	@Override
	public void run() {
		for (int i = 0; i<1000000000; i++) {
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
	    private int c = 0;
	    synchronized public void inc() {  
	    	int d = c;
	    	d++;
	        c = d;
	    }

	    synchronized public void dec() {
	    	int d = c;
	    	d--;
	        c = d;
	    }
	    public int getContador() {
	        return c;
	    }
	}
	
}
