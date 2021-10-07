package nz.ac.auckland.se206.team27.view.viewmodel;

import nz.ac.auckland.se206.team27.game.Game;
import nz.ac.auckland.se206.team27.game.Round;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;
import nz.ac.auckland.se206.team27.view.dto.EndGameScreenDto;
import nz.ac.auckland.se206.team27.view.dto.GuessScreenDto;
import nz.ac.auckland.se206.team27.view.dto.ResultScreenDto;

/**
 * Class to manage displaying the {@link Game} model for different screens, as well as manipulating the model for
 * varying view inputs.
 * <p>
 * NB: Ideally this file would be split such that each view / screen has its own ViewModel, but since there is not much
 * content, we can merge these a bit. The specific functions used for each screen is labelled.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GameViewModel implements ViewModel {

    private final Game currentGame;


    public GameViewModel(Game game) {
        this.currentGame = game;
    }

    /*
     * The following are used in GUESS screen.
     */

    public GuessScreenDto getGuessScreenData() {
        Round round = currentGame.getCurrentRound();
        return new GuessScreenDto(currentGame.getTopic(),
                                  round.getWord(),
                                  currentGame.getNumberOfRounds(),
                                  currentGame.getCurrentRoundIndex(),
                                  round.getGuessesRemaining(),
                                  round.isFirstGuess(),
                                  // Show hint when it is not the first guess
                                  !round.isFirstGuess());
    }

    /**
     * Submits a word for comparison, and checks if the result is correct or not.
     *
     * @return If another chance for guess is available.
     */
    public boolean makeGuess(String word) {
        return currentGame.getCurrentRound().makeGuess(word);
    }

    public void skipCurrentWord() {
        currentGame.getCurrentRound().markSkipped();
    }

    /*
     * The following are used in the RESULT screen.
     */

    public ResultScreenDto getResultScreenData() {
        Round round = currentGame.getCurrentRound();
        return new ResultScreenDto(currentGame.hasNextWord(),
                                   currentGame.getTopic(),
                                   round.getWord(),
                                   round.getResult(),
                                   currentGame.getCumulativeScore(),
                                   round.getScoreContribution(),
                                   null,
                                   null);
    }

    public void loadNextWord() {
        currentGame.toNextWord();
    }

    /*
     * The following are used in the END_GAME screen.
     */

    public EndGameScreenDto getEndGameScreenData() {
        return new EndGameScreenDto(currentGame.getTopic(),
                                    currentGame.getCumulativeScore());
    }

    public void playAgain() {
        Game.createInstance(currentGame.getWordList(), currentGame.isPracticeMode());
    }

}
