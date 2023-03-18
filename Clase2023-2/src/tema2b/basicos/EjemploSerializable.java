package tema2b.basicos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/** Ejemplo de uso de Serializable para guardar datos en ficheros
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EjemploSerializable {
	public static void main(String[] args) throws Exception {
		ArrayList<MiDato> l = new ArrayList<>();
		l.add( new MiDato( "Andoni", 51 ) );
		l.add( new MiDato( "Ana", 53 ) );
		l.add( new MiDato( "Iñaki", 38 ) );
		l.add( new MiDato( "Blanca", 42 ) );
		escribeAFichero( l );
		System.out.println( "Escrito a fichero: " + l );
		l = leeDeFichero();
		System.out.println( "Leído de fichero: " + l );
	}
	
	// Método para escribir datos a ficheros
	private static void escribeAFichero( ArrayList<MiDato> l ) throws Exception {
		FileOutputStream fout = new FileOutputStream("test.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject( l );
		oos.close();
	}

	// Método para leer datos de ficheros
	private static ArrayList<MiDato> leeDeFichero() throws Exception {
		FileInputStream fin = new FileInputStream("test.dat");
		ObjectInputStream ois = new ObjectInputStream(fin);
		ArrayList<MiDato> l = (ArrayList<MiDato>) ois.readObject();	
		ois.close();
		return l;
	}
}

class MiDato implements Serializable {
	private static final long serialVersionUID = 1L;  // Recomendable cuando se usa Serializable
	private String nombre;
	private int valor;
	public MiDato(String nombre, int valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	@Override
	public String toString() {
		return nombre + " (" + valor + ")";
	}
}