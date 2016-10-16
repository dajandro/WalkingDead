
import java.util.Random;
import java.util.Stack;
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
public class Puente {
    
    private int n_cruzando;
    private int dir; // -1 = nadie, 0 = derecha, 1 = izquierda
    private Stack<Thread> derecha = new Stack<>();
    private Stack<Thread> izquierda = new Stack<>();

    public Puente() {
        this.n_cruzando = 0;
        this.dir = 0;
    }

    public int getN_cruzando() {
        return n_cruzando;
    }

    public void setN_cruzando(int n_cruzando) {
        this.n_cruzando = n_cruzando;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Stack<Thread> getDerecha() {
        return derecha;
    }

    public void setDerecha(Stack<Thread> derecha) {
        this.derecha = derecha;
    }

    public Stack<Thread> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Stack<Thread> izquierda) {
        this.izquierda = izquierda;
    }

    @Override
    public String toString() {
        return "Estan cruzando: " + n_cruzando + ". En direccion " + (dir == 0 ? "derecha" : "izquierda");
    }
    
    public void addWaitInge(int d){
        if (d==0) {if(!derecha.contains(Thread.currentThread()))derecha.push(Thread.currentThread());}
        else if (d==1) {if(!izquierda.contains(Thread.currentThread())) izquierda.push(Thread.currentThread());};
    }
    
    public void notifyInge(){
        //System.out.println("Puente dir: " + dir);
        if (dir==0){
            if (!derecha.isEmpty()) derecha.pop().notify();
            else 
                if(n_cruzando <= 0)
                    if(!izquierda.isEmpty()) izquierda.pop().notify();
        }
        else if (dir==1){
            if (!izquierda.isEmpty()) izquierda.pop().notify();
            else
                if(n_cruzando <= 0)
                    if(!derecha.isEmpty()) derecha.pop().notify();
        }
        else if (dir==-1) notify();
    }
    
    public void salir(){
        // Descontar contador
        n_cruzando--;
        if (n_cruzando <= 0) dir=-1;
        notifyInge();
    }
    
    public int cruzar(int d){
        // Espera si no esta en la direccion actual
        while (dir != -1 && d != dir) try {
            addWaitInge(d);            
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dir = d;
        // Espera si ya hay 4 cruzando
        while (n_cruzando >= 4) try {
            addWaitInge(d);
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Puente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.n_cruzando++;
        return 1;        
    }
    
}