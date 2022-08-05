/** interaccion con bloqueos del camino, tiene sus colisiones para interactuar con la nave */
package complementosJuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**Clase responsable. carga el puente dentro de estadosdeljuego*/
public class Puentes {
    
    private Image aparienciaPuente;
    private Rectangle ubicacionPuente;
    private final int anchoPuente=235, altoPuente=160;
    private Boolean ini = false;
    private int borrar =0;
    
    /**constructor por defecto . Inicializa el objeto de rectangulo para las colisiones y carga la imagen del puente*/
    
    public Puentes(int x , int y){
        ubicacionPuente=new Rectangle(x,y,anchoPuente,altoPuente);
        try {
            aparienciaPuente = ImageIO.read(new File("Puente.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen del puente no se encuentra en la ruta por defecto");
        }
    }
    
    /**Metodo que retorna un int. cambia la imagen del puente por la de la explosion*/
    
    public int getBorrar() {
        return borrar;
    }
    
    /**Devuelve la imagen del puente que sera pintada*/
    public Image getaparienciaPuente() {
        return aparienciaPuente;
    }
    
    /**devuelve la posicion en x del puente*/
    public int getPosX(){
        return ubicacionPuente.x;
    }
    
    /**pone el puente en su posicion al morir el jugador*/
    public void resetear(int set){ 
        ubicacionPuente.y = set;
    }
    /**retorna la posicion en y del puente*/
    public int getPosY(){
        return ubicacionPuente.y;
    }
    /**Cambia la imagen del punte por el de la explosion*/
    
    public void explotar(){
        try {
           aparienciaPuente = ImageIO.read(new File("src/Explosion/explo_puente.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
        ini=true;
    }
    
    /**retorna el objeto Rectangle para las colisiones*/
    
    public Rectangle getubicacionPuente() {
        return ubicacionPuente;
    }
    
    /**mueve el puente a la misma velocidad que se mueve el mapa*/
    
    public void AumentarPosicion(int set){
        ubicacionPuente.y+=set;
        if(ini)
        borrar++;
    }

    
    
    
}

