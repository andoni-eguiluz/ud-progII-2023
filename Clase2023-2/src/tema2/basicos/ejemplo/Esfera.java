package tema2.basicos.ejemplo;

public class Esfera {  // Java me hace extends Object {
	private double xCentro;
	private double yCentro;
	private double radio = 50;
	public Esfera(double xCentro, double yCentro, double radio) {
		super();
		System.out.println( "C ESF");
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		setRadio( radio );
	}
	public double getxCentro() {
		return xCentro;
	}
	public void setxCentro(double xCentro) {
		this.xCentro = xCentro;
	}
	public double getyCentro() {
		return yCentro;
	}
	public void setyCentro(double yCentro) {
		this.yCentro = yCentro;
	}
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	@Override
	public String toString() {
		return String.format( "(%.1f,%.1f)R%.1f", xCentro, yCentro, radio );
	}
	
}
