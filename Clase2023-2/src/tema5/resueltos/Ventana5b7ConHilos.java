package tema5.resueltos;

// AWT -> Abstract Windowing Toolkit
// Swing -> J

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

/** Solución ejercicio 5a.4
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class Ventana5b7ConHilos extends JFrame {

	/** Método de prueba de la ventana del ejercicio 5a.4
	 * @param args
	 */
	public static void main(String[] args) {
//		JFrame ventana = new JFrame( "Prueba" );	
		Ventana5b7ConHilos vent = new Ventana5b7ConHilos();
		vent.setVisible( true );
//		Ventana5a4 vent2 = new Ventana5a4();
//		vent2.setVisible( true );
		System.out.println( "Fin de programa" );
	}
	
	// No static 
	
	private JLabel lSuperior;
	private Thread hilo;
	private boolean ventanaSigue = true;
	
	public Ventana5b7ConHilos() {
		// 0.- Configuración
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );  // cambiado por tarea de cierre de ventana
		setSize( 600, 400 );
		setTitle( "Ventana de prueba" );
		setAlwaysOnTop(true);
		setLocationRelativeTo( null );
		// 1. Crear contenedores
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		JPanel pDerecho = new JPanel( new GridLayout( 4, 3 ) );
		JPanel pIzquierdo = new JPanel();
		JPanel pIzq1 = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pIzq2 = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pIzq3 = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		// 2. Crear componentes
		lSuperior = new JLabel( "Ventana super útil" );
		JButton bCancelar = new JButton( "¡Cancela!");
		JButton bAdelante = new JButton( "¡A por ello campeón!" );
		JButton bCambioColor = new JButton( "Cambia negro/blanco" );
		JButton bCrono = new JButton( "Lanzar crono" );
		JTextArea taTexto = new JTextArea( 5, 10 );
		JButton[] bDerecha = new JButton[12];
		String botones = "123456789<0*";
		for (int i=0; i<botones.length(); i++) {
			bDerecha[i] = new JButton( "" + botones.charAt(i) );
		}
		JLabel lLogoUni = new JLabel( new ImageIcon( "src/tema5/ejercicios/UD-blue-peq.png" ) );
		String[] arrayOpciones = { "Creativx", "Organizadx", "Disciplinadx", "Dinámicx" };
		JComboBox<String> cbTipo = new JComboBox<>( arrayOpciones );
		JLabel lNombre = new JLabel( "Nombre:" );
		final JTextField tfNombre = new JTextField( "<Nombre>", 15 );
		JLabel lCodPostal = new JLabel( "Cod.Postal:" );
		JTextField tfCodPostal = new JTextField( 8 );
		JCheckBox checkBoxUrgente = new JCheckBox( "Urgente", true ); 
		
		// 3. Formato de contenedores
		this.setLayout( new BorderLayout() ); // Por defecto la ventana tiene borderlayout
		pInferior.setLayout( new FlowLayout( FlowLayout.RIGHT ) ); // Por defecto
		pInferior.setBackground( Color.PINK );
		pSuperior.setLayout( new FlowLayout() );
		pSuperior.setBackground( Color.CYAN );
		JScrollPane spCentral = new JScrollPane( taTexto );
		pIzquierdo.setLayout( new BoxLayout( pIzquierdo, BoxLayout.Y_AXIS ));
		this.setMinimumSize( new Dimension( 500, 250 ) );
		// 4. Formato de componentes
		bCancelar.setSize( 100, 40 );
		lSuperior.setBackground( Color.YELLOW );
		lSuperior.setOpaque( true );
		lSuperior.setFont( new Font( "Arial", Font.BOLD, 24 ));
		bCancelar.setBackground( Color.RED );
		bCancelar.setForeground( new Color( 255, 255, 255, 200 ) );
		bAdelante.setBackground( Color.GREEN );
		bAdelante.setEnabled( false );
		cbTipo.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq1.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq2.setMaximumSize( new Dimension( 300, 35 ) );
		pIzq3.setMaximumSize( new Dimension( 300, 35 ) );
		// bAdelante.setVisible( false );
		
		// 5. Añadir componentes a contenedores
		this.add( pSuperior, BorderLayout.NORTH );
		pSuperior.add( lSuperior );
		this.add( pInferior, BorderLayout.SOUTH );
//		this.add( bCancelar, BorderLayout.SOUTH );
//		this.add( bAdelante, BorderLayout.SOUTH );
		pInferior.add( bCambioColor );
		pInferior.add( bCancelar );
		pInferior.add( bAdelante );
		pInferior.add( bCrono );
		this.add( spCentral, BorderLayout.CENTER );
		this.add( pDerecho, BorderLayout.EAST );
		for (JButton boton : bDerecha) {
			pDerecho.add( boton );
		}
		this.add( pIzquierdo, BorderLayout.WEST );
		pIzquierdo.add( lLogoUni );
		pIzquierdo.add( cbTipo );
		pIzquierdo.add( pIzq1 );
		pIzquierdo.add( pIzq2 );
		pIzquierdo.add( pIzq3 );
		pIzq1.add( lNombre );
		pIzq1.add( tfNombre );
		pIzq2.add( lCodPostal );
		pIzq2.add( tfCodPostal );
		pIzq3.add( checkBoxUrgente );
		
		// 6.- Preparación de eventos de la ventana
		// Manera 1 - clase externa
		// bCancelar.addActionListener( new GestorBotonCancelar( lSuperior ) );
		// Manera 2 - clase interna
		// bCancelar.addActionListener( new GestorBotonCancelarInterna() );
		// Manera 3 - clase interna anónima
		// Si la clase se instancia una sola vez...
		// ... se puede hacer anónima
		bCancelar.addActionListener( new ActionListener() {
			// Crea un atributo oculto con el mismo nombre que tfNombre
			// Copia el valor de tfNombre a ese atributo
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println( "hola");
				lSuperior.setText( "Ventana cancelada" );
				tfNombre.setText( "" );  // Funciona pero ojo que está pasando en una copia
			}
		});
		// Qué pasa si hay cambio de variable?
		// tfNombre.setText( "h" );
		// tfNombre = new JTextField();
		bAdelante.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lSuperior.setText( "Ventana confirmada" );
			}
		} );
		tfNombre.addFocusListener( new FocusListener() {
			// Internamente se ha creado un atributo bAdelante
			// y se ha copiado la referencia del botón
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println( "lost" );
				if (tfNombre.getText().equals("") || tfNombre.getText().equals("<Nombre>")) {
					bAdelante.setEnabled( false );
				} else {
					bAdelante.setEnabled( true );
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println( "gain" );
			}
		});
		lLogoUni.addMouseListener( new MouseListener() {
			private Point puntoPulsacion = null;
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println( "pressed " + e );
				puntoPulsacion = e.getPoint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println( "released " + e );
				// dibujo línea de origen a destino
				Graphics g = lLogoUni.getGraphics();
				g.setColor( Color.YELLOW );
				g.drawLine( puntoPulsacion.x, puntoPulsacion.y, e.getX(), e.getY() );
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println( "clicked " + e );
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println( "entered " + e );
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println( "exited " + e );
			}
		});
		lLogoUni.addMouseMotionListener( new MouseMotionListener() {
			Point puntoAnterior = null;
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println( "moved " + e );
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println( "drag " + e );
				if (puntoAnterior != null) {
					Graphics g = lLogoUni.getGraphics();
					g.setColor( Color.RED );
					g.drawLine( puntoAnterior.x, puntoAnterior.y, e.getX(), e.getY() );
				}
				puntoAnterior = e.getPoint();
			}
		});
		tfCodPostal.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println( "type " + e );
				if (e.getKeyChar()<'0' || e.getKeyChar()>'9' || tfCodPostal.getText().length()==5) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println( "release " + e );
			}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println( "press " + e );
			}
		});
		KeyAdapter ka = new KeyAdapter() {
			private boolean shiftPulsado = false;
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
					shiftPulsado = true;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println( "Released " + e );
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE && shiftPulsado) { // NOOOOOO  && e.getKeyCode()==KeyEvent.VK_SHIFT) {
					// También se podría usar e.isShiftDown ... isControlDown... etc.
					if (e.getSource() instanceof JTextField) {
						JTextField tf = (JTextField) e.getSource();
						tf.setText( "" );
					}
				} else if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
					shiftPulsado = false;
				}
			}
		};
		tfCodPostal.addKeyListener( ka );
		tfNombre.addKeyListener( ka );
		this.addWindowListener( new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println( "OPEN" );
			}
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println( "CLOSING" );
				int resp = JOptionPane.showConfirmDialog( Ventana5b7ConHilos.this, "Quieres cerrar?", "Cierre", JOptionPane.YES_NO_OPTION );
				if (resp==JOptionPane.OK_OPTION) {
					// Hilo - segunda manera - implementar el interfaz Runnable
					Runnable r = new Runnable() {
						public void run() {
							int numRestas = getHeight() - 250;
							while (getHeight()>250) {
								setSize( getWidth()-1, getHeight()-1 );
								try {
									Thread.sleep( 3000 / numRestas );
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							Ventana5b7ConHilos.this.dispose();
						}
					};
					Thread hilo = new Thread(r);
					hilo.start();
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println( "CLOSED" );
				// Cerrar el hilo. 3 maneras
				if (hilo!=null) {
					// 1.- Desaconsejada
					// hilo.stop();
					// 2.- Aconsejada: muerte educada
					// hilo.interrupt();
					// 3.- Lógica normal de programación
					ventanaSigue = false;
				}
			}
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println( "ICON" );
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println( "DEICON" );
			}
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println( "ACTIV" );
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println( "DEACTIV" );
			}
		});
		spCentral.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int nuevaAltura = spCentral.getWidth()/10;
				taTexto.setFont( new Font( "Arial", Font.PLAIN, nuevaAltura ) );
				System.out.println( "Cambio altura: " + nuevaAltura );
			}
		});
		System.out.println( "Antes de añadir el actionlistener" );
		bCambioColor.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println( "Iniciando actionPerformed" );
				bCambioColor.setEnabled( false );
				// Hilo -> 1: Heredar la clase Thread / run()
				// TODO - Protección antes de crear y lanzar el hilo
				// de que no haya otro hilo ejecutándose
				Thread hilo = new Thread() {  // clase anónima hija de Thread
					public void run() {
						System.out.println( "Iniciando run del hilo" );
						int cambio = 0;
						int colorIni = 0;
						if (taTexto.getBackground().getGreen()==0) {  // Negro
							cambio = +1;
							colorIni = 0;
						} else {  // 255 - Blanco
							cambio = -1;
							colorIni = 255;
						}
						for (int i=0; i<255; i++) {
							colorIni += cambio;
							taTexto.setBackground( new Color( colorIni, colorIni, colorIni ) );
							try {
								Thread.sleep( 10 );
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
						if (colorIni==0) {
							taTexto.setForeground( Color.WHITE );
						} else {
							taTexto.setForeground( Color.BLACK );
						}
						bCambioColor.setEnabled( true );
					}
				};
				// hilo.run(); // NO HACE MAGIA!!!
				hilo.start(); // MAGIA!!!!    Lanza un nuevo hilo y le da la tarea de ejecutar run()
				System.out.println( "Ya he hecho start" );
				  // Acabará cuando acabe el run()
			}
		} );
		bCrono.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bCrono.setVisible( false );
				hilo = new Thread( new Runnable() {
					public void run() {
						int cont = 0;
						// while (true) {
						while (ventanaSigue) {
							SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss SSSS" );
							lSuperior.setText( "" + sdf.format(new Date()) );
							cont++;
							if (cont % 50 == 0) {
								System.out.println( cont );
							}
							try {
								Thread.sleep( 20 );
							} catch (InterruptedException e) {
								// e.printStackTrace();
								// break;
								return;
							}
						}
					}
				} );
				hilo.start();
			}
		});
	}

	// Podríamos usar una clase interna con nombre
	// pero lo vamos a hacer con una anónima
	private class MiHilo extends Thread {
		@Override
		public void run() {
		}
	}
	
	class GestorBotonCancelarInterna implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println( "Acción en botón" );
			lSuperior.setText( "Ventana cancelada" );
		}
	}	
	
}
