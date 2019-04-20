package Dijkstra;

public class Vertex {
    final private int x;
    final private int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == 0) ? 0 : 0);
        return result;
    }
    
    
    public boolean equalsPosition(int x, int y) {
    	if (this.x == x && this.y == y) {
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (this.x == other.x && this.y == other.y) {
        	return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }

}