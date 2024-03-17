package prog.unidad05.gestionclientes.core;

/**
 * Información sobre un cliente
 */
public class Cliente {

	// Atributos
	private String nif;
	private String apellidos;
	private String nombre;
	private int empleados;
	private double facturacion;
	private boolean nacionalUe;

	/**
	 * Constructor con toda la información Ningún parámetro puede ser null
	 * 
	 * @param nif         Nif del cliente
	 * @param apellidos   Apellido del cliente
	 * @param nombre      Nombre del cliente
	 * @param empleados   Empleados del cliente
	 * @param facturacion Facturación anual del cliente. Debe ser superior a cero
	 * @param nacionalUe  true si el cliente es nacional de un pais de la UE. false
	 *                    si no lo es
	 * @throws IllegalArgumentException Si alguno de los parámetros no es válido
	 */
	public Cliente(String nif, String apellidos, String nombre, int empleados, double facturacion, boolean nacionalUe) {
		// Validamos el nif
		if (validarNif(nif)) {
			this.nif = nif;
		} else {
			throw new IllegalArgumentException();
		}

		if (apellidos == null || apellidos.isBlank() || !validaApellido(apellidos)) {
			throw new IllegalArgumentException();
		}
		this.apellidos = apellidos;

		if (nombre == null || nombre.isBlank() || !validaNombre(nombre)) {
			throw new IllegalArgumentException();
		}
		this.nombre = nombre;

		if (empleados > 0) {
			this.empleados = empleados;
		} else {
			throw new IllegalArgumentException();
		}

		if (facturacion > 0) {
			this.facturacion = facturacion;
		} else {
			throw new IllegalArgumentException();
		}

		this.nacionalUe = nacionalUe;
	}

	/**
	 * Obtiene los apellidos del cliente
	 * 
	 * @return Apellidos del cliente
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Obtiene el número de empleados del cliente
	 * 
	 * @return Número de empleados del cliente
	 */
	public int getEmpleados() {
		return empleados;
	}

	/**
	 * Obtiene la facturación del cliente
	 * 
	 * @return Facturacion del cliente, en euros
	 */
	public double getFacturacion() {
		return facturacion;
	}

	/**
	 * Obtiene el nif del cliente
	 * 
	 * @return Nif del cliente
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Obtiene el nombre del cliente
	 * 
	 * @return Nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene si el cliente es nacional de un pais de la UE o no
	 * 
	 * @return true si el cliente es nacional de un pais de la UE, false si no lo es
	 */
	public boolean isNacionalUe() {
		return nacionalUe;
	}

	// Metodo para comprobar si el dni es valido
	private boolean validarNif(String dni) {
		boolean valida = false;

		// Si es null o no tiene 9 carácteres el dni no es correcto.
		if (dni == null || dni.length() != 9) {
			return false;
		}
		// Sacamos la cadena de numeros
		String cadenaNumerosDni = dni.substring(0, 8);
		// Sacamos la letra del DNI
		char letraDni = dni.charAt(8);

		try {
			// Pasamos la cadena de numeros a Enteros
			int numerosDni = Integer.parseInt(cadenaNumerosDni);

			// Segun el resto que de, pertenece a una letra del array, (Estan en orden)
			int resto = numerosDni % 23;
			// Letras posibles en cada posicion segun su resto
			// Es decir si el resto es 1 la letra deberia ser T
			char[] letrasPosibles = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
					'V', 'H', 'L', 'C', 'K', 'E' };

			if (letrasPosibles[resto] == letraDni) {
				valida = true;
			}
		} catch (NumberFormatException e) {
			// Si los 8 primeros caracteres no son números
			return false;
		}
		return valida;
	}

	// Método para validar el apellido
	private boolean validaApellido(String apellidos) {
		boolean valida = true;
		// Dividimos para cuando tengan dos apellidos
		String[] apellidoCliente = apellidos.split("\\s+");
		// Para cada apellido comprobamos si la letra que empieza es mayúscula
		for (String apellido : apellidoCliente) {
			if (!Character.isUpperCase(apellido.charAt(0))) {
				return false;
			}
		}
		return valida;
	}

//Método para validar el apellido
	private boolean validaNombre(String apellidos) {
		boolean valida = true;
		// Dividimos para cuando tenga nombre compuesto
		String[] nombreCliente = apellidos.split("\\s+");
		// Para cada "nombre" comprobamos si la letra que empieza es mayúscula
		for (String nombre : nombreCliente) {
			if (!Character.isUpperCase(nombre.charAt(0))) {
				return false;
			}
		}
		return valida;
	}
}
