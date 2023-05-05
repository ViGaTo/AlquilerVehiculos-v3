package org.iesalandalus.programacion.alquilervehiculos;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVista;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	public static void main(String[] args) {
		// Ánimo!!!!
		
		Modelo modelo = elegirModelo();
		Vista vista = elegirVista();
		Controlador controlador = new Controlador(modelo, vista);
		
		try {
			controlador.comenzar();
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR: Se ha producido un error a la hora de leer los ficheros, revisar que todo este en su sitio.");
		}
	}
	
	private static Modelo elegirModelo() {
		
		int modeloElegido=0;
		Modelo modelo = null;
		
		 System.out.println("MENÚ DE SELECCIÓN DE MODELO A USAR");
		 System.out.println("----------------------------------");
		 
		System.out.println("0. Memoria");
		System.out.println("1. Ficheros");
		System.out.println("");
		
		do{
			System.out.print("Introduzca el modelo a usar (0-1): ");
			modeloElegido = Entrada.entero();
		}while (modeloElegido<0 || modeloElegido>1);
		
		if(modeloElegido == 0) {
			modelo = new ModeloCascada(FactoriaFuenteDatos.MEMORIA);
		}else {
			modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		}
		
		return modelo;
	}
	
private static Vista elegirVista() {
		
		int vistaElegida=0;
		Vista vista = null;
		
		 System.out.println("MENÚ DE SELECCIÓN DE VISTA A USAR");
		 System.out.println("----------------------------------");
		 
		System.out.println("0. Textual");
		System.out.println("1. Gráfica");
		System.out.println("");
		
		do{
			System.out.print("Introduzca la vista a usar (0-1): ");
			vistaElegida = Entrada.entero();
		}while (vistaElegida<0 || vistaElegida>1);
		
		if(vistaElegida == 0) {
			vista = FactoriaVista.TEXTO.crear();
		}else {
			vista = FactoriaVista.GRAFICA.crear();
		}
		
		return vista;
	}
}
