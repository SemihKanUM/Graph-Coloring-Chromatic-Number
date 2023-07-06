import Graph.ConnectedComponents;
import Graph.Launch;

public class Main  {
    public static void main(String[] args) {
        Launch launch = new Launch();
        String string = args[0];

        launch.LoadInitialValues(string);

        ConnectedComponents cc = new ConnectedComponents();
        int[] result = cc.search(launch.getGraph());
        System.out.println("NEW BEST UPPER BOUND = " + result[2]);
        System.out.println("NEW BEST LOWER BOUND = " + result[0]);
        System.out.println("CHROMATIC NUMBER = " + result[1]);
    }
}
