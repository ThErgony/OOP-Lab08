package it.unibo.oop.lab.advanced;

import java.io.IOException;

public class DrawNumberViewWriteStdout implements DrawNumberView {

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    /**
     * StdOut for incorrect number.
     */
    @Override
    public void numberIncorrect() {
        System.out.println("Incorrect number");

    }

    /**
     * StdOut for result.
     */
    @Override
    public void result(final DrawResult res) {
       System.out.println(res.getDescription());

    }

    /**
     * Limit reach, looser.
     */
    @Override
    public void limitsReached() {
        System.out.println("You loose!");

    }

    /**
     * Display Error for IOException.
     */
    @Override
    public void displayError(final String message) throws IOException {
        System.out.println("Error!" + " " + message);

    }

}
