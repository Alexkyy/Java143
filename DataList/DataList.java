package DataList;
import java.util.Arrays;

/**
 * This is a collection class that acts like as array list for objects
 * of the Integer type.
 *
 * @author Sijin Yang
 * @version VERSION#1& 4/14/2025
 */
public class DataList {

    // private fields
    private Integer[] array;

    private int count = 0;

    public DataList(int capacity) {
        this.array = new Integer[capacity];
    }

    /**
     * Inserts the given object at the given index.
     *
     * @param index the index where the object should be inserted
     * @param obj the object to insert
     * @return true if the insertion was successful, false if the index
     * is out of range.
     */
    boolean insert(int index, Integer obj) {
        //TODO - write this code
        if (index < 0 || index >= count) return false;
        resize();
        Integer[] tmp = new Integer[count - index];
        System.arraycopy(array, index, tmp, 0, count - index);
        System.arraycopy(tmp, 0, array, index + 1, tmp.length);
        array[index] = obj;
        count++;
        return true;
    }

    /**
     * Adds the given object to the end of the list (the next available place
     * in the array).
     *
     * @param obj the object to add
     * @return the index where the object was stored
     */
    int add(Integer obj) {
        resize();
        array[count++] = obj;
        return count - 1;
    }

    /**
     * Replaces the object in the list at the given index with this object.
     *
     * @param index the location to place this object
     * @param obj the object to set at the given index
     * @return the object that was at this index before replacement
     */
    Integer set(int index, Integer obj) {
        if (index < 0 || index >= count) return null;
        Integer oldValue = array[index];
        array[index] = obj;
        return oldValue;
    }

    /**
     * Deletes the object at the given index from the list.
     *
     * @param index the index of the object to delete
     * @return the object deleted, or null if the index is out of range
     */
    Integer delete(int index) {
        if (index < 0 || index >= count) return null;
        Integer[] tmp = new Integer[count - (index + 1)];
        Integer oldValue = array[index];
        System.arraycopy(array, index + 1, tmp, 0, tmp.length);
        System.arraycopy(tmp, 0, array, index, tmp.length);
        resize();
        count--;
        return oldValue;
    }

    /**
     * Removes the first instance of this object from the list.
     *
     * @param obj the object to match for removal
     * @return true if removed, false if the object is not in the list
     */
    boolean removeFirst(Integer obj) {
        //TODO - write this code
        int foundIndex = findFirst(obj);
        if (foundIndex < 0) return false;
        delete(foundIndex);
        return true; // or true
    }

    /**
     * Removes all instances of this object from the list.
     *
     * @param obj the object to match for removal
     * @return the number of objects removed
     */
    int removeAll(Integer obj) {
        int removeObjects = 0;
        while (removeFirst(obj)) removeObjects++;
        return removeObjects;
    }

    /**
     * Reports the object in the list at the given index.
     *
     * @param index the index of the object to return
     * @return the object at the given index, or null if the index is out of
     * range.
     */
    Integer report(int index) {
        if (index < 0 || index >= count) return null;
        return array[index];
    }

    /**
     * Finds the first instance of the given object.
     *
     * @param obj the object to find
     * @return the index where the object was found, or -1 if not found.
     */
    int findFirst(Integer obj) {
        //TODO - write this code
        //using for-loop to iterate through the data list (count) starting from the first element
        //if found return the current index.  Otherwise, return -1
        for (int index = 0; index < count; index++) {
            if (array[index].equals(obj)) return index;
        }
        return -1;
    }

    /**
     * Finds the last instance of the given object.
     *
     * @param obj the object to fine
     * @return the index where the object was found, or -1 if not found.
     */
    int findLast(Integer obj) {
        //TODO - Write this code
        //using for-loop to iterate through the data list (count) starting from the last element
        //if found return the current index.  Otherwise, return -1
        for (int index = count - 1; index >= 0; index--) {
            if (array[index].equals(obj)) return index;
        }
        return -1;
    }

    /**
     * Reports the number of elements currently stored in the list.
     *
     * @return the number of elements
     */
    int size() {
        return count;
    }

    /**
     * Reports the current size of the underlying array.
     *
     * @return the size of the array
     */
    int capacity() {
        return array.length;
    }

    /**
     * Returns a String representation of the list of objects currently stored
     * in the list.
     *
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        String str = "[";
        for (Integer obj : array) {
            str += obj.toString() + ",";
        }
        if (array.length > 0) {
            str = str.substring(0, str.length() - 1);
        }
        str += "]";
        return str;
    }

    /**
     YOU MUST UPDATE THIS FUNCTION
     * Doubles the size of the array and copies the current elements into the
     * new, larger array.
     * Use System.arraycopy();
     */
    private void resize() {
        if (count >= .75 * array.length) {
            Integer[] tmp = new Integer[array.length * 2];
            System.arraycopy(array, 0, tmp, 0, count);
            array = tmp;

        } else if (count <= 0.25 * array.length) {
            Integer[] tmp = new Integer[array.length / 2];
            System.arraycopy(array, 0, tmp, 0, count);
            array = tmp;
        }
    }
}