public class Edge implements Comparable<Edge> {
    Point point1;
    Point point2;
    double weight;

    /**
     * Constructor for a new edge
     * @param point1 One of the edges endpoints
     * @param point2 The other endpoint
     */
    public Edge(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        this.weight = point1.getDistanceTo(point2);
    }

    public Point[] getPoints(){return new Point[]{this.point1, this.point2};}

    /**
     * Compares two edges. 
     * Returns 0 if they are the same edge.
     * Returns 1 if they are different lengths and the method is called on the longer one.
     * Returns -1 if they are different lengths and the method is called on the shorter one 
     *  or if they are the same length with different points.
     */
    @Override
    public int compareTo(Edge e){
        double diff = this.weight - e.weight;
        if (diff == 0) {
            Point[] compare = e.getPoints();
            if ((point1.compareTo(compare[0]) == 0 && point2.compareTo(compare[1]) == 0)
                    || (point1.compareTo(compare[1]) == 0 && point2.compareTo(compare[0]) == 0)) {
                return 0;
            }
            return -1;
        }
    return diff > 0 ? 1 : -1;
    }
}
