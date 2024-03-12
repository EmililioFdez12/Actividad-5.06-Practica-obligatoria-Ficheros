package prog.unidad05.gestionclientes.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClienteTest {

  @Test
  void testClienteOk() {
    // Caso: Inserta un cliente con datos correctos
    // RE  : El cliente se crea correctamente y los datos son los introducidos
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    // Creamos el objeto y no debe lanzar excepcion
    Cliente cliente = null;
    try {
      cliente = new Cliente(nif, apellidos, nombre, empleados, facturacion, esNacionalUe);
    } catch (Exception e) {
      fail("No se pudo crear el Cliente");
    }
    
    // Comprueba que los datos son identicos a los empleados en la creación
    assertEquals(nif, cliente.getNif());
    assertEquals(apellidos, cliente.getApellidos());
    assertEquals(nombre, cliente.getNombre());
    assertEquals(empleados, cliente.getEmpleados());
    assertEquals(facturacion, cliente.getFacturacion());
    assertEquals(esNacionalUe, cliente.isNacionalUe());
  }

  @Test
  void testClienteOkApellidoRaro() {
    // Caso: Inserta un cliente con datos correctos. Los apellidos contienen caracteres raros (ñ y
    //       acentos)
    // RE  : El cliente se crea correctamente y los datos son los introducidos
    String nif = "73370382Z";
    String apellidos = "García Muñoz";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    // Creamos el objeto y no debe lanzar excepcion
    Cliente cliente = null;
    try {
      cliente = new Cliente(nif, apellidos, nombre, empleados, facturacion, esNacionalUe);
    } catch (Exception e) {
      fail("No se pudo crear el Cliente");
    }
    
    // Comprueba que los datos son identicos a los empleados en la creación
    assertEquals(nif, cliente.getNif());
    assertEquals(apellidos, cliente.getApellidos());
    assertEquals(nombre, cliente.getNombre());
    assertEquals(empleados, cliente.getEmpleados());
    assertEquals(facturacion, cliente.getFacturacion());
    assertEquals(esNacionalUe, cliente.isNacionalUe());
  }

  @Test
  void testClienteOkNombreRaro() {
    // Caso: Inserta un cliente con datos correctos. El nombre contiene caracteres raros (ñ y
    //       acentos)
    // RE  : El cliente se crea correctamente y los datos son los introducidos
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Iñaki Luís";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    // Creamos el objeto y no debe lanzar excepcion
    Cliente cliente = null;
    try {
      cliente = new Cliente(nif, apellidos, nombre, empleados, facturacion, esNacionalUe);
    } catch (Exception e) {
      fail("No se pudo crear el Cliente");
    }
    
    // Comprueba que los datos son identicos a los empleados en la creación
    assertEquals(nif, cliente.getNif());
    assertEquals(apellidos, cliente.getApellidos());
    assertEquals(nombre, cliente.getNombre());
    assertEquals(empleados, cliente.getEmpleados());
    assertEquals(facturacion, cliente.getFacturacion());
    assertEquals(esNacionalUe, cliente.isNacionalUe());
  }

  @Test
  void testClienteNifNull() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que es null
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = null;
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifVacio() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que es vacio
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifCorto() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que es corto
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "7337038Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifLargo() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que es largo
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "733703822Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifLetraInicio() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que contiene una letra al inicio en lugar de un número
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "a3370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifLetraMedio() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, que contiene una letra en medio en lugar de un número
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "7337a382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifLetraIncorrecta() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, cuya letra no se corresponde con el número
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Y";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNifLetraMinuscula() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el NIF, cuya letra es correcta pero minúscula
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
      , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteApellidosNull() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando los apellidos que son null
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = null;
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteApellidosVacio() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando los apellidos que es la cadena vacía
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteApellidosBlancos() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando los apellidos que es contiene sólo blancos
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "  ";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteApellidosUnaSolaMinuscula() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando los apellidos que contiene una sola palabra en minúscula
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "garcia";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteApellidosDosSegundaMinusculas() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando los apellidos que contiene dos palabras, la primera en mayúsculas y la
    //       segunda no
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia marquez";
    String nombre = "Juan Antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNombreNull() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el nombre que es null
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = null;
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNombreVacio() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el nombre que es la cadena vacía
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNombreBlancos() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el nombre que es contiene sólo blancos
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "  ";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNombreUnaSolaMinuscula() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el nombre que contiene una sola palabra en minúscula
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "juan";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteNombreDosSegundaMinusculas() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el nombre que contiene dos palabras, la primera en mayúsculas y la
    //       segunda no
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan antonio";
    int empleados = 10;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteEmpleadosCero() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el número de empleados que es cero
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan antonio";
    int empleados = 0;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteEmpleadosNegativo() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando el número de empleados que es negativo
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan antonio";
    int empleados = -1;
    double facturacion = 1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteFacturacionCero() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando la facturación que es cero
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan antonio";
    int empleados = 10;
    double facturacion = 0;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

  @Test
  void testClienteFacturacionNegativa() {
    // Caso: Inserta un cliente con datos correctos
    //       exceptuando la facturación que es negativa
    // RE  : El cliente no se crea y se lanza IllegalArgumentException
    String nif = "73370382Z";
    String apellidos = "Garcia Marquez";
    String nombre = "Juan antonio";
    int empleados = 10;
    double facturacion = -1234.56;
    boolean esNacionalUe = true;
    
    assertThrows(IllegalArgumentException.class, () -> new Cliente(nif, apellidos, nombre
        , empleados, facturacion, esNacionalUe));
  }

}
