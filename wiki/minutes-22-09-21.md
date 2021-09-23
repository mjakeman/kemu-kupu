# Meeting Minutes

## Meeting agenda:
* Team standup
* Current progress
* Next steps


## Team Standup

### Matt

* Made views for menu:
  * Topic selection screen
  * Topic preview screen
  * Preferences screen
* Reworked stylesheets
  * One master stylesheet and a stylesheet per related view
* Created some reusable layout components
* Prototyped a transition system
  * Created a few preset transitions
  * Allow granularity when selecting which transitional elements to apply

### Jordan

* Created separate CSS files for the views
* Progress: Decent starting block, not quite finished, need a few pieces of help (@Matt)
* Small adjustments to the FXML files to get the right formatting (as close to what we drew up at the last meeting)


### Ray

* Created WordListRepository to get word lists for all topics dynamically
* Currently working on game logic and "View Models"
* Created data models for each of the views based on game logic


## Decisions made

* Disregard the duplicate / loan word lists
* Set next meeting time as Friday 8PM
* To show the right spelling, after that word round

## Action items

* Raymond to finish data population for controllers - @Ray, 22nd night
* Decide on how presentation will be done - Sunday / Monday
* Add simple transitions to the other views
* Create word list files - @Jordan, 22nd
* Deploy jar / README - @Ray Sunday

* Menu Screen: @Matthew
  * To review @Matt's PR's (for screens) - 22nd 
  * Disable preferences button - 22nd
  * Add topic selection and randomisation - 23rd
  * Topic description is needed on the preview screen - 23rd
  * Make quit button work - 22nd
  * Find placeholder stock image (applies across everything) - 22nd
  * Add group credits / info - 22nd

* Game Screen:
  * Finish screens @Jordan, 22nd
  * Double check if we need to be able to change the speed of playback for A3 @Jordan

* Result Screen:
  * Need scoring information and implementation, @Ray, 23rd
  * Score this round: / Total socre: labels @Jordan
  * Need encouraging message - @Jordan 23rd
  * Note: May need to use a timer to go from the result screen to the next screen Saturday, 

* Rewards / End game screen:
  * Play again button @Jordan 22nd

* Friday/Saturday night - join view and implementation @EVERYONE

* @EVERYONE to finish their individual reports
  * Self and peer evaluation attached inside the individual report
