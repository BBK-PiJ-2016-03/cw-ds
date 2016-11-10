public class Tests{

    public static void main(String[] args){
        Tests tests = new Tests();

        tests.arrayListTests();
        //tests.linkedListTests();
    }

    private void linkedListTests(){
        List list = new LinkedList();

        boolean isEmpty = testIsEmpty(list);
        boolean size = testSize(list);
        boolean get = testGet(list);
        boolean remove = testRemove(list);
        boolean add = testAdd(list);
        boolean insert = testInsert(list);
        boolean content = testContent(list);
        boolean large = testLarge(list);

        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && large
                        ? "All tests passed"
                        : "Test failure"
                        );
    }

    private void arrayListTests(){

        List list = new ArrayList();

        boolean isEmpty = testIsEmpty(list);
        boolean size = testSize(list);
        boolean get = testGet(list);
        boolean remove = testRemove(list);
        boolean add = testAdd(list);
        boolean insert = testInsert(list);
        boolean large = testLarge(list);

        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && large
                        ? "All tests passed"
                        : "Test failure"
                        );

        
    }

    private boolean testLarge(List list){        
        System.out.println("----test large size-----");
        clearList(list);
        int numberOfelements = 20000000;
        byte store = 1;
        
        long startTime = System.nanoTime();
        for(int i = 1; i <= numberOfelements; i++){
            list.add(store);
            //if(i % 100000 == 0)
                //System.out.println("iteration: " + i + " array size: " + list.getArraySize());
        }
        //System.out.println("Array size: " + list.getArraySize());
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000+"ms");


        boolean t1 = assertEquals(numberOfelements, list.size(), "Array increased in size to "+numberOfelements+" elements successfully");
        
        clearList(list);

        return t1;
    }

    private boolean testRemove(List list){
        System.out.println("----test remove()-----");
        ReturnObject ret = null;
        populateList(list);

        int size = list.size();
        Object value = list.get(0).getReturnValue();

        size--;

        ret = list.remove(0);
        boolean t1 = assertEquals(size, list.size(), "Size reduced by 1");
        boolean t2 = assertEquals(false, ret.hasError(), "Element removed without error");
        boolean t3 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "Error is NO_ERROR");
        boolean t4 = assertEquals(value, ret.getReturnValue(), "Returned value matches the value at index");

        ret = list.remove(list.size());
        boolean t5 = assertEquals(size, list.size(), "Size unchanged");
        boolean t6 = assertEquals(true, ret.hasError(), "Element not removed with error");
        boolean t7 = assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is INDEX_OUT_OF_BOUNDS");

        ret = list.remove(-10);
        boolean t8 = assertEquals(size, list.size(), "Size unchanged");
        boolean t9 = assertEquals(true, ret.hasError(), "Element not removed with error");
        boolean t10 = assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is INDEX_OUT_OF_BOUNDS");

        clearList(list);

        return t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 && t10;
    }

    private boolean testInsert(List list){
        System.out.println("----test add(index, value)-----");
        ReturnObject ret = null;

        ret = list.add(-1, 1);
        boolean t1 = assertEquals(true, ret.hasError(), "Element added at index -1 with error");
        boolean t2 = assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is out of bounds");
        boolean t3 = assertEquals(0, list.size(), "list still has size 0");

        populateList(list);

        Object originalValue = list.get(5).getReturnValue();

        ret = list.add(5, 500);

        boolean t4 = assertEquals(false, ret.hasError(), "Element added at index 5 without error");
        boolean t5 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "NO_ERROR is returned");

        boolean t6 = assertEquals(500, list.get(5).getReturnValue(), "Value inserted at index is returned from index");
        boolean t7 = assertEquals(originalValue, list.get(6).getReturnValue(), "Value previously at index is returned from index + 1");

        //System.out.println(list.toString());

        boolean t8 = assertEquals((int)originalValue-1, list.get(4).getReturnValue(), "Value at index is returned from index");
        boolean t9 = assertEquals(101, list.size(), "list is sized 101");

        clearList(list);

        return t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9;
        
    }

    private boolean testAdd(List list){
        System.out.println("----test add(value)-----");
        ReturnObject ret = null;

        ret = list.add(1);
        
        boolean t1 = assertEquals(false, ret.hasError(), "Element added without error");
        boolean t2 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "Returned error is NO_ERROR");
        boolean t3 = assertEquals(null, ret.getReturnValue(), "Returned object is null");

        ret = list.add(null);
        
        boolean t4 = assertEquals(true, ret.hasError(), "Null element returned error");
        boolean t5 = assertEquals(ErrorMessage.INVALID_ARGUMENT, ret.getError(), "Null element returned INVALID_ARGUMENT");
        boolean t6 = assertEquals(null, ret.getReturnValue(), "Returned object is null");

        clearList(list);

        return t1 && t2 && t3 && t4 && t5 && t6;
    }

    private boolean testIsEmpty(List list){
        System.out.println("----test isEmpty()-----");
        boolean t1 = assertEquals(true, list.isEmpty(), "isEmpty at initialisation");
        populateList(list);
        boolean t2 = assertEquals(false, list.isEmpty(), "isEmpty false when filled");
        clearList(list);
        boolean t3 = assertEquals(true, list.isEmpty(), "isEmpty at initialisation");
        return t1 && t2 && t3;
    }

    private boolean testSize(List list){
        System.out.println("----test size()-----");
        boolean t1 = assertEquals(0, list.size(), "Size 0");
        populateList(list);
        boolean t2 = assertEquals(100, list.size(), "Size 100");
        clearList(list);
        boolean t3 = assertEquals(0, list.size(), "Size 0");  
        return t1 && t2 && t3;  
    }

    private boolean testContent(List list){
        System.out.println("----test content-----");
        populateList(list, 5);
        boolean t1 = assertEquals("0,1,2,3,4,", list.toString(), "Element contents match");
        boolean t2 = assertEquals(2, list.get(2).getReturnValue(), "Get value stored at index 2");
        return t1 && t2;
    }

    private boolean testGet(List list){
        System.out.println("----test get()-----");

        ReturnObject ret = null;

        ret = list.get(-1);
        boolean t1 = assertEquals(true, ret.hasError(), "Element retrieved at index -1 with error");
        boolean t2 = assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError(), "Error is EMPTY_STRUCTURE");
        boolean t3 = assertEquals(null, ret.getReturnValue(), "Returned object is null");

        populateList(list);

        ret = list.get(0);
        boolean t4 = assertEquals(false, ret.hasError(), "Element retrieved at index 0 without error");
        boolean t5 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "Error is NO_ERROR");
        boolean t6 = assertEquals(0, ret.getReturnValue(), "Returned value is 0");

        ret = list.get(99);
        boolean t7 = assertEquals(false, ret.hasError(), "Element retrieved at index 0 without error");
        boolean t8 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "Error is NO_ERROR");
        boolean t9 = assertEquals(99, ret.getReturnValue(), "Returned value is 99");
        // System.out.println(list.toString());
        // for(int i = 0; i < list.size(); i++){
        //     System.out.print(list.get(i).getReturnValue() + " ");
        // }

        ret = list.get(100);
        boolean t10 = assertEquals(true, ret.hasError(), "Element retrieved at index 100 with error");
        boolean t11 = assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is INDEX_OUT_OF_BOUNDS");
        boolean t12 = assertEquals(null, ret.getReturnValue(), "Returned object is null");

        clearList(list);
 
        return t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 && t10 && t11 && t12;
    }

    private void populateList(List list){
        //populate list
        populateList(list, 100);
    }

    private void populateList(List list, int length){
        //populate list
        for(int i = 0; i < length; i++){
            list.add(i);
        }
    }

    public void clearList(List list){
        while(!list.isEmpty()){
            list.remove(0);
        }
    }

    private boolean assertEquals(Object expected, Object attained, String test){
        boolean result = false;
        if(expected == null){
            result = expected == attained;
        }
        else{
            result = expected.equals(attained);
        }        

        String response = "Test ";
        response +=  result ? "Passed | " + test : "Failed | " + test + " : " + attained;

        System.out.println(response);

        return result;
    }
}