package tema5.ejemplos.login;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class VentanaUsuario extends JFrame {
	private static final int ANCHO_LABEL_DATO = 40;
	private static final int ALTO_LABEL_DATO = 20;
	private JPanel pCentral;
	private JComboBox<TipoUsuario> cbTipoUsuario;
	private JList<DatoUsuario> lDatos;  // Componente visual
	private DefaultListModel<DatoUsuario> modeloDatos; // Componente de datos (no visual)
	public VentanaUsuario( JFrame ventAnterior, DatoUsuario[] datos ) {
		// 1.- Configurar cont. principal
		
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		
		setSize( ventAnterior.getSize() );
		setTitle( "Gestión de datos de usuario" );
		setLocation( ventAnterior.getLocation() );
		// 2.- Crear contenedores
		pCentral = new JPanel();
		JPanel pSuperior = new JPanel();
		// 3.- Configurar contenedores
		pCentral.setBackground( Color.white );
		pCentral.setLayout( null );
		// 4.- Crear componentes
		cbTipoUsuario = new JComboBox<>( TipoUsuario.values() );
		modeloDatos = new DefaultListModel<DatoUsuario>();
		for (DatoUsuario du : datos) {
			modeloDatos.addElement( du );
		}
		lDatos = new JList<>( modeloDatos );
		JScrollPane sp = new JScrollPane( lDatos );
		// 5.- Configurar componentes
		
		sp.setPreferredSize( new Dimension( 100, 50 ) ); // Tamaños preferidos, mínimos, máximos
		
		crearVisualDeDatos( datos );
		// 6.- Asignar componentes a contenedores
		add( pCentral, BorderLayout.CENTER );
		add( pSuperior, BorderLayout.NORTH );
		add( sp, BorderLayout.WEST );
		pSuperior.add( cbTipoUsuario );

		// 7.- Eventos
		cbTipoUsuario.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println( "COMBO " + cbTipoUsuario.getSelectedItem() + " " + e );
			}
		});
		lDatos.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					System.out.println( "LISTSELECTION " + e );
					for (Component c : pCentral.getComponents()) {
						JLabel l = (JLabel) c;
						if (l.getText().equals( ((DatoUsuario) lDatos.getSelectedValue()).getNombre() )) {
							l.setBackground( Color.GREEN );
						} else {
							l.setBackground( Color.LIGHT_GRAY );
						}
					}
				}
			}
		});
		// Posibilidad 1 - que el ratón lo escuche el panel
		MouseAdapter ma = new MouseAdapter() {  // WindowAdapter (clase) vs WindowListener (interfaz).  WindowAdapter además implementa tanto ML como MML
			JLabel lSelec = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println( "CLICK " + e );
				if (e.getClickCount()==2) {
					JLabel l = new JLabel( "NEW" );
					l.setBackground( Color.LIGHT_GRAY );
					l.setOpaque( true );
					l.setHorizontalAlignment( JLabel.CENTER );
					l.setSize( ANCHO_LABEL_DATO, ALTO_LABEL_DATO );
					l.setLocation( e.getX()-ANCHO_LABEL_DATO/2, e.getY()-ALTO_LABEL_DATO/2 );
					pCentral.add( l );
					pCentral.repaint();  // Observar que sin el repaint() no se ve salvo que se reescale la ventana (algunos cambios en caliente necesitan repaint()
					// otros necesitan revalidate() - si hay cambio significativo de layout
					// Añadir item:
					DatoUsuario du = new DatoUsuario( "NEW", e.getX(), e.getY() );
					modeloDatos.addElement( du );
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println( "PRESSED " + e );
				lSelec = null;
				for (Component c : pCentral.getComponents()) {
					JLabel l = (JLabel) c;
					double dist = e.getPoint().distance( l.getX()+ANCHO_LABEL_DATO/2, l.getY()+ ALTO_LABEL_DATO/2);
					if (dist < 11) {
						lSelec = l;
						lSelec.setBackground( Color.CYAN );
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println( "RELEASED " + e );
				if (lSelec!=null) {
					lSelec.setBackground( Color.LIGHT_GRAY );
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println( "ENTERED " + e );
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println( "EXITED " + e );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println( "DRAGGED " + e );
				if (lSelec!=null) {
					lSelec.setLocation( e.getX()-ANCHO_LABEL_DATO/2, e.getY()-ALTO_LABEL_DATO/2 );
				}
				// TODO pendiente actualizar el item correspondinte
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println( "MOVED " + e );
			}
		};
		pCentral.addMouseListener( ma );
		pCentral.addMouseMotionListener( ma );
		// Posibilidad 2 - que el ratón lo escuchen los labels
		// (ver código de crearVisualDeDatos)

		pCentral.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				for (Component c : pCentral.getComponents()) {
					JLabel l = (JLabel) c;
					if (l.getX()+ANCHO_LABEL_DATO > pCentral.getWidth() || l.getY()+ALTO_LABEL_DATO>pCentral.getHeight()) {
						l.setBackground( Color.RED );
					}
				}
			}
		});
		this.addWindowListener( new WindowListener() {  
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println( "WINDOW OPENED" );
			}
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println( "WINDOW ICONIFIED" );
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println( "WINDOW DEICONIFIED" );
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println( "WINDOW DEACTIVATED" );
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "WINDOW CLOSING" );
				if (cbTipoUsuario.getSelectedItem()==TipoUsuario.ADMINISTRADOR) {
					VentanaUsuario.this.dispose();  // Observar esta notación de this (para clases internas)
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println( "WINDOW CLOSED" );
			}
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println( "WINDOW ACTIVATED" );
			}
		});
	}
	
	private void crearVisualDeDatos( DatoUsuario[] datos ) {
		for (DatoUsuario dato : datos) {
			JLabel l = new JLabel();
			l.setSize( ANCHO_LABEL_DATO, ALTO_LABEL_DATO );
			l.setLocation( dato.getX() - ANCHO_LABEL_DATO/2, dato.getY() - ALTO_LABEL_DATO/2 );
			l.setText( dato.getNombre() );
			l.setBackground( Color.LIGHT_GRAY );
			l.setOpaque( true );
			l.setHorizontalAlignment( JLabel.CENTER );
			pCentral.add( l );
			
			// Posibilidad 2 de eventos de ratón
			MouseAdapter ma = new MouseAdapter() {
				Point posicionInicial;
				Point posicionInicialLabel;
				@Override
				public void mousePressed(MouseEvent e) {
					posicionInicial = e.getPoint();
					posicionInicialLabel = l.getLocation();
				}
				@Override
				public void mouseDragged(MouseEvent e) {
					if (posicionInicial!=null) {
						int despX = e.getX()-posicionInicial.x;
						int despY = e.getY()-posicionInicial.y;
						l.setLocation( l.getX() + despX, l.getY() + despY );
						l.setBackground( Color.CYAN );
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					posicionInicial = null;
					l.setBackground( Color.LIGHT_GRAY );
					// TODO actualizar el dato en la lista / contenedor de datos
					// Cancelar drag si está fuera del pCentral
					if (l.getX()>pCentral.getWidth() || l.getX()<0 || l.getY()<0 || l.getY()>pCentral.getHeight()) {
						retornarLabel( l );
					}
				}
				private void retornarLabel( JLabel l ) {
					Thread hilo = new Thread() {
						public void run() {
							double posX = l.getX();
							double posY = l.getY();
							double incX = (posicionInicialLabel.x - posX) / 100;
							double incY = (posicionInicialLabel.y - posY) / 100;
							for (int i=0; i<100; i++) {
								posX += incX;
								posY += incY;
								l.setLocation( (int) posX, (int) posY );
								try {
									Thread.sleep( 100 );
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
					hilo.start();
				}
			};
			l.addMouseListener(ma);
			l.addMouseMotionListener(ma);
		}
	}
	
}
