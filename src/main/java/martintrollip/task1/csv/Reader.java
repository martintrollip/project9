package martintrollip.task1.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Reader for large CSV files.
 *
 * It creates a stream based on Files.lines.  This is wrapped in a Supplier since some of the stream operations are termination operations.
 * The current position is tracked and .next() may be called to retrieve the next page.
 * hasNext() indicates if more data is present, and if there is no more data next() will return an empty list.
 *
 * Default page size is 100 000 entries and it may be changed by calling the relevant constructor.
 *
 * @author Martin Trollip
 * @since 2020/06/16 17:33
 */
public class Reader {

    private int resultsPerPage = 100_000;

    private final Supplier<Stream<String>> streamSupplier;

    private long currentPosition = 0;
    private long size = 0;

    /**
     * Creates a new {@link Reader} for the specified file with a set number of resultsPerPage
     *
     * @param path file location
     * @param resultsPerPage the page size
     * @throws IOException thrown if the file was not found, this should be handled on the UI to indicate to the user what went wrong
     */
    public Reader(String path, int resultsPerPage) throws IOException {
        this(path);
        this.resultsPerPage = resultsPerPage;
    }

    /**
     * Creates a new {@link Reader} for the specified file
     *
     * @param path file location
     * @throws IOException thrown if the file was not found, this should be handled on the UI to indicate to the user what went wrong
     */
    public Reader(String path) throws IOException {
        streamSupplier = () -> {
            try {
                return Files.lines(Paths.get("." + path)).skip(1); // skipping the heading
            } catch (IOException e) {
                return null;
            }
        };

        if (streamSupplier.get() == null) {
            throw new IOException("Unable to read file");
        }

        setSize();
    }

    private void setSize() {
        this.size = streamSupplier.get().count();
    }

    public boolean hasNext() {
        return currentPosition < size;
    }

    public List<String> next() {
        if (hasNext()) {
            List<String> lines = streamSupplier.get().skip(currentPosition).limit(resultsPerPage).collect(Collectors.toList());
            currentPosition += resultsPerPage;

            return lines;
        } else {
            return new ArrayList<>();
        }
    }
}
