package martintrollip.task3;

import java.util.HashMap;

/**
 * A HashMap where the hashCode is a combination of 2 Strings, pointA and pointB.
 *
 * Since the sum of integers are commutative the combined hash for pointA + pointB = combined has for pointB + pointA.  This means
 * each distance between 2 points is only stored in the HashMap once.
 *
 * @author Martin Trollip
 * @since 2020/06/18 21:23
 */
public class DistanceTable extends HashMap<Integer, Integer> {

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
