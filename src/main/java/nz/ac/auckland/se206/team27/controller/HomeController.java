package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.resource.Screen;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HomeController extends BaseController {

    /**
     * Corresponding action executed when the "New Game" button is clicked.
     */
    public void clickNewGame() {
        System.out.println("New game!");
        sceneLoader.loadScreen(Screen.GAME);
    }

    /**
     * Corresponding action executed when the "Practice" button is clicked.
     */
    public void clickPractice() {
        System.out.println("Practice!");
        sceneLoader.loadScreen(Screen.GAME);
    }

    /**
     * Corresponding action executed when the "Preferences" button is clicked.
     */
    public void clickPreferences() {
        System.out.println("Preferences");
        sceneLoader.loadScreen(Screen.PREFERENCES);
    }

    /**
     * Corresponding action executed when the "Quit" button is clicked.
     */
    public void clickQuit() {
        closeApplication();
    }

}
