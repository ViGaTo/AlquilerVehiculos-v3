package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {

	private List<Vehiculo> coleccionVehiculos;

	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}

	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> copiaVehiculo = new ArrayList<>(coleccionVehiculos);
		
		return copiaVehiculo;
	}

	public int getCantidad() {
		return coleccionVehiculos.size();
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		
		if (vehiculo instanceof Turismo) {
			coleccionVehiculos.add(new Turismo((Turismo) vehiculo));
		}

		if (vehiculo instanceof Autobus) {
			coleccionVehiculos.add(new Autobus((Autobus) vehiculo));
		}
		
		if (vehiculo instanceof Furgoneta) {
			coleccionVehiculos.add(new Furgoneta((Furgoneta) vehiculo));
		}
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoEncontrado;
		
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		
		int indice = coleccionVehiculos.indexOf(vehiculo);
		if(coleccionVehiculos.contains(vehiculo.copiar(vehiculo))) {
			vehiculoEncontrado = coleccionVehiculos.get(indice);
		}else {
			vehiculoEncontrado = null;
		}
		
		return vehiculoEncontrado;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}

		if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		
		coleccionVehiculos.remove(vehiculo);
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