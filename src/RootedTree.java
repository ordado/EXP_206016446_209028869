import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;

    public RootedTree() {
        // need to implement something???
    }

    public RootedTree(int key) {
        root = new GraphNode(key);
    }

    public RootedTree(GraphNode graphNode) {
        this.root = graphNode;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }


    public void PrintByLayer(DataOutputStream out) {
        try {
            Queue q = new Queue();
            q.Enqueue(new Node(root));
            q.Enqueue(new Node(new GraphNode(-1000)));
            Node index = q.Dequeue();
            while (q.tail != null) {
                if (index.key.key == -1000) {
                    if (q.list.head != null)
                        q.Enqueue(new Node(new GraphNode(-1000)));
                    index = q.Dequeue();
                    continue;
                }
                if (q.list.head != null && q.list.head.getKey().key == -1000) {
                    out.writeInt(index.getKey().key);
                    out.writeChars(System.lineSeparator());
                    //System.out.println(index.getKey().key);
                    //print index.getkey without , and new line//
                } else {
                    out.writeInt(index.getKey().key);
                    out.writeChar(',');
                    //print with ,//
                    //System.out.print(index.getKey().key + ",");
                }

                if (index.getKey().out_adjacency_list.list.head != null) {
                    Node index2 = index.getKey().out_adjacency_list.list.head;
                    while (index2 != null) {
                        //  System.out.println(index2.key.key);
                        q.Enqueue(new Node(index2.key));
                        index2 = index2.getNext();
                    }
                }
                index = q.Dequeue();

            }
        } catch (IOException ex) {
            return;
        }
    }

    private static void preorderPrintAux(DataOutputStream out, GraphNode root_pre) {
        try {
            if (root_pre == null)
                return;
            Node index = root_pre.out_adjacency_list.list.getHead();
            out.writeInt(root_pre.getKey());
            out.writeChar(',');
            System.out.println(root_pre.getKey());
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


