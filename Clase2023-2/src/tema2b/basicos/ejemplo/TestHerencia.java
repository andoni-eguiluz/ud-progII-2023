package tema2b.basicos.ejemplo;

import java.awt.Point;
import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Ejemplo de herencia
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class TestHerencia {
	public static void main(String[] args) {
		pruebas();
		polimorfismoDeDatos();
		animacionTierra();
		animacionPlanetasYBurbujas();
		pruebaDeEquals();
	}
	private static void pruebas() {
		Esfera e1 = new Esfera( 100, 100, 50 );
		System.out.println( e1.toString() );
		Planeta p1 = new Planeta( "Tierra", 200, 200, 50 );
		// ¿error en el radio?
		p1.setRadio( 3 );
		System.out.println( p1 );
		
		// Polimorfismo de código
		Planeta p2 = new Planeta( "Marte", 400, 400, 2 );  // ¿A qué setRadio se llama? EN EJECUCIÓN
		System.out.println( p2 );
		
		// Polimorfismo de datos
//		Planeta p3 = new Esfera( 100, 100, 50 );
		Esfera e2 = new Planeta( "Plutón", 250, 200, 100 );
		Object o = p2;

		// Lista "normal": planetas
		ArrayList<Planeta> lP = new ArrayList<>();
		lP.add( p2 );

		// Lista polimórfica: permite guardar esferas... y planetas (todo planeta ES-UNA esfera)
		ArrayList<Esfera> lE = new ArrayList<>();
		lE.add( p2 );
		
		ArrayList<Object> lO = new ArrayList<>();  // Lista super polimórfica: todo ES-UN object
		lO.add( p2 );
		lO.add( e1 );
		lO.add( e2 );
		lO.add( o );
		lO.add( Integer.valueOf( 7 ) );
		lO.add( "hola" );
	}
	
	private static void polimorfismoDeDatos() {
		Esfera e1 = new Esfera( 150, 100, 80 );
		Planeta p1 = new Planeta( "tierra", 280, 300, 150 );
		// Planeta p2 = new Esfera( 50, 100, 100 );  // No funciona - lógico
		Esfera e2 = new Planeta( "tierra", 280, 300, 150 );  // ¿PERDÓN????? Sí que funciona
			// Esto es polimorfismo de datos
			// Un objeto planeta ES-UNA esfera
			// Luego puede almacenarse (referenciarse) en una variable de tipo esfera
			// Dicho de otra forma: una variable de tipo madre puede almacenar (referenciar) cualquier objeto de sus clases descendientes
		// Y llevado al extremo...
		Object o1 = new Planeta( "tierra", 280, 300, 150 );  // Claro, un planeta es un objeto
		Object o2 = new Esfera( 450, 300, 100 );  // Y una esfera también
		Object o3 = new Point(100, 50);  // De hecho, cualquier objeto en Java ES-UN Object (todas las clases heredan de Object)
		// OJO - NO SE CONVIERTE A OBJECT; NO HAY UN "CAST" 
		// Es algo así como que su "etiqueta" dice Object, pero lo que guarda (referencia) es una instancia más ESPECIALIZADA, es-un Object, pero puede ser algo más
		
		// Sin embargo Java al COMPILAR lo que "ve" es el cajón. Entonces se puede hacer
		System.out.println( p1.getRotacion() );
		// System.out.println( e2.getRotacion() );  // Pero no esto
		// System.out.println( o1.getRotacion() );  // Ni esto
		
		// Esta diferencia entre lo que etiqueta la VARIABLE y el OBJETO (DATO) que contiene se puede resolver en ejecución con el operador 
		// instanceof combinado con una sintaxis de casting:
		if (o1 instanceof Planeta) {  // Si el objeto al que referencia la variable o1 es un planeta y no cualquier otro object...
			Planeta planeta = (Planeta) o1;  // "LO MIRO COMO" un planeta, COMO LO QUE ES (en ejecución)
			System.out.println( planeta );
		}
		// A esto se le llama una cláusula de protección (de polimorfismo de datos). Solo lo tratamos como un planeta
		// SI ESTAMOS SEGUROS (en ejecución) que es un planeta.
		// No se recomienda hacer nunca el cast sin un instanceof antes:
		// (Planeta) o1
		// Tengamos en cuenta que no siempre sabemos lo que tenemos en ejecución porque puede venir de un proceso de código.
		// Por eso a menudo hay que chequearlo en ejecución, y casi nunca sabemos a priori lo que hay en esa variable.
		
		// En resumen, todas las variables de tipo objeto SON potencialmente POLIMÓRFICAS, porque lo que en ejecución pueden contener
		// es cualquer instancia de su jerarquía INFERIOR (subclases).  Nunca superior
		
		// Y esto afecta tanto a variables, como atributos, como retornos, como parámetros...
		// y por supuesto a estructuras de datos. Veamos:
		
		ArrayList<Object> listaGenerica = new ArrayList<>();  // Esta lista tendrá objects cualesquiera
		ArrayList<Esfera> listaEsferas = new ArrayList<>();  // Esta lista tendrá esferas cualesquiera
		ArrayList<Planeta> listaPlanetas = new ArrayList<>();  // Esta lista tendrá solo planetas (mientras no tenga clases hijas)
		listaGenerica.add( o1 );
		listaGenerica.add( o2 );
		listaGenerica.add( o3 );
		listaGenerica.add( e1 );
		listaGenerica.add( p1 );
		listaGenerica.add( e2 );
		
		// listaEsferas.add( o1 );  // No porque Java no sabe a priori qué hay dentro y puede ser cualquier cosa. Sí se puede chequear en ejecución:
		if (o1 instanceof Esfera) {
			listaEsferas.add( (Esfera) o1 );  // Observa el cast
		}
		// listaEsferas.add( o2 );  // Idem
		// listaEsferas.add( o3 );  // Idem
		listaEsferas.add( e1 );
		listaEsferas.add( p1 );
		listaEsferas.add( e2 );
		
		// listaPlanetas.add( o1 ); // No (pero podría hacerse instanceof)
		// listaPlanetas.add( e1 ); // Idem
		listaPlanetas.add( p1 );

		// Una vez que tenemos una estructura de datos polimórfica (de datos), podemos aprovechar su polimorfismo de código. Por ejemplo
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Prueba herencia" );
		for (Esfera esfera : listaEsferas) {
			esfera.dibujar( v );
		}
		// O bien
		for (Object posibleEsfera : listaGenerica) {
			// posibleEsfera.dibujar( v );  // No se puede pero...
			if (posibleEsfera instanceof Esfera) {
				Esfera e = (Esfera) posibleEsfera;
				e.dibujar( v );
			}
		}
		v.esperaAClick();
		v.setMensaje( "Haz click para cerrar" );
		v.acaba();		
	}
	
	private static void animacionTierra() {
		Planeta p1 = new Planeta( "tierra", 280, 300, 150 );
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Animación de tierra" );
		v.setMensaje( "Haz click para acabar animación" );
		p1.dibujar( v );
		
		// Rotación de la tierra: 5 segundos, 50 fps = 250 iteraciones
		// Vamos a hacer la animación
		
		// Intento 1. Funciona pero no temporiza muy bien
		// El tiempo no es muy exacto por los retrasos del dibujado y la propia espera
		// long tiempo = System.currentTimeMillis();
		// for (int i=0; i<250; i++) {
		// 	v.espera( 20 );
		// 	p1.rotar( Math.PI*2 / 250 );
		// 	p1.dibujar( v );
		// }
		// System.out.println( "Tiempo de rotación: " + (System.currentTimeMillis() - tiempo) + " msgs." );
		
		// Intento 2. Funciona ok
		// Medir el tiempo exactamente y hacer las esperas en función del tiempo
		// long tiempo = System.currentTimeMillis();
		// long tiempoEspera = 20;
		// for (int i=0; i<250; i++) {
		// 	if (tiempoEspera>0) {
		//  	v.espera( tiempoEspera );
		// }
		// 	p1.rotar( Math.PI*2 / 250 );
		// 	p1.dibujar( v );
		// 	// Ajuste para que se respete el tiempo
		// 	tiempoEspera = (20*(i+1)) - (System.currentTimeMillis() - tiempo);  // Tiempo que quedaría por esperar en la siguiente iteración para ajustarnos a 20 msg por frame
		// 	System.out.println( tiempoEspera );
		// }
		// tiempo = System.currentTimeMillis() - tiempo;
		// System.out.println( "Tiempo de rotación: " + tiempo + " msgs." );
		// System.out.println( "FPS = " + 250.0 / tiempo * 1000 );
		
		// Intento 3. Funciona ok
		// Delegar la animación en el planeta, configurando el tiempo de rotación
		// y programando un método de "animación" por ejemplo que rote en función del tiempo transcurrido 
		long tiempoInicial = System.currentTimeMillis();
		p1.setTiempoRotacion( 10000 );  // Tiempo que se quiere que tarde en dar una vuelta completa
		long tiempo = System.currentTimeMillis();
		int frames = 0;
		while (System.currentTimeMillis() - tiempoInicial <= 10000) {
			v.espera( 20 ); // Más o menos 50 fps (será un poco menos)
			long deltaTime = System.currentTimeMillis() - tiempo;
			tiempo = System.currentTimeMillis();
			p1.animar( deltaTime );
			p1.dibujar( v );
			frames++;
			if (v.getRatonClicado()!=null) { // Acaba la animación si se hace click
				break;
			}
		}
		tiempo = System.currentTimeMillis() - tiempoInicial;
		System.out.println( "Desviación rotación final: " + (p1.getRotacion() - Math.PI*2) );
		System.out.println( "Tiempo de rotación: " + tiempo + " msgs." );
		System.out.println( "FPS = " + (1.0 * frames / tiempo * 1000) );
		v.setMensaje( "Haz click para cerrar" );
		v.esperaAClick();
		v.acaba();		
	}

	private static void animacionPlanetasYBurbujas() {
		ArrayList<Esfera> lEsferas = new ArrayList<>();
		lEsferas.add( new Planeta( "tierra", 280, 300, 150 ) );
		Planeta marte = new Planeta( "marte", 570, 450, 75 );
		marte.setTiempoRotacion( 2000 );
		lEsferas.add( marte );
		lEsferas.add( new Burbuja( 50, 520, 90 ) );
		lEsferas.add( new Burbuja( 400, 450, 110 ) );
		lEsferas.add( new Burbuja( 600, 90, 45 ) );
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Animación planetas y burbujas" );
		v.setDibujadoInmediato( false );
		v.setMensaje( "Cierra la ventana para acabar" );
		
		long tiempo = System.currentTimeMillis();
		while (!v.estaCerrada()) {  // Animación hasta que la ventana se cierre
			v.espera( 20 ); // Más o menos 50 fps (será un poco menos)
			long deltaTime = System.currentTimeMillis() - tiempo;
			tiempo = System.currentTimeMillis();
			for (Esfera e : lEsferas) {
				// e.animar( deltaTime );  // ¿Por qué no puede hacerse esto 
					// si tanto las burbujas como los planetas se pueden animar?
				if (e instanceof Planeta) {
					((Planeta) e).animar( deltaTime );  // da igual: Planeta p = (Planeta) e; p.animar(deltaTime);
				} else if (e instanceof Burbuja) {
					((Burbuja) e).animar( deltaTime );
				}
			}
			v.borra();
			for (Esfera e : lEsferas) {
				e.dibujar( v );  // sin embargo, sí que se puede dibujar
			}
			v.repaint();
		}
	}
	
	private static void pruebaDeEquals() {
		// Probamos el equals:
		Planeta p1 = new Planeta( "tierra", 280, 300, 150 );
		Esfera e2 = new Planeta( "tierra", 280, 300, 150 );
		Object o1 = new Planeta( "tierra", 280, 300, 150 );
		
		System.out.println( "o1 == p1? " + o1.equals( p1 ) );  // ¿Y esto de false? ¿Por qué?
		System.out.println( "o1 == e2? " + o1.equals( e2 ) );
		// Redefinamos el equals (clase Planeta) y a ver si cambia (también en la clase Esfera)

		// Importante: el equals a veces se usa sin que lo parezca (indirectamente). Por ejemplo:
		ArrayList<Object> listaGenerica = new ArrayList<>();  // Esta lista tendrá objects cualesquiera
		listaGenerica.add( o1 );
		listaGenerica.add( new Esfera( 450, 300, 100 ) );
		listaGenerica.add( new Point(100, 50) );
		listaGenerica.add( new Esfera( 150, 100, 80 ) );
		listaGenerica.add( p1 );
		listaGenerica.add( e2 );
		if (listaGenerica.contains( p1 )) {
			System.out.println( p1 + " está en la lista " + listaGenerica );
		}
		// En el ArrayList pasa lo mismo con indexOf, lastIndexOf, remove(objeto)... remove solo quita 1. Para quitar varios hay que recorrer:
		//		for (Object objeto : listaGenerica) {  // Por qué no vale un for each?
		//			if (objeto.equals(p1)) { 
		//				listaGenerica.remove( p1 );
		//			}
		//		}
		while (listaGenerica.contains(p1)) {
			listaGenerica.remove( p1 );
		}
		System.out.println( "Tras quitar " + p1 + " del todo, la lista queda: " + listaGenerica );
	}
	
}
