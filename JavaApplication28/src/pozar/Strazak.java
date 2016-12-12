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
                        podajeWPrawo();
                        w = er.exchange(w);
                        odbieraOdPrawego();
                    } else {
                        napelnianieWiadra();
                        w.setNapelnanie(true);
                    }
                } else if (er == null) { 
                    if (!w.jestPelne()) {
                        podajeWLewo();
                        w = el.exchange(w);
                        odebrałOdLewego();
                    } else {
                        pusteWiadro();
                        w.setNapelnanie(false);
                    }
                } else { 
                    if (w.jestPelne()) {
                        podajeWPrawo();
                        w = er.exchange(w);
                        odbieraOdPrawego();
                    } else {
                        podajeWLewo();
                        w = el.exchange(w);
                        odebrałOdLewego();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void podajeWPrawo() {
        System.out.println(name + " przekazuje wiadro " + w.status() + " w prawo.");
        sleep();
    }

    private void odbieraOdPrawego() {
        System.out.println(name + " odebrał od prawego wiadro " + w.status());
        sleep();
    }

    private void napelnianieWiadra() {
        System.out.println(name + "      NAPEŁNIA wiadro " + w.status() );
        sleep();
    }

    
    private void odebrałOdLewego() {
        System.out.println(name + "                odebrał od lewego wiadro " + w.status());
        sleep();
    }

    
    private void podajeWLewo() {
        System.out.println(name + "                przekazuje wiadro " + w.status() + " w lewo.");
        sleep();
    }

    
    private void pusteWiadro() {
        System.out.println(name + "       WYLEWA wiadro " + w.status() );
        sleep();
    }

    
    private void sleep() {
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

