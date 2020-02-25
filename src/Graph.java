import java.util.*;

public class Graph<T> {
    // store edges and vertices in the graph
    private HashMap<T, List<T>> map = new HashMap<>();


    /**
     * Add a vertex to the graph
     * @param vertex Vertex to add to graph
     */
    public void addVertex(T vertex) {
        map.put(vertex, new LinkedList<T>());
    }

    /**
     * Add an edge between two vertices (directed)
     * @param source Vertex
     * @param destination Vertex
     * @param undirected true if edge should form in both directions
     */
    public void addEdge(T source, T destination, boolean undirected) {
        if (!map.containsKey(source)) {
            // source vertex did not exist so create that vertex
            addVertex(source);
        }

        if (!map.containsKey(destination)) {
            // destination vertex did not exist so create that vertex
            addVertex(destination);
        }

        // add connections
        map.get(source).add(destination);
        if (undirected) {
            // undirected graph, a connection goes two ways
            map.get(destination).add(source);
        }
    }

    /**
     * Count the number of vertices in a graph
     * @return count of vertices
     */
    public int countVertices() {
        return map.size();
    }


    /**
     * Count the number of edges in a graph
     * @param undirected true if the graph is undirected
     * @return count of edges
     */
    public int countEdges(boolean undirected) {
        int count = 0;

        // loop through vertices of the graph (keys of the map)
        for (T vertex : map.keySet()) {
            count += map.get(vertex).size();
        }

        if (undirected) {
            // undirected graph, a connection goes two ways
            count /= 2;  // count each edge only once not both directions
        }

        return count;
    }


    /**
     * Does the graph contain the vertex
     * @param vertex Vertex to look for
     * @return true if contained in graph
     */
    public boolean hasVertex(T vertex) {
        if (map.containsKey(vertex)) {
            // vertex is contained in graph
            return true;
        }

        return false;
    }

    /**
     * Does the graph contain the edge
     * @param source Vertex
     * @param destination Vertex
     * @return true if contained in graph
     */
    public boolean hasEdge(T source, T destination) {
        if (map.get(source).contains(destination)) {
            // Edge between source and destination exists
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringProgress = new StringBuilder();

        // loop through vertices of the graph (keys of the map)
        for (T vertex : map.keySet()) {
            stringProgress.append(vertex.toString() + " : ");
            for (T edge : map.get(vertex)) {
                stringProgress.append(edge.toString() + " ");
            }
            stringProgress.append("\n");
         }

        return stringProgress.toString();
    }

    /**
     * Perform a BFS (Breadth First Search)
     * @return Path traveled
     */
    public String bfs() {
        // track what vertices have been visited
        ArrayList<T> visited = new ArrayList<>();

        StringBuilder stringProgress = new StringBuilder();
        // loop through covering each vertex
        for (T vertex : map.keySet()) {
            if (!visited.contains(vertex)) {
                // not visited yet
                // Queue for vertices to visit
                LinkedList<T> queue = new LinkedList<>();

                // visit first node
                visited.add(vertex);  // mark as visited
                queue.add(vertex);  // add to queue

                while (queue.size() != 0) {
                    // Dequeue from vertex and process
                    vertex = queue.poll();
                    stringProgress.append(vertex + " ");

                    // Add each adjacent vertex of the current vertex to queue (if not visited yet)
                    for (T elt : map.get(vertex)) {
                        if (!visited.contains(elt)) {
                            // have not visited element yet
                            visited.add(elt);
                            queue.add(elt);
                        }
                    }
                }
            }
        }

        return stringProgress.toString();
    }

    /**
     * Perform a DFS (Depth First Search)
     * @return Path traveled
     */
    public String dfs() {
        // track what vertices have been visited
        ArrayList<T> visited = new ArrayList<>();

        StringBuilder stringProgress = new StringBuilder();
        // loop through covering each vertex
        for (T vertex : map.keySet()) {
            if (!visited.contains(vertex)) {
                // not visited yet
                // Stack for vertices to visit
                Stack<T> stack = new Stack<>();

                // visit first node
                visited.add(vertex);  // mark as visited
                stack.push(vertex);  // add to stack

                while (stack.size() != 0) {
                    // Dequeue from vertex and process
                    vertex = stack.peek();
                    stack.pop();
                    stringProgress.append(vertex + " ");

                    // Add each adjacent vertex of the current vertex to queue (if not visited yet)
                    for (T elt : map.get(vertex)) {
                        if (!visited.contains(elt)) {
                            // have not visited element yet
                            visited.add(elt);
                            stack.push(elt);
                        }
                    }
                }
            }
        }

        return stringProgress.toString();
    }
}
