package circle;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// author-- Sijin Yang
public class TestCircularList {
    public static void main(String[] args) {
        if (!testConstructor()) return;
        if (!testPrintFunction()) return;
        if (!testRemove(0, 0)) return;
        if (!testRemove(0, 3)) return;
        if (!testRemove(1, 3)) return;
        if (!testRemove(3, 3)) return;
        if (!testRemove(1, 0)) return;
        if (!testRemove(10, 0)) return;
        if (!testRemove(10, 3)) return;
        if (!testRemove(10, 9)) return;
        if (!testRemove(2, 1)) return;
        if (!testRemove(2, 0)) return;
        if (!check("Test josephus size 5 rotation 3", 3, CircularList.josephus(5, 3))) return;
        if (!check("Test josephus size 5 rotation 2", 2, CircularList.josephus(5, 2))) return;
        if (!check("Test josephus size 5 rotation 1", 4, CircularList.josephus(5, 1))) return;
        if (!check("Test josephus size 13 rotation 1", 12, CircularList.josephus(13, 1))) return;
        if (!check("Test josephus size 40 rotation 7", 23, CircularList.josephus(40, 7))) return;
        if (!testInvalidValues()) return;
        System.out.println("Congrats your code passed all the tests!.  You survive the first round of the Squid Game");
    }

    static boolean testInvalidValues() {
        try {
            CircularList.josephus(0, 3);
            System.err.println("FAILED - testInvalidValues - size = 0");
        } catch (IllegalArgumentException e) {
            //ignore.  Pass the test
            return true;
        }
        return false;
    }
    static boolean testConstructor() {
        CircularList list = new CircularList(10);
        return check("Test the size of the list", 10, list.size);
    }

    static boolean testPrintFunction() {
        CircularList list = new CircularList(10);
        Deque<Integer> queue = list.print();
        List<Integer> numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        return check("Test print function", numbers.toString(), queue.toString());
    }

    static boolean testRemove(int size, int index) {
        try {
            CircularList list = new CircularList(size);
            Integer value = list.remove(index);
            List<Integer> numbers = IntStream.range(0, size).filter(n -> n != index).mapToObj(Integer::new).collect(Collectors.toList());
            return check("Test remove function. Check remove value: ", index, value)
            && check("Test remove function.  Check the size of the list after removal.", size - 1, list.size)
                    && check("Test remove function. Check the content of the list after removal: ", numbers.toString(), list.print().toString());
        } catch (IllegalArgumentException ex) {
            return true;
        }
    }


    private static boolean check(String descr, Integer result, Integer expected) {
        if (result == null) {
            System.err.println("FAILED: " + descr + " expected " + expected + " but found " + result);
            return false;
        }

        if (expected == null) {
            System.err.println("FAILED: " + descr + " expected " + expected + " but found " + result);
            return false;
        }
        if (!result.equals(expected)) {
            System.err.println("FAILED: " + descr + " expected " + expected + " but found " + result);
            return false;
        }
        return true;
    }

    private static boolean check(String descr, String result, String expected) {
        if (result == null) {
            System.err.println("FAILED " + descr + "expected " + expected + " but found " + result);
            return false;
        }

        if (expected == null) {
            System.err.println("FAILED " + descr + "expected " + expected + " but found " + result);
            return false;
        }
        if (!result.equals(expected)) {
            System.err.println("FAILED " + descr + "expected " + expected + " but found " + result);
            return false;
        }
        return true;

    }
}