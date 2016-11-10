/***
 * A node is an element to hold the stored value as well as provide pointers
 * to the previous and next values
 */
public interface Node{
    /**
     * returns the value stored in the node
     * @return Object value stored in the node
     */
    Object getValue();

    /**
     * returns the pointer to the next node
     * @return Node being next node
     */
    Node getNextNode();

    /**
     * sets the pointer to the next node
     * @param node being node to set as the next node
     */
    void setNextNode(Node node);

    /**
     * returns the pointer to the previous node
     * @return Node being previous node
     */
    Node getPrevNode();

    /**
     * sets the pointer to the next node
     * @param node being node to set as the next node
     */
    void setPrevNode(Node node);

}