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
    this.listaClientes = cargarClientesDesdeArchivo();
  }

  private List<Cliente> cargarClientesDesdeArchivo() {
    List<Cliente> clientes = new ArrayList<>();
    String linea = null;
    try (BufferedReader flujoEntrada = new BufferedReader(new FileReader(rutaFichero))) {
      while ((linea = flujoEntrada.readLine()) != null) {
        String[] datosCliente = linea.split("\\|");
        if (datosCliente.length == 6) { // Verificar que hay suficientes datos en la línea
          Cliente cliente = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2],
              Integer.parseInt(datosCliente[3]), Double.parseDouble(datosCliente[4]),
              Boolean.parseBoolean(datosCliente[5]));
          clientes.add(cliente);
        } else {
          // Manejar la línea que no cumple con el formato esperado
          System.out.println("La línea del archivo no cumple con el formato esperado: " + linea);
        }
      }
    } catch (FileNotFoundException e) {
      // Manejar la excepción correctamente
      System.out.println("El archivo no existe.");
    } catch (IOException e) {
      // Manejar la excepción correctamente
      System.out.println("Error de entrada/salida.");
    }
    return clientes;
  }

  @Override
  public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
    return listaClientes.toArray(new Cliente[0]);
  }

  @Override
  public void saveAll(Cliente[] clientes) throws ProveedorAlmacenamientoClientesException {
    try (PrintWriter flujoSalida = new PrintWriter(new FileWriter(rutaFichero, false))) {
      for (Cliente cliente : clientes) {
        flujoSalida.printf("%s|%s|%s|%s|%s|%s%n", cliente.getNif(), cliente.getApellidos(), cliente.getNombre(),
            cliente.getEmpleados(), cliente.getFacturacion(), cliente.isNacionalUe());
        if (!listaClientes.contains(cliente)) {
          listaClientes.add(cliente);
        }
      }
    } catch (IOException e) {
      System.out.println("No se ha podido crear el archivo.");
      throw new ProveedorAlmacenamientoClientesException();
    }
  }
}
