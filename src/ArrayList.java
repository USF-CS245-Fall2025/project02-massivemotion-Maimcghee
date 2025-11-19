import java.lang.reflect.InaccessibleObjectException;

/**
 * An implementation on ArrayList that implements the List interface
 * includes implementations of add(index) & add(index,element), remove(), get(), and grow()
 * @param <T> is the type of element in this list
 */

public class ArrayList<T> implements List<T> {
    private T[] data; //array to store elements
    private int size; // # of elements currently in array
    private int length; // current length of data[]

    /**
     * Constructs an empty ArrayList with starting length of 10
     */ 
    public ArrayList(){
        length = 10;
        data = (T[]) new Object[length];
        size = 0;

    }

    /**
     * Inserts the specified element in the specific index in the ArrayList
     * Shifts elements at and after the specified index (if any) to make room for the added element
     * increments size after element is added
     * 
     * @param index The specific index that the element will be inserted
     * @param element The element to be added to the ArrayList
     * @throws IndexOutOfBoundsException if the index is outside the range of the ArrayList
     */
    @Override
    public void add(int index, T element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        grow();

        for(int i = size; i > index; i--){
            data[i] = data[i -1];
        }
        data[index] = element; 
        size++;
    }

    /**
     * Adds specifies element to the end of the ArrayList
     * 
     * @param element the specific element to be added to the end of the ArrayList
     * @return true returns true if the element was successfully added to the end 
     */
    @Override
    public boolean add(T element){
        grow();
        data[size] = element;
        size++;
        return true;
    }

    /**
     * Returns the element at the specified position in the list
     * 
     * @param index The specific index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the speficied index is outside the bounds of the ArrayList
     */
    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    /** 
     * Removes the element at the specified position in the ArrayList
     * Shifts all elements after the deleted one to the left to fill the gap
     * decrements the size of the ArrayList after removal
     * 
     * @param specific index of the element to be removed
     * @return the element that was removed from the ArrayList
     * @throws IndexOutOfBoundsException if the specified position it outside of the bounds of the ArrayList
     */
    @Override
    public T remove (int index){
        //checking bounds
        if(index < 0|| index >= size){
            throw new IndexOutOfBoundsException();
        }

        T removed = data[index];

        //shift elements to fill the gap
        for(int i = index; i < size-1; i++){
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    /**
     * Returns the number of elements in the ArrayList
     * 
     * @returns the number of elements in the list
     */
    @Override
    public int size(){
        return size;

    }
    
    /**
     * Helper method to double th size of the internal array if it is considered full
     * This method ensures that there is always room for at least 1 element
     */
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
