
package filediff;

import java.util.List;


public class Changediff extends Diff {
    
   
    public Changediff(Segment original, Segment revised) {
        super(original, revised);
    }
    
   
    @Override
    public void applyTo(List<Object> target) throws PatchFailedException {
        verify(target);
        int position = getOriginal().getPosition();
        int size = getOriginal().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (Object line : getRevised().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }
    
   
    @Override
    public void restore(List<Object> target) {
        int position = getRevised().getPosition();
        int size = getRevised().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (Object line : getOriginal().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void verify(List<?> target) throws PatchFailedException {
        getOriginal().verify(target);
        if (getOriginal().getPosition() > target.size()) {
            throw new PatchFailedException("Incorrect patch for delta: "
                    + "delta original position > target size");
        }
    }
    
    @Override
    public String toString() {
        return "[ChangeDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getOriginal().getLines() + " to " + getRevised().getLines() + "]";
    }

    @Override
    public TYPE getType() {
        return Diff.TYPE.CHANGE;
    }
}
