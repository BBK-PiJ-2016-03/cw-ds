public class ArrayList implements List{

    private int size = 0;
    private Object[] arr;
    private final int MAX_ARRAY_SIZE = 2147483639 - 8;

    public ArrayList(){
        this(5);
    }

    public ArrayList(int startingSize){
        this.arr = new Object[startingSize];
    }

    public int getArraySize(){
        return this.arr.length;
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
        return retrieveObjectFromIndex(index);      
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
        ReturnObject object = retrieveObjectFromIndex(index);

        if(!object.hasError()){
            removeObjectFromIndex(index);
            this.size--;
        }

        return object;
    }

    private void removeObjectFromIndex(int index){
        Object[] newArr = new Object[this.arr.length];
        copyArrayTo(this.arr, newArr, index);
        copyArrayFromRemoval(this.arr, newArr, index);
        this.arr = newArr;
    }

    private ReturnObject retrieveObjectFromIndex(int index){
        ErrorMessage error = ErrorMessage.NO_ERROR;
        Object returnObject = null;

        if(this.size == 0){
            error = ErrorMessage.EMPTY_STRUCTURE;
        } 
        else if(index >= this.size || index < 0){
            error = ErrorMessage.INDEX_OUT_OF_BOUNDS;
        }
        else{
            returnObject = this.arr[index];
        }

        return new ReturnObjectImpl(returnObject, error);
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

        if(item == null)
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);

        if(index >= this.size || index < 0)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        verifyArraySize();
        insertArrayElementAt(index, item);
        this.size++;    

        return new ReturnObjectImpl(null, null);
    }

    private void insertArrayElementAt(int index, Object item){        
        Object[] newArr = new Object[this.arr.length];
        copyArrayTo(this.arr, newArr, index);
        newArr[index] = item;
        copyArrayFromInsertion(this.arr, newArr, index);
        this.arr = newArr;
    }

    
    @Override
	public ReturnObject add(Object item){
        verifyArraySize();
        this.arr[this.size] = item;
        this.size++;

        return new ReturnObjectImpl(null, null);
    }

    private void verifyArraySize(){
        if(this.arr.length < this.size + 1)
            expandArraySize();
    }

    private void expandArraySize(){
        int newArrSize = this.arr.length * 2;
        if(newArrSize > MAX_ARRAY_SIZE)
            newArrSize = MAX_ARRAY_SIZE;

        if(newArrSize > this.arr.length){
            Object[] newArr = new Object[newArrSize];
            copyArray(this.arr, newArr);
            this.arr = newArr;
        }
    }
    
    private void copyArray(Object[] source, Object[] destination){
        for(int i = 0; i < source.length; i++){
            destination[i] = getArrayCopyWriteValue(source, i);
        }
    }

    private void copyArrayTo(Object[] source, Object[] destination, int stopIndex){
        for(int i = 0; i < source.length && i <= stopIndex; i++){
            destination[i] = getArrayCopyWriteValue(source, i);
        }
    }

    private void copyArrayFromRemoval(Object[] source, Object[] destination, int removalIndex){
        for(int i = removalIndex; i < source.length; i++){
            destination[i] = getArrayCopyWriteValue(source, i+1);
        }
    }

    private void copyArrayFromInsertion(Object[] source, Object[] destination, int insertionIndex){
        for(int i = insertionIndex+1; i < source.length; i++){
            destination[i] = getArrayCopyWriteValue(source, i-1);
        }
    }

    private Object getArrayCopyWriteValue(Object[] source, int index){
        return (index < source.length && index >= 0) ? source[index] : null;
    }

    @Override
    public String toString(){
        String output = ""; //did not use stringbuilder due to constraints of specification
        for(int i = 0; i < this.size; i++){
            output += getStringValue(this.arr, i);
        }

        return output;
    }

    private String getStringValue(Object[] arr, int index){
        if(arr[index] != null)
            return arr[index].toString()+"\n";

        return "";
    }

	
}