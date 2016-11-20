public class ImprovedStackImpl implements ImprovedStack{

    private List list;

    public ImprovedStackImpl(List list) {
        this.list = list;
        if(this.list == null)
            this.list = new LinkedList();
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public int size(){
        return this.list.size();
    }

    public void push(Object item){
        this.list.add(item);  
    }

    public ReturnObject top(){
        int tailIndex = size() - 1;     
        return this.list.get(tailIndex);
    }

    public ReturnObject pop(){
        int tailIndex = size() - 1;
        return this.list.remove(tailIndex);
    }

    public ImprovedStack reverse(){   
        if(isEmpty())
            return new ImprovedStackImpl(null);          

        return getReversedStack();
    }

    private ImprovedStack getReversedStack(){        
        ImprovedStack reversedStack = new ImprovedStackImpl(new ArrayList());
        int tailIndex = size() - 1;
        for(int i = tailIndex; i >= 0; i--){
            reversedStack.push(this.list.get(i).getReturnValue());
        }
        return reversedStack;
    }

    public void remove(Object object){
        List shortenedList = new LinkedList();

        while(size() > 0) {          
            writeOrDiscardTopElement(shortenedList, object);
            System.out.println("This list:\n" + this.list + " shortenedList:\n" + shortenedList);
        }

        this.list = shortenedList;

        System.out.println("Size: " + size());
        System.out.println(this.list);
    }

    private void writeOrDiscardTopElement(List shortenedList, Object objectToRemove){
        Object topElement = pop().getReturnValue();

        //System.out.println("size: " + size() + " topElement: " + topElement + " objectToRemove: " + objectToRemove);  

        if(topElement.equals(objectToRemove)) 
            return;     

        if(shortenedList.size() < 1){
            shortenedList.add(topElement);
            return;
        }
        
        ReturnObject ret = shortenedList.add(0, topElement); 
        //System.out.println(ret.getError());
        //shortenedList.add(topElement);
    }
}