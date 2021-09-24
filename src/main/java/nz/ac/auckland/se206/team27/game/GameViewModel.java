package nz.ac.auckland.se206.team27.game;

import java.util.LinkedHashMap;

import nz.ac.auckland.se206.team27.resource.ScreenResource;
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
                                   currentGame.getLastRoundResult(),
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
     * USED ON {@link ScreenResource#GUESS}.
     * <p>
     * Submits a word for comparison, and checks if the result is correct or not.
     *
     * @return If another chance for guess is available.
     */
    // TODO: Test if this works for macrons
    public boolean submitWord(String word) {
        return currentGame.makeGuess(word);
    }

    /**
     * USED ON {@link ScreenResource#GUESS}.
     */
    public void giveUpOnWord() {
        currentGame.skipCurrentWord();
    }

    /**
     * USED ON {@link ScreenResource#RESULT}.
     */
    public void loadNextWord() {
        currentGame.toNextWord();
    }

    /**
     * USED ON {@link ScreenResource#END_GAME}.
     */
    public void playAgain() {
        Game.createInstance(currentGame.getWordList());
    }

}
