package tema4.ejemploFicherosUsuario;

import java.io.*;
import java.util.ArrayList;

/** Ejemplo de ficheros con la clase Usuario.
 * Guardado y cargado de datos de esta clase con varias opciones de ficheros distintas
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class EjemploFicherosUsuario {
	
	/** Lee los usuarios de un fichero de datos separado por comas y los devuelve
	 * @param nomFic	Nombre de fichero
	 * @return	Lista de usuarios del fichero, sólo formada por los usuarios leídos correctamente
	 */
	public static ArrayList<Usuario> leerDeFicheroConComas( String nomFic ) {
		ArrayList<Usuario> ret = new ArrayList<Usuario>();
		BufferedReader brFich = null;
		try {
			brFich = new BufferedReader( new
					InputStreamReader( new FileInputStream(nomFic) ) );
			String linea = brFich.readLine();
			while (linea != null) {
				// Proceso de línea
				Usuario u = Usuario.crearDeLinea(linea);
				if (u!=null) ret.add( u );
				linea = brFich.readLine();
			}
		} catch (Exception e) {  // FileNotFound, IO
			e.printStackTrace();
		} finally {
			if (brFich!=null)
				try {
					brFich.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	/** Escribe los usuarios de una lista de usuarios a un fichero de texto,
	 * en formato de datos de usuario separado por comas (una línea por usuario)
	 * @param nomFic	Nombre de fichero
	 * @param l	Lista de usuarios a escribir al fichero
	 */
	public static void escribirAFicheroConComas( String nomFic, ArrayList<Usuario> l ) {
		PrintStream fich = null;
		try {
			fich = new PrintStream(new FileOutputStream(nomFic));
			for (Usuario u : l) {
				fich.println( u.aLinea() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fich!=null) fich.close();
		}
	}

	/** Lee los usuarios de un fichero de datos binario serializado de usuarios
	 * @param nomFic	Nombre de fichero
	 * @return	Lista de usuarios del fichero, sólo formada por los usuarios leídos correctamente
	 */
	public static ArrayList<Usuario> leerDeFicheroSerializado( String nomFic ) {
		ArrayList<Usuario> ret = new ArrayList<Usuario>();
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream( new FileInputStream(nomFic) );
			while (true) {
				// Lectura hasta final de fichero (excepción)
				// Tb a veces se graba un null al final y se usa ese null para acabar
				Usuario u = (Usuario) ois.readObject();
				ret.add( u );
			}
		} catch (EOFException e) {  // FileNotFound, IO, EOF, classcast
			// Ok - final de bucle
		} catch (Exception e) {  // FileNotFound, IO, EOF, classcast
			e.printStackTrace();
		} finally {
			if (ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}
	
	/** Escribe los usuarios de una lista de usuarios a un fichero binario,
	 * serializado por cada usuario
	 * @param nomFic	Nombre de fichero
	 * @param l	Lista de usuarios a escribir al fichero
	 */
	public static void escribirAFicheroSerializado( String nomFic, ArrayList<Usuario> l ) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(nomFic));
			for (Usuario u : l) {
				oos.writeObject( u );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/** Lee los usuarios de un fichero de datos de usuarios con tags, y los devuelve
	 * @param nomFic	Nombre de fichero
	 * @return	Lista de usuarios del fichero, sólo formada por los usuarios leídos correctamente
	 */
	public static ArrayList<Usuario> leerDeFicheroConTags( String nomFic ) {
		ArrayList<Usuario> ret = new ArrayList<Usuario>();
		BufferedReader brFich = null;
		try {
			brFich = new BufferedReader( new
					InputStreamReader( new FileInputStream(nomFic) ) );
			String linea = brFich.readLine();
			while (linea != null) {
				// Proceso de línea
				if ("[USUARIO]".equals(linea)) {
					Usuario u = Usuario.crearDeLineasConTags( brFich );
					if (u!=null) ret.add( u );
				}
				linea = brFich.readLine();
			}
		} catch (Exception e) {  // FileNotFound, IO
			e.printStackTrace();
		} finally {
			if (brFich!=null)
				try {
					brFich.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return ret;
	}

	/** Escribe los usuarios de una lista de usuarios a un fichero de texto,
	 * en formato de usuario con tags:<p>
	 * [USUARIO]<p>
	 * [nick] nick\n<p>
	 * [password] password\n<p>
	 * [nombre] nombre\n<p>
	 * [apellidos] apellidos\n<p>
	 * [telefono] telefono\n<p>
	 * [fechaUltimoLogin] fechaUltimoLogin(msgs.)\n<p>
	 * [tipo] tipo\n<p>
	 * [emails] email1,email2...\n<p>
	 * [FINUSUARIO]<p>
	 * ... resto de usuarios con el mismo formato
	 * @param nomFic	Nombre de fichero
	 * @param l	Lista de usuarios a escribir al fichero
	 */
	public static void escribirAFicheroConTags( String nomFic, ArrayList<Usuario> l ) {
		PrintStream fich = null;
		try {
			fich = new PrintStream(new FileOutputStream(nomFic));
			for (Usuario u : l) {
				fich.println( "[USUARIO]" );
				fich.println( u.aLineasConTags() );
				fich.println( "[FINUSUARIO]" );
			}
			fich.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fich!=null) fich.close();
		}
	}

	public static void main(String[] args) {
		ArrayList<Usuario> pruebaUsuarios = new ArrayList<>();
		ArrayList<String> emails = new ArrayList<>();
		emails.add( "jodiefoster@gmail.com" );
		pruebaUsuarios.add( new Usuario( "jodieF", "j", "Jodie", "Foster", 666555444, TipoUsuario.Cliente, emails ) );
		emails = new ArrayList<>();
		emails.add( "kevinspacey@gmail.com" );
		emails.add( "houseofcardsfan@gmail.com" );
		pruebaUsuarios.add( new Usuario( "kevinS", "k", "Kevin", "Spa,cey", 111222333, TipoUsuario.Admin, emails ) );
		escribirAFicheroConComas( "usuarios-comas.txt", pruebaUsuarios );
		escribirAFicheroConTags( "usuarios-tags.txt", pruebaUsuarios );
		escribirAFicheroSerializado( "usuarios-ser.dat", pruebaUsuarios );
		System.out.println( "Prueba de lectura:");
		System.out.println();
		System.out.println( "Lista original:" + pruebaUsuarios );
		System.out.println();
		System.out.println( "Leyendo de serializado...");
		pruebaUsuarios = leerDeFicheroSerializado("usuarios-ser.dat");
		System.out.println( "Lista leída:" + pruebaUsuarios );
		System.out.println();
		System.out.println( "Leyendo de tags...");
		pruebaUsuarios = leerDeFicheroConTags("usuarios-tags.txt");
		System.out.println( "Lista leída:" + pruebaUsuarios );
		System.out.println();
		System.out.println( "Leyendo de comas...");
		pruebaUsuarios = leerDeFicheroConComas("usuarios-comas.txt");
		System.out.println( "Lista leída:" + pruebaUsuarios );
	}
	
}
