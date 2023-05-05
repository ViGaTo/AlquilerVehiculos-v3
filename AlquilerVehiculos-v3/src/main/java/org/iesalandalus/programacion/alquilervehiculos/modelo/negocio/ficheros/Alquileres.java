package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres{

	private static final String RUTA_FICHERO = "datos/alquileres.xml";
	private static final String FORMATO = "Formato";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "Alquileres";
	private static final String ALQUILER = "Alquiler";
	private static final String DNI_CLIENTE = "Dni";
	private static final String MATRICULA_VEHICULO = "Matricula";
	private static final String FECHA_ALQUILER = "FechaAlquiler";
	private static final String FECHA_DEVOLUCION = "FechaDevolucion";
	
	List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}
	
	static Alquileres getInstancia() {
		if(instancia == null) {
			instancia = new Alquileres();
		}
		
		return instancia;
	}

	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}
	
	private void leerXml() throws OperationNotSupportedException {
		Document DOMAlquileres = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raizDOM = DOMAlquileres.getDocumentElement();
		
		NodeList listaDOM = raizDOM.getElementsByTagName(ALQUILER);
		
		for(int i = 0; i < listaDOM.getLength(); i++) {
			{
				Node nodo = listaDOM.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element alquilerDOM = (Element) nodo;
					Alquiler alquiler = elementToAlquiler(alquilerDOM);
					
					insertar(alquiler);
				}
			}
		}
	}
	
	private Alquiler elementToAlquiler(Element elemento) {
		Alquiler alquiler = null;
		
		String aDni = elemento.getAttribute(DNI_CLIENTE);
		Cliente cliente = Cliente.getClienteConDni(aDni);
		Cliente clienteBuscado = Clientes.getInstancia().buscar(cliente);
		
		String aMatricula = elemento.getAttribute(MATRICULA_VEHICULO);
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(aMatricula);
		Vehiculo vehiculoBuscado = Vehiculos.getInstancia().buscar(vehiculo);
		
		Element eFechaAlquiler = (Element) elemento.getElementsByTagName(FECHA_ALQUILER).item(0);
		String eFechaAlquilerCadena = eFechaAlquiler.getTextContent();
		LocalDate aFechaAlquiler;
		try {
			aFechaAlquiler = LocalDate.parse(eFechaAlquilerCadena, FORMATO_FECHA);
		}catch(DateTimeParseException e) {
			aFechaAlquiler = null;
		}
		
		alquiler = new Alquiler(clienteBuscado, vehiculoBuscado, aFechaAlquiler);
		
		Element eFechaDevolucion = (Element) elemento.getElementsByTagName(FECHA_DEVOLUCION).item(0);
		String eFechaDevolucionCadena = eFechaDevolucion.getTextContent();
		LocalDate aFechaDevolucion;
		try {
			aFechaDevolucion = LocalDate.parse(eFechaDevolucionCadena, FORMATO_FECHA);
		}catch(DateTimeParseException e) {
			aFechaDevolucion = null;
		}
		
		if(aFechaDevolucion != null) {
			try {
				alquiler.devolver(aFechaDevolucion);
			}catch(Exception e) {
				
			}
		}
		
		return alquiler;
	}
	
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document DOMAlquileres = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizDOM = DOMAlquileres.getDocumentElement();
		
		if(!coleccionAlquileres.isEmpty()) {
			for(Alquiler alquiler : coleccionAlquileres) {
				Element alquilerDOM = alquilerToElement(DOMAlquileres, alquiler);
				raizDOM.appendChild(alquilerDOM);
			}
		}
		
		UtilidadesXml.domToXml(DOMAlquileres, RUTA_FICHERO);
	}
	
	private Element alquilerToElement(Document dom, Alquiler alquiler) {
		Element alquilerDOM = dom.createElement(ALQUILER);
		alquilerDOM.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
		alquilerDOM.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());
		
		Element eFechaAlquiler = dom.createElement(FECHA_ALQUILER);
		eFechaAlquiler.setTextContent(alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		eFechaAlquiler.setAttribute(FORMATO, "dd/MM/yyyy");
		alquilerDOM.appendChild(eFechaAlquiler);
		
		Element eFechaDevolucion = dom.createElement(FECHA_DEVOLUCION);
		if(alquiler.getFechaDevolucion() != null) {
			eFechaDevolucion.setTextContent(alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}else {
			eFechaDevolucion.setTextContent("");
		}
		
		eFechaDevolucion.setAttribute(FORMATO, "dd/MM/yyyy");
		alquilerDOM.appendChild(eFechaDevolucion);
		
		return alquilerDOM;
	}
	
	@Override
	public List<Alquiler> get() {
		List <Alquiler> copiaAlquileres = new ArrayList<>(coleccionAlquileres);
		
		return copiaAlquileres;
	}
	
	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}
				
				if (alquiler.getVehiculo().equals(vehiculo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
				
			} else {
				if ((alquiler.getCliente().equals(cliente)) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) ||
						alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
				
				if ((alquiler.getVehiculo().equals(vehiculo)) && (alquiler.getFechaDevolucion().isAfter(fechaAlquiler) ||
						alquiler.getFechaDevolucion().isEqual(fechaAlquiler))) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
				}
			}
		}
	}
	
	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> it = coleccionAlquileres.iterator();
		while(alquilerEncontrado == null && it.hasNext()) {
			Alquiler alquiler = it.next();
			
			if(alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				alquilerEncontrado = alquiler;
			}
		}
		
		return alquilerEncontrado;
	}
	
	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(cliente);
		
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No se ha encontrado ningún alquiler abierto para el cliente introducido.");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Alquiler alquilerEncontrado = null;
		Iterator<Alquiler> it = coleccionAlquileres.iterator();
		while(alquilerEncontrado == null && it.hasNext()) {
			Alquiler alquiler = it.next();
			
			if(alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				alquilerEncontrado = alquiler;
			}
		}
		
		return alquilerEncontrado;
	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(vehiculo);
		
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No se ha encontrado ningún alquiler abierto para el vehículo introducido.");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	
	@Override
	public List<Alquiler> get(Cliente cliente) {
		ArrayList<Alquiler> alquilerCliente = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				alquilerCliente.add(alquiler);
			}
		}
		
		return alquilerCliente;
	}
	
	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {
		ArrayList<Alquiler> alquilerVehiculo = new ArrayList<>();

		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquilerVehiculo.add(alquiler);
			}
		}
		
		return alquilerVehiculo;
	}
	
	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		
		coleccionAlquileres.add(alquiler);
	}
	
	@Override
	public Alquiler buscar(Alquiler alquiler) {
		Alquiler alquilerEncontrado;
		
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		
		int indice = coleccionAlquileres.indexOf(alquiler);
		if(coleccionAlquileres.contains(alquiler)) {
			alquilerEncontrado = coleccionAlquileres.get(indice);
		}else {
			alquilerEncontrado = null;
		}
		
		return alquilerEncontrado;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		
		coleccionAlquileres.remove(alquiler);
	}
}
