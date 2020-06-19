# Project 9

This repository includes a few short exercises, written in Java 8 SE with no 3rd party dependencies.

This readme describes the usage and assumptions for each task

## [task 1](src/main/java/martintrollip/task1)

This is a JavaFX application using. 

1. The current system time updates runs on a `singleThreadScheduledExecutor` and the UI thread updates the front end using `Platform.runLater`. 
2. `ChristmasUtils` handles logic for determining the number of days until Christmas.  This class was build to be testable, and the tests are included in the source
3. Loading a large CSV is also possible.  The CSV is paginated at 100k entries per load.  The loading of new entries runs on a separate task and thread to prevent the front end from lagging.  The UI gives the user an indication that the application is busy loading in the background. The CSV file should be present in `.\docs\1500000 Sales Records.csv`.  An error message will be shown otherwise.  The CSV can be downloaded from [here](http://eforexcel.com/wp/wp-content/uploads/2017/07/1500000%20Sales%20Records.7z).

## [task 2](src/main/java/main/martintrollip/task2)

Created a final (not extendable) and immutable POJO which represents a person's CV.  The builder pattern ensures that the POJOs are umodifiable and `Collections.unmodifiableList` will prevent and modifications to the list of work history and qualifications.

## [task 3](src/main/java/main/martintrollip/task3) 



## [task 4](src/main/java/main/gew/evaluation/sorter)



