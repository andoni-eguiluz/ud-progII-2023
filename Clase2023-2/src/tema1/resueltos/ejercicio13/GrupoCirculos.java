package tema1.resueltos.ejercicio13;

import java.awt.Point;
import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class GrupoCirculos {
	private ArrayList<Circulo> listaCirculos;
	public GrupoCirculos() {
		listaCirculos = new ArrayList<Circulo>();
	}
	
	public void anyadir( Circulo circulo ) {
		listaCirculos.add( circulo );
	}
	
	public void remove( Circulo circulo ) {
		listaCirculos.remove( circulo );
	}
	
	public int size() {
		return listaCirculos.size();
	}
	
	public void dibujar( VentanaGrafica vent ) {
		for (Circulo circulo : listaCirculos) {
			circulo.dibujar( vent );
		}
	}
	
	public Circulo hayClickEnAlgunCirculo( Point point ) {
		Circulo hayCirculoClicado = null;
		for (Circulo circulo : listaCirculos) {
			if (circulo.contienePunto( point )) {
				hayCirculoClicado = circulo;
				break;
			}
		}
		return hayCirculoClicado;
	}
}
