/** tda la musica y sonidos, para darle vitalidad al juego */
package interfaces;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonidos {
    private Clip SonidosMenu;
    private Clip SonidosJuego;
    private Clip SonidosDisparo;
    private Clip SonidosNave;
    private Clip Explo_barco;
    private Clip Explo_avioneta;
    private Clip SonidosFondoJuego;
    private Clip mouseMenu;
    private Clip motor;
    
    /** Constructor donde inicializa los Sonidos, con el uso de los metodos de Audio*/
    public Sonidos(){
        try {
            motor= AudioSystem.getClip();
            Explo_barco = AudioSystem.getClip();
            Explo_barco.open(AudioSystem.getAudioInputStream(new File("src/Sonidos/explo-barco.wav")));
            Explo_avioneta = AudioSystem.getClip();
            Explo_avioneta.open(AudioSystem.getAudioInputStream(new File("src/Sonidos/explo-avioneta.wav")));
            SonidosMenu = AudioSystem.getClip();
            SonidosDisparo = AudioSystem.getClip();
            SonidosDisparo.open(AudioSystem.getAudioInputStream(new File("src/Sonidos/flaunch.wav")));
            mouseMenu = AudioSystem.getClip();
            mouseMenu.open(AudioSystem.getAudioInputStream(new File("src/Sonidos/rollover1.wav")));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            System.out.println("error cargando el Sonidos");
        }
       
    }
    /**inicia los Sonidos de disparo*/
    public void disparo()
    {
        SonidosDisparo.setMicrosecondPosition(800);
        
        SonidosDisparo.start();
    }
    /** Sonidos de la explosion del barco*/
     public void explo_barco()
    {
        Explo_barco.setMicrosecondPosition(800);
        Explo_barco.start();
    }
     /** Sonidos de explosion de avioneta*/
      public void explo_avioneta()
    {
        Explo_avioneta.setMicrosecondPosition(800);
        Explo_avioneta.start();
    }
      /** Sonidos del fondo menu*/
    public void fondoMenu()
    {
        try {
            /**escogimos esta cancion, que es nuestra preferida debido al juego de Nintendo de Pokemon. Mucha ilusion esa cancion xd*/
            SonidosMenu.open(AudioSystem.getAudioInputStream(new File("Evergrande City Remix  Pokemon Ruby  Sapphire.wav")));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        SonidosMenu.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**Sonidos de motor como de nave*/
    public void mOtor()
    {
        try {
            motor.open(AudioSystem.getAudioInputStream(new File("src/Sonidos/plane.wav")));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        }
         motor.loop(Clip.LOOP_CONTINUOUSLY);
    }
    //diferentes timer para detener el motor (sonidos)
    public void detenermOtor()
    {
       
         motor.close();
    }
    /**detiene los Sonidos del fondo*/
    public void pararSonidosFondo()
    {
        SonidosMenu.close();
    }
    /**inicia Sonidos del boton*/
    public void Boton()
    {
        mouseMenu.setMicrosecondPosition(1000);
        mouseMenu.start();
    }
  
}

