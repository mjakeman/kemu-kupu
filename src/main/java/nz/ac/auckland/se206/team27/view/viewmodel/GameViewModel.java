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
 *
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

    /**
     * Gets data to populate the GUESS screen.
     *
     * @return Data object
     */
    public GuessScreenDto getGuessScreenData() {
        Round round = currentGame.getCurrentRound();

        // Starts the round timer when the next game is active
        // TODO: Move this to a better place to execute such that it will only run once when the round starts instead of on data load (model?)
        round.startRoundTimer();

        return new GuessScreenDto(currentGame.getTopic(),
                currentGame.isPracticeMode(), round.getWord(),
                currentGame.getNumberOfRounds(),
                // Incrementing wordIndex by 1 to have this ready for display (1 indexed)
                currentGame.getCurrentRoundIndex() + 1,
                round.getGuessesRemaining(),
                round.getHints(),
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

    /**
     * Marks the current word as skipped and moves to the next word.
     */
    public void skipCurrentWord() {
        currentGame.getCurrentRound().markSkipped();
    }

    /*
     * The following are used in the RESULT screen.
     */

    /**
     * Gets data to populate the RESULT screen.
     *
     * @return Data object
     */
    public ResultScreenDto getResultScreenData() {
        Round round = currentGame.getCurrentRound();
        return new ResultScreenDto(currentGame.hasNextWord(),
                                   currentGame.getTopic(),
                                   round.getWord(),
                                   round.getResult(),
                                   currentGame.getCumulativeScore(),
                                   round.getScoreContribution(),
                                   currentGame.isPracticeMode(),
                                   null,
                                   null);
    }

    public void loadNextWord() {
        currentGame.toNextWord();
    }

    /*
     * The following are used in the END_GAME screen.
     */

    /**
     * Gets data to populate the END_GAME screen.
     *
     * @return Data object
     */
    public EndGameScreenDto getEndGameScreenData() {
        return new EndGameScreenDto(currentGame.getTopic(),
                                    currentGame.getCumulativeScore(),
                                    currentGame.getAllRounds(),
                                    currentGame.isPracticeMode());
    }

    /**
     * Play another game or practice session using the same topic list.
     */
    public void playAgain() {
        Game.createInstance(currentGame.getWordList(), currentGame.isPracticeMode());
    }

}
