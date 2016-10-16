
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanielAlejandro
 */
public class Inge implements Runnable{

    private Puente puente;
    private String name;

    public Inge(Puente puente, String name) {
        this.puente = puente;
        this.name = name;
    }
    
    @Override
    public void run() {
        // Establecer direccion aleatoria
        Random rnd = new Random();
        int n_rnd = rnd.nextInt(100);
        int dir = (n_rnd < 50)? 0 : 1;
        synchronized(puente){            
            int n = puente.cruzar(dir);
            System.out.println("Inge " + name + " cruzando hacia la " + ((dir == 0)? "derecha" : "izquierda"));
        }
        int sleep_time = rnd.nextInt(3) + 1;
        try {Thread.sleep(sleep_time * 1000);}
        catch (InterruptedException ex) {Logger.getLogger(Inge.class.getName()).log(Level.SEVERE, null, ex);}
        synchronized(puente){
            System.out.println("Inge " + name + " ha salido");
            puente.salir();            
        }
    }
    
}
