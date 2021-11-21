package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";

    private File file = new File(HOME + SEPARATOR + DEFAULT_FILE);;
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     */
    /**
     *  A method for setting a File as current file.
     *
     * @param newFile the new file location to save
     */
    public void setFile(final File newFile) {
         if (newFile.getParentFile().exists()) {
            this.file = newFile;
        } else {
            throw new IllegalArgumentException("The folder not exist, you need to create it");
        }
    }
     /* 2) A method for getting the current File
     */
    /**
     * A method for getting the current File.
     * 
     * @return the current file
     */
    public File getFile() {
        return this.file;
    }
     /* 3) A method for getting the path (in form of String) of the current File
     */
    /**
     * A method for getting the path (in form of String) of the current File.
     * 
     * @return path of current file
     */
    public String getPath() {
        return this.file.getPath();
    }
     /* 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */
    /**
     * A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * @param text string of text to save in file
     * @throws IOException
     *              if something wrong
     */
    public void save(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(file)) {
            ps.println(text);
        }
    }
     /* 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
