

package filediff.myers;


public abstract class PathNode {
    /** Position in the original sequence. */
    public final int i;
    /** Position in the revised sequence. */
    public final int j;
    /** The previous node in the path. */
    public final PathNode prev;
    
    
    public PathNode(int i, int j, PathNode prev) {
        this.i = i;
        this.j = j;
        this.prev = prev;
    }
    
    
    public abstract boolean isPointer();
    
    
    public boolean isBootstrap() {
        return i < 0 || j < 0;
    }
    
   
    public final PathNode previousSnake() {
        if (isBootstrap())
            return null;
        if (!isPointer() && prev != null)
            return prev.previousSnake();
        return this;
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        PathNode node = this;
        while (node != null) {
            buf.append("(");
            buf.append(Integer.toString(node.i));
            buf.append(",");
            buf.append(Integer.toString(node.j));
            buf.append(")");
            node = node.prev;
        }
        buf.append("]");
        return buf.toString();
    }
}