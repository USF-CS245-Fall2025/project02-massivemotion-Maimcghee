public class DoublyLinkedList<T> implements List<T> {
    Node head;
    int size;
    
    /**
     * Constructs an empty DoublyLinkedList.
     */
    public DoublyLinkedList(){
        head = null;
        size = 0;
    }
    
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right. 
     * Updates both forward and backward links.
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
        Node n = new Node(element);

        if(index == 0){
            n.next = head;
            head = n;
            n.prev = null;
        }
        else{
            Node curr = head;
            for(int i = 0; i < index - 1; i++){
                curr = curr.next;
            }
            n.prev = curr;
            n.next = curr.next;
            curr.next = n;
            n.next.prev = n;
        }
        size++; 
    }

    /**
     * Adds the specified element to the end of this list.
     * Updates both forward and backward links.
     *
     * @param element the element to be appended to this list
     * @return true 
     */
    @Override
    public boolean add (T element){
         Node n = new Node(element);

        if(head == null){
            head = n;
            n.prev = null;
        }else{
            Node curr = head;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
            n.prev = curr;
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
          if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = head;
        for(int i = 0; i < index; i ++){
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     * Updates both forward and backward links.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove (int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> removed;
        if(index == 0){
            removed = head;
            head = head.next;
            if(head != null){
                head.prev = null;
            }
        }else{
            Node<T> curr = head;
            for(int i = 0; i < index -1; i++){
                curr = curr.next;
            }
            removed = curr.next;
            curr.next = removed.next;
            if(curr.next != null){
                curr.next.prev = curr;
            }
        }
        size--;
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