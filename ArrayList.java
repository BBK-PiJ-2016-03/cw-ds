public class ArrayList implements List{

    private int size = 0;
    private Object[] objectArray;
    private final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public ArrayList(){
        this(16);
    }

    public ArrayList(int startingSize){
        this.objectArray = new Object[startingSize];
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

        if(!object.hasError()){
            removeObjectFromIndex(index);
            this.size--;
        }

        return object;
    }

    private void removeObjectFromIndex(int index){
        int newArraySize = this.objectArray.length;

        if(arrayIsTooLarge())
            newArraySize /= 2;

        Object[] newArr = new Object[newArraySize];
        
        copyArrayTo(this.objectArray, newArr, index);
        copyArrayFromRemoval(this.objectArray, newArr, index);

        this.objectArray = newArr;
    }

    private ReturnObject retrieveObjectFromIndex(int index){
        boolean indexOutOfBounds = index >= this.size || index < 0;

        if(isEmpty())
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);        

        if(indexOutOfBounds)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);

        Object returnObject = this.objectArray[index]; 
        return new ReturnObjectImpl(returnObject, ErrorMessage.NO_ERROR);
    }

	
    @Override
	public ReturnObject add(int index, Object item){
        boolean indexOutOfBounds = index >= this.size || index < 0;

        if(item == null)
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);

        if(indexOutOfBounds)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);
                
        insertArrayElementAt(index, item);
        this.size++;    

        return new ReturnObjectImpl(null, null);
    }

    private void insertArrayElementAt(int index, Object item){  

        int newArraySize = this.objectArray.length;

        if(arrayIsTooSmall())
            newArraySize *= 2;

        Object[] newArr = new Object[newArraySize];

        copyArrayTo(this.objectArray, newArr, index);
        newArr[index] = item;
        copyArrayFromInsertion(this.objectArray, newArr, index);

        this.objectArray = newArr;
    }

    
    @Override
	public ReturnObject add(Object item){

        if(item == null)
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);

        if(arrayIsTooSmall())
            expandArraySize();

        this.objectArray[this.size] = item;
        this.size++;

        return new ReturnObjectImpl(null, null);
    }

    private boolean arrayIsTooSmall(){
        if(this.objectArray.length < this.size + 1)
            return true;
        return false;
    }

    private boolean arrayIsTooLarge(){
        int sizeCompare = (this.objectArray.length + 1) / 2;

        if(sizeCompare > this.size && sizeCompare >= 16)
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