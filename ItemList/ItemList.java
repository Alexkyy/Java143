package ItemList;

public class ItemList {

    //field
    private InternalNode first;
    private int count;

    /*
    Inserts the given item at the given index if 
    an equal item is not already in the list. If the
    method is successful it returns true. If an equal 
    item already exists in the list or the index
    is out of range, this method returns false.
    */
    
    public boolean insert(int index, Item item) {
        if(index < 0 || index >= count) return false;
        
        if(index == 0){
            add(item);
        }else {
            InternalNode curr = first;
            int currentIndex = 0;
            while(currentIndex < index - 1){
                curr = curr.next;
                currentIndex++;
            }
            InternalNode node = new InternalNode(item);
            InternalNode nodeAtIndex = curr.next;
            curr.next = node;
            node.next = nodeAtIndex;
            count++;
        }
        return true;
    }

    
    /*Adds the given item to the front
    of the list. Returns true if successful
    and false if an
    equal item is already in the list.*/
    
    public boolean add(Item item) {
        InternalNode node = new InternalNode(item);
        //1 case the list is empty
        if(first == null){
            first = node;
            count++;
            return true;
        }
        //2 list has item
        if (find(item)!= -1) return false;
        
        InternalNode temp = first;
        first = node;
        first.next = temp;
        count++;
        return true; 
    }

    /*
    Replaces the object in the list at the
    given index with this object. If the index is out of
    range or the given item is already in the list,
    the method returns null. If the operation is
    successful, the method returns the object that was replaced by this one.
    
    */
    
    public Item set(int index, Item item) {
        if(index < 0 || index >= count) return null;
        
        InternalNode curr = first;
        int currentIndex = 0;
        while(currentIndex < index){
            curr = curr.next;
            currentIndex++;
        }
        Item old = curr.value;
        curr.value = item; 
        return old; //TODO replace this return statement
    }

    public Item delete(int index) {
        if(index < 0 || index >= count) return null;
        
        InternalNode curr = first;
        int currentIndex = 0;
        
        while(currentIndex < index - 1){
            curr = curr.next;
            currentIndex++;
        }
        InternalNode old = curr.next;
        curr.next = curr.next.next;
        count--;
        return old.value;
    }

    public boolean remove(Item item) {
        int index = find(item);
        if (index < 0) return false;
        delete(index);
        return true; //TODO replace this return statement
    }

    /*
    Reports the object in the list
    at the given index. If the index 
    is out of range, returns null.
    */
    
    public Item report(int index) {
        if(index < 0 || index >= count)return null;
        
        InternalNode curr = first;
        int currentIndex = 0;
        while(currentIndex < index){
            curr = curr.next;
            currentIndex++;
        }
        return curr.value; //TODO replace this return statement
    }

    /*
    Finds the given item in the list 
    and returns the index where it was
    found. Returns -1 if it
    is not found.
    */
    
    public int find(Item item) {
        InternalNode curr = first;
        int currentIndex = 0;
        while(curr != null){
            if(curr.value.equals(item)) return currentIndex;
            curr = curr.next;
            currentIndex++;
        }
        return -1; //TODO replace this return statement
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        //TODO write this method
        return null; //TODO replace this return statement
    }

    
    /* Reports the total of all
    the cost field values of all
    the items in the list.*/
    
    public double getTotalCost() {
        double totalCost = 0.;
        InternalNode curr = first;
        while(curr != null){
            totalCost += curr.value.getCost();
            curr = curr.next;
        }
        return totalCost; //TODO replace this return statement
    }

    public double getTotalValue() {
        double totalPrice = 0.;
        InternalNode curr = first;
        while(curr != null){
            totalPrice += curr.value.calcPrice();
            curr = curr.next;
        }
        return totalPrice; //TODO replace this return statement
    }

}

class InternalNode {
Item value;
 InternalNode next;

 public InternalNode(Item value) {
this.value = value;
 }
}
