/**
 * 
 */
package ar.edu.ort.a2019.c2.tp1.f1.clases;

/**
 *
 */
public class ClienteVIP extends Cliente {

	public ClienteVIP(int nroCliente, String cuit, String nombreApellido, String email, String celular) throws RuntimeException {
		super(nroCliente, cuit, nombreApellido, email, celular);
	}

	@Override
	public boolean esVip() {
		return true;
	}

	@Override
	public void indicarOperacionRequerida(Operacion operacion) throws RuntimeException {
		if (operacion == null) {
			throw new RuntimeException("Operacion Inválida");
		}
		this.setOperacionRequerida(operacion);
	}

}
