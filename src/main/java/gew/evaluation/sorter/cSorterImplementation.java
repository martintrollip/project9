package gew.evaluation.sorter;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//Task 4
public class cSorterImplementation implements ISorter {

    private final List<Double> sortedList = new CopyOnWriteArrayList<>();

    public cSorterImplementation() {
        System.out.println("cSorterImplementation constructed");
        openFileOutput();
        scheduleStatistics();
    }

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void addValueAndSort(Object object) {
        lock.lock();
        if (object instanceof Double) {
            sortedList.add((Double) object);
        } else if (object instanceof String) {
            sortedList.add(Double.valueOf(object.toString()));
        } else {
            System.out.println("Unsupported data type " + object);
        }
        Collections.sort(sortedList);
        lock.unlock();
    }

    @Override
    public List<?> getSortedList() {
        try {
            lock.lock();
            return sortedList;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void publishStatistics() {
        writeFileOutput("Current list size " + sortedList.size() + "\n");
    }

    @Override
    public void terminate() {
        publishStatistics();
        stopStatistics();
        closeFileOutput();
    }

    private static final int STATISTICS_DELAY = 0;
    private static final int STATISTICS_INTERVAL_MS = 700;

    private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    private void scheduleStatistics() {
        timer.scheduleAtFixedRate(this::publishStatistics, STATISTICS_DELAY, STATISTICS_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    private void stopStatistics() {
        timer.shutdown();
    }

    private static final String fileName = "task4.txt";
    private FileOutputStream fileOutputStream;
    private BufferedWriter bufferedWriter;

    private void openFileOutput() {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("Unable to create " + fileName);
                }
            }

            fileOutputStream = new FileOutputStream(fileName);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        } catch (FileNotFoundException e) {
            System.err.println("cSorterImplementation FileNotFoundException " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void writeFileOutput(String statistics) {
        try {
            bufferedWriter.write(statistics);
        } catch (IOException e) {
            System.err.println("publishStatistics IOException " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeFileOutput() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("terminate IOException " + e.getMessage());
        }
    }
}