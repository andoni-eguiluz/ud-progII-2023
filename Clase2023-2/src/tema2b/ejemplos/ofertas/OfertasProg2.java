package tema2b.ejemplos.ofertas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import utils.ventanas.ventanaConsola.VentanaConsolaConBotones;

/** Clase contenedora del ejemplo de Ofertas
 * Contiene la lista de todas las ofertas en curso
 * y almacena también la lista de todas las ofertas compradas
 * Permite E/S básica y operatividad esencial sobre las ofertas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class OfertasProg2 {
	// Ofertas en curso
	ArrayList<Oferta> listaOfertas;
	// Mapa de compras realizadas por usuario (clave usuario, valor lista de compras de ese usuario)
	HashMap<String,ArrayList<Oferta>> mapaCompras;

	/** Crea un objeto de ofertas con los datos de ofertas y compras vacíos
	 */
	public OfertasProg2() {
		listaOfertas = new ArrayList<>();
		mapaCompras = new HashMap<>();
	}

	/** Devuelve la lista de ofertas disponibles
	 * @return	Ofertas en curso
	 */
	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	/** Añade una compra al sistema
	 * @param usuario	Nombre de usuario que compra
	 * @param ofertaComprada	Oferta comprada
	 */
	public void addCompra( String usuario, Oferta ofertaComprada ) {
		if (!mapaCompras.containsKey( usuario )) {
			mapaCompras.put( usuario, new ArrayList<>() );
		}
		mapaCompras.get( usuario ).add( ofertaComprada );  // Añade la compra a la lista de compras del usuario
	}

	/** Muestra la información histórica de ventas
	 * @param ventana	Ventana en la que mostrar la información
	 */
	public void muestraInfo( VentanaConsolaConBotones ventana ) {
		ventana.println();
		ventana.println( "Resumen de ventas realizadas:" );
		for (String usu : mapaCompras.keySet()) {
			ventana.println( "  Usuario " + usu + ": " + mapaCompras.get( usu ) );
		}
		ventana.println();
	}

	/** Guarda los datos para poder restaurar posteriormente el sistema
	 * @param nomFichero	Nombre de fichero de guardado
	 */
	public void guardarDatos( String nomFichero ) {
		// Guardar datos a un fichero : lista y mapa
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( nomFichero ) );
			oos.writeObject( listaOfertas );
			oos.writeObject( mapaCompras );
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Restaura el sistema con los datos existentes en un fichero
	 * @param nomFichero	Nombre de fichero de carga
	 */
	@SuppressWarnings("unchecked")  // Esto suprime los warnings (lógicos si no comprobamos el tipo al hacer los casts - forzamos que el fichero tenga lo que esperamos que tenga)
	public void cargarDatos( String nomFichero ) {
		// Cargar datos del fichero a las estructuras de memoria
		try {
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream( nomFichero ) );
			listaOfertas = (ArrayList<Oferta>) ois.readObject();
			mapaCompras = (HashMap<String,ArrayList<Oferta>>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
