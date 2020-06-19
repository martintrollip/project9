package martintrollip.task1.csv;

import javafx.concurrent.Task;

import java.util.List;

/**
 * A task for loading the next page of a CSV on a separate thread
 */
public class NextCSVTask extends Task<List<String>> {

    private final Reader reader;

    public NextCSVTask(Reader reader) {
        this.reader = reader;
    }

    @Override
    protected List<String> call() throws Exception {
        return reader.next();
    }
}
