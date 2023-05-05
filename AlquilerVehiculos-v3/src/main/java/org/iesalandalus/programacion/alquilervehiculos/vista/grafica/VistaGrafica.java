package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladoresvista.ControladorEscenarioPrincipal;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGrafica extends Vista{

	private static Controlador controladorMVC = null;
	
	public void setControlador(Controlador controlador) {
		controladorMVC = controlador;
	}
	
	public void comenzar() {
		launch(this.getClass());
	}
	
	public void terminar() {
		controladorMVC.terminar();
		System.out.println("La vista ha terminado.");
	}
	
	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		try {
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/EscenarioPrincipal.fxml"));
			Parent raiz = loader.load();
			
			ControladorEscenarioPrincipal controladorEscenarioPrincipal = loader.getController();
			controladorEscenarioPrincipal.setControladorMVC(controladorMVC);
			controladorEscenarioPrincipal.inicializaObservables();
			
			Scene scene = new Scene(raiz);
			scene.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
			escenarioPrincipal.setScene(scene);
			escenarioPrincipal.getIcons().add(new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/logo.png")));
			escenarioPrincipal.setTitle("Gestión del alquiler de vehículos");
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Está seguro de que quiere salir de la aplicación?", escenarioPrincipal)) {
			terminar();
			escenarioPrincipal.close();
		}else {
			e.consume();	
		}
	}
}
