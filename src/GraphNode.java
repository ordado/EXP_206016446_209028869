public class GraphNode {
    int key;
    Queue in_adjacency_list = new Queue();
    Queue out_adjacency_list = new Queue();

    public GraphNode(int key) {
        this.key = key;
    }


    public int getOutDegree() {
        return out_adjacency_list.list.lengh;
        /*
        int count = 0;
        Node temp = out_adjacency_list.getHead();
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
        */
    }

    public int getInDegree() {
        return in_adjacency_list.list.lengh;
        /*
        int count = 0;
        Node temp = in_adjacency_list.getHead();
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
         */
    }

    public int getKey() {
        return key;
    }

}
