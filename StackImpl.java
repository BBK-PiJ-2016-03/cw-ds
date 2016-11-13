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

    public void push(Object item){
        int tailIndex = size() - 1;
        super.add(tailIndex, item);        
    }

    public ReturnObject top(){
        int tailIndex = size() - 1;
        return get(tailIndex);
    }

    public ReturnObject pop(){
        int tailIndex = size() - 1;
        return remove(tailIndex);
    }
}