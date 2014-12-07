
package filediff;

import filediff.myers.MyersDiff;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DiffUtils {
    private static DiffAlgorithm defaultDiffAlgorithm = new MyersDiff();
    private static Pattern unifiedDiffChunkRe =
            Pattern.compile("^@@\\s+-(?:(\\d+)(?:,(\\d+))?)\\s+\\+(?:(\\d+)(?:,(\\d+))?)\\s+@@$");

    public static Patch diff(List<?> original, List<?> revised) {
        return DiffUtils.diff(original, revised, defaultDiffAlgorithm);
    }

    
    public static Patch diff(List<?> original, List<?> revised, DiffAlgorithm algorithm) {
        return algorithm.diff(original, revised);
    }

    public static List<?> patch(List<?> original, Patch patch) throws PatchFailedException {
        return patch.applyTo(original);
    }

    
    public static List<?> unpatch(List<?> revised, Patch patch) {
        return patch.restore(revised);
    }

    
    public static Patch parseUnifiedDiff(List<String> diff) {
        boolean inPrelude = true;
        List<Object[]> rawChunk = new ArrayList<Object[]>();
        Patch patch = new Patch();

        int old_ln = 0, new_ln = 0;
        String tag;
        String rest;
        for (String line : diff) {
            // Skip leading lines until after we've seen one starting with '+++'
            if (inPrelude) {
                if (line.startsWith("+++")) {
                    inPrelude = false;
                }
                continue;
            }
            Matcher m = unifiedDiffChunkRe.matcher(line);
            if (m.find()) {
                // Process the lines in the previous chunk
                if (rawChunk.size() != 0) {
                    List<String> oldChunkLines = new ArrayList<String>();
                    List<String> newChunkLines = new ArrayList<String>();

                    for (Object[] raw_line : rawChunk) {
                        tag = (String) raw_line[0];
                        rest = (String) raw_line[1];
                        if (tag.equals(" ") || tag.equals("-")) {
                            oldChunkLines.add(rest);
                        }
                        if (tag.equals(" ") || tag.equals("+")) {
                            newChunkLines.add(rest);
                        }
                    }
                    patch.addDelta(new Changediff(new Segment(old_ln - 1, oldChunkLines),
                            new Segment(new_ln - 1, newChunkLines)));
                    rawChunk.clear();
                }
                // Parse the @@ header
                old_ln = m.group(1) == null ? 1 : Integer.parseInt(m.group(1));
                new_ln = m.group(3) == null ? 1 : Integer.parseInt(m.group(3));

                if (old_ln == 0) {
                    old_ln += 1;
                }
                if (new_ln == 0) {
                    new_ln += 1;
                }
            } else {
                if (line.length() > 0) {
                    tag = line.substring(0, 1);
                    rest = line.substring(1);
                    if (tag.equals(" ") || tag.equals("+") || tag.equals("-")) {
                        rawChunk.add(new Object[]{tag, rest});
                    }
                } else {
                    rawChunk.add(new Object[]{" ", ""});
                }
            }
        }

        // Process the lines in the last chunk
        if (rawChunk.size() != 0) {
            List<String> oldChunkLines = new ArrayList<String>();
            List<String> newChunkLines = new ArrayList<String>();

            for (Object[] raw_line : rawChunk) {
                tag = (String) raw_line[0];
                rest = (String) raw_line[1];
                if (tag.equals(" ") || tag.equals("-")) {
                    oldChunkLines.add(rest);
                }
                if (tag.equals(" ") || tag.equals("+")) {
                    newChunkLines.add(rest);
                }
            }

            patch.addDelta(new Changediff(new Segment(old_ln - 1, oldChunkLines), new Segment(
                    new_ln - 1, newChunkLines)));
            rawChunk.clear();
        }

        return patch;
    }

    
    public static List<String> generateUnifiedDiff(String original, String revised,
                                                   List<String> originalLines, Patch patch, int contextSize) {
        if (!patch.getDeltas().isEmpty()) {
            List<String> ret = new ArrayList<String>();
            ret.add("--- " + original);
            ret.add("+++ " + revised);

            List<Diff> patchDeltas = new ArrayList<Diff>(patch.getDeltas());

            // code outside the if block also works for single-delta issues.
            List<Diff> deltas = new ArrayList<Diff>(); // current list of Diff's to process
            Diff delta = patchDeltas.get(0);
            deltas.add(delta); // add the first Diff to the current set
            // if there's more than 1 Diff, we may need to output them together
            if (patchDeltas.size() > 1) {
                for (int i = 1; i < patchDeltas.size(); i++) {
                    int position = delta.getOriginal().getPosition(); // store the current position of
                    // the first Diff

                    // Check if the next Diff is too close to the current position.
                    // And if it is, add it to the current set
                    Diff nextDelta = patchDeltas.get(i);
                    if ((position + delta.getOriginal().size() + contextSize) >=
                            (nextDelta.getOriginal().getPosition() - contextSize)) {
                        deltas.add(nextDelta);
                    } else {
                        // if it isn't, output the current set,
                        // then create a new set and add the current Diff to it.
                        List<String> curBlock = processDeltas(originalLines, deltas, contextSize);
                        ret.addAll(curBlock);
                        deltas.clear();
                        deltas.add(nextDelta);
                    }
                    delta = nextDelta;
                }

            }
            // don't forget to process the last set of Deltas
            List<String> curBlock = processDeltas(originalLines, deltas, contextSize);
            ret.addAll(curBlock);
            return ret;
        }
        return new ArrayList<String>();
    }


    private static List<String> processDeltas(List<String> origLines, List<Diff> deltas,
                                              int contextSize) {
        List<String> buffer = new ArrayList<String>();
        int origTotal = 0; // counter for total lines output from Original
        int revTotal = 0;  // counter for total lines output from Original
        int line;

        Diff curDelta = deltas.get(0);

        // NOTE: +1 to overcome the 0-offset Position
        int origStart = curDelta.getOriginal().getPosition() + 1 - contextSize;
        if (origStart < 1) {
            origStart = 1;
        }

        int revStart = curDelta.getRevised().getPosition() + 1 - contextSize;
        if (revStart < 1) {
            revStart = 1;
        }

        // find the start of the wrapper context code
        int contextStart = curDelta.getOriginal().getPosition() - contextSize;
        if (contextStart < 0) {
            contextStart = 0; // clamp to the start of the file
        }

        // output the context before the first Diff
        for (line = contextStart; line < curDelta.getOriginal().getPosition(); line++) { //
            buffer.add(" " + origLines.get(line));
            origTotal++;
            revTotal++;
        }

        // output the first Diff
        buffer.addAll(getDeltaText(curDelta));
        origTotal += curDelta.getOriginal().getLines().size();
        revTotal += curDelta.getRevised().getLines().size();

        int deltaIndex = 1;
        while (deltaIndex < deltas.size()) { // for each of the other Deltas
            Diff nextDelta = deltas.get(deltaIndex);
            int intermediateStart = curDelta.getOriginal().getPosition()
                    + curDelta.getOriginal().getLines().size();
            for (line = intermediateStart; line < nextDelta.getOriginal().getPosition(); line++) {
                // output the code between the last Diff and this one
                buffer.add(" " + origLines.get(line));
                origTotal++;
                revTotal++;
            }
            buffer.addAll(getDeltaText(nextDelta)); // output the Diff
            origTotal += nextDelta.getOriginal().getLines().size();
            revTotal += nextDelta.getRevised().getLines().size();
            curDelta = nextDelta;
            deltaIndex++;
        }

        // Now output the post-Diff context code, clamping the end of the file
        contextStart = curDelta.getOriginal().getPosition()
                + curDelta.getOriginal().getLines().size();
        for (line = contextStart; (line < (contextStart + contextSize))
                && (line < origLines.size()); line++) {
            buffer.add(" " + origLines.get(line));
            origTotal++;
            revTotal++;
        }

        // Create and insert the block header, conforming to the Unified Diff standard
        StringBuffer header = new StringBuffer();
        header.append("@@ -");
        header.append(origStart);
        header.append(",");
        header.append(origTotal);
        header.append(" +");
        header.append(revStart);
        header.append(",");
        header.append(revTotal);
        header.append(" @@");
        buffer.add(0, header.toString());

        return buffer;
    }

    private static List<String> getDeltaText(Diff delta) {
        List<String> buffer = new ArrayList<String>();
        for (Object line : delta.getOriginal().getLines()) {
            buffer.add("-" + line);
        }
        for (Object line : delta.getRevised().getLines()) {
            buffer.add("+" + line);
        }
        return buffer;
    }

}
