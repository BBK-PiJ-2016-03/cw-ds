public class ArrayList implements List{

    private int size = 0;
    private Object[] objectArray;
    //max safe size taken untested as per http://stackoverflow.com/questions/3038392/do-java-arrays-have-a-maximum-size
    private final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private final int DEFAULT_STARTING_SIZE = 16;

    //Removed due to clarifications document
    // public ArrayList(){
    //     this(DEFAULT_STARTING_SIZE);
    // }
    
    // public ArrayList(int startingSize){
    //     this.objectArray = new Object[startingSize];
    // }

    public ArrayList() {
        this.objectArray = new Object[DEFAULT_STARTING_SIZE];
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

	
    @Override
	public ReturnObject remove(int index){        
        ReturnObject object = retrieveObjectFromIndex(index);

        if(!object.hasError())
            removeObjectFromIndex(index);            
        
        return object;
    }

    private void removeObjectFromIndex(int index){
        if(isLastIndex(index)){
            removeObjectFromEndOfList();
            return;
        }
        removeObjectFromWithinList(index);
    }

    private void removeObjectFromEndOfList(){
        this.objectArray[this.size-1] = null;
        this.size--;

        if(arrayIsTooLarge())
            reduceArraySize();
    }

    private void removeObjectFromWithinList(int index){
        Object[] newArr = createNewObjectArray();        
        copyArrayTo(this.objectArray, newArr, index);
        copyArrayFromRemoval(this.objectArray, newArr, index);
        this.objectArray = newArr;
        this.size--;
    }

    private boolean isLastIndex(int index){
        return index == this.size - 1;
    }

    private Object[] createNewObjectArray(){
        int newArraySize = this.objectArray.length;

        if(arrayIsTooLarge())
            newArraySize /= 2;

        if(arrayIsTooSmall())
            newArraySize *= 2;

        return new Object[newArraySize];
    }

    private ReturnObject retrieveObjectFromIndex(int index){
        if(isEmpty())
            return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);        

        if(isIndexOutOfBounds(index))
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);

        Object returnObject = this.objectArray[index]; 
        return new ReturnObjectImpl(returnObject);
    }

    private boolean isIndexOutOfBounds(int index){
        return index >= this.size || index < 0;
    }

	
    @Override
	public ReturnObject add(int index, Object item){
        if(item == null)
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);

        if(isIndexOutOfBounds(index))
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
                
        insertArrayElementAt(index, item);   

        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }

    private void insertArrayElementAt(int index, Object item){ 
        Object[] newArr = createNewObjectArray();
        copyArrayTo(this.objectArray, newArr, index);
        newArr[index] = item;
        copyArrayFromInsertion(this.objectArray, newArr, index);
        this.objectArray = newArr;        
        this.size++; 
    }

    
    @Override
	public ReturnObject add(Object item){
        if(item == null)
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);

        return appendElementToArrayEnd(item);
    }

    private boolean arrayIsTooSmall(){
        if(this.objectArray.length < this.size + 1)
            return true;
        return false;
    }

    private ReturnObject appendElementToArrayEnd(Object item){
        if(arrayIsTooSmall())
            expandArraySize();

        this.objectArray[this.size] = item;
        this.size++;

        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }

    private boolean arrayIsTooLarge(){
        int sizeCompare = (this.objectArray.length + 1) / 2;

        if(sizeCompare > this.size && sizeCompare >= DEFAULT_STARTING_SIZE)
            return true;

        return false;            
    }

    private void expandArraySize(){
        int newArrSize = this.objectArray.length * 2;
        
        if(newArrSize > MAX_ARRAY_SIZE)
            newArrSize = MAX_ARRAY_SIZE;

        if(newArrSize > this.objectArray.length){
            Object[] newArr = new Object[newArrSize];
            copyArray(this.objectArray, newArr);
            this.objectArray = newArr;
        }
        //would normally throw an exception here regarding having hit the upper capacity limit, not doing so due to restriction on using complex types.
        //An indexoutofbounds exception will be thrown instead when attempting to access the next index. Unlikely to reach here in 2016 due to exceeding heap memory.
    }

    private void reduceArraySize(){
        int newArrSize = (this.objectArray.length + 1) / 2;

        Object[] newArr = new Object[newArrSize];
        copyArray(this.objectArray, newArr);
        this.objectArray = newArr;
    }
    
    private void copyArray(Object[] source, Object[] destination){
        for(int i = 0; i < this.size; i++){
            destination[i] = getArrayCopyWriteValue(source, i);
        }
    }

    private void copyArrayTo(Object[] source, Object[] destination, int stopIndex){
        for(int i = 0; i < source.length && i <= stopIndex; i++){
            destination[i] = getArrayCopyWriteValue(source, i);
        }
    }

    private void copyArrayFromRemoval(Object[] source, Object[] destination, int removalIndex){
        for(int i = removalIndex; i < this.size; i++){
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
            output += getStringValue(this.objectArray, i);
        }

        return output;
    }

    private String getStringValue(Object[] arr, int index){
        if(arr[index] != null)
            return arr[index].toString()+"\n";

        return "";
    }
}