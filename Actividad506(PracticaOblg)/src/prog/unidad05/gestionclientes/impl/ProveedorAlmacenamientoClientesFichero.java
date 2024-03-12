package prog.unidad05.gestionclientes.impl;


import java.util.HashSet;
import java.util.Set;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;

/**
 * Proveedor de almacenamiento de clientes que almacena los datos de los
 * clientes en un fichero en disco. Si el fichero no existe se crea la primera
 * vez que se guarde y devuelve una colección vacía de clientes al leer.
 */
public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {

  private Set<Cliente> clientes;

  /**
   * Constructor con ruta
   * 
   * @param rutaFichero Ruta al fichero a emplear para leer y almacenar los
   *                    clientes
   * @throws NullPointerException Si la ruta no es nula
   */
  public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
    this.clientes = new HashSet<>();
  }
  
  public boolean contains(Cliente cliente){
  	return clientes.contains(cliente);
  }
  
  public int size() {
  	return clientes.size();
  }

  @Override
  public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
    return clientes.toArray(new Cliente[0]);
  }

  @Override
  public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException {
  	this.clientes = Set.of(clientes);
  }
 
}
