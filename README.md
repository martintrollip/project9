# Project 9

This repository includes a few short exercises, written in Java 8 SE with no 3rd party dependencies.

This readme describes the usage and assumptions for each task

## [task 1](src/main/java/martintrollip/task1)

This is a JavaFX application using. 

1. The current system time updates runs on a `singleThreadScheduledExecutor` and the UI thread updates the front end using `Platform.runLater`. 
2. `ChristmasUtils` handles logic for determining the number of days until Christmas.  This class was build to be testable, and the tests are included in the source
3. Loading a large CSV is also possible.  The CSV is paginated at 100k entries per load.  The loading of new entries runs on a separate task and thread to prevent the front end from lagging.  The UI gives the user an indication that the application is busy loading in the background. The CSV file should be present in `.\docs\1500000 Sales Records.csv`.  An error message will be shown otherwise.


## [task 2](src/main/java/main/martintrollip/task2)



## [task 3](src/main/java/main/martintrollip/task3)



## [task 4](src/main/java/main/gew/evaluation/sorter)



