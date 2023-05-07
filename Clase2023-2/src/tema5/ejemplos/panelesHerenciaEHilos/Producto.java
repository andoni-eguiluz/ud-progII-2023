package tema5.ejemplos.panelesHerenciaEHilos;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Producto {
	protected String nombre;
	protected double precioEuros;
	
	public Producto(String nombre, double precioEuros) {
		super();
		this.nombre = nombre;
		this.precioEuros = precioEuros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioEuros() {
		return precioEuros;
	}

	public void setPrecioEuros(double precioEuros) {
		this.precioEuros = precioEuros;
	}
	
	/** Devuelve el tiempo de trabajo que lleva asociado este producto
	 * @return	tiempo en segundos
	 */
	public abstract int getTiempoTrabajo();

	/** Devuelve el panel de visualización de producto, personalizado para cada clase de producto concreta
	 * @return	Panel con la información del producto
	 */
	public JPanel getPanelProducto() {
		JPanel panel = new JPanel( new BorderLayout() );
		panel.add( new JLabel( nombre, JLabel.CENTER ), BorderLayout.NORTH );
		panel.add( new JLabel( precioEuros + " €", JLabel.CENTER ), BorderLayout.SOUTH );
		return panel;
	}
	
	@Override
	public String toString() {
		return nombre + " - " + precioEuros + " €";
	}
	
}
