package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

public class Alquileres implements IAlquileres{

	List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	@Override
	public List<Alquiler> get() {
		List <Alquiler> copiaAlquileres = new ArrayList<>(coleccionAlquileres);
		
		return copiaAlquileres;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	
	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				
				if (alquiler.getVehiculo().equals(vehiculo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
				
			} else {
				if ((alquiler.getCliente().equals(cliente)) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) ||
						alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				
				if ((alquiler.getVehiculo().equals(vehiculo)) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) ||
						alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
				}
			}
		}
	}
	
	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> it = coleccionAlquileres.iterator();
		while(alquilerEncontrado == null && it.hasNext()) {
			Alquiler alquiler = it.next();
			
			if(alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerEncontrado = alquiler;
			}
		}
		
		return alquilerEncontrado;
	}
	
	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(cliente);
		
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No se ha encontrado ningún alquiler abierto para el cliente introducido.");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> it = coleccionAlquileres.iterator();
		while(alquilerEncontrado == null && it.hasNext()) {
			Alquiler alquiler = it.next();
			
			if(alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				alquilerEncontrado = alquiler;
			}
		}
		
		return alquilerEncontrado;
	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(vehiculo);
		
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No se ha encontrado ningún alquiler abierto para el vehículo introducido.");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	
	@Override
	public List<Alquiler> get(Cliente cliente) {
		ArrayList<Alquiler> alquilerCliente = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquilerCliente.add(alquiler);
			}
		}
		
		return alquilerCliente;
	}
	
	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {
		ArrayList<Alquiler> alquilerVehiculo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquilerVehiculo.add(alquiler);
			}
		}
		
		return alquilerVehiculo;
	}
	
	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		
		coleccionAlquileres.add(alquiler);
	}
	
	@Override
	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerEncontrado;
		
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		
		int indice = coleccionAlquileres.indexOf(alquiler);
		if(coleccionAlquileres.contains(alquiler)) {
			alquilerEncontrado = coleccionAlquileres.get(indice);
		}else {
			alquilerEncontrado = null;
		}
		
		return alquilerEncontrado;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		
		coleccionAlquileres.remove(alquiler);
	}

	@Override
	public void comenzar() throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}
