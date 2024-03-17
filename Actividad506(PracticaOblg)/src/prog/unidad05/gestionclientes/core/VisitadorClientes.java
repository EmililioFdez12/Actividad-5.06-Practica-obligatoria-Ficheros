package prog.unidad05.gestionclientes.core;

/**
 * Interfaz que define los método que debe tener un visitador de clientes
 * Un visitador de clientes define un método (visita) que será llamado 
 * desde el almacén una vez por cada cliente del almacén.
 * Se emplea para recorrer el almacén si necesidad de conocer su implementación
 */
public interface VisitadorClientes {

  /**
   * Visita un cliente
   * @param cliente Cliente a visitar
   */
  public void visita(Cliente cliente);
}
