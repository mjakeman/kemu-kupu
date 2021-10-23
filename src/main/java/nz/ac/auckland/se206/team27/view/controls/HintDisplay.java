package nz.ac.auckland.se206.team27.view.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 * Custom JavaFX element (Use {@link HintDisplay#getNodes()} for the JavaFX Node
 * elements) to display a string as a monospaced label where each letter can be
 * customised.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HintDisplay {

    /**
     * The characters allowed in a valid word, excluding all punctuation.
     */
    // TODO: Move this somewhere else since it is used in Round to determine hints
    public static final String ALPHABET_REGEX = "[a-zA-ZāēīōūĀĒĪŌŪ]";

    /**
     * List of {@link Label} nodes to be displayed on the JavaFX pane.
     */
    private final List<Label> labels = new ArrayList<>();

    /**
     * Map of all hints.
     */
    private final Map<Integer, Character> hints;

    /**
     * Toggle whether hints should be enabled for this node.
     */
    private final boolean showHint;

    /**
     * The current word to display.
     */
    private final String word;

    /**
     * Creates a new {@link HintDisplay} control.
     *
     * @param word The complete word that is being hinted at.
     * @param hints A map of hint characters.
     * @param showHint Whether the hint(s) should be shown.
     */
    public HintDisplay(String word, Map<Integer, Character> hints, boolean showHint) {
        this.word = word;
        this.hints = hints;
        this.showHint = showHint;

        for (int i = 0; i < word.length(); i++) {
            labels.add(new Label());
        }

        // Set the text to the hint
        setLabelContents("");
    }

    /**
     * Sets the word (obfuscated) and input, along with the hint if required.
     */
    // TODO: Rename this method
    public void overrideWithUserInput(String input) {
        setLabelContents(input);
    }

    /**
     * @return The list of {@link Label} nodes.
     */
    public List<Label> getNodes() {
        return labels;
    }

    /**
     * Sets a string to exist inside the label objects with priority on input.
     * 
     * @param input The user's current input.
     */
    private void setLabelContents(String input) {
        // Replace all letters from the te reo alphabet with underscores
        String base = word.replaceAll(ALPHABET_REGEX, "_");

        for (int i = 0; i < word.length(); i++) {
            Label currentLabel = labels.get(i);
            ObservableList<String> labelStyles = currentLabel.getStyleClass();

            // Put the input string but flag any differences between input string and hints
            // TODO: Consider making grammar part of the hints string so they get flagged
            char letter;
            boolean flagged;

            Character letterHint = hints.get(i);

            // If there are input characters, prioritise these and flag if they are different from the hints
            if (input.length() > i) {
                letter = input.charAt(i);
                flagged = showHint && letterHint != null && letterHint != letter;
            } else {
                letter = showHint && letterHint != null ? letterHint : base.charAt(i);
                flagged = false;
            }

            currentLabel.setText(Character.toString(letter));

            // If the character is flagged, give it a different style
            if (flagged) {
                labelStyles.add("letter-not-match");
            } else {
                labelStyles.clear();
            }
        }
    }

}
