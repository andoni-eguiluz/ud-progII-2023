package tema5.resueltos.ej5b8;

public enum DiaSemana {
	LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
	private static String[] nombres = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
	@Override
	public String toString() {
		return nombres[ordinal()];
	}
}
