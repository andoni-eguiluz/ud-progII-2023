package tema3.javaCollections.sacaEquipos;

public class Partido {
	private String equipo1;
	private String equipo2;
	public Partido(String equipo1, String equipo2) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}
	public String getEquipo1() {
		return equipo1;
	}
	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}
	public String getEquipo2() {
		return equipo2;
	}
	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}
	@Override
	public String toString() {
		return equipo1 + "-" + equipo2;
	}
}
