/** atrae e inicializa todo lo posible del juego,
por medio de los objetos,
representa el uso de todas las clases individuales desarrolladas*/
package juego;


import enemigos.*;
import interfaces.*;
import estados.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



public class Game extends JFrame implements InterfacesVentana{
	
	
    private static Game instancia = null;
    
    JLabel fond,fond1;
    MenuInicial men;
    MejoresJugadores top;
    MenuUsuario menu;
    Instrucciones inst;
    Instrucciones inst2;
    Creditos Creditos;
    EstadosDelJuego nuevoJuego;
    Sonidos fondo;
    Icon icon1;
    Timer main,main2;
    int contador=0;
    
    /** inicializa un objeto de tipo Game*/
    
    public void initGame() throws MalformedURLException{
        Inicio.instance();  
    }
    /**Meotodo que genera menu de usuario*/
    public void nuevoJuego(){
        menu = new MenuUsuario(this);
        getContentPane().remove(men);
        getContentPane().remove(fond);
        super.getContentPane().add(menu);
        repaint();
    }
    /**Metodo que retorna al menu inicial*/
    public void VolvernuevoJuego(){
        men = new MenuInicial(this);
        getContentPane().remove(menu);
        super.getContentPane().add(men);
        repaint();
    }
    /**Metodo que genera menu de de top 10*/
    public void Top10(){
        top = new MejoresJugadores(this);
        getContentPane().remove(men);
        super.getContentPane().add(top);
        repaint();
    }
    /**Metodo que retorna al menu inicial*/
    public void VolverTop10(){
        men = new MenuInicial(this);
        getContentPane().remove(top);
        super.getContentPane().add(men);
        repaint();
    }
    /**Meotodo que genera menu de instrucciones*/
    public void Ayuda(){
        inst = new Instrucciones(this);
        getContentPane().remove(men);
        super.getContentPane().add(inst);
        repaint();
    }
    /**Metodo que retorna al menu inicial*/
    public void VolverIns(){
        men = new MenuInicial(this);
        getContentPane().remove(inst);
        super.getContentPane().add(men);
        repaint();
    }
    /**Meotodo que genera menu de instrucciones 2*/
    public void NextIns(){
        inst2 = new Instrucciones(this);
        getContentPane().remove(inst);
        super.getContentPane().add(inst2);
        repaint();
    }
    /**Metodo que retorna al menu instrucciones 1*/
    public void NextInsV(){
        inst = new Instrucciones(this);
        getContentPane().remove(inst2);
        super.getContentPane().add(inst);
        repaint();
    }
    /**Meotodo que genera menu de creditos*/
    public void Creditos(){
        Creditos = new Creditos(this);
        getContentPane().remove(men);
        super.getContentPane().add(Creditos);
        repaint();
    }
    /**Metodo que retorna al menu inicial*/
    public void VolverCreditos(){        
        men = new MenuInicial(this);
        getContentPane().remove(Creditos);
        super.getContentPane().add(men);
        repaint();
    }
    /**Metodo que tiene como funcion cerrar el Game*/
    public void Salir(){
        setVisible(false);
        dispose();
    }
    /**Metodo que carga el gif y lo visualiza, usando un timer para luego mostrar el menu inicial*/
    private Game() throws MalformedURLException{
        
         super("River Raid");
         try{
             icon1 = new ImageIcon((new File("src/inicio/carga.gif")).toURI().toURL());
         }
         catch(NullPointerException e){
             System.out.println("El icono de la InterfacesVentana no se encuentra en su ruta por defecto");
         }
         men = new MenuInicial(this);
         fond = new JLabel(icon1);
         fondo = new Sonidos();
         fondo.fondoMenu();
         fond.setLocation(0,0);
         fond.setSize(InterfacesVentana.width,InterfacesVentana.heigth);
         fond.setVisible(true);
         
         super.getContentPane().setBackground(new Color(0,0,0));
         super.setUndecorated(true);
         super.setSize(InterfacesVentana.width, InterfacesVentana.heigth);
         super.setLayout(null);
         super.setResizable(false);
         super.setLocationRelativeTo(null);
         super.getContentPane().add(fond);
         super.setVisible(true);
        main = new Timer(1000,new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(contador > 4){
                     getContentPane().remove(fond);
                     getContentPane().add(men);
                     repaint();
                     contador=0;
                     main.stop();
                 }
                 contador++;
             }
         });
         setDefaultCloseOperation(Game.EXIT_ON_CLOSE);
    }
    /**Permite crear una sola instancia de la clase Game*/
    public static Game instance() throws MalformedURLException{
        if(instancia == null){   
            instancia = new Game();
        }
        return instancia;
    }
    /**metodo que detiene el Sonidos del fondo*/
   public void paparSonidos()
   {
       fondo.pararSonidosFondo();
   }
   /**cierra el JFrame del menu*/
    public static void close_() throws MalformedURLException{
        instance().dispose();
        instancia = null;
    }
}
