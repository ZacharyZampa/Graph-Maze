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
        System.out.println(graph.shortestPath(1, 4));

        graph.clearGraph();
        System.out.println("Graph = " + graph);

        // found example to test with on Geeks for Geeks
        graph.addEdge(0, 1, true);
        graph.addEdge(0, 3, true);
        graph.addEdge(1, 2, true);
        graph.addEdge(3, 4, true);
        graph.addEdge(3, 7, true);
        graph.addEdge(4, 5, true);
        graph.addEdge(4, 6, true);
        graph.addEdge(4, 7, true);
        graph.addEdge(5, 6, true);
        graph.addEdge(6, 7, true);
        System.out.println(graph.shortestPath(0, 7));
    }
}
