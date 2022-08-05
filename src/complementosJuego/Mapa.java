/** muestra de la imagen de interaccion con todos los objetos,
el plano, la figura principal.
Tiene sus respectivas diferentes colosiones para al nave, etc
*/
package complementosJuego;


import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**clase que da vida al mapa y a su movimientoJugador en sincronia con la nave*/
public final class Mapa {

    private Image parteMapa;
    private ArrayList <Rectangle> colisionDerecha, colisionIzquierda, colisionMedio;
    private int segmentoActual, posicionY, movimientoJugador;

    /**constructor por defecto, carga la imagen del mapa. 
    Inicializa todos los rectangulos para las colisiones*/
    public Mapa() {
        try {
            parteMapa = ImageIO.read(new File("MapaFinalV3.png"));
        } catch (IOException ex) {
            System.out.println("xd");
        }
        
        posicionY =-29400;
        movimientoJugador =2;
        
        
        colisionMedio = new ArrayList<>();
        colisionDerecha = new ArrayList<>();
        colisionIzquierda = new ArrayList<>();
        
        
        
        
        /**Aquí se cargan todas las posibles posiciiones aen el medio*/
        Rectangle me;
              me = new Rectangle(361,-5450,378,1917);
              colisionMedio.add(me);
              me = new Rectangle(361,-8172,378,455);
              colisionMedio.add(me);
              me = new Rectangle(361,-7720,72,412);
              colisionMedio.add(me);
              me = new Rectangle(662,-7720,72,412);
              colisionMedio.add(me);
              me = new Rectangle(433,-14614,228,386);
              colisionMedio.add(me);
              me = new Rectangle(361,-21450,378,1917);
              colisionMedio.add(me);
              me = new Rectangle(361,-24175,378,455);
              colisionMedio.add(me);
              me = new Rectangle(361,-23720,72,412);
              colisionMedio.add(me);
              me = new Rectangle(661,-23720,72,412);
              colisionMedio.add(me);
        /**Aquí se cargan todas las posibles posiciiones a la derecha*/
         Rectangle der;
             der=new Rectangle(1100-360,-450,360,1050);
       /**1*/ colisionDerecha.add(der);
             der=new Rectangle(1100-130,-1440,130,990);
       /**2*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,-1855,360,415);
       /**3*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,-2485,134,622);
       /**4*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,-3030,360,545);
       /**5*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,-5830,134,2800);
       /**6*/ colisionDerecha.add(der);
             der=new Rectangle(1100-432,-6232,432,402);
       /**7*/ colisionDerecha.add(der);
             der=new Rectangle(1100-180,-7274,180,1042);
       /**8*/ colisionDerecha.add(der);
             der=new Rectangle(1100-90,-8479,90,1205);
       /**9*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,-8989,360,510);
       /**10*/colisionDerecha.add(der);
             der=new Rectangle(1100-459,-10615,459,1626);
       /**11*/colisionDerecha.add(der);
             der=new Rectangle(1100-147,-12163,147,1548);
       /**12*/colisionDerecha.add(der);
             der=new Rectangle(1100-435,-13902,435,1739);
       /**13*/colisionDerecha.add(der);
             der=new Rectangle(1100-267,-14880,267,978);
       /**14*/colisionDerecha.add(der);
            der=new Rectangle(1100-432,-15403,432,523);
       /**15*/colisionDerecha.add(der);
            der=new Rectangle(1100-360,-16453,360,1050);
       /**16*/colisionDerecha.add(der);
             der=new Rectangle(1100-130,-17443,130,990);
       /**17*/colisionDerecha.add(der);
             der=new Rectangle(1100-360,-17858,360,415);
       /**18*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,-18480,134,622);
       /**19*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,-19025,360,545);
       /**20*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,-21825,134,2800);
       /**21*/ colisionDerecha.add(der);
             der=new Rectangle(1100-432,-22227,432,402);
       /**22*/colisionDerecha.add(der);
             der=new Rectangle(1100-180,-23277,180,1042);
       /**23*/colisionDerecha.add(der);
             der=new Rectangle(1100-90,-24487,90,1210);
       /**24*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,-24997,360,510);
       /**25*/ colisionDerecha.add(der);
             der=new Rectangle(1100-459,-26623,459,1626);
       /**26*/ colisionDerecha.add(der);
             der=new Rectangle(1100-147,-28171,147,1548);
       /**27*/colisionDerecha.add(der);
             der=new Rectangle(1100-435,-29910,435,1739);
       /**28*/colisionDerecha.add(der);
    
     
       /**Aquí se cargan todas las posibles posiciiones a la izquierda*/
        Rectangle iz;
             iz=new Rectangle(0,-450,360,1050);
       /**1*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-1440,130,990);
       /**2*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-1855,360,415);
       /**3*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-2485,134,622);
       /**4*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-3030,360,545);
       /**5*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-5830,134,2800);
       /**6*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-6232,432,402);
       /**7*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-7274,180,1042);
       /**8*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-8479,90,1205);
       /**9*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-8989,360,510);
       /**10*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-10615,459,1626);
       /**11*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-12163,147,1548);
       /**12*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-13902,435,1739);
       /**13*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-14880,267,978);
       /**14*/ colisionIzquierda.add(iz);
            iz=new Rectangle(0,-15403,432,523);
       /**15*/ colisionIzquierda.add(iz);
            iz=new Rectangle(0,-16453,360,1050);
       /**16*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-17443,130,990);
       /**17*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-17858,360,415);
       /**18*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-18480,134,622);
       /**19*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-19025,360,545);
       /**20*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-21825,134,2800);
       /**21*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-22227,432,402);
       /**22*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400+6123,180,1042);
       /**23*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400+4913,90,1210);
       /**24*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400+4403,360,510);
       /**25*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400+2777,459,1626);
       /**26*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400+1229,147,1548);
       /**27*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,-29400-510,435,1739);
       /**28*/ colisionIzquierda.add(iz);
      
      
             
             
        
    }

    /**devuelve la imagen del mapa, se pintara en pantalla*/
    public Image getMapa() {
        return parteMapa;
    
    }
    
    //todos sus getters de sus posiciones diferentes, con sus colisiones
    public int getposicionY() {
        return posicionY;
    }
    public int getrecposicionY(int i) {
        return colisionMedio.get(i).y;
    }
    public int getPosX(int i) {
        return     colisionMedio.get(i).x ;
    }
     public int getancho(int i) {
        return     colisionMedio.get(i).width ;
    }
      public int getalto(int i) {
        return     colisionMedio.get(i).height ;
    }

    
    /**actualiza las posiciones de los rectangulos y del mapa. 
       Cuando el jugador pierde uan vida, va a posicion inicial (de forma correcta)*/
    public void actualizarPos(int posicionY){
        this.posicionY = posicionY;
        colisionIzquierda.clear();
        colisionDerecha.clear();
        colisionMedio.clear();
        
        Rectangle iz;
             iz=new Rectangle(0,posicionY+28950,360,1050);
       /**1*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+27960,130,990);
       /**2*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+27545,360,415);
       /**3*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+26915,134,622);
       /**4*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+26370,360,545);
       /**5*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+23570,134,2800);
       /**6*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+23168,432,402);
       /**7*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+22126,180,1042);
       /**8*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+20921,90,1205);
       /**9*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+20411,360,510);
       /**10*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+18785,459,1626);
       /**11*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+17237,147,1548);
       /**12*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+15498,435,1739);
       /**13*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+14520,267,978);
       /**14*/ colisionIzquierda.add(iz);
            iz=new Rectangle(0,posicionY+13997,432,523);
       /**15*/ colisionIzquierda.add(iz);
            iz=new Rectangle(0,posicionY+12947,360,1050);
       /**16*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+11957,130,990);
       /**17*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+11542,360,415);
       /**18*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+10920,134,622);
       /**19*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+10375,360,545);
       /**20*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+7575,134,2800);
       /**21*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+7173,432,402);
       /**22*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+6123,180,1042);
       /**23*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+4913,90,1210);
       /**24*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+4403,360,510);
       /**25*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+2777,459,1626);
       /**26*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY+1229,147,1548);
       /**27*/ colisionIzquierda.add(iz);
             iz=new Rectangle(0,posicionY-510,435,1739);
       /**28*/ colisionIzquierda.add(iz);
       
       Rectangle der;
             der=new Rectangle(1100-360,posicionY+28950,360,1050);
       /**1*/ colisionDerecha.add(der);
             der=new Rectangle(1100-130,posicionY+27960,130,990);
       /**2*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+27545,360,415);
       /**3*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,posicionY+26915,134,622);
       /**4*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+26370,360,545);
       /**5*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,posicionY+23570,134,2800);
       /**6*/ colisionDerecha.add(der);
             der=new Rectangle(1100-432,posicionY+23168,432,402);
       /**7*/ colisionDerecha.add(der);
             der=new Rectangle(1100-180,posicionY+22126,180,1042);
       /**8*/ colisionDerecha.add(der);
             der=new Rectangle(1100-90,posicionY+20921,90,1205);
       /**9*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+20411,360,510);
       /**10*/ colisionDerecha.add(der);
             der=new Rectangle(1100-459,posicionY+18785,459,1626);
       /**11*/ colisionDerecha.add(der);
             der=new Rectangle(1100-147,posicionY+17237,147,1548);
       /**12*/colisionDerecha.add(der);
             der=new Rectangle(1100-435,posicionY+15498,435,1739);
       /**13*/ colisionDerecha.add(der);
             der=new Rectangle(1100-267,posicionY+14520,267,978);
       /**14*/ colisionDerecha.add(der);
            der=new Rectangle(1100-432,posicionY+13997,432,523);
       /**15*/colisionDerecha.add(der);
            der=new Rectangle(1100-360,posicionY+12947,360,1050);
       /**16*/ colisionDerecha.add(der);
             der=new Rectangle(1100-130,posicionY+11957,130,990);
       /**17*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+11542,360,415);
       /**18*/colisionDerecha.add(der);
             der=new Rectangle(1100-134,posicionY+10920,134,622);
       /**19*/ colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+10375,360,545);
       /**20*/ colisionDerecha.add(der);
             der=new Rectangle(1100-134,posicionY+7575,134,2800);
       /**21*/colisionDerecha.add(der);
             der=new Rectangle(1100-432,posicionY+7173,432,402);
       /**22*/ colisionDerecha.add(der);
             der=new Rectangle(1100-180,posicionY+6123,180,1042);
       /**23*/colisionDerecha.add(der);
             der=new Rectangle(1100-90,posicionY+4913,90,1210);
       /**24*/colisionDerecha.add(der);
             der=new Rectangle(1100-360,posicionY+4403,360,510);
       /**25*/colisionDerecha.add(der);
             der=new Rectangle(1100-459,posicionY+2777,459,1626);
       /**26*/colisionDerecha.add(der);
             der=new Rectangle(1100-147,posicionY+1229,147,1548);
       /**27*/colisionDerecha.add(der);
             der=new Rectangle(1100-435,posicionY-510,435,1739);
       /**28*/ colisionDerecha.add(der);
      
       Rectangle me;
             me = new Rectangle(361,posicionY+23950,378,1917);
             colisionMedio.add(me);
             me = new Rectangle(361,posicionY+21228,378,455);
             colisionMedio.add(me);
             me = new Rectangle(361,posicionY+21680,72,412);
             colisionMedio.add(me);
             me = new Rectangle(662,posicionY+21680,72,412);
             colisionMedio.add(me);
             me = new Rectangle(433,posicionY+14686,228,386);
             colisionMedio.add(me);
             me = new Rectangle(361,posicionY+7950,378,1917);
             colisionMedio.add(me);
             me = new Rectangle(361,posicionY+5225,378,455);//o tal vez este
             colisionMedio.add(me);
             me = new Rectangle(361,posicionY+5680,72,412);//este esta mal
             colisionMedio.add(me);
             me = new Rectangle(661,posicionY+5680,72,412);
             colisionMedio.add(me);
    }
    
    /**metodo que mueve los rectangulos*/
    public void aumentarposicionY(int set) {
        this.posicionY += set;
        
        for(Rectangle rec : colisionDerecha)
        rec.y+=set;
        
        for(Rectangle rec : colisionIzquierda)
        rec.y+=set;
        
        for(Rectangle rec : colisionMedio)
        rec.y+=set;
    }
    
    /**hace mover el mapa*/
    public void setmovimientoJugador(int a){
        this.movimientoJugador = a;
    }
    
    /**metodo que retorna el movimientoJugador (mapa)*/
    public int getmovimientoJugador(){
       return this.movimientoJugador;      
    }
    
    /**un ArrayList con las colisiones de la derecha*/
    public ArrayList<Rectangle> getPosDerecha(){
        return colisionDerecha;
    }
    /**un ArrayList con las colisiones de la izquierda*/
    public ArrayList<Rectangle> getPosIzquierda(){
        return colisionIzquierda;
    }
    /**un ArrayList con las colisiones del medio*/
    public ArrayList<Rectangle> getPosMedia(){
        return colisionMedio;
    }
   
    
    

}
