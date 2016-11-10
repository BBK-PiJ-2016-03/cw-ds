public class NodeImpl implements Node{

    private Object value;
    private Node prevNode;
    private Node nextNode;

    public NodeImpl(Object value){
        this.value = value;
    }

    public Object getValue(){
        return this.value;
    }

    public Node getNextNode(){
        return this.nextNode;
    }

    public void setNextNode(Node node){
        this.nextNode = node;
    }

    public Node getPrevNode(){
        return this.prevNode;
    }

    public void setPrevNode(Node node){
        this.prevNode = node;
    }

}