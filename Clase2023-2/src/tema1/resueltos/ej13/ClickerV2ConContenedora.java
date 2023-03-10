package tema1.resueltos.ej13;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Ejercicio 1.13 resuelto (segunda versión del clicker - multicírculo y puntuación negativa tras círculo "muerto")
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ClickerV2ConContenedora {

	private static final long TIEMPO_MAXIMO_PUNTOS = 2000;
	// v2
	private static final long PUNTUACION_CLICK_CIRCULO_MUERTO = -10000;
	private static final long ESPERA_ENTRE_FRAMES = 20;  // 50 fps
	private static final long ESPERA_ENTRE_CIRCULOS = 750;  // milis entre círculo y círculo
	private static final int NUM_MAXIMO_CIRCULOS = 30; // máximo número de círculos (si hay más se acaba el juego)
	
	private static VentanaGrafica ventana;
	private static int puntos;
	// v2
	private static GrupoCirculos circulos;
	
	public static void main(String[] args) {
		initVentana();
		juego();
	}
	
	private static void initVentana() {
		ventana = new VentanaGrafica( 800, 600, "Clicker (ej. 1.13)" );
		ventana.setMensajeFont( new Font( "Arial", Font.PLAIN, 24 ) );
		ventana.setDibujadoInmediato(false);
	}
	
	private static void juego() {
		circulos = new GrupoCirculos();
		puntos = 0;
		long tiempoUltimoCirculo = 0;
		while (!ventana.estaCerrada()) {
			// Mecánica 1: aparición de círculos
			if (System.currentTimeMillis() - tiempoUltimoCirculo > ESPERA_ENTRE_CIRCULOS) {
				circulos.anyadir( creaNuevoCirculo() );
				tiempoUltimoCirculo = System.currentTimeMillis();
			}
			// Mecánica 3: fin de juego por número de círculos
			if (circulos.size() > NUM_MAXIMO_CIRCULOS) {
				ventana.setMensaje( "Puntos: " + puntos + " JUEGO FINALIZADO - Nº MÁXIMO DE CÍRCULOS" );
				break;  // Sale del bucle de juego
			} else if (circulos.size() > NUM_MAXIMO_CIRCULOS - 5) {
				ventana.setMensaje( "Puntos: " + puntos + " ¡Atención! Ya hay " + circulos.size() + " círculos en juego" );
			}
			// Dibujado
			ventana.borra();
			circulos.dibujar( ventana );
			ventana.repaint();
			// Mecánica 2: click en círculo - puntuación
			Circulo circuloClick = hayClickEnAlgunCirculo( circulos );
			if (circuloClick!=null) {
				long tiempo = circuloClick.getTiempoVida();
				if (tiempo <= TIEMPO_MAXIMO_PUNTOS) {
					puntos += (int) (TIEMPO_MAXIMO_PUNTOS - tiempo);  // Puntuación = milisegundos "salvados" del círculo
				} else {
					puntos += PUNTUACION_CLICK_CIRCULO_MUERTO;
				}
				ventana.setMensaje( "Puntos: " + puntos );
				circulos.remove( circuloClick );
			}
			// Ciclo de espera del bucle de juego de tiempo real
			ventana.espera( ESPERA_ENTRE_FRAMES );
		}
	}
	
	private static Circulo creaNuevoCirculo() {
		return new Circulo( ventana );
	}
	
	private static Circulo hayClickEnAlgunCirculo( GrupoCirculos circulos ) {
		Point hayClick = ventana.getRatonClicado();
		if (hayClick!=null) {
			Circulo hayCirculoClicado = circulos.encuentraCirculoEnPunto(hayClick);
			if (hayCirculoClicado==null) {  // Ningún círculo pulsado
				puntos -= 1000;
				ventana.setMensaje( "Puntos: " + puntos );
			} else {
				return hayCirculoClicado;
			}
		}			
		return null;
	}
	
}
