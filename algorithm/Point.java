import java.util.HashSet;
import java.util.Set;

public class Point implements Comparable<Point>{
    private final int x;
    private final int y;
    private final Set<Point> adjacent;
    
    /**
     * Constructor for a new point
     * @param x x-coordinate of the point 
     * @param y y-coordinate of the point
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.adjacent = new HashSet<>();
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public Set<Point> getAdjacentEdges(){return this.adjacent;}

    public int getDegree(){return this.adjacent.size();}

    /**
     * Adds an adjacent point
     * @param p The connected point
     */
    public void addEdge(Point p){
        this.adjacent.add(p);
    }

    /**
     * Compares two points.
     * Returns 0 if they are the same point.
     * Returns 1 if called on the point with the higher average coordinate.
     * Returns -1 otherwise;
     */
    @Override
    public int compareTo(Point p) {
        if(this.x == p.getX() && this.y == p.getY()){
            return 0;
        } else if((this.x + this.y) / 2 > (p.getX() + p.getY()) / 2){
            return 1;
        } else{
            return -1;
        }
    }

    /**
     * Calculates the distance to another point.
     * @param p The point to calculate the distance to
     * @return The distance to the other point
     */
    public double getDistanceTo(Point p){
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
    }

    @Override
    public String toString(){
        return "[" + this.x + ", " + this.y + "]";
    }
}
