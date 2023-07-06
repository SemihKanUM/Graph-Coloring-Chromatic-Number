package Algorithms;
import java.util.Random;

public class SimulateAnnealing {

    public static double colorGraph(int[][] adjacencyMatrix, int numColors, double initialTemperature, double coolingRate) {
        int n = adjacencyMatrix.length;
        // Create an array to store the coloring of each vertex
        int[] vertexColors = new int[n];

        // Initialize the coloring randomly
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            vertexColors[i] = rand.nextInt(numColors) + 1;
        }
        int invalidColorings = 0;
        // Start with an initial temperature
        double temperature = initialTemperature;

        // Repeat until the temperature has cooled down
        while (temperature > 1e-10) {
            // Pick a random vertex
            int vertex = rand.nextInt(n);

            // Save the current color of the vertex
            int currentColor = vertexColors[vertex];

            // Pick a new color for the vertex
            int newColor = rand.nextInt(numColors) + 1;

            // Check if the new color is valid for the vertex
            boolean isValidColoring = true;
            for (int adjVertex = 0; adjVertex < n; adjVertex++) {
                if (adjacencyMatrix[vertex][adjVertex] == 1 && vertexColors[adjVertex] == newColor) {
                    isValidColoring = false;
                    break;
                }
            }

            // Decide whether to accept the new color
            if (isValidColoring) {
                vertexColors[vertex] = newColor;
            } else {
                double delta = -1.0;
                double probability = Math.exp(delta / temperature);

                if (rand.nextDouble() < probability) {
                    vertexColors[vertex] = newColor;
                }
            }
            // Cool down the temperature
            temperature *= coolingRate;
        }
        // Check if the final coloring is valid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] == 1 && vertexColors[i] == vertexColors[j]) {
                    invalidColorings++;
                }
            }
        }
        // Calculate the error rate
        double error = (double) invalidColorings / (double) (n*(n-1));
        return error;
    }
}

