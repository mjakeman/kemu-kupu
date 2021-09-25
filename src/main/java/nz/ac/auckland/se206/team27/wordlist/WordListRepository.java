package nz.ac.auckland.se206.team27.wordlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class WordListRepository {

    /**
     * The directory containing all the word lists.
     */
    public static File WORDS_DIRECTORY;
    static {
        File wordsDir = new File("words");
        if (wordsDir.isDirectory()) {
            WORDS_DIRECTORY = wordsDir;
        } else {
            // Get the root directory by the current code source (JAR file)
            File rootDir = new File(WordList.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
            WORDS_DIRECTORY = new File(rootDir, "words");
        }
    }


    /**
     * Returns a map of {@link WordList}s with the key as the list's title.
     */
    public Map<String, WordList> getWordLists() {
        return getAllWordListFileNames().stream()
                // Create a wordlist from every wordlist file name
                .map((name) -> {
                    try {
                        return new WordList(name);
                    } catch (FileNotFoundException e) {
                        System.err.println("[Error] No word list file exists with name: " + name);
                        return null;
                    } catch (IOException e) {
                        System.err.println("[Error] An unknown IO error occurred");
                        e.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull)
                // Map each WordList object to a HashMap with key as title and content as the WordList object
                .collect(Collectors.toMap(WordList::getTitle, Function.identity()));
    }

    /**
     * @return a list of all topics.
     */
    private List<String> getAllWordListFileNames() {
        File[] wordListFiles = WORDS_DIRECTORY.listFiles();

        // Is null when the directory does not exist or is not a directory
        if (wordListFiles == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(wordListFiles).map(File::getName).collect(Collectors.toList());
    }

}
