package tema4.ejemplos.tiempoProg;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Clase para registro de tiempo dedicado a la programación
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class TiempoProg 
implements Comparable<TiempoProg>  // Si se quiere utilizar TiempoProg en un TreeSet o como clave de un TreeMap
, Serializable {  // Para serializar TiempoProg
	
	private static final long serialVersionUID = 1L;  // Evita el warning de Serializable
		// Java recomienda que al hacer una clase serializable se defina esta constante 
		// que funciona como un número de "versión" de la clase. Si se hacen cambios en la clase, 
		// se cambia el número de versión (y los ficheros antiguos no podrán ser leídos) 
	
	/** Formateador de fecha día/mes/año hora:minutos
	 */
	public static final SimpleDateFormat FORMATEADOR_FECHA_DMAHM = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
	private Date fecha;  // Fecha-hora en la que empezó el tiempo de programación
	private int tiempo;  // Minutos de duración del trabajo de programación
	
	/** Crea un nuevo objeto de tiempo de programación
	 * @param fecha	Fecha-hora de inicio del trabajo
	 * @param tiempo	Número de minutos dedicados (positivos, mayor que cero)
	 */
	public TiempoProg(Date fecha, int tiempo) {
		super();
		this.fecha = fecha;
		this.tiempo = tiempo;
	}

	/** Devuelve la fecha-hora
	 * @return	Fecha-hora de inicio de tiempo de programación
	 */
	public Date getFecha() {
		return fecha;
	}

	/** Modifica la fecha-hora
	 * @param fecha	Nueva fecha-hora de inicio de tiempo de programación
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/** Devuelve el tiempo dedicado
	 * @return	Minutos dedicados a la programación
	 */
	public int getTiempo() {
		return tiempo;
	}

	/** Modifica el tiempo dedicado
	 * @param tiempo	Nuevo número de minutos dedicados a la programación (debe ser mayor que cero)
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	@Override
	public String toString() {
		return fecha + " : " + tiempo;
	}
	
	/** Convierte los datos de tiempo de programación en una línea de texto, válida para ser guardada en fichero
	 * @return	Datos en formato dd/mm/aaaa hh:mm tab tiempo
	 */
	public String aLinea() {
		return FORMATEADOR_FECHA_DMAHM.format(fecha) + "\t" + tiempo;
	}
	
	/** Convierte los datos de una línea de texto en un objeto de tiempo de programación
	 * @param linea	Texto de los datos en formato dd/mm/aaaa hh:mm tab tiempo
	 * @return	Nuevo objeto de tiempo de programación, null si hay cualquier error en la conversión del texto
	 */
	public static TiempoProg leerLinea( String linea ) {
		TiempoProg ret = new TiempoProg( null, 0 );
		String[] trozos = linea.split("\t");
		try {
			ret.fecha = FORMATEADOR_FECHA_DMAHM.parse( trozos[0] );
			ret.tiempo = Integer.parseInt( trozos[1] );
		} catch (Exception e) {
			return null;
		}
		return ret;
	}
	
	@Override
	public int hashCode() {
		return fecha.hashCode() + tiempo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TiempoProg) {
			TiempoProg tp2 = (TiempoProg) obj;
			return fecha.equals(tp2.fecha) && tiempo==tp2.tiempo;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(TiempoProg o) {
		int comp = this.fecha.compareTo( o.fecha );
		if (comp==0) {
			comp = this.tiempo - o.tiempo;
		}
		return comp;
	}
	
}
