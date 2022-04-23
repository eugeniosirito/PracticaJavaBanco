/**
 * 
 */
package ar.edu.ort.a2019.c2.tp1.f1.clases;

/**
 *
 */
public abstract class Cliente implements EsVip, Mostrable {

	private int nroCliente;
	private String cuit;
	private String nombreApellido;
	private String email;
	private String celular;
	private Operacion operacionRequerida;

	public Cliente(int nroCliente, String cuit, String nombreApellido, String email, String celular)
			throws RuntimeException {
		this.setNroCliente(nroCliente);
		this.setCuit(cuit);
		this.setNombreApellido(nombreApellido);
		this.setEmail(email);
		this.setCelular(celular);
	}

	private void setNroCliente(int nroCliente) throws RuntimeException {
		if (nroCliente <= 0) {
			throw new RuntimeException("N�mero de cliente inv�lido");
		}
		this.nroCliente = nroCliente;
	}

	private void setCuit(String cuit) throws RuntimeException {
		if (cuit == null || cuit.isEmpty()) {
			throw new RuntimeException("Cuit del cliente inv�lido");
		}
		this.cuit = cuit;
	}

	private void setNombreApellido(String nombreApellido) throws RuntimeException {
		if (nombreApellido == null || nombreApellido.isEmpty()) {
			throw new RuntimeException("Nombre y Apellido del cliente inv�lido");
		}
		this.nombreApellido = nombreApellido;
	}

	private void setEmail(String email) throws RuntimeException {
		if (email == null || email.isEmpty()) {
			throw new RuntimeException("Email del cliente inv�lido");
		}
		this.email = email;
	}

	private void setCelular(String celular) throws RuntimeException {
		if (celular == null || celular.isEmpty()) {
			throw new RuntimeException("Celular del cliente inv�lido");
		}
		this.celular = celular;
	}

	protected int getNroCliente() {
		return nroCliente;
	}

	protected String getCuit() {
		return cuit;
	}

	protected String getNombreApellido() {
		return nombreApellido;
	}

	protected String getEmail() {
		return email;
	}

	protected String getCelular() {
		return celular;
	}

	/**
	 * Indica la operaci�n que desea realizar el cliente en su visita al banco.
	 * 
	 * @param operacion la operaci�n que desea realizar el cliente.
	 * @throws RuntimeException en caso de algun problema (operaci�n no valida para
	 *                          el tipo de cliente.)
	 */
	public abstract void indicarOperacionRequerida(Operacion operacion) throws RuntimeException;

	/**
	 * Indica si el n�mero de cliente indicado como par�metro coincide con el
	 * propio.
	 * 
	 * @param nroCliente
	 * @return <code>true</code> si el n�mero pasado coincide con el del cliente,
	 *         <code>false</code> en cualquier otro caso.
	 */
	public boolean esMiNumero(int nroCliente) {
		return this.nroCliente == nroCliente;
	}

	@Override
	public void mostrar() {
		String vip = this.esVip() ? "VIP" : "Estandar";
		System.out.println("Cliente nro " + this.getNroCliente() + "(" + vip + ") - " + getNombreApellido()
				+ " - CUIT: " + getCuit() + " - email: " + getEmail() + " - Celular: " + getCelular());

	}

	public Operacion indicarOperacionRequerida() {
		return this.operacionRequerida;
	}

	protected void setOperacionRequerida(Operacion operacionRequerida) {
		this.operacionRequerida = operacionRequerida;
	}
}
