## Minutes

26-09-2021

## Team Standup

### Matt

* Made user-chosen text-to-speech speed persist across multiple rounds within a single game
* This should be replaced with a preferences keystore at a later date

### Jordan

* Implemented timer for automatically transitioning between results view and next guess/end game.

### Ray

* Fixed `words/` directory discovery
  * will first check working directory
  * next check for `words/` folder adjacent to jar file location
* Implementing word hint control which displays letters as underscores
  * Handles special characters like dashes, spaces, etc
  * Shows second letter as a hint on subsequent attempts
* Added colour and style consistency in the results and end game views to match guess view 


## Demo
Ray demonstrated his three pull requests
 - Reviewed and merged them during the meeting
 - Matt's PR was also tested and merged

## Validation
A final JAR file was generated for the program with all features implemented.
It was validated against the Assignment 3 brief.

## Packaging
The JAR file was packaged along with a script to run it. Instructions were added to the README.
