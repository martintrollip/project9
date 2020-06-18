package martintrollip.task3;

import java.util.HashMap;

/**
 * @author Martin Trollip
 * @since 2020/06/18 21:23
 */
public class DistanceTable extends HashMap<Integer, Integer> {
    //TODO assumption the property where 1+ 2 = 2 + 1 makes the has code work
    //TODO small enough data set to make conflicts unlikely.

    public int distanceBetween(String pointA, String pointB) {
        return get(combinedHash(pointA, pointB));
    }

    public void put(String pointA, String pointB, int distance) {
        put(combinedHash(pointA, pointB), distance);
    }

    private int combinedHash(String a, String b) {
        return a.hashCode() + b.hashCode();
    }
}
