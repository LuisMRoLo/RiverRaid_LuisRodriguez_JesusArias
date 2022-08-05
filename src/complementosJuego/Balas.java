/**
Estos son los complementos del juego, para el caso de las balas, 
en el que se van a ver las diferentes imagenes ejecutadas
saliendo de la nave y colosionando con los objetivos
(barcos, helicopteros, etc).
Hasta es posible colisionar el disparo con la gasolina.
*/

package complementosJuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Balas {
/** Declarando imagenes y rectangulos, para que se vea y para las colisiones,
    con sus respetvias dimensionesy efectos de disparo*/
	
    private Image aparienciaBalas;
    private Boolean disparoBala;
    private Rectangle ubicacion;
    private final int anchoBala=53, altoBala=128;
    
    
    /**el constructor (por defecto en este caso), 
    inicializa las variables para el objeto Rectangle y carga la imagen de "Balas"*/
    public Balas(){
        disparoBala=false;
        ubicacion= new Rectangle(0,0,anchoBala,altoBala);
        /**se induce a un try catch por si acaso no caga la imagen... solo para saber...*/
        try {
            aparienciaBalas = ImageIO.read(new File("disparo.png"));
        } catch (IOException ex) {
            System.out.println("error la imagen del Balas no se encuentra en la ruta por defecto");
        }
    }
    
    /**es el metodo getter que retorna la apariencia de las "Balas". 
    Sirve tambien para pintarlo en pantalla*/
    public Image getaparienciaBalas(){
        
        return aparienciaBalas;     
    }
    
    /**establece el efecto de disparo (por medio del booleano)*/
    public void setDisparo(Boolean i){
     this.disparoBala= i;   
    }
    
    /**se necesita para acceder al disparo (que es privado), activa un true*/
    public Boolean getDisparo(){
        
        return disparoBala;
    }
    /**metodo que establece la posicion en x (coordenada de pantalla) de las "Balas" del Rectangulo*/
    public void setDireccionX(int i){
        this.ubicacion.x=i;
    }
    /**metodo que nos devuelve la posicion en x (coordenada de pantalla) de las "Balas" del Rectangulo*/
    public  int getDireccionX(){
        return this.ubicacion.x;
    }
    
    /**metodo que setea la posicion en y de las "Balas"*/
     public void setProgresionY(int i){
        this.ubicacion.y=i;
    }
     /**metodo que retorna la posicion actual de Balas en y*/
     public int getProgresionY(){
        return this.ubicacion.y;
    }
     
     /**mueve y retorna la posicion del Balas en y
     coordenadas que se iran acomodando a las apariciones random*/
     public int mover()
     {   this.ubicacion.y-=20;
         return this.ubicacion.y;
     }
     
     /**hace retorno del objeto Rectangulo para las colisiones*/
     public Rectangle getUbicacion(){
         return ubicacion;
     }
    
}


