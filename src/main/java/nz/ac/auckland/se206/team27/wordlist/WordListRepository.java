package nz.ac.auckland.se206.team27.wordlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
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
     * @return The {@link File} associated with the {@code words} directory.
     */
    public static File getWordListDir() throws URISyntaxException {
        File wordsDir = new File("words");
        if (wordsDir.isDirectory()) {
            return wordsDir;
        }
        wordsDir = new File(WordList.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(), "words");
        System.out.println(wordsDir);
        return wordsDir;
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
        try {
            File[] wordListFiles = getWordListDir().listFiles();

            // Is null when the directory does not exist or is not a directory
            if (wordListFiles == null) {
                return new ArrayList<>();
            }

            return Arrays.stream(wordListFiles).map(File::getName).collect(Collectors.toList());
        } catch (URISyntaxException e) {
            return new ArrayList<>();
        }
    }

}
