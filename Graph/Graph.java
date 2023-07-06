package Graph;

import Algorithms.Bisection;
import Algorithms.NewtonRaphson;
import Algorithms.SpecialCases;

/**
 * The Graph class represents a graph and its properties.
 * It contains an array of Vertex objects, an adjacency matrix, 
 * the number of vertices and edges, the chromatic number, 
 * and the upper and lower bounds of the graph.
 */
public class Graph {

    private Vertex[] Vertices; // Contains all the Vertices object of graph.
    private int numberOfVertices;
    private int[][] adjacencyMatrix; //Two dimensinoal matrix for edges.
    private int numberOfEdges;
    public int chromaticNumber;
    public int UpperBound;
    public int LowerBound;

     /**
     * Constructs a new graph with a given array of Vertex objects.
     * @param Vertices the array of Vertex objects representing the graph
     */
    public Graph(Vertex[] Vertices){
        this.Vertices = Vertices;
        this.numberOfVertices = Vertices.length;
    }
    /**
     * Determines if the graph is special and sets the chromatic number, 
     * lower bound, and upper bound accordingly.
     * @return true if the graph is special, false otherwise
     */
    public boolean isSpecial(){
        SpecialCases SC = new SpecialCases(this);
        if(SC.isSpecial()){
            chromaticNumber = SC.chromaticNumber;
            LowerBound = SC.chromaticNumber;
            UpperBound = SC.chromaticNumber;
            return true;
        }
        return false;
    }


    /**
     * Finds the lower bound and upper bound of the graph using NewtonRaphson and Bisection methods.
     */
    public void findBounds(){
        NewtonRaphson nw = new NewtonRaphson(Vertices.length, adjacencyMatrix, numberOfEdges);
        LowerBound = nw.search();
        
        Bisection bis = new Bisection(Vertices.length, adjacencyMatrix);
        UpperBound = bis.search(1, Vertices.length, 2);
       

        if(UpperBound == LowerBound){
            chromaticNumber = UpperBound;
        }  
        else if(UpperBound < LowerBound){
            LowerBound = UpperBound-1;
        }
        chromaticNumber = LowerBound;
    }

    /**
     * A method that sets the color of all vertices in the graph to 0.
     *
     */
    public void clear(){
        for (int i = 0; i < Vertices.length; i++) {
            Vertices[i].setColorID(0);
        }
    }

   /**
 * Sets the adjacency matrix of the graph.
 * 
 * @param adjacencyMatrix The adjacency matrix of the graph.
 */
public void setAdjacancyMatrix(int[][] adjacencyMatrix){
    this.adjacencyMatrix = adjacencyMatrix;
}

/**
 * Returns the vertex with the highest degree in the graph.
 * 
 * @return The vertex with the highest degree in the graph.
 */
public Vertex getHighestDegreeVertex(){
    int highestDegree = 0;
    Vertex highestVertex = null;
    for (int i = 0; i < Vertices.length; i++) {
        if(Vertices[i].getAdjacentVertices().size() > highestDegree){
            highestDegree = Vertices[i].getAdjacentVertices().size();
            highestVertex = Vertices[i];
        }
    }
    return highestVertex;
}

/**
 * Returns the chromatic number of the graph.
 * 
 * @return The chromatic number of the graph.
 */
public int getChromaticNumber(){
    return chromaticNumber;
}

/**
 * Returns the adjacency matrix of the graph.
 * 
 * @return The adjacency matrix of the graph.
 */
public int[][] getAdjacencyMatrix(){
    return adjacencyMatrix;
}

/**
 * Returns the number of vertices in the graph.
 * 
 * @return The number of vertices in the graph.
 */
public int getNumberOfVertices(){
    return Vertices.length;
}

/**
 * Sets the number of edges in the graph.
 * 
 * @param numberOfEdges The number of edges in the graph.
 */
public void setNumberOfEdges(int numberOfEdges){
    this.numberOfEdges = numberOfEdges;
}

/**
 * Returns the number of edges in the graph.
 * 
 * @return The number of edges in the graph.
 */
public int getNumberOfEdges(){
    return numberOfEdges;
}

/**
 * Returns the array of vertices in the graph.
 * 
 * @return The array of vertices in the graph.
 */
public Vertex[] getVertices(){
    return Vertices;
}

/**
 * Sets the chromatic number of the graph.
 * 
 * @param chromaticNumber The chromatic number of the graph.
 */
public void setChromaticNumber(int chromaticNumber){
    this.chromaticNumber = chromaticNumber;
}


 public int cliqueFinder(int n , int numberOfVertices){ // n = side length of square, for the first iteration it is equal to numberOfVertices

        // To Do
        return 0;
    }
    
}
