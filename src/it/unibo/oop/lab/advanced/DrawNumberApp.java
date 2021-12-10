package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param config configuration file path for min, max, attempts
     * @param multiViews list of all views
     */
    public DrawNumberApp(final String config, final DrawNumberView... multiViews) {
        this.views = Arrays.asList(multiViews);
        for (final DrawNumberView view : multiViews) {
            view.setObserver(this);
            view.start();
        }
        try (var importConfig = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(config)))) {
            for (var lineConfig = importConfig.readLine(); lineConfig != null; lineConfig = importConfig.readLine()) {
                final StringTokenizer element = new StringTokenizer(lineConfig);
                final String text = element.nextToken();
                final int value = Integer.parseInt(element.nextToken());
                if ("minimum:".equals(text)) {
                    this.min = value;
                } else if ("maximum:".equals(text)) {
                    this.max = value;
                } else if ("attempts:".equals(text)) {
                    this.attempts = value;
                } else {
                    displayError("Error: import data from a file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
    }

    private void displayError(final String string) throws IOException {
        for (final DrawNumberView view : views) {
            view.displayError(string);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml",
                new DrawNumberViewImpl(),
                new DrawNumberViewWriteFile("out.txt"),
                new DrawNumberViewWriteStdout());

    }

}
