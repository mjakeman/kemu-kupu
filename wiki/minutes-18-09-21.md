# Meeting Minutes

## Meeting agenda:
 * Determine next tasks
 * Status update
 * Delegate

## Next Tasks
 * Finalise what our views look like
 * Write the game logic / Hook everything up
 * Implement the design using FXML/CSS
   * Game
   * Home
   * Preferences
   * Results
   * End Game
 * Write report (individual, 1000 words)

## Status Update
All previously decided tasks completed:
 * GUI Boilerplate and basic FXML views implemented
 * SpeechManager and Festival Integration
 * WordList class and reading from disk

Project structure is fairly settled. Most of the core functionality is done, excluding game logic.

### Matthew
Implemented SpeechManager which wraps festival and allows text-to-speech tasks to be queued. These tasks run
synchronously and in the background as to not free the GUI. There is still probably some level of refactoring/tweaking
to be done, but it should be feature-complete at this point.

### Jordan
Implemented the word list class, which essentially gets the title, description, and list of words to spell for each
topic. Refactoring may be needed as the game logic gets built. Overall, core logic is done.

### Raymond
Created basic skeleton layout of views. Loading of resources (e.g. fonts, fxml) is done. Established project structure
and worked on gradle build.

## Delegate
### Design (Wireframes)
**Assignee: Everyone**
We will work on this now, during the meeting

### Menu/Layout/Scene Transitions
Assignee: Matt
* Have fun lol
* Implementing complex-ish layout transitions, scenes, etc
* Basic views due for A3
* Implement topic selection screen
* Layout system due for Project
* Providing ongoing support to Raymond and Jordan

### Core Logic
Assignee: Raymond
* Implementing the general flow of the program
* Game code and logic

### Game Views
Assignee: Jordan
* Implement Game Screen/Results Screen/End Game Screen
* FXML + CSS

## Things to Ask (Tuesday Lecture)
 * Can we have 3 select speed settings or does it need to be any possible speed?
 * If the word is wrong, can we show the user the correct spelling once they skip?

## Next Meeting
Next Meeting Wednesday @ 5PM-6PM

Aim to have done by meeting:
 * Jordan: Get reasonably final views done
 * Matthew: Menu and Topic Selection (View/Logic)
 * Raymond: Game logic done

For A3, following days will focus on pairing "final" views with game logic.

Lots of QA (testing/bug-fixing/polish)
