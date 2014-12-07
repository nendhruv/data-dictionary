
package filediff;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Patch {
    private List<Diff> deltas = new LinkedList<Diff>();

    
    public List<?> applyTo(List<?> target) throws PatchFailedException {
        List<Object> result = new LinkedList<Object>(target);
        ListIterator<Diff> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Diff delta = (Diff) it.previous();
            delta.applyTo(result);
        }
        return result;
    }
    
   
    public List<?> restore(List<?> target) {
        List<Object> result = new LinkedList<Object>(target);
        ListIterator<Diff> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Diff delta = (Diff) it.previous();
            delta.restore(result);
        }
        return result;
    }
    
   
    public void addDelta(Diff delta) {
        deltas.add(delta);
    }

    public List<Diff> getDeltas() {
        Collections.sort(deltas, DiffComparator.INSTANCE);
        return deltas;
    }
}

