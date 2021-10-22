package nz.ac.auckland.se206.team27.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Organises Wordlists from textfiles into splitting them into titles, descriptions
 * and the words available to spell
 *
 * @author Jordan York (jyor212@aucklanduni.ac.nz)
 */
public class WordList {

    //Create fields to hold topic title, description and wordlist
    private final String title;
    private final String description;
    private final ArrayList<String> wordList = new ArrayList<>();


    public WordList(String fileName) throws IOException {
        // Locate file of topic
        File topicFile = new File(WordListRepository.WORDS_DIRECTORY, fileName);

        //Create new reader to start reading the file
        BufferedReader fileScanner = new BufferedReader(new FileReader(topicFile));

        //First line in file is topic so read it and assign to title field
        title = fileScanner.readLine();

        //Second line in file is description so read it and assign to description field
        description = fileScanner.readLine();

        //The remaining lines in file are words to be spelt. Read all of these and add them to wordlist field
        fileScanner.lines().forEach(wordList::add);

        //Stop reading file
        fileScanner.close();
    }

    public ArrayList<String> getWordList() {

        //Retrieve wordlist
        return wordList;
    }

    /**
     * Gets title of Wordlist
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets Description of Wordlist
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

}
