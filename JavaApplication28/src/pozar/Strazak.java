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

class Strazak implements Runnable {

    String name;
    Exchanger<Wiadro> el, er;
    Wiadro w;

    
   
    

    public Strazak(String name, Exchanger<Wiadro> el, Exchanger<Wiadro> er, Wiadro w) {
        this.name = name;
        this.el = el;
        this.er = er;
        this.w = w;
    }

    
    @Override
    public void run() {
        try {
            while (true) {
                if (el == null) { 
                    if (w.jestPelne()) {
                        System.out.println(name + " przekazuje wiadro " + w.status() + " >>>>");
                        w = er.exchange(w);
                        System.out.println(name + " odebrał od prawego wiadro " + w.status());
                    } else {
                        System.out.println(name + " NAPEŁNIA wiadro " + w.status() );
                        w.setNapelnanie(true);
                    }
                } else if (er == null) { 
                    if (!w.jestPelne()) {
                         System.out.println(name + " przekazuje wiadro " + w.status() + " <<<<");
                        w = el.exchange(w);
                        System.out.println(name + "odebrał od lewego wiadro " + w.status());
                    } else {
                         System.out.println(name + " WYLEWA wiadro " + w.status() );
                        w.setNapelnanie(false);
                    }
                } else { 
                    if (w.jestPelne()) {
                        System.out.println(name + " przekazuje wiadro " + w.status() + " >>>>");
                        w = er.exchange(w);
                         System.out.println(name + " odebrał od prawego wiadro " + w.status());
                    } else {
                        System.out.println(name + " przekazuje wiadro " + w.status() + " <<<<");
                        w = el.exchange(w);
                        System.out.println(name + " odebrał od lewego wiadro " + w.status());
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

