package prog.unidad05.gestionclientes.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Version mock del proveedor de almacenamiento de clientes que almacena los datos en un conjunto
 */
public class ProveedorAlmacenamientoClientesMock implements ProveedorAlmacenamientoClientes {

  // Atributos
  // Almacen de clientes
  private Set<Cliente> clientes;
  
  public ProveedorAlmacenamientoClientesMock() {
    this.clientes = new HashSet<>();
  }
  
  public boolean contains(Cliente cliente) {
    return clientes.contains(cliente);
  }
  
  public int size() {
   return clientes.size(); 
  }
  
  @Override
  public Cliente[] getAll() {
    // Devuelve todos los elementos en un array
    return clientes.toArray(new Cliente[0]);
  }

  @Override
  public void saveAll(Cliente[] clientes) {
    // Crea el conjunto de nuevo con los datos del array (el conjunto es inmutable pero nosotros
    // no necesitamos modificarlo)
    this.clientes = Set.of(clientes);
  }

}
