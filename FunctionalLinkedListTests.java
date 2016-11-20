import org.junit.*;
import static org.junit.Assert.*;

public class FunctionalLinkedListTests{

    private FunctionalList list = new FunctionalLinkedList();

    private void initList(){
        list = new FunctionalLinkedList();
    }

    private void populateList(){
        //populate list
        populateList(100);
    }

    private void populateList(int length){
        //populate list
        for(int i = 0; i < length; i++){
            list.add(i);
        }
    }

    private void clearList(){
        while(!list.isEmpty()){
            list.remove(list.size()-1);
        }
    }

    private void initList(int size){
        initList();        
        populateList(size);
    }


    @Test
    public void isEmpty(){
        initList();
        assertEquals(true, list.isEmpty());

        populateList(129);
        assertEquals(false, list.isEmpty());

        clearList();
        assertEquals(true, list.isEmpty());

        populateList(257);
        assertEquals(false, list.isEmpty());

        clearList();
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void size(){
        initList();
        assertEquals(0, list.size());
        populateList(64);
        assertEquals(64, list.size());
        clearList();
        assertEquals(0, list.size());
        populateList(32);        
        assertEquals(32, list.size());
        clearList();
        assertEquals(0, list.size());
        populateList(1025);        
        assertEquals(1025, list.size());
        clearList();
        assertEquals(0, list.size());
    }

    @Test
    public void getNegativeIndex(){
        initList();
        ReturnObject ret = list.get(-1);
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError());
        assertEquals(null, ret.getReturnValue());
    }

    @Test
    public void getValidZeroIndex(){
        initList(100);

        ReturnObject ret = list.get(0);
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(0, ret.getReturnValue());
    }

    @Test
    public void getValidPisitiveIndex(){
        initList(100);

        ReturnObject ret = list.get(99);
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(99, ret.getReturnValue());
    }

    @Test
    public void getInvalidPositiveIndex(){
        initList(100);

        ReturnObject ret = list.get(100);
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError());
        assertEquals(null, ret.getReturnValue());      
    }

    @Test
    public void removeValidIndex(){
        initList(100);
        int size = list.size();

        Object value = list.get(0).getReturnValue();
        size--;

        ReturnObject ret = list.remove(0);
        assertEquals(size, list.size());
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(value, ret.getReturnValue());
    }

    @Test
    public void removeInValidIndex(){
        initList(100);
        int size = list.size();

        ReturnObject ret = list.remove(list.size());
        assertEquals(size, list.size());
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError());

    }

    @Test
    public void removeNegativeIndex(){
        initList(100);
        int size = list.size();

        ReturnObject ret = list.remove(-10);
        assertEquals(size, list.size());
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError());
    }

    @Test
    public void addValidObject(){
        initList();
        ReturnObject ret = list.add(1);
        
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(null, ret.getReturnValue());

    }

    @Test
    public void addNullObject(){
        initList();
        ReturnObject ret = list.add(null);
        
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.INVALID_ARGUMENT, ret.getError());
        assertEquals(null, ret.getReturnValue());
    }

    @Test
    public void insertInvalidIndex(){
        initList();
        ReturnObject ret = list.add(-1, 1);
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError());
        assertEquals(0, list.size());
    }

    @Test
    public void insertValidIndex(){
        initList(100);
        Object originalValue = list.get(5).getReturnValue();

        ReturnObject ret = list.add(5, 500);
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        //check inserted value is at correct position
        assertEquals(500, list.get(5).getReturnValue());

        //check value previously at that position is now one index further along
        assertEquals(originalValue, list.get(6).getReturnValue());

        //check previous value is still in correct position
        assertEquals((int)originalValue-1, list.get(4).getReturnValue());
        assertEquals(101, list.size());
    }

    @Test
    public void contentMatches(){
        initList(5);
        assertEquals("0|1|2|3|4|", list.toString());
        assertEquals(2, list.get(2).getReturnValue());
    }

    @Test(timeout = 7000)
    public void largeInsert(){
        initList(8000000);
    }

    @Test(timeout = 1500)
    public void depopulate(){
        clearList();
    }

    @Test
    public void testHeadEmptyList(){
        initList();
        ReturnObject ret = list.head();
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError());
    }

    @Test
    public void testHeadPopulatedList(){
        initList();
        //add value
        list.add(1);

        ReturnObject ret = list.head();
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError()); 
        assertEquals(1, ret.getReturnValue());    

        //add value
        list.add(2);

        ret = list.head();
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError()); 
        assertEquals(1, ret.getReturnValue());
    }

    @Test
    public void testHeadDepopulatedList(){
        initList();
        //add value
        list.add(1);
        list.add(2);

        clearList();

        ReturnObject ret = list.head();
        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError());

        list.add(1);

        ret = list.head();
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError()); 
        assertEquals(1, ret.getReturnValue());    
    }

    @Test
    public void testRestEmpty(){
        initList();

        FunctionalList ret = list.rest();
        assertEquals(0, ret.size());
        assertEquals(true, ret.isEmpty());
    }

    @Test
    public void testRestSingleElementList(){
        initList();
        //add value
        list.add(1);

        FunctionalList ret = list.rest();
        assertEquals(0, ret.size());
        assertEquals(true, ret.isEmpty());  
    }

    @Test
    public void testRestMultipleElementList(){
        initList();

        //add values
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        FunctionalList ret = list.rest();
        assertEquals(4, ret.size());
        assertEquals(2, ret.get(0).getReturnValue());     
        assertEquals(3, ret.get(1).getReturnValue());    
        assertEquals(4, ret.get(2).getReturnValue());    
        assertEquals(5, ret.get(3).getReturnValue());   
    }
}