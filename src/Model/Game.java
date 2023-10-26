
package Model;

import Controller.SimulatorGameController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Cristofer
 */
public class Game {
    
    private String nombre;
    private int puntaje;
    private String time; 
    private String movimientosCubo;
    
    private SimulatorGameController controller = new SimulatorGameController();

    public Game() {
    }

    public Game(String nombre, int puntaje, String time, String movimientosCubo) {
        this.puntaje = puntaje;
        this.time = time;
        this.movimientosCubo = movimientosCubo;
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getTime() {
        return time;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMovimientosCubo() {
        return movimientosCubo;
    }

    public void setMovimientosCubo(String movimientosCubo) {
        this.movimientosCubo = movimientosCubo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void saveGame(){

        String nombreUsuario = getNombre();
        String tiempoPartida = getTime();
        int puntajePartida = getPuntaje();
        String movimientosCubo = getMovimientosCubo(); 

        String partidaGuardada = String.format("Usuario: %s\nTiempo: %s\nPuntaje: %s\nMovimientos del Cubo:\n%s\n",
                nombreUsuario, tiempoPartida, puntajePartida, movimientosCubo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C://"
                + "Users//Cristofer//OneDrive - Universidad Nacional de Costa Rica"
                + "//Escritorio//Proyectos Prueba Cubo Rubik//MyCubeRubik//src"
                + "//Utils//partidaGuardada.txt", true))) {
            writer.write(partidaGuardada);
            writer.newLine(); // Agregar un salto de línea entre partidas
            System.out.println("Partida guardada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace(); 
        }      
    }
}
