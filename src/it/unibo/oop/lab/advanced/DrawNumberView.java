package it.unibo.oop.lab.advanced;

import java.io.IOException;

/**
 *
 */
public interface DrawNumberView {

    /**
     * @param observer the controller to attach
     */
    void setObserver(DrawNumberViewObserver observer);

    /**
     * This method is called before the UI is used. It should finalize its status (if needed).
     */
    void start();

    /**
     * Tells the user that the inserted number is not correct.
     */
    void numberIncorrect();

    /**
     * @param res the result of the last draw
     */
    void result(DrawResult res);

    /**
     * Tells the user that the match is lost.
     */
    void limitsReached();

    /**
     * Display I/O Error.
     * @param message error
     */
    void displayError(String message) throws IOException;

}
