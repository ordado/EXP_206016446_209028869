public class DynamicGraph {

    Queue vertices;
    static int time;

    public DynamicGraph() {
        vertices = new Queue();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode gn = new GraphNode(nodeKey);
        Node in_vertices = new Node(gn);
        vertices.Enqueue(in_vertices);
        gn.pointer_to_vertices_list = in_vertices;
        return gn;
    }

    public void deleteNode(GraphNode Node) {
        if (Node.out_adjacency_list == null && Node.in_adjacency_list == null) {
            if (Node.pointer_to_vertices_list == vertices.tail) {
                vertices.tail = vertices.tail.prev;
            }
            vertices.list.listDelete(Node.pointer_to_vertices_list);
        }
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge ge = new GraphEdge(from, to);
        return ge;
    }

    public void deleteEdge(GraphEdge edge) {
        edge.from.out_adjacency_list.list.listDelete(edge.pointer_to);
        if (edge.from.out_adjacency_list.tail == edge.pointer_to)
            edge.from.out_adjacency_list.tail = edge.from.out_adjacency_list.tail.prev;
        if (edge.to.in_adjacency_list.tail == edge.getPointer_from)
            edge.to.in_adjacency_list.tail = edge.to.in_adjacency_list.tail.prev;
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
        Node v = u.out_adjacency_list.list.head;

        while (v != null) {
            if (v.key.color == "white") {
                v.key.pi = u;
                dfs_visit(vertices, v.key);
            }
            v = v.next;
        }

        u.color = "black";
        time++;
        u.f = time;
        vertices.Enqueue(new Node(u));

    }

    public Queue dfs(Queue vertices2) {
        Node u = vertices2.list.head;
        Queue vertices_second_dfs = new Queue();
        while (u != null) {
            u.key.color = "white";
            u.key.pi = null;
            u.key.helper_bfs_dfs = null;
            u = u.next;
        }
        time = 0;
        Node uu = vertices2.list.head;
        while (uu != null && uu.next != null)
            uu = uu.next;

        while (uu != null) {
            if (uu.key.color == "white")
                dfs_visit(vertices_second_dfs, uu.key);
            uu = uu.prev;
        }
        return vertices_second_dfs;
    }

    public void transpose(Queue vertices) {
        Node u = vertices.list.head;
        while (u != null) {
            Queue temp = u.key.out_adjacency_list;
            u.key.out_adjacency_list = u.key.in_adjacency_list;
            u.key.in_adjacency_list = temp;
            u = u.next;
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
        while (temp != null) {
            temp.key.helper_bfs_dfs = new GraphNode(temp.key.key);
            temp = temp.next;
        }

        temp = vertices_final.list.getHead();

        while (temp != null) {
            if (temp.key.pi == null)
                new GraphEdge(scc_forest.root, temp.key.helper_bfs_dfs);
            else
                new GraphEdge(temp.key.pi.helper_bfs_dfs, temp.key.helper_bfs_dfs);

            temp = temp.next;
        }
        return scc_forest;
    }
}
