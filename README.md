# Project 9

This repository includes a few short exercises, written in Java 8 SE with no 3rd party dependencies.

This readme describes the usage and assumptions for each task

## [task 1](src/main/java/martintrollip/task1)

The application should do the following
1. Display a window that show the current time in the format of `hh:mm:ss`
2. It should have a days until Christmas button which will show the number of days left until Christmas when clicked
3. It should have a load CSV button which will load and display the contents of a CSV file 

### Solution 
This is a JavaFX application. 

1. The current system time updates runs on a `singleThreadScheduledExecutor` and the UI thread updates the front end using `Platform.runLater`. 
2. `ChristmasUtils` handles logic for determining the number of days until Christmas.  This class was build to be testable, and the tests are included in the source
3. Loading a large CSV is also possible.  The CSV is paginated at 100k entries per load.  The loading of new entries runs on a separate task and thread to prevent the front end from lagging.  The UI gives the user an indication that the application is busy loading in the background. The CSV file should be present in `.\docs\1500000 Sales Records.csv`.  An error message will be shown otherwise.  The CSV can be downloaded from [here](http://eforexcel.com/wp/wp-content/uploads/2017/07/1500000%20Sales%20Records.7z).

#### Requirements
- `JUnit` has to be added to the classpath to allow testing. 
- `1500000 Sales Records.csv` must be in `.\docs`
- `test_sales.csv` must be in `.\docs`, this is used for running test cases.

## [task 2](src/main/java/main/martintrollip/task2)

The task is to create a POJO representing a person's CV.  Include personal details, a list of qualifications and working history.  The POJO should be immutable and not extendable. 

### Solution
Created a final (not extendable) and immutable POJO which represents a person's CV.  The builder pattern ensures that the POJOs are umodifiable and `Collections.unmodifiableList` will prevent and modifications to the list of work history and qualifications.

## [task 3](src/main/java/main/martintrollip/task3) 

Write a datastructure for a [distance matrix (table)](https://en.wikipedia.org/wiki/Distance_matrix).  It should not have duplicated entries, for example JHB -> CPT is that same as CPT -> JHB.  This datastructure must be as efficient as possible. 

### Solution
Implemented a Distance Table by extending a HashMap.  The combination of two strings will form the new `hashCode` using `string1.hashCode() + string2.hashCode()`.  Since the addition of integers are commutative the combined hash code for `string1 + string2` = `string2 + string1` allowing the distance between `string1` and `string2` to be persisted only once in the hash map.  The data set is small enough to make hash collisions unlikely. 

## [task 4](src/main/java/main/gew/evaluation/sorter)

Sort a list using the given JAR. 

### Solution
Implemented `cSorterImplementation`.  On instantiation, it will create a new file for logging and kick off a periodic thread to publish statistics (list size) every 700ms. To prevent `ConcurrentModification` exceptions a `ReentrantLock` was used.  This is locked before modifying the list, and unlocked after the sorting finished. A thread safe data type `CopyOnWriteArrayList` was also used.  On termination the scheduled thread is shutdown and the file output streams are closed. 

The output is sorted and the statistics indicated ~1578 entries at the end of execution.

#### Requirements
- `SortImplementation.jar` has to be included on the classpath. 
