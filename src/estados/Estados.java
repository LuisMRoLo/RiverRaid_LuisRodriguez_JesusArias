package estados;

import javax.swing.JPanel;


/**clase padre de todas las clases Estados*/
public abstract class Estados extends JPanel {
  
    
    public Estados(){}
    /** metodo que sera usado por las clases hijas para iniciar componentes*/
    public abstract void onEnter(); 
 
    
}
