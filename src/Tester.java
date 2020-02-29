import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        graph.addEdge(0, 4, false, true);
        graph.addEdge(1, 2, false, true);
        graph.addEdge(1, 3, false, true);
        graph.addEdge(1, 4, false, true);
        graph.addEdge(2, 3, false, true);
        graph.addEdge(3, 4, false, true);

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
        graph.addEdge(0, 1, true, true);
        graph.addEdge(0, 3, true, true);
        graph.addEdge(1, 2, true, true);
        graph.addEdge(3, 4, true, true);
        graph.addEdge(3, 7, true, true);
        graph.addEdge(4, 5, true, true);
        graph.addEdge(4, 6, true, true);
        graph.addEdge(4, 7, true, true);
        graph.addEdge(5, 6, true, true);
        graph.addEdge(6, 7, true, true);
        System.out.println(graph.shortestPath(0, 7));

        graph.clearGraph();

        // create a simple Integer matrix to add to Graph
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        int elt = 0;
        for (int row = 0; row < 5; row++) {
            // loop through each column in row
            ArrayList<Integer> arr = new ArrayList<>();
            for (int column = 0; column < 5; column++) {
                arr.add(elt);
                elt++;
            }
            matrix.add(arr);
        }

        System.out.println(matrix.toString());

        // Add matrix to graph
        graph.matrixToGraph(matrix, null, false);
        System.out.println(graph.toString());
        System.out.println(graph.shortestPath(0, 10));


        graph.clearGraph();
        // create a simple Integer maze matrix to add to Graph
        ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
        elt = 0;
        for (int row = 0; row < 5; row++) {
            // loop through each column in row
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(elt);
            elt++;
            arr.add(elt);
            elt++;
            arr.add(elt);
            elt++;
            arr.add(5);
            arr.add(elt);
            elt++;
            maze.add(arr);
        }

        // Add maze matrix to graph
        System.out.println(maze.toString());
        graph.matrixToGraph(maze, 5, true);
        System.out.println(graph.toString());
        System.out.println(graph.shortestPath(0, 3));
    }
}
