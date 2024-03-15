package prog.unidad05.gestionclientes.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Clientes {

  private ProveedorAlmacenamientoClientes proveedorAlmacenamiento;
  private List<Cliente> listaClientes;
  private Cliente[] arrayClientes;
  boolean clienteEncontrado;

  public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamiento) {
    if (proveedorAlmacenamiento != null) {
      this.proveedorAlmacenamiento = proveedorAlmacenamiento;
    } else {
      throw new NullPointerException();
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

    // Recargar la lista de clientes desde el proveedor de almacenamiento
    listaClientes = new ArrayList<>(Arrays.asList(proveedorAlmacenamiento.getAll()));

    boolean clienteEncontrado = false;
    for (int i = 0; i < listaClientes.size(); i++) {
      // Si el NIF del cliente de la lista coincide exactamente con el NIF del cliente
      // que hemos pasado
      if (listaClientes.get(i).getNif().equals(cliente.getNif())) {
        clienteEncontrado = true;
        // Actualizamos el cliente en la lista
        listaClientes.set(i, cliente);
      }
    }

    if (!clienteEncontrado) {
      // Si no se encuentra el cliente en la lista, lanzamos una excepción
      throw new ClientesException();
    }

    // Guardar todos los clientes, incluido el cliente actualizado
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
