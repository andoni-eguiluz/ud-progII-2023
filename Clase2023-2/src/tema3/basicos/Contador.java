package tema3.basicos;

public class Contador {
	private int cont = 0;
	public Contador() {
		cont = 1;
	}
	public void inc() {
		cont++;
	}
	public int get() {
		return cont;
	}
	@Override
	public String toString() {
		return "" + cont;
	}
}
