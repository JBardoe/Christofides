import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Graph {
    private Point[] points;
    private ArrayList<Edge> edges;

    public Graph(Point[] points, ArrayList<Edge> edges){
        this.points = points;
        this.edges = edges;
    }

    public Point[] getPoints(){return this.points;}
    public ArrayList<Edge> getEdges(){return this.edges;}
    
    public void addEdge(Point p1, Point p2){
        this.edges.add(new Edge(p1, p2));
        p1.addEdge(p2);
        p2.addEdge(p1);
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
        Point[] endpoints = edge.getPoints();
        endpoints[0].addEdge(endpoints[1]);
        endpoints[1].addEdge(endpoints[0]);
    }

    public boolean removeEdge(Edge e){
        for(Edge edge : this.edges){
            if(edge.compareTo(e) == 0){
                this.edges.remove(edge);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Point> getOddDegree(){
        ArrayList<Point> odd = new ArrayList<>();
        for(Point p : points){
            if(p.getDegree() % 2 == 1){
                odd.add(p);
            }
        }
        return odd;
    }

    public void generatePerfectMatching(){
        ArrayList<Point> toAdd = new ArrayList<>(Arrays.asList(this.points));
        while(toAdd.size() > 0){
            toAdd.remove(addLowestEdge(toAdd.get(0)));
            toAdd.remove(0);
        }
    }

    public void mergeEdges(Graph g){
        for(Edge edge : g.getEdges()){
            if(!this.edges.contains(edge)){
                this.addEdge(edge);
            }
        }
    }

    public void addLowestEdge(Point p, Set<Point> added){
        double min = Integer.MAX_VALUE;
        Point point2 = null;
        for(Point addedPoint : added){
            if(p.getDistanceTo(addedPoint) < min){
                min = p.getDistanceTo(addedPoint);
                point2 = addedPoint;
            }
        }
        this.addEdge(p, point2);
    }

    public Point addLowestEdge(Point p){
        double min = Integer.MAX_VALUE;
        Point point2 = null;
        for(Point point : points){
            if(p.compareTo(point) != 0 && p.getDistanceTo(point) < min){
                min = p.getDistanceTo(point);
                point2 = point;
            }
        }
        this.addEdge(p, point2);
        return point2;
    }
}
