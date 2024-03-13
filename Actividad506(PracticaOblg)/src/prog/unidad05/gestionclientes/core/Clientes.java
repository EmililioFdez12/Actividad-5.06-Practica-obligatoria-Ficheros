package prog.unidad05.gestionclientes.core;

import java.util.ArrayList;
import java.util.List;

public class Clientes {

	private ProveedorAlmacenamientoClientes proveedorAlmacenamiento;
	private List<Cliente> listaClientes;
	
	public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamiento) {
		if (proveedorAlmacenamiento != null) {
			this.proveedorAlmacenamiento = proveedorAlmacenamiento;
			this.listaClientes = new ArrayList<>();
		} else {
			throw new NullPointerException();
		}
		
		try {
			proveedorAlmacenamiento.getAll();
		} catch (ProveedorAlmacenamientoClientesException e) {
			
		}
	}

	public void addCliente(Cliente cliente)
			throws ClientesException, NullPointerException, ProveedorAlmacenamientoClientesException {
		if (cliente == null) {
			throw new NullPointerException();
		}

		if (listaClientes.contains(cliente)) {
			throw new ClientesException();
		}

		listaClientes.add(cliente);

		Cliente[] arrayClientes = listaClientes.toArray(new Cliente[0]);
		proveedorAlmacenamiento.saveAll(arrayClientes);
	}

	public void updateCliente(Cliente cliente)
			throws ClientesException, NullPointerException, ProveedorAlmacenamientoClientesException {
		if (cliente == null) {
			throw new NullPointerException();
		}

		boolean nifExiste = false;
		for (Cliente cliente2 : listaClientes) {
			if (cliente2.getNif().equals(cliente.getNif())) {
				nifExiste = true;
			}
		}

		if (!nifExiste) {
			throw new ClientesException();
		}

		for (int i = 0; i < listaClientes.size(); i++) {
			// Si el Dni del cliente de la lista coincide con el Dni del cliente que hemos
			// pasado
			if (listaClientes.get(i).getNif().equals(cliente.getNif())) {
				listaClientes.set(i, cliente);
			}
		}

		// Guardar todos los clientes, incluido el nuevo
		Cliente[] arrayClientes = listaClientes.toArray(new Cliente[0]);
		proveedorAlmacenamiento.saveAll(arrayClientes);
	}

	/**
	 * 
	 * @param nif
	 * @throws ClientesException
	 * @throws ProveedorAlmacenamientoClientesException
	 */
	public void removeCliente(String nif) throws ClientesException, ProveedorAlmacenamientoClientesException {
		if (nif == null) {
			throw new NullPointerException();
		}

		boolean clienteEncontrado = false;
		// Hacemos copia de la lista de clientes, la recorremos
		// Y si coinciden los nif, y en la posicion de la lista de la copia borramos el
		// cliente
		// en la lista de clientes
		List<Cliente> copiaClientes = new ArrayList<>(listaClientes);
		for (Cliente cliente : copiaClientes) {
			if (cliente.getNif().equals(nif)) {
				clienteEncontrado = true;
				listaClientes.remove(cliente);
			}
		}

		if (!clienteEncontrado) {
			throw new ClientesException();
		}

		// Guardar todos los clientes, incluido el nuevo
		Cliente[] arrayClientes = listaClientes.toArray(new Cliente[0]);
		proveedorAlmacenamiento.saveAll(arrayClientes);
	}

	public Cliente getByNif(String nif) throws ProveedorAlmacenamientoClientesException {
		Cliente cliente = null;
		// Obtener todos los clientes del almacenamiento
		Cliente[] clientesArray = proveedorAlmacenamiento.getAll();
		for (Cliente clientes : clientesArray) {
			if (clientes.getNif().equals(nif)) {
				cliente = clientes;
			}
		}
		return cliente;

	}

	public void visita(VisitadorClientes visitador) {
		for (Cliente cliente : listaClientes) {
			visitador.visita(cliente);
		}
	}
}
