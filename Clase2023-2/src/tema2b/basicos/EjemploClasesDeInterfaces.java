package tema2b.basicos;

public class EjemploClasesDeInterfaces {
}

class Tenedor implements Fregable, SirveParaComer, Lavajilleable {
	@Override
	public void fregar() {
		System.out.println( "pasar 3 veces estropajo" );
	}
	@Override
	public void usaParaComer(String comida) {
		System.out.println( "Como " + comida );
	}
	@Override
	public void guardaEnCajon(String cajon) {
		// TODO Auto-generated method stub
	}
	@Override
	public void meterEnLavavajillas() {
		System.out.println( "bla");
	}
}

abstract class Cristaleria implements Fregable {
	// abstract void fregar()
}

class Vaso extends Cristaleria implements Lavajilleable { // implements Fregable {
	@Override
	public void fregar() {
		System.out.println( "meter los deditos estropajo");
	}
	@Override
	public void meterEnLavavajillas() {
		// TODO Auto-generated method stub
	}
}

class CopaChampan extends Cristaleria implements EsFragil, Fregable {
	@Override
	public void fregar() {
		System.out.println( "fregar con cuidadín" );
	}
	@Override
	public boolean estaRoto() {
		return true;  // TODO
	}
}