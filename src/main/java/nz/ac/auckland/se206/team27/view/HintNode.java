package nz.ac.auckland.se206.team27.view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;

/**
 * Custom JavaFX element (Use {@link HintNode#getNodes()} for the JavaFX Node
 * elements) to display a string as a monospaced label where each letter can be
 * customised.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HintNode {

    /**
     * List of {@link Label} nodes to be displayed on the JavaFX pane.
     */
    private final List<Label> labels = new ArrayList<>();


    public HintNode(String word, String input, boolean showHint) {
        for (int i = 0; i < word.length(); i++) {
            labels.add(new Label());
        }

        setWord(word, input, showHint);
    }

    /**
     * Sets the word (obfuscated) and input, along with the hint if required.
     */
    public void setWord(String word, String input, boolean showHint) {
        String processHint = getHintString(word, input, showHint);

        int minChanges = Math.min(processHint.length(), labels.size());

        for (int i = 0; i < minChanges; i++) {
            labels.get(i).setText(Character.toString(processHint.charAt(i)));
        }
        if (showHint && input.length() > 1 && input.charAt(1) != word.charAt(1)) {
            labels.get(1).getStyleClass().add("letter-not-match");
        } else {
            labels.get(1).getStyleClass().clear();
        }
    }

    /**
     * @return The list of {@link Label} nodes.
     */
    public List<Label> getNodes() {
        return labels;
    }

    /**
     * @return The hint string for the round.
     *
     * @param word The actual spelling of the word.
     * @param input The current user input.
     * @param showHint If a hint (2nd letter) should be shown.
     */
    private static String getHintString(String word, String input, boolean showHint) {
        String underscores = word
                // Replace all letters from the te reo alphabet with underscores
                .replaceAll("[a-zA-ZāēīōūĀĒĪŌŪ]", "_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < underscores.length(); i++) {
            if (i < input.length()) {
                sb.append(input.charAt(i));
            } else {
                sb.append(underscores.charAt(i));
            }
        }

        if (showHint) {
            sb.setCharAt(1, word.charAt(1));
        }

        return sb.toString();
    }

}
