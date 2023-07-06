package Algorithms;

import java.util.Random;

import Graph.Graph;

/**
* The NewtonRaphson class is a Java class that uses the Newton-Raphson method 
* to find the optimal value of a certain parameter within a given range, 
* using a tolerance value as the stopping criterion.
*
* @author Group 13
* @version 23/01/2023
*/
public class NewtonRaphson {
    /**
     * An integer value representing the number of vertices in the graph.
     */
    int V;
    /**
     * A 2D integer array representing the graph.
     */
    int[][] graph;
    /**
     * An instance of the SimulateAnnealing class
     */
    SimulateAnnealing SA;
    /**
     * An instance of the GreedyAlgorithm class, which is used to color the graph.
     */
    public GreedyAlgorithm GA;
    /**
     * An integer array used for testing 
     */
    int[] test = new int[5];
    /**
     * An integer value representing the number of iterations
     */
    int numberOfIteration;
    /**
     * A boolean value representing whether the solution has been found or not
     */
    boolean solved = false;
    /**
     * An integer value representing the number of edges in the graph
     */
    int numberOfEdges;
    /**
     * A boolean value representing whether the answer has been found or not
     */
    boolean answerFound = false;
    /**
     * An integer value representing the answer
     */
    int answer;

    /**
     * Constructor for objects of class NewtonRaphson
     *
     * @param V an integer value representing the number of vertices in the graph.
     * @param graph a 2D integer array representing the graph.
     * @param numberOfEdges an integer value representing the number of edges in the graph.
     */
    public NewtonRaphson(int V, int[][] graph, int numberOfEdges){
        this.V = V;
        this.graph = graph;
        this.numberOfEdges = numberOfEdges;
        GA = new GreedyAlgorithm(V, graph);
    }

    /**
     * The search method uses the Newton-Raphson method to find the optimal value of a certain parameter
     *
     * @return an integer value representing the optimal value of the parameter
     */
    public int search(){
        double reference = 2;

        return (int)newtonEngine(reference);
    }

    /**
     * Method that implements the Newton-Raphson algorithm to find the root of a function.
     * @param numberOfColours  The input value for the function.
     * @return  The root of the function, as a double.
     */
    public double newtonEngine(double numberOfColours){
        if(answerFound){
            return answer;
        }

        if(numberOfColours - (int)numberOfColours > 0.5){
            numberOfColours = (int)numberOfColours+1;
        }

        double iterationResult = function(numberOfColours);

        if(iterationResult < 0.005){
            return numberOfColours;
        }
        else{
            return newtonEngine(numberOfColours - (iterationResult/derivativeFunction(numberOfColours)));
        }
    }

    /**
     * The function method calculates the value of the function for a given number of colors
     *
     * @param numberOfColours a double value representing the number of colors
     *
     * @return a double value representing the value of the function
     */
    public double function(double numberOfColours){
        double conflicts;

        if(numberOfColours < 0){
            return 1;
        }

        GA.setC((int)numberOfColours);
        conflicts = GA.colorGraph();

        if(conflicts == 0){
            answerFound = true;
            answer = (int)numberOfColours;
        }

        return conflicts/(double)numberOfEdges;
    }

    /**
     * A function that calculates the derivative of a given function at a certain point using finite differences method.
     * @param numberOfColours  The input value for the function.
     * @return  The derivative of the function at the given point, as a double.
     */
    public double derivativeFunction(double numberOfColours){
        double derivative;

        derivative = (function(numberOfColours+1.0) - function(numberOfColours-1.0))/2.0;
        return derivative;
    }
}
