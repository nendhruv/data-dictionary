
package filediff;

import java.util.Arrays;
import java.util.List;

public class Segment {

    private final int position;
    private List<?> lines;
    public Segment(int position, List<?> lines) {
        this.position = position;
        this.lines = lines;
    }
    
   
    public Segment(int position, Object[] lines) {
        this.position = position;
        this.lines = Arrays.asList(lines);
    }
    
    
    public void verify(List<?> target) throws PatchFailedException {
        if (last() > target.size()) {
            throw new PatchFailedException("Incorrect Chunk: the position of chunk > target size");
        }
        for (int i = 0; i < size(); i++) {
            if (!target.get(position + i).equals(lines.get(i))) {
                throw new PatchFailedException(
                        "Incorrect Chunk: the chunk content doesn't match the target");
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public void setLines(List<?> lines) {
        this.lines = lines;
    }

    public List<?> getLines() {
        return lines;
    }

    public int size() {
        return lines.size();
    }
    
    /**
     * Returns the index of the last line of the chunk.
     */
    public int last() {
        return getPosition() + size() - 1;
    }
    
  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lines == null) ? 0 : lines.hashCode());
        result = prime * result + position;
        result = prime * result + size();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Segment other = (Segment) obj;
        if (lines == null) {
            if (other.lines != null)
                return false;
        } else if (!lines.equals(other.lines))
            return false;
        if (position != other.position)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "[position: " + position + ", size: " + size() + ", lines: " + lines + "]";
    }
    
}
