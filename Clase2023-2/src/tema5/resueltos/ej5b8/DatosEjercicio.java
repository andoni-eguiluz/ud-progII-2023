package tema5.resueltos.ej5b8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/** Clase contenedora de datos de ejercicio
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class DatosEjercicio {
	
	private HashMap<DiaSemana,String> mapaDatosEj;
	private HashMap<DiaSemana,String> mapaHoraIni;
	private HashMap<DiaSemana,String> mapaHoraFin;
	
	public DatosEjercicio() {
		mapaDatosEj = new HashMap<>();
		mapaHoraIni = new HashMap<>();
		mapaHoraFin = new HashMap<>();
	}
	
	public String getDatosEjercicio( DiaSemana dia ) {
		return mapaDatosEj.get( dia );
	}
	
	public String getHoraIni( DiaSemana dia ) {
		return mapaHoraIni.get( dia );
	}
	
	public String getHoraFin( DiaSemana dia ) {
		return mapaDatosEj.get( dia );
	}
	
	public void setDatosEjercicio( DiaSemana dia, String datos ) {
		mapaDatosEj.put( dia, datos );
	}
	
	public void setHoraIni( DiaSemana dia, String hora ) {
		mapaHoraIni.put( dia, hora );
	}
	
	public void setHoraFin( DiaSemana dia, String hora ) {
		mapaHoraFin.put( dia, hora );
	}
	
	public void guardaEnFichero() {
		try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "ejercicio.dat" ) )) {
			oos.writeObject( mapaDatosEj );
			oos.writeObject( mapaHoraIni );
			oos.writeObject( mapaHoraFin );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cargaDeFichero() {
		try (ObjectInputStream ois = new ObjectInputStream( new FileInputStream( "ejercicio.dat" ) )) {
			mapaDatosEj = (HashMap<DiaSemana,String>) ois.readObject();
			mapaHoraIni = (HashMap<DiaSemana,String>) ois.readObject();
			mapaHoraFin = (HashMap<DiaSemana,String>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
