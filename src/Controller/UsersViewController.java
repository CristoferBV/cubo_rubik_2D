package Controller;

import App.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class UsersViewController implements Initializable {

    @FXML
    private AnchorPane escenaPrincipal;
    @FXML
    private Pane escenaVistaUsuarios;
    @FXML
    private ListView<String> listaUsuarios;
    
    //variables locales
    static double w = 400;
    static double h = 600;
    Label Lb_nombreUsuario;
    Label tiempo;
    Label puntos;
    private String nombreUsuario;
    private String tiempoPartida;
    private String puntajePartida;

    private static final String NOMBRE_ARCHIVO = "C://"
                + "Users//Cristofer//OneDrive - Universidad Nacional de Costa Rica"
                + "//Escritorio//Proyectos Prueba Cubo Rubik//MyCubeRubik//src"
                + "//Utils//usuario.txt";
    private static final String NOMBRE_ARCHIVO_GAME = "C://"
                + "Users//Cristofer//OneDrive - Universidad Nacional de Costa Rica"
                + "//Escritorio//Proyectos Prueba Cubo Rubik//MyCubeRubik//src"
                + "//Utils//partidaGuardada.txt";
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Llenando la ListView
        try (FileReader fileReader = new FileReader(NOMBRE_ARCHIVO);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                // Agregar cada línea del archivo a la ListView
                listaUsuarios.getItems().add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UsersViewController() {
    }

    public UsersViewController(String nombreUsuario, String tiempoPartida, String puntajePartida) {
        this.nombreUsuario = nombreUsuario;
        this.tiempoPartida = tiempoPartida;
        this.puntajePartida = puntajePartida;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTiempoPartida() {
        return tiempoPartida;
    }

    public String getPuntajePartida() {
        return puntajePartida;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public void setTiempoPartida(String tiempoPartida) {
        this.tiempoPartida = tiempoPartida;
    }

    public void setPuntajePartida(String puntajePartida) {
        this.puntajePartida = puntajePartida;
    }
    
    @FXML
    private void playSaveGame(ActionEvent event) throws IOException {
        // Obtener el nombre de usuario seleccionado
        nombreUsuario = listaUsuarios.getSelectionModel().getSelectedItem();

        if (nombreUsuario == null) {
            // Mostrar una alerta si no se ha seleccionado un usuario
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un usuario.");
            alert.showAndWait();
            return; // Salir del método
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO_GAME))) {
            String linea;
            boolean bandera = false;
            tiempoPartida = "";
            puntajePartida = "";
            StringBuilder movimientosPartida = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Usuario: " + nombreUsuario)) {
                    bandera = true;
                } else if (bandera) {
                    if (linea.startsWith("Tiempo: ")) {
                        tiempoPartida = linea.substring(8);
                    } else if (linea.startsWith("Puntaje: ")) {
                        puntajePartida = linea.substring(9);
                    } else if (linea.equals("Movimientos del Cubo:")) {
                        // Comienza a leer los movimientos
                        while ((linea = reader.readLine()) != null) {
                            if (linea.isEmpty() || linea.startsWith("Usuario: ")) {
                                break; // Sal del bucle si la línea está vacía o es un nuevo usuario
                            }
                            movimientosPartida.append(linea).append(" ");
                        }
                        break;
                    }
                }
            }

            if (bandera) {
                // Crear una instancia de SimulatorGameController si aún no tienes una
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SimulatorGame.fxml"));
                Parent root = loader.load();
                SimulatorGameController s = loader.getController();

                // Establecer los datos en SimulatorGameController
                s.actualizarDatosPartida(puntajePartida, tiempoPartida, nombreUsuario);

                // Cerrar la ventana actual
                App.Close((Stage) escenaPrincipal.getScene().getWindow());
                // Cargar y mostrar la nueva escena (SimulatorGame.fxml)
                App.setRoot("/View/SimulatorGame", 1000, 600);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Menu", w, h);
    }
}    