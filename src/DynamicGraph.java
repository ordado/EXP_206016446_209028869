public class DynamicGraph {

    Queue vertices;
    static int time;

    public DynamicGraph() {
        vertices = new Queue();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode gn = new GraphNode(nodeKey);
        Node in_vertices = new Node(gn);
        vertices.list.listInsert(in_vertices);
        gn.pointer_to_vertices_list = in_vertices;
        return gn;
    }

    public void deleteNode(GraphNode Node) {
        if (Node.out_adjacency_list == null && Node.in_adjacency_list == null) {
            vertices.list.listDelete(Node.pointer_to_vertices_list);
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
        Node v = vertices.list.getHead();
        while (v != null) {
            v.getKey().color = "white";
            v.key.d = -1000;
            v.key.pi = null;
            v.key.helper_bfs_dfs = null;
            v = v.next;
        }
        q.Enqueue(new Node(s));
        s.color = "gray";
        s.d = 0;
        s.pi = null;
    }

    public RootedTree bfs(GraphNode source) {
        RootedTree root = new RootedTree();
        Queue q = new Queue();
        BFS_Initialization(source, q);
        source.helper_bfs_dfs = new GraphNode(source.key);
        while (q.tail != null) {
            Node u = q.Dequeue();
            Node v = u.key.out_adjacency_list.tail;
            while (v != null) {
                if (v.key.color == "white") {
                    v.key.color = "gray";
                    v.key.d = u.key.d + 1;
                    v.key.pi = u.key;
                    if (v.key.helper_bfs_dfs != null) {
                        new GraphEdge(u.key.helper_bfs_dfs, v.key.helper_bfs_dfs);
                    } else {
                        v.key.helper_bfs_dfs = new GraphNode(v.key.key);
                        new GraphEdge(u.key.helper_bfs_dfs, v.key.helper_bfs_dfs);
                    }
                    q.Enqueue(new Node(v.key));
                }
                v = v.getPrev();
            }
            u.key.color = "black";
        }
        root.root = source.helper_bfs_dfs;
        return root;
    }

    public void dfs_visit(Queue vertices, GraphNode u) {
        time++;
        u.d = time;
        u.color = "gray";
        Node v = u.out_adjacency_list.tail;
        while (v != null) {
            if (v.key.color == "white") {
                v.key.pi = u;
                dfs_visit(vertices, v.key);
            }
            u.color = "black";
            time++;
            u.f = time;
            vertices.list.listInsert(new Node(u));
        }
    }

    public Queue dfs(Queue vertices) {
        Node u = vertices.tail;
        Queue vertices_second_dfs = new Queue();
        while (u != null) {
            u.key.color = "white";
            u.key.pi = null;
            u.key.helper_bfs_dfs = null;
            u = u.getPrev();
        }
        time = 0;
        u = vertices.tail;
        while (u != null) {
            if (u.key.color == "white")
                dfs_visit(vertices_second_dfs, u.key);
        }
        return vertices_second_dfs;
    }

    public void transpose(Queue vertices) {
        Node u = vertices.tail;
        while (u != null) {
            Queue temp = u.key.out_adjacency_list;
            u.key.out_adjacency_list = u.key.in_adjacency_list;
            u.key.in_adjacency_list = temp;
            u = u.prev;
        }
    }

    public RootedTree scc() {
        RootedTree scc_forest = new RootedTree();
        scc_forest.root = new GraphNode(0);
        Queue vertices_second_dfs = dfs(vertices);
        transpose(vertices_second_dfs);
        Queue vertices_final = dfs(vertices_second_dfs);
        transpose(vertices_second_dfs);
        Node temp = vertices_final.list.getHead();
        GraphNode last_in = null;
        while (temp != null) {
            if (temp.key.pi == null) {
                if (temp.key.helper_bfs_dfs == null) {
                    temp.key.helper_bfs_dfs = new GraphNode(temp.key.key);
                    new GraphEdge(scc_forest.root, temp.key.helper_bfs_dfs);
                    last_in = temp.key.helper_bfs_dfs;
                } else {
                    new GraphEdge(scc_forest.root, temp.key.helper_bfs_dfs);
                    last_in = temp.key.helper_bfs_dfs;
                }

            } else {
                if (temp.key.helper_bfs_dfs == null) {
                    temp.key.helper_bfs_dfs = new GraphNode(temp.key.key);
                    new GraphEdge(last_in, temp.key.helper_bfs_dfs);
                    last_in = temp.key.helper_bfs_dfs;
                } else {
                    new GraphEdge(last_in, temp.key.helper_bfs_dfs);
                    last_in = temp.key.helper_bfs_dfs;
                }
            }
        }
        return scc_forest;
    }

}
