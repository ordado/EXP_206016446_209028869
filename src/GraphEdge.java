public class GraphEdge {
    GraphNode from;
    GraphNode to;

    public GraphEdge(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
        from.out_adjacency_list.Enqueue(new Node(to));
        to.in_adjacency_list.Enqueue(new Node(from));
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }
}
