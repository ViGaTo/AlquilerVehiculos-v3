package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Cliente cliente;
	private Vehiculo vehiculo;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) {
		setCliente(cliente);
		setVehiculo(vehiculo);
		setFechaAlquiler(fechaAlquiler);
	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}

		setCliente(new Cliente(alquiler.getCliente()));
		
		if(alquiler.getVehiculo() instanceof Turismo) {
			setVehiculo((Vehiculo) new Turismo((Turismo) alquiler.getVehiculo()));
		}else if(alquiler.getVehiculo() instanceof Autobus) {
			setVehiculo((Vehiculo) new Autobus((Autobus) alquiler.getVehiculo()));
		}else if(alquiler.getVehiculo() instanceof Furgoneta) {
			setVehiculo((Vehiculo) new Furgoneta((Furgoneta) alquiler.getVehiculo()));
		}
		setFechaAlquiler(alquiler.getFechaAlquiler());
		
		if(alquiler.getFechaDevolucion() == null) {
			this.fechaDevolucion = null;
		}else {
			setFechaDevolucion(alquiler.getFechaDevolucion());
		}
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if(fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha devolución introducida no puede ser nula.");
		}
		
		if (getFechaDevolucion() != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}

		setFechaDevolucion(fechaDevolucion);
	}

	public int getPrecio() {
		int precioTotal = 0;

		if (getFechaDevolucion() == null) {
			precioTotal = 0;
		} else {
			int numDias = (int) ChronoUnit.DAYS.between(getFechaAlquiler(), getFechaDevolucion());
			precioTotal = (PRECIO_DIA + vehiculo.getFactorPrecio()) * numDias;
		}

		return precioTotal;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}

		if (fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}

		this.fechaAlquiler = fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}

		if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}

		if (fechaDevolucion.isBefore(getFechaAlquiler()) || fechaDevolucion.isEqual(getFechaAlquiler())) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}

		this.fechaDevolucion = fechaDevolucion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}

		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	private void setVehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El vehiculo no puede ser nulo.");
		}
		
		this.vehiculo = vehiculo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public String toString() {
		String cadenaString = "";
		if (fechaDevolucion == null) {
			cadenaString = String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					fechaAlquiler.format(FORMATO_FECHA), "Aún no devuelto", getPrecio());
		} else {
			cadenaString = String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo,
					fechaAlquiler.format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA), getPrecio());
		}

		return cadenaString;
	}
}
