

package filediff.myers;


public final class Pointer extends PathNode {
    
    public Pointer(int i, int j, PathNode prev) {
        super(i, j, prev);
    }
   
   
    @Override
    public boolean isPointer() {
        return true;
    }
    
}