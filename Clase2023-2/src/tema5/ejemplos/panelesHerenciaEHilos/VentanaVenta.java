package tema5.ejemplos.panelesHerenciaEHilos;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/** Ventana principal de ejemplo de ventas (con herencia Producto (padre) - Servicio / Móvil (hijas)
 * Incluye también ejemplo de uso de hilos para simulación de espera de dependiente libre
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class VentanaVenta extends JFrame {
	private static final long serialVersionUID = 1L;  // Atributo de Serializable (aunque no la vamos a serializar)
	
	// Atributos de ventana
	private ArrayList<JLabel> lTrabajadores;     // Lista de labels de trabajadores
	private JTextArea taPubliTienda;             // Mensaje principal de tienda
	private JPanel pProducto;                    // Panel de producto
	private JComboBox<Producto> cbProductos;     // Combo de productos
	private JButton bComprar;                    // Botón de compra
	private JButton bConfigurar;                 // Botón de configuración
	private JLabel lMensaje;                     // Etiqueta de mensajes

	// Atributos de hilo para simular espera
	private Thread hiloEstadoDependientes;
	
	// Otros 
	private Tienda tienda;
	
	/** Crea una nueva ventana de venta
	 * @param tienda	Tienda con la que trabaja esta ventana
	 */
	public VentanaVenta( Tienda tienda ) {
		// Inicialización
		this.tienda = tienda;
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Contenedores
		JPanel pCentral = new JPanel( new BorderLayout() );
		JPanel pCentralInf = new JPanel();
		pCentralInf.setLayout( new GridLayout( tienda.getListaTrabajadores().size() + 1, 1 ) );
		JPanel pDerecho = new JPanel( new BorderLayout() );
		JPanel pDerechoSup = new JPanel();
		JPanel pDerechoInf = new JPanel();
		pProducto = new JPanel( new BorderLayout() );
		cbProductos = new JComboBox<>();
		for (Producto prod : tienda.getListaProductos()) {
			cbProductos.addItem( prod );
		}
		
		// Componentes
		taPubliTienda = new JTextArea( 15, 25 );
		sacaInfo();
		lTrabajadores = new ArrayList<>();
		for (Trabajador tr : tienda.getListaTrabajadores()) {
			JLabel label = new JLabel( tr.getInfoTrabajador(), JLabel.CENTER );
			label.setOpaque( true );
			lTrabajadores.add( label );
		}
		bComprar = new JButton( "Comprar" );
		bComprar.setEnabled( false );
		bConfigurar = new JButton( "Configurar" );
		bConfigurar.setEnabled( false );
		lMensaje = new JLabel( " " );
		lMensaje.setOpaque( true );
		lMensaje.setBackground( Color.WHITE );
		
		// Asociación componentes y contenedores
		pCentral.add( new JScrollPane( taPubliTienda ), BorderLayout.CENTER );
		pCentral.add( pCentralInf, BorderLayout.SOUTH );
		pCentralInf.add( new JLabel( "Nuestros trabajadores:" ) );
		for (JLabel label : lTrabajadores) {
			pCentralInf.add( label );
		}
		pDerechoSup.add( new JLabel( "¿Qué desea?" ) );
		pDerechoSup.add( cbProductos );
		pDerechoInf.add( bComprar );
		pDerechoInf.add( bConfigurar );
		pDerecho.add( pDerechoSup, BorderLayout.NORTH );
		pDerecho.add( pProducto, BorderLayout.CENTER );
		pDerecho.add( pDerechoInf, BorderLayout.SOUTH );
		getContentPane().add( pCentral, BorderLayout.CENTER );
		getContentPane().add( pDerecho, BorderLayout.EAST );
		getContentPane().add( lMensaje, BorderLayout.SOUTH );
		
		// Eventos
		cbProductos.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarPanelProducto();
				lMensaje.setText( "Nuevo producto selecionado" );
				lMensaje.setBackground( Color.YELLOW );
			}
		});
		bConfigurar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Producto pSel = (Producto) cbProductos.getSelectedItem(); 
				if (pSel!=null && pSel instanceof Configurable) {
					((Configurable)pSel).configurar();
					refrescarPanelProducto();
					lMensaje.setText( "¡Producto configurado!" );
					lMensaje.setBackground( Color.GREEN );
				}
			}
		});
		bComprar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Producto pSel = (Producto) cbProductos.getSelectedItem();
				if (pSel instanceof Configurable) {
					Configurable conf = (Configurable) pSel;
					if (!conf.estaConfigurado()) {
						lMensaje.setText( "¡No se puede comprar un servicio no configurado!" );
						lMensaje.setBackground( Color.RED );
						return;  // Si no está configurado, no se puede todavía comprar
					}
				}
				procesoVenta( pSel );
				lMensaje.setText( "¡Producto comprado!" );
				lMensaje.setBackground( Color.GREEN );
			}
		});
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				hiloEstadoDependientes.interrupt();
			}
		});
		
		// Final inicialización y creación hilo
		pack();  // Pone el tamaño exacto para su contenido
		setLocationRelativeTo( null );  // Centra la ventana en el escritorio
		hiloEstadoDependientes = new Thread() {
			public void run() {
				while (!interrupted()) {
					actualizaEstadoDependientes();
					// Duerme un segundo
					try {
						Thread.sleep( 1000 );
					} catch (InterruptedException e) {
						break; // Se ha interrumpido -cerrado- el hilo
					}
				}
			}
		};
		hiloEstadoDependientes.start();
	}
	
	private void sacaInfo() {
		taPubliTienda.setText( "Bienvenido a Deustienda" );
		taPubliTienda.append( "\nNuestros productos son:" );
		for (int i=0; i<tienda.getListaProductos().size(); i++) {
			Producto prod = tienda.getListaProductos().get(i);
			taPubliTienda.append( "\n - " + prod );
		}
		taPubliTienda.append( "\n¿Qué desea? Opere en el panel derecho" );
		taPubliTienda.append( "\nInformación de nuestros trabajadores, abajo" );
	}
	
	private void actualizaEstadoDependientes() {
		tienda.pasaElTiempo();  // Simula el paso del tiempo
		// Actualiza el color de los dependientes
		for (int i=0; i<tienda.getListaTrabajadores().size(); i++) {
			Trabajador trab = tienda.getListaTrabajadores().get(i);
			JLabel label = lTrabajadores.get(i);
			label.setText( trab.getInfoTrabajador() );
			if (trab.isLibre()) {
				label.setBackground( Color.GREEN );
			} else {
				if (trab.getTiempoPendiente()<10) {
					label.setBackground( Color.ORANGE );
				} else {
					label.setBackground( Color.RED );
				}
			}
		}
	}
	
	private void refrescarPanelProducto() {
		Producto pSel = (Producto) cbProductos.getSelectedItem(); 
		if (pSel!=null) {
			bComprar.setEnabled( true ); // Hay producto seleccionado, se puede comprar
			bConfigurar.setEnabled( (pSel instanceof Configurable) ); // Activar botón de configuración solo para los configurables
			pProducto.removeAll(); // Vacía el panel
			pProducto.add( ((Producto)cbProductos.getSelectedItem()).getPanelProducto() );  // Panel polimórfico de cada producto
			pProducto.revalidate();  // Al cambiar en caliente la estructura de un panel, hay que informar a Swing (revalidate)
		}
	}
	
	// Proceso de venta del producto
	private void procesoVenta( Producto prod ) {
		// El proceso de venta puede tardar un tiempo, así que lo hacemos en un hilo
		bComprar.setEnabled( false );
		bConfigurar.setEnabled( false );
		Thread hilo = new Thread() {
			public void run() {
				bComprar.setEnabled( false );
				// 1.- Veamos si hay operario libre
				actualizaEstadoDependientes(); // Actualizamos el tiempo que haya pasado en la tienda
				Trabajador trabajador = tienda.getTrabajadorLibre();
				while (trabajador==null) {
					lMensaje.setText( "No hay trabajadores libres! En espera..." );
					lMensaje.setBackground( Color.RED );
					try {
						Thread.sleep( 1000 );
					} catch (InterruptedException e) {
						return;  // Hilo interrupido - se cancela el trabajo
					}
					actualizaEstadoDependientes();
					trabajador = tienda.getTrabajadorLibre();
				}
				lMensaje.setBackground( Color.WHITE );
				// 2.- Cuando ya hay trabajador libre, se le asigna el trabajo
				lMensaje.setText( "Su dependiente asignado es " + trabajador );
				actualizaEstadoDependientes();  // Actualiza el tiempo pasado en la tienda
				trabajador.setTiempoPendiente( prod.getTiempoTrabajo() );
			}
		};
		hilo.start();
	}

	
}
