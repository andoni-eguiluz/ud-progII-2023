package tema4.ejemplos.tiempoProg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/** Programa interactivo de gestión de tiempos de programación
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class MainTiempoProg {
	private static GestorTiemposProg gestor;
	public static final SimpleDateFormat FORMATO_DMYHM = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );

	/** Programa principal de la aplicación, interactivo para editar y cargar-guardar tiempos en fichero
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		gestor = new GestorTiemposProg();
		menu();
	}
	
	// Menú principal
	private static void menu() {
		String[] opciones = {
			"Añadir tiempo", "Calcular tiempo total", "Guardar fichero bin", "Cargar fichero bin",
			"Guardar fichero texto", "Cargar fichero texto", "Fin"
		};
		String respuestaString;
		do {
			Object respuesta = JOptionPane.showInputDialog( null, "¿Qué quieres hacer?", "Menú principal", JOptionPane.QUESTION_MESSAGE, null, 
					opciones, "Añadir tiempo" );
			respuestaString = (String) respuesta;
			// System.out.println( respuesta );
			if ("Añadir tiempo".equals(respuestaString)) {
				anyadirTiempo();
			} else if ("Calcular tiempo total".equals(respuestaString)) {
				calcularTiempoTotal();
			} else if ("Guardar fichero bin".equals(respuestaString)) {
				guardarFichero( true );
			} else if ("Cargar fichero bin".equals(respuestaString)) {
				cargarFichero( true );
			} else if ("Guardar fichero texto".equals(respuestaString)) {
				guardarFichero( false );
			} else if ("Cargar fichero texto".equals(respuestaString)) {
				cargarFichero( false );
			}
		} while (!"Fin".equals(respuestaString) && respuestaString!=null);
	}
	
	// Cálculo de tiempos
	private static void calcularTiempoTotal() {
		int suma = 0;
		for (TiempoProg tp : gestor.getListaTiempos()) {
			suma += tp.getTiempo();
		}
		System.out.println( "Tiempo total: " + suma );
	}
	
	// Añadir tiempo
	private static void anyadirTiempo() {
		String resp = JOptionPane.showInputDialog( "Introduce fecha en la que has programado (formato dd/mm/aaaa hh:mm)", 
				FORMATO_DMYHM.format(new Date()) );
		try {
			Date fecha = FORMATO_DMYHM.parse( resp );
			if (resp==null | resp.isEmpty()) {  // El usuario no quiere introducir fecha
				return;
			}
			resp = JOptionPane.showInputDialog( "Introduce minutos programando:" );
			if (resp==null | resp.isEmpty()) {  // El usuario no quiere introducir minutos
				return;
			}
			int tiempo = Integer.parseInt( resp );
			TiempoProg tp = new TiempoProg( fecha, tiempo );
			gestor.addTiempo( tp );
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog( null, "Error en entrada. Los minutos deben ser enteros y mayores que cero" );
		} catch (ParseException e) {
			JOptionPane.showMessageDialog( null, "Error en entrada. Debe tener el formato dd/mm/aaaa hh:mm" );
		}
	}
	
	// Guardar tiempos a fichero
	private static void guardarFichero( boolean binario ) {
		String nombreFic = JOptionPane.showInputDialog( "Introduce nombre de fichero para guardar:", 
				binario ? "tiempos.dat" : "tiempos.txt" );
		try {
			if (nombreFic!=null && !nombreFic.isEmpty()) {
				gestor.guardarFichero( binario, nombreFic );
			}
		} catch (IOException e) {
			System.out.println( "Error en la creación del fichero de salida " + nombreFic );
		}
	}
	
	// Cargar tiempos de fichero
	private static void cargarFichero( boolean binario ) {
		String nombreFic = JOptionPane.showInputDialog( "Introduce nombre de fichero para cargar:", 
				binario ? "tiempos.dat" : "tiempos.txt" );
		if (nombreFic!=null && !nombreFic.isEmpty()) {
			try {
				gestor.cargarFichero( binario, nombreFic );
				System.out.println( "Cargados datos de fichero: " + gestor.getListaTiempos().size() + " registros cargados." );
			} catch (FileNotFoundException e) {
				System.out.println( "El fichero que has indicado no existe: " + nombreFic );
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println( "Error en la lectura del fichero " + nombreFic + ". No se ha podido cargar" );
			}
		}
	}
	
}
