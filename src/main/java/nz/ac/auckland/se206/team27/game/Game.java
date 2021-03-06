package nz.ac.auckland.se206.team27.game;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;
import nz.ac.auckland.se206.team27.wordlist.WordList;

/**
 * A data model for managing the state of a game.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class Game {

    /**
     * The topic for the game.
     */
    private final String topic;

    /**
     * The word list that was used to generate this game.
     */
    private final WordList wordList;

    /**
     * List of rounds to be tested in this game.
     */
    private final List<Round> rounds;

    /**
     * Whether the game will be run in practice mode.
     */
    private final boolean isPracticeMode;

    /**
     * The index value of the current word (if counter is -1, the game has not
     * started yet).
     */
    private int roundIndex = 0;

    private static Game _instance = null;


    /**
     * Returns the current active instance of the game class.
     */
    public static Game getInstance() {
        if (_instance != null) return _instance;

        throw new IllegalCallerException("No instance of \"game\" has been created yet!");
    }

    public static void createInstance(WordList wordList, boolean isPracticeMode) {
        _instance = new Game(wordList, 5, 2, isPracticeMode);
    }

    /**
     * Creates a new game with words selected from the topic list.
     * <p>
     * NB: Causes side effect (re-orders {@link WordList} words). We can fix
     * this copying the list of word strings, but I think we can live with
     * this side effect rather than consuming more memory.
     */
    private Game(WordList wordList, int wordCount, int maxGuesses, boolean isPracticeMode) {
        topic = wordList.getTitle();
        this.wordList = wordList;
        this.isPracticeMode = isPracticeMode;

        // Shuffle the wordlist to randomise word selection
        List<String> allWords = wordList.getWordList();
        Collections.shuffle(allWords);
        rounds = allWords.subList(0, Math.min(wordCount, allWords.size())).stream()
                .map((word) -> new Round(word, maxGuesses, isPracticeMode))
                .collect(Collectors.toList());

        _instance = this;
    }

    public boolean isPracticeMode() {
        return isPracticeMode;
    }

    /**
     * @return The currently active round.
     */
    public Round getCurrentRound() {
        return rounds.get(roundIndex);
    }

    /**
     * Get all rounds in the game as a {@link List} for
     * use with various list-displaying controls.
     *
     * @return A list of all rounds
     */
    public List<Round> getAllRounds() {
        return rounds;
    }

    public int getNumberOfRounds() {
        return rounds.size();
    }

    public int getCurrentRoundIndex() {
        return roundIndex;
    }

    public String getTopic() {
        return topic;
    }

    public WordList getWordList() {
        return wordList;
    }

    /**
     * @return The score collected up to this current round.
     */
    public int getCumulativeScore() {
        return rounds.subList(0, roundIndex + 1).stream().mapToInt(Round::getScoreContribution).sum();
    }

    /**
     * Checks if there is another word after the current word.
     */
    public Boolean hasNextWord() {
        return roundIndex + 1 < rounds.size();
    }

    /**
     * Skips to the next word.
     *
     * @throws IllegalCallerException If no next word exists.
     */
    public void toNextWord() {
        if (hasNextWord()) {
            roundIndex++;
        } else {
            throw new IllegalCallerException("Could not go to next word, no such word exists!");
        }
    }

}
