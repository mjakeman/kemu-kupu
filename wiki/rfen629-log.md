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