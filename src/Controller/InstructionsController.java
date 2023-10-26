
package Controller;

import App.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class InstructionsController implements Initializable {

    @FXML
    private AnchorPane escenaPrincipal;
    
    //Variables Locales
    static double w = 400;
    static double h = 600;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Menu", w, h);
    }
    
}
