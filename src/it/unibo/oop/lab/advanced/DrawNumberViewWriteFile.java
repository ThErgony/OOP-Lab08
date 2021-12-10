package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class DrawNumberViewWriteFile implements DrawNumberView {

    private final PrintStream writeFile;

    public DrawNumberViewWriteFile(final String path) throws FileNotFoundException {
        super();
        this.writeFile = new PrintStream(new File(path));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    /**
     * If pass a wrong number ask to change.
     */
    @Override
    public void numberIncorrect() {
        writeFile.println("Incorrect number!");

    }

    /**
     * Save all result to file.
     * @param res the result
     */
    @Override
    public void result(final DrawResult res) {
        writeFile.println(res.getDescription());

    }

    /**
     * Limit reach receive a message.
     */
    @Override
    public void limitsReached() {
        writeFile.println("You loose!");

    }

    /**
     * Display I/O Error.
     * @param message error
     */
    @Override
    public void displayError(final String message) throws IOException {
        writeFile.println(message);

    }

}
