package tema1.resueltos;

/** Clase que define usuarios de redes sociales (ejercicio 1.0.i)
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class UsuarioRedSocial {

	// ATRIBUTOS Y MÉTODOS STATIC (de clase)
	
	// Posibles atributos static (de clase, solo una vez)
	public static int numUsuarios = 0;
	public static void recalcularUsuarios() {
		//...
		// this.nombre = "";  No existe this - no se puede usar en un método static
	}

	
	// ATRIBUTOS Y MÉTODOS NO STATIC (de objeto)
	
	private String nombre;  // COMPOSICIÓN
	private int seguidores;
	// ... fechaAlta, direccion, correoElectronico...
	
	// Constructores (pueden estar sobrecargados)
	
	// Labor del constructor:
	// 1.- Crea memoria (implícito)
	// 2.- Inicialización de atributos (por defecto o explícito arriba)
	// 3.- Ejecutar código de contructor
	// 4.- Devuelve referencia del objeto creado

	/** Crea un usuario nuevo con nombre vacío ("") y 0 seguidores
	 */
	public UsuarioRedSocial() {
		nombre = "";
		UsuarioRedSocial.numUsuarios++;
	}
	
	/** Crea un usuario nuevo
	 * @param nombre	Nombre del usuario
	 * @param seguidores	Número de seguidores (en miles)
	 */
	public UsuarioRedSocial( String nombre, int seguidores ) {
		this.nombre = nombre;
		this.seguidores = seguidores;
	}
	
	/** Crea un usuario nuevo con 0 seguidores
	 * @param nombre	Nombre de este usuario
	 */
	public UsuarioRedSocial( String nombre ) {
		this.nombre = nombre;
		this.seguidores = 0;  // podría no ponerse porque el atributo por defecto se inicializa a 0
	}
	
	/** Devuelve el nombre
	 * @return	Nombre actual del usuario
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/** Devuelve los seguidores
	 * @return	Número actual de seguidores, en miles
	 */
	public int getSeguidores() {
		return this.seguidores;
	}
	
	/** Modifica los seguidores. Indica un error y no los modifica si son negativos
	 * @param seguidores	Nuevo número de seguidores del usuario, en miles (número no negativo)
	 */
	public void setSeguidores(int seguidores) {
		if (seguidores < 0) {
			System.err.println( "Error: los seguidores deben ser positivos o cero" );
		} else {
			this.seguidores = seguidores;
		}
	}

	/** Convierte a String el usuario
	 * @return	String con nombre de usuario, un tabulador y número de seguidores
	 */
	public String toString() {
		return this.nombre + "\t" + seguidores;  // puede ponerse this.seguidores o seguidores, da igual
	}
	
	/** Saca a consola la información del usuario (nombre y seguidores)
	 */
	public void sacarAConsola() {
		System.out.println( toString() );  // se puede llamar a unos métodos desde otros  (reutilizar)
	}
	
	/** Compara el usuario para ver si la fama (número de seguidores) es menor o mayor
	 * @param usuario2	Otro usuario
	 * @return	true si el usuario en curso tiene menos seguidores que usuario2, false en caso contrario
	 */
	public boolean esMenosFamosoQue( UsuarioRedSocial usuario2 ) {
		return this.seguidores < usuario2.seguidores;
	}
	
}