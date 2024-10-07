import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Path {
    private ArrayList<Point> path;

    /**
     * Default constructor for a new path.
     */
    public Path(){
        path = new ArrayList<>();
    }

    /**
     * Converts an Eulerian cycle to a Hamiltonian cycle by skipping repeated vertices.
     */
    public void convertToHamiltonian(){
        Set<Point> seen = new HashSet<>();
        ArrayList<Point> newPath = new ArrayList<>();
        for(int i = 0;  i < this.path.size() - 1; i++){
            if(seen.add(path.get(i))){
                newPath.add(this.path.get(i));
            }
        }
        newPath.add(newPath.get(0));
        this.path = newPath;
    }

    /**
     * Returns a neat string of the path in order. 
     */
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < path.size() - 1; i++){
            string.append(path.get(i).toString());
            string.append(" -> ");
        }
        string.append(path.get(path.size() - 1));
        return string.toString();
    }

    /**
     * Generates a Eulerian cycle from the edges in the graph
     * @param g The graph to generate the cycle on
     */
    public void generateEulerianCycle(Graph g){
        //Get the edges of the graph and start by adding the first point into the cycle
        ArrayList<Edge> edges = g.getEdges();
        Point currentPoint = g.getPoints().get(0);
        Set<Point> currentEdges;
        path.add(currentPoint);

        //Continue taking random edges that are not in the graph from every point until every edge is in the path 
        for(int i = 0; i < edges.size(); i++){
            currentEdges = currentPoint.getAdjacentEdges();
            for(Point next : currentEdges){
                if(!inPath(currentPoint, next)){
                    path.add(next);
                    currentPoint = next;
                    break;
                }
            }
        }
        //Re-add the first edge so the path loops back
        path.add(path.get(0));
    }

    /**
     * Checks if an edge is already in the path
     * @param p1 One of the endpoints of the edge
     * @param p2 The other endpoint of the edge
     * @return Whether the edge in already in the path
     */
    public boolean inPath(Point p1, Point p2){
        for(int i = 0; i < path.size() - 1; i++){
            if(((path.get(i).compareTo(p1) == 0) && (path.get(i + 1).compareTo(p2) == 0)) 
                || (path.get(i).compareTo(p2) == 0) && (path.get(i + 1).compareTo(p1) == 0)){
                    return true;
            }
        }
        return false;
    }
}
