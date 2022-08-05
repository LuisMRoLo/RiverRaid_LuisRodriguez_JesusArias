package interfaces;


import estados.*;
import juego.*;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**Clase de tipo JFrame que es ejecutado el EstadosDelJuego, el motivo de esto fue por un problema al momento de captar el focus en el Frame de menu*/
public class Inicio extends JFrame{
 
    private static Inicio instancia;
    
    /**constructor por defecto donde se pone un panel EstadosDelJuego sobre este frame para empezar el juego*/
    private Inicio()
    {
        super("River Raid");

         setSize(1100,700);
         try{
             Image icon =  new ImageIcon(getClass().getResource("Imagenes/pycharm.png")).getImage();
             setIconImage(icon);
         }
         catch(NullPointerException e){
             System.out.println("El icono de la ventana no se encuentra en su ruta por defecto");
         }
         setDefaultCloseOperation(Game.EXIT_ON_CLOSE);
         EstadosDelJuego e=new EstadosDelJuego();
         getContentPane().add(e,0);
         setResizable(false);
         setLocationRelativeTo(null);
         setVisible(true);
    }
    
    /**metodo estatico para usar solo una instancia de este frame*/
    public static Inicio instance()
    {
        if(instancia == null)
        {
            instancia = new Inicio();
        }
        return instancia;
    }
    
    /**metodo para eliminar este Frame*/
    public static void close_()
    {
        instance().dispose();
        instancia = null;
    }
}
