package prog.unidad05.gestionclientes.core;

import java.util.ArrayList;
import java.util.List;

public class Clientes {

  private List<Cliente> listaClientes = new ArrayList<>();

  public Clientes(ProveedorAlmacenamientoClientes proveedorAlmacenamiento) {

  }

  public void addCliente(Cliente cliente) throws ClientesException {
    if (cliente == null) {
      throw new IllegalArgumentException();
    }

    if (listaClientes.contains(cliente.getNif())) {
      throw new ClientesException();
    }
    listaClientes.add(cliente);
  }

  public void updateCliente(Cliente cliente) throws ClientesException {
    if (cliente == null) {
      throw new IllegalArgumentException();
    }

    if (!listaClientes.contains(cliente.getNif())) {
      throw new ClientesException();
    }
    listaClientes.add(cliente);
  }

  public void removeCliente(String nif) throws ClientesException {
    if (nif == null) {
      throw new NullPointerException();
    }

    boolean encontrado = false;
    // Para cada cliente de la lista
    for (Cliente cliente: listaClientes) {
      // Si el dni del cliente coincide con el dni proporcionado
      // Se retira de la lista
      if (cliente.getNif().equals(nif)) {
        listaClientes.remove(cliente);
        encontrado = true;
      }
    }
    if (!encontrado) {
      throw new ClientesException();
    }
  }

  public Cliente getByNif(String nif) {
    for (Cliente cliente : listaClientes) {
      if (cliente.getNif().equals(nif)) {
        return cliente;
      }
    }
    return null;
  }

  public void visita(VisitadorClientes visitador) {

  }

}
