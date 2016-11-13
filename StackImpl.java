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
        ReturnObject ret = super.internalList.add(tailIndex, item);    

        if(ret.hasError())
            System.out.println("Error: " + ret.getError() + "Value: " + ret.getReturnValue());    
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