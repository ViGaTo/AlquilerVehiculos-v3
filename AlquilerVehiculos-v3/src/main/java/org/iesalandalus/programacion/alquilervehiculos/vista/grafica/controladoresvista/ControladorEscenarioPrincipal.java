package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControladorEscenarioPrincipal {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Controlador controladorMVC;
	
    @FXML
    private Label labelAlquileres;

    @FXML
    private Label labelAlquileresCliente;

    @FXML
    private Label labelAlquileresVehiculo;

    @FXML
    private Label labelCliente;

    @FXML
    private Label labelVehiculos;

    @FXML
    private MenuBar menu;

    @FXML
    private MenuItem miAcerca;

    @FXML
    private MenuItem miBorrarAlquiler;

    @FXML
    private MenuItem miBorrarAlquilerCliente;

    @FXML
    private MenuItem miBorrarAlquilerVehiculo;

    @FXML
    private MenuItem miBorrarCliente;

    @FXML
    private MenuItem miBorrarVehiculo;

    @FXML
    private MenuItem miDevolverAlquiler;

    @FXML
    private MenuItem miDevolverAlquilerCliente;

    @FXML
    private MenuItem miDevolverAlquilerVehiculo;

    @FXML
    private MenuItem miInsertarAlquiler;

    @FXML
    private MenuItem miInsertarAlquilerCliente;

    @FXML
    private MenuItem miInsertarAlquilerVehiculo;

    @FXML
    private MenuItem miInsertarCliente;

    @FXML
    private MenuItem miInsertarVehiculo;

    @FXML
    private MenuItem miModificarCliente;

    @FXML
    private MenuItem miSalir;

    //Tabla Alquileres
    @FXML
    private TableView<Alquiler> tablaAlquileres;

    @FXML
    private TableColumn<Alquiler, String> tcNombreAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcDniAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcModeloAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcMatriculaAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcFechaAlquilerAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcFechaDevolucionAlquiler;
    @FXML
    private TableColumn<Alquiler, String> tcPrecioAlquiler;
    
    //Tabla Alquileres del cliente
    @FXML
    private TableView<Alquiler> tablaAlquileresCliente;

    @FXML
    private TableColumn<Alquiler, String> tcModeloCliente;
    @FXML
    private TableColumn<Alquiler, String> tcMatriculaCliente;
    @FXML
    private TableColumn<Alquiler, String> tcFechaAlquilerCliente;
    @FXML
    private TableColumn<Alquiler, String> tcFechaDevolucionCliente;
    @FXML
    private TableColumn<Alquiler, String> tcPrecioCliente;
    
    //Tabla Alquileres del vehiculo
    @FXML
    private TableView<Alquiler> tablaAlquileresVehiculo;

    @FXML
    private TableColumn<Alquiler, String> tcNombreVehiculo;
    @FXML
    private TableColumn<Alquiler, String> tcDniVehiculo;
    @FXML
    private TableColumn<Alquiler, String> tcFechaAlquilerVehiculo;
    @FXML
    private TableColumn<Alquiler, String> tcFechaDevolucionVehiculo;
    @FXML
    private TableColumn<Alquiler, String> tcPrecioVehiculo;
    
    //Tabla Clientes
    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> tcNombreCliente;
    @FXML
    private TableColumn<Cliente, String> tcDniCliente;
    @FXML
    private TableColumn<Cliente, String> tcTelefonoCliente;
    
    //Tabla Vehiculos
    @FXML
    private TableView<Vehiculo> tablaVehiculos;
    
    @FXML
    private TableColumn<Vehiculo, String> tcMarcaVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcModeloVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcTipoVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcMatriculaVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcCilindradaVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcPlazasVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcPmaVehiculo;
    
    //Creacion Observable
    private ObservableList<Cliente> obsClientes = FXCollections.observableArrayList();
    private ObservableList<Vehiculo> obsVehiculos = FXCollections.observableArrayList();
    private ObservableList<Alquiler> obsAlquileres = FXCollections.observableArrayList();
    private ObservableList<Alquiler> obsAlquileresCliente = FXCollections.observableArrayList();
    private ObservableList<Alquiler> obsAlquileresVehiculo = FXCollections.observableArrayList();
    
    public void setControladorMVC(Controlador controlador) {
    	controladorMVC = controlador;
    }
    
    public void inicializaObservables() {
    	
    	tablaClientes.getSelectionModel().clearSelection();
    	obsClientes.setAll(controladorMVC.getClientes());
    	obsVehiculos.setAll(controladorMVC.getVehiculos());
    	obsAlquileres.setAll(controladorMVC.getAlquileres());
    }
    
    @FXML
    private void initialize() {
    	
    	//Tabla Clientes
    	tcNombreCliente.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getNombre()));
    	tcDniCliente.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDni()));
    	tcTelefonoCliente.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getTelefono()));
    	tablaClientes.setItems(obsClientes);
    	
    	tablaClientes.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarAlquileresCliente(nv));
    	
    	//Tabla Alquileres del cliente
    	
    	tcModeloCliente.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getModelo()));
    	tcMatriculaCliente.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getMatricula()));
    	tcFechaAlquilerCliente.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getFechaAlquiler().format(FORMATO_FECHA).toString()));
    	tcFechaDevolucionCliente.setCellValueFactory(alquiler -> new SimpleStringProperty(mostrarFechaDevolucion(alquiler.getValue())));
    	tcPrecioCliente.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getPrecio() + " €"));
    	tablaAlquileresCliente.setItems(obsAlquileresCliente);
    	
    	//Tabla Vehiculos
    	tcMarcaVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getMarca()));
    	tcModeloVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getModelo()));
    	tcTipoVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(getTipoVehiculo(vehiculo.getValue())));
    	tcMatriculaVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(vehiculo.getValue().getMatricula()));
    	tcCilindradaVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(mostrarCilindrada(vehiculo.getValue())));
    	tcPlazasVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(mostrarPlazas(vehiculo.getValue())));
    	tcPmaVehiculo.setCellValueFactory(vehiculo -> new SimpleStringProperty(mostrarPma(vehiculo.getValue())));
    	tablaVehiculos.setItems(obsVehiculos);
    	
    	tablaVehiculos.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> mostrarAlquileresVehiculo(nv));
    	
    	//Tabla Alquileres del vehiculo
    	
    	tcNombreVehiculo.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getNombre()));
    	tcDniVehiculo.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getDni()));
    	tcFechaAlquilerVehiculo.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getFechaAlquiler().format(FORMATO_FECHA).toString()));
    	tcFechaDevolucionVehiculo.setCellValueFactory(alquiler -> new SimpleStringProperty(mostrarFechaDevolucion(alquiler.getValue())));
    	tcPrecioVehiculo.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getPrecio() + " €"));
    	tablaAlquileresVehiculo.setItems(obsAlquileresVehiculo);
    	
    	//Tabla Alquileres
    	
    	tcNombreAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getNombre()));
    	tcDniAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getCliente().getDni()));
    	tcModeloAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getModelo()));
    	tcMatriculaAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getVehiculo().getMatricula()));
    	tcFechaAlquilerAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getFechaAlquiler().format(FORMATO_FECHA).toString()));
    	tcFechaDevolucionAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(mostrarFechaDevolucion(alquiler.getValue())));
    	tcPrecioAlquiler.setCellValueFactory(alquiler -> new SimpleStringProperty(alquiler.getValue().getPrecio() + " €"));
    	tablaAlquileres.setItems(obsAlquileres);
    }
    
    @FXML
    void insertarCliente(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarCliente.fxml"));
    		Parent raiz = loader.load();
    	
    		Scene escena = new Scene(raiz);
    		ControladorInsertarCliente cInsertarCliente = loader.getController();
    		cInsertarCliente.inicializaCampos();
    		cInsertarCliente.setControladorMVC(controladorMVC);
    		cInsertarCliente.setClientes(obsClientes);
    	
    		Stage nuevoEscenario = new Stage();
    		escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    		nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    		nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    		nuevoEscenario.setTitle("Insertar cliente");
    		nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
			nuevoEscenario.setResizable(false);
    		nuevoEscenario.setScene(escena);
    		nuevoEscenario.showAndWait();
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR INSERTAR CLIENTE", e.getMessage());
    	}
    }
    
    @FXML
    void modificarCliente(ActionEvent event) {
    	
    	Cliente cliente = null;
    	
    	try {
    		cliente = tablaClientes.getSelectionModel().getSelectedItem();
    		
    		if(cliente != null) {
    			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ModificarCliente.fxml"));
    			Parent raiz = loader.load();
        	
    			Scene escena = new Scene(raiz);
    			ControladorModificarCliente cModificarCliente = loader.getController();
    			cModificarCliente.inicializaCampos();
    			cModificarCliente.cargarCliente(cliente);
    			cModificarCliente.setClientes(obsClientes, obsAlquileres);
    			cModificarCliente.setControladorMVC(controladorMVC);
        	
    			Stage nuevoEscenario = new Stage();
    			escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    			nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    			nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    			nuevoEscenario.setTitle("Modificar cliente");
    			nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
    			nuevoEscenario.setResizable(false);
    			nuevoEscenario.setScene(escena);
    			nuevoEscenario.showAndWait();
    		}else {
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR MODIFICAR CLIENTE", e.getMessage());
    	}
    }
    
    @FXML
    void insertarVehiculo(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarVehiculo.fxml"));
    		Parent raiz = loader.load();
    	
    		Scene escena = new Scene(raiz);
    		ControladorInsertarVehiculo cInsertarVehiculo = loader.getController();
    		cInsertarVehiculo.inicializaCampos();
    		cInsertarVehiculo.setControladorMVC(controladorMVC);
    		cInsertarVehiculo.setVehiculos(obsVehiculos);
    	
    		Stage nuevoEscenario = new Stage();
    		escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    		nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    		nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    		nuevoEscenario.setTitle("Insertar vehículo");
    		nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
			nuevoEscenario.setResizable(false);
    		nuevoEscenario.setScene(escena);
    		nuevoEscenario.showAndWait();
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR INSERTAR VEHÍCULO", e.getMessage());
    	}
    }
    
    @FXML
    void insertarAlquiler(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarAlquiler.fxml"));
    		Parent raiz = loader.load();
    	
    		Scene escena = new Scene(raiz);
    		ControladorInsertarAlquiler cInsertarAlquiler = loader.getController();
    		cInsertarAlquiler.inicializaCampos();
    		cInsertarAlquiler.setControladorMVC(controladorMVC);
    		cInsertarAlquiler.setAlquileres(obsAlquileres);
    	
    		Stage nuevoEscenario = new Stage();
    		escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    		nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    		nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    		nuevoEscenario.setTitle("Insertar alquiler");
    		nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
			nuevoEscenario.setResizable(false);
    		nuevoEscenario.setScene(escena);
    		nuevoEscenario.showAndWait();
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR INSERTAR ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    void devolverAlquiler(ActionEvent event) {
    	
    	Alquiler alquiler = null;
    	Alquiler alquilerCliente = null;
    	Alquiler alquilerVehiculo = null;
    	
    	try {
    		alquiler = tablaAlquileres.getSelectionModel().getSelectedItem();
    		alquilerCliente = tablaAlquileresCliente.getSelectionModel().getSelectedItem();
    		alquilerVehiculo = tablaAlquileresVehiculo.getSelectionModel().getSelectedItem();
    		
    		if(alquiler != null) {
    			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/DevolverAlquiler.fxml"));
    			Parent raiz = loader.load();
        	
    			Scene escena = new Scene(raiz);
    			ControladorDevolverAlquiler cDevolverAlquiler = loader.getController();
    			cDevolverAlquiler.inicializaCampos();
    			cDevolverAlquiler.cargarAlquiler(alquiler);
    			cDevolverAlquiler.setAlquileres(obsAlquileres);
    			cDevolverAlquiler.setControladorMVC(controladorMVC);
    			cDevolverAlquiler.setAlquiler(alquiler);
        	
    			Stage nuevoEscenario = new Stage();
    			escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    			nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    			nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    			nuevoEscenario.setTitle("Devolver alquiler");
    			nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
    			nuevoEscenario.setResizable(false);
    			nuevoEscenario.setScene(escena);
    			nuevoEscenario.showAndWait();
    			
    		}else if(alquilerCliente != null) {
    			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/DevolverAlquiler.fxml"));
    			Parent raiz = loader.load();
    	
    			Scene escena = new Scene(raiz);
    			ControladorDevolverAlquiler cDevolverAlquiler = loader.getController();
    			cDevolverAlquiler.inicializaCampos();
    			cDevolverAlquiler.cargarAlquiler(alquilerCliente);
    			cDevolverAlquiler.setAlquileres(obsAlquileres);
    			cDevolverAlquiler.setControladorMVC(controladorMVC);
    			cDevolverAlquiler.setAlquiler(alquilerCliente);
    	
    			Stage nuevoEscenario = new Stage();
    			escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    			nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    			nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    			nuevoEscenario.setTitle("Devolver alquiler");
    			nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
    			nuevoEscenario.setResizable(false);
    			nuevoEscenario.setScene(escena);
    			nuevoEscenario.showAndWait();
    			
    		}else if(alquilerVehiculo != null) {
    			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/DevolverAlquiler.fxml"));
    			Parent raiz = loader.load();
        	
    			Scene escena = new Scene(raiz);
    			ControladorDevolverAlquiler cDevolverAlquiler = loader.getController();
    			cDevolverAlquiler.inicializaCampos();
    			cDevolverAlquiler.cargarAlquiler(alquilerVehiculo);
    			cDevolverAlquiler.setAlquileres(obsAlquileres);
    			cDevolverAlquiler.setControladorMVC(controladorMVC);
    			cDevolverAlquiler.setAlquiler(alquilerVehiculo);
        	
    			Stage nuevoEscenario = new Stage();
    			escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
    			nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
    			nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
    			nuevoEscenario.setTitle("Devolver alquiler");
    			nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
    			nuevoEscenario.setResizable(false);
    			nuevoEscenario.setScene(escena);
    			nuevoEscenario.showAndWait();
    		}else {
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR DEVOLVER ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    void borrarCliente(ActionEvent event) {
    	
    	Cliente cliente = null;
    	
    	try {
    		cliente = tablaClientes.getSelectionModel().getSelectedItem();
    		
    		if(cliente != null && Dialogos.mostrarDialogoConfirmacion("Borrar cliente", "¿Quiere borrar el cliente seleccionado?", null)){
    			controladorMVC.borrar(cliente);
    			inicializaObservables();
    			obsAlquileresCliente.clear();
    			obsAlquileresVehiculo.clear();
    			
    			Dialogos.mostrarDialogoInformacion("EXITO AL BORRAR CLIENTE", "El cliente se ha borrado con éxito.");
    		}else if(cliente == null){
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR BORRAR CLIENTE", e.getMessage());
    	}
    }
    
    @FXML
    void borrarAlquilerCliente(ActionEvent event) {
    	
    	Alquiler alquiler = null;
    	
    	try {
    		alquiler = tablaAlquileresCliente.getSelectionModel().getSelectedItem();
    		
    		if(alquiler != null && Dialogos.mostrarDialogoConfirmacion("Borrar alquiler", "¿Quiere borrar el alquiler seleccionado?", null)){
    			controladorMVC.borrar(alquiler);
    			inicializaObservables();
    			obsAlquileresCliente.clear();
    			obsAlquileresVehiculo.clear();
    			
    			Dialogos.mostrarDialogoInformacion("EXITO AL BORRAR ALQUILER", "El alquiler se ha borrado con éxito.");
    		}else if(alquiler == null){
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR BORRAR ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    void borrarVehiculo(ActionEvent event) {
    	
    	Vehiculo vehiculo = null;
    	
    	try {
    		vehiculo = tablaVehiculos.getSelectionModel().getSelectedItem();
    		
    		if(vehiculo != null && Dialogos.mostrarDialogoConfirmacion("Borrar vehículo", "¿Quiere borrar el vehículo seleccionado?", null)){
    			controladorMVC.borrar(vehiculo);
    			inicializaObservables();
    			obsAlquileresCliente.clear();
    			obsAlquileresVehiculo.clear();
    			
    			Dialogos.mostrarDialogoInformacion("EXITO AL BORRAR VEHÍCULO", "El vehículo se ha borrado con éxito.");
    		}else if(vehiculo == null){
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR BORRAR VEHÍCULO", e.getMessage());
    	}
    }
    
    @FXML
    void borrarAlquilerVehiculo(ActionEvent event) {
    	
    	Alquiler alquiler = null;
    	
    	try {
    		alquiler = tablaAlquileresVehiculo.getSelectionModel().getSelectedItem();
    		
    		if(alquiler != null && Dialogos.mostrarDialogoConfirmacion("Borrar alquiler", "¿Quiere borrar el alquiler seleccionado?", null)){
    			controladorMVC.borrar(alquiler);
    			inicializaObservables();
    			obsAlquileresCliente.clear();
    			obsAlquileresVehiculo.clear();
    			
    			Dialogos.mostrarDialogoInformacion("EXITO AL BORRAR ALQUILER", "El alquiler se ha borrado con éxito.");
    		}else if(alquiler == null){
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR BORRAR ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    void borrarAlquiler(ActionEvent event) {
    	
    	Alquiler alquiler = null;
    	
    	try {
    		alquiler = tablaAlquileres.getSelectionModel().getSelectedItem();
    		
    		if(alquiler != null && Dialogos.mostrarDialogoConfirmacion("Borrar alquiler", "¿Quiere borrar el alquiler seleccionado?", null)){
    			controladorMVC.borrar(alquiler);
    			inicializaObservables();
    			obsAlquileresCliente.clear();
    			obsAlquileresVehiculo.clear();
    			
    			Dialogos.mostrarDialogoInformacion("EXITO AL BORRAR ALQUILER", "El alquiler se ha borrado con éxito.");
    		}else if(alquiler == null){
    			Dialogos.mostrarDialogoAdvertencia("ERROR DE SELECCIÓN", "Usted no esta seleccionado ningún objeto.");
    		}
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR BORRAR ALQUILER", e.getMessage());
    	}
    }
    
    @FXML
    private void salir(ActionEvent event) {
    	
    	if (Dialogos.mostrarDialogoConfirmacion("SALIR", "¿Está seguro de que quiere salir?", null)) {
    		controladorMVC.terminar();
    		System.out.println("La vista ha terminado.");
    		System.exit(0);
    	}
    }
    
    public void mostrarAlquileresCliente(Cliente cliente) {
    	try {
    		if(cliente != null) {
    			obsAlquileresCliente.setAll(controladorMVC.getAlquileres(cliente));
    		}
    	}catch(IllegalArgumentException e) {
    		obsAlquileresCliente.setAll(FXCollections.observableArrayList());
    	}
    }
    
    public void mostrarAlquileresVehiculo(Vehiculo vehiculo) {
    	try {
    		if(vehiculo != null) {
    			obsAlquileresVehiculo.setAll(controladorMVC.getAlquileres(vehiculo));
    		}
    	}catch(IllegalArgumentException e) {
    		obsAlquileresVehiculo.setAll(FXCollections.observableArrayList());
    	}
    }
    
    private String mostrarFechaDevolucion(Alquiler alquiler) {
    	if(alquiler.getFechaDevolucion() == null) {
    		return "";
    	}else {
    		return FORMATO_FECHA.format(alquiler.getFechaDevolucion());
    	}
    }
    
    private String getTipoVehiculo(Vehiculo vehiculo) {
    	String tipoVehiculo = null;
    	
    	if(vehiculo instanceof Turismo) {
			tipoVehiculo = "Turismo";
		}else if(vehiculo instanceof Autobus) {
			tipoVehiculo = "Autobús";
		}else if(vehiculo instanceof Furgoneta) {
			tipoVehiculo = "Furgoneta";
		}
		
		return tipoVehiculo;
    }
    
    private String mostrarCilindrada(Vehiculo vehiculo) {
    	String cilindrada = null;
    	
    	if(vehiculo instanceof Turismo) {
    		cilindrada = Integer.toString(((Turismo) vehiculo).getCilindrada()) + " cc";
    	}else {
    		cilindrada = "";
    	}
    	
    	return cilindrada;
    }
    
    private String mostrarPlazas(Vehiculo vehiculo) {
    	String plazas = null;
    	
    	if(vehiculo instanceof Autobus) {
    		plazas = Integer.toString(((Autobus) vehiculo).getPlazas());
    	}else if(vehiculo instanceof Furgoneta) {
    		plazas = Integer.toString(((Furgoneta) vehiculo).getPlazas());
    	}else {
    		plazas = "";
    	}
    	
    	return plazas;
    }
    
    private String mostrarPma(Vehiculo vehiculo) {
    	String pma = null;
    	
    	if(vehiculo instanceof Furgoneta) {
    		pma = Integer.toString(((Furgoneta) vehiculo).getPma());
    	}else {
    		pma = "";
    	}
    	
    	return pma;
    }
    
    @FXML
    private void acercaDe(ActionEvent event) {
    	
    	try {
    	FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/acercaDe.fxml"));
		Parent raiz = loader.load();
	
		Scene escena = new Scene(raiz);
		Stage nuevoEscenario = new Stage();
		escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
		nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
		nuevoEscenario.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
		nuevoEscenario.setTitle("Acerca de...");
		nuevoEscenario.setOnCloseRequest(e -> confirmarSalidaVentana(nuevoEscenario, e));
		nuevoEscenario.setResizable(false);
		nuevoEscenario.setScene(escena);
		nuevoEscenario.showAndWait();
		
    	}catch(Exception e) {
    		Dialogos.mostrarDialogoError("ERROR", e.getMessage());
    	}
    }
    
    @FXML
    private void confirmarSalidaVentana(Stage nuevoEscenario, WindowEvent e) {
		
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Está seguro de que desea salir de la ventana?", nuevoEscenario)) {
			nuevoEscenario.close();
		}else {
			e.consume();	
		}
	}
}
