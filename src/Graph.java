import java.lang.reflect.Array;
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
     * Clear the graph
     */
    public void clearGraph() {
        map.clear();
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
        // vertex is contained in graph
        return map.containsKey(vertex);
    }

    /**
     * Does the graph contain the edge
     * @param source Vertex
     * @param destination Vertex
     * @return true if contained in graph
     */
    public boolean hasEdge(T source, T destination) {
        // Edge between source and destination exists
        return map.get(source).contains(destination);
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
     * Perform a BFS (Breadth First Search)
     * This version stores the precursor of each vertex
     * @return ArrayList of updated distance and precursor ArrayLists or null if no possible path
     */
    public ArrayList<ArrayList<Integer>> bfsPrecursorVersion(ArrayList<Integer> distances,
                                                       ArrayList<Integer> precursor,
                                                       ArrayList<T> vertices,
                                                       int sourceIndex,
                                                       int destinationIndex) {
        // track what vertices have been visited
        ArrayList<Boolean> visited = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        // set distance to infinity for all vertices; each index corresponds to the index
        // of the vertex in the vertices array
        for (int i = 0; i < vertices.size(); i++) {
            distances.add(Integer.MAX_VALUE);
            precursor.add(-1);
            visited.add(false);
        }

        //  first visit the source
        visited.set(sourceIndex, true);
        distances.set(sourceIndex, 0);
        queue.push(sourceIndex);

        // BFS
        while (!queue.isEmpty()) {
            int frontItem = queue.poll();
            // Add each adjacent vertex of the current vertex to queue (if not visited yet)
            for (T elt : map.get(vertices.get(frontItem))) {
                int currentItem = vertices.indexOf(elt);
                if (!visited.get(currentItem)) {
                    // have not visited element yet
                    visited.set(currentItem, true);
                    distances.set(currentItem, distances.get(frontItem) + 1);
                    precursor.set(currentItem, frontItem);
                    queue.push(currentItem);

                    // is this the element we are looking for
                    if (currentItem == destinationIndex) {
                        // found index, stop and return
                        ArrayList<ArrayList<Integer>> containerList = new ArrayList<>();
                        containerList.add(distances);
                        containerList.add(precursor);
                        return containerList;
                    }
                }
            }
        }

        // no connection was found
        return null;
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

    /**
     * Find the shortest path between two nodes.
     * @param source Start Node
     * @param destination End Node
     * @return String output of results
     */
    public String shortestPath(T source, T destination) {
        // array to keep track of the nodes where the distances[index] = distance4 of index vertex
        // from the source vertex.
        ArrayList<Integer> distances = new ArrayList<>(countVertices());  // set to number of items

        // array to keep track of the nodes such that precursors[index] is the immediate precursor
        // to the index vertex in the breadth first search
        ArrayList<Integer> precursors = new ArrayList<>(countVertices());  // set to number of items

        // array to correspond the vertices in the map to a static index
        ArrayList<T> vertices = new ArrayList<>(countVertices());

        // index that source vertex is in
        int sourceIndex = -1;

        // index that destination vertex is in
        int destinationIndex = -1;

        // loop through covering each vertex
        int iter = 0;
        for (T vertex : map.keySet()) {
            vertices.add(vertex);
            if (vertex.equals(source)) {
                sourceIndex = iter;
            }

            if (vertex.equals(destination)) {
                destinationIndex = iter;
            }
            iter++;
        }

        // search the tree
        ArrayList<ArrayList<Integer>> containerList = bfsPrecursorVersion(distances, precursors,
                vertices, sourceIndex, destinationIndex);
        if (containerList == null) {
            // no connection found
            return  "no connection between source and destination found";
        }

        distances = containerList.get(0);
        precursors = containerList.get(1);

        // create the shortest path
        ArrayList<T> sPath = new ArrayList<>();  // holds the shortest path
        int current = destinationIndex;
        sPath.add(vertices.get(current));

        // start from destination and work back towards source
        while (precursors.get(current) != -1) {
            int precursor = precursors.get(current);
            sPath.add(vertices.get(precursor));
            current = precursor;
        }

        // create outcome output
        StringBuilder stringProgress = new StringBuilder();
        stringProgress.append("The length of the shortest path = " + distances.get(destinationIndex));
        stringProgress.append("\nThe path is: ");
        for (int i = sPath.size() - 1; i >= 0; i--) {
            stringProgress.append(sPath.get(i) + " ");
        }

        return  stringProgress.toString();
    }
}
