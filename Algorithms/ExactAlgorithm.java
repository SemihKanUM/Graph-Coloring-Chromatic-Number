package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import Graph.Graph;
import Graph.Vertex;

public class ExactAlgorithm {
    
    private Graph graph;
    private ArrayList<int[]> colors = new ArrayList<>();
    private int[] degreeOrder;
    private Vertex[] Vertices;
    private int chromaticNumber;
    
    public ExactAlgorithm(Graph graph){ //Takes graph object as parameter and extracts the Vertex[] Vertices array from it.
        this.graph = graph;
        this.Vertices = graph.getVertices(); 
    }

    public int search(){ //Returns the exact chromatic number using the two methods created below.
        if(Vertices.length == 0 ){
            return 0;
        }

        
        return chromaticNumber = chromaticNumberGenerator(orderIndexesByDegrees(Vertices));
    }

    public int[] orderIndexesByDegrees(Vertex[] Vertices) { //Returns an integer array of VertexIDs sorted in descending order of degrees. 

        ArrayList<Vertex> sortedList = new ArrayList<>(); //ArrayList for ease of manipulation.

        for (int i = 0; i < Vertices.length; i++) { /*This loop simply adds the elements of the Vertices array to the ArrayList,
            for the moment as Vertex objects.  */
            sortedList.add(Vertices[i]);
        }

        sortedList.sort(Comparator.comparing(Vertex::getVertexDegree).reversed()); /*This sort function takes a Comparator as input. The pre-defined
        comparing() method compares a given input class from the List, in this case Vertex, by a given attribute, in this case the VertexDegree and 
        then the sort function sorts them according to the attribute. However, this by default gives an ascending order, so we use reversed() to get descending order.*/

        int[] indexes = new int[sortedList.size()]; //We define a new int array to contain just the VertexIDs of the objects in the ArrayList.

        for (int i = 0; i < indexes.length; i++) { //Simply retrieves the VertexID of the index in the ArrayList and puts it in the same index in the int[] array.
            indexes[i] = sortedList.get(i).getVertexID();
        }

        return indexes; 
    }

    public int chromaticNumberGenerator(int[] degreeArray) { //Returns chromatic number by taking the sorted int[] array above as input.

        int[] colors = new int[degreeArray.length]; //int array to store whether each vertex in our sorted array is colored or not. Originally contains all 0's, meaning no colors.
        int colorId = 1; 
        
        while (true) { //While loop so that it continues looping through the colors[] array until it contains no more 0's.
            for (int i = 0; i < degreeArray.length; i++) {
             
                if (drawable(Vertices[degreeArray[i] - 1], colorId, colors, degreeArray)) {/*Passes our defined parameters to the drawable() method, checks if returns true. 
                    Since that method takes a vertex object as input, we use our sortedArray to return the Vertex object at that index in the Vertices array. 
                    We need to use degreeArray[i] - 1 as the index bcz the degreeArray values start at 1 (since they're VertexIDs) & arrays start at 0. */
                    Vertices[degreeArray[i] - 1].setColorID(colorId); //Sets the ColorID attribute of the VertexID 
                    colors[i] = colorId; //Updates our colors array
                }
            }

            int numColoredVertices = 0;
            for (int i = 0; i < colors.length; i++) { //Increments a if an only if it detects a value greater than 0.
                if(colors[i] > 0){
                    numColoredVertices++;
                }   
            }
            if(numColoredVertices == colors.length){ //a can only be equal to the length of the colors array if it incremented with every element, i.e, every element had a value greater than 0.
                break;
            }

            colorId++; //colorId is incremented outside the for loop so that it only changes when every vertice that can be colored by a certain color, say 1, has been colored. 
        }
        
        return colorId;
    }


    public boolean drawable(Vertex vertex, int colorId, int[] colors, int[] degreeArray) { //Checks whether a given element in the colors array is colorable or not.

        if(vertex.getColorID() != 0){ //If the element already has a color, i.e its value is not 0, then it is obviously not colorable.
            return false;
        }

        for (int i = 0; i < colors.length; i++) { //This loop checks whether a given vertex adjacent to any already colored vertex. If it is, it can't be colored.
            if(colors[i] == colorId){
                if(Vertices[degreeArray[i]-1].adjacentOrNot(vertex)){
                    return false;
                }
            }
        }
        return true; //Only if the above conditions are not met is it colorable.
    }

    public ArrayList<ArrayList<Vertex>> collectSameVertices(){

        search();

        ArrayList<ArrayList<Vertex>> sameColors = new ArrayList<>();

        for (int i = 1; i <= chromaticNumber; i++) {
            ArrayList<Vertex> sameColorVertices = new ArrayList<>();
            for (int j = 0; j < Vertices.length; j++) {
                if(Vertices[i].getColorID() == i){
                    sameColorVertices.add(Vertices[i]);
                }    
            }
            sameColors.add(sameColorVertices);
        }    
        return sameColors;    
    }

}