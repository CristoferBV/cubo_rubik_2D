package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public static double width = 400;
    public static double heigh = 600;
    private static Scene scene;

    public App() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/View/StartGame"));
        stage.setScene(scene);
        stage.show();
    }
    
    public static void Close(Stage stage){
        stage.close();
    }
    
    public static void setRoot(String fxml, double w, double h) throws IOException { //Se modificó el método a publico para poder acceder a ella
        Stage stage = new Stage();
        scene = new Scene(loadFXML(fxml), w, h);
        stage.setScene(scene);
        stage.show();
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}