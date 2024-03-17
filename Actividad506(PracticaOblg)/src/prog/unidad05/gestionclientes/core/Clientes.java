package prog.unidad05.gestionclientes.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clientes {
	// Proveedor de almacenamiento de clientes
	private ProveedorAlmacenamientoClientes proveedorAlmacenamiento;

	/**
	 * Constructor
	 * 
	 * @param proveedorAlmacenamiento Proveedor de almacenamiento a emplear para
	 *                                leer y escribir los clientes desde o hacia
	 *                                almacenamiento secundario
	 * @throws NullPointerException Si cliente es null
	 * @throws ClientesException    Si ya hay un cliente con el mismo NIF o hay
	 *                              problemas con el almacenamiento
	 */
	public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamiento) {
		if (proveedorAlmacenamiento != null) {
			this.proveedorAlmacenamiento = proveedorAlmacenamiento;
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * Añade un nuevo cliente. El cliente se almacena en almacenamiento secundario
	 * inmediatamente
	 * 
	 * @param cliente Nuevo cliente a añadir. No puede ser null ni su NIF coincidir
	 *                con el NIF de un cliente ya almacenado
	 * @throws ClientesException    Si ya hay un cliente con el mismo NIF o hay
	 *                              problemas con el almacenamiento
	 * @throws NullPointerException Si cliente es null
	 */
	public void addCliente(Cliente cliente) throws ClientesException, NullPointerException {
		if (cliente == null) {
			throw new NullPointerException();
		}

		try {
			// Creamos un array con los clientes actuales
			Cliente[] arrayClientes = proveedorAlmacenamiento.getAll();
			// Pasamos el array a lista
			List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(arrayClientes));
			// Si y hay un cliente con el mismo nif salta ClientesException(No puede haber
			// dos clientes con el mismo nif)
			for (int i = 0; i < listaClientes.size(); i++) {
				if (cliente.getNif().trim().equalsIgnoreCase(arrayClientes[i].getNif())) {
					throw new ClientesException();
				}
			}
			// Añadimos el cliente a la lista
			listaClientes.add(cliente);
			// Pasamos la lista a array
			arrayClientes = listaClientes.toArray(new Cliente[0]);
			// Guardamos los clientes(con el nuevo cliente)
			proveedorAlmacenamiento.saveAll(arrayClientes);
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
	}

	/**
	 * Actualiza un cliente ya existente. El cliente se actualiza inmediatamente en
	 * el almacenamiento secundario
	 * 
	 * @param cliente Cliente a almacenar. No puede ser null y su NIF debe coincidir
	 *                con el de algún cliente ya almacenado.
	 * @throws ClientesException    Si no existe en el almacén ningún cliente con el
	 *                              mismo NIF que el cliente proporcionado o no se
	 *                              puede almacenar en almacenamiento secundario
	 * @throws NullPointerException Si cliente es null
	 */
	public void updateCliente(Cliente cliente)
			throws ClientesException, NullPointerException, ProveedorAlmacenamientoClientesException {
		if (cliente == null) {
			throw new NullPointerException();
		}

		// Comprobamos si el cliente existe, si no se lanza Excepcion
		boolean nifExiste = false;
		Cliente[] arrayCliente = proveedorAlmacenamiento.getAll();
		for (Cliente cliente2 : arrayCliente) {
			if (cliente2.getNif().trim().equalsIgnoreCase(cliente.getNif().trim())) {
				nifExiste = true;
			}
		}

		// Si existe
		if (nifExiste) {
			// Actualizamos el cliente en la lista
			for (int i = 0; i < arrayCliente.length; i++) {
				if (arrayCliente[i].getNif().trim().equalsIgnoreCase(cliente.getNif().trim())) {
					arrayCliente[i] = cliente;
					proveedorAlmacenamiento.saveAll(arrayCliente);
				}
			}
		} else {
			throw new ClientesException();
		}
	}

	/**
	 * Elimina un cliente del almacén. El cliente se eliminará inmediatamente del
	 * almacenamiento secundario
	 * 
	 * @param nif NIF del cliente a eliminar
	 * @throws ClientesException    Si no hay ningún cliente con el NIF
	 *                              proporcionado o ocurre un error con el
	 *                              almacenamiento secundario
	 * @throws NullPointerException Si el nif es null
	 */
	public void removeCliente(String nif) throws ClientesException, NullPointerException {
		if (nif == null) {
			throw new NullPointerException();
		}

		try {
			// Obtenemos el array con los clietnes del fichero
			Cliente[] arrayCliente = proveedorAlmacenamiento.getAll();
			// Pasamos el array a lista
			List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(arrayCliente));

			boolean borrado = false;
			// Comprobamos si el cliente esta en la lista
			// Si lo esto lo borramos
			for (int i = 0; i < listaClientes.size(); i++) {
				if (listaClientes.get(i).getNif().trim().equalsIgnoreCase(nif)) {
					borrado = true;
					// Borramos el cliente en la posicion especifica
					listaClientes.remove(i);
					// Pasamos la lista a array
					arrayCliente = listaClientes.toArray(new Cliente[0]);
					// Guardamos la nueva lista
					proveedorAlmacenamiento.saveAll(arrayCliente);
				}
			}
			if (!borrado) {
				throw new ClientesException();
			}
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
	}

	/**
	 * Obtiene un cliente por su nif
	 * 
	 * @param nif NIF del cliente
	 * @return Cliente con el NIF especificado, si se encuentra. null si no se
	 *         encuentra
	 */
	public Cliente getByNif(String nif) {
		Cliente cliente = null;
		try {

			// Obtener todos los clientes del almacenamiento
			Cliente[] clientesArray = proveedorAlmacenamiento.getAll();
			// Para cada cliente del fichero
			for (Cliente clienteArchivo : clientesArray) {
				// Si el nif es igual al que nos han pasado
				// Creamos el cliente
				if (clienteArchivo.getNif().equals(nif)) {
					cliente = clienteArchivo;
				}
			}
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
		return cliente;
	}

	/**
	 * Proporciona un visitador para recorrer los clientes almacenados
	 * 
	 * @param visitador Visitador al que se llamará con cada uno de los clientes
	 *                  almacenados. No puede ser null
	 * @throws NullPointerException Si el visitador es nulo
	 */
	public void visita(VisitadorClientes visitador) {

		if (visitador == null) {
			throw new NullPointerException();
		}

		try {
			Cliente[] clientesArray = proveedorAlmacenamiento.getAll();
			for (Cliente cliente : clientesArray) {
				visitador.visita(cliente);
			}
		} catch (ProveedorAlmacenamientoClientesException e) {
		}
	}
}
