package tema3.resueltos.ejercicios3b1_2;

import java.util.*;

/** Gestión de usuarios con scores
 * Los usuarios se identifican con nicks únicos
 * y da igual minúsculas que mayúsculas
 * (independiente del caso, o de la capitalización)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class HighScores {
	private TreeMap<String,Integer> mapaPunts;
	
	/** Construye un nuevo espacio de high scores
	 * vacío
	 */
	public HighScores() {
		mapaPunts = new TreeMap<String,Integer>();
	}
	
	/** Devuelve los usuarios
	 * @return	Conjunto de usuarios (sin repetición)
	 */
	public Set<String> getUsuarios() {
		return mapaPunts.keySet();
	}
	
	/** Añade al espacio de high scores un usuario nuevo
	 * sin puntuación. Si el usuario ya existía
	 * lo dejo como estaba
	 * @param nick	Nick del usuario a añadir
	 */
	public void addUsuario( String nick ) {
		nick = nick.toLowerCase();
		if (!mapaPunts.containsKey( nick )) {
		// if (mapaPunts.get(nick)==null) {
			mapaPunts.put( nick, null );  // Puntuación null = sin puntuación
		}
	}

	/** Añade al espacio de high scores un usuario nuevo
	 * o existente, con su puntuación. Si ya tenía
	 * una puntuación mayor que esta, la deja
	 * @param nick	Nick del usuario a añadir/modificar
	 * @param puntuacion	Puntuación de ese usuario
	 */
	public void addPuntuacion( String nick, int puntuacion ) {
		nick = nick.toLowerCase();
		if (!mapaPunts.containsKey( nick )) {
			mapaPunts.put( nick, new Integer(puntuacion) );
		} else {
			Integer puntAnt = mapaPunts.get( nick );
			if (puntAnt==null || puntAnt < puntuacion) {
				mapaPunts.remove( nick );  // Opcional
				mapaPunts.put( nick, puntuacion );
			}
		}
	}
		
	public boolean existe( String nick ) {
		nick = nick.toLowerCase();
		return mapaPunts.containsKey( nick );
	}
	
	/** Devuelve el high score del usuario indicado
	 * @param nick	Usuario a consultar
	 * @return	Su highscore, -1 si no existe
	 */
	public int getHighScore( String nick ) {
		nick = nick.toLowerCase();
		if (mapaPunts.containsKey( nick )) {
			Integer i = mapaPunts.get( nick );
			if (i==null)
				return -1;
			else
				return i;
		} else {
			return -1;
		}
	}
	
	public ArrayList<Integer> getMejoresPunts() {
		ArrayList<Integer> ret = new ArrayList<>();
		for (Integer i : mapaPunts.values()) {
			if (i!=null) {
				ret.add( i );
			}
		}
		// Collections.sort( ret );  // Ordenación natural
		Comparator<Integer> c = new ComparaAlReves();
		ret.sort(c);
		return ret;
	}
	
	@Override
	public String toString() {
		return mapaPunts.toString();
	}
	
	public static void main(String[] args) {
		HighScores punts = new HighScores();
		punts.addUsuario( "ainhoa" );
		punts.addUsuario( "andoni" );
		if (punts.existe("Andoni")) 
			System.out.println( "Ya estaba" );
		punts.addUsuario( "Andoni" );
		punts.addPuntuacion( "Ainhoa", 150 );
		punts.addPuntuacion( "ainhoa", 50 );
		punts.addPuntuacion( "ainhoa", 200 );
		System.out.println( "Punt Ainhoa = " +
				punts.getHighScore( "ainhoa" ));
		System.out.println( "Punt Andoni = " +
				punts.getHighScore( "Andoni" ));
		System.out.println( punts );
		punts.addPuntuacion( "luis", 100 );
		punts.addPuntuacion( "amaia", 25 );
		System.out.println( punts.getMejoresPunts() );
	}

}
