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
  
  /**
   * Constructor con ruta
   * 
   * @param rutaFichero Ruta al fichero a emplear para leer y almacenar los
   *                    clientes
   * @throws NullPointerException Si la ruta no es nula
   */
  public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
    this.rutaFichero = rutaFichero;
  }


  @Override
  public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
    List<Cliente> listaClientes = new ArrayList<>();
    String linea = null;
    try (BufferedReader flujoEntrada = new BufferedReader(new FileReader(rutaFichero))) {
      while ((linea = flujoEntrada.readLine()) != null) {
        String[] datosCliente = linea.split("\\|");
        Cliente cliente = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2],
            Integer.parseInt(datosCliente[3]), Double.parseDouble(datosCliente[4]),
            Boolean.parseBoolean(datosCliente[5]));
        listaClientes.add(cliente);
      }
    } catch (FileNotFoundException e) {
    } catch (IOException e) {
      System.out.println("No se puede leer el archivo");
    }
    return listaClientes.toArray(new Cliente[0]);
  }

  @Override
  public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException {
    try (PrintWriter flujoSalida = new PrintWriter(new FileWriter(rutaFichero, false))) {
      for (Cliente cliente : clientes) {
        flujoSalida.printf("%s|%s|%s|%s|%s|%s%n", cliente.getNif(), cliente.getApellidos(), cliente.getNombre(),
            cliente.getEmpleados(), cliente.getFacturacion(), cliente.isNacionalUe());
      }
    } catch (IOException e) {
      System.out.println("No se ha podido crear el archivo.");
      e.printStackTrace();
    }
  }

    public static void main(String[] args) throws ProveedorAlmacenamientoClientesException, NullPointerException, ClientesException {
      Cliente cliente = new Cliente("77687485V", "Rodriguez", "Pepin", 20, 25.5, false);
      Cliente cliente2 = new Cliente("67071237V", "Fernandez", "Lolito", 500, 100000, true);
      Cliente cliente3 = new Cliente("76750075H", "Julian", "Lolito", 500, 100000, true);
      String ruta = "clientes.dat";

      ProveedorAlmacenamientoClientesFichero proveedor = new ProveedorAlmacenamientoClientesFichero(ruta);
      Clientes clientes = new Clientes(proveedor);
      clientes.addCliente(cliente);
      clientes.addCliente(cliente2);

      // Obtener la lista de clientes actualizada después de agregar cliente3
      Cliente[] arrayCliente = proveedor.getAll();

      clientes.addCliente(cliente3);
      
      // Obtener la lista de clientes actualizada después de agregar cliente3
      arrayCliente = proveedor.getAll();

      // Guardar la lista actualizada de clientes
      proveedor.saveAll(arrayCliente);

  }

}
