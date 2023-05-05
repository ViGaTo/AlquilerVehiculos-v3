package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
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

public class ControladorDevolverAlquiler {

	private Controlador controladorMVC;
	private ObservableList<Alquiler> obsAlquileres;
	private Alquiler alquilerDevuelto;
	
    @FXML
    private Button buttonCancelarDevolverAlquiler;

    @FXML
    private Button buttonDevolverAlquilerDevolver;

    @FXML
    private DatePicker dpInsertarFecha_devolucionAlquiler;

    @FXML
    private Label labelDevolverDniAlquiler;

    @FXML
    private Label labelDevolverMatriculaAlquiler;

    @FXML
    private Label labeldevolverFecha_devolucion;

    @FXML
    private TextField tfDevolverDniAlquiler;

    @FXML
    private TextField tfDevolverMatriculaAlquiler;

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
                           
                    	if (item.isAfter(LocalDate.now()) || (item.isBefore(alquilerDevuelto.getFechaAlquiler().plusDays(1)))) {
                                setDisable(true);
                        }   
                    }
                };
            }
        };
        
        dpInsertarFecha_devolucionAlquiler.setDayCellFactory(dayCellFactory);
    }
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void setAlquileres(ObservableList<Alquiler> alquileres) {
    	obsAlquileres = alquileres;
    }
    
    public void setAlquiler(Alquiler alquiler) {
    	alquilerDevuelto = alquiler;
    }
    
    public void inicializaCampos() {
    	tfDevolverDniAlquiler.setDisable(true);
    	tfDevolverMatriculaAlquiler.setDisable(true);
    	dpInsertarFecha_devolucionAlquiler.setDisable(false);
    }
    
    public void cargarAlquiler(Alquiler alquiler) {
    	tfDevolverDniAlquiler.setText(alquiler.getCliente().getDni());
    	tfDevolverMatriculaAlquiler.setText(alquiler.getVehiculo().getMatricula());
    }
    
    @FXML
    private void devolverAlquiler(ActionEvent event){
    	
    	try {
    		controladorMVC.devolver(alquilerDevuelto.getCliente(), dpInsertarFecha_devolucionAlquiler.getValue());
    		obsAlquileres.setAll(controladorMVC.getAlquileres());
    		
    		Dialogos.mostrarDialogoInformacion("EXITO AL DEVOLVER ALQUILER", "El alquiler se ha devuelto con éxito.");
    		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	escenario.close();
    	}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
    		Dialogos.mostrarDialogoError("ERROR AL DEVOLVER ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    private void salirDevolverAlquiler(ActionEvent event) {

    	Stage escenario = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que no quiere devolver el alquiler seleccionado?", escenario)) {
    		escenario.close();
    	}else {
    		event.consume();
    	}
    }
}

