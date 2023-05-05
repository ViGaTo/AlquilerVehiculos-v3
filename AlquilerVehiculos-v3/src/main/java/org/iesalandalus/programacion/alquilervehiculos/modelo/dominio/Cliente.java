package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

	private static final String ER_NOMBRE = "([A-Z]{1}[a-z]+([ ][A-Z]{1}[a-z]+)*)";
	private static final String ER_DNI = "([0-9]{8})([A-Z]{1})";
	private static final String ER_TELEFONO = "([0-9]{9})";
	
	private String nombre;
	private String dni;
	private String telefono;
	
	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setTelefono(cliente.getTelefono());
	}
	
	public static Cliente getClienteConDni(String dni) {
		return new Cliente("Manuel", dni, "650111111");
	}

	private boolean comprobarLetraDni(String dni) {
		Boolean dniValido = false;
		
		Pattern p;
		Matcher m;
		
		p = Pattern.compile(ER_DNI);
		m = p.matcher(dni);
		
		if(m.matches()) {
			int dniNumero = Integer.parseInt(m.group(1));
			String letra = m.group(2);
			String[] letras = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		if (letra.equals(letras[dniNumero % 23])) {
				dniValido = true;
			}
		}
		
		return dniValido;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		
		if(!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if(dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		
		if(!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		
		if(!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if(telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		
		if(!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)",nombre,dni,telefono);
	}
}
