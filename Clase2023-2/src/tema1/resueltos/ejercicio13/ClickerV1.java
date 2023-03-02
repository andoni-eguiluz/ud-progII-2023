package tema1.resueltos.ejercicio13;

import java.awt.Color;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de pruebas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ClickerV1 {
	
	private static VentanaGrafica vent;
	private static Circulo circulo;
	private static int puntos;
	
	public static void main(String[] args) {
		iniciarVentana();
		juego();
	}
	
	private static void iniciarVentana() {
		vent = new VentanaGrafica( 800, 600, "Clicker" );
	}
	
	private static void juego() {
		puntos = 0;
		circulo = new Circulo(); // Circulo aleatorio
		circulo.dibuja( vent );
		esperarAClickDentro();
		long tiempoVida = circulo.getTiempoVida();
		if (tiempoVida>=2000) {
			// Nada (sumar 0)
		} else {
			puntos += (tiempoVida-2000);
		}
	}
	
	private static void esperarAClickDentro() {
		while (true) {
			Point click = vent.esperaAClick();
			if (circulo.estaDentro(click)) {
				return;
			} else {
				puntos -= 1000;
			}
		}
	}
}
