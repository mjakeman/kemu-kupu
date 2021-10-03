# Minutes
03-10-2021

## Team Standup

- We did the presentation and took a little break to relax a bit before tackling Final Project.
- Presentation went well

## Discussion

- **Everyone** to start on their individual reports
- Aim to finish project (code base) before A4 submission (and submit for A4). Finish user manual in the following week.
- Discussed Catherine's (client) feedback, and came to the following conclusions
  - We need to put more emphasis on colourblind friendly design / colour scheme
  - We need to incorporate more Te Reo Maori in the interface
  
- Discussed what the "help tool" needs to be, actioned to ask on Piazza (**@Matt**)

- Discussed having a "maximum time cap" for each round. Concluded that we wouldn't because 
  we want to incentivise people to think deeply about the correct answer, and use the multiple listen feature.
  Instead, we opted for a "score floor" of 1/2 the maximum points per round if the user takes longer than 30 seconds to answer.
  
- Discussed that for preferences, we should save these to the file system so that they remain the same after loading the application again

### Combined Features / Improvements List

#### Requirements
 1. Make scoring for game module dependent on time taken to answer the question (consider some kind of curve which could aid discussion?)
      - We should have 1000 points per round maximum
      - Minimum of 500 regardless of your time on your first try
      - Minimum score is applied at 30 seconds
      - Log-esque curve to model the reduction of score over time
      - On second try, a multiplier of 2/3 is applied. We chose 2/3 because this was an acceptable penalty without being too punitive.

![score curve](https://github.com/SOFTENG206-2021/assignment-3-and-project-team-27/blob/main/wiki/03-10-21%20desmos_score_modelling.png?raw=true)

 2. Preferences menu with the following options
      - Colourblindness toggle (switches all colours) INTEGRATED
      - Text to speech default speed (stick to 3 button box) INTEGRATED
      - TTS speed should be globally applied
      - Credits and our names ;)

File should look like:

 ```
 game_speed=FAST|SLOW|NORMAL
 colourblind_mode=true|false
 ```

 3. Practice module (unscored with more letter hints) with corresponding menu integration
      - No timing
      - No letter hints
      - KISS: Make it clear it's a practice by putting text on the screen next to the topic
   
 4. Make rewards screen actually a "rewards" screen (with trophy +1?) and display list of spelt and incorrect words from that round
      - Show trophy image / image of some sort
      - Show words with corresponding time taken to get that word correct (if applicable) and score associated with it
      - Time should be cumulative across both attempts (but not rounds)
 
 5. Help screen TBC - *NB: This could wait beyond the A4 deadline*

#### Extra
 1. Randomise encouraging messages +1
 2. Improve word hints to a) display mistakes with punctuation input errors (highlight red) and b) handle input more robustly +1
 3. Sort topic list alphabetically
 4. Add application icon
 5. Topic randomisation selection
 6. Sound Theme (Alter sound depending on outcome) - extend `SpeechManager` with audio playback tasks to avoid overlapping

 - Dynamic background to main menu screen
 - Global scoreboard on rewards screen
 - More Te Reo Maori in feedback provided (potentially game wide toggle which switches between Maori and English)
 - Definitions for words


## Action Items

1. REQ scoring (**@Ray**)
2. REQ preferences / preference option integration (**@Matt**)
3. REQ practice mode (**@Ray**)
4. REQ rewards screen (**@Matt**)
5. EXT randomise encouraging messages (**@Jordan**)
6. EXT improve word hints (**@Jordan**)
7. EXT alphabetical list sort (**@Jordan**)
8. EXT add application icon (**@Jordan**)
9. EXT topic randomisation (**@Jordan**)
10. EXT sound theme (**@Jordan/@Matt**)

**Meet at Friday 11AM**




