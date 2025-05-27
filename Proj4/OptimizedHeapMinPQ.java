package Proj4;

import java.util.*;

/**
 * Alex y
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {

    private final List<PriorityNode<E>> elements;
    private final Map<E, Integer> elementsToIndex;
    private int n;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        elements = new ArrayList<>();
        elements.add(null); // 1-based indexing
        elementsToIndex = new HashMap<>();
        n = 0;
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     */
    public OptimizedHeapMinPQ(Map<E, Double> elementsAndPriorities) {
        this();
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        elements.add(new PriorityNode<>(element, priority));
        n++;
        elementsToIndex.put(element, n);
        swim(n);
    }

    @Override
    public boolean contains(E element) {
        return elementsToIndex.containsKey(element);
    }

    @Override
    public double getPriority(E element) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        return elements.get(elementsToIndex.get(element)).getPriority();
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return elements.get(1).getElement();
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }

        E min = elements.get(1).getElement();
        swap(1, n);
        elements.remove(n);
        elementsToIndex.remove(min);
        n--;
        if (n >= 1) {
            sink(1);
        }
        return min;
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }

        int index = elementsToIndex.get(element);
        double oldPriority = elements.get(index).getPriority();
        elements.set(index, new PriorityNode<>(element, priority));

        if (priority < oldPriority) {
            swim(index);
        } else {
            sink(index);
        }
    }

    @Override
    public int size() {
        return n;
    }

    private boolean isEmpty() {
        return n == 0;
    }

    private void swim(int k) {
        while(k > 1 && greater(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if(!greater(k, j)) break;
            swap(j, k);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        double eleI = elements.get(i).getPriority();
        double eleJ = elements.get(j).getPriority();
        return eleI > eleJ;
    }

    private void swap(int i, int j) {
        elementsToIndex.remove(elements.get(i).getElement());
        elementsToIndex.remove(elements.get(j).getElement());

        PriorityNode<E> swap = elements.get(i);
        elements.set(i, new PriorityNode<>(elements.get(j).getElement(), elements.get(j).getPriority()));
        elements.set(j, new PriorityNode<>(swap.getElement(), swap.getPriority()));

        elementsToIndex.put(elements.get(i).getElement(), i);
        elementsToIndex.put(elements.get(j).getElement(), j);
    }
}