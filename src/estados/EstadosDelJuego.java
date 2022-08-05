package estados;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.Timer;

import enemigos.*;
import interfaces.*;
import complementosJuego.*;
import estados.*;
import juego.*;


/**Clase donde ocurre toda la interaccion del juego, desde los timer para los enemigos como las colisiones y Sonidos*/
public final class EstadosDelJuego extends Estados{

    /**private int FPS=0; opcional para setear los fps*/
    private ArrayList<Puentes>Puentes;
    private Puentes puenteUno, puenteDos;
    private Jugador Jugador;
    private Boolean moverIzquierda, moverDerecha;
    private EnemigoAvion EnemigoAvion; 
    private EnemigoBarco EnemigoBarco;
    private ArrayList<EnemigoBarco> barcosEnemigos; 
    private Timer desplazamiento, timerEnemigoUno, timerEnemigoDos,  timerGasolina;
    private KeyListener teclas; 
    private int aceleracion = 2;
    private UI barra;
    private Gasolina Gasolina;
    private ArrayList <Gasolina> combustible; 
    private Balas balas;
    private ArrayList<Balas> municiones; 
    private Boolean gasolina=false;
    private Pausa Pausa;
    private int tiempo;
    private int cont;
    private JLabel contadorTiempo;
    private Mapa mapa;
    private Final termianrJuego;
    private int reinicio=0;
    private BufferedWriter guardaScore;
    private BufferedReader solicitarRespaldo;
    private Sonidos Sonidos;
    
    /** contructor por defecto que inicializa las variables tipicas de un JPanel asi como variables auxiliares*/
    public EstadosDelJuego() {
        this.cont = 0;
        this.tiempo=0;
        this.onEnter();
        this.setBackground(Color.BLUE); 
        this.setLayout(null);
        this.moverDerecha= false;
        this.moverIzquierda=false;
        this.addKeyListener(teclas);
        setFocusable(true);
        setVisible(true);
       
        
    }
   
    /** metodo sobreescrito que inicializa todos los componentes de juego*/
    @Override
    public void onEnter() {
            Sonidos = new Sonidos();
            Sonidos.mOtor();
            contadorTiempo = new JLabel("0");
            contadorTiempo.setBounds(990, 0, 60,60);
            contadorTiempo.setVisible(true);
            Font cont = new Font("Agency FB",Font.BOLD,30);
            contadorTiempo.setFont(cont);
            contadorTiempo.setForeground(Color.white);
            puenteUno = new Puentes(433,-6097);
            puenteDos = new Puentes(430,-15136);
            Puentes = new ArrayList<>();
            Puentes.add(puenteDos);
            Puentes.add(puenteUno);
            
            mapa = new Mapa();
            Jugador= new Jugador();
            EnemigoAvion = new EnemigoAvion();
            barcosEnemigos = new ArrayList<>();
            
            barra = new UI();
            barra.setBounds(0,600,1100,100);
            
            termianrJuego = new Final();
            termianrJuego.getContinuar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Inicio.instance().close_();
                    try {
                        Game.instance().nuevoJuego();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(EstadosDelJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            Pausa = new Pausa();
            Pausa.setBounds(InterfacesVentana.width/2 -90, 200, 300, 400);
            Pausa.setVisible(false);
            Pausa.getReanudar().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    desplazamiento.restart();
                    timerEnemigoUno.restart();
                    timerEnemigoDos.restart();
                    Pausa.setVisible(false);
                }
            });
            
            Pausa.getSalir().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Sonidos.detenermOtor();
                    Inicio.instance().close_();
                    try {
                        Game.instance().nuevoJuego();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(EstadosDelJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            combustible = new ArrayList<>();
            municiones = new ArrayList<>();
            
            balas = new Balas();
            
            add(Pausa);
            add(barra);
            add(contadorTiempo);
            comenzar();
            GenerarEnemigo1();
            GenerarEnemigo2();
            GenerarTanke();
            
            teclas  = new KeyListener() {
                
                @Override
                public void keyTyped(KeyEvent e) {
                    
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                    
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        moverIzquierda=true;
                        
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        moverDerecha=true;
                        
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        aceleracion=2;
                        repaint();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        aceleracion= 15;
                        repaint();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    aceleracion = 5;
                    moverIzquierda = false;
                    moverDerecha = false;
                    
                    if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    {
                        balas = new Balas();
                        balas.setDisparo(true);
                        balas.setDireccionX(Jugador.getPosx()-3);
                        balas.setProgresionY(Jugador.getPosy());
                       Sonidos.disparo();
                    }
                    
                    if(e.getKeyCode() == KeyEvent.VK_P){
                        desplazamiento.stop();
                        timerEnemigoUno.stop();
                        timerEnemigoDos.stop();
                        Pausa.setVisible(true);
                    }
                }
            };  
    }
    
    /** metodo que inicializa el timer que controla todo el desarrollo del juego menos la aparicion de enemigos*/
    public void comenzar(){
        
        desplazamiento = new Timer(50, new ActionListener() {
  
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(Jugador.getvidas()==0 || tiempo==90){
                   Sonidos.detenermOtor();
                    timerEnemigoUno.stop();
                    timerEnemigoDos.stop();
                    desplazamiento.stop();
                    try {
                         solicitarRespaldo = new BufferedReader(new FileReader("respaldo.txt"));
                         guardaScore= new BufferedWriter(new FileWriter("Lista.txt",true));
                         guardaScore.write(solicitarRespaldo.readLine()+";"+String.valueOf(barra.getPuntos()));
                         guardaScore.newLine();
                         guardaScore.close();
                         solicitarRespaldo.close();
                 } catch (IOException ex) {
                        System.out.println("error no se pudo cargar el archivo Lista.txt");
                 }
                    add(termianrJuego);
                }
                
                cont++;
                if(cont>=20)
                {
                  tiempo++;
                  contadorTiempo.setText(String.valueOf(tiempo));
                  cont =0;
                }
                if(balas.getDisparo())
                {
                     municiones.add(balas);
                     balas.setDisparo(false);
                }
                
                mapa.aumentarposicionY(aceleracion);
                
                /**desplaza el Puentes*/
                for(Puentes p : Puentes)
                {
                    p.AumentarPosicion(aceleracion);/**Este proceso provoca la aceleracion al presionar la tecla superior*/
                }

                if(moverIzquierda){
                    Jugador.setPosx(-15); 
                }
                else if(moverDerecha){
                    Jugador.setPosx(15);
                }
                
                for(EnemigoBarco enemigo1 : barcosEnemigos) {
                    enemigo1.desplazar(aceleracion);
                }
                 for(EnemigoBarco enemigo1 : barcosEnemigos) {
                     if((540-enemigo1.getY())<=100)
                     enemigo1.atacar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());
                }
                for(Gasolina Gasolina1 : combustible) {
                    Gasolina1.desplazar(aceleracion);
                }
             
               if(gasolina)
               {
                   barra.aumentaCombustible();
               }
               else
               {
                   barra.disminuyeCombustible();
               }
                if(EnemigoAvion.getEnemigo()==1)
                  EnemigoAvion.mover(-20,aceleracion);   
                else if(EnemigoAvion.getEnemigo()==2)
                 EnemigoAvion.mover(20,aceleracion);
                repaint();
            }
        });
        desplazamiento.start();
    }
    
    /** metodo que genera el enemigo "EnemigoAvion" de manera aleatoria*/
    public void GenerarEnemigo1(){
        timerEnemigoUno = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                EnemigoAvion.generar();
               
            }
        });
        timerEnemigoUno.start();
    }
    
    /** metodo que genera el enemigo "EnemigoBarco" de manera aleatoria*/
    public void GenerarEnemigo2(){
        
        timerEnemigoDos = new Timer(900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnemigoBarco = new EnemigoBarco();
                EnemigoBarco.generar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());
                barcosEnemigos.add(EnemigoBarco);
                
                
            }
        });
        timerEnemigoDos.start();
    }
    
    /** metodo que genera los Gasolinas de combustible de manera aletoria*/
    public void GenerarTanke(){
        
        timerGasolina = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Gasolina = new Gasolina();
                Gasolina.generar(mapa.getPosIzquierda(),mapa.getPosDerecha(),mapa.getPosMedia());       
                combustible.add(Gasolina);
            }
        });
        timerGasolina.start();
    }
    
    /**metodo sobreescrito que permite repintar el JPanel cada cierto tiempo dando la persepcion de que las cosas se mueven, valida todas las colicciones al ser llamado por el metodo Comenzar*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
           
        
            /**Esto dibuja todo el mapa donde se desarrolla el juego*/
            g.drawImage(mapa.getMapa(),0,mapa.getposicionY(),this);
        
            
            /**Esto mueve dibuja los puentes en sus posicione sy los meuve cuando ahce falta*/
            for(Puentes p : Puentes)
             {
                   g.drawImage(p.getaparienciaPuente(),p.getPosX(),p.getPosY(),this);
             }
            
     
        /**Dibuja los tanques de Gasolina, si el jugador los toca regenera su tanque y podra seguir jugando*/
        for(Gasolina comb : combustible) {
            g.drawImage(comb.getImagen(), comb.getPosx(), comb.getPosy(), this);
        }
        
        /**Esto dibuja a los enemigos Barcos*/
        for(int i =0;i<barcosEnemigos.size();i++) {
            g.drawImage(barcosEnemigos.get(i).getLook(), barcosEnemigos.get(i).getX(),barcosEnemigos.get(i).getY(), this);
            
            
           if(barcosEnemigos.get(i).getY()>750)
        	   barcosEnemigos.remove(i);
        }
        
        
        
        /**Dibuja las balas cuando el jugador las lanza*/
        for(int i =0;i<municiones.size() ;i++) {
            g.drawImage(municiones.get(i).getaparienciaBalas(),municiones.get(i).getDireccionX(),municiones.get(i).mover(),this);
            if(municiones.get(i).getProgresionY()<-20)
                municiones.remove(i);
        }
        
        /**Dibuja a los enemigos aviones*/
        if(EnemigoAvion.getEnemigo()!=0)
        {
            g.drawImage(EnemigoAvion.getLook(),EnemigoAvion.getX(),EnemigoAvion.getY(), this);
        }
        
        /**Esto dibuja al jugador*/
        g.drawImage(Jugador.getImagen(),Jugador.getPosx(),Jugador.getPosy(), this); 
       

        /**Este proceso valida el impacto con los enemigos Aviones, si el jugador lso toca perdera una vida*/
        if(Jugador.getubicacionJugador().intersects(EnemigoAvion.getUbicacion()))
        {
                barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)
                {
                    EnemigoAvion.removerPorColision();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)
                {
                    EnemigoAvion.removerPorColision();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
        }
        
        /**Este es un proceso para validar cuando se agota la gasolina del Jugador, si esto sucede el jguador perdera una vida.*/
        if(barra.getCombustible()==0){
            barra.setCombustible();
            Jugador.perdervidas();
            barra.getVida().setVisible(false);
                if(reinicio==0)
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    
                    Jugador.reiniciar();
                    combustible.clear();
                   
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
        }
        /**Este es un proceso que valida el impacto con los barcos enemigos. SI el jugador los toca perdera una vida*/
       for(EnemigoBarco enemigo1 : barcosEnemigos) {
            if(Jugador.getubicacionJugador().intersects(enemigo1.getUbicacion()))
            {
                barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)/**Esto quiere decir que no se ha destruido ningun puente y por lo tanto no hay punto de control*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)/**Esto quiere decir que se ha destruido un puente y por lo anto hay un punto de control. Al haber un punto de control esto quiere decir que al perder una vida se iniciará desde aqui y no desde el principio.*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
                 break;
            }
        }
       for(Puentes puenteUno : Puentes) {
            if(Jugador.getubicacionJugador().intersects(puenteUno.getubicacionPuente()))
            {
                barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)/**Esto quiere decir que no se ha destruido ningun puente y por lo tanto no hay punto de control*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)/**Esto quiere decir que se ha destruido un puente y por lo anto hay un punto de control. Al haber un punto de control esto quiere decir que al perder una vida se iniciará desde aqui y no desde el principio.*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
                 break;
            }
        }
       
       /**Este proceso valida si el jugador toca un tanque de gasolina para que su nivel de combustible se rellene*/
       for(Gasolina Gasolina1 : combustible) {
            if(Jugador.getubicacionJugador().intersects(Gasolina1.getubicacionGasolina()))
            {
                
                gasolina=true;
                break;
            }
            else
                gasolina=false;
            
        }
       
        /**Este es un proceso que valida el impacto con el terreno.  Si el jugador choca con el terreno pierde una vida. Aquí empeiza el proceso*/
       for (int i =0;i<mapa.getPosDerecha().size();i++){
                
             if (Jugador.getubicacionJugador().intersects(mapa.getPosDerecha().get(i))){
                 
                barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)/**Esto quiere decir que no se ha destruido ningun puente y por lo tanto no hay punto de control*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)/**Esto quiere decir que se ha destruido un puente y por lo anto hay un punto de control. Al haber un punto de control esto quiere decir que al perder una vida se iniciará desde aqui y no desde el principio.*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       for (int i =0;i<mapa.getPosIzquierda().size();i++){
                
             if (Jugador.getubicacionJugador().intersects(mapa.getPosIzquierda().get(i))){
                 barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)/**Esto quiere decir que no se ha destruido ningun puente y por lo tanto no hay punto de control*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)/**Esto quiere decir que se ha destruido un puente y por lo anto hay un punto de control. Al haber un punto de control esto quiere decir que al perder una vida se iniciará desde aqui y no desde el principio.*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       for (int i =0;i<mapa.getPosMedia().size();i++){
                
             if (Jugador.getubicacionJugador().intersects(mapa.getPosMedia().get(i))){
                    barra.setCombustible();
                Jugador.perdervidas();
                barra.getVida().setVisible(false);
                if(reinicio==0)/**Esto quiere decir que no se ha destruido ningun puente y por lo tanto no hay punto de control*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-29400);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-6097);
                    Puentes.get(1).resetear(-15136);
                }
                
               if(reinicio == 1)/**Esto quiere decir que se ha destruido un puente y por lo anto hay un punto de control. Al haber un punto de control esto quiere decir que al perder una vida se iniciará desde aqui y no desde el principio.*/
                {
                    EnemigoAvion.removeEnemigo();
                    barcosEnemigos.clear();
                    mapa.actualizarPos(-22940);
                    Jugador.reiniciar();
                    combustible.clear();
                    Puentes.get(0).resetear(-15136);
                }
                 break;
                }
            }
       /**Aqui termina el proceso que provoca el impacto con el terreno.*/
       
       /**Este es un proceso para poder hacer que los barcos enemigos desaparezcan.*/
       for(int i=0;i<municiones.size();i++) {
           for(int j=0;j<barcosEnemigos.size();j++){
                if(municiones.get(i).getUbicacion().intersects(barcosEnemigos.get(j).getUbicacion()))
                {
                     Sonidos.explo_barco();
                    barra.setPuntaje(100);
                    municiones.remove(i);
                    barcosEnemigos.get(j).explotar();
                    break;
                }
           }
        }
       /**Esto elimina y borra a los enemigos con tiempo*/
       for(int j=0;j<barcosEnemigos.size();j++)
       {
           if(barcosEnemigos.get(j).getBorrar()>3)
        	   barcosEnemigos.remove(j);
       }
       
       /**Este proceso es para eliminar los puentes*/
       for(int i=0;i<municiones.size();i++) {
           for(int j=0;j<Puentes.size();j++){
                if(municiones.get(i).getUbicacion().intersects(Puentes.get(j).getubicacionPuente()))
                {
                    Sonidos.explo_barco();
                    barra.setPuntaje(200);/**Al destruir un puente el jugador 200 puntos.*/
                    municiones.clear();
                    Puentes.get(j).explotar();
                    barcosEnemigos.clear();
                    combustible.clear();
                    reinicio++;
                    break;
                }
           }}
       for(int j=0;j<Puentes.size();j++)
       {
           if(Puentes.get(j).getBorrar()>3)
           {
               Puentes.remove(j);
           }
       }
               
       
       /**Este es un proceso para eliminar los tanques de Gasolina*/
       for(int i=0;i<municiones.size();i++) {
           for(int j=0;j<combustible.size();j++){
                if(municiones.get(i).getUbicacion().intersects(combustible.get(j).getubicacionGasolina()))
                {
                    barra.setPuntaje(-100);
                    municiones.remove(i);
                    combustible.get(j).explotar();
                    break;
                }
           }}
       for(int j=0;j<combustible.size();j++)
       {
           if(combustible.get(j).getBorrar()>3)
               combustible.remove(j);
       }
       
       /**Este es un proceso que elimina y borra a los aviones Enemigos*/
       for(int i=0;i<municiones.size();i++) {
             if(municiones.get(i).getUbicacion().intersects(EnemigoAvion.getUbicacion()))
                {
                     Sonidos.explo_barco();
                    barra.setPuntaje(150);
                    municiones.remove(i);
                    EnemigoAvion.explotar();
                }
           }
       
           if(EnemigoAvion.getBorrar()>3)
               EnemigoAvion.removeEnemigo();
       
       
       
    }
  
}
