package prog.unidad05.gestionclientes.consola;

import java.util.Scanner;

import prog.unidad05.gestionclientes.core.Cliente;
import prog.unidad05.gestionclientes.core.Clientes;
import prog.unidad05.gestionclientes.core.ClientesException;
import prog.unidad05.gestionclientes.core.ProveedorAlmacenamientoClientesException;
import prog.unidad05.gestionclientes.impl.ProveedorAlmacenamientoClientesFichero;

public class GestionClientesConsolaApp {

  private static final String ARCHIVO = "clientes.dat";

  private static final int OPCION_SALIR = 0;

  private static final int OPCION_LISTAR = 1;

  private static final int OPCION_NUEVO_CLIENTE = 2;

  private static final int OPCION_ACTUALIZAR_CLIENTE = 3;

  private static final int OPCION_ELIMINAR_CLIENTE = 4;

  private static final int OPCION_MINIMA = OPCION_SALIR;

  private static final int OPCION_MAXIMA = OPCION_ELIMINAR_CLIENTE;

  private static Clientes almacenClientes;

  private Scanner sc;
  private ProveedorAlmacenamientoClientesFichero proveedorClientes;
  private Clientes listadoClientes;
  private Cliente[] arrayClientes;

  public static void main(String[] args) {
    ProveedorAlmacenamientoClientesFichero proveedorClientes = new ProveedorAlmacenamientoClientesFichero(ARCHIVO);
    almacenClientes = new Clientes(proveedorClientes);

    GestionClientesConsolaApp app = new GestionClientesConsolaApp();
    app.run();

  }

  public GestionClientesConsolaApp() {
    sc = new Scanner(System.in);
    proveedorClientes = new ProveedorAlmacenamientoClientesFichero(ARCHIVO);

  }

  private void run() {
    // Muestra el menú repetidamente hasta que se elija la opción Salir
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

  private void comandoListarCliente() {
    System.out.println();
    System.out.println("LISTA DE CLIENTES");
    System.out.println("-----------------");
    try {
      listadoClientes = new Clientes(proveedorClientes);
      // Obtener la lista de clientes actualizada
      arrayClientes = proveedorClientes.getAll();
      // Guardar la lista actualizada de clientes
      proveedorClientes.saveAll(arrayClientes);
      for (Cliente cliente : arrayClientes) {
        System.out.printf("Nif: %s, %s, %s.%n", cliente.getNif(), cliente.getApellidos(), cliente.getNombre());
      }
    } catch (NullPointerException | ProveedorAlmacenamientoClientesException e) {
    }

  }

  private void comandoNuevoCliente() {
    
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
  
      boolean esNacional = respuestaUE.equalsIgnoreCase("s") ? true : false;
      
      try {
        Cliente cliente = new Cliente(nifCliente, apellidoCliente, nombreCliente, empleadosCliente, facturacionCliente, esNacional);
        listadoClientes.addCliente(cliente);
        arrayClientes = proveedorClientes.getAll();
        proveedorClientes.saveAll(arrayClientes);
      } catch (NullPointerException | ClientesException | ProveedorAlmacenamientoClientesException e) {
      }

  }

  private String comandoActualizarCliente() {
    return null;

  }

  private String comandoEliminarCliente() {
    return null;

  }

}
