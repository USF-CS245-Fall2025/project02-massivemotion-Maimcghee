public class SingleLinkedList<T> implements List<T> {
    private Node head;
    private int size;

    public SingleLinkedList(){
        head = null;
        size = 0;
    }

    @Override
    public void add (int index, T element){
        //checking bounds:
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        //making node from element
        Node n = new Node(element);

        //if index is head-> place in head:
        if(index == 0){
            n.next = head;
            head = n;
        }
        else{
            Node curr = head;
            //find next spot
            for(int i = 0; i < index - 1; i++){
                curr = curr.next;
            }
            //should be in position by now -> place in spot
            n.next = curr.next;
            curr.next = n;
        }
        //increment size
        size++; 
    }

    @Override
    public boolean add (T element){
        Node n = new Node(element);

        if(head == null){
            //if list is empty place at head
            head = n;
        }else{
            Node curr = head;
            //traverse to the end of list
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
        }
        size++;
        return true;
    }
    
    @Override
    public T get (int index){
        if(index < 0 || index > size){
            //index OOB -> element does not exists there
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = head;
        for(int i = 0; i < index; i ++){
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public T remove (int index){
        //check bounds:
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
         Node<T> removed;
        //if trying to remove head:
        if(index == 0){
            removed = head;
            head = head.next;
            size--;
        }else{
            Node<T> curr = head;
            for(int i = 0; i < index -1; i++){
                curr = curr.next;
            }
            removed = curr.next;
            curr.next = curr.next.next;
            size--;
        }
        return removed.data;
    }
    public int size (){
        return size;
    }
}
