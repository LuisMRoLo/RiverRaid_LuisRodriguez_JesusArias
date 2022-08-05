/**
imagen de "FUEL" que se deja interactuar,
 
para ser tomada y aumentar la barra de combustible
*/
package complementosJuego;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;


/**Clase responsable de cargar el tanque de combustible
sirve para al calse donde se interaccionan las colisiones, enemigos, sonido, etc en EstadosDelJuego*/
public class Gasolina {
    /**ciertos objetos necesarios para que la imagen interactue con el juego*/
    
    private Rectangle ubicacionGasolina;
    private final int anchoGasolina=36, altoGasolina=63;
    private Image aparienciaGasolina;
    private Boolean ini=false;
    private int borrar;
    
    /**constructor por defecto que se encarga de inicializar el objeto ubicacionGasolina (Rectangulo), 
    para colisionar y que se cargue la imagen del tanque de combustible "FUEL"*/
    public Gasolina(){
        /**necesario el borrar para producir la colision y desaparicion de la imagen*/
        borrar=0;
        ini = false;
        /**rectangulo ubicacionGasolina se acopla a las coordenadas x, e, y*/
        ubicacionGasolina = new Rectangle(0,-150,anchoGasolina,altoGasolina);
        /**try catch por si acaso la imagen no corre*/
        try {
            aparienciaGasolina= ImageIO.read(new File("Combustible.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
    }
    /**metodo que retorna obtejo ubicacionGasolina (rectangulo) para las colisiones (privado)*/
    public Rectangle getubicacionGasolina(){
        return this.ubicacionGasolina;
    }
   /**metodo que devuelve la posicion de los combustibles en x*/
    public int getPosx() {
        return ubicacionGasolina.x;
    }
    /**metodo que devuelve la posicion de los combustibles en y*/
    public int getPosy() {
        return ubicacionGasolina.y;
    }
    
    /**metodo que obtiene la imagen del combustible*/
    public Image getImagen() {
        return aparienciaGasolina;
    }
    /**el metodo cambia la imagen del combustible por la de la explosion BOOM!
    logra surgir por medio de los booleanos e interacciones con los rectangulos*/
    public void explotar(){
        try {
           aparienciaGasolina = ImageIO.read(new File("src/Explosion/Explosion.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
        ini=true;
    }
     /**metodo que actualiza la posicion de los combustibles en y, para simular que se mueven con el mapa*/
    public void desplazar(int d){
        this.ubicacionGasolina.y+=d;
        if(ini)
        borrar++;
    }
    /**metodo que retorna un int que al tener cierto valor (diferente de cero), 
    permite que el sprite del combustible cambie por el de la explosion*/
    public int getBorrar(){
        return borrar;
    }
    
    /** metodo que intenta generar aleatoriamente los combustibles dentro del area azul (mar) y no del area verde (tierra),
    aun presenta errores porque los numeros son random y ocasionalmente fallan con la posicion*/
    public void generar(ArrayList<Rectangle> izquierdo, ArrayList<Rectangle> derecho,ArrayList<Rectangle> medio){

         Random a = new Random();
        ubicacionGasolina.x=a.nextInt(900)+100;
        if(ubicacionGasolina.x<550){
            for(Rectangle rec :izquierdo){
                
            if(ubicacionGasolina.intersects(rec))
            {  
             ubicacionGasolina.x = rec.width;
             break;
            }
            }
            for(Rectangle rec :medio){
                
            if(ubicacionGasolina.intersects(rec))
            {  
                 int aux = ubicacionGasolina.x-rec.x;
                 ubicacionGasolina.x = ubicacionGasolina.x-ubicacionGasolina.width-aux; 
                 break;
            }
            
            }
        }
        else
        {
            for(Rectangle rec :derecho){
                
                if(ubicacionGasolina.intersects(rec))
                 {  
                 int aux = ubicacionGasolina.x-rec.x;
                 ubicacionGasolina.x = ubicacionGasolina.x-ubicacionGasolina.width-aux; 
                 break;
                 }
            }
            
            for(Rectangle rec :medio){
                
                 if(ubicacionGasolina.intersects(rec))
                 {  
                 ubicacionGasolina.x = rec.width+ rec.x;
                 break;
                 }
            
            }

        }
  
    } 
    
}

