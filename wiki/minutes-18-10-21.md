# Minutes
18-10-2021

## Team Standup
N/A

## Agenda

* Revisit team agreement
* Go through user feedback
* Discuss competition demo details
* User manual/help screen
* Use zoom rather than slide for returning to main menu

## Discussion

* Revisted team agreement and updated parts of it to match our team's currently agreed working
  style. The team agreement needed minimal changes and has been respected and maintained by all
  team members.
* Create a google doc for the help manual and begin working on that from tonight.
* Add a help button to the menu (underneath preferences) -> This will open the user manual PDF in
  the user's default pdf viewer. Elementary comes with evince.

### User Feedback
Discussed feedback from peer reviews:
* Colourblind colours were somewhat confusing to users (e.g. pink for success)
  * We decided to keep the "safe" colour scheme and mention in the user manual what
    the effect of the colourblind mode setting is, as we do not want to reduce contrast
    by changing this colour.
* Commenting in the code
  * Need to comment the entire codebase by Friday. Aim to fill out the gaps by Thursday night.
* Quit button within a specific game
  * We decided to implement this as it was highly requested by all users who reviewed our game. This
    button will likely be placed in the lower corner of the game screen and allows the user to exit
    a game at any point.
* Data-clumps/big methods
  * Will aim to reduce big methods where possible, however it is too late in the development process
    to change how the `populateViewData` methods work.
  * Extract some logic into separate methods to improve this somewhat.
* Click for confetti is shown when confetti is disabled
  * Respect "special-effects" preference
* Results table
  * The results table is not entirely optimal. We have a recent change which improves the sizing somewhat,
    however it is not feasible to rework it entirely. It may be worth investigating other minor changes to
    ensure all columns fit on screen properly.
* Buttons are sensitive while hidden on the results screen
* Preferences screen needs an image and credits information
* Poor variable name in ChooseTopicController

### Competition Demo
 * We all want to talk
 * Six and a half minutes
 * No slides (that's boring)
 * Features to show
   * Topic selection with images for each topic  @JORDAN
   * One run through of a game (colours) @JORDAN
     * Demonstrate Speech Speed
     * Textbox shake
     * Scoring (vary time to answer)
     * Results table colouring
     * Auto-continue button
     * Buttons become insensitive while festival is playing
     * Confetti!
   * Turn on colourblind mode/Turn off confetti @MATT
   * Second run through with some prepopulated data @MATT
     * Show how colourblind mode affects the results table at the end
   * Final run practice mode @RAYMOND
     * Practice mode doesn't have any scoring
     * Quit halfway
   * End off demo with button in bottom of menu that brings up a "Vote for Team 27" screen with confetti @RAYMOND
 * Raymond will run demo
 * One third of the presentation each
 * **Meet tomorrow at 11am for practice run**

## Action Items
 * Commenting - **Thursday**
   * Use style `// contents` (have a space between slashes and text)
   * `controller` @RAY (sorry!)
   * `view`/`resource` @MATT
   * other directories @JORDAN
 * Data-clumps/big-methods @unassigned - **Thursday Night**
 * Buttons are sensitive @MATT - **Monday**
 * Poor variable name @MATT - **Monday**
 
### Competition Action Items
 * Make "Vote for Team 27" screen @MATT - **Monday**
 * Hook up help button with sample PDF @RAYMOND - **Monday**
    * Use platform/host/whatever services to open a file with the default handler? 
 * Confetti label @MATT - **Monday**
 * Preferences screen image @JORDAN - **Monday**
 * Zoom transition for results screen @MATT - **Monday**
 * Quit Button @JORDAN - **Monday**
