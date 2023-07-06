package Algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Graph.Graph;
import Graph.Vertex;

/**
* The SpecialCases class is a Java class that determines if a certain graph belongs to a special case or not.
*
* @author Group 13
* @version 23/01/2023
*/

public class SpecialCases {

    Graph graph;
    int[][] adjacancyMatrix;
    int V;
    public int chromaticNumber;
    static final int UNCOLORED = 0;
    static final int RED = 1;
    static final int BLUE = 2;


    ArrayList<Integer> chromaticNumbers;
    ArrayList<Graph> subGraphs;

    /**
     * The constructor for the SpecialCases class
     * 
     * @param graph a Graph object that represents the graph to be analyzed
     */
    public SpecialCases(Graph graph){
        this.graph = graph;
        this.V = graph.getNumberOfVertices();
    }

    /**
     * The isSpecial method checks if the graph is one of the special cases and sets the chromatic number accordingly
     *
     * @return a boolean value indicating if the graph is a special case
     */
    public boolean isSpecial(){
        if(isEmptyGraph()){
            return true;
        }
        else if(isStarGraph()){
            return true;
        }
        else if(isCycleGraph()){
            return true;
        }
        else if(isPathGraph()){
            return true;
        }
        else if(isCompleteGraph()){
            return true;
        }
        else if(isBipartiteGraph()){
            return true;
        }
        else if(isRegularGraph()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * The isEmptyGraph method checks if the graph is an empty graph
     *
     * @return a boolean value indicating if the graph is an empty graph
     */
    public boolean isEmptyGraph(){
        if (graph.getNumberOfEdges() == 0 && graph.getNumberOfVertices() > 0) {
            chromaticNumber = 1;
            return true;
        }
        return false;
    }

    /**
     * The isStarGraph method checks if the graph is a star graph
     *
     * @return a boolean value indicating if the graph is a star graph
     */
    public boolean isStarGraph(){
        if (graph.getNumberOfVertices() == graph.getNumberOfEdges() + 1) {  //1st condition: number of vertices equal to number of edges + 1
            Vertex temp = graph.getHighestDegreeVertex();  
            if (temp.getVertexDegree() == graph.getNumberOfEdges()) { 
                chromaticNumber = 2;
                return true;
            }
        }
        return false;
    }

    /**
     * The isCycleGraph method checks if the graph is a cycle graph
     *
     * @return a boolean value indicating if the graph is a cycle graph
     */
    public boolean isCycleGraph(){
        Vertex initialVertex = new Vertex(1);
        int vertexDegree = initialVertex.getVertexDegree(); 

        if (graph.getNumberOfEdges() == graph.getNumberOfVertices()) {
            if(vertexDegree == 2) {
                for (int i = 1; i < graph.getVertices().length; i++){
                    Vertex temp = new Vertex(i);
                    if (2 == temp.getVertexDegree()){
                        continue;
                    } else {
                        break;
                    }
                }
            }
            if(V%2 == 0){
                chromaticNumber = 2;
            }
            else{
                chromaticNumber = 3;
            }
            return true;
        }
        return false;
    }

    /**
     * The isPathGraph method checks if the graph is a path graph
     *
     * @return a boolean value indicating if the graph is a path graph
     */
    public boolean isPathGraph(){
        if(graph.getNumberOfEdges() == (graph.getNumberOfVertices() - 1)) { 
                        Vertex initialVertex = new Vertex(1);
            int vertexDegree = initialVertex.getVertexDegree(); 
            if(vertexDegree == 1) {
                for (int i = 1; i < graph.getVertices().length; i++){
                    Vertex temp = new Vertex(i);
                    if (1 == temp.getVertexDegree()){
                        continue;
                    } else {
                        break;
                    }
                }
                chromaticNumber = graph.getNumberOfVertices();
                return true;
            }
        }
        return false;
    }

    /**
     * The isCompleteGraph method checks if the graph is a complete graph
     *
     * @return a boolean value indicating if the graph is a complete graph
     */
    public boolean isCompleteGraph(){
        if(graph.getNumberOfEdges() == (graph.getNumberOfVertices()*(graph.getNumberOfVertices()-1))/2) { 
            chromaticNumber = graph.getNumberOfVertices();
            return true;
        }
        return false;
    }

    /**
     * The isBipartiteGraph method checks if the graph is a bipartite graph
     *
     * @return a boolean value indicating if the graph is a bipartite graph
     */
    public boolean isBipartiteGraph(){
       return false;
    }

    /**
     * The isRegularGraph method checks if the graph is a regular graph
     *
     * @return a boolean value indicating if the graph is a regular graph
     */
     public boolean isRegularGraph(){
        Vertex initialVertex = new Vertex(1);
        int vertexDegree = initialVertex.getVertexDegree();
        
        if (initialVertex.getVertexDegree() > 2) {  //2nd condition : if the degree of all vertices is more than 2
            for (int i = 1; i < graph.getVertices().length ; i++) {
                Vertex temp = new Vertex(i);
                if (vertexDegree == temp.getAdjacentVertices().size()) { //1st condition: All Vertices have same degree
                  continue;
                } else break; 
            }
            chromaticNumber = vertexDegree + 1;
            return true;
        }
        
            //Chromatic Number = at least degree + 1
        return false;
    }

}
