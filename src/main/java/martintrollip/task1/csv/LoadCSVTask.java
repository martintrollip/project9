package martintrollip.task1.csv;

import javafx.concurrent.Task;

/**
 * A task for loading a CSV on a separate thread
 */
public class LoadCSVTask extends Task<Reader> {

    private final String file;

    public LoadCSVTask(String file) {
        this.file = file;
    }

    @Override
    protected Reader call() throws Exception {
        return new Reader(file);
    }
}
