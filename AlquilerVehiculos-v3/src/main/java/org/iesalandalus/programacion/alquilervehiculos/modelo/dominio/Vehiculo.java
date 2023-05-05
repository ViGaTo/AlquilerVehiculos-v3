package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {

	private static final String ER_MARCA = "([A-Z]+[a-z]*([ -]?[A-Z][a-z]+)*)";
	private static final String ER_MATRICULA = "([1-9]{4}[B-Z^ÑQ]{3})";
	
	private String marca;
	private String modelo;
	/*private int cilindrada;*/
	private String matricula;
	
	protected Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		/*setCilindrada(cilindrada);*/
		setMatricula(matricula);
	}
	
	protected Vehiculo(Vehiculo vehiculo) {
		if(vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
		}
		
		setMarca(vehiculo.getMarca());
		setModelo(vehiculo.getModelo());
		/*setCilindrada(turismo.getCilindrada());*/
		setMatricula(vehiculo.getMatricula());
	}

	public Vehiculo copiar(Vehiculo vehiculo) {
		Vehiculo copiaVehiculo = null;
		
		if(vehiculo instanceof Turismo) {
			copiaVehiculo = new Turismo((Turismo) vehiculo);
		}else if(vehiculo instanceof Autobus) {
			copiaVehiculo = new Autobus((Autobus) vehiculo);
		}else if(vehiculo instanceof Furgoneta) {
			copiaVehiculo = new Furgoneta((Furgoneta) vehiculo);
		}
		
		return copiaVehiculo;
	}
	
	protected abstract int getFactorPrecio();
	
	public static Vehiculo getVehiculoConMatricula(String matricula) {
		return new Turismo("Seat", "Panda", 4500, matricula);
	}
	
	public String getMarca() {
		return marca;
	}

	protected void setMarca(String marca) {
		if(marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}
		
		if(!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
		
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	protected void setModelo(String modelo) {
		if(modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		
		if(modelo.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		
		this.modelo = modelo;
	}

	/*public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if(cilindrada<=0 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		
		this.cilindrada = cilindrada;
	}*/

	public String getMatricula() {
		return matricula;
	}

	protected void setMatricula(String matricula) {
		if(matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		
		if(!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

	/*@Override
	public String toString() {
		return String.format("%s %s (%sCV) - %s",marca,modelo,cilindrada,matricula);
	}*/
}
