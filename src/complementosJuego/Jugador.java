/**
El jugador es la nave, que se mueve a diferentes velocidades y a su vez de izquierda a derecha.

Evade a los enemigos y agarra los nivelCombustibles.
Para hacerlo, colosiona por medio de los metodos Rectangle
*/

package complementosJuego;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**Clase que contiene todas las varibles para controlar al Jugador, la nave, el usuario lo usa para interactuar con el juego*/
public class Jugador {
    /**se tienen de nuevo los rectangulos e imagenes para la interaccion*/
    /**se tienen vidass por parte del jugador, para terminar el jeugo en caso de perder 3*/
    private ArrayList<Image>apariencia;
    private Rectangle ubicacionJugador;
    private final int anchoJugador=50, altoJugador=50;
    private int nivelCombustible, vidas = 3;
    
    /**Este es el constructor por defecto y este sirve para cargar la imagen del jugador*/
    /**Este constructor tambien inicializa el objeto ubicacionJugador, el cual es un rectangulo, y esto genera las colisiones*/
    public Jugador() {
        /**un buen rango de barra de nivelCombustible, da tiempo para poder tomar los demas nivelCombustibles aleatorios del mapa*/
        this.nivelCombustible = 5000;
        ubicacionJugador=new Rectangle(550,540,anchoJugador,altoJugador);
        apariencia = new ArrayList<>();
        /**try cath para evaluar la ruta de la imagen y dibujarla*/
        try {
            apariencia.add(0,ImageIO.read(new File("JugadorNave.png")));
        } catch (IOException ex) {
            System.out.println("error la imagen de la Jugador no se encuentra en la ruta por defecto");
        }
           
    }
    
    /**metodo que devuelve la posicion en x del jugador(por se privado)*/
    public int getPosx() {
        return ubicacionJugador.x;
    }
    
    /**metodo que devuelve la posicion en y del jugador (por ser privado)*/
    public int getPosy() {
        return ubicacionJugador.y;
    }
    
    /**metodo que devuelve el nivelCombustible del jugador
    para su modificacion en la barra mostrada en pantalla*/
    public int getnivelCombustible(){
        return nivelCombustible;
    }
    
    /**metodo que establece la posicion en x del jugador*/
    public void setPosx(int posx) {
        ubicacionJugador.x += posx;
    }
    
    /**reinicia la ubicacionJugador inicial del Jugador*/
    public void reiniciar(){
        ubicacionJugador.x=510;
    }
    
    /**metodo que devuelve la imagen del Jugador, la nave, lo visual*/
    public Image getImagen() {
        return apariencia.get(0);
    }
    
    /**metodo que cambia el sprite del Jugador cuando este da un giro*/
    public void setImagen(int index,Image imagen) {
        this.apariencia.set(index, imagen);
    }
    
    /**metodo que retorna el objeto Rectangle para las colisiones con su respectiva ubicacionJugador*/
    public Rectangle getubicacionJugador(){
        
        return this.ubicacionJugador;
    }
    
    /**metodo que disminuye la vidas (corazon) en uno*/
    public void perdervidas(){
        vidas--;
    }
    
    /**metodo que devuelve la vidas de la nave actual*/
    public int getvidas(){
        return vidas;
    }
    
    
    
    }

