public class GraphEdge {
    GraphNode from;
    GraphNode to;
    Node pointer_to;
    Node getPointer_from;

    public GraphEdge(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
        Node node_to = new Node(to);
        from.out_adjacency_list.Enqueue(node_to);
        Node node_from = new Node(from);
        to.in_adjacency_list.Enqueue(node_from);
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }
}
