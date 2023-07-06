package Algorithms;

/**
* The Bisection class is a Java class that uses the bisection method 
* to find the optimal value of a certain parameter within a given range, 
* using a tolerance value as the stopping criterion.
*
* @author Group 13
* @version 20/1/2023
*/
public class Bisection {
    /**
     * An integer value representing the number of vertices in the graph.
     */
    int V;
    /**
     * A 2D integer array representing the graph.
     */
    int[][] graph;
    /**
     * An instance of the GreedyAlgorithm class, which is used to color the graph.
     */
    GreedyAlgorithm GA;     
    /**
     * An integer value representing the number of iterations.
     */
    int numberOfIteration;

    /**
     * Constructor for objects of class Bisection
     *
     * @param V an integer value representing the number of vertices in the graph.
     * @param graph a 2D integer array representing the graph.
     */
    public Bisection(int V, int[][] graph){
        // initialize the GreedyAlgorithm object with the number of vertices and the graph
        GA = new GreedyAlgorithm(V, graph);
    }

    /**
     * The search method uses the bisection method to find the optimal value of the parameter "C"
     * within the range [a, b], using the GreedyAlgorithm class to color the graph and the tolerance value as the stopping criterion.
     *
     * @param a an integer value representing the lower bound of the range.
     * @param b an integer value representing the upper bound of the range.
     * @param tolerance an integer value representing the stopping criterion.
     *
     * @return an integer value representing the optimal value of the parameter "C".
     */
    public int search(int a, int b, int tolerance) {
        // set the number of colors to the lower bound and color the graph
        GA.setC(a);
        int fa = GA.colorGraph();

        // set the number of colors to the upper bound and color the graph
        GA.setC(b);
        int fb = GA.colorGraph();

        // calculate the midpoint of the search range
        int c = (a + b) / 2;

        // set the number of colors to the midpoint and color the graph
        GA.setC(c);
        int fc = GA.colorGraph();

        // continue bisecting the range until the accuracy is reached
        while (Math.abs(b - a) > tolerance) {
            if (fa * fc == 0) {
                // if the lower bound or the midpoint yields a valid result, set the upper bound to the midpoint
                b = c;
                fb = fc;
            } else {
                // if the upper bound or the midpoint yields a valid result, set the lower bound to the midpoint
                a = c;
                fa = fc;
            }
            // calculate the new midpoint
            c = (a + b) / 2;

            // set the number of colors to the new midpoint and color the graph
            GA.setC(c);
            fc = GA.colorGraph();
        }

        // return the number of colors needed to color the graph
        return b;
    }
}

