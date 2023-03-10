package tema1.resueltos.ej13;

import java.awt.Font;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

// TODO creado desde la última clase

/** Ejercicio 1.13 resuelto (primera versión del clicker)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ClickerV1 {

	private static final long TIEMPO_MAXIMO_PUNTOS = 2000;
	private static VentanaGrafica ventana;
	private static int puntos;
	
	public static void main(String[] args) {
		initVentana();
		juego();
	}
	
	private static void initVentana() {
		ventana = new VentanaGrafica( 800, 600, "Clicker (ej. 1.13)" );
		ventana.setMensajeFont( new Font( "Arial", Font.PLAIN, 24 ) );  // TODO objeto Font
	}
	
	private static void juego() {
		puntos = 0;
		while (!ventana.estaCerrada()) {
			Circulo circulo = creaNuevoCirculo();
			ventana.borra();
			circulo.dibujar( ventana );
			esperaAClickEnCirculo( circulo );
			long tiempo = circulo.getTiempoVida();
			if (tiempo < TIEMPO_MAXIMO_PUNTOS) {
				puntos += (int) (TIEMPO_MAXIMO_PUNTOS - tiempo);
				ventana.setMensaje( "Puntos: " + puntos );
			}
		}
	}
	
	private static Circulo creaNuevoCirculo() {
		return new Circulo( ventana );
	}
	
	private static void esperaAClickEnCirculo( Circulo circulo ) {
		while (!ventana.estaCerrada()) {  // TODO Ojo a esta condición (true = no se acaba - probar)
			Point click = ventana.esperaAClick();
			if (click!=null) {
				if (circulo.contienePunto( click )) {
					return;
				} else {
					puntos -= 1000;
					ventana.setMensaje( "Puntos: " + puntos );
				}
			}
		}
	}
	
}