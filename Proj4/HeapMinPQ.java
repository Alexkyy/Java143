package Proj4;

import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Alex Y
 */
public class HeapMinPQ<E> implements MinPQ<E> {
    
    private final PriorityQueue<PriorityNode<E>> pq;

    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));
    }

    public HeapMinPQ(Map<E, Double> elementsAndPriorities) {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            pq.add(new PriorityNode<>(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) throw new IllegalArgumentException("Already contains " + element);
        pq.add(new PriorityNode<>(element, priority));
    }

    @Override
    public boolean contains(E element) {
        return pq.contains(new PriorityNode<>(element, 0));
    }

    @Override
    public double getPriority(E element) {
        for (PriorityNode<E> node : pq) {
            if (node.getElement().equals(element)) {
                return node.getPriority();
            }
        }
        throw new NoSuchElementException("PQ does not contain " + element);
    }

    @Override
    public E peekMin() {
        if (pq.isEmpty()) throw new NoSuchElementException("PQ is empty");
        return pq.peek().getElement();
    }

    @Override
    public E removeMin() {
        if (pq.isEmpty()) throw new NoSuchElementException("PQ is empty");
        return pq.poll().getElement();
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) throw new NoSuchElementException("PQ does not contain " + element);
        pq.remove(new PriorityNode<>(element, 0));
        pq.add(new PriorityNode<>(element, priority));
    }

    @Override
    public int size() {
        return pq.size();
    }
}
