
package filediff;

import java.util.*;

public interface DiffAlgorithm {
    
  
    public Patch diff(Object[] original, Object[] revised);
   
    public Patch diff(List<?> original, List<?> revised);
}
