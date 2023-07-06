package Algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Graph.Graph;
import Graph.Vertex;
    /**
* The BruteForce class is a Java class that uses brute force algorithm to find the optimal value of k 
* (minimum number of colors required to color the graph such that no two adjacent vertices have the same color)
*
* @author Group 13
* @version 23/01/2023
*/
public class BruteForce{
    /**
     * An instance of the graph
     */
    Graph graph;
    /**
     * An array of vertices
     */
    Vertex[] Vertices;

    /**
     * Constructor for objects of class BruteForce
     *
     * @param graph an instance of the graph
     */
    public BruteForce(Graph graph){
        this.graph = graph;
        Vertices = graph.getVertices();
    }

    /**
     * The search method uses brute force algorithm to find the optimal value of k 
     * (minimum number of colors required to color the graph such that no two adjacent vertices have the same color)
     *
     * @return an integer value representing the optimal value of k.
     */
    public int search(){

        int n = graph.getNumberOfVertices();

        for (int i = 0; i < Vertices.length + 1 ; i++) {
            int[] colors = new int[i+1];
            int[] coloring = new int[Vertices.length];

            for (int j = 0; j < coloring.length; j++) {
                coloring = draw(j,colors,graph.getAdjacencyMatrix(),coloring);
            }   

            int counter = 0;

            for (int j = 0; j < coloring.length; j++) {
                if(coloring[j] == 0){
                    counter++;
                }
            }
            if(counter == 0){
                return i;
            }
        }

        return n;    
    }

    /**
     * The draw method colors the vertex j with the first color that is not used by any of its adjacent vertices
     *
     * @param j an integer value representing the vertex to be colored
     * @param colors an array of integers representing the set of colors
     * @param graph a 2D integer array representing the graph's adjacency matrix
     * @param coloring an array of integers representing the coloring of the graph
     *
     * @return an array of integers representing the coloring of the graph
     */
    public int[] draw(int j, int[] colors , int[][] graph, int[] coloring){
        for (int i = 1; i < colors.length; i++) {
            int counter = 0;
            for (int k = 0; k < graph.length; k++) {
                if(graph[j][k] == 1){
                    if(coloring[k] == i){
                        counter++;
                    }
                }
            }
            if(counter == 0){
                coloring[j] = i;
            }
        }
        return coloring;
    }

    /**
     * The findColoring method finds the coloring of a graph using the brute force algorithm.
     *
     * @param graph a 2D integer array representing the graph's adjacency matrix
     * @param      * coloring an array of integers representing the coloring of the graph
     * @param k an integer value representing the number of colors
     *
     * @return a boolean value representing whether a valid coloring was found or not
     */
    private boolean findColoring(int[][] graph, int[] coloring, int k) {
        for (int v = 0; v < graph.length; v++) {
            if (!colorVertex(graph, coloring, k, v)) {
                return false;
            }
        }
        return true;
    }

    /**
     * The colorVertex method colors a vertex using the brute force algorithm.
     *
     * @param graph a 2D integer array representing the graph's adjacency matrix
     * @param coloring an array of integers representing the coloring of the graph
     * @param k an integer value representing the number of colors
     * @param v an integer value representing the vertex to be colored
     *
     * @return a boolean value representing whether the vertex was successfully colored or not
     */
    private static boolean colorVertex(int[][] graph, int[] coloring, int k, int v) {
        boolean[] takenColors = new boolean[k];

        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && coloring[i] != -1) {
                takenColors[coloring[i]] = true;
            }
        }

        for (int c = 0; c < k; c++) {
            if (!takenColors[c]) {
                coloring[v] = c;
                return true;
            }
        }
        return false;
    }
}
