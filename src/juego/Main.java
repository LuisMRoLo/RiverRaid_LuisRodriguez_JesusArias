package juego;

import java.net.MalformedURLException;



public class Main {

   /** empieza todo a ejecutarse, se intancia primero en la clase Game */
    public static void main(String[] args) throws MalformedURLException {
       
        
        Game.instance().main.start();
    }
    
}
