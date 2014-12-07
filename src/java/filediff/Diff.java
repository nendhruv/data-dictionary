
package filediff;

import java.util.*;


public abstract class Diff {
    private Segment original;
    private Segment revised;
    
    public enum TYPE {
        CHANGE, DELETE, INSERT
    }
    
  
    public Diff(Segment original, Segment revised) {
        this.original = original;
        this.revised = revised;
    }
    
    public abstract void verify(List<?> target) throws PatchFailedException;
    
   
    public abstract void applyTo(List<Object> target) throws PatchFailedException;
    
  
    public abstract void restore(List<Object> target);
    
  
    public abstract TYPE getType();
    
   
    public Segment getOriginal() {
        return original;
    }
    
    
    public void setOriginal(Segment original) {
        this.original = original;
    }
    
    /**
     * @return the Segment describing the revised text
     */
    public Segment getRevised() {
        return revised;
    }
    
   
    public void setRevised(Segment revised) {
        this.revised = revised;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((original == null) ? 0 : original.hashCode());
        result = prime * result + ((revised == null) ? 0 : revised.hashCode());
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
        Diff other = (Diff) obj;
        if (original == null) {
            if (other.original != null)
                return false;
        } else if (!original.equals(other.original))
            return false;
        if (revised == null) {
            if (other.revised != null)
                return false;
        } else if (!revised.equals(other.revised))
            return false;
        return true;
    }
    
}
