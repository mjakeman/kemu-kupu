package nz.ac.auckland.se206.team27.wordlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jordan York (jyor212@aucklanduni.ac.nz)
 */
public class WordList {

    //Create fields to hold topic title, description and wordlist
    private String title;
    private String description;
    private ArrayList<String> wordList;

    public WordList(String fileName) throws FileNotFoundException {
        //Locate file of topic
        String filePath = new File("").getAbsolutePath() + "/words/" + fileName;
        File topicFile = new File(filePath);

        //Set wordlist to be new
        wordList = new ArrayList<String>();

        //Create string to hold each word in the list
        String wordForLoop;

        //Create new scanner to start reading the file
        Scanner fileScanner = new Scanner(topicFile);

        //First line in file is topic so read it and assign to title field
        title = fileScanner.nextLine();

        //Second line in file is description so read it and assign to description field
        description = fileScanner.nextLine();

        //The remaining lines in file are words to be spelt. Read all of these and add them to wordlist field
        while (fileScanner.hasNext()) {
            wordForLoop = fileScanner.nextLine();
            wordList.add(wordForLoop);
        }

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

}
