package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Vehiculos implements IVehiculos {

	private static final String RUTA_FICHERO = "datos/vehiculos.xml";
	private static final String RAIZ = "Vehiculos";
	private static final String VEHICULO = "Vehiculo";
	private static final String MARCA = "Marca";
	private static final String MODELO = "Modelo";
	private static final String MATRICULA = "Matricula";
	private static final String CILINDRADA = "Cilindrada";
	private static final String PLAZAS = "Plazas";
	private static final String PMA = "Pma";
	private static final String TIPO = "Tipo";
	private static final String TURISMO = "Turismo";
	private static final String AUTOBUS = "Autobus";
	private static final String FURGONETA = "Furgoneta";
	
	private List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia;

	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
	}

	static Vehiculos getInstancia() {
		if(instancia == null) {
			instancia = new Vehiculos();
		}
		
		return instancia;
	}
	
	@Override
	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}
	
	private void leerXml() throws OperationNotSupportedException {
		Document DOMVehiculos = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raizDOM = DOMVehiculos.getDocumentElement();
		
		NodeList listaDOM = raizDOM.getElementsByTagName(VEHICULO);
		
		for(int i = 0; i < listaDOM.getLength(); i++) {
			{
				Node nodo = listaDOM.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element vehiculoDOM = (Element) nodo;
					Vehiculo vehiculo = elementToVehiculo(vehiculoDOM);
					
					insertar(vehiculo);
				}
			}
		}
	}
	
	private Vehiculo elementToVehiculo(Element elemento) {
		Vehiculo vehiculo = null;
		
		String aMatricula = elemento.getAttribute(MATRICULA);
		
		String aTipo = elemento.getAttribute(TIPO);
		Element eMarca = (Element) elemento.getElementsByTagName(MARCA).item(0);
		Element eModelo = (Element) elemento.getElementsByTagName(MODELO).item(0);
		String aMarca = eMarca.getTextContent();
		String aModelo = eModelo.getTextContent();
		
		switch(aTipo) {
			case TURISMO:
				Element eTurismo = (Element) elemento.getElementsByTagName(aTipo).item(0);
				Element eCilindrada = (Element) eTurismo.getElementsByTagName(CILINDRADA).item(0);
				int aCilindrada = Integer.parseInt(eCilindrada.getTextContent());
				
				vehiculo = new Turismo(aMarca, aModelo, aCilindrada, aMatricula);
				break;
			
			case AUTOBUS:
				Element eAutobus = (Element) elemento.getElementsByTagName(aTipo).item(0);
				Element ePlazasBus = (Element) eAutobus.getElementsByTagName(PLAZAS).item(0);
				int aPlazasBus = Integer.parseInt(ePlazasBus.getTextContent());
				
				vehiculo = new Autobus(aMarca, aModelo, aPlazasBus, aMatricula);
				break;
				
			case FURGONETA:
				Element eFurgoneta = (Element) elemento.getElementsByTagName(aTipo).item(0);
				Element ePlazasFurgo = (Element) eFurgoneta.getElementsByTagName(PLAZAS).item(0);
				int aPlazasFurgo = Integer.parseInt(ePlazasFurgo.getTextContent());
				Element ePMA = (Element) eFurgoneta.getElementsByTagName(PMA).item(0);
				int aPMA = Integer.parseInt(ePMA.getTextContent());
				
				vehiculo = new Furgoneta(aMarca, aModelo, aPMA, aPlazasFurgo, aMatricula);
		}
		
		return vehiculo;
	}
	
	@Override
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document DOMVehiculos = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizDOM = DOMVehiculos.getDocumentElement();
		
		if(!coleccionVehiculos.isEmpty()) {
			for(Vehiculo vehiculo : coleccionVehiculos) {
				Element vehiculoDOM = vehiculoToElement(DOMVehiculos, vehiculo);
				raizDOM.appendChild(vehiculoDOM);
			}
		}
		
		UtilidadesXml.domToXml(DOMVehiculos, RUTA_FICHERO);
	}
	
	private Element vehiculoToElement(Document dom, Vehiculo vehiculo) {
		String tipoVehiculo = null;
		if(vehiculo instanceof Turismo) {
			tipoVehiculo = TURISMO;
		}else if(vehiculo instanceof Autobus) {
			tipoVehiculo = AUTOBUS;
		}else if(vehiculo instanceof Furgoneta) {
			tipoVehiculo = FURGONETA;
		}
		
		Element vehiculoDOM = dom.createElement(VEHICULO);
		vehiculoDOM.setAttribute(MATRICULA, vehiculo.getMatricula());
		vehiculoDOM.setAttribute(TIPO, tipoVehiculo);
		
		Element eMarca = dom.createElement(MARCA);
		eMarca.setTextContent(vehiculo.getMarca());
		vehiculoDOM.appendChild(eMarca);
		
		Element eModelo = dom.createElement(MODELO);
		eModelo.setTextContent(vehiculo.getModelo());
		vehiculoDOM.appendChild(eModelo);
		
		if(vehiculo instanceof Turismo turismo) {
			Element eTurismo = dom.createElement(TURISMO);
			
			Element eCilindrada = dom.createElement(CILINDRADA);
			eCilindrada.setTextContent(Integer.toString(turismo.getCilindrada()));
			eTurismo.appendChild(eCilindrada);
			
			vehiculoDOM.appendChild(eTurismo);
			
		}else if(vehiculo instanceof Autobus autobus) {
			Element eAutobus = dom.createElement(AUTOBUS);
			
			Element ePlazasBus = dom.createElement(PLAZAS);
			ePlazasBus.setTextContent(Integer.toString(autobus.getPlazas()));
			eAutobus.appendChild(ePlazasBus);
			
			vehiculoDOM.appendChild(eAutobus);
			
		}else if(vehiculo instanceof Furgoneta furgoneta) {
			Element eFurgoneta = dom.createElement(FURGONETA);
			
			Element ePlazasFurgo = dom.createElement(PLAZAS);
			ePlazasFurgo.setTextContent(Integer.toString(furgoneta.getPlazas()));
			eFurgoneta.appendChild(ePlazasFurgo);
			
			Element ePMA = dom.createElement(PMA);
			ePMA.setTextContent(Integer.toString(furgoneta.getPma()));
			eFurgoneta.appendChild(ePMA);
			
			vehiculoDOM.appendChild(eFurgoneta);
		}
		
		return vehiculoDOM;
	}
	
	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> copiaVehiculo = new ArrayList<>(coleccionVehiculos);
		
		return copiaVehiculo;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		
		if (vehiculo instanceof Turismo) {
			coleccionVehiculos.add(new Turismo((Turismo) vehiculo));
		}

		if (vehiculo instanceof Autobus) {
			coleccionVehiculos.add(new Autobus((Autobus) vehiculo));
		}
		
		if (vehiculo instanceof Furgoneta) {
			coleccionVehiculos.add(new Furgoneta((Furgoneta) vehiculo));
		}
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo vehiculoEncontrado;
		
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		
		int indice = coleccionVehiculos.indexOf(vehiculo);
		if(coleccionVehiculos.contains(vehiculo.copiar(vehiculo))) {
			vehiculoEncontrado = coleccionVehiculos.get(indice);
		}else {
			vehiculoEncontrado = null;
		}
		
		return vehiculoEncontrado;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}

		if (!coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		
		coleccionVehiculos.remove(vehiculo);
	}
}