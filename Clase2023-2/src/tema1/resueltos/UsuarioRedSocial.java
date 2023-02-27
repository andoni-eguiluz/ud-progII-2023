package tema1.resueltos;

public class UsuarioRedSocial {
	public String nombre; // COMPOSICIÓN
	public int seguidores;
	
	/** Crea un usuario con nombre vacío ("") y 0 seguidores
	 */
	public UsuarioRedSocial() {
		nombre = "";
	}
	
	// 1.- Crea memoria (implícito)
	// 2.- Inicialización de atributos (por defecto o explícito arriba)
	// 3.- Ejecutar código de contructor
	// 4.- Devuelve referencia del objeto creado

	/** Crea un nuevo usuario
	 * @param nombre	Nombre del nuevo usuario
	 * @param seguidores	Nº de seguidores de ese usuario
	 */
	public UsuarioRedSocial( String nombre, int seguidores ) {
		this.nombre = nombre;
		this.seguidores = seguidores;
	}
	
	/** Crea un nuevo usuario con cero seguidores
	 * @param nombre	Nombre de ese usuario
	 */
	public UsuarioRedSocial( String nombre ) {
		this.nombre = nombre;
		// seguidores = 0;
	}
	
	public String toString() {
		return this.nombre + " " + this.seguidores;
	}
	
	public boolean esMenosFamosoQue( UsuarioRedSocial usuario2 ) {
		return this.seguidores < usuario2.seguidores;
	}
	
	public static boolean comparaFama( UsuarioRedSocial u1, UsuarioRedSocial u2 ) {
		return u1.seguidores < u2.seguidores;
	}
}
