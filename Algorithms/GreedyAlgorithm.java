package Algorithms;

import java.util.Arrays;

public class GreedyAlgorithm {

    
    int V;
    
    int C;
    
    int[][] graph;
   
    int[] colors;
   
    boolean[] available;

    /**
     * Constructor for the GreedyAlgorithm class
     * @param V The number of vertices in the graph
     * @param graph The graph represented as an adjacency matrix
     */
    public GreedyAlgorithm(int V, int[][] graph) {
        this.V = V;
        this.graph = graph;
        this.colors = new int[V];
    }

    /**
     * Method to set the number of colors used in the graph coloring problem
     * @param C The number of colors to be used
     */
    public void setC(int C){
        this.C = C;
        this.available = new boolean[C+1];
        this.colors = new int[V];
    }

    /**
     * Method to color the graph using the greedy algorithm
     * @return The number of errors in the graph coloring
     */
    public int colorGraph() {
                
        for (int i = 0; i < V; i++) {
            
            Arrays.fill(available, true);

        
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 1) {
                    available[colors[j]] = false;
                }
            }

 
            int c;
            for (c = 1; c < C+1; c++) {
                if (available[c]) {
                    colors[i] = c;
                    break;
                }
            }
        }


        // calculate the error of the coloring
        
        int error = 0;
        for (int i = 0; i < colors.length; i++) {
            if(colors[i] == 0){
                error++;
            }
        }
        

       return error;
    }
}