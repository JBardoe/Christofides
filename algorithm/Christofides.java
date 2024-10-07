import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Christofides {
    public static void main(String[] args) throws IOException {
        //Get the points from a file
        ArrayList<Point> points = getPoints();

        //Generate the graph
        Graph graph = new Graph(points);

        //Find the minimum spanning tree with Prim's algorithm
        primsAlgorithm(graph);

        //Get the points of odd degree and generate a perfect matching
        Graph oddDegree = new Graph(graph.getOddDegree());
        graph.generatePerfectMatching();

        //Merge the perfect matching into the rest of the graph
        graph.mergeEdges(oddDegree);
        
        //Generate an Eulerian cycle using the added edges
        Path path = new Path();
        path.generateEulerianCycle(graph);
        
        //Convert the Eulerian cycle to a Hamiltonian cycle
        path.convertToHamiltonian();
        System.out.println("The path is "+ path.toString());
    }

    /**
     * Reads the points in from the file
     * @return The points in an arraylist
     * @throws IOException if stream to file cannot be read from
     */
    public static ArrayList<Point> getPoints() throws IOException {
        Scanner kb = new Scanner(System.in);
        ArrayList<Point> points = new ArrayList<>();

        //Repeat until file can be read
        while (true) {
            //User inputs the name of the dataset
            System.out.println("Please enter the file name of the dataset:");
            String fileName = kb.nextLine();

            //Open the file
            File file1 = new File("data/" + fileName + ".txt");

            //Read from the file and add to the array list
            if (file1.exists() && !file1.isDirectory()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                    String line = br.readLine();
                    int comma, x, y;
                    while (line != null) {
                        comma = line.indexOf(",");
                        x = Integer.parseInt(line.substring(0, comma));
                        y = Integer.parseInt(line.substring(comma + 1, line.length()));
                        points.add(new Point(x, y));
                        line = br.readLine();
                    }
                }
                kb.close();
                return points;

            } else {
                System.out.println("File not found");
            }
        }
    }

    /**
     * Implements Prim's algorithm to generate a minimum spanning tree of the graph
     * @param g The graph of points
     */
    public static void primsAlgorithm(Graph g){
        ArrayList<Point> points = g.getPoints(); //Points on the graph
        boolean[] added = new boolean[points.size()]; //Points that have already been included in the tree

        double[] weights = new double[points.size()]; //Weights for each point in the graph
        
        //Start by arbitrarily adding point 1
        added[0] = true;
        for(int k = 0; k < points.size(); k++){
            weights[k] = points.get(0).getDistanceTo(points.get(k));
        }
        weights[0] = -1;

        //For every other point:
        Point current;
        for(int i = 1; i < points.size(); i++){
            //Find the unadded point with the lowest weight and add it in
            int min = getMin(weights);
            current = points.get(min);
            added[min] = true;
            weights[min] = -1;

            //Add it using its lowest weighted edge
            g.addLowestEdge(current, added, min);

            //Update point weightings
            for(int j = 0; j < points.size(); j++){
                if(current.getDistanceTo(points.get(j)) < weights[j]){
                    weights[j] = current.getDistanceTo(points.get(j));
                }
            }
        }
    }

    /**
     * Utility to get the smallest weight in the graph for Prim's algorithm 
     * @param nums The weights from which to find the minimum
     * @return The minimum index
     */
    public static int getMin(double[] nums){
        int mindex = -1;
        double min = Integer.MAX_VALUE; 
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != -1 && nums[i] < min){
                mindex = i;
                min = nums[i];
            }
        }
        return mindex;
    }
}