package tema2b.interfacesAvanzados;

public interface A extends InterfazConDefault {

	@Override
	default int comportamiento2() {
		return -2;
	}
	
}
