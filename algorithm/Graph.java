import java.util.ArrayList;

public class Graph {
    private final ArrayList<Point> points;
    private final ArrayList<Edge> edges;

    /**
     * Constructor for a new graph
     * @param points The points in the graph
     */
    public Graph(ArrayList<Point> points){
        this.points = points;
        this.edges = new ArrayList<>();
    }

    public ArrayList<Point> getPoints(){return this.points;}
    public ArrayList<Edge> getEdges(){return this.edges;}
    
    /**
     * Adds a new edge into the graph using its two endpoints
     * @param p1 Endpoint 1 of the edge
     * @param p2 Endpoint 2 of the edge
     */
    public void addEdge(Point p1, Point p2){
        this.edges.add(new Edge(p1, p2));
        p1.addEdge(p2);
        p2.addEdge(p1);
    }

    /**
     * Integrates an already created edge into the graph
     * @param edge The initialised edge object
     */
    public void addEdge(Edge edge){
        this.edges.add(edge);
        Point[] endpoints = edge.getPoints();
        endpoints[0].addEdge(endpoints[1]);
        endpoints[1].addEdge(endpoints[0]);
    }

    /**
     * Removes an edge from the graph using the edge to be removed
     * @param e The edge to remove
     * @return Whether it could be removed
     */
    public boolean removeEdge(Edge e){
        for(Edge edge : this.edges){
            if(edge.compareTo(e) == 0){
                this.edges.remove(edge);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all points of odd degree in the graph
     * @return An arraylist of all the points of odd degree
     */
    public ArrayList<Point> getOddDegree(){
        ArrayList<Point> odd = new ArrayList<>();
        for(Point p : points){
            if(p.getDegree() % 2 == 1){
                odd.add(p);
            }
        }
        return odd;
    }

    /**
     * Uses the points in the graph to turn it into a perfect matching assuming there are no edges already added
     */
    public void generatePerfectMatching(){
        ArrayList<Point> toAdd = new ArrayList<>(this.points);
        while(!toAdd.isEmpty()){
            toAdd.remove(addLowestEdge(toAdd.get(0)));
            toAdd.remove(0);
        }
    }

    /**
     * Merges the edges from another graph with the same points into the current one
     * @param g The graph to fold in
     */
    public void mergeEdges(Graph g){
        for(Edge edge : g.getEdges()){
            if(!this.edges.contains(edge)){
                this.addEdge(edge);
            }
        }
    }

    /**
     * Adds a point's shortest edge into the graph out of an array of available points to connect it to
     * @param p The point to add
     * @param added The points to connect it with
     * @param current The index of the current point in the graph's arraylist
     */
    public void addLowestEdge(Point p, boolean[] added, int current){
        double min = Integer.MAX_VALUE;
        Point point2 = null;
        for(int i = 0; i < added.length; i++){
            if(i == current) continue;
            if(added[i] && p.getDistanceTo(points.get(i)) < min){
                min = p.getDistanceTo(points.get(i));
                point2 = points.get(i);
            }
        }
        this.addEdge(p, point2);
    }

    /**
     * Adds a point's shortest edge into the graph out of all available options
     * @param p The point to add
     * @return The point it is connected with
     */
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
