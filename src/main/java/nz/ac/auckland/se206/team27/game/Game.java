package nz.ac.auckland.se206.team27.game;

import java.util.Collections;
import java.util.List;

import nz.ac.auckland.se206.team27.wordlist.WordList;

public class Game {

	/**
	 * The topic for the game.
	 */
	private final String topic;

    /**
     * List of words to be tested in this game.
     */
	private List<String> words;

    /**
     * The index value of the current word (if counter is -1, the game has not
	 * started yet).
     */
    private int counter = 0;


    /**
     * Creates a new game start with the word selection from the topic list.
     *
     * NB: Causes side effect (re-orders {@link WordList} words). We can fix
     *  this copying the list of word strings, but I think we can live with
     *  this side effect rather than consuming more memory.
     */
	public Game(WordList wordList, int wordCount) {
		// Shuffle the wordlist to randomise word selection
		Collections.shuffle(wordList.getWordList());

		topic = wordList.getTitle();

		// Set the words to display as either the wordCount, or the maximum number of words in the list
		words = wordList.getWordList().subList(0, Math.min(wordCount, words.size()));
	}

	/**
	 * Returns the current word.
	 */
	public String getCurrentWord() {
        if (counter >= words.size()) return null;

		return words.get(counter);
	}


	/**
	 * Checks if there is another word after the current word.
	 */
	public Boolean hasNextWord() {
        return counter + 1 < words.size();
	}

	/**
	 * Returns the next word or {@code null} if no more words exist.
	 */
	public String getNextWord() {
		if (hasNextWord()) {
            counter++;
            return getCurrentWord();
        }

        return null;
	}

	/**
	 * @return The number of words available in this game.
	 */
	public int getWordCount() {
		return words.size();
	}

	/**
	 *
	 * @return The current index (1-indexed) of the word.
	 */
	public int getCurrentWordIndex() {
		return counter;
	}

	/**
	 * @return The topic of the game.
	 */
	public String getTopic() {
		return topic;
	}

}
