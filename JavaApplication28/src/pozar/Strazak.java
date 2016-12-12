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

    int id;
    Exchanger<Wiadro> el, er;
    Wiadro w;

    /**
     *
     * @param id
     * @param el
     * @param er
     */
    public Strazak(int id, Exchanger<Wiadro> el, Exchanger<Wiadro> er, Wiadro w) {
        this.id = id;
        this.el = el;
        this.er = er;
        this.w = w;
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (el == null) { 
                    if (w.jestPelne()) {
                        passingToTheRight();
                        w = er.exchange(w);
                        passedToTheRight();
                    } else {
                        refillBucket();
                        w.setNapelnanie(true);
                    }
                } else if (er == null) { 
                    if (!w.jestPelne()) {
                        passingToTheLeft();
                        w = el.exchange(w);
                        passedToTheLeft();
                    } else {
                        emptyBucket();
                        w.setNapelnanie(false);
                    }
                } else { 
                    if (w.jestPelne()) {
                        passingToTheRight();
                        w = er.exchange(w);
                        passedToTheRight();
                    } else {
                        passingToTheLeft();
                        w = el.exchange(w);
                        passedToTheLeft();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void passingToTheRight() {
        System.out.println(id + " przekazuje wiadro " + w.state() + " w prawo.");
        sleep();
    }

    private void passedToTheRight() {
        System.out.println(id + " odebrał od prawego wiadro " + w.state());
        sleep();
    }

    private void refillBucket() {
        System.out.println(id + " napełnia wiadro " + w.state() + ". [NAPELNIA]");
        sleep();
    }

    /**
     *
     */
    private void passedToTheLeft() {
        System.out.println(id + " odebrał od lewego wiadro " + w.state());
        sleep();
    }

    /**
     *
     */
    private void passingToTheLeft() {
        System.out.println(id + " przekazuje wiadro " + w.state() + " w lewo.");
        sleep();
    }

    /**
     *
     */
    private void emptyBucket() {
        System.out.println(id + " opróżnia wiadro " + w.state() + ". [GASI POZAR]");
        sleep();
    }

    /**
     *
     */
    private void sleep() {
        try {
            // Thread.sleep(1000 + (long)(Math.random() * 1000));
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

