package tema5.ejemplos.gestionPelis;

public class Peli {
	protected String nombre;
	protected long ingresos;
	protected String poster;
	protected String comentarios;
	public Peli(String nombre, long ingresos, String poster) {
		super();
		this.nombre = nombre;
		this.ingresos = ingresos;
		this.poster = poster;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIngresos() {
		return ingresos;
	}
	public void setIngresos(long ingresos) {
		this.ingresos = ingresos;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return nombre + " (" + ingresos + "$)";
	}
}
