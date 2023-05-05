package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO("Turismo"), AUTOBUS("Autobús"), FURGONETA("Furgoneta");

private String nombre;
	
	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}
	
	public static TipoVehiculo get(int ordinal) {
		if(esOrdinalValido(ordinal)) {
			return values()[ordinal];
		}
		
		throw new IllegalArgumentException("ERROR: Valor del tipo de vehículo no válido.");
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >=0 && ordinal <= values().length - 1;
	}
	
	public static TipoVehiculo get(Vehiculo vehiculo) {
		if(vehiculo == null) {
			throw new NullPointerException("ERROR: El vehículo no puede ser nulo.");
		}
		
		TipoVehiculo tipoVehiculoElegido = null;
		
		if(vehiculo instanceof Turismo) {
			tipoVehiculoElegido = TURISMO;
		}else if(vehiculo instanceof Autobus) {
			tipoVehiculoElegido = AUTOBUS;
		}else if(vehiculo instanceof Furgoneta) {
			tipoVehiculoElegido = FURGONETA;
		}
		
		return tipoVehiculoElegido;
	}
	
	@Override
	public String toString() {
		return ordinal() + ". " + nombre;
	}
}
