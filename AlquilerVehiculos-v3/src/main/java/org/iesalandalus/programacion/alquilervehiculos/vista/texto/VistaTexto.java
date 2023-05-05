package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

import javafx.stage.Stage;

public class VistaTexto extends Vista{
	
	public VistaTexto() {
		super();
	}
	
	public void comenzar() {
		Accion accion = null;
		
		do {
		Consola.mostrarMenu();
		accion = Consola.elegirAccion();
		ejecutar(accion);
		}while(accion != Accion.SALIR);
	}
	
	public void terminar() {
		controlador.terminar();
		System.out.println("La vista ha terminado.");
	}
	
	private void ejecutar(Accion opcion) {
		switch(opcion.ordinal()) {
		case 0:
			terminar();
			break;
			
		case 1:
			insertarCliente();
			break;
			
		case 2:
			insertarVehiculo();
			break;
			
		case 3:
			insertarAlquiler();
			break;
			
		case 4:
			buscarCliente();
			break;
			
		case 5:
			buscarVehiculo();
			break;
			
		case 6:
			buscarAlquiler();
			break;
			
		case 7:
			modificarCliente();
			break;
			
		case 8:
			devolverAlquilerCliente();
			break;
			
		case 9:
			devolverAlquilerVehiculo();
			break;
			
		case 10:
			borrarCliente();
			break;
			
		case 11:
			borrarVehiculo();
			break;
			
		case 12:
			borrarAlquiler();
			break;
			
		case 13:
			listarClientes();
			break;
			
		case 14:
			listarVehiculos();
			break;
			
		case 15:
			listarAlquileres();
			comenzar();
			break;
			
		case 16:
			listarAlquileresCliente();
			break;
			
		case 17:
			listarAlquileresVehiculo();
			break;
			
		case 18:
			mostrarEstadisticasMensualesTipoVehiculo();
			break;
		}
	}
	
	protected void insertarCliente() {
		Consola.mostrarCabecera(Accion.INSERTAR_CLIENTE.toString());
		
		try {
			controlador.insertar(Consola.leerCliente());
			System.out.println("EXITO: Se ha introducido exitosamente el cliente.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
	
	protected void insertarVehiculo() {
		Consola.mostrarCabecera(Accion.INSERTAR_VEHICULO.toString());
		
		try {
			controlador.insertar(Consola.leerVehiculo(null));
			System.out.println("EXITO: Se ha introducido exitosamente el vehículo.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void insertarAlquiler() {
		Consola.mostrarCabecera(Accion.INSERTAR_ALQUILER.toString());
		
		try {
			controlador.insertar(Consola.leerAlquiler());
			System.out.println("EXITO: Se ha introducido exitosamente el alquiler.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void buscarCliente() {
		Cliente cliente;
		Consola.mostrarCabecera(Accion.BUSCAR_CLIENTE.toString());
		
		try {
			cliente = controlador.buscar(Consola.leerClienteDni());
			String mensaje = (cliente != null) ? cliente.toString() : "ERROR: No existe el cliente introducido.";
			System.out.println(mensaje);
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException| OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void buscarVehiculo() {
		Vehiculo vehiculo;
		Consola.mostrarCabecera(Accion.BUSCAR_VEHICULO.toString());
		
		try {
			vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
			String mensaje = (vehiculo != null) ? vehiculo.toString() : "ERROR: No existe el vehículo introducido.";
			System.out.println(mensaje);
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void buscarAlquiler() {
		Alquiler alquiler;
		Consola.mostrarCabecera(Accion.BUSCAR_ALQUILER.toString());
		
		try {
			alquiler = controlador.buscar(Consola.leerAlquiler());
			String mensaje = (alquiler != null) ? alquiler.toString() : "ERROR: No existe el alquiler introducido.";
			System.out.println(mensaje);
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void modificarCliente() {
		Consola.mostrarCabecera(Accion.MODIFICAR_CLIENTE.toString());
		
		try {
			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("EXITO: Se ha modificado el cliente con exito.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void devolverAlquilerCliente() {
		Consola.mostrarCabecera(Accion.DEVOLVER_ALQUILER_CLIENTE.toString());
		
		try {
			controlador.devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.println("EXITO: Se ha devuelto el alquiler exitosamente.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
	
	protected void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera(Accion.DEVOLVER_ALQUILER_VEHICULO.toString());
		
		try {
			controlador.devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.println("EXITO: Se ha devuelto el alquiler exitosamente.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void borrarCliente() {
		Consola.mostrarCabecera(Accion.BORRAR_CLIENTE.toString());
		
		try {
			controlador.borrar(Consola.leerClienteDni());
			System.out.println("EXITO: Se ha borrado el cliente con exito.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void borrarVehiculo() {
		Consola.mostrarCabecera(Accion.BORRAR_VEHICULO.toString());
		
		try {
			controlador.borrar(Consola.leerVehiculoMatricula());
			System.out.println("EXITO: Se ha borrado el vehículo con exito.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void borrarAlquiler() {
		Consola.mostrarCabecera(Accion.BORRAR_ALQUILER.toString());
		
		try {
			controlador.borrar(Consola.leerAlquiler());
			System.out.println("EXITO: Se ha borrado el alquiler con exito.");
			System.out.println("");
		} catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void listarClientes() {
		Consola.mostrarCabecera(Accion.LISTAR_CLIENTES.toString());
		
		try {
			List<Cliente> clientes = controlador.getClientes();
			clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
			
			if(clientes.isEmpty()) {
				System.out.println("ERROR: No hay ningún cliente introducido.");
				System.out.println("");
			}else {
				for(Cliente cliente : clientes) {
					System.out.println(cliente);
					System.out.println("");
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void listarVehiculos() {
		Consola.mostrarCabecera(Accion.LISTAR_VEHICULOS.toString());
		
		try {
			List<Vehiculo> vehiculos = controlador.getVehiculos();
			vehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
					.thenComparing(Vehiculo::getMatricula));
			
			if(vehiculos.isEmpty()) {
				System.out.println("ERROR: No hay ningún vehículo introducido.");
				System.out.println("");
			}else {
				for(Vehiculo vehiculo : vehiculos) {
					System.out.println(vehiculo);
					System.out.println("");
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void listarAlquileres() {
		Consola.mostrarCabecera(Accion.LISTAR_ALQUILERES.toString());
		
		try {
			List <Alquiler> alquileres = controlador.getAlquileres();
			Comparator<Cliente> compararCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente ,compararCliente));
			
			if(alquileres.isEmpty()) {
				System.out.println("ERROR: No hay ningún alquiler introducido.");
				System.out.println("");
			}else {
				for(Alquiler alquiler : alquileres) {
					System.out.println(alquiler);
					System.out.println("");
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void listarAlquileresCliente() {
		Consola.mostrarCabecera(Accion.LISTAR_ALQUILERES_CLIENTE.toString());
		
		try {
			List <Alquiler> alquileresCliente = controlador.getAlquileres(Consola.leerClienteDni());
			Comparator<Cliente> compararCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			alquileresCliente.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente ,compararCliente));
			
			if(alquileresCliente.isEmpty()) {
				System.out.println("ERROR: No hay ningún alquiler con el cliente introducido.");
				System.out.println("");
			}else {
				for(Alquiler alquilerClientes : alquileresCliente) {
					System.out.println(alquilerClientes);
					System.out.println("");
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}

	protected void listarAlquileresVehiculo() {
		Consola.mostrarCabecera(Accion.LISTAR_ALQUILERES_VEHICULO.toString());
		
		try {
			List<Alquiler> alquileresVehiculo = controlador.getAlquileres(Consola.leerVehiculoMatricula());
			Comparator<Cliente> compararCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
			alquileresVehiculo.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente ,compararCliente));
			
			if(alquileresVehiculo.isEmpty()) {
				System.out.println("ERROR: No hay ningún alquiler con el vehículo introducido.");
			}else {
				for(Alquiler alquilerTurismos : alquileresVehiculo) {
					System.out.println(alquilerTurismos);
					System.out.println("");
				}
			}
		} catch(NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
	
	private Map<TipoVehiculo, Integer> inicializarEstadisticas(){
		Map<TipoVehiculo, Integer> inicializacion = new EnumMap<>(TipoVehiculo.class);
		
		inicializacion.put(TipoVehiculo.TURISMO, 0);
		inicializacion.put(TipoVehiculo.AUTOBUS, 0);
		inicializacion.put(TipoVehiculo.FURGONETA, 0);
		
		return inicializacion;
	}
	
	public void mostrarEstadisticasMensualesTipoVehiculo() {
		Consola.mostrarCabecera(Accion.MOSTRAR_ESTADISTICAS_MENSUALES.toString());
		
		LocalDate mes = Consola.leerMes();
		List<Alquiler> alquileres = controlador.getAlquileres();
		
		Map<TipoVehiculo, Integer> estadisticas = inicializarEstadisticas();
		
		if(mes != null) {
			for(Alquiler alquiler : alquileres) {
				if(alquiler.getFechaAlquiler().getYear() == mes.getYear()
					&& alquiler.getFechaAlquiler().getMonth().equals(mes.getMonth())){
					TipoVehiculo tipoVehiculo = TipoVehiculo.get(alquiler.getVehiculo());
					estadisticas.put(tipoVehiculo, estadisticas.get(tipoVehiculo)+1);
				}
			}
			
			System.out.println(estadisticas);
		}else {
			System.out.println("ERROR: El mes introducido no es correcto.");
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
}
