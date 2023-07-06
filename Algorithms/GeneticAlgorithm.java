package Algorithms;

import java.util.Random;

public class GeneticAlgorithm {
    // Number of vertices in the graph
    int V;
    // Number of colors
    int C;
    // Adjacency matrix representation of the graph
    int[][] graph;
    // Population of color assignments
    int[][] population;
    // Random number generator
    Random rnd = new Random();

    public GeneticAlgorithm(int V, int C, int[][] graph) {
        this.V = V;
        this.C = C;
        this.graph = graph;
        this.population = new int[V][C];
    }

    public void colorGraph() {
        // Initialize the population randomly
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < C; j++) {
                population[i][j] = rnd.nextInt(2);
            }
        }
        
        // Genetic algorithm parameters
        int populationSize = V;
        double mutationRate = 0.01;
        int maxGenerations = 1000;
        
        // Start the main loop
        for (int generation = 0; generation < maxGenerations; generation++) {
            // Evaluate the fitness of the population
            int[] fitness = evaluateFitness();

            // Select parents for crossover
            int[][] parents = selection(fitness);

            // Perform crossover
            int[][] offspring = crossover(parents);

            // Perform mutation
            mutation(offspring, mutationRate);

            // Replace the population with the offspring
            population = offspring;
        }

        // Decode the binary population to the final color assignment
        int[] colors = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < C; j++) {
                if (population[i][j]== 1) {
                    colors[i] = j;
                    break;
                }
            }
        }

        // print the final color assignment
        for (int i = 0; i < V; i++) {
            System.out.print(colors[i] + " ");
        }

        // calculate the error of the coloring
        int error = 0;
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (graph[i][j] == 1 && colors[i] == colors[j]) {
                    error++;
                }
            }
        }
        System.out.println("Error: " + error);
    }

    private int[] evaluateFitness() {
        // Array to store the fitness of each individual
        int[] fitness = new int[V];

        // Calculate the fitness of each individual
        for (int i = 0; i < V; i++) {
            int color1 = 0;
            for (int j = 0; j < C; j++) {
                if (population[i][j] == 1) {
                    color1 = j;
                    break;
                }
            }
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 1) {
                    int color2 = 0;
                    for (int k = 0; k < C; k++) {
                        if (population[j][k] == 1) {
                            color2 = k;
                            break;
                        }
                    }
                    if (color1 == color2) {
                        fitness[i]++;
                    }
                }
            }
        }
        return fitness;
    }

    private int[][] selection(int[] fitness) {
        // Array to store the parents
        int[][] parents = new int[V][C];

        // Select the parents
        for (int i = 0; i < V; i++) {
            // Select the first parent
            int parent1 = rnd.nextInt(V);
            while (rnd.nextDouble() > (double) fitness[parent1]/ (double) V) {
                parent1 = rnd.nextInt(V);
            }
            // Select the second parent
            int parent2 = rnd.nextInt(V);
            while (rnd.nextDouble() > (double) fitness[parent2] / (double) V) {
                parent2 = rnd.nextInt(V);
            }

            // Add the parents to the array
            parents[i] = population[parent1];
            parents[++i] = population[parent2];
        }
        return parents;
    }

    private int[][] crossover(int[][] parents) {
        // Array to store the offspring
        int[][] offspring = new int[V][C];

        // Perform crossover
        for (int i = 0; i < V; i++) {
            int parent1 = rnd.nextInt(V);
            int parent2 = rnd.nextInt(V);
            int crossoverPoint = rnd.nextInt(C);
            for (int j = 0; j < crossoverPoint; j++) {
                offspring[i][j] = parents[parent1][j];
            }
            for (int j = crossoverPoint; j < C; j++) {
                offspring[i][j] = parents[parent2][j];
            }
        }
        return offspring;
    }

    private void mutation(int[][] offspring, double mutationRate) {
        // Perform mutation
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < C; j++) {
                if (rnd.nextDouble() < mutationRate) {
                    offspring[i][j]= (offspring[i][j] == 1 ? 0 : 1);
                }
            }
        }
    }

}
