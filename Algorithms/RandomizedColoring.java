package Algorithms;

import java.util.Random;

public class RandomizedColoring {
    public static double colorGraph(int[][] adjacencyMatrix, int numColors, int numTrials) {
        int numVertices = adjacencyMatrix.length;
        int invalidColorings = 0;
        for (int t = 0; t < numTrials; t++) {
            // Create an array to store the coloring of each vertex
            int[] vertexColors = new int[numVertices];

            // Initialize the coloring randomly
            Random rand = new Random();
            for (int i = 0; i < numVertices; i++) {
                vertexColors[i] = rand.nextInt(numColors) + 1 ;
            }
            // Check if the current coloring is valid
            for (int i = 0; i < numVertices; i++) {
                for (int j = i; j < numVertices; j++) {
                    if (adjacencyMatrix[i][j] == 1 && vertexColors[i] == vertexColors[j]) {
                        invalidColorings++;
                    }
                }
            }
        }
        
        // Calculate the error rate
        double error = (double) invalidColorings / (double) numTrials;
        return error;
    }
}
