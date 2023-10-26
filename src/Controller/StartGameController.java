package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import App.App;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class StartGameController implements Initializable {
    
    // Variables FXML
    @FXML
    private Pane paneTittle;
    @FXML
    private AnchorPane escenaPrincipal;
    
    //Variables locales
    static double w = 400;
    static double h = 600;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Crea la animación de crecimiento y contracción
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.9), paneTittle);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0.9); // Escala en X
        scaleTransition.setToY(0.9); // Escala en Y
        scaleTransition.setAutoReverse(true); // Auto inversión
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE); // Repetición infinita

        // Inicia la animación
        scaleTransition.play();
    }    

    @FXML
    private void playGame(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Menu", w, h);
       
    }
}
