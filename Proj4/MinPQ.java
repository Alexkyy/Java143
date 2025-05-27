package Proj4;

public interface MinPQ<E> {
    void add(E element, double priority);
    boolean contains(E element);
    double getPriority(E element);
    E peekMin();
    E removeMin();
    void changePriority(E element, double priority);
    int size();
}
