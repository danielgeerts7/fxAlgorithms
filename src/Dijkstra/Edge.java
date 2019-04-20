package Dijkstra;

public class Edge  {
    private final Vertex source;
    private final Vertex destination;
    private final int weight;
    
    public enum Direction {UP,DOWN,RIGHT,LEFT};
    public Direction current = Direction.UP;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
    	id = null;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    public Edge(Direction id, Vertex source, Vertex destination, int weight) {
    	this.current = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Direction getDirection() {
        return current;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}