package tema5.ejemplos.login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lMensajeInfo;
	public VentanaLogin() {
		// 1.- Crear contenedor - ya está creado, es this
		// new JFrame()
		// 2.- Configurar contenedor
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize( 500, 300 );
		this.setTitle( "Login" );
		this.setLayout( new BorderLayout() );  // no hace falta
		// 3.- Crear resto de contenedores
		JPanel pCentral = new JPanel();
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		JPanel pIntermedio = new JPanel();
		// 4.- Configurar esos contenedores
		pCentral.setLayout( new BoxLayout( pCentral, BoxLayout.Y_AXIS ) );
		pCentral.setBackground( Color.CYAN );
		pCentral.setBorder( BorderFactory.createLineBorder( Color.RED ));
		pSuperior.setBorder( BorderFactory.createLineBorder( Color.GREEN ));
		pIntermedio.setBorder( BorderFactory.createLineBorder( Color.MAGENTA ));
		// 5.- Crear componentes
		JLabel lCabecera = new JLabel( "Identifícate con tus datos oficiales" );
		final JLabel lUsuario = new JLabel( "Usuario:" );
		JTextField tfUsuario = new JTextField( "", 15 );
		JLabel lPassword = new JLabel( "Password:" );
		JTextField tfPassword = new JTextField( "", 15 );
		JCheckBox cbRecuerdame = new JCheckBox( "Recuérdame", true );
		lMensajeInfo = new JLabel( " " );
		JLabel lLogo = new JLabel( new ImageIcon( "src/utils/ventanas/ventanaBitmap/img/UD-blue-girable.png" ) );
		JButton bAceptar = new JButton( "Aceptar" );
		JButton bCancelar = new JButton( "Cancelar" );
		JButton bOlvidada = new JButton( "He olvidado mi contraseña" );
		// 6.- Configurar componentes
		bAceptar.setForeground( Color.GREEN );
		bAceptar.setEnabled( false );
		bOlvidada.setVisible( false );
		bCancelar.setToolTipText( "Pulsa aquí para cancelar el login" );
		// 7.- Asignar componentes a contenedores
		this.add( pCentral, BorderLayout.CENTER );
		this.add( pInferior, BorderLayout.SOUTH );
		this.add( lCabecera, BorderLayout.NORTH );
		this.add( lLogo, BorderLayout.WEST );
		pSuperior.add( lUsuario );
		pSuperior.add( tfUsuario );
		pCentral.add( pSuperior );
		pCentral.add( pIntermedio );
		pCentral.add( cbRecuerdame );
		pCentral.add( lMensajeInfo );
		pSuperior.add( lUsuario );
		pSuperior.add( tfUsuario );
		pIntermedio.add( lPassword );
		pIntermedio.add( tfPassword );
		pInferior.add( bAceptar );
		pInferior.add( bCancelar );
		pInferior.add( bOlvidada );
		// if botón pulsado  // no se puede
		// 8.- Inicializar los gestores de eventos
		// bCancelar.addActionListener( new EscuchadorBotonExterno() );  // manera 1 - clases externas
		// bCancelar.addActionListener( new EscuchadorBotonInterno() );  // manera 2 - clases internas
		// Manera 3: Clases internas anónimas
		bCancelar.addActionListener( new ActionListener() {  // creando un nuevo objeto de una clase sin nombre que implementa ActionListener
			@Override
			public void actionPerformed(ActionEvent e) {
				// NO SE EJECUTA EN EL MISMO TIEMPO
				// NO SE EJECUTA EN EL MISMO CONTEXTO (No tiene las mismas variables)
				lMensajeInfo.setText( "Pulsado cancelar" );
				// lUsuario.setText( "noseque" ); // Si hay que hacerlo como atributo, o saber el truco
			}
		});
		// Sigue
		tfUsuario.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println( "TYPED " + e );
				if (e.getKeyChar()>='0' && e.getKeyChar()<='9') {
					System.out.println( "Es un dígito" );
					e.consume();
				}
				if (tfUsuario.getText().length()>=10) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println( "RELEASED " + e );
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println( "PRESSED " + e );
			}
		});
		tfUsuario.addFocusListener( new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println( "GAINED " + e );
			}
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println( "LOST " + e );
				if (tfUsuario.getText().length()==0) {
					tfUsuario.requestFocus();
				}
				if (!tfUsuario.getText().equals("Andoni")) {  // Consulta a un mapa...
					lMensajeInfo.setText( "Usuario erróneo: mete Andoni" );
				}
			}
		});
		KeyListener escEscape = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
		tfUsuario.addKeyListener( escEscape );
		tfPassword.addKeyListener( escEscape );
		cbRecuerdame.addKeyListener( escEscape );
		bAceptar.addKeyListener( escEscape );
		bCancelar.addKeyListener( escEscape );
		// Prueba mouselistener
		lLogo.addMouseListener( new MouseListener() {
			Point pulsacion;
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println( "RELEASED " + e );
				if (e.getPoint().distance( pulsacion ) < 10) {
					// ...
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println( "PRESS " + e );
				pulsacion = e.getPoint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println( "EXIT " + e );
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println( "ENTER " + e );
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println( "CLICK " + e );
			}
		});
		tfPassword.addFocusListener( new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (tfUsuario.getText().length()>0 && tfPassword.getText().length()>0) {
					bAceptar.setEnabled( true );
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		bAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ¿Cómo lanzar otra ventana?
				DatoUsuario[] datosPrueba = new DatoUsuario[] {
						new DatoUsuario( "A", 20, 80 ),
						new DatoUsuario( "B", 120, 110 ),
						new DatoUsuario( "C", 180, 200 ),
						new DatoUsuario( "D", 200, 150 ),
						new DatoUsuario( "E", 230, 40 ),
					};
				VentanaUsuario v = new VentanaUsuario( VentanaLogin.this, datosPrueba );
				v.setVisible( true );
				// ¿Cómo quitar la que lanza?
				// 1.- Ocultar
				// VentanaLogin.this.setVisible( false );
				// 2.- Liberar
			 	/*VentanaLogin.this.*/dispose();
			}
		});
	}
	
	class EscuchadorBotonInterno implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println( "Botón cancelar pulsado" );
			lMensajeInfo.setText( "Pulsado cancelar" );  // Error - no acceso al atributo
		}
	}

	
}

class EscuchadorBotonExterno implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println( "Botón cancelar pulsado" );
		// lMensajeInfo.setText( "Pulsado cancelar" );  // Error - no acceso al atributo
	}
}
