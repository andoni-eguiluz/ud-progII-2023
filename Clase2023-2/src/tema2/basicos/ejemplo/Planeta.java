package tema2.basicos.ejemplo;

public class Planeta extends Esfera {
	// x,y,r
	// getx,gety... set...  tos..
	
	private String nombre;
	
	public Planeta( String nombre, double xCentro, double yCentro, double radio) {
		super(xCentro,yCentro,radio);
//		setxCentro( xCentro );
//		this.yCentro = yCentro;
//		this.radio = radio;
		System.out.println( "C PL");
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Planeta " + nombre + super.toString();
	}
	
	@Override
	public void setRadio(double radio) {
		if (radio < 5) {
			System.err.println( "Error radio " + radio + " menor que 5" );
		} else {
			super.setRadio(radio);
		}
	}
}
