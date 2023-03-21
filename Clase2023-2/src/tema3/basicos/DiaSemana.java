package tema3.basicos;

/** Enum de ejemplo - lista de días de la semana
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public enum DiaSemana {
	LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
	
	// Es una clase
	// Crea 7 objetos inmutables y no se pueden crear más
	// Es como si hicera algo así:
	// public static final LUNES = new DiaSemana( 0, "LUNES" );
	// public static final MARTES = new DiaSemana( 1, "MARTES" );
	// etc.
	
	// Podrían tener atributos, métodos si hiciera falta
	
	// public String algunAtributo;
	// public void algunMetodo() {
	// }
}
