public class ArrayList implements List{

    private int size = 0;
    private Object[] arr;

    public ArrayList(){
        ArrayList(5);
    }

    public ArrayList(int startingSize){
        this.arr = new Object[startingSize];
    }
    
    @Override
	public boolean isEmpty(){
        return this.size == 0;
    }

    @Override
	public int size(){
        return this.size;
    }

    @Override
	public ReturnObject get(int index){
        ErrorMessage error = ErrorMessage.NO_ERROR;
        Object returnObject = null;

        if(index == 0){
            error = ErrorMessage.EMPTY_STRUCTURE;
        } 
        else if(index >= this.size || index < 0){
            error = ErrorMessage.INDEX_OUT_OF_BOUNDS;
        }
        else{
            returnObject = arr[index];
        }

        return new ReturnObjectImpl(returnObject, error);        
    }

	/**
	 * Returns the elements at the given position and removes it
	 * from the list. The indeces of elements after the removed
	 * element must be updated accordignly.
	 * 
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 * 
	 * @param index the position in the list of the item to be retrieved
	 * @return the element or an appropriate error message, 
	 *         encapsulated in a ReturnObject
	 */
    @Override
	public ReturnObject remove(int index){

    }

	/**
	 * Adds an element to the list, inserting it at the given
	 * position. The indeces of elements at and after that position
	 * must be updated accordignly.
	 * 
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 * 
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 * 
	 * @param index the position at which the item should be inserted in
	 *              the list
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         or containing an appropriate error message otherwise
	 */
    @Override
	public ReturnObject add(int index, Object item){
        verifyArraySize();
        this.size++;
       
    }

    /**
	 * Adds an element at the end of the list.
	 * 
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 * 
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         or containing an appropriate error message otherwise
	 */
    @Override
	public ReturnObject add(Object item){
        verifyArraySize();
        this.arr[this.size] = item;
        this.size++;
    }

    private void verifyArraySize(){
        if(arr.length < size + 1)
            expandArraySize();
    }

    private void expandArraySize(){
        Object[] newArr = new Object[(this.arr.length * 2)]
        copyArray(this.arr, newArr);
        this.arr = newArr;
    }
    
    private void copyArray(Object[] source, Object[] destination, int startIndex = 0, int offset = 0){
        //write null values for all non copied values
        for(int i = startIndex; i < destination.length; i++){
            destination[i+offset] = getArrayCopyWriteValue(source, i);
        }
    }

    private Object getArrayCopyWriteValue(Object[] source, int index){
        return index < source.length ? source[index] : null;
    }

    @Override
    public String toString(){
        String output = ""; //did not use stringbuilder due to constraints of specification
        for(int i = 0; i < this.size; i++){
            output += getStringValue(this.arr, i);
        }
    }

    private String getStringValue(Object[] arr, int index){
        if(arr[i] != null)
            return arr[i]+"\n";

        return "";
    }

	
}