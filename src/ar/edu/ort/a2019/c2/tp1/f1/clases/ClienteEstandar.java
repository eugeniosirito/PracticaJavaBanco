/**
 * 
 */
package ar.edu.ort.a2019.c2.tp1.f1.clases;

/**
 *
 */
public class ClienteEstandar extends Cliente {

	public ClienteEstandar(int nroCliente, String cuit, String nombreApellido, String email, String celular) throws RuntimeException {
		super(nroCliente, cuit, nombreApellido, email, celular);
	}

	@Override
	public boolean esVip() {
		return false;
	}

	@Override
	public void indicarOperacionRequerida(Operacion operacion) throws RuntimeException {
		if (operacion == null || operacion.isTipoVIP()) {
			throw new RuntimeException("Operacion Inválida");
		}
		this.setOperacionRequerida(operacion);
	}

}
