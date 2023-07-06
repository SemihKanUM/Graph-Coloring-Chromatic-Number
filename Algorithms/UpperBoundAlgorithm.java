package Algorithms;

import java.util.*;

import Graph.Graph;
import Graph.Vertex;

public class UpperBoundAlgorithm {

    private Vertex[] Vertices;
    private Graph graph;
    
    public UpperBoundAlgorithm(Graph graph) { //Takes graph object as parameter and extracts the Vertex[] Vertices array from it.
        /*Launch launch = new Launch();
        launch.LoadInitialValues(); */ //took too much time reading the test data again, so this one was omitted and replaced with the graph code to be more efficient
        this.graph = graph; 
        this.Vertices = graph.getVertices();
    }

    public int search() {
        if(Vertices.length == 0){ //incase of 0 vertices the upperBound is 0
            return 0;
        }

        ArrayList<Integer> allVertexDegrees = new ArrayList<Integer>(); //arraylist with all degrees

        //int numVertices = graph.getNumberOfVertices(); //number of vertices
        //int numEdges = graph.getNumberOfEdges(); //number of edges

        //int completeGraphEdges = numVertices * (numVertices - 1) / 2; //using formula to compute how many edges each vertex needs for graph to be complete
        
        for (int i = 0; i < Vertices.length; i++) { //looping through elements in vertices array
            int currentVertexDegree = Vertices[i].getVertexDegree(); //assigns vertexDegree from Vertices array to variable
            allVertexDegrees.add(currentVertexDegree); //receiving the vertex degree from each vertex and storing it in arraylist
        }
        
        int k = Collections.max(allVertexDegrees); //gets highest degree from all degrees

        /*if ((numEdges % 2 == 0) && (numEdges != completeGraphEdges)) {//holds if number of edges is even AND the graph is NOT complete
            if (numEdges == 0) return 1; //when no edges but the graph is disconnected then the upperBound must equal 1
            //if ((k <= 3) && (k != 2)) return k + 1;  //makes upper
            return k; //if the conditions are met, then the upperBound is equal to the highest degree of all vertices (Brook's theorem)
        }*/

        int upperBound = k + 1; //chromatic number less than or equal to highest degree plus 1 for all graphs
        return upperBound;
    }
}
