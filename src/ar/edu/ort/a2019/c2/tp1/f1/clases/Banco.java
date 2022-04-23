package ar.edu.ort.a2019.c2.tp1.f1.clases;

import java.util.ArrayList;

import ar.edu.ort.a2019.c2.tp1.f1.tad.TAD;
import ar.edu.ort.a2019.c2.tp1.f1.tad.implementaciones.arraylist.ColaAL;

public class Banco {

	private static final String NO_HABIA_CLIENTES_PARA_ATENDER = "No había clientes para atender";

	private static final String CLIENTE_INGRESADO_EN_LA_FILA_ESTANDAR = "Cliente ingresado en la fila Estandar";

	private static final String NO_SE_PUEDEN_RECIBIR_MAS_CLIENTES_ESTANDAR = "No se pueden recibir mas clientes Estandar";

	private static final String CLIENTE_INGRESADO_EN_LA_FILA_VIP = "Cliente ingresado en la fila VIP";

	private static final String NO_SE_PUEDEN_RECIBIR_MAS_CLIENTES_VIP = "No se pueden recibir mas clientes VIP";

	private static final String NOMBRE_DEL_BANCO_INVALIDO = "Nombre del banco inválido";

	private static final String LISTADO_DE_CLIENTES_INVALIDO = "Listado de clientes inválido";

	private static final String TAMANIO_DE_LAS_FILAS_INVALIDO = "Tamaï¿½o de las filas inválido";


	private String nombre;
	private ArrayList<Cliente> clientes;
	private TAD<Cliente> filaClientesVIP;
	private TAD<Cliente> filaClientesEstandar;

	private int[] cantidadOperacionesPorMes;

	/**
	 * Constructor del banco.
	 * 
	 * @param nombre             Nombre del banco
	 * @param clientes           Nomina de clientes del banco
	 * @param tamanioMaximoFilas Tamaño máximo de las filas.
	 * @throws RuntimeException En caso de haber un error construyendo el banco.
	 */
	public Banco(String nombre, ArrayList<Cliente> clientes, int tamanioMaximoFilas) throws RuntimeException {
		this.setNombre(nombre);
		if (tamanioMaximoFilas < 2) {
			throw new RuntimeException(TAMANIO_DE_LAS_FILAS_INVALIDO);
		}
		filaClientesVIP = new ColaAL<>(tamanioMaximoFilas);
		filaClientesEstandar = new ColaAL<>(tamanioMaximoFilas);

		cantidadOperacionesPorMes = new int[Mes.values().length];

		for (int i = 0; i < cantidadOperacionesPorMes.length; i++) {
			cantidadOperacionesPorMes[i] = 0;
		}
		if (clientes == null || clientes.isEmpty()) {
			throw new RuntimeException(LISTADO_DE_CLIENTES_INVALIDO);
		}
		this.clientes = new ArrayList<Cliente>(clientes.size());
		for (Cliente cliente : clientes) {
			this.clientes.add(cliente);
		}

	}

	private void setNombre(String nombre) throws RuntimeException {
		if (nombre == null || nombre.isEmpty()) {
			throw new RuntimeException(NOMBRE_DEL_BANCO_INVALIDO);
		}
		this.nombre = nombre;
	}

	/**
	 * Recibe a un cliente que desea ser atendido.
	 * 
	 * @param nroCliente         Número de cliente que lo identifica
	 * @param operacionRequerida {@link Operacion} que desea realizar
	 * @throws RuntimeException En caso de ocurrir un error recibiendo a un cliente.
	 */
	public void recibirCliente(int nroCliente, Operacion operacionRequerida) throws RuntimeException {
		Cliente c = buscarCliente(nroCliente);
		if (c == null) {
			throw new RuntimeException("Cliente nro " + nroCliente + " no encontrado");
		}
		c.indicarOperacionRequerida(operacionRequerida);
		if (c.esVip()) {
			if (this.filaClientesVIP.isFull()) {
				throw new RuntimeException(NO_SE_PUEDEN_RECIBIR_MAS_CLIENTES_VIP);
			}
			this.filaClientesVIP.add(c);
			System.out.println(CLIENTE_INGRESADO_EN_LA_FILA_VIP);
		} else {
			if (this.filaClientesEstandar.isFull()) {
				throw new RuntimeException(NO_SE_PUEDEN_RECIBIR_MAS_CLIENTES_ESTANDAR);
			}
			this.filaClientesEstandar.add(c);
			System.out.println(CLIENTE_INGRESADO_EN_LA_FILA_ESTANDAR);
		}
	}

	/**
	 * Busca un cliente en la nomina de clientes en base a su número.
	 * 
	 * @param nroCliente Número del cliente a buscar
	 * @return el cliente buscado, o null si no lo encuentra
	 */
	private Cliente buscarCliente(int nroCliente) {
		Cliente cliente = null, aux;
		int i = 0;
		while (cliente == null && i < clientes.size()) {
			aux = clientes.get(i);
			if (aux.esMiNumero(nroCliente)) {
				cliente = aux;
			} else {
				i++;
			}
		}
		return cliente;
	}

	/**
	 * Atiende al proximo cliente en la fila, primero agota los clientes vip, si no
	 * hay mas vip atiende a un estandar.
	 * 
	 * @throws RuntimeException si no hay clientes para atender.
	 */
	public void atender() throws RuntimeException {

		// atiende uno, si hay en vip, en vip si no en estandar
		if (!filaClientesVIP.isEmpty()) {
			atender(filaClientesVIP);
		} else if (!filaClientesEstandar.isEmpty()) {
			atender(filaClientesEstandar);
		} else {
			throw new RuntimeException(NO_HABIA_CLIENTES_PARA_ATENDER);
		}
	}

	/**
	 * Atiende un cliente de la fila especificada
	 * 
	 * @param fila la fila de la cual atender al cliente.
	 */
	private void atender(TAD<Cliente> fila) {
		Cliente c = fila.remove();
		Operacion o = c.indicarOperacionRequerida();

		o.operar();

		this.cantidadOperacionesPorMes[o.getMes().ordinal()]++;
	}

	/**
	 * Muestra un resumen con el diario de operaciones.
	 */
	public void finDelDia() {
		System.out.println("Final del día del banco " + nombre);
		System.out.println("- Cantidad de clientes: " + this.clientes.size());
		// mostrar operaciones
		System.out.println("Cantidad total de operaciones: " + this.obtenerCantidadTotalOperaciones());
//		mostrar cantidad de operaciones por mes.
		System.out.println("Operaciones por mes:");
		for (int i = 0; i < cantidadOperacionesPorMes.length; i++) {
			int cantOperaciones = cantidadOperacionesPorMes[i];
			System.out.println("Mes: " + Mes.values()[i] + " " + cantOperaciones + " operaciones.");
		}
	}

	/**
	 * Calcula el total de operaciones realizadas
	 * 
	 * @return un entero conteniendo la cantidad de operaciones realizadas.
	 */
	private int obtenerCantidadTotalOperaciones() {
		int acum = 0;
		for (int i : cantidadOperacionesPorMes) {
			acum += i;
		}
		return acum;
	}
}
