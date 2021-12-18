import java.io.DataOutputStream;

public class RootedTree {
    GraphNode root;

    public RootedTree() {
        // need to implement something???
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }

    public void PrintByLayerAux(Node curent, LinkedList ls) {
        ls.listInsert(curent);


    }

    public void PrintByLayer(DataOutputStream out) {
        try {


            Queue q = new Queue();
            q.Enqueue(new Node(root));
            q.Enqueue(new Node(new GraphNode(-1000)));
            while (q.list.head != null) {
                Node index = q.list.getHead();
                if (index.getNext() != null && index.getNext().getKey().key == -1000)
                    out.writeInt(index.getKey().key);
                    //print index.getkey without ,//

                else
                    //print with ,//
                    q.Dequeue();
                Node index2 = new Node((index.getKey().out_adjacency_list.head).getKey());
                while (index2 != null) {
                    q.Enqueue(index2);
                    index2 = index2.getNext();
                }
                if (index.getNext() != null && index.getNext().getKey().key == -1000) {
                    //new line//
                    index = index.getNext();
                    q.Enqueue(new Node(new GraphNode(-1000)));
                }
                index = q.list.getHead();
            }
        }

    }


}


