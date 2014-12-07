

package filediff.myers;

import filediff.Changediff;
import filediff.Diff;
import filediff.Segment;
import filediff.DiffAlgorithm;
import filediff.DeleteDiff;
import filediff.Patch;
import filediff.InsertDiff;

import java.lang.reflect.Array;
import java.util.List;


public class MyersDiff implements DiffAlgorithm {
  
    public MyersDiff() {
    }
    
  
    public Patch diff(List<?> original, List<?> revised) {
        return diff(original.toArray(), revised.toArray());
    }
    
   
    public Patch diff(Object[] orig, Object[] rev) {
        PathNode path;
        try {
            path = buildPath(orig, rev);
            return buildRevision(path, orig, rev);
        } catch (DifferentiationFailedException e) {
            e.printStackTrace();
        }
        return new Patch();
    }
   
    public static PathNode buildPath(Object[] orig, Object[] rev)
            throws DifferentiationFailedException {
        if (orig == null)
            throw new IllegalArgumentException("original sequence is null");
        if (rev == null)
            throw new IllegalArgumentException("revised sequence is null");
        
        // these are local constants
        final int N = orig.length;
        final int M = rev.length;
        
        final int MAX = N + M + 1;
        final int size = 1 + 2 * MAX;
        final int middle = size / 2;
        final PathNode diagonal[] = new PathNode[size];
        
        diagonal[middle + 1] = new Pointer(0, -1, null);
        for (int d = 0; d < MAX; d++) {
            for (int k = -d; k <= d; k += 2) {
                final int kmiddle = middle + k;
                final int kplus = kmiddle + 1;
                final int kminus = kmiddle - 1;
                PathNode prev = null;
                
                int i;
                if ((k == -d) || (k != d && diagonal[kminus].i < diagonal[kplus].i)) {
                    i = diagonal[kplus].i;
                    prev = diagonal[kplus];
                } else {
                    i = diagonal[kminus].i + 1;
                    prev = diagonal[kminus];
                }
                
                diagonal[kminus] = null; // no longer used
                
                int j = i - k;
                
                PathNode node = new DiffNode(i, j, prev);
                
                // orig and rev are zero-based
                // but the algorithm is one-based
                // that's why there's no +1 when indexing the sequences
                while (i < N && j < M && orig[i].equals(rev[j])) {
                    i++;
                    j++;
                }
                if (i > node.i)
                    node = new Pointer(i, j, node);
                
                diagonal[kmiddle] = node;
                
                if (i >= N && j >= M) {
                    return diagonal[kmiddle];
                }
            }
            diagonal[middle + d - 1] = null;
            
        }
        // According to Myers, this cannot happen
        throw new DifferentiationFailedException("could not find a diff path");
    }
    
    
    public static Patch buildRevision(PathNode path, Object[] orig, Object[] rev) {
        if (path == null)
            throw new IllegalArgumentException("path is null");
        if (orig == null)
            throw new IllegalArgumentException("original sequence is null");
        if (rev == null)
            throw new IllegalArgumentException("revised sequence is null");
        
        Patch patch = new Patch();
        if (path.isPointer())
            path = path.prev;
        while (path != null && path.prev != null && path.prev.j >= 0) {
            if (path.isPointer())
                throw new IllegalStateException("bad diffpath: found snake when looking for diff");
            int i = path.i;
            int j = path.j;
            
            path = path.prev;
            int ianchor = path.i;
            int janchor = path.j;
            
            Segment original = new Segment(ianchor, copyOfRange(orig, ianchor, i));
            Segment revised = new Segment(janchor, copyOfRange(rev, janchor, j));
            Diff delta = null;
            if (original.size() == 0 && revised.size() != 0) {
                delta = new InsertDiff(original, revised);
            } else if (original.size() > 0 && revised.size() == 0) {
                delta = new DeleteDiff(original, revised);
            } else {
                delta = new Changediff(original, revised);
            }
            
            patch.addDelta(delta);
            if (path.isPointer())
                path = path.prev;
        }
        return patch;
    }
    
    /**
     * Copied here from JDK 1.6
    */
    
    @SuppressWarnings("unchecked")
    public static <T> T[] copyOfRange(T[] original, int from, int to) {
        return copyOfRange(original, from, to, (Class<T[]>) original.getClass());
    }
    
    /**
     * Copied here from JDK 1.6
     */
    @SuppressWarnings("unchecked")
    public static <T, U> T[] copyOfRange(U[] original, int from, int to,
            Class<? extends T[]> newType) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        T[] copy = ((Object) newType == (Object) Object[].class) ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }
    
}
