package tema4.ejemplos.tiempoProg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/** Clase contenedora de tiempos de programación, con capacidad de guardado-carga de fichero
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GestorTiemposProg {
	
	private ArrayList<TiempoProg> listaTiempos;
	
	/** Crea un nuevo gestor contenedor de tiempos de programación, vacío
	 */
	public GestorTiemposProg() {
		listaTiempos = new ArrayList<>();
	}

	/** Devuelve la lista de tiempos almacenada en el gestor
	 * Si se modifica esta lista, se modificará la lista contenida en el gestor
	 * @return	Lista de tiempos de programación
	 */
	public ArrayList<TiempoProg> getListaTiempos() {
		return listaTiempos;
		// Si se quisiera que no se pueda modificar la lista externamente, aquí se podría devolver una copia:
		// return (ArrayList<TiempoProg>) listaTiempos.clone();
	}
	
	/** Añade tiempo al gestor
	 * @param tp	Nuevo tiempo de programación a añadir
	 */
	public void addTiempo( TiempoProg tp ) {
		listaTiempos.add( tp );
	}
	
	/** Guarda los datos del gestor a un fichero.
	 * El fichero puede cargarse posteriormente con el método {@link #cargarFichero(boolean, String)}
	 * @param binario	Si es true se guarda en binario, false se guarda en modo texto
	 * @param nomFichero	Nombre-ruta del fichero
	 * @throws IOException	Error de entrada-salida al guardar el fichero (no se ha generado correctamente)
	 */
	public void guardarFichero( boolean binario, String nomFichero ) throws IOException {
		if (binario) {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(nomFichero));
			oos.writeObject( listaTiempos );
			oos.close();
		} else {
			// try (PrintStream ps = new PrintStream( nomFichero )) {
			File file = new File( nomFichero );
			if (file.exists()) {
				nomFichero = nomFichero + ".copia";
			}
			PrintStream ps = new PrintStream( nomFichero );
			// ps.println( listaTiempos.toString() );  // Esto guardaría los datos, pero en un formato poco cómodo para luego cargar
			// Guardamos los datos en un formato cómodo para la carga:
			for (TiempoProg tp : listaTiempos) {
				ps.println( tp.aLinea() );
			}
			ps.close();
		}
	}
	
	/** Carga los datos del gestor desde un fichero, eliminando los datos antiguos que pudiera haber.
	 * El fichero debería generarse con el método {@link #guardarFichero(boolean, String)}
	 * @param binario	true si es binario, false si es de texto
	 * @param nomFichero	Nombre-ruta del fichero
	 * @throws IOException	Error de entrada-salida al cargar el fichero
	 * @throws ClassNotFoundException	Error de carga de datos no compatibles del fichero
	 * @throws ClassCastException	Error de carga de datos diferentes a los necesitados
	 */
	@SuppressWarnings("unchecked")
	public void cargarFichero( boolean binario, String nomFichero ) throws IOException, ClassNotFoundException, ClassCastException {
		if (binario) {
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream(nomFichero));
			listaTiempos = (ArrayList<TiempoProg>) ois.readObject();  // Carga el arraylist desde el fichero binario
			ois.close();
		} else {
			Scanner scanner = new Scanner( new File( nomFichero ) );
			listaTiempos.clear();  // Reinicia el arraylist para cargarlo
			int numLinea = 1;
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				TiempoProg tp = TiempoProg.leerLinea( linea );
				if (tp==null) {
					System.out.println( "Error en lectura en la línea " + numLinea + ": " + linea );
				} else {
					addTiempo( tp );
				}
				numLinea++;
			}
		}
	}
	
}
