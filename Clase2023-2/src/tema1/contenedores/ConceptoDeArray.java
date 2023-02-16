package tema1.contenedores;

import java.util.Arrays;
import java.util.Date;

/** Concepto de array en Java. Lee el código en secuencia y vete entendiendo y probando
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoDeArray {
	public static void main(String[] args) {
		arrayDePrimitivos();
		arrayDeObjetos();
		matriz();
	}
	
	public static void arrayDePrimitivos() {
		// Un array es una estructura que almacena una serie de datos homogéneos. 
		// Lo que en matemáticas conocemos como un vector (si es de una dimensión) o una matriz (si es de dos). 
		// El array en Java y otros lenguajes puede ser de 1, 2 o más dimensiones.
		
		// Para declarar un array, simplemente definimos el tipo de los elementos que va a contener seguido de corchetes:
		int[] vectorEnteros;  // Vector de enteros
		// También se pueden poner los corchetes tras la variable (es indiferente): 
		int vectorEnteros2[];
		
		// El array en Java ES UN OBJETO. Esto significa:
		// 1) Se gestiona por REFERENCIA (vectorEnteros será una referencia al espacio donde está el array)
		// 2) Hay que CONSTRUIRLO (el array vectorEnteros no está construido aún, solo está el espacio para la referencia)
		
		// Construyamos el array. Se usa la palabra clave new (como cualquier otro objeto):
		vectorEnteros = new int[5]; // Entre corchetes, el tamaño del array
		
		// La construcción del array define el espacio para los elementos, que se inicializan con valor por defecto 
		// (0 para los números, falso para boolean, carácter asc 0 para los char) 
		
		// El tamaño del array es FIJO. Esto quiere decir que, una vez construido, 
		// el tamaño del array ya no cambiará nunca. Es decir, tenemos que saber a priori qué tamaño queremos que tenga.
		// Sus elementos se "indexan" (acceden por índice) desde 0 a tamaño - 1 (el primer elemento es el 0, el último el n-1)
		// Para acceder a los elementos se indica el nombre del array y el índice entre corchetes:
		vectorEnteros[0] = 10;
		vectorEnteros[1] = 20;
		vectorEnteros[2] = 30;
		vectorEnteros[3] = 40;
		vectorEnteros[4] = 50;
		
		// Es muy habitual querer inicializar un array sabiendo sus elementos. Para que no sea tan pesado, Java permite una sintaxis alternativa:
		vectorEnteros = new int[] { 10, 20, 30, 40, 50 };  // Observa que si se pone entre llaves los valores, no hace falta indicar el tamaño entre los corchetes (se toma del número de valores)

		// Si se hace la inicialización en la misma declaración, se puede usar una sintaxis aún más reducida:
		char[] vectorVocales = { 'a', 'e', 'i', 'o', 'u' };
		
		// Los valores de un vector se pueden modificar o consultar:
		vectorEnteros[4] = 51;
		System.out.println( "El tercer elemento del vector de enteros es " + vectorEnteros[2] );

		// Pero hay que tener cuidado con acceder fuera de los índices porque generará un error de ejecución
		// TODO ¿Qué pasaría si hacemos esto?
		// System.out.println( vectorEnteros[11] );
		
		// El tamaño de un array se puede saber con un atributo especial que tienen todos los arrays: length
		System.out.println( "Tamaño del vector: " + vectorEnteros.length );

		// La visualización de un array se puede hacer directamente pero no es legible:
		System.out.println( "Vector: " + vectorEnteros );
		// Hay un método en la clase de utilidad Arrays que lo facilita: toString:
		System.out.println( "Vector (más legible): " + Arrays.toString( vectorEnteros ) );
		
		// Los valores no se pueden ELIMINAR, ni INSERTAR. Si queremos quitar uno, habría que mover el resto. Lo mismo si quisiéramos añadir. Por ejemplo:
		double valores[] = { 1.0, 2.0, 4.0, 5.0, 0.0, 0.0, 0.0 };
		System.out.println( "Vector = " + Arrays.toString(valores) );
		// ¿Cómo insertamos el 3.0 entre el 2.0 y el 4.0?
		for (int i=5; i>=2; i--) {  // Observa que un for se puede usar para recorrer decrementando
			System.out.println( "Movemos el elemento " + i + " a la posición " + (i+1) );
			valores[i+1] = valores[i];
		}
		System.out.println( "Tras mover = " + Arrays.toString(valores) );
		valores[2] = 3.0;  // Ahora que hemos hecho hueco, metemos el 3
		System.out.println( "Cambiando el tercero = " + Arrays.toString(valores) );
		// TODO ¿podrías hacer un método que inserte de una forma genérica pudiendo cambiar los valores?  
		// Por ejemplo insertaEnVector( valores, 3.0, 3 ) que inserte el 3.0 en la posición 3, con cualquier valor de elemento e índice
		
	}
	
	public static void arrayDeObjetos() {
		// Los arrays pueden ser de objetos. En ese caso lo que guarda el array son REFERENCIAS en lugar de valores. Por ejemplo...
		// Declaramos un array de fechas:
		Date[] vectorFechas;
		// TODO ¿Qué pasaría si hacemos 
		// System.out.println( v[3] );
		// ? Prueba y piensa por qué ocurre ese error
		
		// Construimos el array por ejemplo de 6 fechas:
		vectorFechas = new Date[6];
		
		// Atención: los objetos se inicializan a referencias NULAS. Por ejemplo:
		System.out.println( "Fecha 3: " + vectorFechas[3] );
		
		// Obviamente podemos crear objetos y meterlos en el array:
		Date fecha = new Date();  // Fecha-hora actual
		vectorFechas[3] = fecha;
		System.out.println( "Fecha 3 ahora: " + vectorFechas[3] );
		System.out.println( "Array de fechas: " + Arrays.toString( vectorFechas ));
		
		fecha.setTime( 0 );
		System.out.println( vectorFechas[5] );
		
		// Se puede hacer esto con cualquier tipo objeto, y también se pueden inicializar. Por ejemplo un array de Strings:
		String[] vectorNombres = { "Andoni", "Amaia", "Enrique", "Josune" };
		// Lo que en realidad es
		String[] vectorNombres2 = { new String("Andoni"), new String("Amaia"), new String("Enrique"), new String("Josune") };
		System.out.println( Arrays.toString( vectorNombres ) );
		System.out.println( Arrays.toString( vectorNombres2 ) );
		
		// La igualdad de arrays es como la de objetos... por referencias. Así que...
		if ( vectorNombres==vectorNombres2 ) {
			System.out.println( "Estos dos vectores son el mismo" );
		} else {
			System.out.println( "Estos dos vectores NO son el mismo objeto" );
		}
		// Pero cuidado, porque el equals del vector NO compara la igualdad de contenidos!!!!
		if ( vectorNombres.equals(vectorNombres2) ) {
			System.out.println( "Estos dos vectores son iguales (equals)" );
		} else {
			System.out.println( "Estos dos vectores NO son iguales (equals)" );
		}
		// En su lugar, en la clase de utilidad Arrays.equals que sí compara los contenidos:
		if (Arrays.equals( vectorNombres, vectorNombres2 )) {
			System.out.println( "Estos dos vectores son iguales (Vector.equals)" );
		} else {
			System.out.println( "Estos dos vectores NO son iguales (Vector.equals)" );
		}
		
	}
	
	public static void matriz() {
		// Tanto los arrays de primitivos como de objetos pueden definirse de varias dimensiones, con tantos corchetes como dimensiones. Por ejemplo un array tridimensional:
		int [][][] matriz3D;
		// Se crea con el tamaño de cada dimensión
		matriz3D = new int [10][5][4];
		// Y se accede con tantos índices como dimensiones
		matriz3D[0][0][0] = 5;
		matriz3D[1][3][2] = 7;
		
		// Para saber el tamaño, cada dimensión funciona diferente:
		System.out.println( "Tamaño de la primera dimensión: " + matriz3D.length );
		System.out.println( "Tamaño de la segunda dimensión: " + matriz3D[0].length );
		System.out.println( "Tamaño de la tercera dimensión: " + matriz3D[0][0].length );
		
		// Visualizar las matrices no es tan fácil
		System.out.println( Arrays.toString( matriz3D ) );
		// Hay que programarlo ad hoc. Por ejemplo:
		for (int i1=0; i1<matriz3D.length; i1++) {
			for (int i2=0; i2<matriz3D[0].length; i2++) {
				for (int i3=0; i3<matriz3D[0][0].length; i3++) {
					System.out.println( "matriz[" + i1 + "][" + i2 + "][" + i3 + "] = " + matriz3D[i1][i2][i3] );
				}
			}
		}
		// TODO ¿Podrías definir y visualizar una matriz de 2 dimensiones con la tabla de multiplicar de 1 a 10?
	}
	
}
