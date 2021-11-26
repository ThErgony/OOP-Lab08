package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private String nextString;
    private final List<String> history = new LinkedList<>();

    /**
     * A method for setting the next string to print. Null values are not
     * acceptable, and an exception should be produced.
     */
    @Override
    public void setNextStringToPrint(final String text) {
        this.nextString = Objects.requireNonNull(text);
    }

    /**
     * A method for getting the next string to print.
     */
    @Override
    public String getNextString() {
        return this.nextString;
    }

    /**
     * A method for getting the history of the printed strings (in form of a List
     * of Strings).
     */
    @Override
    public List<String> getPrintHistory() {
        return this.history;
    }

    /**
     * A method that prints the current string. If the current string is unset,
     * an IllegalStateException should be thrown.
     */
    @Override
    public void printCurrentString() {
        if (this.nextString.isEmpty()) {
            throw new IllegalStateException("Insert a valid text");
        }
        this.history.add(this.nextString);
        System.out.println(this.nextString);
    }
}
