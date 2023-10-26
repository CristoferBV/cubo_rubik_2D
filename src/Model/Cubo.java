
package Model;

import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Cristofer
 */
public class Cubo {
    
    public TipoCara [][] amarillo = new TipoCara[3][3];
    public TipoCara [][] azul = new TipoCara[3][3];
    public TipoCara [][] blanco = new TipoCara[3][3];
    public TipoCara [][] verde = new TipoCara[3][3];
    public TipoCara [][] naranja = new TipoCara[3][3];
    public TipoCara [][] rojo = new TipoCara[3][3];
    
    public boolean[][] cambiosAmarilla = new boolean[3][3];
    public TipoCara [][] tempAmarillo = new TipoCara[3][3];
    private Stack<String> movimientosRealizados = new Stack<>();
    private Stack<Integer>movimientosPila = new Stack<>();
    
    public Cubo(){
        iniciarCaras();
        movimientosPila = new Stack<>();
        movimientosRealizados = new Stack<>();
    } 
    
    //Método que inicializa las caras
    public void iniciarCaras(){
        for (int i = 0; i < amarillo.length; i++){
           for (int j = 0; j < amarillo[i].length; j++){
                amarillo [i][j] = TipoCara.AMARILLO;
                azul [i][j] = TipoCara.AZUL;
                blanco [i][j] = TipoCara.BLANCO;
                verde [i][j] = TipoCara.VERDE;
                rojo [i][j] = TipoCara.ROJO;
                naranja [i][j] = TipoCara.NARANJA; 
                
                cambiosAmarilla[i][j] = false;
                tempAmarillo[i][j] = TipoCara.AMARILLO;
           }
        }
    }
    
    public void rotarCara(TipoCara [][] cara){
        
        TipoCara celda1 = cara[0][0];
        TipoCara celda2 = cara[0][1];
        TipoCara celda3 = cara[0][2];
        
        TipoCara celda4 = cara[1][0];
        TipoCara celda6 = cara[1][2];
        
        TipoCara celda7 = cara[2][0];
        TipoCara celda8 = cara[2][1];
        TipoCara celda9 = cara[2][2];
        
        cara[0][0] =  celda7;
        cara[0][1] =  celda4;
        cara[0][2] =  celda1;
        
        cara[1][0] =  celda8;
        cara[1][2] =  celda2;
        
        cara[2][0] =  celda9;
        cara[2][1] =  celda6;
        cara[2][2] =  celda3;   
    }
    
    public void rotarCaraInverse(TipoCara[][] cara) {
        // Realiza la rotación inversa de la cara
        TipoCara celda1 = cara[0][0]; //esquina superior izquierda
        TipoCara celda2 = cara[0][1];
        TipoCara celda3 = cara[0][2]; //esquina superior derecha

        TipoCara celda4 = cara[1][0];
        TipoCara celda6 = cara[1][2];

        TipoCara celda7 = cara[2][0]; //esquina inferior izquierda
        TipoCara celda8 = cara[2][1];
        TipoCara celda9 = cara[2][2]; //esquina inferior derecha

        cara[0][0] = celda3;
        cara[0][1] = celda6;
        cara[0][2] = celda9;

        cara[1][0] = celda2;
        cara[1][2] = celda8;

        cara[2][0] = celda1;
        cara[2][1] = celda4;
        cara[2][2] = celda7;
    }

    public void moveRight(){
        
        // Realiza el movimiento hacia la derecha en la cara frontal
        TipoCara aux1 = amarillo[0][2];
        TipoCara aux2 = amarillo[1][2];
        TipoCara aux3 = amarillo[2][2];

        amarillo[0][2] = verde[0][2];
        amarillo[1][2] = verde[1][2];
        amarillo[2][2] = verde[2][2];

        verde[0][2] = blanco[0][0]; 
        verde[1][2] = blanco[1][0];
        verde[2][2] = blanco[2][0];

        blanco[0][0] = azul[0][2];
        blanco[1][0] = azul[1][2];
        blanco[2][0] = azul[2][2];

        azul[0][2] = aux1;
        azul[1][2] = aux2;
        azul[2][2] = aux3; 
        
        movimientosRealizados.push("moveRight");
        
        rotarCara(naranja);   
    }
    
    public void moveLeft(){
        
        // Realiza el movimiento hacia la izquierda en la cara frontal
        TipoCara aux1 = amarillo[0][0];
        TipoCara aux2 = amarillo[1][0];
        TipoCara aux3 = amarillo[2][0];

        amarillo[0][0] = azul[0][0];
        amarillo[1][0] = azul[1][0];
        amarillo[2][0] = azul[2][0];

        azul[0][0] = blanco[0][2];
        azul[1][0] = blanco[1][2];
        azul[2][0] = blanco[2][2];

        blanco[0][2] = verde[0][0];
        blanco[1][2] = verde[1][0];
        blanco[2][2] = verde[2][0];

        verde[0][0] = aux1;
        verde[1][0] = aux2;
        verde[2][0] = aux3; 
        
        movimientosRealizados.push("moveLeft");
        rotarCara(rojo);
    }
      
    public void moveUp(){
        
        // Realiza el movimiento hacia arriba en la cara frontal
        TipoCara aux1 = amarillo[0][0];
        TipoCara aux2 = amarillo[0][1];
        TipoCara aux3 = amarillo[0][2];

        amarillo[0][0] = naranja[0][0];
        amarillo[0][1] = naranja[0][1];
        amarillo[0][2] = naranja[0][2];

        naranja[0][0] = blanco[0][0]; 
        naranja[0][1] = blanco[0][1];
        naranja[0][2] = blanco[0][2];

        blanco[0][0] = rojo[0][0];
        blanco[0][1] = rojo[0][1];
        blanco[0][2] = rojo[0][2];

        rojo[0][0] = aux1;
        rojo[0][1] = aux2;
        rojo[0][2] = aux3;
        
        movimientosRealizados.push("moveUp");
        rotarCara(azul); 
    }
    
    public void moveDown(){
        
        // Realiza el movimiento hacia abajo en la cara frontal
        TipoCara aux1 = amarillo[2][0];
        TipoCara aux2 = amarillo[2][1];
        TipoCara aux3 = amarillo[2][2];

        amarillo[2][0] = rojo[2][0];
        amarillo[2][1] = rojo[2][1];
        amarillo[2][2] = rojo[2][2];

        rojo[2][0] = blanco[2][0];
        rojo[2][1] = blanco[2][1];
        rojo[2][2] = blanco[2][2];

        blanco[2][0] = naranja[2][0];
        blanco[2][1] = naranja[2][1];
        blanco[2][2] = naranja[2][2];

        naranja[2][0] = aux1;
        naranja[2][1] = aux2;
        naranja[2][2] = aux3;
        
        movimientosRealizados.push("moveDown");
        rotarCara(verde); 
    }

    public void moveFront(){
        
        TipoCara aux1 = azul[2][0];
        TipoCara aux2 = azul[2][1];
        TipoCara aux3 = azul[2][2];
        
        azul[2][0] = rojo[0][2];
        azul[2][1] = rojo[1][2];
        azul[2][2] = rojo[2][2];
        
        rojo[0][2] = verde[0][0]; 
        rojo[1][2] = verde[0][1];
        rojo[2][2] = verde[0][2];
        
        verde[0][0] = naranja[0][0];
        verde[0][1] = naranja[1][0];
        verde[0][2] = naranja[2][0];
        
        naranja[0][0] = aux1;
        naranja[1][0] = aux2;
        naranja[2][0] = aux3;
        
        movimientosRealizados.push("moveFront");
        rotarCara(amarillo);   
    }
    
    public void moveBack(){ //
        
        TipoCara aux1 = azul[0][0];
        TipoCara aux2 = azul[0][1];
        TipoCara aux3 = azul[0][2];
        
        azul[0][0] = naranja[0][2];
        azul[0][1] = naranja[1][2];
        azul[0][2] = naranja[2][2];
        
        naranja[0][2] = verde[2][0];
        naranja[1][2] = verde[2][1];
        naranja[2][2] = verde[2][2];
        
        verde[2][0] = rojo[0][0];
        verde[2][1] = rojo[1][0];
        verde[2][2] = rojo[2][0];
        
        rojo[0][0] = aux1;
        rojo[1][0] = aux2;
        rojo[2][0] = aux3;
        
        movimientosRealizados.push("moveBack");
        rotarCara(blanco); 
    }
    
    //Movimientos a la inversa
    
    public void moveRightInverse() {
        // Realiza el movimiento hacia la derecha en la cara frontal (en sentido inverso)
        
        TipoCara aux1 = amarillo[0][2];
        TipoCara aux2 = amarillo[1][2];
        TipoCara aux3 = amarillo[2][2];
        
        amarillo[0][2] = azul[0][2];
        amarillo[1][2] = azul[1][2];
        amarillo[2][2] = azul[2][2];
        
        azul[0][2] = blanco[0][0];
        azul[1][2] = blanco[1][0];
        azul[2][2] = blanco[2][0];
        
        blanco[0][0] = verde[0][2];
        blanco[1][0] = verde[1][2];
        blanco[2][0] = verde[2][2];
        
        verde[0][2] = aux1;
        verde[1][2] = aux2;
        verde[2][2] = aux3;
                
        // Deshace la rotación de la cara naranja (en sentido inverso)
        rotarCaraInverse(naranja);   
    }
    
    public void moveLeftInverse() {
        // Realiza el movimiento hacia la izquierda en la cara frontal (en sentido inverso)
        TipoCara aux1 = amarillo[0][0];
        TipoCara aux2 = amarillo[1][0];
        TipoCara aux3 = amarillo[2][0];
        
        amarillo[0][0] = verde[0][0];
        amarillo[1][0] = verde[1][0];
        amarillo[2][0] = verde[2][0];
        
        verde[0][0] = blanco[0][2];
        verde[1][0] = blanco[1][2];
        verde[2][0] = blanco[2][2];
        
        blanco[0][2] = azul[0][0];
        blanco[1][2] = azul[1][0];
        blanco[2][2] = azul[2][0];
        
        azul[0][0] = aux1;
        azul[1][0] = aux2;
        azul[2][0] = aux3;

        // Deshace la rotación de la cara roja (en sentido inverso)
        rotarCaraInverse(rojo);
    }
    
    public void moveUpInverse() {
        // Realiza el movimiento hacia arriba en la cara frontal (en sentido inverso)
        TipoCara aux1 = amarillo[0][0];
        TipoCara aux2 = amarillo[0][1];
        TipoCara aux3 = amarillo[0][2];
        
        amarillo[0][0] = rojo[0][0];
        amarillo[0][1] = rojo[0][1];
        amarillo[0][2] = rojo[0][2];
        
        rojo[0][0] = blanco[0][0];
        rojo[0][1] = blanco[0][1];
        rojo[0][2] = blanco[0][2];
        
        blanco[0][0] = naranja[0][0];
        blanco[0][1] = naranja[0][1];
        blanco[0][2] = naranja[0][2];
        
        naranja[0][0] = aux1;
        naranja[0][1] = aux2;
        naranja[0][2] = aux3;

        // Deshace la rotación de la cara azul (en sentido inverso)
        rotarCaraInverse(azul);  
    }

    public void moveDownInverse() {
        // Realiza el movimiento hacia abajo en la cara frontal (en sentido inverso)
        TipoCara aux1 = amarillo[2][0];
        TipoCara aux2 = amarillo[2][1];
        TipoCara aux3 = amarillo[2][2];
        
        amarillo[2][0] = naranja[2][0];
        amarillo[2][1] = naranja[2][1];
        amarillo[2][2] = naranja[2][2];
        
        naranja[2][0] = blanco[2][0]; //Se cambio blanco[2][0] - blanco[2][1] - blanco[2][2]
        naranja[2][1] = blanco[2][1];
        naranja[2][2] = blanco[2][2];
        
        blanco[2][0] = rojo[2][0];
        blanco[2][1] = rojo[2][1];
        blanco[2][2] = rojo[2][2];
        
        rojo[2][0] = aux1;
        rojo[2][1] = aux2;
        rojo[2][2] = aux3;
       
        rotarCaraInverse(verde);
    }

    public void moveFrontInverse() {
        // Realiza el movimiento hacia adelante en la cara frontal (en sentido inverso)
        TipoCara aux1 = azul[2][0];
        TipoCara aux2 = azul[2][1];
        TipoCara aux3 = azul[2][2];
        
        azul[2][0] = naranja[0][0];
        azul[2][1] = naranja[1][0];
        azul[2][2] = naranja[2][0];
        
        naranja[0][0] = verde[0][0];
        naranja[1][0] = verde[0][1];
        naranja[2][0] = verde[0][2];
        
        verde[0][0] = rojo[0][2];
        verde[0][1] = rojo[1][2];
        verde[0][2] = rojo[2][2];
        
        rojo[0][2] = aux1;
        rojo[1][2] = aux2;
        rojo[2][2] = aux3;

        rotarCaraInverse(amarillo);
    }

    public void moveBackInverse() { 
        // Realiza el movimiento hacia atrás en la cara frontal (en sentido inverso)
        TipoCara aux1 = rojo[0][0];
        TipoCara aux2 = rojo[1][0];
        TipoCara aux3 = rojo[2][0];

        rojo[0][0] = verde[2][0];
        rojo[1][0] = verde[2][1];
        rojo[2][0] = verde[2][2];

        verde[2][0] = naranja[0][2];
        verde[2][1] = naranja[1][2];
        verde[2][2] = naranja[2][2];

        naranja[0][2] = azul[0][0];
        naranja[1][2] = azul[0][1];
        naranja[2][2] = azul[0][2];

        azul[0][0] = aux1;
        azul[0][1] = aux2;
        azul[0][2] = aux3;

        // Deshace la rotación de la cara blanco (en sentido inverso)
        rotarCaraInverse(blanco);
    }

    public void caraAmarilla() { 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cambiosAmarilla[i][j]) {
                    tempAmarillo[i][j] = amarillo[i][j];
                }
               
            }
        }    
    }
    
    public void marcarCambioEnCaraAmarilla(int fila, int columna) {
        cambiosAmarilla[fila][columna] = true;
    }
    
    public void deshacerMovimientos() {
        // Revertir los movimientos en orden inverso
        while (movimientosRealizados.isEmpty() != true) {
            String movimiento = movimientosRealizados.pop();
            //System.out.println(movimiento);

            // Aplicar el movimiento inverso
            switch (movimiento) {
                case "moveRight" -> moveRightInverse();
                case "moveLeft" -> moveLeftInverse();
                case "moveUp" -> moveUpInverse();
                case "moveDown" -> moveDownInverse();
                case "moveFront" -> moveFrontInverse();
                case "moveBack" -> moveBackInverse();
                default -> {
                }
            }
        }
        System.out.println("\n");
    }
    
    public void deshacerMovimientos(int tiempoEntreMovimientos, Runnable actualizarInterfaz) {
        // Revertir los movimientos en orden inverso
        while (!movimientosRealizados.isEmpty()) {
            String movimiento = movimientosRealizados.pop();

            // Aplicar el movimiento inverso
            switch (movimiento) {
                case "moveRight" -> moveRightInverse();
                case "moveLeft" -> moveLeftInverse();
                case "moveUp" -> moveUpInverse();
                case "moveDown" -> moveDownInverse();
                case "moveFront" -> moveFrontInverse();
                case "moveBack" -> moveBackInverse();
                default -> {
                }
            }
            
            try {
                Thread.sleep(tiempoEntreMovimientos); // Espera un tiempo entre movimientos
            } catch (InterruptedException e) {
                // Manejo de excepciones si es necesario
            }

            actualizarInterfaz.run(); // Llama a la función de actualización de la interfaz gráfica.
        }
        System.out.println("\n");
    }

    public void mezclarCubo(int movimientos, int tiempoEntreMovimientos, Runnable actualizarInterfaz) {
        Random rand = new Random();

        for (int i = 0; i < movimientos; i++) {
            int movimientoAleatorio = rand.nextInt(6); // 0 a 5 para los 6 posibles movimientos
            movimientosPila.push(movimientoAleatorio);

            switch (movimientoAleatorio) {
                case 0 -> moveRight();
                case 1 -> moveLeft();
                case 2 -> moveUp();
                case 3 -> moveDown();
                case 4 -> moveFront();
                case 5 -> moveBack();
            }

            try {
                Thread.sleep(tiempoEntreMovimientos); // Espera un tiempo entre movimientos
            } catch (InterruptedException e) {
                // Manejo de excepciones si es necesario
            }

            actualizarInterfaz.run(); // Llama a la función de actualización de la interfaz gráfica.
        }
    }      
}