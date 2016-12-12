/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pozar;

/**
 *
 * @author Dominika
 */
class Wiadro {
    
    private boolean pelne = false;
    private int id;

    public Wiadro(int id, boolean isBucketFull) {
        this.id = id;
        setNapelnanie(isBucketFull);
    }

    public boolean jestPelne() {
        return pelne;
    }

    public void setNapelnanie(boolean stan) {
        this.pelne = stan;
    }

    public String status() {
        if (jestPelne()) {
            return "pełne(" + id + ")" ;
        } else {
            return "puste(" + id + ")";
        }
}
}