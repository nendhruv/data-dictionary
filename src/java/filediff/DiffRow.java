

package filediff;


public class DiffRow {
    private Tag tag;
    private String oldLine;
    private String newLine;
    
    public DiffRow(Tag tag, String oldLine, String newLine) {
        this.tag = tag;
        this.oldLine = oldLine;
        this.newLine = newLine;
    }
    
    public enum Tag {
        INSERT, DELETE, CHANGE, EQUAL
    }
    
    
    public Tag getTag() {
        return tag;
    }
   
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    
   
    public String getOldLine() {
        return oldLine;
    }
    
    
    public void setOldLine(String oldLine) {
        this.oldLine = oldLine;
    }
    
   
    public String getNewLine() {
        return newLine;
    }
   
    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((newLine == null) ? 0 : newLine.hashCode());
        result = prime * result + ((oldLine == null) ? 0 : oldLine.hashCode());
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
        DiffRow other = (DiffRow) obj;
        if (newLine == null) {
            if (other.newLine != null)
                return false;
        } else if (!newLine.equals(other.newLine))
            return false;
        if (oldLine == null) {
            if (other.oldLine != null)
                return false;
        } else if (!oldLine.equals(other.oldLine))
            return false;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        return true;
    }
    
    public String toString() {
        return "[" + this.tag + "," + this.oldLine + "," + this.newLine + "]";
    }
}
