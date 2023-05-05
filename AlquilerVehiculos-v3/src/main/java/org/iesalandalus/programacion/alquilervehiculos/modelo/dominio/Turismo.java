package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo{

	private static final int FACTOR_CILINDRADA = 10;
	
	private int cilindrada;
	
	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
	}
	
	public Turismo(Turismo turismo) {
		super(turismo);
		setCilindrada(turismo.getCilindrada());
	}
	
	@Override
	protected int getFactorPrecio() {
		return (cilindrada / FACTOR_CILINDRADA);
	}
	
	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if(cilindrada<=0 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		
		this.cilindrada = cilindrada;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return String.format("%s %s (%d cc) - %s",super.getMarca(),super.getModelo(),cilindrada,super.getMatricula());
	}
}