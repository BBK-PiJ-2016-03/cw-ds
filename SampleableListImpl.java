public class SampleableListImpl extends LinkedList implements SampleableList{

    public SampleableList sample(){
        SampleableList list = new SampleableListImpl(); 

        if(super.isEmpty())
            return list;
           
        for(int i = 0; i < super.size(); i+=2){
            list.add(super.get(i).getReturnValue());
        }

        return list;
    }

}