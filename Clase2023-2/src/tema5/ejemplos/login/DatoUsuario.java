package tema5.ejemplos.login;

public class DatoUsuario {
	private String nombre;
	private int x;
	private int y;
	public DatoUsuario(String nombre, int x, int y) {
		super();
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return nombre + " (" + x + "," + y + ")";
	}
	
}
