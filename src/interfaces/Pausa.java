package interfaces;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**Clase de tipo JPanel. muestra en pantalla, elementos de menu de Pausa*/
public class Pausa extends JPanel{
    private JButton reanudar;
    private JButton salir;
    private JLabel imgFondo;
    /**constructor por defecto. inicializa y agrega los componenetes
    botones y sus interacciones para continuar o salir...
    mas la imagen...*/
    public Pausa(){
        setLayout(null);
        setOpaque(false);
        reanudar = new JButton("Reanudar");
        salir = new JButton("Salir");
        imgFondo = new JLabel(new ImageIcon("src/FondoMenuPause/Menu.png"));
        imgFondo.setBounds(0, 0, 200, 200); 
        reanudar.setBounds(50,45,100,20);
        salir.setBounds(50,120,100,20);

        add(reanudar);
        add(salir);
                add(imgFondo);

    }
    
    /**getter. retorna el boton reanudar para darle funcionalidad en estadosdeljuego*/
    public JButton getReanudar(){
        return reanudar;
    }
    /**getter. devuelve el boton salir para darle funcionalidad en estadosdeljuego*/
    public JButton getSalir(){
        return salir;
    }
}

