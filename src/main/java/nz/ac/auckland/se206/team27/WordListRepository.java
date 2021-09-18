package nz.ac.auckland.se206.team27;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class WordListRepository {

    private static final String WORDS_DIRECTORY = "words";


    /**
     * Returns a map of {@link WordList}s with the key as the list's title.
     */
    public Map<String, WordList> getWordLists() {
        return getAllWordListFileNames().stream()
                // Create a wordlist from every wordlist file name
                .map(WordList::new)
                // Map each WordList object to a HashMap with key as title and content as the WordList object
                .collect(Collectors.toMap(WordList::getTitle, Function.identity()));
    }

    /**
     * Returns a list of all topics.
     */
    private List<String> getAllWordListFileNames() {
        File wordsDir = new File(WORDS_DIRECTORY);

        File[] wordListFiles = wordsDir.listFiles();

        // Is null when the directory does not exist or is not a directory
        if (wordListFiles == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(wordListFiles).map(File::getName).collect(Collectors.toList());
    }

}
