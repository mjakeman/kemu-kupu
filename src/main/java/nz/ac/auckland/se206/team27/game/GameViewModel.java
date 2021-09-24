package nz.ac.auckland.se206.team27.game;

import java.util.LinkedHashMap;

import nz.ac.auckland.se206.team27.game.Game.GuessResult;
import nz.ac.auckland.se206.team27.view.EndGameScreenDto;
import nz.ac.auckland.se206.team27.view.GameScreenDto;
import nz.ac.auckland.se206.team27.view.ResultScreenDto;

/**
 * Class to manage displaying the {@link Game} model for different screens, as
 * well as manipulating the model for varying view inputs.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GameViewModel {

    private final Game currentGame;


    public GameViewModel(Game game) {
        this.currentGame = game;
    }

    public EndGameScreenDto getEndGameScreenData() {
        return new EndGameScreenDto(currentGame.getTopic(), new LinkedHashMap<>());
    }

    public ResultScreenDto getResultScreenData() {
        return new ResultScreenDto(currentGame.hasNextWord(),
                                   currentGame.getTopic(),
                                   currentGame.getCurrentWord(),
                                   0,
                                   0,
                                   "Good work!",
                                   null,
                                   null);
    }

    public GameScreenDto getGameScreenData() {
        return new GameScreenDto(currentGame.getTopic(),
                                 currentGame.getCurrentWord(),
                                 currentGame.getWordCount(),
                                 currentGame.getCurrentWordIndex(),
                                 currentGame.getGuessesRemaining());
    }

    /**
     * Submits a word for comparison, and checks if the result is correct or not.
     */
    // TODO: Test if this works for macrons
    public GuessResult submitWord(String word) {
        return currentGame.makeGuess(word);
    }

    public void giveUpOnWord() {
        currentGame.toNextWord();
    }

    public void loadNextWord() {
        currentGame.toNextWord();
    }

}
