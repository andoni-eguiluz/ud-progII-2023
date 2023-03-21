package tema3.basicos;

/** Clase de ejemplo de uso de Enums (ver el enum: DiaSemana)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploEnum {

	// Ejemplo con días de la semana
	// Otros ejemplos de posibles enums:
	//  gama: alta, media, baja   (0, 1, 2)
	//  zona: norte, sur, este, oeste
	//  dia: lunes, martes, .... viernes
	//  Cargos: repartidor, reponedor, dependiente
	//  Somatotipo: ectomorfo - mesomorfo - endomorfo
	//  talla: xs, s, m, l, xl 
	//  Mes: enero, febrero, ... diciembre
	
	public static void main(String[] args) {
		// String dia = "Lunes";
		DiaSemana dia = DiaSemana.LUNES;       // Referencia al objeto enum lunes
		DiaSemana dia2 = DiaSemana.MIERCOLES;  // Referencia al objeto enum miércoles
		// Un enum no se puede construir: 
		// new DiaSemana();  // Error
		// Porque solo existen las instancias que se definan en el propio enum. 
		// Todo lo demás son referencias a esas mismas instancias (7 en este caso)
		
		// ¿Qué tiene un enum?
		
		// Como solo hay objetos únicos inmutables da igual comparar con == que con equals
		
		// Contiene el nombre con toString
		System.out.println( dia.toString() );
		// Contiene el índice de 0 a n-1 (en orden de definición)
		System.out.println( dia2.ordinal() );
		// Es recorrible: values() tiene un array con todos los valores en el orden definido
		for (DiaSemana d : DiaSemana.values()) {
			System.out.println( d );
		}
		// Implementa Comparable<DiaSemana>
		if (dia.compareTo(dia2) < 0) {
			System.out.println( dia + " antes de " + dia2 );
		}
		// Si tienes un String se puede convertir
		DiaSemana dia3 = DiaSemana.valueOf("MARTES");
		System.out.println( dia3 );
		// DiaSemana dia4 = DiaSemana.valueOf("martes");  // Pero ojo que genera error si hay cualquier diferencia
		
	}
}
