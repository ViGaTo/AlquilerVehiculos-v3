package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControladorInsertarAlquiler {

	private Controlador controladorMVC;
	private ObservableList<Alquiler> obsAlquileres;
	
    @FXML
    private Button buttonCancelarInsertarAlquiler;

    @FXML
    private Button buttonInsertarAlquilerInsertar;

    @FXML
    private DatePicker dpInsertarFecha_alquilerAlquiler;

    @FXML
    private Label labelInsertarDniAlquiler;

    @FXML
    private Label labelInsertarFecha_alquiler;

    @FXML
    private Label labelInsertarMatriculaAlquiler;

    @FXML
    private TextField tfInsertarDniAlquiler;

    @FXML
    private TextField tfInsertarMatriculaAlquiler;

    @FXML
    public void initialize() {
    	inicializaCalendario();
    }
    
    private void inicializaCalendario() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        	
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                	super.updateItem(item, empty);
                           
                    	if (item.isAfter(LocalDate.now())) {
                                setDisable(true);
                        }   
                    }
                };
            }
        };
        
        dpInsertarFecha_alquilerAlquiler.setDayCellFactory(dayCellFactory);
    }
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void setAlquileres(ObservableList<Alquiler> alquileres) {
    	obsAlquileres = alquileres;
    }
    
    public void inicializaCampos() {
    	tfInsertarDniAlquiler.setDisable(false);
    	tfInsertarMatriculaAlquiler.setDisable(false);
    	dpInsertarFecha_alquilerAlquiler.setDisable(false);
    }
    
    @FXML
    private void insertarAlquiler(ActionEvent event) {
    	
    	Cliente clienteBuscado = null;
    	Vehiculo vehiculoBuscado = null;
    	
    	try {
    		clienteBuscado = Cliente.getClienteConDni(tfInsertarDniAlquiler.getText());
    		vehiculoBuscado = Vehiculo.getVehiculoConMatricula(tfInsertarMatriculaAlquiler.getText());
    		
    		Alquiler alquiler = new Alquiler(clienteBuscado, vehiculoBuscado, dpInsertarFecha_alquilerAlquiler.getValue());
    		
    		controladorMVC.insertar(alquiler);
    		obsAlquileres.add(alquiler);
    		obsAlquileres.setAll(controladorMVC.getAlquileres());
    		
    		Dialogos.mostrarDialogoInformacion("EXITO AL INSERTAR ALQUILER", "El alquiler se ha insertado con éxito.");
    		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	escenario.close();
    	}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
    		Dialogos.mostrarDialogoError("ERROR AL INSERTAR ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    private void salirAlquiler(ActionEvent event) {

    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que no quiere añadir un nuevo alquiler?", escenario)) {
    		escenario.close();
    	}else {
    		event.consume();
    	}
    }
}

