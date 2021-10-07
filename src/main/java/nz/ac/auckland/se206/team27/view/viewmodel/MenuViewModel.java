package nz.ac.auckland.se206.team27.view.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se206.team27.game.Game;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.view.dto.TopicPreviewScreenDto;
import nz.ac.auckland.se206.team27.view.dto.TopicPreviewScreenDto.ImageDto;
import nz.ac.auckland.se206.team27.wordlist.WordList;
import nz.ac.auckland.se206.team27.wordlist.WordListRepository;

/**
 * Class to populate the topic selection screen(s).
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class MenuViewModel implements ViewModel {

    private final Map<String, WordList> wordLists;

    private static MenuViewModel _instance;

    private WordList selectedList = null;

    private boolean isPracticeMode = false;


    /**
     * @return a single instance of this class (to prevent having to read from disk multiple times).
     *
     * NB: There is possibly a better solution to this, but for now this will work.
     */
    public static MenuViewModel getInstance() {
        _instance = _instance == null ? new MenuViewModel(new WordListRepository()) : _instance;
        return _instance;
    }

    // TODO: Change to repo singleton with a wordList cache
    private MenuViewModel(WordListRepository repo) {
        this.wordLists = repo.getWordLists();
    }


    /*
     * The following are used for the main menu screen.
     */

    /**
     * Sets the practice mode.
     */
    public void setPracticeMode(boolean practiceMode) {
        isPracticeMode = practiceMode;
    }

    /*
     * The following are used for the topic selection screen.
     */

    /**
     * @return a list of all selectable topics.
     */
    public List<String> getTopics() {
        return new ArrayList<>(wordLists.keySet());
    }

    /**
     * Selects a topic to be used.
     */
    public void selectTopic(String topic) {
        selectedList = this.wordLists.get(topic);
    }

    /*
     * The following are used for the topic preview screen.
     */

    public TopicPreviewScreenDto getTopicPreviewData() {
        String imgUrl = ResourceUtil.getResourceUrl("media/University_of_Auckland_Clock_Tower.png").toString();
        String externalLink = "https://commons.wikimedia.org/wiki/File:University_of_Auckland_Clock_Tower.jpg";
        ImageDto image = new ImageDto("Colin Rose", "CC BY 2.0", imgUrl, externalLink);
        return new TopicPreviewScreenDto(isPracticeMode, selectedList.getTitle(), selectedList.getDescription(), image);
    }

    /**
     * Starts a game with the selected list.
     */
    public void startGameWithCurrentTopic() {
        Game.createInstance(selectedList, isPracticeMode);
    }

}
