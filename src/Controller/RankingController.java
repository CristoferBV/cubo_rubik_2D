
package Controller;

import App.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cristofer
 */
public class RankingController implements Initializable {

    @FXML
    private AnchorPane escenaPrincipal;
    @FXML
    private Pane escenaRanking;
    @FXML
    private ListView<String> listaRanking;
    
    //variables locales
    private static final String NOMBRE_ARCHIVO = "C://"
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
        // Leer el contenido de partidaGuardada.txt y ordenar las partidas
        List<Partida> partidas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String line;
            Partida currentPartida = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Usuario: ")) {
                    currentPartida = new Partida();
                    currentPartida.usuario = line.substring("Usuario: ".length());
                } else if (line.startsWith("Tiempo: ")) {
                    currentPartida.tiempo = line.substring("Tiempo: ".length());
                } else if (line.startsWith("Puntaje: ")) {
                    currentPartida.puntaje = Integer.parseInt(line.substring("Puntaje: ".length()));
                } else if (line.startsWith("Movimientos del Cubo:")) {
                    currentPartida.movimientos = br.readLine();
                    partidas.add(currentPartida);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar las partidas por tiempo ascendente y puntaje descendente
        Collections.sort(partidas, new Comparator<Partida>() {
            @Override
            public int compare(Partida p1, Partida p2) {
                if (p1.tiempo.equals(p2.tiempo)) {
                    return Integer.compare(p2.puntaje, p1.puntaje);
                } else {
                    return p1.tiempo.compareTo(p2.tiempo);
                }
            }
        });

        // Escribir los datos ordenados en listaRanking
        for (Partida partida : partidas) {
            listaRanking.getItems().add("Usuario: " + partida.usuario);
            listaRanking.getItems().add("Tiempo: " + partida.tiempo);
            listaRanking.getItems().add("Puntaje: " + partida.puntaje);
            listaRanking.getItems().add("Movimientos del Cubo:");
            listaRanking.getItems().add(partida.movimientos);
            listaRanking.getItems().add("");
        }
    }

    static class Partida {
        String usuario;
        String tiempo;
        int puntaje;
        String movimientos;
    }    

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {  
        App.Close((Stage) escenaPrincipal.getScene().getWindow());
        App.setRoot("/View/Menu", 400, 600);
    }
    
}