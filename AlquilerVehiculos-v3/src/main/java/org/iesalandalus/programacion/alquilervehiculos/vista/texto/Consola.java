package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);
	private static final String PATRON_MES = "MM/yyyy";
	
	private Consola() {
		
	}

	public static void mostrarMenu() {
		 System.out.println("MENÚ DEL PROGRAMA");
		 System.out.println("-----------------");
		 
		for (Accion accion : Accion.values()) {
			System.out.println(accion);
		}
		System.out.println("");
	}
	
	public static void mostrarCabecera(String mensaje) {
		int longitud = mensaje.length();
		
		System.out.println(mensaje);
		for(int i = 0; i < longitud; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		String cadena = Entrada.cadena();
		
		return cadena;
	}

	private static Integer leerEntero(String mensaje) {
		System.out.print(mensaje);
		int entero = Entrada.entero();
		
		return entero;
	}

	private static LocalDate leerFecha(String mensaje, String patron) {
		System.out.print(mensaje);
		LocalDate fecha = null;
		
			try {
				if(PATRON_FECHA.equals(patron)) {
					fecha = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
				}else {
					fecha = LocalDate.parse("13/" + Entrada.cadena(), FORMATO_FECHA);
				}
			}catch(DateTimeParseException e) {
				System.out.println("ERROR: El formato de la fecha introducida no es válida.");
			}

		return fecha;
	}

	public static Accion elegirAccion() {
		int accionElegida=0;
		
		do{
			accionElegida = leerEntero("Introduce la acción a realizar (0-18): ");
		}while (accionElegida<0 || accionElegida>18);
		
		return Accion.get(accionElegida);
	}

	public static Cliente leerCliente() {
		String nombre = leerCadena("Introduzca el nombre del cliente: ");
		String dni = leerCadena("Introduzca el dni del cliente: ");
		String telefono = leerCadena("Introduzca el teléfono del cliente: ");
		return new Cliente(nombre, dni, telefono);
	}

	public static Cliente leerClienteDni() {
		String dni = leerCadena("Introduzca el dni del cliente a buscar: ");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		String nombre = leerCadena("Introduzca el nombre del cliente: ");
		return nombre;
	}

	public static String leerTelefono() {
		String telefono = leerCadena("Introduzca el teléfono del cliente: ");
		return telefono;
	}

	private static void mostrarMenuTiposVehiculos() {
		 System.out.println("¿Que tipo de vehículo quiere escoger?:");
		 System.out.println("");
		 
		for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
			System.out.println(tipoVehiculo);
		}
		System.out.println("");
	}
	
	private static TipoVehiculo elegirTipoVehiculo() {
		int tipoVehiculoElegido = -1;
		
		do{
			tipoVehiculoElegido = leerEntero("Introduzca el tipo a elegir (0-2): ");
		}while (tipoVehiculoElegido<0 || tipoVehiculoElegido>2);
		
		return TipoVehiculo.get(tipoVehiculoElegido);
	}
	
	public static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculoElegido = null;
		
		mostrarMenuTiposVehiculos();
		TipoVehiculo tipoVehiculoElegido = elegirTipoVehiculo();
		
		String marca = leerCadena("Introduzca la marca del vehículo: ");
		String modelo = leerCadena("Introduzca el módelo del vehículo: ");
		String matricula = leerCadena("Introduzca la matrícula del vehículo: ");
		
		if(tipoVehiculoElegido == TipoVehiculo.TURISMO) {
		
		int cilindrada = leerEntero("Introduzca la cilindrada del vehículo: ");
		
		vehiculoElegido = new Turismo(marca, modelo, cilindrada, matricula);
		
		}else if(tipoVehiculoElegido == TipoVehiculo.AUTOBUS) {
			int plazas = leerEntero("Introduzca las plazas que tiene el vehículo: ");
			
			vehiculoElegido = new Autobus(marca, modelo, plazas, matricula);
			
		}else if(tipoVehiculoElegido == TipoVehiculo.FURGONETA) {
			int pma = leerEntero("Introduzca el PMA del vehículo:");
			int plazas = leerEntero("Introduzca las plazas que tiene el vehículo: ");
			
			vehiculoElegido = new Furgoneta(marca, modelo, pma, plazas, matricula);
		}
		
		return vehiculoElegido;
	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = leerCadena("Introduzca la matrícula a buscar: ");
		return Vehiculo.getVehiculoConMatricula(matricula);
	}

	public static Alquiler leerAlquiler() {
		Cliente cliente = leerClienteDni();
		Vehiculo turismo = leerVehiculoMatricula();
		
		LocalDate fechaAlquiler = leerFecha("Introduzca la fecha de alquiler: ", PATRON_FECHA);
		
		return new Alquiler(cliente, turismo, fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() {
		LocalDate fechaDevolucion = leerFecha("Introduzca la fecha de devolución: ", PATRON_FECHA);
		
		return fechaDevolucion;
	}
	
	public static LocalDate leerMes() {
		LocalDate mes = leerFecha("Introduzca un mes y año: ", PATRON_MES);
		
		return mes;
	}
}

