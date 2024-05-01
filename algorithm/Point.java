import java.util.Set;
import java.util.HashSet;

public class Point implements Comparable<Point>{
    private final int x;
    private final int y;
    private Set<Point> adjacent;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.adjacent = new HashSet<>();
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public Set<Point> getAdjacentEdges(){return this.adjacent;}

    public int getDegree(){return this.adjacent.size();}

    public void addEdge(Point p){
        this.adjacent.add(p);
    }

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

    public double getDistanceTo(Point p){
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
    }

    public String toString(){
        return "[" + this.x + ", " + this.y + "]";
    }
}
