package prog.unidad05.gestionclientes.core;

/**
 * Un proveedor de almacenamiento de clientes almacena clientes en un sistema de
 * almacenamiento secundario o permanente como ficheros, base de datos, etc.
 * Cada implementación almacena los datos en un dispositivo y formato
 * particulares
 */
public interface ProveedorAlmacenamientoClientes {

	/**
	 * Obtiene todos los clientes almacenados
	 * 
	 * @return Array de Cliente con todos los clientes. Puede estar vacío si aún no
	 *         hay clientes
	 * @throws ProveedorAlmacenamientoClientesException Si ocurre algún error
	 *                                                  accediendo al almacenamiento
	 */
	public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException;

	/**
	 * Almacena todos los clientes en almacenamiento secundario Los clientes
	 * previamente existentes se sustituyen por los que se proporcionan aqui
	 * 
	 * @param clientes Clientes a almacenar. Puede ser vacío pero no null
	 * @throws NullPointerException                     Si clientes es null
	 * @throws ProveedorAlmacenamientoClientesException Si ocurre algún error
	 *                                                  accediendo al almacenamiento
	 */
	public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException;

}