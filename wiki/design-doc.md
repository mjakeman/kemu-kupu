# Design Doc

## Target Audience
A young adult (18-25 years old), recently moved to New Zealand, who wants to
improve their knowledge about te reo Māori.

## Word List Format
* One word list per topic
* Filenames should be 'Linux-Friendly' (all lower case, no spaces)
  * Prefer dashes over underscores
* The first line of the word list is the topic name (human readable form)
* Second line is a short "blurb" describing the topic. This may be left blank.
* All successive lines (line 3 onwards) are words to be spelt

## Services

### Text To Speech
Assignee: Matt

* Looks like: `talk(String phraseToSpeak, speed: float)`
* Uses Festival under the hood
* Have a class which abstracts away the implementation
* Have a talk() method
* Async/Threading to call Festival
* Use a queue-based system to avoid overlapping calls to Festival
* Investigate if we can get the "completion" status of Festival so we can wait/terminate
* (Optional?) Allow for the playback speed to be adjusted via GUI (Slider vs Play Fast/Normal/Slow)

## Views / Screens
* Home screen with option selection
* Game state screen
* Result screen (per-round)
* Reward screen (per-game)
* Preference Selection on Home Page

### Game View
* Have macron vowel buttons so the user can enter special characters without the necessary keyboard layout.
* "The app must accept macrons in the spelling, and mark accordingly. If the word with
  a macronised vowel is spelt without the macron it must be marked wrong."
* Clearly show number of letters in word (for now, just use textfield) - INVESTIGATE
* Each game has five words to spell (could be a preference?)

_To Clarify at next design meeting: Can the user see the correct spelling after getting both attempts wrong?_

### Help View/Pop-Up
Consider having a pop-up that dims the current view and displays instructions on how to use it.
This will be context-aware, and could potentially include a screenshot if helpful.

## Controllers
* One controller per view

## Repository
Assignee: Jordan

`class WordList { ... some kind of list of strings + topic + blurb ... }`

* WordRepository -> This is the word list loader
* `getTopics(): List<String>`
* `getWordsWithTopic(String topic)`

```
serializeWordList(String pathToFile) {
    return some kind of String[]    
}

HashMap<String, WordList>
Topics -> Word Lists

map.getKeys()
Selects a key
We retrieve the value (word list) associated
Play!

If we use a collection vs String[]
we can use shuffle to randomise word order
```

## Animations & Transitions
Inter-Scene Transitions
 * Transition between one scene and another scene
 * Sliding/cross-fading/etc

Animations
 * Subtle control transformations (one place to another)
 * These will play when a scene "enters" (i.e. is navigated to)