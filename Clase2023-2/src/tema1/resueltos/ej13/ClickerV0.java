package tema1.resueltos.ej13;

import java.awt.Color;
import java.awt.Point;
import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class ClickerV0 {
	public static void main(String[] args) {
		// Parte batch
		Circulo c = new Circulo( 15, 100, 200, Color.CYAN, Color.BLUE );
		System.out.println( c.toString() );
		Point punto = new Point( 200, 150 );
		Circulo c2 = new Circulo( 15, punto, Color.WHITE, Color.BLUE );
		System.out.println( c2 );
		Circulo c3 = new Circulo( 20, 80, 90 );
		System.out.println( c3 + " color " + c3.getColorBorde() );
		Circulo c4 = new Circulo();
		System.out.println( c4 );
		// Parte un pelín interactiva
		crearYProbarVentana( c4 );
	}
	
	private static void crearYProbarVentana( Circulo c ) {
		// Pruebas de ventana gráfica
		VentanaGrafica vent = new VentanaGrafica( 800, 600, "Mi ventanita" );
		vent.dibujaCirculo( c.getxCentro(), c.getyCentro(), c.getRadioEnPixels(), Circulo.getGrosor(), 
				c.getColorBorde(), c.getColorRelleno() );
		Point punto = vent.getRatonClicado();
		vent.setMensaje( "Pulsa en algún sitio de pantalla ");
		punto = vent.esperaAClick();
		Circulo cNuevo = new Circulo( 30, punto.x, punto.y );
		vent.dibujaCirculo( cNuevo.getxCentro(), cNuevo.getyCentro(), cNuevo.getRadioEnPixels(), Circulo.getGrosor(), 
				cNuevo.getColorBorde(), cNuevo.getColorRelleno() );
		System.out.println( punto );
		// TODO Nuevo desde el otro día en clase
		// Prueba de crear varios puntos aleatorios
		for (int i=0; i<10; i++) {
			Circulo circuloRandom = new Circulo(vent);
			vent.dibujaCirculo( circuloRandom.getxCentro(), circuloRandom.getyCentro(), circuloRandom.getRadioEnPixels(), Circulo.getGrosor(), 
					circuloRandom.getColorBorde(), circuloRandom.getColorRelleno() );
		}
	}
}
