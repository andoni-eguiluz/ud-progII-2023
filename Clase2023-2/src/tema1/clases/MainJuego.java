package tema1.clases;

/** Ejemplo de clase principal que prueba la clase Juego creando algunas instancias
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class MainJuego {
	public static void main(String[] args) {
		
		System.out.println( Juego.getNumTotalHorasJugadas() );
		
		Juego miJuego = new Juego( "Tetris" );
		// miJuego.nombre = "Tetris";
		System.out.println( miJuego.getNombre() );
		// miJuego.nombre = "Otro";
		// miJuego.setNombre( "Otro" );  -- no escribo este método
		miJuego.setNumHorasJugadas( -10 );
		System.out.println( miJuego.getEdadMinimaRecomendada() );
		System.out.println( miJuego.getNumHorasJugadas() );
		Juego miJuego2 = new Juego( "Mario", -15 );
		System.out.println( miJuego2.getNumHorasJugadas() );
		Juego miJuego3 = null;
		// NO - Error de ejecución - miJuego3.setNumHorasJugadas( 100 );
		miJuego.setPrecioFinal( 12.10 );
		System.out.println( miJuego.getPrecioFinal() );
		System.out.println( miJuego.getPrecioSinIva() );
		Juego miJuego4 = null;
		miJuego3 = new Juego( "Monkey Island", 150 );
		miJuego4 = new Juego( "Monkey Island", 150 );
		if (miJuego3==miJuego4) {
			System.out.println( "Juego 3 y 4 son ==" );
		}
		if (miJuego3.equals(miJuego4)) {
			System.out.println( "Juego 3 y 4 son iguales" );
		}
		// Cómo hacer que se vea bien?
		miJuego2.setGenero( Genero.PLATAFORMAS );
		miJuego3.setGenero( Genero.AVENTURAS );
		miJuego4.setGenero( Genero.AVENTURAS );
		miJuego4.setPrecioFinal( 15.9999999 );
		System.out.println( "Datos del juego: " + miJuego3 );  // .toString() no hace falta
		System.out.println( "Datos del juego: " + miJuego4.toString() );
		// this()
		// static?
		// Juego.numTotalHorasJugadas = 17;
		miJuego4.setNumHorasJugadas( 200 );
		System.out.println( Juego.getNumTotalHorasJugadas() );
		// Géneros?
	}
}
