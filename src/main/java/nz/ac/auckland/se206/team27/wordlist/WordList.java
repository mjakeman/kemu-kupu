package nz.ac.auckland.se206.team27.wordlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @author Jordan York (jyor212@aucklanduni.ac.nz)
 */
public class WordList {

    //Create fields to hold topic title, description and wordlist
    private final String title;
    private final String description;
    private final ArrayList<String> wordList = new ArrayList<>();


    public WordList(String fileName) throws IOException {
        // Locate file of topic
        File topicFile;
        try {
            topicFile = new File(WordListRepository.getWordListDir(), fileName);
        } catch (URISyntaxException e) {
            throw new IOException();
        }

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
