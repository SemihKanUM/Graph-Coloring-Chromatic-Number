package Graph;


public class Launch {
    
    public ReadGraph readGraph = new ReadGraph();
    public Graph graph;
    public Vertex[] Vertices;
    
    public Launch(){}

    public void LoadInitialValues(String string){
        //The codes below are for loading graph object.
        readGraph.run(string); //(it should take the file name as a input.
        Vertices = new Vertex[readGraph.getNumberOfVertices()]; 
        Vertices = LoadVertices(readGraph.getNumberOfVertices()); //Creates n Vertices ,and adds them to the array.
        Vertices = LoadEdges(readGraph.colEdgeList); //
        LoadGraph(Vertices);
    }

    public Vertex[] LoadVertices(int n){ // Creates n Vertices. First index contains the Vertex that has Vertex ID 1.

        for (int i = 1; i <= n; i++) { //This loop creates n Vertex objects and assigns them a vertex ID i+1
            Vertex Vertex = new Vertex(i);
            Vertices[i - 1] = Vertex;
        }
        return Vertices;
    }

    public Vertex[] LoadEdges(ColEdge[] colEdge){ //This Method adds the adjacent Vertices of Vertices.
        for (ColEdge colEdge2 : colEdge) { 
        /*Takes u and v of each colEdge object in the colEdge[] array. 
        The colEdge object contains two attributes, u & v, which correspond to the VerticeIDs of two vertices which have an edge.*/
        int u = colEdge2.u; 
        int v = colEdge2.v;
       
       /*Creates new vertex objects and sets them as the Vertex at the u-1 & v-1 index in the Vertices array 
       (it has to be -1 as u & v start from 1 and index)  */  
       Vertex currentVertexOne = Vertices[u - 1]; 
       Vertex currentVertexTwo = Vertices[v - 1];

       //Since having an edge means that two vertices are adjacent, adds each object to the AdjacentVertices array of the other.
       currentVertexOne.getAdjacentVertices().add(currentVertexTwo); 
       currentVertexTwo.getAdjacentVertices().add(currentVertexOne);

       //Replaces the original objects at those indexes with our updated objects. 
       Vertices[u - 1] = currentVertexOne;
       Vertices[v - 1] = currentVertexTwo;
        }
        return Vertices;
    }

    public void LoadGraph(Vertex[] Vertices){
        graph = new Graph(Vertices);
        graph.setAdjacancyMatrix(toAdjacencyMatrix(readGraph.colEdgeList, readGraph.numberOfVertices));
        graph.setNumberOfEdges(readGraph.numberOfEdges);
    }

    public int[][] toAdjacencyMatrix(ColEdge[] edges, int numVertices) {
        int[][] adjMatrix = new int[numVertices][numVertices];
        for (ColEdge edge : edges) {
            adjMatrix[edge.u-1][edge.v-1] = 1;
            adjMatrix[edge.v-1][edge.u-1] = 1;
        }
        return adjMatrix;
    }
    

    public Graph getGraph(){
        return graph;
    }
}
