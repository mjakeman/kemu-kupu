package nz.ac.auckland.se206.team27.game;

import nz.ac.auckland.se206.team27.view.EndGameScreenDto;
import nz.ac.auckland.se206.team27.view.GameScreenDto;
import nz.ac.auckland.se206.team27.view.ResultScreenDto;
import nz.ac.auckland.se206.team27.wordlist.WordList;

import java.util.LinkedHashMap;

import static nz.ac.auckland.se206.team27.game.GameManager.SubmitResult.*;

/**
 * Class to manage all game related content and view populations.
 *
 * @author Raymond Feng (rf.raymondfeng)
 */
public class GameManager {

    private static GameManager _gameManager = null;

    private Game currentGame;

    private final int maxGuesses = 2;

    private int guess;


    private GameManager() {}

    /**
     * Returns the current instance of the manager class.
     */
    public static GameManager getInstance() {
        _gameManager = _gameManager == null ? new GameManager() : _gameManager;
        return _gameManager;
    }

    /**
     * Starts a new game using the {@link WordList} provided.
     */
    public void startNewGame(WordList wordList) {
        currentGame = new Game(wordList, 5);
    }

    public EndGameScreenDto getEndGameScreenData() {
        // TODO: Replace this with scoring system and words
        return new EndGameScreenDto(currentGame.getTopic(), new LinkedHashMap<>());
    }

    public ResultScreenDto getResultScreenData() {
        // TODO: Replace this with scoring system and words
        return new ResultScreenDto(currentGame.hasNextWord(), currentGame.getTopic(), currentGame.getCurrentWord());
    }

    public GameScreenDto getGameScreenData() {
        int guessesRemaining = maxGuesses - guess;
        return new GameScreenDto(currentGame.getTopic(),
                                 currentGame.getCurrentWord(),
                                 currentGame.getWordCount(),
                                 currentGame.getCurrentWordIndex(),
                                 guessesRemaining);
    }

    /**
     * Submits a word for comparison, and checks if the result is correct or not.
     */
    // TODO: Test if this works for macrons
    public SubmitResult submitWord(String word) {
        guess++;

        if (currentGame.getCurrentWord().equalsIgnoreCase(word)) {
            guess = 0;
            return CORRECT;
        } else if (guess > maxGuesses) {
            return INCORRECT;
        } else {
            return REDO;
        }
    }

    public void giveUpOnWord() {
        guess = 0;
    }

    public void loadNextWord() {
        currentGame.getNextWord();
    }

    /*
     * Inner types
     */

    public enum SubmitResult {

        REDO,
        CORRECT,
        INCORRECT

    }

}
