public class LinkedList implements List{

    private int size = 0;
    private Node head;
    private Node tail;

    @Override
    public boolean isEmpty(){
        return this.size == 0;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public ReturnObject get(int index){

        if(isEmpty())
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);

        if(index >= this.size || index < 0)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);       

        Node node = getNodeAt(index);
        return new ReturnObjectImpl(node.getValue(), null);
    }

    @Override
    public ReturnObject remove(int index){
        if(isEmpty())
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);
            
        if(index >= this.size || index < 0)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);       

        if(this.size == 1)
            return removeLastNode();

        Node nodeToRemove = getNodeAt(index);
        return removeNode(nodeToRemove);        
    }

    private ReturnObject removeNode(Node nodeToRemove){

        Node nextNode = nodeToRemove.getNextNode();
        Node prevNode = nodeToRemove.getPrevNode();

        if(nodeToRemove.equals(this.tail))
            this.tail = prevNode;

        if(nodeToRemove.equals(this.head))
            this.head = nextNode;
    
        if(nextNode != null)
            nextNode.setPrevNode(prevNode);

        if(prevNode != null)
            prevNode.setNextNode(nextNode);

        this.size--;

        return new ReturnObjectImpl(nodeToRemove.getValue(), null);
    }

    private ReturnObject removeLastNode(){
        Node removedNode = this.tail;
        this.head = null;
        this.tail = null;
        this.size--;
        if(removedNode != null)
            return new ReturnObjectImpl(removedNode.getValue(), null);

        return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);
    }

    @Override
    public ReturnObject add(int index, Object item){
        if(item == null)
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);

        if(index >= this.size || index < 0)
            return new ReturnObjectImpl(null, ErrorMessage.INDEX_OUT_OF_BOUNDS);

        Node nextNode = getNodeAt(index);
        Node insertionNode = new NodeImpl(item);
        Node prevNode = nextNode.getPrevNode();

        insertNode(prevNode, insertionNode, nextNode);
        
        return new ReturnObjectImpl(null, null);
    }

    private void insertNode(Node prev, Node insert, Node next){
        insert.setPrevNode(prev);
        insert.setNextNode(next);

        if(prev != null)
            prev.setNextNode(insert);

        if(next != null)
            next.setPrevNode(insert);

        this.size++;
    }

    private Node getNodeAt(int index){
        //take shortest path
        if(index <= this.size / 2)
            return getNodeForwardAt(index);

        return getNodeBackwardAt(index);
    }

    private Node getNodeForwardAt(int index){
        Node selectedNode = this.head;
        for(int i = 1; i <= index; i++){
            if(selectedNode.getNextNode() == null)
                return null;
            selectedNode = selectedNode.getNextNode();
        }
        return selectedNode;
    }

    private Node getNodeBackwardAt(int index){
        int distance = (this.size-2) - index;
        Node selectedNode = this.tail;
        for(int i = 0; i <= distance; i++){
            if(selectedNode.getPrevNode() == null)
                return null;
            selectedNode = selectedNode.getPrevNode();
        }
        return selectedNode;
    }

    @Override
    public ReturnObject add(Object item){
        if(item == null)
            return new ReturnObjectImpl(null, ErrorMessage.INVALID_ARGUMENT);

        if(this.size == 0){
            this.head = new NodeImpl(item);
            this.tail = head;
            this.size++;
            return new ReturnObjectImpl(null, null);
        }

        Node penultimate = this.tail;
        this.tail = new NodeImpl(item);
        this.tail.setPrevNode(penultimate);
        penultimate.setNextNode(this.tail);
        this.size++;
        return new ReturnObjectImpl(null, null);

    }

    @Override
    public String toString(){
        String output = "";
        Node currentNode = this.head;
        while(currentNode != null){
            output += currentNode.getValue() + ",";
            currentNode = currentNode.getNextNode();
        }
        return output;
    }
}