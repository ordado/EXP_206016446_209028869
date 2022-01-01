import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;


    public RootedTree() {
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


    public void printByLayer(DataOutputStream out) {
        try {
            Queue q = new Queue();
            q.Enqueue(new Node(root));
            q.Enqueue(new Node(new GraphNode(-1000)));
            Node index;
            while (q.list.head != null) {
                index = q.Dequeue();
                if (index.key.key == -1000) {
                    if (q.list.head != null)
                        q.Enqueue(new Node(new GraphNode(-1000)));
                    continue;
                }
                if (q.list.head != null && q.list.head.getKey().key == -1000) {
                    out.writeBytes(index.getKey().key + System.lineSeparator());
                } else {
                    out.writeBytes(index.getKey().key + ",");
                }

                if (index.getKey().out_adjacency_list.list.head != null) {
                    Node index2 = index.getKey().out_adjacency_list.list.head;
                    while (index2 != null) {
                        q.Enqueue(new Node(index2.key));
                        index2 = index2.getNext();
                    }
                }
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

            out.writeBytes(root_pre.getKey() + ",");
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

    public void printByLayer() {

            Queue q = new Queue();
            q.Enqueue(new Node(root));
            q.Enqueue(new Node(new GraphNode(-1000)));
            Node index;
            while (q.list.head != null) {
                index = q.Dequeue();
                if (index.key.key == -1000) {
                    if (q.list.head != null)
                        q.Enqueue(new Node(new GraphNode(-1000)));
                    continue;
                }
                if (q.list.head != null && q.list.head.getKey().key == -1000) {
                    System.out.println(index.getKey().key);
                } else {
                    System.out.print(index.getKey().key + ",");
                }

                if (index.getKey().out_adjacency_list.list.head != null) {
                    Node index2 = index.getKey().out_adjacency_list.list.head;
                    while (index2 != null) {
                        q.Enqueue(new Node(index2.key));
                        index2 = index2.getNext();
                    }
                }
            }
    }


    private static void preorderPrintAux(GraphNode root_pre, int x) {
        if (root_pre == null)
            return;
        Node index = root_pre.out_adjacency_list.list.getHead();
        if (root_pre.getKey() == x)
            System.out.print(root_pre.getKey());
        else
            System.out.print(root_pre.getKey() + ",");

        while (index != null) {
            preorderPrintAux(index.key, x);
            index = index.getNext();
        }
    }


    public void preorderPrint() {
        Node temp = root.out_adjacency_list.tail;
        while (temp.key.out_adjacency_list.list.head != null) {
            temp = temp.key.out_adjacency_list.tail;
        }
        preorderPrintAux(root, temp.key.key);
    }
}


