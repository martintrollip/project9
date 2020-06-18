package martintrollip.task1.csv;

import javafx.concurrent.Task;

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
