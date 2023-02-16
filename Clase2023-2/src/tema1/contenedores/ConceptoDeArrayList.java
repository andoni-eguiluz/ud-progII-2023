package tema1.contenedores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/** Concepto de arraylist (lista) en Java. Lee el código en secuencia y vete entendiendo y probando
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoDeArrayList {

	public static void main(String[] args) {
		listaDePrimitivos();
		listaDeObjetos();
	}
	
	public static void listaDePrimitivos() {
		// Un arraylist es una estructura que almacena una serie de datos homogéneos, como el array
		// Pero a diferencia del array, su tamaño es VARIABLE y pueden ir añadiéndose o borrándose elementos sin problema
		// y en cualquier posición (siempre que esté dentro de su rango)
		
		// El arraylist SOLO TIENE UNA DIMENSIÓN (es lineal). Aunque sí podremos hacer listas de listas (dos dimensiones)
		
		// Para declarar un arraylist, definimos el tipo ArrayList poniendo entre < y > el tipo de los elementos que va a contener
		ArrayList<Integer> listaEnteros;  // Listade enteros
		// Obsérvese que la clase ArrayList debe importarse porque no está en el paquete java.lang (está en java.util)

		// Es importante notar que NO SE PUEDEN HACER ARRAYLISTS DE TIPOS PRIMITIVOS (int, double, char...)
		// En su lugar tenemos unas clases que "envuelven" valores primitivos. Son estas ocho:
		// Byte, Short, Integer, Long, Float, Double, Character, Boolean
		// Obsérvesen las mayúsculas. Se llaman Wrappers (envoltorios). Hay una por cada tipo primitivo:
		// byte, short, int,     long, float, double, char,      boolean

		
		// El arraylist en Java es un objeto, y como en todos los objetos:
		// 1) Se gestiona por REFERENCIA (listaEnteros será una referencia al espacio donde están los datos)
		// 2) Hay que CONSTRUIRLO (el arraylist listaEnteros no está construido aún, solo está el espacio para la referencia)
		
		// Construyamos el arraylist. Se usa la palabra clave new (como cualquier otro constructor):
		listaEnteros = new ArrayList<Integer>(); 
		// No hace falta indicar el tamaño. Al principio está vacío (sin elementos)
		
		// Como al inicio no hay elementos, para acceder a ellos habrá que primero añadirlos.
		// Para esto se utiliza el método add:
		listaEnteros.add( 10 );
		listaEnteros.add( 20 );
		listaEnteros.add( 30 );
		// El método add añade al final de la lista. Si se quiere añadir por puntos intermedios, 
		// está sobrecargado con el índice y el elemento a añadir:
		listaEnteros.add( 0, 5 );   // Se añade un nuevo elemento 5 en la posición 0
		listaEnteros.add( 2, 15 );  // Se añade un nuevo elemento 15 en la posición 2
		
		// Como en el array, Sus elementos se "indexan" (acceden por índice) desde 0 a tamaño - 1 (el primer elemento es el 0, el último el n-1)
		
		// Muy importante: solo se pueden acceder elementos en posiciones VALIDAS. Por ejemplo, ahora la lista
		// tiene 5 elementos (hemos hecho 5 add), luego tiene elementos en la posición 0 a 4.
		// Sería un error de ejecución añadir ahora un elemento en la posición 7
		// listaEnteros.add( 7, 100 );  <--- ERROR DE EJECUCIÓN: IndexOutOfBoundsException
		// (sí se podría añadir en la 0 o en la 5. No en la -2 o en la 100)

		// Para consultar los elementos se usa el método get y el índice:
		System.out.println( "Primer elemento: " + listaEnteros.get(0) );
		System.out.println( "Último elemento: " + listaEnteros.get(4) );
		
		// Además de insertar, se pueden modificar elementos, también con índice:
		listaEnteros.set( 0, 27 );
		System.out.println( "Primer elemento cambiado: " + listaEnteros.get(0) );
		
		// Es muy habitual querer inicializar un arraylist sabiendo sus elementos. 
		// No es tan fácil como en los arrays, pero se puede hacer desde un array con una utilidad Arrays.asList:
		ArrayList<Integer> lista2 = new ArrayList<Integer>( Arrays.asList( new Integer[] { 1, 2, 3, 4 } ) );
		System.out.println( "Otra lista creada desde array: " + lista2 );

		// Y en cualquier caso hay que tener cuidado con acceder fuera de los índices porque generará un error de ejecución
		// TODO ¿Qué pasaría si hacemos esto?
		// System.out.println( listaEnteros.get(11) );
		
		// El tamaño de un arraylist se puede saber con un método que tienen todos los arraylists: size()
		System.out.println( "Tamaño del arraylist inicial: " + listaEnteros.size() );

		// La visualización de una lista se puede hacer directamente, no como en el array
		System.out.println( "Contenidos: " + listaEnteros );
		
		// Los valores sí se pueden ELIMINAR además de INSERTAR (y al hacerlo cambian las posiciones de los posteriores)
		ArrayList<Double> valores = new ArrayList<Double>( Arrays.asList( new Double[] { 1.0, 2.0, 4.0, 5.0, 8.3, 9.2, 11.1 } ) );
		System.out.println( "Otra lista = " + valores );
		// ¿Cómo insertamos el 3.0 entre el 2.0 y el 4.0?
		valores.add( 2, 3.0 );
		System.out.println( "Tras insertar en la posición 2 = " + valores );
		// ¿Cómo borramos el 5.0? Método remove:
		valores.remove( 4 );
		System.out.println( "Tras borrar el de la posición 4 = " + valores );
		
		// Además, podemos buscar un valor en la lista muy fácilmente, con el método indexOf:
		System.out.println( "El elemento 2.0 está en la posición " + valores.indexOf( 2.0 ) );
		// Que devuelve -1 si no se encuentra:
		System.out.println( "El elemento 5.0 está en la posición " + valores.indexOf( 5.0 ) );

		// Si solo se quiere comprobar si está o no, se puede usar el método contains:
		System.out.println( "El elemento 5.0 está en la lista? " + valores.contains( 5.0 ) );
		
	}
	
	public static void listaDeObjetos() {
		// Los arraylists pueden ser de cualquier objeto, no solo de los wrappers. Por ejemplo...
		// Declaramos un arraylist de fechas:
		ArrayList<Date> listaFechas;
		// TODO ¿Qué pasaría si hacemos 
		// System.out.println( vectorFechas.get(3) );
		// ? Prueba y piensa por qué ocurre ese error
		
		// Construimos el arraylist:
		listaFechas = new ArrayList<Date>();
		// Para simplificar la construcción, en el constructor se puede obviar el tipo base de arraylist, por ejemplo
		listaFechas = new ArrayList<>();  // Java infiere que el arraylist es de Dates (porque la variable está declarada así)
		
		// Obviamente podemos crear objetos y meterlos en la lista:
		Date fecha = new Date();  // Fecha-hora actual
		listaFechas.add( fecha );
		System.out.println( "Lista de fechas: " + listaFechas );

		// Importante es que los objetos dentro de la lista se guardan por referencia, con lo que si los cambiamos fuera,
		// cambiará el objeto que referencia la lista:
		fecha.setTime( 0 );
		System.out.println( "Tras cambiar el objeto: " + listaFechas );
		
		// Se puede hacer esto con cualquier tipo objeto, y también se pueden inicializar con Arrays.asList. Por ejemplo de Strings:
		ArrayList<String> listaNombres = new ArrayList<>( Arrays.asList( new String[] { "Andoni", "Amaia", "Enrique", "Josune" } ) );
		// Lo que sería igual a hacer la inserción uno por uno:
		ArrayList<String> listaNombres2 = new ArrayList<>();
		listaNombres2.add( "Andoni" );
		listaNombres2.add( "Amaia" );
		listaNombres2.add( "Enrique" );
		listaNombres2.add( "Josune" );
		System.out.println( listaNombres );
		System.out.println( listaNombres2 );
		
		// Los arraylists se pueden comparar... como los objetos: por referencias. Así que...
		if ( listaNombres==listaNombres2 ) {
			System.out.println( "Estas dos listas son la misma" );
		} else {
			System.out.println( "Estas dos listas NO son el mismo objeto" );
		}
		// Pero el equals del arraylist SÍ compara la igualdad de contenidos:
		if ( listaNombres.equals(listaNombres2) ) {
			System.out.println( "Estas dos listas SÍ SON iguales (equals)" );
		} else {
			System.out.println( "Estas dos listas NO SON iguales (equals)" );
		}
		
	}
		
}
