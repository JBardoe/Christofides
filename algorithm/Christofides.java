import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Christofides {
    public static void main(String[] args) throws IOException {
        ArrayList<Point> points = new ArrayList<>();
        getPoints(points);
        Graph graph = new Graph(convertToPointsArray(points), new ArrayList<Edge>());
        primsAlgorithm(graph);
        Graph oddDegree = new Graph(convertToPointsArray(graph.getOddDegree()), new ArrayList<Edge>());
        graph.generatePerfectMatching();
        graph.mergeEdges(oddDegree);
        Path path = new Path();
        path.generateEulerianCycle(graph);
        path.convertToHamiltonian();
        System.out.println("The path is "+ path.toString());
    }

    public static void getPoints(ArrayList<Point> points) throws IOException {
        // Scanner kb = new Scanner(System.in);

        while (true) {
            // System.out.println("Please enter the file name of the dataset:");
            // String fileName = kb.nextLine();

            File file1 = new File("data/" + "dataset1" + ".txt");

            if (file1.exists() && !file1.isDirectory()) {
                BufferedReader br = new BufferedReader(new FileReader(file1));
                String line = br.readLine();
                int comma, x, y;
                while (line != null) {
                    comma = line.indexOf(",");
                    x = Integer.parseInt(line.substring(0, comma));
                    y = Integer.parseInt(line.substring(comma + 1, line.length()));
                    points.add(new Point(x, y));
                    line = br.readLine();
                }
                br.close();
                // kb.close();
                return;

            } else {
                System.out.println("File not found");
            }
        }
    }

    public static void primsAlgorithm(Graph g){
        Set<Point> added = new HashSet<>();
        Point[] points = g.getPoints();
        added.add(points[0]);
        int[] weights = new int[points.length];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[0] = 0;

        Point current;
        while(added.size() != points.length){
            int min = getMin(weights);
            current = points[min];
            weights[min] = -1;
            g.addLowestEdge(current, added);
            for(int j = 0; j < points.length; j++){
                if(current.getDistanceTo(points[j]) < weights[j]){
                    weights[j] = (int) current.getDistanceTo(points[j]);
                }
            }
        }
    }

    public static int getMin(int[] nums){
        int min = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != -1 && nums[i] < nums[min]){
                min = i;
            }
        }
        return min;
    }

    public static Point[] convertToPointsArray(ArrayList<Point> points){
        Point[] ret = new Point[points.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = points.get(i);
        }
        return ret;
    }
}