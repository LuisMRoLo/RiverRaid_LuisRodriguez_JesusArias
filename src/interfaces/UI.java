/** seudo interfaz de clase, para apoyar la ventana,
para mostrar en pantalla los puntajes, la vida y el combustible*/
package interfaces;

import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


import juego.*;


public class UI extends JPanel {
    private int cantVidas;
    private JProgressBar combustible;
    private JLabel vida,vida2,vida3;
    private JLabel puntaje;
    private int combustible2;
    private int contVida=3;
    private int puntos;
    ImageIcon iniciar;
    JLabel img;
    JLabel palabra = new JLabel("Combustible");
    /**constructor por defecto que inicializa varibles auxiliares y componentes y los agrega al panel
    como sus difeerentes imagenes, e interacciones visuales para que entienda el jugador el progreso
    */
    public UI(){
        puntos=0;
        combustible2=5000;
        setBackground(Color.GRAY);
        img = new JLabel();
        try {
            iniciar = new ImageIcon(ImageIO.read(new File("src/FondoMenuPause/fondoBarra.png")));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cantVidas = 3;
        this.combustible =  new JProgressBar();
        this.combustible.setMaximum(5000);
        this.combustible.setMinimum(0);
        this.palabra.setBounds(InterfacesVentana.width/2 -35,20,200,30);
        this.combustible.setBounds(InterfacesVentana.width/2 - 100,20,200,30);
        this.combustible.setValue(5000);
        this.vida = new JLabel(new ImageIcon("src/FondoMenuPause/vidas.png"));
        this.vida.setBounds(100, 30,20, 20);
        this.vida2 = new JLabel(new ImageIcon("src/FondoMenuPause/vidas.png"));
        this.vida2.setBounds(125, 30,20, 20);
        this.vida3 = new JLabel(new ImageIcon("src/FondoMenuPause/vidas.png"));
        this.vida3.setBounds(150, 30,20, 20);
        this.puntaje = new JLabel("Score : "+String.valueOf(puntos));
        this.puntaje.setBounds(800,30,200,30);
        puntaje.setEnabled(false);
        setLayout(null);
        
        img.setIcon(iniciar);
        img.setSize(1100, 100);
        img.setLocation(0,0);
        img.setFocusable(false);
        img.setVisible(true);
        
        add(palabra);
        add(combustible);
        add(vida);
        add(vida2);
        add(vida3);
        add(puntaje);   
        add(img);
        setVisible(true);
    }
    
    /**getter. retorna la cantidad de vidas diponible*/
    public int getCantVidas() {
        return cantVidas;
    }

    /**void. setea la cantidad de vidas a algun valor no max a 3 no menos a 0*/
    public void setCantVidas(int cantVidas) {
        this.cantVidas = cantVidas;
    }
    
    /**disminuye el combustible cada vez que es llamado (barra indicadora)*/
    public void disminuyeCombustible(){
        combustible2-=7;
        this.combustible.setValue(combustible2);
        if(combustible2 < 0){
         combustible2=0;   
        }
    }
    
    /**aumenta el combustible cada vez que se llama*/
    public void aumentaCombustible(){
        combustible2+=100;
        this.combustible.setValue(combustible2);
        if(combustible2 > 5000){
         combustible2=5000;   
        }
    }
    
    /**getter. devuelve la cantidad de combustible actual*/
    public int getCombustible(){
        return combustible.getValue();
    }
    
    /**void. setea la cantidad de combustible al maximo*/
    public void setCombustible(){
        combustible2 = 5000;
    }
   
    /**metodo que establece cambios del puntaje*/
    public void setPuntaje(int set){
        puntos +=set;
        if(puntos<=0)
        {
            puntos = 0;
        }
        puntaje.setText("Score : "+String.valueOf(puntos));
    }
    
    /**getetr. devuelve la cantidad de puntos actuales*/
    public int getPuntos(){
        return puntos;
    }
    
    /**retorna un Jlabel que representa la vida, para quitarla, si la nave la pierde*/
    public JLabel getVida()
    {
        cantVidas--;
        switch(cantVidas)
        {
            case 0 : return vida; 
            case 1 : return vida2;
            case 2 : return vida3;
        }
        return vida;
        
    }
   

}    
    
   
   
