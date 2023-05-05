package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo{

	private static final int FACTOR_PLAZAS = 2;
	
	private int plazas;

	public Autobus(String marca, String modelo, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPlazas(plazas);
	}
	
	public Autobus(Autobus autobus) {
		super(autobus);
		setPlazas(autobus.getPlazas());
	}
	
	@Override
	protected int getFactorPrecio() {
		return (plazas * FACTOR_PLAZAS);
	}
	
	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if(plazas <= 0) {
			throw new IllegalArgumentException("ERROR: Las plazas no son válidas");
		}
		
		this.plazas = plazas;
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
		return String.format("%s %s (%d plazas) - %s",super.getMarca(),super.getModelo(),plazas,super.getMatricula());
	}
}
