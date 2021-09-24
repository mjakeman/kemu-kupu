# Meeting Minutes

## Meeting agenda:
* Team standup
* Current progress
* Next steps


## Team Standup

### Matt

* PR Merged (which is very nice :))
* Disabled preferences button
* Made quit button work
* Added group info to title screen
* Added placeholder stock image of the UOA Clocktower
* We're successfully passing the topic description
* NOT DONE: Topic randomisation not currently done (move to Final Project Milestone)
* Added Milestone on GH

### Jordan

* Created word lists under `words/`
* Finished screens with the text
* Added score placeholders and encouraging message context
* Play again button on the reward screen added
* Confirmed that speed did not need to be implemented for A3

### Raymond

* Each controller now has a function to populate the view data on the view's initialisation
* Game logic is all done, and encapsulated inside two classes `Round` and `Game`.
* To data dynamically, added another abstraction called `ViewModel` to interact between `Controllers` and `Models`
* All game logic and the view's Jordan created are now integrated together
* Scoring feature is also done


# Things Still to Do

* To show the right spelling, after that word round
* Decide on how presentation will be done - Sunday / Monday
* Add simple transitions to the other views
* Find out how to get the jar to read `words` directory when double clicking and make executable
* @DELAYED TO PROJECTS: Randomisation of topics
* Add timer between result screen and next guess screen