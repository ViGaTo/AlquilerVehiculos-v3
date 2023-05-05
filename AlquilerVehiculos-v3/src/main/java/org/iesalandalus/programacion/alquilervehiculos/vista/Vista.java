package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;

import javafx.application.Application;

public abstract class Vista extends Application {

	protected Controlador controlador;
	
	public void setControlador(Controlador controlador){
		if(controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		
		this.controlador = controlador;
	}
	
	public abstract void comenzar();
	
	public abstract void terminar();
}
