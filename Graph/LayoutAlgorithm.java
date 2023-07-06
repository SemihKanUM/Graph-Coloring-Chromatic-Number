package Graph;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LayoutAlgorithm {

    private Graph graph;
    
    public LayoutAlgorithm(Graph graph){
        this.graph = graph;
    }

    public void setCoordinates(){ //Sets the X and Y coordinates for the vertices such they're not in the same vertical and horizontal line.

        int side = 2*graph.getNumberOfVertices();
        ArrayList<Integer> usedNumbersX = new ArrayList<>();
        ArrayList<Integer> usedNumbersY = new ArrayList<>();


        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            while(true){
                int randomNumX = ThreadLocalRandom.current().nextInt(1, side + 1);
                int randomNumY = ThreadLocalRandom.current().nextInt(1, side + 1);
                if(usedNumbersX.contains(randomNumX)|| usedNumbersY.contains(randomNumY)){
                    continue;
                }
                else{
                    double randomX = ThreadLocalRandom.current().nextDouble(20*randomNumX,20*randomNumX+20);
                    double randomY = ThreadLocalRandom.current().nextDouble(20*randomNumY,20*randomNumY+20);
                    graph.getVertices()[i].setCoordinates(randomX,randomY);
                    usedNumbersX.add(randomNumX);
                    usedNumbersY.add(randomNumY);
                    break;
                }
            }
        }
    }
}