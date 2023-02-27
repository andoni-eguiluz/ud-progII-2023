package tema1.resueltos;

/** Ejercicio 1.0.i resuelto
 * @author andoni.eguiluz
 */
public class Ejercicio10iConObjetos {
	/* Crea dos arrays, uno con los nombres de 4 o 5 usuarios de cualquier red social que uses,
	   y otro con sus seguidores. Realiza un programa que muestre por consola esos usuarios
	   PRIMERO SIN ORDENAR Y LUEGO 
	   ordenados por número de seguidores (de mayor a menor).
	 */
	public static void main(String[] args) {
		// int[] seguidores = new int[] { 1300, 61000, 62000, 128900 };
		// String[] usuarios = { new String("@sama"), "@JeffBezos", "@BillGates", "@elonmusk" };
		
		// Método 1: primero crear array y luego meter objetos
		// UsuarioRedSocial[] usuariosRS = new UsuarioRedSocial[4];
		// usuariosRS[0] = new UsuarioRedSocial();
		// usuariosRS[0].nombre = "@sama";
		// usuariosRS[0].seguidores = 1300;
		// usuariosRS[0] = new UsuarioRedSocial( "@sama", 1300 );
		// usuariosRS[1] = new UsuarioRedSocial( "@JeffBezos", 61000 );
		// usuariosRS[2] = new UsuarioRedSocial( "@BillGates", 62000 );
		// usuariosRS[3] = new UsuarioRedSocial( "@elonmusk", 128900 );
		
		// Método 2 de inicialización: primero se crean los objetos y luego se crea el array
//		UsuarioRedSocial u1 = new UsuarioRedSocial( "@sama", 1300 );
//		UsuarioRedSocial u2 = new UsuarioRedSocial( "@JeffBezos", 61000 );
//		UsuarioRedSocial u3 = new UsuarioRedSocial( "@BillGates", 62000 );
//		UsuarioRedSocial u4 = new UsuarioRedSocial( "@elonmusk", 128900 );
//		UsuarioRedSocial[] usuariosRS = { u1, u2, u3, u4 };
		
		// Versión 3:
		UsuarioRedSocial[] usuariosRS = { 
				new UsuarioRedSocial( "@sama", 1300 ),
				new UsuarioRedSocial( "@JeffBezos", 61000 ),
				new UsuarioRedSocial( "@BillGates", 62000 ),
				new UsuarioRedSocial( "@elonmusk", 128900 )
		};
		
		
		
		visualizarUsuariosYSeguidores( usuariosRS );
		ordenarUsuarios( usuariosRS );
		visualizarUsuariosYSeguidores( usuariosRS );
	}

	// Visualiza los usuarios y sus seguidores, una línea cada usuario
	private static void visualizarUsuariosYSeguidores( UsuarioRedSocial[] usuariosRS ) {
		for (int indice=0; indice<usuariosRS.length; indice++) {
			System.out.println( usuariosRS[indice].toString() );
		}
	}
	
	// (Single responsability - cada método hace solo una cosa)
	// Ordena los usuarios y los seguidores
	private static void ordenarUsuarios( UsuarioRedSocial[] usuariosRS ) {
		for (int pasada=0; pasada<usuariosRS.length-1; pasada++) {
			for (int comp=0; comp<usuariosRS.length-1; comp++) {   // TODO se puede mejorar la iteración?
				boolean intercambiar = usuariosRS[comp].esMenosFamosoQue( usuariosRS[comp+1] );
				// UsuarioRedSocial.comparaFama( ..., ...)
				if (intercambiar) {
					UsuarioRedSocial auxUsu = usuariosRS[comp];
					usuariosRS[comp] = usuariosRS[comp+1];
					usuariosRS[comp+1] = auxUsu;
				}
			}
		}
	}
	
}