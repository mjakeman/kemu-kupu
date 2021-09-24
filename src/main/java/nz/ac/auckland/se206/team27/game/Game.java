package nz.ac.auckland.se206.team27.game;

import java.util.Collections;
import java.util.List;

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
     * List of words to be tested in this game.
     */
    private final List<String> words;

    /**
     * Original wordlist used to start the game.
     */
    private final WordList wordList;

    /**
     * The index value of the current word (if counter is -1, the game has not
     * started yet).
     */
    private int wordIndex = 0;

    /**
     * The maximum number of guesses allowed per word, before the word is marked
     * as failed.
     */
    private final int maxGuesses;

    /**
     * The number of guesses that have currently been made.
     */
    // TODO: Move this into the words map once scoring system is complete
    private int guessCounter = 0;

    /**
     * The result of the last round.
     */
    // TODO: Move this into the words map once scoring system is complete
    private RoundResult lastRoundResult = null;

    private static Game _instance = null;


    /**
     * Returns the current active instance of the game class.
     */
    public static Game getInstance() {
        if (_instance != null) return _instance;

        throw new IllegalCallerException("No instance of \"game\" has been created yet!");
    }

    public static void createInstance(WordList wordList) {
        _instance = new Game(wordList, 5, 2);
    }

    /**
     * Creates a new game with words selected from the topic list.
     * <p>
     * NB: Causes side effect (re-orders {@link WordList} words). We can fix
     * this copying the list of word strings, but I think we can live with
     * this side effect rather than consuming more memory.
     */
    private Game(WordList wordList, int wordCount, int maxGuesses) {
        this.maxGuesses = maxGuesses;
        topic = wordList.getTitle();

        this.wordList = wordList;

        // Shuffle the wordlist to randomise word selection
        List<String> allWords = wordList.getWordList();
        Collections.shuffle(allWords);
        words = allWords.subList(0, Math.min(wordCount, allWords.size()));

        _instance = this;
    }

    /**
     * Returns the current word.
     */
    public String getCurrentWord() {
        if (wordIndex >= words.size()) return null;

        return words.get(wordIndex);
    }

    public int getWordCount() {
        return words.size();
    }

    public int getCurrentWordIndex() {
        return wordIndex;
    }

    public String getTopic() {
        return topic;
    }

    public RoundResult getLastRoundResult() {
        return lastRoundResult;
    }

    public WordList getWordList() {
        return wordList;
    }

    /**
     * @return The number of guesses remaining for the current word.
     */
    public int getGuessesRemaining() {
        return maxGuesses - guessCounter;
    }

    /**
     * Returns whether a word is the same as the previous word, and if there is
     * another turn remaining after the guess.
     *
     * @return if there is another guess available.
     */
    public boolean makeGuess(String word) {
        guessCounter++;
        if (getCurrentWord().equalsIgnoreCase(word)) {
            lastRoundResult = (guessCounter == 1) ? RoundResult.PASSED : RoundResult.FAULTED;
            return false;
        } else if (guessCounter >= maxGuesses) {
            lastRoundResult = RoundResult.FAILED;
            return false;
        }

        return true;
    }

    /**
     * Sets the current word to {@link RoundResult#SKIPPED}.
     */
    public void markCurrentWordSkipped() {
        lastRoundResult = RoundResult.SKIPPED;
    }

    /**
     * Checks if there is another word after the current word.
     */
    public Boolean hasNextWord() {
        return wordIndex + 1 < words.size();
    }

    /**
     * Skips to the next word.
     *
     * @throws IllegalCallerException If no next word exists.
     */
    public void toNextWord() {
        // Reset guess counter
        guessCounter = 0;
        if (hasNextWord()) {
            wordIndex++;
        } else {
            throw new IllegalCallerException("Could not go to next word, no such word exists!");
        }
    }

}
