
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DanielAlejandro
 */
public class Runner {
    
    private Puente puente;

    public Runner() {
        // Pedir al usuario la cantidad de inges
        System.out.println("Ingrese la cantidad de Inges: ");
        Scanner in = new Scanner(System.in);
        String str_cant = in.nextLine();
        int cant = Integer.valueOf(str_cant);
        
        this.puente = new Puente();
        
        for(int i=0; i<cant; i++){
            Thread thread_inge = new Thread(new Inge(puente, String.valueOf(i)));
            thread_inge.start();
        }
        
        /*Thread thread_sum = new Thread(new Sumador(this.comms));
        Thread thread_res = new Thread(new Restador(this.comms));
        thread_sum.start();
        thread_res.start();*/
    }
    
}
