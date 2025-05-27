package PQ;

/**
 *
 * @author alexyang
 */

public class MaxPQ <T extends Comparable<T>> {
    private T[] array;
    private int n;
    
    public MaxPQ(int capacity){
        this.array = (T[]) new Comparable[capacity];
        this.n = 0;
    }
    
    public boolean iasEmpty(){ return n==0;}
    
    private void bubblingUp(int index){
        while(index > 1 && less(index/2, index)){
            exchange(index/2, index);
            index = index/2;
        }
    }
    
    private void bubblingDown(int index){
        while(2 * index <= n){
            int childIndex = 2 * index;
            if(childIndex < n && less(childIndex, childIndex + 1)) childIndex++;
            if(!less(index, childIndex)) break;
            exchange(index, childIndex);
            index = childIndex;
        }
    }
    
    public void insert(T value){
        array[++n] = value;
        bubblingUp(n);
    }
    
    public T delMax(){
        T max = array[1];
        exchange(1, n--);
        bubblingDown(1);
        array[n+1] = null;
        return max;
    }
    
    private boolean less(int i, int j){
        return array[i].compareTo(array[j])<0;
    }
    
    private void exchange(int i, int j){
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
