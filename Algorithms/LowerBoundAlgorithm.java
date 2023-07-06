package Algorithms;

import java.util.ArrayList;
import java.util.Comparator;

import Graph.Graph;
import Graph.Vertex;
public class LowerBoundAlgorithm {

    private Vertex[] Vertices;
    private Graph graph;
    private  int lowerBound;

    public LowerBoundAlgorithm(Graph graph) {
        this.graph = graph;
        this.Vertices = graph.getVertices();
    }

    public int search() {

        ArrayList<Vertex> sortedList = new ArrayList<>(); //ArrayList for ease of manipulation.

        for (int i = 0; i < Vertices.length; i++) { //This loop simply adds the elements of the Vertices array to the ArrayList for the moment as Vertex objects./
            sortedList.add(Vertices[i]);
        }

        sortedList.sort(Comparator.comparing(Vertex::getVertexDegree)); //Sorts elements in regards to their degree number in increasing order/

        boolean isClique = false; /*2 boolean variables are needed for this nested loop structure, one for whether what we have is a clique...*/



        while (!isClique) {

            boolean hasEnoughNeighbours = true; /* /...and one for whether a vertex is a part of a clique of not, if its not, the vertex will be removed and this will be set to false. 
            After every iteration this variable is returned to being true */

            for (Vertex vertex : sortedList) {

                if (vertex.getAdjacentVertices().size() != sortedList.size() - 1) { //if a vertex does not meet this criteria it gets completely erased from the array/

                    ArrayList<Vertex> neighbours = vertex.getAdjacentVertices(); /*in order for a vertice to be completely erased it, its edges have to also be removed */
                    for (Vertex vertex2 : neighbours) {                          /*this loop removes the vertex from all adjecent lists that it is in*/
                        vertex2.getAdjacentVertices().remove(vertex);
                    }

                    sortedList.remove(vertex); /*removes the vertex itself from the list of vertices*/

                    hasEnoughNeighbours = false; /*this variable needs to be set to false so that the loop can continue iterating*/
                    sortedList.sort(Comparator.comparing(Vertex::getVertexDegree)); /*list has to be sorted after every removal of a vertex */
                    break;
                }

            }

            if (!hasEnoughNeighbours) {
                continue;
            }

            isClique = true;

        }


        return sortedList.size();
    }
}
