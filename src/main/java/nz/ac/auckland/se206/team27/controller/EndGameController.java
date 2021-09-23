package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.view.EndGameScreenDto;

public class EndGameController extends GameController {

    // TODO: Implement this
    @Override
    protected void populateViewData() {
        EndGameScreenDto data = gameViewModel.getEndGameScreenData();
        System.out.println("Load data");
    }

}
