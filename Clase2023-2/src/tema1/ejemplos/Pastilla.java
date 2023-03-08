package tema1.ejemplos;

/** Clase de ejemplo para gestionar medicamentos y poder crear objetos
 * con cada una de nuestras pastillas.
 */
public class Pastilla {
	
	private String marca;
	private String componente;
	private String unidad;  // "mgr" por miligramo, "ml" por mililitro, "ud" por unidad
	private int medida;
		
	/** Construye una pastilla nueva (errónea si la unidad no tiene un valor correcto)
	 * @param marca	Marca del medicamento (p ej. "Termalgin")
	 * @param componente	Componente activo del medicamento
	 * @param unidad	Unidad de medida del componente activo: "mgr", "ml" o "ud"
	 * @param miligramos	cantidad del componente activo
	 */
	public Pastilla( String marca, String componente, String unidad, int miligramos ) {
		this.marca = marca;
		this.componente = componente;
		this.unidad = unidad;
		this.medida = miligramos;
		// Control de error -lo mejoraremos-
		if (unidad==null || (!unidad.equals("mgr") && !unidad.equals("ml") && !unidad.equals("ud")))
			System.out.println( "ERROR EN UNIDAD!! " + unidad + " incorrecta." );
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getUnidad() {
		return unidad;
	}
	/** Cambia la unidad de medida
	 * @param unidad	Unidad de medida del componente activo: "mgr", "ml" o "ud"
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getComponente() {
		return componente;
	}
	/** Modifica el componente activo de la pastilla
	 * @param componente	El componente activo. DEBE ser uno de los siguientes: "paracetamol", .....
	 */
	public void setComponente(String componente) {
		this.componente = componente;
	}
	public int getMedida() {
		return medida;
	}
	/** Cambia la cantidad de mg de la pastilla
	 * @param miligramos	Esta cantidad DEBE SER POSITIVA
	 */
	public void setMedida(int miligramos) {
		this.medida = miligramos;
	}
	public String toString() {
		String ret = marca + "[" + componente + "," + medida + " ";
		switch (unidad) {
			case "ml": ret += "mls.";
				break;
			case "mgr": ret += "mgrs.";
				break;
			case "ud": ret += "unidades";
				break;
			default: ret += "ERROR EN UNIDAD";
				break;
		}
		return ret + "]";
	}
	
	public boolean equals( Pastilla p ) {
		if (this.marca.equals(p.marca) &&
			this.componente.equals(p.componente) &&
			this.medida==p.medida &&
			this.unidad.equals(p.unidad)
		   ) {
			return true;
		} else {
			return false;
		}
	}
		
	static public void main( String[] s ) {
		new Pastilla( "Termalgin", "Paracetamol", "miligramos", 500 );  // Incorrecta
		Pastilla p1 = new Pastilla( "Termalgin", "Paracetamol", "mgr", 500 );
		Pastilla p2 = new Pastilla( "Dalsy", "Ibuprofeno", "ml", 20 );
		System.out.println( "Pastilla 1 -> " + p1 );
		System.out.println( "Pastilla 2 -> " + p2 );
		System.out.println( "Cambiando datos de la pastilla 2:");
		p2.setMarca( "Espidifen" );
		p2.setMedida( 600 );
		p2.setUnidad( "mgr" );
		System.out.println( "Pastilla 2 -> " + p2 );
		Pastilla p3 = new Pastilla( "Espidifen", "Ibuprofeno", "mgr", 600 );
		if (p2.equals(p3)) {
			System.out.println( "Son iguales!");
		}
	}
}
