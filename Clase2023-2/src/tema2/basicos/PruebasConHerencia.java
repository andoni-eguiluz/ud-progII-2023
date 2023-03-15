package tema2.basicos;

import java.util.ArrayList;

/** Algunas pruebas básicas con herencia
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PruebasConHerencia {
	public static void main(String[] args) {
		ObraCultural miOC = new ObraCultural( "Prueba" );
		// miOC.setTitulo( "Prueba" );
		System.out.println( miOC.getTitulo() );
		Pelicula peli = new Pelicula( "Batman", "Matt Reeves" );
		peli.setAnyo( 2022 );
		// peli.setTitulo( "Batman" );
		// peli.setDirector( "Matt Reeves" );
		System.out.println( peli.getTitulo() );
		Libro libro = new Libro( "Sapiens" );
		// libro.setTitulo( "Sapiens" );
		System.out.println( libro.getTitulo() );
		System.out.println( miOC.toString() );
		System.out.println( peli );
		System.out.println( libro.toString() );
		System.out.println( peli.getDirector() );
		// System.out.println( miOC.getDirector() );
		
		// Polimorfismo de datos
		Libro l = new Libro( "A" ); // ref a un objeto de tipo Libro
		ObraCultural oc = new Libro( "L" );
		ArrayList<ObraCultural> listaOC = new ArrayList<>();
		listaOC.add( peli );
		listaOC.add( libro );
		for (ObraCultural obra : listaOC) {
			System.out.println( "* " + obra.getTitulo() );
			if (obra instanceof Pelicula) {
				Pelicula p = (Pelicula) obra;
				System.out.println( "** " + p.getDirector() );
			}
		}
	}
}

class ObraCultural {  // extends Object
	protected String titulo;
	protected int anyo;
	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
	public String getTitulo() {
		return titulo;
	}
	public ObraCultural( String titulo ) {
		this.titulo = titulo;
	}
	public ObraCultural(String titulo, int anyo) {
		// super();   Java lo pone por defecto
		this.titulo = titulo;
		this.anyo = anyo;
	}
	
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	@Override
	public String toString() {
		return titulo + " (" + anyo + ")";
	}
}

class Pelicula extends ObraCultural {
//	private String titulo;
//	private int anyo;
//	public void setTitulo( String titulo ) {
//		this.titulo = titulo;
//	}
//	public String getTitulo() {
//		return titulo;
//	}
	private String director;
	private ArrayList<String> actoresActrices;
	
	public Pelicula( String titulo, String director ) {
		super( titulo );
		// this.titulo = titulo;
		// this.setTitulo( titulo );
		this.director = director;
		actoresActrices = new ArrayList<>();
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	@Override
	public String toString() {
		return super.toString() + " - " + director;
	}
	
}

class Libro extends ObraCultural {
	private String autor_a;
	private int numPaginas;
	public Libro( String titulo ) {
		super( titulo );
	}
}

class PeliDeAccion extends Pelicula {
	private int numMuertesPorSegundo;
	
	public PeliDeAccion( String titulo, String director ) {
		super( titulo, director );
	}
}

