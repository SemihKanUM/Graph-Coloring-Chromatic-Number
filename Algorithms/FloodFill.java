package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import Graph.Graph;
import Graph.Vertex;

public class FloodFill {

    private Graph graph;
    private int[][] adjacencyMatrix;
    static int num = 0;

    class ColEdge
            {
            int u;
            int v;
            }


    public FloodFill(Graph graph) {
        this.graph = graph;
        adjacencyMatrix = graph.getAdjacencyMatrix();
    }


    public int[] search() {
        // Initialize an ArrayList to store subgraphs and an ArrayList to store chromatic numbers of special cases
        ArrayList<Graph> tempSubGraphs = new ArrayList<>();
        ArrayList<Integer> chromaticNumberSpecial = new ArrayList<>();
        // Initialize variable to keep track of the largest chromatic number found
        int largestChromatic = 0;
        
        // Find subgraphs and store in tempSubGraphs by calling floodFillSeparating and floodFillTagging methods on the input graph
        tempSubGraphs = floodFillSeparating(floodFillTagging(graph));
        ArrayList<Graph> subGraph = new ArrayList<>();
        
        // Iterate through each subgraph
        for (int i = 0; i < tempSubGraphs.size(); i++) {
            // Create a new SpecialCases object for the current subgraph
            SpecialCases SC = new SpecialCases(tempSubGraphs.get(i));
            // Check if the subgraph is a special case
            if (!(SC.isSpecial())) {
                // If not a special case, add the chromatic number to the chromaticNumberSpecial ArrayList
                chromaticNumberSpecial.add(SC.chromaticNumber);
            } else {
                // If it is a special case, print "kaziii" and the number of vertices of the subgraph
                System.out.println("kaziii");
                System.out.println(tempSubGraphs.get(i).getNumberOfVertices());
                subGraph.add(tempSubGraphs.get(i));
            }
        }
    
        // Iterate through chromaticNumberSpecial ArrayList to find the largest chromatic number
        for (int i = 0; i < chromaticNumberSpecial.size(); i++) {
            if (chromaticNumberSpecial.get(i) > largestChromatic) {
                largestChromatic = chromaticNumberSpecial.get(i);
            }
        }
    
        // Print the largest chromatic number found
        System.out.println("TEST:"+largestChromatic);
    
        // Check if there are any special cases
        if(subGraph.isEmpty()){
            // If no special cases, return an array with largest chromatic number
            return new int[] { largestChromatic, largestChromatic, largestChromatic};
        } else {
            // If there are special cases, combine the subgraphs into one graph
            Graph elseGraph = combineGraphs(subGraph);
    
            // Print the number of edges in the combined graph
            System.out.println(elseGraph.getNumberOfEdges()); 
            // Find the upper and lower bounds of chromatic number for the combined graph
            elseGraph.findBounds();
    
            // Get the adjacency matrix of the combined graph
            int [][] graph = elseGraph.getAdjacencyMatrix();

            System.out.println(elseGraph.LowerBound + " " +  elseGraph.chromaticNumber + " " + elseGraph.UpperBound + " " + largestChromatic);

            if (elseGraph.LowerBound == elseGraph.UpperBound) {
                return new int[] { largestChromatic, largestChromatic, largestChromatic };
            } else if (elseGraph.LowerBound >= largestChromatic) {
                return new int[] { elseGraph.LowerBound, elseGraph.chromaticNumber, elseGraph.UpperBound };
            } else if (elseGraph.UpperBound <= largestChromatic) {
                return new int[] { largestChromatic, largestChromatic, largestChromatic };
            } else if (elseGraph.LowerBound <= largestChromatic && largestChromatic <= elseGraph.UpperBound) {
                return new int[] { elseGraph.LowerBound, largestChromatic, elseGraph.UpperBound };
            }

            return new int[] { elseGraph.LowerBound, elseGraph.chromaticNumber, elseGraph.LowerBound }; 
        }

    }


    public static Graph floodFillTagging(Graph graph) {
        Vertex[] vertices = graph.getVertices();
        int tempLabel = 1;

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getLabel() == 0) {
                // Use a Queue to keep track of the vertices to be visited
                Queue<Vertex> queue = new LinkedList<>();
                queue.add(vertices[i]);
                vertices[i].setLabel(tempLabel);
                while (!queue.isEmpty()) {
                    Vertex current = queue.poll();
                    for (Vertex v : current.getAdjacentVertices()) {
                        if (v.getLabel() == 0) {
                            v.setLabel(tempLabel);
                            queue.add(v);
                        }
                    }
                }
                tempLabel++;
            }
        }
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i].getLabel() + " ");
        }
        return graph;

    }


    public static ArrayList<Graph> floodFillSeparating(Graph graph) {
        // Initialize an ArrayList to store subgraphs
        ArrayList<Graph> subGraphs = new ArrayList<>();
        // Initialize an array of vertices from the input graph
        Vertex[] vertices = graph.getVertices();
        // Initialize an ArrayList to store vertices
        ArrayList<Vertex> vertexList = new ArrayList<>();

        // Add all the vertices from the input graph to the vertexList ArrayList
        for (int i = 0; i < vertices.length; i++) {
            vertexList.add(vertices[i]);
        }

        // Sort the vertexList ArrayList by the label of each vertex
        vertexList = (ArrayList<Vertex>) vertexList.stream().sorted(Comparator.comparingInt(Vertex::getLabel))
                .collect(Collectors.toList());

        // Initialize a variable to keep track of the current vertex
        Vertex currenVertex = vertexList.get(0);

        // Iterate through all the labels
        for (int i = 1; i < vertexList.get(vertexList.size() - 1).getLabel() + 1; i++) {
            ArrayList<Vertex> vercticeList = new ArrayList<>();
            // Iterate through all the vertices with the current label
            while (currenVertex.getLabel() == i) {
                vercticeList.add(currenVertex);
                // Check if there are more vertices in the vertexList
                if (!(vertexList.size() <= vertexList.indexOf(currenVertex) + 1)) {
                    currenVertex = vertexList.get(vertexList.indexOf(currenVertex) + 1);
                } else {
                    break;
                }
            }
            // Convert the ArrayList of vertices to an array
            Vertex[] verticeArray = vercticeList.toArray(new Vertex[vercticeList.size()]);
            // Create a new Graph object for the current subgraph
            Graph subGraph = new Graph(verticeArray);
            // Set the number of edges for the current subgraph
            subGraph.setNumberOfEdges(i);
            // Add the current subgraph to the subGraphs ArrayList
            subGraphs.add(subGraph);
        }

        // Return the ArrayList of subgraphs
        return subGraphs;
    }


    public Graph combineGraphs(ArrayList<Graph> elseGraph) {
        int numberOfEdges = 0;
        
        ArrayList<Vertex> combinedVertices = new ArrayList<Vertex>();
    // Iterate through each input graph.
    for (Graph graph : elseGraph) {
    // Get the vertices of the current graph.
    Vertex[] vertices = graph.getVertices();
    // Add all of the vertices from the current graph to the combinedVertices ArrayList.
    combinedVertices.addAll(Arrays.asList((vertices)));
}
// Convert the combinedVertices ArrayList to an array of Vertex objects.
Vertex[] verticeArray = combinedVertices.toArray(new Vertex[combinedVertices.size()]);
// Create a new Graph object using the combined vertices.
Graph endGraph = new Graph(verticeArray);

// Initialize an adjacency matrix for the final graph.
int[][] adjMatrix = new int[endGraph.getNumberOfVertices()][endGraph.getNumberOfVertices()];

// Iterate through each vertex in the final graph.
for (int i = 0; i < verticeArray.length; i++) {
    // Get the list of adjacent vertices for the current vertex.
    ArrayList<Vertex> currentAdjecencyList = verticeArray[i].getAdjacentVertices();

    if (!currentAdjecencyList.isEmpty()) {
        // Iterate through each adjacent vertex.
        for (int j = 0; j < currentAdjecencyList.size(); j++) {
            // adjMatrix[i][j] = 1;
            // adjMatrix[j][i] = 1;
        }
    }
}

// Create an ArrayList to store the edges of the final graph.
ArrayList<ColEdge[]> colEdgesOfSubgraphs = new ArrayList<>();

// Iterate through each vertex in the final graph.
for (int i = 0; i < endGraph.getVertices().length; i++) {
    // Initialize an array to store the edges of the current vertex.
    ColEdge[] colEdges = new ColEdge[endGraph.getVertices()[i].getAdjacentVertices().size()];

    // Iterate through the adjacent vertices of the current vertex.
    for (int j = 0; j < endGraph.getVertices()[i].getAdjacentVertices().size(); j++) {
        // Create a new ColEdge object to represent the edge between the current vertex and its adjacent vertex.
        ColEdge colEdge = new ColEdge();
        // Set the "u" and "v" properties of the ColEdge object to the indices of the current vertex and its adjacent vertex.
        colEdge.u = i;
        colEdge.v = j;
    }
            numberOfEdges += endGraph.getVertices()[i].getAdjacentVertices().size();
            colEdgesOfSubgraphs.add(colEdges);
        }

        endGraph.setNumberOfEdges(numberOfEdges/2);

        for (int i = 0; i < colEdgesOfSubgraphs.size(); i++) {
            for (int j = 0; j < colEdgesOfSubgraphs.get(i).length; j++) {
                if(adjMatrix[colEdgesOfSubgraphs.get(i)[j].u][colEdgesOfSubgraphs.get(i)[j].v] != 1){
                    adjMatrix[colEdgesOfSubgraphs.get(i)[j].u][colEdgesOfSubgraphs.get(i)[j].v] = 0;
                }
            }
        }
        
        endGraph.setAdjacancyMatrix(adjMatrix);

        System.out.println("---------------");
            
                for (int i = 0; i < adjMatrix.length; i++) {
                    for (int j = 0; j < adjMatrix.length; j++) {
                        System.out.print(adjMatrix[i][j] + " ");
                    }
                    System.out.println();
                }

        return endGraph;
    }
}
