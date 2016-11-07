public class Tests{

    public static void main(String[] args){
        Tests tests = new Tests();

        tests.arrayListTests();
    }

    private void arrayListTests(){
        ReturnObject ret = null;
        System.out.println("----New ArrayList");
        ArrayList list = new ArrayList();    

        ret = list.get(-1);
        assertEquals(true, ret.hasError(), "Element retrieved at index -1 with error");
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError(), "Error is EMPTY_STRUCTURE");    


        assertEquals(true, list.isEmpty(), "isEmpty at initialisation");
        assertEquals(0, list.size(), "Size 0 at initialisation");

        
        //System.out.println("size: " + list.size());
        System.out.println("----Adding 1 element");
        ret = list.add(1);
        
        assertEquals(false, list.isEmpty(), "isEmpty false once holding an element");
        assertEquals(false, ret.hasError(), "Element added without error");
        assertEquals(null, ret.getReturnValue(), "Returned object is null");

        ret = list.get(-1);

        assertEquals(true, ret.hasError(), "Element retrieved at index -1 with error");
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is INDEX_OUT_OF_BOUNDS"); 

        ret = list.get(50);

        assertEquals(true, ret.hasError(), "Element retrieved at index 50 with error");
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is INDEX_OUT_OF_BOUNDS"); 

        ret = list.get(0);

        assertEquals(false, ret.hasError(), "Element retrieved at index 0 without error");
        assertEquals(1, ret.getReturnValue(), "Returned value matches input"); 

        ret = list.add(-1, 1);
        assertEquals(true, ret.hasError(), "Element added at index -1 with error");
        assertEquals(ErrorMessage.INDEX_OUT_OF_BOUNDS, ret.getError(), "Error is out of bounds");

        System.out.println("----Adding 4 more elements");
        for(int i = 2; i <= 5; i++){
            list.add(i);
        }
        System.out.println("Array size: " + list.getArraySize());    
        System.out.println("----Adding 5 more elements"); 
        for(int i = 6; i <= 10; i++){
            list.add(i);
        }
        System.out.println("Array size: " + list.getArraySize());    
        System.out.println("----Adding 5 more elements");
        for(int i = 11; i <= 15; i++){
            list.add(i);
        }
        System.out.println("Array size: " + list.getArraySize());    
        System.out.println("----Adding 5 more elements");
        for(int i = 16; i <= 20; i++){
            list.add(i);
        }

        assertEquals(20, list.size(), "Size 20");

        System.out.println("Array size: " + list.getArraySize()); 
        System.out.println("----Adding 10,000 more elements");
        for(int i = 1; i <= 10000; i++){
            list.add(i);
        }
        System.out.println("Array size: " + list.getArraySize()); 

        

        //System.out.println("Added 21 ints... ");
        //System.out.println("size: " + list.size());

        //System.out.println(list.toString());

        //System.out.println("----------");
        //System.out.println("Removing 5 from the beginning... ");
        ret = list.remove(0);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        //if(ret.hasError()) System.out.println(ret.getError());
        //System.out.println("size: " + list.size());
        //System.out.println(list.toString());

        //System.out.println("----------");
        //System.out.println("Inserting 1-5 at index 5... ");
        ret = list.add(1,5);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(2,5);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(3,5);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(4,5);
        //if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(5,5);
        //if(ret.hasError()) System.out.println(ret.getError());
        //System.out.println("size: " + list.size());
        //System.out.println(list.toString());

        int count = 0;
        while(!list.isEmpty()){
            list.remove(0);
            count++;
        }
        assertEquals(true, list.isEmpty(), "isEmpty after removing all elements. "+count+" elements removed");

        //max test 2 147 483 639
        for(int i = 1; i <= Integer.MAX_VALUE - 10; i++){
            list.add(1);
            if(i % 100000 == 0)
                System.out.println("iteration: " + i + " array size: " + list.getArraySize());
        }
        System.out.println("Array size: " + list.getArraySize());
    }


    private void assertEquals(Object expected, Object attained, String test){
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
    }
}