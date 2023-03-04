package tema1.resueltos.ejercicio13;

import java.awt.Color;
import java.awt.Point;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de pruebas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Clicker {
	public static void main(String[] args) {
		Circulo circulo = new Circulo( 15, 150, 220, Color.RED, Color.BLACK );
		System.out.println( circulo );
		VentanaGrafica vent = new VentanaGrafica( 800, 600, "Clicker" );
		circulo.dibujar( vent );
		// vent.dibujaCirculo( circulo.getxCentro(), circulo.getyCentro(), circulo.getRadio(),
		//		Circulo.getGrosor(), circulo.getColorBorde(), circulo.getColorRelleno() );
		Point punto = vent.esperaAClick();
		System.out.println( punto );
		Circulo circulo2 = new Circulo( 15, punto, Color.CYAN, Color.YELLOW );
		circulo2.dibujar( vent );
		// vent.dibujaCirculo( circulo2.getxCentro(), circulo2.getyCentro(), circulo2.getRadio(),
		//		Circulo.getGrosor(), circulo2.getColorBorde(), circulo2.getColorRelleno() );
	}
}
