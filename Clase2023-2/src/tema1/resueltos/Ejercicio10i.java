package tema1.resueltos;

/** Ejercicio 1.0.i resuelto
 * @author andoni.eguiluz
 */
public class Ejercicio10i {
	/* Crea dos arrays, uno con los nombres de 4 o 5 usuarios de cualquier red social que uses,
	   y otro con sus seguidores. Realiza un programa que muestre por consola esos usuarios
	   PRIMERO SIN ORDENAR Y LUEGO 
	   ordenados por número de seguidores (de mayor a menor).
	 */
	public static void main(String[] args) {
		int[] seguidores = { 1300, 61000, 62000, 128900 };
		String[] usuarios = { "@sama", "@JeffBezos", "@BillGates", "@elonmusk" };
		visualizarUsuariosYSeguidores( seguidores, usuarios );
		ordenarUsuarios( seguidores, usuarios );
		visualizarUsuariosYSeguidores( seguidores, usuarios );
	}

	// Visualiza los usuarios y sus seguidores, una línea cada usuario
	private static void visualizarUsuariosYSeguidores(
			int[] seguidores, String[] usuarios) {
		for (int indice=0; indice<seguidores.length; indice++) {
			System.out.println( usuarios[indice] + " " + seguidores[indice] );
		}
	}
	
	// (Single responsability - cada método hace solo una cosa)
	// Ordena los usuarios y los seguidores
	private static void ordenarUsuarios( int[] seguidores, String[] usuarios ) {
		for (int pasada=0; pasada<seguidores.length-1; pasada++) {
			for (int comp=0; comp<seguidores.length-1; comp++) {   // TODO se puede mejorar la iteración?
				boolean intercambiar = seguidores[comp] < seguidores[comp+1];
				if (intercambiar) {
					int aux = seguidores[comp];
					seguidores[comp] = seguidores[comp+1];
					seguidores[comp+1] = aux;
					String auxUsu = usuarios[comp];
					usuarios[comp] = usuarios[comp+1];
					usuarios[comp+1] = auxUsu;
				}
			}
		}
	}
	
}