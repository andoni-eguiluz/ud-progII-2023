package tema5.ejemplos.login;

import javax.swing.JFrame;

public class PruebaVentanaUsuario {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize( 400, 300 );
		f.setLocation( 200, 100 );
		DatoUsuario[] datosPrueba = new DatoUsuario[] {
			new DatoUsuario( "A", 20, 80 ),
			new DatoUsuario( "B", 120, 110 ),
			new DatoUsuario( "C", 180, 200 ),
			new DatoUsuario( "D", 200, 150 ),
			new DatoUsuario( "E", 230, 40 ),
		};
		VentanaUsuario v = new VentanaUsuario( f, datosPrueba );
		v.setVisible( true );
		f.dispose();
	}
}
