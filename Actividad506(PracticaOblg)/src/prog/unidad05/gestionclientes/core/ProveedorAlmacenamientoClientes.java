package prog.unidad05.gestionclientes.core;

public interface ProveedorAlmacenamientoClientes {
 
  /**
   * Obtiene todos los clientes almacenados
   * @return Array de Cliente con todos los clientes. Puede estar vacío si aún no hay clientes
   * @throws ProveedorAlmacenamientoClientesException Si ocurre algún error accediendo al almacenamiento
   */
  public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException;
  
  /**
   * 
   * @param clientes Clientes a almacenar. Puede ser vacío pero no null
   * @throws NullPointerException Si clientes es null
   * @throws ProveedorAlmacenamientoClientesException Si ocurre algún error accediendo al almacenamiento
   */
  public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException;
     
}