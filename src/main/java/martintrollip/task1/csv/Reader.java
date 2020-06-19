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
 * @author Martin Trollip
 * @since 2020/06/16 17:33
 */
public class Reader {

    private int resultsPerPage = 100_000;

    private final Supplier<Stream<String>> streamSupplier;

    private long currentPosition = 0;
    private long size = 0;

    public Reader(String path, int resultsPerPage) throws IOException {
        this(path);
        this.resultsPerPage = resultsPerPage;
    }

    public Reader(String path) throws IOException {
        streamSupplier = () -> {
            try {
                return Files.lines(Paths.get("." + path)).skip(1); // skipping the heading
            } catch (IOException e) {
                return null;
            }
        };

        if (streamSupplier.get() == null) {
            throw new IOException("Unable to read file"); //TODO this is for front end
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
