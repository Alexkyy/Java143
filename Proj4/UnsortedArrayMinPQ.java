package Proj4;

/**
 * Alex Y
 */

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class UnsortedArrayMinPQ<E> implements MinPQ<E> {
    private final List<PriorityNode<E>> elements;

    public UnsortedArrayMinPQ() {
        elements = new ArrayList<>();
    }

    public UnsortedArrayMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>();
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            elements.add(new PriorityNode<>(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        elements.add(new PriorityNode<>(element, priority));
    }

    @Override
    public boolean contains(E element) {
        return elements.contains(new PriorityNode<>(element, 0));
    }

    @Override
    public double getPriority(E element) {
        for (PriorityNode<E> node : elements) {
            if (node.getElement().equals(element)) {
                return node.getPriority();
            }
        }
        throw new NoSuchElementException("PQ does not contain " + element);
    }

    @Override
    public E peekMin() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        PriorityNode<E> min = elements.get(0);
        for (PriorityNode<E> node : elements) {
            if (node.getPriority() < min.getPriority()) {
                min = node;
            }
        }
        return min.getElement();
    }

    @Override
    public E removeMin() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        int minIndex = 0;
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i).getPriority() < elements.get(minIndex).getPriority()) {
                minIndex = i;
            }
        }
        return elements.remove(minIndex).getElement();
    }

    @Override
    public void changePriority(E element, double newPriority) {
        for (int i = 0; i < elements.size(); i++) {
            PriorityNode<E> node = elements.get(i);
            if (node.getElement().equals(element)) {
                elements.set(i, new PriorityNode<>(element, newPriority));
                return;
            }
        }
        throw new NoSuchElementException("PQ does not contain " + element);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
