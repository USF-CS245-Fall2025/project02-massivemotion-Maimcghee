public class DummyHeadLinkedList<T> implements List<T>{
    Node<T> dummy = new Node(null);
    int size;

    /**
    * Constructs an empty DummyHeadLinkedList with a dummy head node.
    */
    public DummyHeadLinkedList(){
        dummy.next = null;
        size = 0;
    }

    /**
    * Inserts the specified element at the specified position in this list.
    * Shifts the element currently at that position 
    *
    * @param index the index at which the specified element is to be inserted
    * @param element the element to be inserted
    * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
    */
    @Override
    public void add (int index, T element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> n = new Node(element);

        if(index == 0){
            n.next = dummy.next;
            dummy.next = n;
        }
        else{
            Node curr = dummy.next;
            for(int i = 0; i < index - 1; i++){
                curr = curr.next;
            }
            n.next = curr.next;
            curr.next = n;
        }
        size++; 
    }

    /**
    * Adds the specified element to the end of this list.
    *
    * @param element the element to be appended to this list
    * @return true when successfully added 
    */
    @Override
    public boolean add (T element){
        Node n = new Node(element);

        if(dummy.next == null){
            dummy.next = n;
        }else{
            Node curr = dummy.next;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
        }
        size++;
        return true;
    }
    
    /**
    * Returns the element at the specified position in this list.
    *
    * @param index the index of the element to return
    * @return the element at the specified position in this list
    * @throws IndexOutOfBoundsException if the index is out of range 
    */
    @Override
    public T get (int index){
        if(index < 0 || index >= size){
            //index OOB -> element does not exists there
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = dummy.next;
        for(int i = 0; i < index; i ++){
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any elements on the right of the deleted to the left 
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range 
     */
    @Override
    public T remove (int index){
        //check bounds:
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
         Node<T> removed;
        //if trying to remove head:
        if(index == 0){
            removed = dummy.next;
            dummy.next = removed.next;
            size--;
        }else{
            Node<T> curr = dummy.next;
            for(int i = 0; i < index -1; i++){
                curr = curr.next;
            }
            removed = curr.next;
            curr.next = curr.next.next;
            size--;
        }
        return removed.data;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size (){
        return size;
    }
    
}
