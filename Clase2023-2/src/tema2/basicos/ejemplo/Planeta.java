package tema2.basicos.ejemplo;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Clase de ejemplo de herencia - permite crear planetas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Planeta extends Esfera {
	
	// Sin escribirlos, estamos heredando los atributos (aunque sean private):
	// double xCentro;
	// double yCentro;
	// double radio;
	
	// Y también los métodos:  (EXCEPTO LOS CONSTRUCTORES, que nunca se heredan)
	// getxCentro, getyCentro...  etc.
	
	// Tampoco se heredan los métodos y atributos static

	// Y ahora añadimos lo nuevo de esta clase. Atributos:
	
	private String nombre;
	
	// Atributos para rotación de planeta y animación de giro completo
	private double rotacion = 0;                // Rotación por defecto
	private long milisParaGiroCompleto = 5000;  // Valor por defecto
	
	// Constructores (pueden ser varios):
	
	/** Crea un nuevo planeta
	 * @param nombre	Nombre
	 * @param xCentro	Coordenada x de su centro en píxeles
	 * @param yCentro	Coordenada y de su centro en píxeles
	 * @param radio	Radio en píxeles
	 */
	public Planeta( String nombre, double xCentro, double yCentro, double radio) {
		super( xCentro, yCentro, radio );   // delegamos responsabilidad de inicializar atributos al constructor de la clase madre
// 		this.radio = radio;     // ¿Por qué no funciona?  private
//		setxCentro( xCentro );  // Podríamos modificar los atributos aquí aunque fueran private
//		this.yCentro = yCentro; // Podríamos hacerlo también si fueran protected o public o sin indicación (mismo paquete)
		// System.out.println( "Construyendo Planeta"); // Para ver el orden en el que se ejecutan los constructores
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override  // ¿Y esta anotación? - Le da información al compilador (lo que viene es un método REDEFINIDO)
	public String toString() {
		return "Planeta " + nombre + super.toString();  // Reutilización de método madre!  No es obligatoria, solo si nos va bien
	}
	
	// Otro ejemplo de método redefinido
	@Override
	public void setRadio(double radio) {  // Método polimórfico
		if (radio < 5) {
			System.err.println( "Error radio " + radio + " menor que 5" );
		} else {
			super.setRadio(radio);  // Otra reutilización
		}
	}
	
	// Métodos de dibujado y rotación
	
	@Override
	public void dibujar(VentanaGrafica v) {
		String fichero = "planet.png";
		if (nombre.equalsIgnoreCase("Tierra") || nombre.equalsIgnoreCase("Earth")) {
			fichero = "earth.png";
		}
		v.dibujaImagen( fichero, getxCentro(), getyCentro(), (int)(getRadio()*2), (int)(getRadio()*2), 1.0, rotacion, 1.0f );
		super.dibujar(v);
	}

	public double getRotacion() {
		return rotacion;
	}
	
	/** Aplica una rotación al planeta
	 * @param angulo	Ángulo a rotar, en radianes
	 */
	public void rotar( double angulo ) {
		rotacion += angulo;
	}

	/** Configura el tiempo de rotación del planeta para la animación
	 * @param milis	Milisegundos que tarda el planeta en dar un giro completo
	 */
	public void setTiempoRotacion( long milis ) {
		milisParaGiroCompleto = milis;
	}
	
	/** Realiza la animación del planeta
	 * @param milis	Milisegundos transcurridos desde la última animación
	 */
	public void animar( long milis ) {
		rotar( Math.PI * 2 * milis / milisParaGiroCompleto ); // Ojo a poner el double el primero que si no se opera en longs
	}
	
}
