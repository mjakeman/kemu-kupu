# Working Log
Raymond Feng (rfen629)


## 16 September

- Created a basic structure for working on views, including enums for the different views and how these can be loaded.
- Created controllers for each of these views with limited functionality (mainly just switching screens)
- Tested linking CSS and images inside the FXML files, and these appear to work

## 17 September

- Created new build structure using gradle that allows developers to develop on any platform, and automates downloading
  JavaFX dependencies.
- Added task to generate "fat" standalone, executable jars.

## 18 September

- Brainstormed with team on implementing the view and next steps

## 19 September

- Added functionality to retrieve multiple topics and wordlists from a `words` directory.
- Put in practice nifty Java 8 feature of using streams to do list processing
- Built this functionality of top of Jordan's `WordList` class.

## 22 September

- Restructured controllers to load data dynamically on screen load.
- Created game logic inside `Game` class.
- Created `GameViewModel` to abstract away changes between views/controllers and the model.

## 23 September

- Integrated topic selection with ViewModel and repository.
- Created more ViewModel methods in preparation for integrating the game section.

## 24 September

- Merged Jordan's changes into a new branch and started work on integrating game logic and game views
- Implemented a scoring feature and encouraging messages

## 25 September

- Worked on incorporating a word hint selector, which is binded to a custom class housing a set of labels 
  (so that each character is its own individual label).
- Made the `words` directory findable regardless of where you executed the jar file from.
- Made the result and end game screens consistent with the current game screen

## 07 October

- Completed feature with score being based on timing
- Added practice mode menu option without scoring
- Completed feature with multiple hints in practice mode
- Started work on refactoring the folder structure

## 10 October

- Simplified some of the code inside `EndGameController` to reduce code duplication. 
  This was accomplished by using passing lambda functions and generics, which was a good learning experience.
- Refactored some of the directories so that all view related content was inside the `view` directory

## 18 October

- Added a button to the home screen to launch the help manual, in preparation for us completing the help manual.

## 20 October

- Worked with the team to write up our help manual.
- Removed voting related components as the competition was over.

## 23 October

- Added lots of comments and refactoring inside the `controller` package.