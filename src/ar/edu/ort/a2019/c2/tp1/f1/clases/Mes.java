package ar.edu.ort.a2019.c2.tp1.f1.clases;

/**
 * Meses del año, cada uno tiene como atributos el nombre del mes y su cantidad
 * de días
 *
 */
public enum Mes {

	ENERO("Enero", 31), FEBRERO("Febrero", 28), MARZO("Marzo", 31), ABRIL("Abril", 30), MAYO("Mayo", 31),
	JUNIO("Junio", 30), JULIO("Julio", 31), AGOSTO("Agosto", 31), SEPTIEMBRE("Septiembre", 30), OCTUBRE("Octubre", 31),
	NOVIEMBRE("Noviembre", 30), DICIEMBRE("Diciembre", 31);

	private String nombre;
	private int cantidadDias;

	private Mes(String nombre, int cantidadDias) {
		this.nombre = nombre;
		this.cantidadDias = cantidadDias;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidadDias() {
		return cantidadDias;
	}

}
