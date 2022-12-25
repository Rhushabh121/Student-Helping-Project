// CLASS: Node.java
//
// Author: Rhushabh, 7833416
//
// REMARKS: What is the purpose of this class?
//this class is storing parent objects in it's own object to make a
// list of them
//-----------------------------------------

public class Node {

    private Parent data;
    private Node next;

    public Node(Parent data, Node next) {
        this.data = data;
        this.next = next;
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Parent getData() {
        return data;
    }
}