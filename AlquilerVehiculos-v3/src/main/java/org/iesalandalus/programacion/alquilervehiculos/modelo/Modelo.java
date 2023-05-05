package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public abstract class Modelo {

	protected IClientes clientes;
	protected IVehiculos vehiculos;
	protected IAlquileres alquileres;
	protected IFuenteDatos fuenteDatos;

	protected Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
		setFuenteDatos(factoriaFuenteDatos.crear());
	}
	
	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
		/*if(fuenteDatos == null) {
			throw new NullPointerException("ERROR: La fuente de datos es nula.");
		}*/
		
		this.fuenteDatos = fuenteDatos;
	}
	
	public void comenzar() throws OperationNotSupportedException {
		clientes = fuenteDatos.crearClientes();
		clientes.comenzar();
		
		vehiculos = fuenteDatos.crearVehiculos();
		vehiculos.comenzar();
		
		alquileres = fuenteDatos.crearAlquileres();
		alquileres.comenzar();
	}
	
	public void terminar() {
		clientes.terminar();
		vehiculos.terminar();
		alquileres.terminar();
		System.out.println("El modelo ha finalizado.");
	}
	
	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;
	
	public abstract Cliente buscar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract Vehiculo buscar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract Alquiler buscar(Alquiler alquiler) throws OperationNotSupportedException;
	
	public abstract void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;
	
	public abstract void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;
	
	public abstract void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;
	
	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;
	
	public abstract List<Cliente> getClientes();
	
	public abstract List<Vehiculo> getVehiculos();
	
	public abstract List<Alquiler> getAlquileres();
	
	public abstract List<Alquiler> getAlquileres(Cliente cliente);
	
	public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);
}
