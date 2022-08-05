/** pantalla final por el fin del juego (al perder tres vidas) */
package interfaces;

import java.io.File;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import juego.*;

/**clase de tipo JPanel. contiene elementos que se muestran en pantalla - fin de juego - tres vidas*/


public class Final extends JPanel{
    
    private JButton continuar;
    JLabel img;
    ImageIcon continuarButton;
    /**construcor por defecto inicializa componentes y agrega.
    botones imagenes e interacciones para continuar partida (inicar uan de nuevo)*/
    public Final() {
        setLayout(null);
        setOpaque(false);
        try {
            continuarButton = new ImageIcon(ImageIO.read(new File("src/FondoMenuPause/Continuar.png")));
        } catch (IOException ex) {
            Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
        }
        img = new JLabel(new ImageIcon("src/FondoMenuPause/Menu1.png"));
        
        img.setBounds(0,0,450,200);
        continuar = new JButton();
        setBounds(InterfacesVentana.width / 2 - img.getWidth() / 2, 250,450, 190);
        continuar.setBounds(InterfacesVentana.width / 4 - ((img.getWidth() / 6) + 25),120,150,50);
        continuar.setIcon(continuarButton);
        continuar.setOpaque(false);
        add(continuar);
        add(img);
    }
    
    /**devuelve el boton de continuar para darle funcionalidad en EstadosDelJuego*/
    public JButton getContinuar(){
        return continuar;
    }
}
