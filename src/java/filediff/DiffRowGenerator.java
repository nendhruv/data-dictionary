
package filediff;

import filediff.DiffRow.Tag;

import java.util.*;


public class DiffRowGenerator {
    private final boolean showInlineDiffs;
    private final boolean ignoreWhiteSpaces;
    private final boolean ignoreBlankLines;
    private final String InlineOldTag;
    private final String InlineNewTag;
    private final String InlineOldCssClass;
    private final String InlineNewCssClass;
    private final int columnWidth;
    
   
    public static class Builder {
        private boolean showInlineDiffs = false;
        private boolean ignoreWhiteSpaces = true;
        private boolean ignoreBlankLines = true;
        private String InlineOldTag = "span";
        private String InlineNewTag = "span";
        private String InlineOldCssClass = "editOldInline";
        private String InlineNewCssClass = "editNewInline";
        private int columnWidth = 80;
      
        public Builder showInlineDiffs(boolean val) {
            showInlineDiffs = val;
            return this;
        }
        
       
        public Builder ignoreWhiteSpaces(boolean val) {
            ignoreWhiteSpaces = val;
            return this;
        }
        
        
        public Builder ignoreBlankLines(boolean val) {
            ignoreBlankLines = val;
            return this;
        }
       
        public Builder InlineOldTag(String tag) {
            InlineOldTag = tag;
            return this;
        }
        
       
        public Builder InlineNewTag(String tag) {
            InlineNewTag = tag;
            return this;
        }
      
        public Builder InlineOldCssClass(String cssClass) {
            InlineOldCssClass = cssClass;
            return this;
        }
        
        public Builder InlineNewCssClass(String cssClass) {
            InlineNewCssClass = cssClass;
            return this;
        }
        
       
        public Builder columnWidth(int width) {
            if (width > 0) {
                columnWidth = width;
            }
            return this;
        }
        
       
        public DiffRowGenerator build() {
            return new DiffRowGenerator(this);
        }
    }
    
    private DiffRowGenerator(Builder builder) {
        showInlineDiffs = builder.showInlineDiffs;
        ignoreWhiteSpaces = builder.ignoreWhiteSpaces; //
        ignoreBlankLines = builder.ignoreBlankLines; //
        InlineOldTag = builder.InlineOldTag;
        InlineNewTag = builder.InlineNewTag;
        InlineOldCssClass = builder.InlineOldCssClass;
        InlineNewCssClass = builder.InlineNewCssClass;
        columnWidth = builder.columnWidth; //
    }
    
    public List<DiffRow> generateDiffRows(List<String> original, List<String> revised) {
        return generateDiffRows(original, revised, DiffUtils.diff(original, revised));
    }
    
    
    @SuppressWarnings("unchecked")
    public List<DiffRow> generateDiffRows(List<String> original, List<String> revised, Patch patch) {
        // normalize the lines (expand tabs, escape html entities)
        original = StrUtil.normalize(original);
        revised = StrUtil.normalize(revised);
        
        // wrap to the column width
        original = StrUtil.wrapText(original, this.columnWidth);
        revised = StrUtil.wrapText(revised, this.columnWidth);
        
        List<DiffRow> diffRows = new ArrayList<DiffRow>();
        int endPos = 0;
        final List<Diff> deltaList = patch.getDeltas();
        for (int i = 0; i < deltaList.size(); i++) {
            Diff delta = deltaList.get(i);
            Segment orig = delta.getOriginal();
            Segment rev = delta.getRevised();
            
            // We should normalize and wrap lines in deltas too.
            orig.setLines(StrUtil.normalize((List<String>) orig.getLines()));
            rev.setLines(StrUtil.normalize((List<String>) rev.getLines()));
            
            orig.setLines(StrUtil.wrapText((List<String>) orig.getLines(), this.columnWidth));
            rev.setLines(StrUtil.wrapText((List<String>) rev.getLines(), this.columnWidth));
            
            // catch the equal prefix for each chunk
            for (String line : original.subList(endPos, orig.getPosition())) {
                diffRows.add(new DiffRow(Tag.EQUAL, line, line));
            }
            
            // Inserted DiffRow
            if (delta.getClass().equals(InsertDiff.class)) {
                endPos = orig.last() + 1;
                for (String line : (List<String>) rev.getLines()) {
                    diffRows.add(new DiffRow(Tag.INSERT, "", line));
                }
                continue;
            }
            
            // Deleted DiffRow
            if (delta.getClass().equals(DeleteDiff.class)) {
                endPos = orig.last() + 1;
                for (String line : (List<String>) orig.getLines()) {
                    diffRows.add(new DiffRow(Tag.DELETE, line, ""));
                }
                continue;
            }
            
            if (showInlineDiffs) {
                addInlineDiffs(delta);
            }
            // the changed size is match
            if (orig.size() == rev.size()) {
                for (int j = 0; j < orig.size(); j++) {
                    diffRows.add(new DiffRow(Tag.CHANGE, (String) orig.getLines().get(j),
                            (String) rev.getLines().get(j)));
                }
            } else if (orig.size() > rev.size()) {
                for (int j = 0; j < orig.size(); j++) {
                    diffRows.add(new DiffRow(Tag.CHANGE, (String) orig.getLines().get(j), rev
                            .getLines().size() > j ? (String) rev.getLines().get(j) : ""));
                }
            } else {
                for (int j = 0; j < rev.size(); j++) {
                    diffRows.add(new DiffRow(Tag.CHANGE, orig.getLines().size() > j ? (String) orig
                            .getLines().get(j) : "", (String) rev.getLines().get(j)));
                }
            }
            endPos = orig.last() + 1;
        }
        
        // Copy the final matching chunk if any.
        for (String line : original.subList(endPos, original.size())) {
            diffRows.add(new DiffRow(Tag.EQUAL, line, line));
        }
        return diffRows;
    }
    
    /**
     * Add the inline diffs for given delta
     * @param delta the given delta
     */
    @SuppressWarnings("unchecked")
    private void addInlineDiffs(Diff delta) {
        List<String> orig = (List<String>) delta.getOriginal().getLines();
        List<String> rev = (List<String>) delta.getRevised().getLines();
        LinkedList<String> origList = new LinkedList<String>();
        for (Character character : join(orig, "\n").toCharArray()) {
            origList.add(character.toString());
        }
        LinkedList<String> revList = new LinkedList<String>();
        for (Character character : join(rev, "\n").toCharArray()) {
            revList.add(character.toString());
        }
        List<Diff> inlineDeltas = DiffUtils.diff(origList, revList).getDeltas();
        if (inlineDeltas.size() < 3) {
            Collections.reverse(inlineDeltas);
            for (Diff inlineDelta : inlineDeltas) {
                Segment inlineOrig = inlineDelta.getOriginal();
                Segment inlineRev = inlineDelta.getRevised();
                if (inlineDelta.getClass().equals(DeleteDiff.class)) {
                    origList = wrapInTag(origList, inlineOrig.getPosition(), inlineOrig
                            .getPosition()
                            + inlineOrig.size() + 1, this.InlineOldTag, this.InlineOldCssClass);
                } else if (inlineDelta.getClass().equals(InsertDiff.class)) {
                    revList = wrapInTag(revList, inlineRev.getPosition(), inlineRev.getPosition()
                            + inlineRev.size() + 1, this.InlineNewTag, this.InlineNewCssClass);
                } else if (inlineDelta.getClass().equals(Changediff.class)) {
                    origList = wrapInTag(origList, inlineOrig.getPosition(), inlineOrig
                            .getPosition()
                            + inlineOrig.size() + 1, this.InlineOldTag, this.InlineOldCssClass);
                    revList = wrapInTag(revList, inlineRev.getPosition(), inlineRev.getPosition()
                            + inlineRev.size() + 1, this.InlineNewTag, this.InlineNewCssClass);
                }
            }
            StringBuilder origResult = new StringBuilder(), revResult = new StringBuilder();
            for (String character : origList) {
                origResult.append(character);
            }
            for (String character : revList) {
                revResult.append(character);
            }
            delta.getOriginal().setLines(Arrays.asList(origResult.toString().split("\n")));
            delta.getRevised().setLines(Arrays.asList(revResult.toString().split("\n")));
        }
    }
    
   
    public static LinkedList<String> wrapInTag(LinkedList<String> sequence, int startPosition,
            int endPosition, String tag, String cssClass) {
        LinkedList<String> result = (LinkedList<String>) sequence.clone();
        StringBuilder tagBuilder = new StringBuilder();
        tagBuilder.append("<");
        tagBuilder.append(tag);
        if (cssClass != null) {
            tagBuilder.append(" class=\"");
            tagBuilder.append(cssClass);
            tagBuilder.append("\"");
        }
        tagBuilder.append(">");
        String startTag = tagBuilder.toString();
        
        tagBuilder.delete(0, tagBuilder.length());
        
        tagBuilder.append("</");
        tagBuilder.append(tag);
        tagBuilder.append(">");
        String endTag = tagBuilder.toString();
        
        result.add(startPosition, startTag);
        result.add(endPosition, endTag);
        return result;
    }
    
    
    public static String wrapInTag(String line, String tag, String cssClass) {
        StringBuilder tagBuilder = new StringBuilder();
        tagBuilder.append("<");
        tagBuilder.append(tag);
        if (cssClass != null) {
            tagBuilder.append(" class=\"");
            tagBuilder.append(cssClass);
            tagBuilder.append("\"");
        }
        tagBuilder.append(">");
        String startTag = tagBuilder.toString();
        
        tagBuilder.delete(0, tagBuilder.length());
        
        tagBuilder.append("</");
        tagBuilder.append(tag);
        tagBuilder.append(">");
        String endTag = tagBuilder.toString();
        
        return startTag + line + endTag;
    }
    
    /**
     * The helper method for joining collections
     * @param <T>
     * @param objs the collection to join
     * @param delimiter the delimiter to use
     * @return the joined string
     */
    private static <T> String join(final Iterable<T> objs, final String delimiter) {
        Iterator<T> iter = objs.iterator();
        if (!iter.hasNext()) {
            return "";
        }
        StringBuffer buffer = new StringBuffer(String.valueOf(iter.next()));
        while (iter.hasNext()) {
            buffer.append(delimiter).append(String.valueOf(iter.next()));
        }
        return buffer.toString();
    }
}
