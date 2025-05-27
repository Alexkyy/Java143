package circle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Project 1 - Circular List using Singly Linked List
 * If you decided to use another approach to solve the josephus you still NEED to complete required
 * Circular List functions
 * Name: Sijin Yang
 */
public class CircularList {

    Node last;
    int size = 0;

    public CircularList (int N) {
        for (int x = 0; x < N; x++) {
            add(x);
        }
    }

    /**
     * You can use the Circular List to solve the josephus problem
     * or you can use another approach as long as your code passes all the unit tests
     * @param N
     * @param rotation
     * @return the survivor ID
     */
    public static int josephus(int N, int rotation) {
        /**
         * Must check the size of the list.  If it is less than 2 throw Illegal argument exception
         */
        if (N < 2) throw new IllegalArgumentException("N must be greater than 1");

        //I was stuck on this part and got some help with ChatGpt and now I understand. 
        //Initially, I didnt know how to make the loop work properly, but this code 
        //builds a circular list, list. It then sets the index 0 so that you can start from the fiorst node
        //and the while loop keeps eliminating nodes until there is only one left,
        //then it returns the node
        CircularList list = new CircularList(N);

        int index = 0;

        while (list.size() > 1) {
            index = (index + rotation - 1) % list.size();
            list.remove(index);
        }

        return list.last.data;

    }


    public int size() { return size; }

    /**
     * MUST implement this function regardless if you choose not to use Circular List.
     * 
     * @param val
     */
    public void add(int val) {
        Node node = new Node(val);
        if (last == null) {
            last = node;
            last.next = node;
            size++; 
            return;
        }

        Node temp = last.next; 
        last.next = node;
        node.next = temp;
        last = node;
        size++;
    }

    /**
     * MUST implement this function regardless if you choose not to use Circular List.
     * 
     * @param index
     * @return the removed value
     */
    public Integer remove(int index) {
        //checks if index is out of bounds, which then outputs invalid
        if (index < 0 || index >= size) throw new IllegalArgumentException("Invalid");

        Node curr = last.next;
        int currentIndex = 0;
        
        if (index == 0) {
            int val = curr.data;
            last.next = curr.next;
            size--;
            return val;
        }

        while (currentIndex < index - 1) {
            curr = curr.next;
            currentIndex++;
        }
        Node old = curr.next;
        curr.next = old.next;
        if (old == last) last = curr;
        size--;
        return old.data;
    }
 
    public Deque<Integer> print() {
        Deque<Integer> queue = new ArrayDeque<>();
        if (size == 0) return queue;
        Node curr = last;
        while (curr.next != last) {
            queue.add(curr.next.data);
            curr = curr.next;
        }
        queue.add(curr.next.data);
        return queue;
    }

    static class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }
}