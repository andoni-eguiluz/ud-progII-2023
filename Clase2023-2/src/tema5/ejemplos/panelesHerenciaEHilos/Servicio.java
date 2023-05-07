package tema5.ejemplos.panelesHerenciaEHilos;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Servicio extends Producto implements Configurable {
	private static final int TIEMPO_INSTALACION_APP = 120; // Suponemos 2 minutos por instalación de App
	private static final int TIEMPO_LIMPIEZA_VIRUS = 10*60; // Suponemos 10 minutos por instalación de App
	private static final int PRECIO_INSTALACION_APP = 2; // 2 euros por instalación de App
	private static final int PRECIO_LIMPIEZA_VIRUS = 5; // 2 euros por limpieza de virus
	
	/** Tipo posible de servicio
	 */
	public static enum TipoServicio { INSTALACION_APP, LIMPIEZA_VIRUS }
	
	private int tiempoServicio;  // En segundos
	private TipoServicio tipoServicio;
	
	/** Inicializa un servicio con precio 0 euros y tiempo 0 segundos
	 * @param nombre	Nombre del servicio
	 */
	public Servicio(String nombre, TipoServicio tipoServicio ) {
		super(nombre, 0);
		this.tipoServicio = tipoServicio;
		this.tiempoServicio = 0;
	}

	// Crea un nuevo servicio copia del actual
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Servicio serv = new Servicio( this.nombre, this.tipoServicio );
		serv.tiempoServicio = this.tiempoServicio;
		serv.precioEuros = this.precioEuros;
		return serv;
	}

	public int getTiempoServicio() {
		return tiempoServicio;
	}

	public void setTiempoServicio(int tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}

	@Override
	public int getTiempoTrabajo() {
		return tiempoServicio;
	}
	
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/** Calcula el tiempo de servicio y el precio de instalación de apps en función del número de apps a instalar
	 * @param numAppsAInstalar	Número &gt; 0 de apps que se quieren instalar
	 */
	public void calcInstalacionApps( int numAppsAInstalar ) {
		tiempoServicio = numAppsAInstalar * TIEMPO_INSTALACION_APP;
		precioEuros = numAppsAInstalar * PRECIO_INSTALACION_APP;
	}
	
	/** Calcula el tiempo de servicio de limpieza de virus
	 */
	public void calcTiempoLimpiezaVirus( int numVirus ) {
		tiempoServicio = TIEMPO_LIMPIEZA_VIRUS * numVirus;
		precioEuros = PRECIO_LIMPIEZA_VIRUS * numVirus;
	}

	@Override
	public JPanel getPanelProducto() {
		JPanel panel = super.getPanelProducto();
		panel.add( new JLabel( "Tiempo servicio (sgs.): " + tiempoServicio ), BorderLayout.CENTER );
		return panel;
	}

	@Override
	public void configurar() {
		// TODO Auto-generated method stub
		if (tipoServicio == TipoServicio.INSTALACION_APP) {
			int numero = -1;
			do {
				String resp = JOptionPane.showInputDialog( null, "¿Cuántas apps desea instalar?" );
				try {
					numero = Integer.parseInt( resp );
				} catch (NumberFormatException e) { 
					JOptionPane.showMessageDialog( null, "Valor incorrecto: debe ser un número mayor que cero", "Error", JOptionPane.ERROR_MESSAGE );
				}
			} while (numero<=0);
			calcInstalacionApps( numero );
		} else if (tipoServicio == TipoServicio.LIMPIEZA_VIRUS) {
			int numero = -1;
			do {
				String resp = JOptionPane.showInputDialog( null, "¿Cuántos virus desea quitar?" );
				try {
					numero = Integer.parseInt( resp );
				} catch (NumberFormatException e) { 
					JOptionPane.showMessageDialog( null, "Valor incorrecto: debe ser un número mayor que cero", "Error", JOptionPane.ERROR_MESSAGE );
				}
			} while (numero<=0);
			calcTiempoLimpiezaVirus( numero );
		}
	}
	
	public boolean estaConfigurado() {
		return tiempoServicio > 0;
	}
	
	
}
