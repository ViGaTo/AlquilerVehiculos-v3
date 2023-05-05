package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Furgoneta extends Vehiculo{

	private static final int FACTOR_PMA = 100;
	private static final int FACTOR_PLAZAS = 1;
	
	private int pma;
	private int plazas;
	
	public int getPma() {
		return pma;
	}
	
	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPma(pma);
		setPlazas(plazas);
	}
	
	public Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		setPma(furgoneta.getPma());
		setPlazas(furgoneta.getPlazas());
	}
	
	@Override
	protected int getFactorPrecio() {
		return (pma / FACTOR_PMA + plazas * FACTOR_PLAZAS);
	}
	
	private void setPma(int pma) {
		if(pma <= 0) {
			throw new IllegalArgumentException("ERROR: El peso no es válido.");
		}
		
		this.pma = pma;
	}
	
	public int getPlazas() {
		return plazas;
	}
	
	private void setPlazas(int plazas) {
		if(plazas <= 0) {
			throw new IllegalArgumentException("ERROR: Las plazas no son válidas.");
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
		return String.format("%s %s (%d kg, %d plazas) - %s",super.getMarca(),super.getModelo(),pma,plazas,super.getMatricula());
	}
}
