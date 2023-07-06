package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import Algorithms.UpperBoundAlgorithm;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Graph.Graph;
import Graph.Launch;
import Graph.ReadGraph;
import Graph.Vertex;
import java.awt.BorderLayout;


public class Board extends JPanel  {
    public Graph graph;
    private Timer timer;
    public ReadGraph readGraph = new ReadGraph();
    public String GameMode;
    public int numberVertex;
    public int numberEdges;
    public String graphSourcePath;
    public boolean sourceOrNot;
    public Launch launch;
    public Game game;
    public ArrayList<Color> usedColours;
    public ArrayList<Integer> usedColoursInt;

    public Board(){
        super(new BorderLayout());
    }

    /**
    * The setup method sets up the basic layout of the game and initializes the necessary variables for the game to function properly.
    * It sets the layout of the game to be a GridLayout and initializes the graph variable to be the graph launched by the launch object.
    * It also creates a new ArrayList for the usedColours variable and sets up key bindings for the game using the setUpKeyBindings method.
    * A TAdapter object is created and added as a mouse listener to the game, and the focus is set to be on the game.
    * The gameInit method is then called to start the game.
    */
    public void setup(){
        setLayout(new GridLayout());
        this.graph = launch.getGraph();
        usedColours = new ArrayList<>();

        setUpKeyBindings();

        TAdapter adapter =  new TAdapter();
        
        setFocusable(true);
        
        addMouseListener(adapter);

        setFocusable(true);
        gameInit();
    } 

    /**
    * The gameInit method initializes the game by creating a new Timer object with a delay of 100 milliseconds.
    * The GameCycle class is passed as a parameter to the Timer object's constructor and is used as the ActionListener
    * for the timer. The timer is then started.
    */
    private void gameInit(){
        timer = new Timer(100, new GameCycle()); //This uses the do every 100ms.
        timer.start();
    }

    /**
    * The doDrawing method is responsible for drawing the graph on the screen.
    * It takes in a Graphics object as a parameter and converts it to a Graphics2D object.
    * The method then sets up rendering hints for antialiasing and high quality rendering.
    * The method then iterates through the adjacency matrix of the graph, and for each edge that exists, it draws a line between the two vertices.
    * The method then iterates through the vertices of the graph, and for each vertex, it sets the color of the vertex and draws an ellipse at the vertex's coordinates.
    * The method also draws the vertex's ID next to the vertex.
    */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);


        for(int i = 0; i < graph.getAdjacencyMatrix().length;i++){
            for (int j = i; j < graph.getAdjacencyMatrix()[0].length; j++) {
                if(graph.getAdjacencyMatrix()[i][j] == 1){
                    Vertex vertex1 = graph.getVertices()[i];
                    Vertex vertex2 = graph.getVertices()[j];
                    g2d.setPaint(Color.BLACK); 
                    g2d.drawLine((int)vertex1.xCoordinate, (int)vertex1.yCoordinate, (int)vertex2.xCoordinate, (int)vertex2.yCoordinate);
                }
            }
        }

        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            Vertex vertex = graph.getVertices()[i];
            g2d.setPaint(vertex.color);
            g2d.fill(new Ellipse2D.Double(vertex.xCoordinate-10,vertex.yCoordinate-10, 20, 20));
            g2d.setPaint(Color.BLACK);
            g.drawString(String.valueOf(vertex.getVertexID()), (int)(vertex.xCoordinate)-3, (int)(vertex.yCoordinate)+4);
        }
   } 

    /**
     * The paintComponent method is overridden from the JPanel class and is called whenever the panel needs to be repainted.
     * In this method, the super class's paintComponent method is called and then the doDrawing method is called to draw the graph.
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }   
    
    /**
     * The TAdapter class extends the MouseAdapter class and overrides the mouseClicked method.
     * This method is called when the mouse is clicked on the panel and it can be used to handle any desired behavior for a mouse click event.
    */
    private class TAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

    /**
     * The setUpKeyBindings method sets up key bindings for the game using the InputMap and ActionMap classes.
     * It creates InputMap and ActionMap objects and assigns them to the current focused window.
     * It then creates KeyStroke objects for different keys (VK_S, VK_W, VK_1, VK_2, VK_3, VK_4) and assigns
     * them to their corresponding input map and action map. It also creates WAction1, WAction2, WAction3,
     * WAction4, WAction5, WAction6 objects which are used to perform the desired action when the corresponding
     * key is pressed.
     */
    private void setUpKeyBindings() {
        int condition = WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
        ActionMap actionMap = getActionMap();
        
  
        KeyStroke wStroke1 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        inputMap.put(wStroke1, wStroke1.toString());
        actionMap.put(wStroke1.toString(), new WAction1());

        KeyStroke wStroke2 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        inputMap.put(wStroke2, wStroke2.toString());
        actionMap.put(wStroke2.toString(), new WAction2());

        KeyStroke wStroke3 = KeyStroke.getKeyStroke(KeyEvent.VK_1, 0);
        inputMap.put(wStroke3, wStroke3.toString());
        actionMap.put(wStroke3.toString(), new WAction3());

        KeyStroke wStroke4 = KeyStroke.getKeyStroke(KeyEvent.VK_2, 0);
        inputMap.put(wStroke4, wStroke4.toString());
        actionMap.put(wStroke4.toString(), new WAction4());

        KeyStroke wStroke5 = KeyStroke.getKeyStroke(KeyEvent.VK_3, 0);
        inputMap.put(wStroke5, wStroke5.toString());
        actionMap.put(wStroke5.toString(), new WAction5());

        KeyStroke wStroke6 = KeyStroke.getKeyStroke(KeyEvent.VK_4, 0);
        inputMap.put(wStroke6, wStroke6.toString());
        actionMap.put(wStroke6.toString(), new WAction6());
     }

    /**
     * The WAction1 class is an inner class that extends the AbstractAction class.
     * It overrides the actionPerformed method to handle the specific action that should occur
     * when the associated key binding is triggered.
     * In this case, the action is to reduce the x and y coordinates of each vertex in the graph by 60%.
     */
     private class WAction1 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
        /**
         * In this method, it will loop through each vertex in the graph and sets the coordinates of each vertex
         * to be 60% of the original coordinates, thus reducing the size of the graph
        */
            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate*0.6, tempVertex.yCoordinate*0.6);
            }
        }
     }

    /**
     * The WAction2 class extends the AbstractAction class and overrides its actionPerformed method.
     * The actionPerformed method is called when the corresponding keybinding associated with this action is triggered.
     * In this case, the method divides the x and y coordinates of each vertex in the graph by 0.6.
    */
     private class WAction2 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate/0.6, tempVertex.yCoordinate/0.6);
            }
        }
     }

    /**
     * The WAction3 class is an inner class that extends the AbstractAction class and is used to move the vertices of the graph
     * upwards when the key corresponding to the action is pressed.
     * It overrides the actionPerformed method which is called when the action is performed.
     * In the actionPerformed method, a for loop iterates through all the vertices in the graph and changes their y-coordinate
     * by adding 20 to it. This results in all the vertices moving upwards by 20 units on the y-axis.
     */
     private class WAction3 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate, tempVertex.yCoordinate+20);
            }
        }
     }

    /**
     * The WAction4 class is an inner class that extends the AbstractAction class.
     * It overrides the actionPerformed method to move the vertices of the graph up by 20 pixels.
     * The method loops through all the vertices of the graph and updates their y-coordinate
     * by subtracting 20 from it.
     */
     private class WAction4 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate, tempVertex.yCoordinate-20);
            }
        }
     }

    /**
     * The WAction5 class is an extension of the AbstractAction class. It overrides the actionPerformed method
     * to move the coordinates of each vertex in the graph to the left by 20 units.
     * For each vertex in the graph, it gets the current coordinates of the vertex,
     * and sets the x-coordinate to be 20 units less than the current x-coordinate.
     * The y-coordinate remains unchanged.
     */
     private class WAction5 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate - 20, tempVertex.yCoordinate);
            }
        }
     }

    /**
     * The WAction6 class is an inner class that extends the AbstractAction class.
     * It overrides the actionPerformed method to perform a specific action when the corresponding keybinding is triggered.
     * In this case, when the keybinding associated with this class is triggered, the coordinates of all the vertices in the graph will be shifted 20 units to the right.
     * This is achieved by iterating through the vertices of the graph and updating the xCoordinate value of each vertex.
     */
     private class WAction6 extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < graph.getNumberOfVertices(); i++) {
                Vertex tempVertex = graph.getVertices()[i];
                graph.getVertices()[i].setCoordinates(tempVertex.xCoordinate + 20, tempVertex.yCoordinate);
            }
        }
     }

    /**
     * The updateColors method updates the list of used colours in the game by looping through all vertices in the graph and adding their
     * colours to the usedColours ArrayList if they are not already present in the list, without duplicates. It also checks for any colours in the
     * usedColours ArrayList that are not being used by any vertex in the graph, and removes them from the list.
     */
    public void updateColors() {
        //this loop adds colours to the arraylist without duplicates
        for (int i = 0; i < graph.getNumberOfVertices(); i++) { //looping through all vertices, receiving numOfVertices from graph
            Vertex vertex = graph.getVertices()[i]; //creating vertex to work with from vertices array

            if (vertex.getColor() != Color.WHITE) { //if the colour is not default colour then our actual code starts comparing to colours stored

                if (usedColours.size() == 0) { //if arraylist empty
                    usedColours.add(vertex.getColor()); //logically we have to add the first colour

                    //System.out.println("first colour:" + usedColours.get(0) + "\n"); //for testing purposes - prints first colour of arraylist in terminal

                    break; //more efficient to break loop immediately and check next vertex
                }

                for (int k = 0; k < usedColours.size(); k++) { //loop through arraylist
                    if (vertex.getColor() == usedColours.get(k)) { //if vertex colour equals colour of arraylist
                        break; //loop breaks and next vertex will be checked
                    } 
                    if (k == usedColours.size() - 1) { //if in the last iteration of the loop, the loop did not break - it means the colour does not exist in the arraylist yet
                        usedColours.add(vertex.getColor()); //so we add it

                        // System.out.println("colour added:" + usedColours.get(usedColours.size()-1) + "\n"); //for testing to print colours used, after first one, in terminal - DON'T FORGET in previous if statement is another one, for the first colour added
                        // System.out.println("whole arraylist:"); //prints whole arraylist
                        // for(int f = 0; f < usedColours.size(); f++) {   
                        //     System.out.print(usedColours.get(f));
                        // }  
                        // System.out.println();
                    }             
                }
            }
        }
       
        // this loop erases colours from arraylist if the colour is not used on any vertex
        for (int k = 0; k < usedColours.size(); k++) { //loop through arraylist
            //compare colour k to all vertices, if no vertex equals that colour, then delete that colour from arraylist
            for (int i = 0; i < graph.getNumberOfVertices(); i++) { //looping through all vertices, receiving numOfVertices from graph
                Vertex vertex = graph.getVertices()[i]; //creating vertex to work with from vertices array
                if (vertex.getColor() == usedColours.get(k)) { //if one vertex has the same colour, then it does not need to be deleted
                    break; //and the next colour will be checked after the break
                }

                if (i == graph.getNumberOfVertices() - 1) { //if in the last iteration of the loop, the loop did not break - it means no vertex uses that colour
                    //System.out.println("deleted colour:" + usedColours.get(k) + "\n"); //to test which colour gets deleted
                    usedColours.remove(k); //so we delete it
                }
            }
        }
    }

    /**
     * The findVertex method takes in the x and y coordinates of a point and returns a Vertex that is within a certain radius of the point.
     * The method iterates through all the vertices in the graph and calculates the euclidean distance between the point and the vertex.
     * If the euclidean distance is less than or equal to the radius of the vertex, the method returns the vertex.
     * If no vertex is found within the radius, the method returns null.
     * @param xCoordinate The x coordinate on the board
     * @param yCoordinate The y coordinate on the board
     * @return null if euclidean distance greater than the vertex's radius, else return the vertex ID
     */
    public Vertex findVertex(int xCoordinate, int yCoordinate){
       
        for (int i = 0; i < graph.getVertices().length; i++) {
            double xSquared = (xCoordinate - graph.getVertices()[i].xCoordinate)*(xCoordinate - graph.getVertices()[i].xCoordinate);
            double ySquared = (yCoordinate - graph.getVertices()[i].yCoordinate)*(yCoordinate - graph.getVertices()[i].yCoordinate);
            double z = xSquared + ySquared;
            double euclideanDistance = Math.sqrt(z);
            if (euclideanDistance <= graph.getVertices()[i].RADIUS){
                return graph.getVertices()[i];
            }
        }
        return null;
    }

    /**
     * Method to check if a vertex can be drawn with a certain color.
     * @param color The color to check if the vertex can be drawn with.
     * @param vertex The vertex to check if it can be drawn with the specified color.
     * @return true if the vertex can be drawn with the specified color, false otherwise.
    */
    public boolean drawable(Color color, Vertex vertex){

        if(color == Color.WHITE){
            return true;
        }

        for (int i = 0; i < vertex.getAdjacentVertices().size(); i++) {
            if(vertex.getAdjacentVertices().get(i).color == color){
                return false;
            }
        }
        return true;
    }

    /**
     * GameCycle is a private inner class that implements the ActionListener interface.
     * It is used to handle actions performed in the game.
     */
    private class GameCycle implements ActionListener{
        /**
         * Overrides the actionPerformed method of the ActionListener interface.
         * This method is called every time an action is performed.
         * @param e The ActionEvent that occurred.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();            
        }   
    }

    /**
     * The doGameCycle method is a private method that is called every time an action is performed.
     * It is used to perform the main game logic, such as updating game state and rendering graphics.
     */
    private void doGameCycle(){
        repaint();  
    }

    /**
     * The getUsedColors method is a public method that returns an ArrayList of Color objects.
     * It is used to retrieve the list of colors that have been used in the game.
     * @return An ArrayList of Color objects representing the used colors in the game.
     */
    public ArrayList<Color> getUsedColors(){
        return usedColours;
    }

    /**
     *  The getNumberOfUsedColors method is a public method that returns an integer value.
     * It is used to retrieve the number of colors that have been used in the game.
     * @return An integer value representing the number of used colors in the game.
     */
    public int getNumberOfUsedColors(){
        return  usedColours.size();
    }

    /**
     * The setGameMode method is a public method that sets the game mode of the class.
     * It is used to change the game mode to the one specified in the parameter.
     * @param gameMode a string representing the new game mode to be set.
     */
    public void setGameMode(String gameMode) {
        GameMode = gameMode;
    }

    /**
     * The setNumberVertexandEdges method is a public method that sets the number of vertex and edges in the class.
     * It is used to change the number of vertex and edges to the one specified in the parameter.
     * @param numberVertex an integer representing the new number of vertex to be set.
     * @param numberEdges an integer representing the new number of edges to be set.
     */
    public void setNumberVertexandEdges(int numberVertex, int numberEdges){
        this.numberVertex = numberVertex;
        this.numberEdges = numberEdges;
    }

    /**
     * The setGraphSourcePath method is a public method that sets the source path of the graph in the class.
     * It is used to change the graph source path to the one specified in the parameter.
     * @param source a string representing the new source path of the graph.
     */
    public void setGraphSourcePath(String source){
        graphSourcePath = source;
    }

    /**
     * The setLaunch method is a public method that sets the launch object in the class.
     * It is used to change the launch object to the one specified in the parameter.
     * @param launch an object of type `Launch` representing the new launch object to be set.
     */
    public void setLaunch(Launch launch){
        this.launch = launch;
    }

    /**
     * The setGame method is a public method that sets the game object in the class.
     * It is used to change the game object to the one specified in the parameter.
     * @param game an object of type `Game` representing the new game object to be set.
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     *  The getusedColoursInt method is a public method that returns an ArrayList of Integer objects.
     * It is used to retrieve the list of used color integers in the game.
     * @return An ArrayList of Integer objects representing the used color integers in the game.
    */
    public ArrayList<Integer> getusedColoursInt(){
        return usedColoursInt;
    }

}