package tema5.ejemplos.clasesInternas;

/** Prueba del ejemplo de clase interna (ver ClaseExterna.java)
 * @author andoni.eguiluz at deusto.es
 */
public class PruebaClaseInterna {
	public static void main(String[] args) {
		ClaseExterna objetoCE = new ClaseExterna();
		objetoCE.setDatoExterno( 5 );
		System.out.println( objetoCE );
		
		// No se puede:
		// ClaseExterna.ClaseInterna objetoCI = new ClaseExterna.ClaseInterna();
		
		// Pero esto sí (es un "objeto" creado desde un objeto "externo" y por tanto asociado a él):
		ClaseExterna.ClaseInterna objetoCI = objetoCE.new ClaseInterna();
		// objetoCI.setDatoExterno( 6 );  // No se puede acceder a los métodos de la clase externa (NO es herencia)
		objetoCI.setDatoInterno( 7 );
		System.out.println( objetoCI );  // Pero sí a los datos (desde sus métodos)
		
		// O sea: un objeto interno NECESITA estar LIGADO a un objeto externo
		// que es de donde toma sus atributos externos)
		
		// Y además puede haber clases internas ANONIMAS  (no hay tipo con nombre asociado)
		// (Y por ello el objeto será "único en su especie"
		// pero solo se pueden crear desde dentro de la clase externa:
		objetoCE.creaClaseAnonima();
		
		// Por último observa... los nombres de los .class creados en este paquete
	}
}
