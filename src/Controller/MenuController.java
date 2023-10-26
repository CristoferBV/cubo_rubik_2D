package Controller;

import App.App;
import Model.Usuario;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class MenuController implements Initializable {

    @FXML
    private Pane escenaMenu;
    @FXML
    private Pane escenaInicio;
    @FXML
    private AnchorPane escenaPrincipal;
    @FXML
    private TextField txt_nombreUsuario;

    // Variables locales
    static double w = 1000;
    static double h = 600;
    private static final String NOMBRE_ARCHIVO = "C://"
                + "Users//Cristofer//OneDrive - Universidad Nacional de Costa Rica"
                + "//Escritorio//Proyectos Prueba Cubo Rubik//MyCubeRubik//src"
                + "//Utils//usuario.txt";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        escenaInicio.setVisible(false);
        escenaMenu.setVisible(true);
    }

    @FXML
    private void savedUser(ActionEvent event) throws IOException {
        String nombreUsuario = txt_nombreUsuario.getText();

        if (nombreUsuario.isEmpty()) {
            // Mostrar una alerta si el campo de nombre de usuario está vacío
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, escriba un nombre de usuario.");
            alert.showAndWait();
        } else {
            // Crear un objeto Usuario con el nombre ingresado
            Usuario usuario = new Usuario(nombreUsuario);

            // Lógica para guardar el usuario en el archivo de texto
            guardarUsuarioEnArchivo(usuario);

            App.Close((Stage) escenaPrincipal.getScene().getWindow());
            App.setRoot("/View/SimulatorGame", w, h);
        }
    }

    @FXML
    private void viewUsers(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/UsersView", 400, 600);
    }

    @FXML
    private void addUser(ActionEvent event) throws IOException {
        escenaInicio.setVisible(true);
        escenaMenu.setVisible(false);
    }

    private void guardarUsuarioEnArchivo(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            writer.write(usuario.getNombre());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    @FXML
    private void returnMenu(ActionEvent event) {
        escenaInicio.setVisible(false);
        escenaMenu.setVisible(true);
    }

    @FXML
    private void instructionsGame(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Instructions", w, h);
    }

    @FXML
    private void usersRanking(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Ranking", 400, 600);
    }
    
}