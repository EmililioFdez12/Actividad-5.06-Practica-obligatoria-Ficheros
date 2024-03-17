package prog.unidad05.gestionclientes.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;

/**
 * Proveedor de almacenamiento de clientes que almacena los datos de los
 * clientes en un fichero en disco. Si el fichero no existe se crea la primera
 * vez que se guarde y devuelve una colección vacía de clientes al leer.
 */
/**
 * Proveedor de almacenamiento de clientes que almacena los datos de los
 * clientes en un fichero en disco. Si el fichero no existe se crea la primera
 * vez que se guarde y devuelve una colección vacía de clientes al leer.
 */
public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {
	// Ruta del archivo
	private String rutaFichero = "clientes.dat";
	private List<Cliente> listaClientes;

	/**
	 * Constructor con ruta
	 * 
	 * @param rutaFichero Ruta al fichero a emplear para leer y almacenar los
	 *                    clientes
	 * @throws NullPointerException Si la ruta no es nula
	 */
	public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
		this.listaClientes = new ArrayList<>();
	}

	// Lee el archivo y añade los clientes que haya una lista
	@Override
	public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
		String linea = null;
		listaClientes.clear();
		try (BufferedReader flujoEntrada = new BufferedReader(new FileReader(rutaFichero))) {
			while ((linea = flujoEntrada.readLine()) != null) {
				String[] datosCliente = linea.split("\\|");
				if (datosCliente.length == 6) {
					Cliente cliente = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2],
							Integer.parseInt(datosCliente[3]), Double.parseDouble(datosCliente[4]),
							Boolean.parseBoolean(datosCliente[5]));
					listaClientes.add(cliente);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe. Se ha creado uno vacío");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
		}
		return listaClientes.toArray(new Cliente[listaClientes.size()]);
	}

	@Override
	public void saveAll(Cliente[] clientes) throws ProveedorAlmacenamientoClientesException {
		try (PrintWriter flujoSalida = new PrintWriter(new FileWriter(rutaFichero, false))) {
			for (Cliente clienteATexto : clientes) {
				flujoSalida.printf("%s|%s|%s|%s|%s|%s%n", clienteATexto.getNif(), clienteATexto.getApellidos(),
						clienteATexto.getNombre(), clienteATexto.getEmpleados(), clienteATexto.getFacturacion(),
						clienteATexto.isNacionalUe());
			}
		} catch (IOException e) {
			System.out.println("No se ha podido crear el archivo.");
			throw new ProveedorAlmacenamientoClientesException();
		}
	}
}
