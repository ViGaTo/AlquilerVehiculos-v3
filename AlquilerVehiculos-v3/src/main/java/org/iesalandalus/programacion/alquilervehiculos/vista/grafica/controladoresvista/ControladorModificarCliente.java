package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorModificarCliente {

	private Controlador controladorMVC;
	private ObservableList<Cliente> obsClientes;
	private ObservableList<Alquiler> obsAlquileres;
	
    @FXML
    private Button buttonCancelarModificarCliente;

    @FXML
    private Button buttonModificarCliente;

    @FXML
    private Label labelModificarDni;

    @FXML
    private Label labelModificarNombre;

    @FXML
    private Label labelModificarTelefono;

    @FXML
    private TextField tfModificarDni;

    @FXML
    private TextField tfModificarNombre;

    @FXML
    private TextField tfModificarTelefono;

    @FXML
    public void initialize() {
    	
    }
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void setClientes(ObservableList<Cliente> clientes, ObservableList<Alquiler> alquileres) {
    	this.obsClientes = clientes;
    	this.obsAlquileres = alquileres;
    }
    
    public void inicializaCampos() {
    	tfModificarNombre.setDisable(false);
    	tfModificarDni.setDisable(true);
    	tfModificarTelefono.setDisable(false);
    }
    
    public void cargarCliente(Cliente cliente) {
    	tfModificarNombre.setText(cliente.getNombre());
    	tfModificarDni.setText(cliente.getDni());
    	tfModificarTelefono.setText(cliente.getTelefono());
    }
    
    @FXML
    private void modificarCliente(ActionEvent event) {
    	
    	Cliente clienteOriginal = Cliente.getClienteConDni(tfModificarDni.getText());
    	
    	try {
    		Cliente clienteModificado = new Cliente(tfModificarNombre.getText(), tfModificarDni.getText(), tfModificarTelefono.getText());
    		
    		
    		controladorMVC.modificar(clienteOriginal, tfModificarNombre.getText(), tfModificarTelefono.getText());
    		obsClientes.remove(clienteOriginal);
    		obsClientes.add(clienteModificado);
    		obsClientes.setAll(controladorMVC.getClientes());
    		obsAlquileres.setAll(controladorMVC.getAlquileres());
    		
    		Dialogos.mostrarDialogoInformacion("EXITO AL MODIFICAR CLIENTE", "El cliente se ha modificado con éxito.");
    		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	escenario.close();
    		
    	}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
    		Dialogos.mostrarDialogoError("ERROR AL MODIFICAR CLIENTE", e.getMessage());
    	}
    }
    
    @FXML
    private void salirModificarCliente(ActionEvent event) {

    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que no quiere modificar al cliente seleccionado?", escenario)) {
    		escenario.close();
    	}else {
    		event.consume();
    	}
    }
}

