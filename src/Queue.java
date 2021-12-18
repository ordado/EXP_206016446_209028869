public class Queue {
    LinkedList list;
    Node tail;

    public Queue(){}
    public Queue(Node node) {
        tail = node;
        tail.next = null;
        tail.prev = null;
    }

    public void Enqueue(Node node)
    {
        if(tail!=null)
            list.listInsertAfter(node, tail);
        else
            list.listInsert(node);
        tail=node;
    }
    public Node Dequeue(){
        if(list.getHead()==null)
            return null;
        Node node = new Node(list.head.getKey());
        list.listDelete(list.head);
        if(list.head==null)
            tail=null;
        return node;

    }
}
