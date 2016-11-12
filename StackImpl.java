public class StackImpl extends AbstractStack{

    public StackImpl(List list){
        super(list);
        if(super.internalList == null)
            super.internalList = new LinkedList();
    }

    public boolean isEmpty(){
        return super.internalList.isEmpty();
    }

    public int size(){
        return super.internalList.size();
    }



}