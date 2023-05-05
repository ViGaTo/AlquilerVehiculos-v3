package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControladorInsertarVehiculo {

	private Controlador controladorMVC;
	private ObservableList<Vehiculo> obsVehiculos;
	
    @FXML
    private ToggleGroup TipoVehiculo;

    @FXML
    private Button buttonCancelarInsertarVehiculo;

    @FXML
    private Button buttonInsertarVehiculoInsertar;

    @FXML
    private Label labelInsertarCilindrada;

    @FXML
    private Label labelInsertarMarca;

    @FXML
    private Label labelInsertarMatricula;

    @FXML
    private Label labelInsertarModelo;

    @FXML
    private Label labelInsertarPlazas;

    @FXML
    private Label labelInsertarPma;

    @FXML
    private Label labelInsertarTipo;

    @FXML
    private RadioButton rbAutobusTipo;

    @FXML
    private RadioButton rbFurgonetaTipo;

    @FXML
    private RadioButton rbTurismoTipo;

    @FXML
    private TextField tfCilindrada;
    
    @FXML
    private TextField tfInsertarMarca;

    @FXML
    private TextField tfInsertarMatricula;

    @FXML
    private TextField tfInsertarModelo;
    
    @FXML
    private TextField tfPlazas;

    @FXML
    private TextField tfPma;

    @FXML
    public void initialize() {
    
    }
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void setVehiculos(ObservableList<Vehiculo> vehiculos) {
    	obsVehiculos = vehiculos;
    }
    
    public void inicializaCampos() {
    	tfInsertarMarca.setDisable(false);
    	tfInsertarModelo.setDisable(false);
    	tfInsertarMatricula.setDisable(false);
    	TipoVehiculo.selectedToggleProperty().addListener((ob, ov, nv) -> muestraCaracteristicas());
    }
    
    private void muestraCaracteristicas() {
    	
    	RadioButton seleccionado = (RadioButton) TipoVehiculo.getSelectedToggle();
    	
    	if(seleccionado == rbTurismoTipo) {
    		tfCilindrada.setDisable(false);
    		tfPlazas.setDisable(true);
        	tfPma.setDisable(true);
    	}else if(seleccionado == rbAutobusTipo) {
    		tfPlazas.setDisable(false);
    		tfCilindrada.setDisable(true);
    		tfPma.setDisable(true);
    	}else {
    		tfPlazas.setDisable(false);
    		tfPma.setDisable(false);
    		tfCilindrada.setDisable(true);
    	}
    }
    
    @FXML
    private void insertarVehiculo(ActionEvent event) {
    	
    	Vehiculo vehiculo = null;
    	
    	try {
    		if(rbTurismoTipo.isSelected()) {
    			vehiculo = new Turismo(tfInsertarMarca.getText(), tfInsertarModelo.getText(), Integer.parseInt(tfCilindrada.getText()), tfInsertarMatricula.getText());
    			obsVehiculos.add(vehiculo);
        		controladorMVC.insertar(vehiculo);
        		Dialogos.mostrarDialogoInformacion("EXITO AL INSERTAR VEHÍCULO", "El vehículo se ha insertado con éxito.");
        		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	    	escenario.close();
    		}else if(rbAutobusTipo.isSelected()) {
    			vehiculo = new Autobus(tfInsertarMarca.getText(), tfInsertarModelo.getText(), Integer.parseInt(tfPlazas.getText()), tfInsertarMatricula.getText());
    			obsVehiculos.add(vehiculo);
        		controladorMVC.insertar(vehiculo);
        		Dialogos.mostrarDialogoInformacion("EXITO AL INSERTAR VEHÍCULO", "El vehículo se ha insertado con éxito.");
        		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	    	escenario.close();
    		}else if(rbFurgonetaTipo.isSelected()){
    			vehiculo = new Furgoneta(tfInsertarMarca.getText(), tfInsertarModelo.getText(), Integer.parseInt(tfPlazas.getText()), Integer.parseInt(tfPma.getText()), tfInsertarMatricula.getText());
    			obsVehiculos.add(vehiculo);
        		controladorMVC.insertar(vehiculo);
        		Dialogos.mostrarDialogoInformacion("EXITO AL INSERTAR VEHÍCULO", "El vehículo se ha insertado con éxito.");
        		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	    	escenario.close();
    		}else {
    			Dialogos.mostrarDialogoError("ERROR AL INSERTAR VEHÍCULO", "ERROR: No se ha seleccionado ningún tipo de vehículo.");
    		}
    	}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
    		Dialogos.mostrarDialogoError("ERROR AL INSERTAR VEHÍCULO", e.getMessage());
    	}
    }
    
    @FXML
    private void salirVehiculo(ActionEvent event) {

    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que no quiere añadir un nuevo vehículo?", escenario)) {
    		escenario.close();
    	}else {
    		event.consume();
    	}
    }
}

