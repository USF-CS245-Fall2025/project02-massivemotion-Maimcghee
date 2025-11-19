public class DummyHeadLinkedList<T> implements List<T>{
    Node<T> dummy = new Node(null);
    int size;

    public DummyHeadLinkedList(){
        dummy.next = null;
        size = 0;
    }

    @Override
    public void add (int index, T element){
        //checking bounds:
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        //making node from element
        Node<T> n = new Node(element);

        //if index is at the very front -> update dummy.next:
        if(index == 0){
            n.next = dummy.next;
            dummy.next = n;
        }
        else{
            Node curr = dummy.next;
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

        if(dummy.next == null){
            //if list is empty place at head
            dummy.next = n;
        }else{
            Node curr = dummy.next;
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

    @Override
    public int size (){
        return size;
    }
    
}
