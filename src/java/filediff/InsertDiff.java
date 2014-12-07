
package filediff;

import java.util.List;


public class InsertDiff extends Diff {
    
   
    public InsertDiff(Segment original, Segment revised) {
        super(original, revised);
    }
    
   
    @Override
    public void applyTo(List<Object> target) throws PatchFailedException {
        verify(target);
        int position = this.getOriginal().getPosition();
        List<?> lines = this.getRevised().getLines();
        for (int i = 0; i < lines.size(); i++) {
            target.add(position + i, lines.get(i));
        }
    }
    
  
    @Override
    public void restore(List<Object> target) {
        int position = getRevised().getPosition();
        int size = getRevised().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
    }
    
    @Override
    public void verify(List<?> target) throws PatchFailedException {
        if (getOriginal().getPosition() > target.size()) {
            throw new PatchFailedException("Incorrect patch for delta: "
                    + "delta original position > target size");
        }
        
    }
    
    public TYPE getType() {
        return Diff.TYPE.INSERT;
    }
    
    @Override
    public String toString() {
        return "[InsertDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getRevised().getLines() + "]";
    }
}

