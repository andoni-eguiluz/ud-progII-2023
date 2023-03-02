package tema1.resueltos;

public class UsuarioRedSocial {
	private String nombre; // COMPOSICIÓN
	private int seguidores;
	
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
	
	/** Cambia el número de seguidores.
	 * Produce un error en consola si es negativo y no se cambia
	 * @param seguidores	Nuevo número de seguidores, en miles
	 */
	public void setSeguidores( int seguidores ) {
		if (seguidores < 0) {
			System.err.println( "Error: no pueden ser negativos los seguidores" );
		} else {
			this.seguidores = seguidores;
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	/** Devuelve los seguidores
	 * @return	Seguidores, en miles
	 */
	public int getSeguidores() {
		return seguidores;
	}

	public String toString() {
		return this.nombre + " " + this.seguidores;
	}
	
	/** Compara el usuario para ver si la fama (número de seguidores) es menor o mayor
	 * @param usuario2	Otro usuario
	 * @return	true si el usuario en curso tiene menos seguidores que usuario2, false en caso contrario
	 */
	public boolean esMenosFamosoQue( UsuarioRedSocial usuario2 ) {
		return this.seguidores < usuario2.seguidores;
	}
	
	/** Compara dos usuarios entre sí en función de su fama (número de seguidores)
	 * @param u1	usuario 1
	 * @param u2	usuario 2
	 * @return	true si el usuario 1 tiene menos seguidores que el usuario 2, false en caso contrario
	 */
	public static boolean comparaFama( UsuarioRedSocial u1, UsuarioRedSocial u2 ) {
		return u1.seguidores < u2.seguidores;
	}
}
