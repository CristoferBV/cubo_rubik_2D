
package Controller;

import App.App;
import Model.Cubo;
import Model.Game;
import Model.TipoCara;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class SimulatorGameController implements Initializable {

    //Variables FXML
    @FXML
    private Pane escenaCubo;
    Pane[][] cells;
    @FXML
    private GridPane gridPaneCubo;
    @FXML
    private ToggleButton btn_Left;
    @FXML
    private ToggleButton btn_Right;
    @FXML
    private ToggleButton btn_Up;
    @FXML
    private ToggleButton btn_Down;
    @FXML
    private ToggleButton btn_Front;
    @FXML
    private ToggleButton btn_Back;
    @FXML
    public Label puntos;
    @FXML
    public Label Lb_nombreUsuario;
    @FXML
    public Label tiempo;
    @FXML
    private AnchorPane escenaPrincipal;
    
    //Variables locales
    Cubo cubo;
    private Game game;
    static double w = 400;
    static double h = 600;
    private int contadorPuntos = 0;
    private int minutos = 0;
    private int segundos = 0;
    private Timeline timeline;
    private static final String NOMBRE_ARCHIVO = "C://"
                + "Users//Cristofer//OneDrive - Universidad Nacional de Costa Rica"
                + "//Escritorio//Proyectos Prueba Cubo Rubik//MyCubeRubik//src"
                + "//Utils//usuario.txt";
    private String movimientosCubo = "";
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            segundos++;
            if (segundos == 60) {
                minutos++;
                segundos = 0;
            }
            tiempo.setText(String.format("%02d:%02d", minutos, segundos));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.stop();

        // Lógica para cargar el nombre de usuario desde el archivo de texto
        String nombreUsuario = ""; // Inicializamos con null
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                nombreUsuario = linea; // Actualiza el nombre de usuario en cada iteración
            }
            if (nombreUsuario != null) {
                Lb_nombreUsuario.setText(nombreUsuario);
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        
        //Pasarle datos al constructor de Game
        game = new Game(Lb_nombreUsuario.getText(), Integer.parseInt(puntos.getText()), tiempo.getText(), movimientosCubo);
        
        // Crear una instancia del cubo
        cubo = new Cubo();
        
        cells = new Pane[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Pane cell = new Pane();
                cell.setStyle("-fx-background-color: " 
                        + getColorName(cubo.amarillo[row][col]));
                cells[row][col] = cell;
                gridPaneCubo.add(cell, col, row);  
            }
        }
        
        //Movimientos del cubo
        btn_Left.setOnAction(e -> {
           timeline.play();
           cubo.moveLeft();
           actualizarInterfaz(cells);
           contadorPuntos++;
           game.setPuntaje(contadorPuntos);
           movimientosCubo += "L "; 
           game.setMovimientosCubo(movimientosCubo);
           puntos.setText(Integer.toString(contadorPuntos));
        });
        
        btn_Right.setOnAction(e ->{
            timeline.play();
            cubo.moveRight();
            actualizarInterfaz(cells);
            contadorPuntos++;
            game.setPuntaje(contadorPuntos);
            movimientosCubo += "R "; 
            game.setMovimientosCubo(movimientosCubo);
            puntos.setText(Integer.toString(contadorPuntos));
        });
        
        btn_Up.setOnAction(e -> {
            timeline.play();
            cubo.moveUp();
            actualizarInterfaz(cells);
            contadorPuntos++;
            game.setPuntaje(contadorPuntos);
            movimientosCubo += "U "; 
            game.setMovimientosCubo(movimientosCubo);
            puntos.setText(Integer.toString(contadorPuntos));
        });
        
        btn_Down.setOnAction(e -> {
            timeline.play();
            cubo.moveDown();
            actualizarInterfaz(cells);
            contadorPuntos++;
            game.setPuntaje(contadorPuntos);
            movimientosCubo += "D "; 
            game.setMovimientosCubo(movimientosCubo);
            puntos.setText(Integer.toString(contadorPuntos));
        });
        
        btn_Front.setOnAction(e -> {
            timeline.play();
            cubo.moveFront();
            actualizarInterfaz(cells);
            contadorPuntos++;
            game.setPuntaje(contadorPuntos);
            movimientosCubo += "F "; 
            game.setMovimientosCubo(movimientosCubo);
            puntos.setText(Integer.toString(contadorPuntos));
        });
        
        btn_Back.setOnAction(e -> {
            timeline.play();
            cubo.moveBack();
            actualizarInterfaz(cells);
            contadorPuntos++;
            game.setPuntaje(contadorPuntos);
            movimientosCubo += "B "; 
            game.setMovimientosCubo(movimientosCubo);
            puntos.setText(Integer.toString(contadorPuntos));
        });       
    }
 
    public void actualizaDatos(String nombreU, String tiempoP, String puntosP) {
        if (Lb_nombreUsuario != null) {
            Lb_nombreUsuario.setText(nombreU);
        }
        if (tiempo != null) {
            tiempo.setText(tiempoP);
        }
        if (puntos != null) {
            puntos.setText(puntosP);
        }
    }
    
    // Método para actualizar los datos en SimulatorGameController
    public void actualizarDatosPartida(String nombreUsuario, String tiempoPartida, String puntajePartida) {
        Lb_nombreUsuario.setText(nombreUsuario);
        tiempo.setText(tiempoPartida);
        puntos.setText(puntajePartida);
    }
  
    // Método para obtener el nombre del color según el tipo de cara
    private String getColorName(TipoCara cara) {
        return switch (cara) {
            case AMARILLO -> "yellow";
            case AZUL -> "blue";
            case BLANCO -> "white";
            case NARANJA -> "orange";
            case ROJO -> "red";
            case VERDE -> "green";
            default -> "white";
        };
    }
    
    //Metodos para cambiar la cara
    @FXML
    private void changeFaceRight(ActionEvent event) {
        timeline.play();
        actualizarCaraNaranja(cells);
    }

    @FXML
    private void changeFaceLeft(ActionEvent event) {
        timeline.play();
        actualizarCaraRoja(cells);
    }

    @FXML
    private void changeFaceUp(ActionEvent event) {
        timeline.play();
        actualizarCaraAzul(cells);
    }

    @FXML
    private void changeFaceDown(ActionEvent event) {
        timeline.play();
        actualizarCaraVerde(cells);
    }

    @FXML
    private void changeFaceBack(ActionEvent event) {
        timeline.play();
        actualizarCaraBlanco(cells);
    }

    @FXML
    private void changeFaceFront(ActionEvent event) {
        timeline.play();
       // Marcar los cambios en las celdas de la cara amarilla
        cubo.marcarCambioEnCaraAmarilla(0, 0);
        cubo.marcarCambioEnCaraAmarilla(0, 1);
        cubo.marcarCambioEnCaraAmarilla(0, 2);
        cubo.marcarCambioEnCaraAmarilla(1, 0);
        cubo.marcarCambioEnCaraAmarilla(1, 1);
        cubo.marcarCambioEnCaraAmarilla(1, 2);
        cubo.marcarCambioEnCaraAmarilla(2, 0);
        cubo.marcarCambioEnCaraAmarilla(2, 1);
        cubo.marcarCambioEnCaraAmarilla(2, 2);

        // Actualizar la interfaz antes de llamar a caraAmarilla
        actualizarInterfaz(cells);
        // Llamar a caraAmarilla
        cubo.caraAmarilla();
        // Actualizar la interfaz después de llamar a caraAmarilla
        actualizarInterfaz(cells);
    }
    
    //Metodos de actualización de caras
    
    // Método para actualizar la interfaz gráfica con los colores de la cara azul
    private void actualizarCaraAzul(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.azul[row][col]));
            }
        }
    }
    
    // Método para actualizar la interfaz gráfica con los colores de la cara blanco
    private void actualizarCaraBlanco(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.blanco[row][col]));
            }
        }
    }
    
    // Método para actualizar la interfaz gráfica con los colores de la cara verde
    private void actualizarCaraVerde(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.verde[row][col]));
            }
        }
    }
    
    // Método para actualizar la interfaz gráfica con los colores de la cara naranja
    private void actualizarCaraNaranja(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.naranja[row][col]));
            }
        }
    }
    
    // Método para actualizar la interfaz gráfica con los colores de la cara roja
    private void actualizarCaraRoja(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.rojo[row][col]));
            }
        }
    }
    
    // Actualiza la interfaz gráfica con los colores del cubo
    private void actualizarInterfaz(Pane[][] cells) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setStyle("-fx-background-color: " 
                        + getColorName(cubo.amarillo[row][col]));
            }
        }
    }

    @FXML
    private void scramble(ActionEvent event) {
        timeline.play();

        Thread mezclarThread = new Thread(() -> {
            cubo.mezclarCubo(15, 750, () -> {
                // Lógica para actualizar la interfaz gráfica con los colores del cubo
                Platform.runLater(() -> {
                    actualizarInterfaz(cells);
                });
            });
        });

        mezclarThread.start();
    }
    
    @FXML
    private void solve(ActionEvent event) {
        timeline.stop();

        Thread deshacerThread = new Thread(() -> {
            cubo.deshacerMovimientos(750, () -> {
                // Lógica para actualizar la interfaz gráfica con los colores del cubo
                Platform.runLater(() -> {
                    actualizarInterfaz(cells);
                });
            });
        });

        deshacerThread.start();
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea salir del juego?");
        timeline.stop();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
           App.Close((Stage) escenaPrincipal.getScene().getWindow());
           App.setRoot("/View/Menu", w, h);
        }else{
            timeline.play();
        } 
    }

    @FXML
    private void saveGame(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea guardar la partida?");
        timeline.stop();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
           game.setTime(tiempo.getText());
           game.saveGame();
        }else{
            timeline.play();
        }
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea reiniciar el juego?");
        timeline.stop();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            App.Close((Stage) escenaPrincipal.getScene().getWindow());
            App.setRoot("/View/SimulatorGame", 1000, 600);
        }else{
             timeline.play();
        } 
    }
}