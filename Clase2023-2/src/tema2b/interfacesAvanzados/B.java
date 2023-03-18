package tema2b.interfacesAvanzados;

public interface B extends InterfazConDefault {
	@Override
	default int comportamiento2() {
		return -3;
	}
}
