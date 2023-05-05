package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
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

public class ControladorInsertarCliente {

	private Controlador controladorMVC;
	private ObservableList<Cliente> obsClientes;
	
    @FXML
    private Button buttonCancelarInsertarCliente;

    @FXML
    private Button buttonInsertarClienteInsertar;

    @FXML
    private Label labelInsertarCliente;

    @FXML
    private Label labelInsertarDni;

    @FXML
    private Label labelInsertarNombre;

    @FXML
    private Label labelInsertarTelefono;

    @FXML
    private TextField tfInsertarDni;

    @FXML
    private TextField tfInsertarNombre;

    @FXML
    private TextField tfInsertarTelefono;

    @FXML
    public void initialize() {
    	
    }
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void setClientes(ObservableList<Cliente> clientes) {
    	this.obsClientes = clientes;
    }
    
    public void inicializaCampos() {
    	tfInsertarNombre.setDisable(false);
    	tfInsertarDni.setDisable(false);
    	tfInsertarTelefono.setDisable(false);
    }
    
    @FXML
    private void insertarCliente(ActionEvent event) {
    	
    	try {
    		Cliente cliente = new Cliente(tfInsertarNombre.getText(), tfInsertarDni.getText(), tfInsertarTelefono.getText());
    		obsClientes.add(cliente);
    		controladorMVC.insertar(cliente);
    	
    		Dialogos.mostrarDialogoInformacion("EXITO AL INSERTAR CLIENTE", "El cliente se ha insertado con éxito.");
    		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	escenario.close();
    	}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
    		Dialogos.mostrarDialogoError("ERROR AL INSERTAR CLIENTE", e.getMessage());
    	}
    }

    @FXML
    private void salirCliente(ActionEvent event) {

    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que no quiere añadir un nuevo cliente?", escenario)) {
    		escenario.close();
    	}else {
    		event.consume();
    	}
    }
}
