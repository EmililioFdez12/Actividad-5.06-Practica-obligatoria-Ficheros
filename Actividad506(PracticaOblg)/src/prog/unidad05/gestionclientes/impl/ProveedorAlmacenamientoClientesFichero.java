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
import prog.unidad05.gestionclientes.core.Clientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientes;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;

/**
 * Proveedor de almacenamiento de clientes que almacena los datos de los
 * clientes en un fichero en disco. Si el fichero no existe se crea la primera
 * vez que se guarde y devuelve una colección vacía de clientes al leer.
 */
public class ProveedorAlmacenamientoClientesFichero implements ProveedorAlmacenamientoClientes {

  private static final String RUTA_FICHERO = "clientes.dat";

  /**
   * Constructor con ruta
   * 
   * @param rutaFichero Ruta al fichero a emplear para leer y almacenar los
   *                    clientes
   * @throws NullPointerException Si la ruta no es nula
   */
  public ProveedorAlmacenamientoClientesFichero(String rutaFichero) {
    generaArchivo(RUTA_FICHERO);
  }

  @Override
  public Cliente[] getAll() throws ProveedorAlmacenamientoClientesException {
    return null;
  }

  @Override
  public void saveAll(Cliente[] clientes) throws NullPointerException, ProveedorAlmacenamientoClientesException {
    // TODO Auto-generated method stub
  }

  // Genera un archivo vacio
  private void generaArchivo(String rutaFichero) {
    try (PrintWriter flujoSalida = new PrintWriter(new FileWriter(rutaFichero))) {

    } catch (IOException e) {
      System.out.println("No se ha podido crear el archivo.");
      e.printStackTrace();
    }
  }
  
 
}
