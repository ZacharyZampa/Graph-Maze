public class Tester {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        graph.addEdge(0, 4, false);
        graph.addEdge(1, 2, false);
        graph.addEdge(1, 3, false);
        graph.addEdge(1, 4, false);
        graph.addEdge(2, 3, false);
        graph.addEdge(3, 4, false);

        System.out.println(graph);
        System.out.println(graph.countEdges(false));
        System.out.println(graph.hasEdge(1, 0));
        System.out.println(graph.hasEdge(1, 4));
        System.out.println(graph.hasVertex(1));
        System.out.println(graph.hasVertex(5));
        System.out.println(graph.bfs());
        System.out.println(graph.dfs());
    }
}
