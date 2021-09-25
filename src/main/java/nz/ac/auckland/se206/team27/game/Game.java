package nz.ac.auckland.se206.team27.game;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * The index value of the current word (if counter is -1, the game has not
     * started yet).
     */
    private int roundIndex = 0;

    /**
     * The user's preferred speed for text-to-speech playback.
     * TODO: This should be a global preference.
     */
    private SpeechSpeed speechSpeed = SpeechSpeed.NORMAL;

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
        topic = wordList.getTitle();
        this.wordList = wordList;

        // Shuffle the wordlist to randomise word selection
        List<String> allWords = wordList.getWordList();
        Collections.shuffle(allWords);
        rounds = allWords.subList(0, Math.min(wordCount, allWords.size())).stream()
                .map((word) -> new Round(word, maxGuesses))
                .collect(Collectors.toList());

        _instance = this;
    }

    /**
     * @return The currently active round.
     */
    public Round getCurrentRound() {
        return rounds.get(roundIndex);
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
     * @return Return the user's preferred speed
     */
    public SpeechSpeed getSpeechSpeed() {
        return speechSpeed;
    }

    /**
     * @param speed The user's preferred playback speed
     */
    public void setSpeechSpeed(SpeechSpeed speed) {
        speechSpeed = speed;
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
