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
        
        Wiadro wiadro1 = new Wiadro(1, false);
        Wiadro wiadro2 = new Wiadro(2, false);
        Wiadro wiadro3 = new Wiadro(3, false);
        Wiadro wiadro4 = new Wiadro(4, false);
        Wiadro wiadro5 = new Wiadro(5, false);

        Exchanger<Wiadro> e1 = new Exchanger<Wiadro>();
        Exchanger<Wiadro> e2 = new Exchanger<Wiadro>();
        Exchanger<Wiadro> e3 = new Exchanger<Wiadro>();
        Exchanger<Wiadro> e4 = new Exchanger<Wiadro>();

      //  s1 e1 s2 e2 s3 e3 s4 e4 s5 
        Strazak st1 = new Strazak("Strażak 1 ", null, e1, wiadro1);
        Strazak st2 = new Strazak("Strażak 2", e1, e2, wiadro2);
        Strazak st3 = new Strazak("Strażak 3", e2, e3, wiadro3);
        Strazak st4 = new Strazak("Strażak 4", e3, e4, wiadro4);
        Strazak st5 = new Strazak("Strażak 5", e4, null, wiadro5);

        new Thread(st1).start();
        new Thread(st2).start();
        new Thread(st3).start();
        new Thread(st4).start();
        new Thread(st5).start();
    }

}
