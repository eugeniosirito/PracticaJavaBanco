package ar.edu.ort.a2019.c2.tp1.f1.clases;

public class Operacion implements Mostrable {

	private TipoOperacion tipo;
	private float monto;
	private int dia;
	private Mes mes;
	private int anio;

	public Operacion(TipoOperacion tipo, float monto, int dia, Mes mes, int anio) throws RuntimeException {
		this.setTipo(tipo);
		this.setMonto(monto);
		this.setMes(mes);
		this.setDia(dia);
		this.setAnio(anio);
	}

	public void operar() {
		System.out.println("Operando....");
		this.mostrar();
	}

	@Override
	public void mostrar() {
		String vip = this.isTipoVIP() ? "VIP" : "Estandar";
		System.out
				.println("Operacion (" + vip + ") " + tipo + ". Monto: " + monto + " fecha: " + this.formatearFecha());
	}

	/**
	 * Devuelve un String con una representación visual de la fecha.
	 * @return
	 */
	private String formatearFecha() {
		return this.dia + " de " + mes.getNombre() + " de " + anio;
	}

	private void setTipo(TipoOperacion tipo) throws RuntimeException {
		if (tipo == null) {
			throw new RuntimeException("El el tipo de la operacion no puede ser nulo");
		}
		this.tipo = tipo;
	}

	private void setMonto(float monto) throws RuntimeException {
		if (tipo == null) {
			throw new RuntimeException("El el monto de la operacion no puede ser menor a 0");
		}
		this.monto = monto;
	}

	private void setDia(int dia) throws RuntimeException {
		if (dia < 0 || dia > this.mes.getCantidadDias()) {
			throw new RuntimeException("El día debe estar en el rango de dias del mes");
		}
		this.dia = dia;
	}

	private void setMes(Mes mes) throws RuntimeException {
		if (mes == null) {
			throw new RuntimeException("El el mes de la operacion no puede ser nulo");
		}
		this.mes = mes;
	}

	private void setAnio(int anio) throws RuntimeException {

		if (anio < 2000 || anio > 2019) {
			throw new RuntimeException("El el anio de la operacion debe estar en el rango 2000-2019 inclusive");
		}
		this.anio = anio;
	}

	public boolean isTipoVIP() {

		return this.tipo == TipoOperacion.DEPOSITO_DOLARES || this.tipo == TipoOperacion.EXTRACCION_DOLARES;
	}

	public Mes getMes() {
		return mes;
	}

}
