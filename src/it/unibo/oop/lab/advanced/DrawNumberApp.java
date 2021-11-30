package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @param config configuration file path for min, max, attempts
     */
    public DrawNumberApp(final String config) {
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
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
                    view.displayError("Error: import data from a file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
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
     */
    public static void main(final String... args) {
        new DrawNumberApp("config.yml");
    }

}
