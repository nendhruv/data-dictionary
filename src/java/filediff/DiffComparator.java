
package filediff;

import java.io.Serializable;
import java.util.Comparator;

public class DiffComparator implements Comparator<Diff>, Serializable {
    private static final long serialVersionUID = 1L;
    public static final Comparator<Diff> INSTANCE = new DiffComparator();

    private DiffComparator() {
    }

    public int compare(final Diff a, final Diff b) {
        final int posA = a.getOriginal().getPosition();
        final int posB = b.getOriginal().getPosition();
        if (posA > posB) {
            return 1;
        } else if (posA < posB) {
            return -1;
        }
        return 0;
    }
}