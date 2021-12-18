import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;

    public RootedTree() {
        // need to implement something???
    }

    public void setRoot(GraphNode root) {
        this.root = root;
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

                else {
                    out.writeInt(index.getKey().key);
                    out.writeChar(',');
                }
                //print with ,//
                q.Dequeue();
                Node index2 = new Node((index.getKey().out_adjacency_list.head).getKey());
                while (index2 != null) {
                    q.Enqueue(index2);
                    index2 = index2.getNext();
                }
                if (index.getNext() != null && index.getNext().getKey().key == -1000) {
                    //new line//
                    out.writeChars(System.lineSeparator());
                    index = index.getNext();
                    q.Enqueue(new Node(new GraphNode(-1000)));
                }
                index = index.getNext();
            }
        } catch (IOException ex) {
            return;
        }
    }

    private static void preorderPrintAux(DataOutputStream out, GraphNode root_pre) {
        try {
            if (root_pre == null)
                return;
            Node index = root_pre.out_adjacency_list.getHead();
            out.writeInt(root_pre.getKey());
            out.writeChar(',');
            while (index != null) {
                preorderPrintAux(out, index.key);
                index = index.getNext();
            }
        } catch (IOException ex) {
            return;
        }
    }

    public void preorderPrint(DataOutputStream out) {
        preorderPrintAux(out, root);
    }
}


