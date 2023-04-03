package tema3.resueltos.ejercicios3b1_2;

import java.util.Comparator;

public class ComparadorFidelidad implements Comparator<String> {
	private Scores scores;
	public ComparadorFidelidad( Scores scores ) {
		this.scores = scores;
	}
	@Override
	public int compare(String o1, String o2) {
		int numPartidas1 = scores.getScores( o1 ).size();
		int numPartidas2 = scores.getScores( o2 ).size();
		return numPartidas2-numPartidas1;  // Devuelve negativo si el usuario o1 tiene más partidas jugadas que el o2, positivo en caso contrario, 0 si tienen las mismas
		// if (numPartidas1>numPartidas2) return -1;
		// else if (numPartidas1<numPartidas2) return +1;
		// else return 0;
	}
	
}
