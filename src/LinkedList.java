public class LinkedList {
    Node head;
    int lengh = 0;


    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
        lengh++;
    }

    public void listInsert(Node x) {
        x.next = head;
        if (head != null)
            head.prev = x;
        head = x;
        x.prev = null;
        lengh++;
    }

    public void listInsertAfter(Node x, Node y) {
        if (y.next != null)
            y.next.prev = x;
        x.next = y.next;
        x.prev = y;
        y.next = x;
    }


    public void listDelete(Node x) {
        if (x.prev != null)
            x.prev.next = x.next;
        else
            this.head = x.next;
        if (x.next != null)
            x.next.prev = x.prev;
        if (lengh > 0)
            lengh--;
    }

    public int getLengh() {
        return lengh;
    }
}
