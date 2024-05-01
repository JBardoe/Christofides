public class Edge implements Comparable<Edge> {
    Point point1;
    Point point2;
    double weight;

    public Edge(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        this.weight = point1.getDistanceTo(point2);
    }

    public Point[] getPoints(){
        return new Point[]{this.point1, this.point2};
    }

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
