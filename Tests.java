public class Tests{

    public static void main(String[] args){
        Tests tests = new Tests();

        tests.arrayListTests();
    }

    private void arrayListTests(){
        List list = new ArrayList();

        System.out.println("----------\nNew ArrayList");
        System.out.println("size: " + list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(17);
        list.add(18);
        list.add(19);
        list.add(20);
        list.add(21);

        System.out.println("Added 21 ints... ");
        System.out.println("size: " + list.size());

        System.out.println(list.toString());

        System.out.println("----------");
        System.out.println("Removing 5 from the beginning... ");
        ReturnObject ret = list.remove(0);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.remove(0);
        if(ret.hasError()) System.out.println(ret.getError());
        System.out.println("size: " + list.size());
        System.out.println(list.toString());

        System.out.println("----------");
        System.out.println("Inserting 1-5 at index 5... ");
        ret = list.add(1,5);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(2,5);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(3,5);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(4,5);
        if(ret.hasError()) System.out.println(ret.getError());
        ret = list.add(5,5);
        if(ret.hasError()) System.out.println(ret.getError());
        System.out.println("size: " + list.size());
        System.out.println(list.toString());

    }
}