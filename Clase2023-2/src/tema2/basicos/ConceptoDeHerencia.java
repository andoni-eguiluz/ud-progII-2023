package tema2.basicos;

/** Clase de ejemplo del concepto de herencia en Java
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class ConceptoDeHerencia {
	/** Método principal de prueba de clases heredadas
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		Youtuber ibai = new Youtuber( "Ibai", "xxx" );  // Objeto de clase padre
		ibai.setBeneficios( 1000000.50 );
		System.out.println( ibai.toString() + " - " + ibai.calcImpuestos() );
		YoutuberAndorra rubius = new YoutuberAndorra( "El Rubius", "xxxxx", "yyyy" );  // Objeto de clase hija
		// rubius.setNombre( "El Rubius" );  // No hace falta cuando el constructor permite ya inicializar los datos
		rubius.setBeneficios( 2000000.0 );
		rubius.setDiasEnEnpanya( 20 );
		System.out.println( rubius + " - " + rubius.calcImpuestos() );
		Persona p;
		p = rubius;
		System.out.println( p.toString() + " -> " + p.calcImpuestos() );
		System.out.println( rubius.getDirAndorra() );
		p = ibai;
		System.out.println( p.calcImpuestos() );
		
		Persona[] contribuyentes = new Persona[2];
		contribuyentes[0] = ibai;
		contribuyentes[1] = rubius;
		for (int i=0; i<contribuyentes.length; i++) {
			System.out.print( contribuyentes[i].toString() + " - " + contribuyentes[i].calcImpuestos() );
			if (contribuyentes[i] instanceof YoutuberAndorra) {
				YoutuberAndorra yta = (YoutuberAndorra) contribuyentes[i];
				System.out.println( "\t" + yta.getDirAndorra() + "*" );
			} else {
				System.out.println();
			}
		}
	}
}

abstract class Persona {
	protected String nombre;
	protected String direccion;
	public Persona(String nombre, String direccion) {
		// super();
		this.nombre = nombre;
		this.direccion = direccion;
	}
	/** Calcula los impuestos correspondientes a la persona
	 * @return	Cálculo de impuestos en euros
	 */
	public abstract double calcImpuestos();
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}

/** Clase padre - ejemplo de herencia
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
class Youtuber extends Persona {   // extends Object  (por defecto)
	protected double beneficios;	
	public Youtuber(String nombre, String direccion) {
		super( nombre, direccion );  // Llama al constructor de la clase padre (Object) - lo hace Java por defecto si no se explicita
		// this.beneficios = 0.0;  // No hace falta porque los atributos double se inicializan a 0.0
	}
	@Override
	public double calcImpuestos() {
		return 0.5 * beneficios;
	}
	public double getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(double beneficios) {
		this.beneficios = beneficios;
	}
	@Override
	public String toString() {
		return nombre + " - dirección " + direccion;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}

/** Clase hija - ejemplo de herencia
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
class YoutuberAndorra extends Youtuber {
	// private String nombre;  // La herencia copia automáticamente los atributos
	// private String direccion;
	// private double beneficios;
	private int diasEnEnpanya;
	private String dirAndorra;
	/** Crea un nuevo objeto con beneficios a cero y con 0 días de residencia en España
	 * @param nombre	Nombre
	 * @param direccion	Dirección en España
	 * @param dirAndorra	Dirección en Andorra
	 */
	public YoutuberAndorra( String nombre, String direccion, String dirAndorra ) {
		super(nombre,direccion);  // Llamada al constructor de la clase padre (inicializa los atributos que se heredan)
		// this.nombre = nombre;  // No hace falta porque ya lo hace el constructor de la clase padre - también con direccion y beneficios
		// Inicialización de atributos propios de la clase hija:
		this.dirAndorra = dirAndorra;
		// this.diasEnEspanya = 0;  // No hace falta porque los atributos int se inicializan a 0
	}
	public int getDiasEnEnpanya() {
		return diasEnEnpanya;
	}
	public void setDiasEnEnpanya(int diasEnEnpanya) {
		this.diasEnEnpanya = diasEnEnpanya;
	}
	public String getDirAndorra() {
		return dirAndorra;
	}
	public void setDirAndorra(String dirAndorra) {
		this.dirAndorra = dirAndorra;
	}
	// Redefinición de método heredado (se hereda la funcionalidad, pero se diferencia cómo se comporta esa funcionalidad)
	@Override
	public double calcImpuestos() {
		if (diasEnEnpanya>=183) {
			return super.calcImpuestos();
		} else {
			return 0;
		}
	}
	// Otra redefinición de método heredado:
	@Override
	public String toString() {
		return super.toString() + " (" + this.diasEnEnpanya + ")";
	}	
}