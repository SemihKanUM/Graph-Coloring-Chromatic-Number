package Graph;

import java.util.ArrayList;
import java.awt.Color;


/**
 * The Vertex class represents a vertex in a graph, with a unique ID and a list of adjacent vertices.
 * It also has a degree, color ID, label, and coordinates for drawing the graph.
 */
public class Vertex {
  
    private int VertexID; //Every Vertex has unique integer ID.
    private int VertexDegree; // Equal to length of AdjacentVertices Matrix.
    private int ColorID; //It will be zero as default. Zero means no color.
    private int label = 0;
    private ArrayList<Vertex> AdjacentVertices; //Contains adjacent Vertices.
    private ReadGraph test;
    public final int  RADIUS = 20;
    public Color color;
    public double xCoordinate = -1; // minus 1 as default
    public double yCoordinate = -1;

    /**
     * Constructs a vertex object with a given ID
     * @param VertexID the unique ID of the vertex
     */
    public Vertex(int VertexID){
        this.VertexID = VertexID;
        AdjacentVertices = new ArrayList<>();       
        color = Color.WHITE;
    }

    /**
     * Returns the label of the vertex
     * @return the label of the vertex
     */
    public int getLabel() {
        return label;
    }

    /**
     * Sets the coordinates of the vertex
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCoordinates(double x, double y){
        xCoordinate = x;
        yCoordinate =y;
    }

    /**
     * Sets the label of the vertex
     * @param label the label to set
     */
    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * Sets the ReadGraph object associated with the vertex
     * @param test the ReadGraph object
     */
    public void setReadGraph(ReadGraph test){
        this.test = test;
    }

    /**
     * Sets the color ID of the vertex
     * @param ColorID the color ID to set
     */
    public void setColorID(int ColorID){
        this.ColorID = ColorID;
    }

    /**
     * Sets the color of the vertex
     * @param color the color to set
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Returns the color ID of the vertex
     * @return the color ID of the vertex
     */
    public int getColorID(){
        return ColorID;
    }

    /**
     * Returns the color of the vertex
     * @return the color of the vertex
     */
    public Color getColor(){
        return color;
    }

    /**
     * Transforms the color ID of the vertex into a color
     */
    public void intToColorTranformer(){
        //TO DO
    }
    
    /**
     * Adds a vertex to the list of adjacent vertices
     * @param x the vertex to add
     */
    public void addVertex(Vertex x){
        AdjacentVertices.add(x);
    }

       /**
     *  Returns the list of adjacent vertices of the current vertex
     *  @return ArrayList of Vertex objects representing the adjacent vertices of the current vertex
     */
    public ArrayList<Vertex> getAdjacentVertices(){
        return AdjacentVertices;
    }

    /**
     * Returns the ids of the adjacent vertices of the current vertex
     * @return int array of ids of the adjacent vertices of the current vertex
     */
    public int[] getAdjacentVerticesID(){
        int[] adjacent = new int[AdjacentVertices.size()];

        for (int i = 0; i < AdjacentVertices.size(); i++) {
            adjacent[i] = AdjacentVertices.get(i).VertexID;
        }

        return adjacent;
    }

    /**
     * Checks if the provided vertex is adjacent to the current vertex
     * @param vertex, the vertex to check for adjacency
     * @return boolean indicating if the provided vertex is adjacent to the current vertex
     */
    public boolean adjacentOrNot(Vertex vertex){
        for (int i = 0; i < AdjacentVertices.size(); i++) {
            if(AdjacentVertices.get(i).getVertexID() == vertex.getVertexID()){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the degree of the current vertex
     * @return int representing the degree of the current vertex
     */
    public int getVertexDegree(){
        return AdjacentVertices.size();
    }

    /**
     * Returns the id of the current vertex
     * @return int representing the id of the current vertex
     */
    public int getVertexID(){
        return VertexID;
    }
    }
