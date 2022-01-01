import java.io.DataOutputStream;

public class Main {

    public static void main(String[] args) {
        GraphNode layer1 = new GraphNode(7);
        RootedTree tree = new RootedTree(layer1);
        GraphNode layer2R = new GraphNode(2);
        GraphNode layer2L = new GraphNode(4);
        GraphNode layer3L = new GraphNode(1);
        GraphNode layer3R = new GraphNode(5);
        GraphNode layer4L = new GraphNode(9);
        GraphNode layer4R = new GraphNode(8);
        GraphEdge edge74 = new GraphEdge(tree.root, layer2L);
        GraphEdge edge72 = new GraphEdge(tree.root, layer2R);
        GraphEdge edge41 = new GraphEdge(layer2L, layer3L);
        GraphEdge edge25 = new GraphEdge(layer2R, layer3R);
        GraphEdge edge19 = new GraphEdge(layer3L, layer4L);
        GraphEdge edge18 = new GraphEdge(layer3L, layer4R);
        DynamicGraph graph_tree = new DynamicGraph();
        GraphNode layer11 = graph_tree.insertNode(7);
        GraphNode layer22R = graph_tree.insertNode(2);
        GraphNode layer22L = graph_tree.insertNode(4);
        GraphNode layer33L = graph_tree.insertNode(1);
        GraphNode layer33R = graph_tree.insertNode(5);
        GraphNode layer44L = graph_tree.insertNode(9);
        GraphNode layer44R = graph_tree.insertNode(8);
        graph_tree.insertEdge(layer11, layer22L);
        graph_tree.insertEdge(layer22L, layer11);
        graph_tree.insertEdge(layer11, layer22R);
        graph_tree.insertEdge(layer22R, layer11);
        graph_tree.insertEdge(layer22L, layer33L);
        graph_tree.insertEdge(layer33L, layer22L);
        graph_tree.insertEdge(layer22R, layer33R);
        graph_tree.insertEdge(layer33R, layer22R);
        graph_tree.insertEdge(layer33L, layer44L);
        graph_tree.insertEdge(layer44L, layer33L);
        graph_tree.insertEdge(layer33L, layer44R);
        graph_tree.insertEdge(layer44R, layer33L);
        System.out.println("print printByLayer before scc");
        tree.printByLayer();
        System.out.println("print printByLayer after scc");
        graph_tree.scc().printByLayer();




    }

}

