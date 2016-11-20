import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTests{

    private List list = new LinkedList();

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

    private void initList(){
        list = new LinkedList();
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
        int size = 6;
        initList(size);

        //System.out.println(list);

        Object originalPrevValue = list.get(4).getReturnValue();
        Object originalValue = list.get(5).getReturnValue();


        ReturnObject ret = list.add(5, 500);
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        //check inserted value is at correct position
        assertEquals(500, list.get(5).getReturnValue());

        //check value previously at that position is now one index further along
        assertEquals(originalValue, list.get(6).getReturnValue());

        //check previous value is still in correct position
        assertEquals(originalPrevValue, list.get(4).getReturnValue());
        assertEquals(size+1, list.size());

        //System.out.println(list);
    }

    // @Test
    // public void insertValidIndexOnEmpty(){
    //     initList();

    //     ReturnObject ret = list.add(0, 5);
    //     ret = list.add(0, 4);
    //     ret = list.add(0, 3);
    //     ret = list.add(0, 2);
    //     ret = list.add(0, 1);

    //     assertEquals(5, list.size());

    //     assertEquals(1, list.get(0).getReturnValue());
    //     assertEquals(2, list.get(1).getReturnValue());
    //     assertEquals(3, list.get(2).getReturnValue());
    //     assertEquals(4, list.get(3).getReturnValue());
    //     assertEquals(5, list.get(4).getReturnValue());
    // }

    @Test
    public void insertValidZeroIndex(){
        int size = 6;
        initList(size);

        //System.out.println(list);

        Object originalPrevValue = list.get(0).getReturnValue();
        Object originalValue = list.get(1).getReturnValue();


        ReturnObject ret = list.add(0, 30);
        ret = list.add(0, 20);
        ret = list.add(0, 10);
        ret = list.add(0, 0);
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        //check inserted value is at correct position
        assertEquals(0, list.get(0).getReturnValue());
        assertEquals(10, list.get(1).getReturnValue());
        assertEquals(20, list.get(2).getReturnValue());
        assertEquals(30, list.get(3).getReturnValue());

        //check value previously at that position is now one index further along
        assertEquals(originalPrevValue, list.get(4).getReturnValue());

        //check previous value is still in correct position
        assertEquals(originalValue, list.get(5).getReturnValue());
        assertEquals(size+4, list.size());

        //System.out.println(list);
    }

    @Test
    public void contentMatches(){
        initList(5);
        assertEquals("0\n1\n2\n3\n4\n", list.toString());
        assertEquals(2, list.get(2).getReturnValue());
    }

    // @Test(timeout = 5000)
    // public void largeInsert(){
    //     initList(8000000);
    // }

    // @Test(timeout = 1500)
    // public void depopulate(){
    //     clearList();
    // }
}