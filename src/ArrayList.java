import java.lang.reflect.InaccessibleObjectException;

public class ArrayList<T> implements List<T> {
    private T[] data; //array to store elements
    private int size; // # of elements currently in array
    private int length; // current length of data[]

    public ArrayList(){
        length = 10;
        data = (T[]) new Object[length];
        size = 0;

    }

    @Override
    public void add(int index, T element){
        //checking bounds
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        grow();//will only grow arr when it needs too

        //shifting elements to the right so we can place element
        for(int i = size; i > index; i--){
            data[i] = data[i -1];
        }

        data[index] = element; //putting element in specified index
        size++;//incrementing size
    }

    @Override
    public boolean add(T element){
        grow();
        data[size] = element;
        size++;
        return true;
    }


    
    @Override
    public T get(int index){
        //checking bounds
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    @Override
    public T remove (int index){
        //checking bounds
        if(index < 0|| index >= size){
            throw new IndexOutOfBoundsException();
        }

        T removed = data[index];

        //shift elements to fill the gap
        for(int i = index; i < size; i++){
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    @Override
    public int size(){
        return size;

    }

    //Helper Method 
    private void grow(){
        if(size >= length){
            length = length * 2;
            T[] newSize = (T[]) new Object[length];
            //putting all data from old arr into new bigger arr
            for(int i = 0; i < size; i++){
                newSize[i] = data[i];
            }
            data = newSize;
        }
    }
    
}
