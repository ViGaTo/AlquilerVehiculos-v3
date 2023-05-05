package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {

	private static final String RUTA_FICHERO = "datos/clientes.xml";
	private static final String RAIZ = "Clientes";
	private static final String CLIENTE = "Cliente";
	private static final String NOMBRE = "Nombre";
	private static final String DNI = "Dni";
	private static final String TELEFONO = "Telefono";
	
	private List<Cliente> coleccionClientes;
	private static Clientes instancia;

	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}
	
	static Clientes getInstancia() {
		if(instancia == null) {
			instancia = new Clientes();
		}
		
		return instancia;
	}
	
	@Override
	public void comenzar() throws OperationNotSupportedException {
		leerXml();
	}
	
	private void leerXml() throws OperationNotSupportedException {
		Document DOMClientes = UtilidadesXml.xmlToDom(RUTA_FICHERO);
		Element raizDOM = DOMClientes.getDocumentElement();
		
		NodeList listaDOM = raizDOM.getElementsByTagName(CLIENTE);
		
		for(int i = 0; i < listaDOM.getLength(); i++) {
			{
				Node nodo = listaDOM.item(i);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element clienteDOM = (Element) nodo;
					Cliente cliente = elementToCliente(clienteDOM);
					
					insertar(cliente);
				}
			}
		}
	}
	
	private Cliente elementToCliente(Element elemento) {
		String aDni = elemento.getAttribute(DNI);
		
		Element eNombre = (Element) elemento.getElementsByTagName(NOMBRE).item(0);
		Element eTelefono = (Element) elemento.getElementsByTagName(TELEFONO).item(0);
		String aNombre = eNombre.getTextContent();
		String aTelefono = eTelefono.getTextContent();
		
		return new Cliente(aNombre, aDni, aTelefono);
	}
	
	@Override
	public void terminar() {
		escribirXml();
	}
	
	private void escribirXml() {
		Document DOMClientes = UtilidadesXml.crearDomVacio(RAIZ);
		Element raizDOM = DOMClientes.getDocumentElement();
		
		if(!coleccionClientes.isEmpty()) {
			for(Cliente cliente : coleccionClientes) {
				Element clienteDOM = clienteToElement(DOMClientes, cliente);
				raizDOM.appendChild(clienteDOM);
			}
		}
		
		UtilidadesXml.domToXml(DOMClientes, RUTA_FICHERO);
	}
	
	private Element clienteToElement(Document dom, Cliente cliente) {
		Element clienteDOM = dom.createElement(CLIENTE);
		clienteDOM.setAttribute(DNI, cliente.getDni());
		
		Element eNombre = dom.createElement(NOMBRE);
		eNombre.setTextContent(cliente.getNombre());
		clienteDOM.appendChild(eNombre);
		
		Element eTelefono = dom.createElement(TELEFONO);
		eTelefono.setTextContent(cliente.getTelefono());
		clienteDOM.appendChild(eTelefono);
		
		return clienteDOM;
	}

	@Override
	public List<Cliente> get() {
		List <Cliente> copiaCliente = new ArrayList<>(coleccionClientes);
		
		return copiaCliente;
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}

		coleccionClientes.add(cliente);
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente clienteEncontrado;
		
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

		int indice = coleccionClientes.indexOf(cliente);
		if(coleccionClientes.contains(cliente)) {
			clienteEncontrado = coleccionClientes.get(indice);
		}else {
			clienteEncontrado = null;
		}
		
		return clienteEncontrado;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		coleccionClientes.remove(cliente);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		if ((nombre != null) && (!nombre.trim().isEmpty())) {
			buscar(cliente).setNombre(nombre);
		}

		if ((telefono != null) && (!telefono.trim().isEmpty())) {
			buscar(cliente).setTelefono(telefono);
		}
	}
}