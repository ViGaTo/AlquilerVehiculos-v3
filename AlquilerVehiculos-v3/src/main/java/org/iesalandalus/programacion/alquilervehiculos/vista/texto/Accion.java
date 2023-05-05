package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion {
	SALIR("Salir"), INSERTAR_CLIENTE("Insertar cliente"), INSERTAR_VEHICULO("Insertar vehículo"),
	INSERTAR_ALQUILER("Insertar alquiler"), BUSCAR_CLIENTE("Buscar cliente"), BUSCAR_VEHICULO("Buscar vehículo"),
	BUSCAR_ALQUILER("Buscar alquiler"), MODIFICAR_CLIENTE("Modificar cliente"),
	DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler del cliente introducido"),
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler del vehículo introducido"),
	BORRAR_CLIENTE("Borrar cliente"), BORRAR_VEHICULO("Borrar vehiculo"), BORRAR_ALQUILER("Borrar alquiler"),
	LISTAR_CLIENTES("Listar clientes"), LISTAR_VEHICULOS("Listar vehículos"), LISTAR_ALQUILERES("Listar alquileres"),
	LISTAR_ALQUILERES_CLIENTE("Listar los alquileres de un cliente"),
	LISTAR_ALQUILERES_VEHICULO("Listar los alquileres de un turismo"),
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadísticas mensuales");
	
	private String texto;
	
	private Accion(String texto) {
		this.texto = texto;
	}
	
	public static Accion get(int ordinal) {
		if(esOrdinalValido(ordinal))
			return values()[ordinal];
		
		throw new IllegalArgumentException("ERROR: Valor de la opción no válido.");
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >=0 && ordinal <= values().length - 1;
	}
	
	@Override
	public String toString() {
		return ordinal() + ". " + texto;
	}
}
