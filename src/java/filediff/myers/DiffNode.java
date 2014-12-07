package filediff.myers;


public final class DiffNode extends PathNode {
    
    public DiffNode(int i, int j, PathNode prev) {
        super(i, j, (prev == null ? null : prev.previousSnake()));
    }
    
   
    public boolean isPointer() {
        return false;
    }
    
}