package prog.unidad05.gestionclientes.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;
import prog.unidad05.gestionclientes.core.Clientes;
import prog.unidad05.gestionclientes.core.ClientesException;

/**
 * Proveedor de almacenamiento de clientes que almacena los datos de los
 * clientes en un fichero en disco. Si el fichero no existe se crea la primera
 * vez que se guarde y devuelve una colección vacía de clientes al leer.
 */
public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {

	private String rutaFichero = "clientes.dat";
	private Set<Cliente> clientes;

	/**
	 * Constructor con ruta
	 * 
	 * @param rutaFichero Ruta al fichero a emplear para leer y almacenar los
	 *                    clientes
	 * @throws NullPointerException Si la ruta no es nula
	 */
	public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
		this.clientes = new HashSet<>();
	}

	public boolean contains(Cliente cliente) {
		return clientes.contains(cliente);
	}

	public int size() {
		return clientes.size();
	}

	@Override
	public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
		List<String> listaLineas = new ArrayList<>();
		try (BufferedReader flujoEntrada = new BufferedReader(new FileReader(rutaFichero))) {
			String linea = null;
			do {
				linea = flujoEntrada.readLine();
				listaLineas.add(linea);
			} while (linea != null);

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			System.out.println("No se puede leer el archivo");
		}
		return clientes.toArray(new Cliente[0]);
	}

	@Override
	public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException {
		try (PrintWriter flujoSalida = new PrintWriter(new FileWriter(rutaFichero))) {    
          for (Cliente cliente: clientes) {
						flujoSalida.println(cliente.toString());
					}           
    } catch (IOException e) {
      System.out.println("No se ha podido crear el archivo.");
      e.printStackTrace();
    }
		this.clientes = Set.of(clientes);
	}

	public static void main(String[] args)
			throws ProveedorAlmacenamientoClientesException, NullPointerException, ClientesException {
		Cliente cliente = new Cliente("77687485V", "Pepin", "Rodriguez", 20, 25.5, false);
		Cliente cliente2 = new Cliente("67071237V", "Lolito", "Fernandez", 500, 100000, true);
		String ruta = "clientes.dat";

		ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(ruta);
		Clientes clientes = new Clientes(proveedor);
		clientes.addCliente(cliente);
		clientes.addCliente(cliente2);
		
		Cliente[] arrayCliente = proveedor.getAll();
		proveedor.saveAll(arrayCliente);
	}

}
