public class Node {
    GraphNode key;
    Node next = null;
    Node prev = null;

    public Node(GraphNode key) {
        this.key = key;
    }

    public GraphNode getKey() {
        return key;
    }

    public Node getNext() {
        return next;
    }

    public void setKey(GraphNode key) {
        this.key = key;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node previous) {
        this.prev = previous;
    }
}
