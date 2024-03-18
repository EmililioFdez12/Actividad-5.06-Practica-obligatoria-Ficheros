package prog.unidad05.gestionclientes.consola;

import java.util.Scanner;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.Clientes;
import prog.unidad05.gestionclientes.core.ClientesException;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;
import prog.unidad05.gestionclientes.impl.ProveedorAlmacenamientoClientesFichero;

public class GestionClientesConsolaApp {
  // Atributos
  // Ruta donde se va a crear el archivo
  private static final String ARCHIVO = "clientes.dat";

  // Opciones
  private static final int OPCION_SALIR = 0;

  private static final int OPCION_LISTAR = 1;

  private static final int OPCION_NUEVO_CLIENTE = 2;

  private static final int OPCION_ACTUALIZAR_CLIENTE = 3;

  private static final int OPCION_ELIMINAR_CLIENTE = 4;

  private static final int OPCION_MINIMA = OPCION_SALIR;

  private static final int OPCION_MAXIMA = OPCION_ELIMINAR_CLIENTE;

  // Abrimos el scanner
  private Scanner sc;
  // Creamos el almacenamiento secundario
  private ProveedorAlmacenamientoClientesFichero proveedorClientes;
  // Almacenamiento de clientes
  private Clientes clientes;
  // Array de clientes
  private Cliente[] arrayClientes;

  public static void main(String[] args) {
    // Iniciamos la aplicación
    GestionClientesConsolaApp app = new GestionClientesConsolaApp();
    app.run();
  }

  public GestionClientesConsolaApp() {
    sc = new Scanner(System.in);
    // Metemmos la ruta del fichero
    proveedorClientes = new ProveedorAlmacenamientoClientesFichero(ARCHIVO);
    // Rellenamos el almacen de clientes con los clientes actuales
    clientes = new Clientes(proveedorClientes);
  }

  // Metodo para iniciar la aplicacion
  private void run() {
    try {
      // Obtenemos la lista de clientes del archivo al inicio
      arrayClientes = proveedorClientes.getAll();
    } catch (ProveedorAlmacenamientoClientesException e) {
      System.out.println("Error al obtener los clientes del archivo.");
      // Salir del método run() si hay un error
      return;
    }
    // Muestramos el menú repetidamente hasta que se elija la opción Salir
    int opcionElegida = OPCION_SALIR;
    do {
      // Muestra el menú y obtiene una elección
      opcionElegida = mostrarMenu();
      // Según la opción elegida
      switch (opcionElegida) {
      case OPCION_LISTAR:
        comandoListarCliente();
        break;
      case OPCION_NUEVO_CLIENTE:
        comandoNuevoCliente();
        break;
      case OPCION_ACTUALIZAR_CLIENTE:
        comandoActualizarCliente();
        break;
      case OPCION_ELIMINAR_CLIENTE:
        comandoEliminarCliente();
        break;
      case OPCION_SALIR:
        break;
      default:
        // No se debe llegar aqui
        System.out.println("Error. Opción incorrecta.");
      }
    } while (opcionElegida != OPCION_SALIR);
  }

  private int mostrarMenu() {
    // Inicializamos la opción elegida a un valor invalido
    int opcion = OPCION_MINIMA - 1;
    // Mientras no se elija una opción correcta
    for (;;) {
      // Mostramos el menu
      System.out.println();
      System.out.println("MENU PRINCIPAL");
      System.out.println("--------------");
      System.out.println("0. Salir del programa");
      System.out.println("1. Listar clientes");
      System.out.println("2. Nuevo clientes");
      System.out.println("3. Actualizar cliente");
      System.out.println("4. Eliminar Cliente");
      System.out.print("Elija una opción (" + OPCION_MINIMA + "-" + OPCION_MAXIMA + "): ");
      try {
        opcion = Integer.parseInt(sc.nextLine());
        // Si la opción está en rango se devuelve. Si no se muestra error y se da otra
        // vuelta
        if (opcion >= OPCION_MINIMA && opcion <= OPCION_MAXIMA) {
          return opcion;
        } else {
          System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
              + OPCION_MINIMA + " y " + OPCION_MAXIMA);
        }
      } catch (NumberFormatException e) {
        System.out.println("Opción elegida incorrecta. Debe introducir un número" + " comprendido entre "
            + OPCION_MINIMA + " y " + OPCION_MAXIMA);
      }
    }
  }

  // Comando para listar los clientes por consola
  private void comandoListarCliente() {
    System.out.println();
    System.out.println("LISTA DE CLIENTES");
    System.out.println("-----------------");
    try {
      // Obtenemos los clientes del archivo y lo metemos en un array en memoria
      arrayClientes = proveedorClientes.getAll();
      for (Cliente cliente : arrayClientes) {
        // Listamos los clientes
        System.out.printf("Nif: %s, %s, %s.%n", cliente.getNif(), cliente.getApellidos(), cliente.getNombre());
      }
    } catch (NullPointerException | ProveedorAlmacenamientoClientesException e) {
    }
  }

  // Método para añadir un cliente
//Método para añadir un cliente
  private void comandoNuevoCliente() {
    try {
      try {
        // Pedimos los datos del cliente
        System.out.println();
        System.out.println("NUEVO CLIENTE");
        System.out.println("--------------");
        System.out.println("Introduzca los datos del nuevo cliente");
        System.out.print("NIF (8 números y la letra correspondiente):");
        String nifCliente = sc.nextLine();
        System.out.print("Nombre(Primera letra de cada palabra mayúsculas):");
        String nombreCliente = sc.nextLine();
        System.out.print("Apellidos(Primera letra de cada palabra mayúsculas):");
        String apellidoCliente = sc.nextLine();
        System.out.print("Nº de Empleados(entero mayor que 0):");
        int empleadosCliente = Integer.parseInt(sc.nextLine());
        System.out.print("Facturación (real superior a cero):");
        double facturacionCliente = Double.parseDouble(sc.nextLine());
        System.out.print("¿Es nacional de un país de la UE? (s/n)");
        String respuestaUE = sc.nextLine();

        boolean esNacional = respuestaUE.equalsIgnoreCase("s");
        // Creamos el cliente
        Cliente cliente = new Cliente(nifCliente, apellidoCliente, nombreCliente, empleadosCliente, facturacionCliente,
            esNacional);
        // Añadimos el cliente
        clientes.addCliente(cliente);
        // Sobreescribimos en el archivo
        // Se borra todo y se vuelve a escribir en el archivo con el nuevo cliente
        Cliente[] arrayAnyadirClientes = new Cliente[arrayClientes.length + 1];
        System.arraycopy(arrayClientes, 0, arrayAnyadirClientes, 0, arrayClientes.length);
        arrayAnyadirClientes[arrayClientes.length] = cliente;
        proveedorClientes.saveAll(arrayAnyadirClientes);
      } catch (NumberFormatException e) {
        System.out.println("Error al procesar la solicitud. Por favor, asegúrese de que los datos sean válidos.");
      }
    } catch (NumberFormatException | NullPointerException | ClientesException
        | ProveedorAlmacenamientoClientesException e) {
    }
  }

  private void comandoActualizarCliente() {
    System.out.println();
    System.out.println("ACTUALIZAR CLIENTE");
    System.out.println("--------------");
    System.out.println("Introduzca el NIF del cliente a modificar");
    System.out.print("NIF (8 números y la letra correspondiente): ");
    String nifCliente = sc.nextLine();
    try {
      // Obtener el cliente a modificar
      Cliente clienteAModificar = clientes.getByNif(nifCliente);

      // Pedimos los datos para modificar el cliente
      if (clienteAModificar != null) {
        System.out.println("Introduzca los nuevos datos del cliente");
        System.out.print("Nombre (Primera letra de cada palabra mayúsculas): ");
        String nombreCliente = sc.nextLine();
        System.out.print("Apellidos (Primera letra de cada palabra mayúsculas): ");
        String apellidoCliente = sc.nextLine();
        System.out.print("Nº de Empleados (entero mayor que 0): ");
        int empleadosCliente = Integer.parseInt(sc.nextLine());
        System.out.print("Facturación (real superior a cero): ");
        double facturacionCliente = Double.parseDouble(sc.nextLine());
        System.out.print("¿Es nacional de un país de la UE? (s/n): ");
        String respuestaUE = sc.nextLine();
        boolean esNacional = respuestaUE.equalsIgnoreCase("s");

        // Crear el cliente actualizado
        Cliente clienteActualizado = new Cliente(nifCliente, apellidoCliente, nombreCliente, empleadosCliente,
            facturacionCliente, esNacional);
        // Actualizamos el cliente
        clientes.updateCliente(clienteActualizado);
      } else {
        System.out.println("No se encontró ningún cliente con el NIF proporcionado.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: ¡Debe ingresar un número válido para el número de empleados o la facturación!");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: Los datos proporcionados no son válidos. Asegúrese de seguir las instrucciones.");
    } catch (ClientesException e) {
      System.out.println("Error al actualizar el cliente.");
    } catch (ProveedorAlmacenamientoClientesException e) {
      System.out.println("Error al acceder a los clientes.");
    }
  }

  // Método para eliminar un cliente
  private void comandoEliminarCliente() {
    System.out.println();
    System.out.println("ELIMINAR CLIENTE");
    System.out.println("----------------");
    System.out.print("Introduzca el NIF del cliente a eliminar:");
    String nifAEliminar = sc.nextLine();

    try {
      // Eliminamos el cliente
      clientes.removeCliente(nifAEliminar);
    } catch (ClientesException e) {
      System.out.println("ERROR. No se encuentra ningun cliente con ese nif");
    }
  }

}
