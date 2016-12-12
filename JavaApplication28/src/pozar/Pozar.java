/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozar;

import java.util.concurrent.Exchanger;

/**
 *
 * @author Dominika
 */
public class Pozar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Pożar");
        
         

        Wiadro wiadro1 = new Wiadro(0, false);
        Wiadro wiadro2 = new Wiadro(1, false);
        Wiadro wiadro = new Wiadro(2, true);

        Exchanger<Wiadro> e1 = new Exchanger<Wiadro>();
        Exchanger<Wiadro> e2 = new Exchanger<Wiadro>();

        Strazak st1 = new Strazak(0, null, e1, wiadro1);
        Strazak st2 = new Strazak(1,   e1, e2, wiadro2);
        Strazak st3 = new Strazak(2, e2, null, wiadro);

        new Thread(st1).start();
        new Thread(st2).start();
        new Thread(st3).start();
    }
    
}
