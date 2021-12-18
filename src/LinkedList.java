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
        if (head.next != null)
            head.prev = x;
        head = x;
        x.prev = null;
        lengh++;
    }

    public void listDelete(Node x) {
        if (x.next == null)
            x.prev.next = null;
        if (x.prev != null)
            x.prev.next = x.next;
        else
            head = x.next;
        if (lengh > 0)
            lengh--;
    }

    public int getLengh() {
        return lengh;
    }
}
