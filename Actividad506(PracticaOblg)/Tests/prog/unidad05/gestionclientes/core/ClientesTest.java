package prog.unidad05.gestionclientes.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClientesTest {

  @Test
  void testProveedorOk() {
    // Caso: Prueba a crear el almacén con un proveedor de acceso correcto
    // RE  : Se crea el almacén y no se lanza excepción. El almacen está vacío
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    assertDoesNotThrow(() -> new Clientes(proveedor));
    assertEquals(0, proveedor.size());
  }

  @Test
  void testProveedorNull() {
    // Caso: Prueba a crear el almacén con un proveedor nulo
    // RE  : No se crea el almacen y se lanza NullPointerException
    assertThrows(NullPointerException.class, () -> new Clientes(null));
  }

  @Test
  void testProveedorNoPuedeLeer() {
    // Caso: Prueba a crear el almacén que lanza excepcion al intentar acceder al fichero
    // RE  : No se crea el almacen y se lanza ClientesException
    // Creamos un objeto expreso para esta prueba
    ProveedorAlmacenamientoClientes proveedor = new ProveedorAlmacenamientoClientes() {
      
      @Override
      public void saveAll(Cliente[] clientes) {
        // Este no hace nada ya que no nos importa
      }
      
      @Override
      public Cliente[] getAll() {
        // No hace nada y lanza ProveedorAlmacenamientoClientesException
        throw new ProveedorAlmacenamientoClientesException();
      }
    };
    assertThrows(ClientesException.class, () -> new Clientes(proveedor));
  }

  @Test
  void testAddUnCliente() {
    // Caso: Crea el almacen y almacena un cliente
    // RE  : Todo Ok. No se lanza excepción y el cliente se ha añadido
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    Cliente cliente = new Cliente("62527456R", "Makako", "Pako", 10, 12345.67, true);
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    // Comprueba que contiene el cliente
    assertEquals(1, proveedor.size());
    assertTrue(proveedor.contains(cliente));
  }

  @Test
  void testAddNull() {
    // Caso: Crea el almacen y almacena un cliente null
    // RE  : Se lanza NullPointerException y el almacenamiento está vacío
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    Cliente cliente = null;
    assertThrows(NullPointerException.class, () -> clientes.addCliente(cliente));
    assertEquals(0, proveedor.size());
  }

  @Test
  void testAddClienteRepetido() {
    // Caso: Crea el almacen y almacena un cliente dos veces
    // RE  : La primera funciona. La segunda lanza ClientesException. El cliente está en el
    //       almacenamiento
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    Cliente cliente = new Cliente("62527456R", "Makako", "Pako", 10, 12345.67, true);
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertThrows(ClientesException.class, () -> clientes.addCliente(cliente));
    assertTrue(proveedor.contains(cliente));
  }

  @Test
  void testAddDosClientesDistintosYUnoRepetido() {
    // Caso: Crea el almacen y almacena un cliente, otro distinto y de nuevo el primero
    // RE  : La primera funciona. La segunda también. La tercera lanza ClientesException
    //       Sólo están los dos clientes en el almacenamiento
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    Cliente cliente = new Cliente("62527456R", "Makako", "Pako", 10, 12345.67, true);
    Cliente cliente2 = new Cliente("65043932R", "Makaki", "Paqui", 20, 89012.34, false);
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertDoesNotThrow(() -> clientes.addCliente(cliente2));
    assertThrows(ClientesException.class, () -> clientes.addCliente(cliente));
    // El almacenamiento contiene dos clientes y son nuestros dos clientes
    assertEquals(2, proveedor.size());
    assertTrue(proveedor.contains(cliente));
    assertTrue(proveedor.contains(cliente2));
  }

  @Test
  void testAddRemoveAddCliente() {
    // Caso: Crea el almacen y almacena un cliente, lo elimina y luego lo vuelve a añadir
    // RE  : Todas las operaciones funcionan correctamente y sólo hay un cliente, el nuestro
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    String nif = "62527456R";
    Cliente cliente = new Cliente(nif, "Makako", "Pako", 10, 12345.67, true);
    
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertDoesNotThrow(() -> clientes.removeCliente(nif));
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    // Sólo hay un cliente en el almacenamiento
    assertEquals(1, proveedor.size());
    // Y es el nuestro
    assertTrue(proveedor.contains(cliente));
  }

  @Test
  void testAddUpdateCliente() {
    // Caso: Crea el almacen y almacena un cliente y lo actualiza
    // RE  : Todas las operaciones funcionan correctamente
    //       Al final sólo hay un cliente en el almacenamiento y es igual al último
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    String nif = "62527456R";
    Cliente cliente = new Cliente(nif, "Makako", "Pako", 10, 12345.67, true);
    Cliente cliente2 = new Cliente(nif, "Makaka", "Paka", 50, 75642.77, false);
    
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertDoesNotThrow(() -> clientes.updateCliente(cliente2));
    // Sólo hay un cliente en el almacenaminamiento
    assertEquals(1, proveedor.size());
    // Y es el segundo, no el primero
    assertTrue(proveedor.contains(cliente2));
    assertFalse(proveedor.contains(cliente));
  }

  @Test
  void testUpdateClienteNull() {
    // Caso: Crea el almacen y intenta actualizar un cliente null
    // RE  : Se lanza NullPointerException y en almacenamiento no hay clientes
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    Cliente cliente = null;    
    assertThrows(NullPointerException.class, () -> clientes.updateCliente(cliente));
    assertEquals(0, proveedor.size());
  }

  @Test
  void testUpdateClienteInexistente() {
    // Caso: Crea el almacen y almacena un cliente y lo actualiza pero usando un nif inexistente
    // RE  : La adición funciona pero la modificación lanza ClientesException
    //       En almacenamiento sólo hay un cliente y es idéntico al primero, no al segundo
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    String nif = "62527456R";
    String nif2 = "23084581X";
    Cliente cliente = new Cliente(nif, "Makako", "Pako", 10, 12345.67, true);
    Cliente cliente2 = new Cliente(nif2, "Makaka", "Paka", 50, 75642.77, false);
    
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertThrows(ClientesException.class, () -> clientes.updateCliente(cliente2));
    // Sólo un cliente en almacenamiento
    assertEquals(1, proveedor.size());
    assertTrue(proveedor.contains(cliente));
    assertFalse(proveedor.contains(cliente2));
  }

  @Test
  void testAddRemoveTwice() {
    // Caso: Crea el almacen y almacena un cliente e intenta eliminarlo dos veces
    // RE  : La adición y la primera eliminación funcionan pero la segunda lanza ClientesException
    //       El almacenamiento está vacío
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    String nif = "62527456R";
    Cliente cliente = new Cliente(nif, "Makako", "Pako", 10, 12345.67, true);
    
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertDoesNotThrow(() -> clientes.removeCliente(nif));
    assertThrows(ClientesException.class, () -> clientes.removeCliente(nif));
    assertEquals(0, proveedor.size());
  }

  @Test
  void testRemoveNull() {
    // Caso: Crea el almacen e intenta eliminar un cliente con nif null
    // RE  : La eliminación falla con NullPointerException y el almacenamiento está vacío
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);

    assertThrows(NullPointerException.class, () -> clientes.removeCliente(null));
    assertEquals(0,  proveedor.size());
  }

  @Test
  void testVisitador() {
    // Caso: Crea el almacen, añade un par de clientes y los visita
    // RE  : Todo funciona, sólo hay dos clientes y son los insertados
    ProveedorAlmacenamientoClientesMock proveedor = new ProveedorAlmacenamientoClientesMock();
    Clientes clientes = new Clientes(proveedor);
    String nif = "62527456R";
    String nif2 = "23084581X";
    Cliente cliente = new Cliente(nif, "Makako", "Pako", 10, 12345.67, true);
    Cliente cliente2 = new Cliente(nif2, "Makaka", "Paka", 50, 75642.77, false);
    assertDoesNotThrow(() -> clientes.addCliente(cliente));
    assertDoesNotThrow(() -> clientes.addCliente(cliente2));
    // Hacemos la visita
    Contador contador = new Contador();
    contador.cuenta = 0;
    clientes.visita(new VisitadorClientes() {
      
      @Override
      public void visita(Cliente cliente) {
        contador.cuenta++;
        assertTrue(proveedor.contains(cliente));
      }
    });
    // Debe haber contado dos clientes
    assertEquals(2, contador.cuenta);
    
  }

}

class Contador {
  public int cuenta;
}