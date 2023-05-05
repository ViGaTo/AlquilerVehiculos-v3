package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO{
		public Vista crear() {
			return new VistaTexto();
		}
	},
	
	GRAFICA{
		public Vista crear() {
			return new VistaGrafica();
		}
	};
	
	public abstract Vista crear();
}
