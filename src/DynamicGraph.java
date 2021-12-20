public class DynamicGraph {

    LinkedList vertices;

    public DynamicGraph() {
        vertices = new LinkedList();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode gn = new GraphNode(nodeKey);
        Node in_vertices = new Node(gn);
        vertices.listInsert(in_vertices);
        gn.pointer_to_vertices_list = in_vertices;
        return gn;
    }

    public void DeleteNode(GraphNode Node) {
        if (Node.out_adjacency_list == null && Node.in_adjacency_list == null) {
            vertices.listDelete(Node.pointer_to_vertices_list);
        }
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge ge = new GraphEdge(from, to);
        return ge;
    }

    public void deleteEdge(GraphEdge edge) {
        edge.from.out_adjacency_list.list.listDelete(edge.pointer_to);
        edge.to.in_adjacency_list.list.listDelete(edge.getPointer_from);
    }

    public void BFS_Initialization(GraphNode s, Queue q) {
        Node v = vertices.getHead();
        while (v != null) {
            v.getKey().color = "white";
            v.key.d_bfs = -1001;
            v.key.pi = null;
            v = v.next;
        }
        q.Enqueue(new Node(s));
        s.color = "gray";
        s.d_bfs = 0;
        s.pi = null;
    }

    public RootedTree bfs(GraphNode source) {
        RootedTree root = new RootedTree();
        Queue q = new Queue();
        BFS_Initialization(source, q);
        source.helper_bfs_dfs = new GraphNode(source.key);
        while (q.tail != null) {
            Node u = q.Dequeue();
            Node v = u.key.out_adjacency_list.list.getHead();
            while (v != null) {
                if (v.key.color == "white") {
                    v.key.color = "gray";
                    v.key.d_bfs = u.key.d_bfs + 1;
                    v.key.pi = u.key;
                    if (v.key.helper_bfs_dfs != null) {
                        new GraphEdge(u.key.helper_bfs_dfs, v.key.helper_bfs_dfs);
                    } else {
                        v.key.helper_bfs_dfs = new GraphNode(v.key.key);
                        new GraphEdge(u.key.helper_bfs_dfs, v.key.helper_bfs_dfs);
                    }
                    q.Enqueue(new Node(v.key));
                }
                v = v.getNext();
            }
            u.key.color = "black";
        }

        root.root=source.helper_bfs_dfs;
        return root;
    }
}
