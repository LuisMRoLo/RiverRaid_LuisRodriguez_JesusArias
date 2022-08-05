package enemigos;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/** clase del abrco enemigo, colisiones, posiciones alatorias e imagenes estan en accion... con su borrado... */
public class EnemigoBarco {
    private Image look;
    private Rectangle ubicacion;
    private final int ancho=73;
    private final int alto=28;
    private Point coord;
    private int enemigo;
    private int borrar;
    private Boolean ini;
    private int mover=5;
    private int direccion=0;
    
    /**constructor (defecto). carga la imagen necesaria e inicializa variables auxiliares y su rectangulo (y ubicacion)*/
     public EnemigoBarco(){
        ini = false;
        borrar=0;
        enemigo=0;
        ubicacion = new Rectangle(0,-28,ancho, alto);
        
        try {
           look = ImageIO.read(new File("src/Barco/Barco.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
    }
    
     /**getter. devuelve la imagen que representa el barco enemigo*/
     public Image getLook(){
         return look;
    }
     
     /**getter. retorna la posicion del EnemigoBarco en x*/
    public int getX(){
      return ubicacion.x;  
    }
    
    /**}setter. posicion del EnemigoBarco en y*/
    public void setY(int y){
        ubicacion.y = y;
    }
    
    /**getter. devuelve la posicion del EnemigoBarco en y*/
    public int getY(){
      return ubicacion.y;  
    }
    
    /**getter. ancho del EnemigoBarco*/
    public int getAncho(){
      return ubicacion.width;  
    }
    
    /**getter. alto del EnemigoBarco*/
    public int getAlto(){
      return ubicacion.height;  
    } 
    
    /**setter void. posicion de los EnemigoBarcos en y simulan que se mueven con el mapa*/
    public void desplazar(int d){
        this.ubicacion.y+=d;
        if(ini)
        borrar++;
    }
    
    /**metodo void. cambia la imagen del EnemigoBarco por la de la explosion*/
    public void explotar(){
        try {
           look = ImageIO.read(new File("src/Explosion/Explosion.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen de la nave no se encuentra en la ruta por defecto");
        }
        ini=true;
    }
    
    /**metodo que devuelve un int, tiene un valor definido, 
    permite que el sprite de el EnemigoBarco cambie por el de la explosion*/
    public int getBorrar(){
        return borrar;
    }
    
    
    /** metodo de barco enemigo, esta dentro del rango correct de aparecer, no en la tierra, pero a veces falla (deberia solo en el mar) */
    public void generar(ArrayList<Rectangle> izquierdo, ArrayList<Rectangle> derecho,ArrayList<Rectangle> medio){
        
        Random a = new Random();
        ubicacion.x=a.nextInt(900)+100;
        if(ubicacion.x<550){
            direccion=0;
            for(Rectangle rec :izquierdo){
            
            if(ubicacion.intersects(rec) )
            { 
             
             ubicacion.x = rec.width;
            }
            break;
            }
            for(Rectangle rec :medio){
              
            if(ubicacion.intersects(rec))
            {  
                 direccion=1; 
                 int aux = ubicacion.x-rec.x;
                 ubicacion.x = ubicacion.x-ubicacion.width-aux; 
                  break;
            }
            
            }
        
            
        }
        else
        {   
            direccion=3; 
            for(Rectangle rec :derecho){
                
                if(ubicacion.intersects(rec) )
                 { 
                 int aux = ubicacion.x-rec.x;
                 ubicacion.x = ubicacion.x-ubicacion.width-aux; 
                 break;
                 }
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {    direccion=4;
                 ubicacion.x = rec.width;
                 break;
            }
            
            }
            

        }
    } 
    
    /**metodo que se encarga de mover el EnemigoBarco hacia a izquierda o la derecha (dependiendo de donde se encuentre) cuando se encuentra a una distancia del jugador*/
    public void atacar(ArrayList<Rectangle> izquierdo, ArrayList<Rectangle> derecho,ArrayList<Rectangle> medio){ // si es 0 corre a la derecha si es 1 correhac
        
        if(direccion==0 || direccion==4){
            ubicacion.x+=mover;
            for(Rectangle rec :derecho){
                
            if(ubicacion.intersects(rec) )
            { 
             mover=0;
             break;
            }
            
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {  
                
                 mover=0; 
                  break;
            }
            
            }
        
            
        }
        else
        {
            ubicacion.x-=mover; 
            
            for(Rectangle rec :izquierdo){
                
                if(ubicacion.intersects(rec) )
                 {  
                 mover=0;
                 break;
                 }
            }
            for(Rectangle rec :medio){
                
            if(ubicacion.intersects(rec))
            {  
                 mover=0;
                 break;
            }
            
            }
            

        }
        
    }
    
    /**metodo que retorna un objeto Rectangle para determinar las colisiones
    de sus respectivas ubicaciones para als interacciones dle jugador (nave),
    para que funcionen las posiciones y colisiones
    */
    public Rectangle getUbicacion(){
        return this.ubicacion;
    }
}
