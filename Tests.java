public class Tests{

    public static void main(String[] args){
        Tests tests = new Tests();

        tests.arrayListTests();
        tests.linkedListTests();
        tests.functionalArrayListTests();
        tests.functionalLinkedListTests();
        tests.sampleableListTests();
        tests.stackTests();
    }

    private void stackTests(){
        Stack list = new StackImpl(null);
        boolean stack = testStack(list);

        System.out.println(stack
                        ? "All stack tests passed"
                        : "Test failure"
                        );
    }

    private void sampleableListTests(){
        SampleableList list = new SampleableListImpl();

        boolean isEmpty = testIsEmpty(list);
        boolean size = testSize(list);
        boolean get = testGet(list);
        boolean remove = testRemove(list);
        boolean add = testAdd(list);
        boolean insert = testInsert(list);
        boolean content = testContent(list);
        //boolean large = testLarge(list);
        boolean sampleable = testSampleable(list);

        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && sampleable
                        ? "All sampleableList tests passed"
                        : "Test failure"
                        );
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
                        ? "All LinkedList tests passed"
                        : "Test failure"
                        );
    }

    private void functionalLinkedListTests(){
        FunctionalList list = new FunctionalLinkedList();

        boolean isEmpty = testIsEmpty(list);
        boolean size = testSize(list);
        boolean get = testGet(list);
        boolean remove = testRemove(list);
        boolean add = testAdd(list);
        boolean insert = testInsert(list);
        //boolean large = testLarge(list);
        //boolean growAndShrink = growAndShrink((ArrayList)list);
        boolean head = testHead(list);
        boolean rest = testRest(list);

        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && head
                        && rest
                        ? "All FunctionalLinkedList tests passed"
                        : "Test failure"
                        );
    }

    private void functionalArrayListTests(){
        FunctionalList list = new FunctionalArrayList();

        boolean isEmpty = testIsEmpty(list);
        boolean size = testSize(list);
        boolean get = testGet(list);
        boolean remove = testRemove(list);
        boolean add = testAdd(list);
        boolean insert = testInsert(list);
        //boolean large = testLarge(list);
        //boolean growAndShrink = growAndShrink((ArrayList)list);
        boolean head = testHead(list);
        boolean rest = testRest(list);

        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && head
                        && rest
                        ? "All FunctionalArrayList tests passed"
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
        //boolean growAndShrink = growAndShrink((ArrayList)list);


        System.out.println(isEmpty
                        && size
                        && get
                        && remove
                        && add
                        && insert
                        && large
                        //&& growAndShrink
                        ? "All ArrayList tests passed"
                        : "Test failure"
                        );

        
    }

    private boolean testStack(Stack list){
        //System.out.println("----test Samepleable-----");
        clearStack(list);
        ReturnObject ret;
        
        //test top
        ret = list.top();

        boolean t1 = assertEquals(true, ret.hasError(), "top on empty structure has error");
        boolean t2 = assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError(), "top on empty structure has error EMPTY_STRUCTURE");
        boolean t3 = assertEquals(null, ret.getReturnValue(), "top on empty structure has null value");

        ret = list.pop();

        boolean t4 = assertEquals(true, ret.hasError(), "pop on empty structure has error");
        boolean t5 = assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError(), "pop on empty structure has error EMPTY_STRUCTURE");
        boolean t6 = assertEquals(null, ret.getReturnValue(), "pop on empty structure has null value");

        //test push
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        ret = list.top();

        boolean t7 = assertEquals(false, ret.hasError(), "top on populated structure has no error");
        boolean t8 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "top on empty structure has error NO_ERROR");
        boolean t9 = assertEquals(5, ret.getReturnValue(), "size matches expected");

        //test pop
        boolean t10 = assertEquals(5, list.size(), "top on populated structure has value");
        ret = list.pop();
        boolean t11 = assertEquals(false, ret.hasError(), "top on populated structure has no error");
        boolean t12 = assertEquals(ErrorMessage.NO_ERROR, ret.getError(), "top on empty structure has error NO_ERROR");
        boolean t13 = assertEquals(5, ret.getReturnValue(), "top on populated structure has value");
        boolean t14 = assertEquals(4, list.size(), "size matches expected");
        boolean t15 = assertEquals(4, list.top().getReturnValue(), "top on populated structure has value");
        boolean t16 = assertEquals(4, list.pop().getReturnValue(), "top on populated structure has value");
        boolean t17 = assertEquals(3, list.pop().getReturnValue(), "top on populated structure has value");
        boolean t18 = assertEquals(2, list.pop().getReturnValue(), "top on populated structure has value");
        boolean t19 = assertEquals(1, list.pop().getReturnValue(), "top on populated structure has value");
        boolean t20 = assertEquals(null, list.pop().getReturnValue(), "pop on empty structure has null value");

        clearStack(list);

        return t1 && t2 && t3 && t4 && t5 && t6 && t7 && t8 && t9 && t10 && t11 && t12 && t13 && t14 && t15 && t16 && t17 && t18 && t19 && t20;

    }

    

    





    

    
}