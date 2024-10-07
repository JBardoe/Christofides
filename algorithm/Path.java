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

    public void generateEulerianCycle(Graph g){
        ArrayList<Edge> edges = g.getEdges();
        Point currentPoint = g.getPoints().get(0);
        Set<Point> currentEdges;
        Set<Edge> added = new HashSet<>();
        path.add(currentPoint);
        while(added.size() != edges.size()){
            currentEdges = currentPoint.getAdjacentEdges();
            for(Point next : currentEdges){
                if(added.add(new Edge(currentPoint, next))){
                    path.add(next);
                    currentPoint = next;
                    break;
                }
            }
        }
        path.add(path.get(0));
    }
}
