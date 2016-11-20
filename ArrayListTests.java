import org.junit.*;
import static org.junit.Assert.*;

public class ArrayListTests{

    private List list = new ArrayList();

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
        list = new ArrayList();
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
    public void removeFirstElementAndVerifyLastIndex(){
        initList(5);
        int size = list.size();

        assertEquals("0|1|2|3|4|", list.toString());
        ReturnObject ret = list.remove(0);
        size--;

        assertEquals(size, list.size());
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        assertEquals(4, list.get(size-1).getReturnValue());

        //System.out.println("ArrayList: " + list);
    }

    @Test
    public void removeLastElementAndVerifyLastIndex(){
        initList(5);
        int size = list.size();

        assertEquals("0|1|2|3|4|", list.toString());
        ReturnObject ret = list.remove(size-1);
        size--;

        assertEquals(size, list.size());
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        assertEquals(3, list.get(size-1).getReturnValue());

        //System.out.println("ArrayList: " + list);
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
    public void indexInsertionAtExpansion(){
        initList(16);
        System.out.println(list);
        list.add(0,"Y");
        System.out.println(list);
        list.add(0,"Y");
        System.out.println(list);
        list.add(0,"Y");
        System.out.println(list);

    }

    @Test
    public void insertValidZeroIndexOnSingleVal(){
        initList();

        list.add("Z");
        System.out.println(list);

        list.add(0,"Y");
        System.out.println(list);

        list.add(0,"X");
        System.out.println(list);

        list.add(0,"W");
        System.out.println(list);

        list.add(0,"V");
        System.out.println(list);

        list.add(0,"U");
        System.out.println(list);

        list.add(0,"T");
        System.out.println(list);

        list.add(0,"S");
        System.out.println(list);

        list.add(0,"R");
        System.out.println(list);

        list.add(0,"Q");
        System.out.println(list);

        list.add(0,"P");
        System.out.println(list);

        list.add(0,"O");
        System.out.println(list);

        list.add(0,"N");
        System.out.println(list);

        list.add(0,"M");
        System.out.println(list);

        list.add(0,"L");
        System.out.println(list);

        list.add(0,"K");
        System.out.println(list);

        list.add(0,"J");
        System.out.println(list);

        list.add(0,"I");
        System.out.println(list);

        list.add(0,"H");
        System.out.println(list);

        list.add(0,"G");
        System.out.println(list);

        list.add(0,"F");
        System.out.println(list);

        list.add(0,"E");
        System.out.println(list);

        list.add(0,"D");
        System.out.println(list);

        list.add(0,"C");
        System.out.println(list);

        list.add(0,"B");
        System.out.println(list);

        list.add(0,"A");
        System.out.println(list);

        list.add(0,"9");
        System.out.println(list);

        list.add(0,"8");
        System.out.println(list);

        list.add(0,"7");
        System.out.println(list);

        list.add(0,"6");
        System.out.println(list);

        list.add(0,"5");
        System.out.println(list);

        list.add(0,"4");
        System.out.println(list);

        list.add(0,"3");
        System.out.println(list);

        list.add(0,"2");
        System.out.println(list);

        list.add(0,"1");
        System.out.println(list);

        list.add(0,"0");
        System.out.println(list);



        System.out.println(list);

        assertEquals("0", list.get(0).getReturnValue());
        assertEquals("1", list.get(1).getReturnValue());
        assertEquals("2", list.get(2).getReturnValue());
        assertEquals("3", list.get(3).getReturnValue());
        assertEquals("4", list.get(4).getReturnValue());
        assertEquals("5", list.get(5).getReturnValue());
        assertEquals("6", list.get(6).getReturnValue());
        assertEquals("7", list.get(7).getReturnValue());        
        assertEquals("8", list.get(8).getReturnValue());
        assertEquals("9", list.get(9).getReturnValue());
        assertEquals("A", list.get(10).getReturnValue());
        assertEquals("B", list.get(11).getReturnValue());
        assertEquals("C", list.get(12).getReturnValue());
        assertEquals("D", list.get(13).getReturnValue());
        assertEquals("E", list.get(14).getReturnValue());
        assertEquals("F", list.get(15).getReturnValue());
        assertEquals("G", list.get(16).getReturnValue());
        assertEquals("H", list.get(17).getReturnValue());        
        assertEquals("I", list.get(18).getReturnValue());
        assertEquals("J", list.get(19).getReturnValue());
        assertEquals("K", list.get(20).getReturnValue());
        assertEquals("L", list.get(21).getReturnValue());
        assertEquals("M", list.get(22).getReturnValue());
        assertEquals("N", list.get(23).getReturnValue());
        assertEquals("O", list.get(24).getReturnValue());
        assertEquals("P", list.get(25).getReturnValue());
        assertEquals("Q", list.get(26).getReturnValue());
        assertEquals("R", list.get(27).getReturnValue());
        assertEquals("S", list.get(28).getReturnValue());
        assertEquals("T", list.get(29).getReturnValue());
        assertEquals("U", list.get(30).getReturnValue());
        assertEquals("V", list.get(31).getReturnValue());
        assertEquals("W", list.get(32).getReturnValue());
        assertEquals("X", list.get(33).getReturnValue());
        assertEquals("Y", list.get(34).getReturnValue());
        assertEquals("Z", list.get(35).getReturnValue());

    }

    @Test
    public void contentMatches(){
        initList(5);
        assertEquals("0|1|2|3|4|", list.toString());
        assertEquals(2, list.get(2).getReturnValue());
    }

    

    // @Test(timeout = 1500)
    // public void largeInsert(){
    //     initList(8000000);
    // }

    // @Test(timeout = 1500)
    // public void depopulate(){
    //     clearList();
    // }
}