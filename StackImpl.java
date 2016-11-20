public class StackImpl extends AbstractStack{

    public StackImpl(List list){
        super(list);
        if(super.internalList == null)
            super.internalList = new ArrayList();
    }

    public boolean isEmpty(){
        return super.internalList.isEmpty();
    }

    public int size(){
        return super.internalList.size();
    }

    public void push(Object item){
        super.internalList.add(item);  
    }

    public ReturnObject top(){
        int tailIndex = size() - 1;
        return super.internalList.get(tailIndex);
    }

    public ReturnObject pop(){
        int tailIndex = size() - 1;
        return super.internalList.remove(tailIndex);
    }
}