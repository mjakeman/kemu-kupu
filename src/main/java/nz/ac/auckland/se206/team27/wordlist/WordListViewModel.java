package nz.ac.auckland.se206.team27.wordlist;

import nz.ac.auckland.se206.team27.game.Game;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.view.TopicPreviewScreenDto;
import nz.ac.auckland.se206.team27.view.TopicPreviewScreenDto.ImageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to populate the topic selection screen(s).
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class WordListViewModel {

    private final Map<String, WordList> wordLists;

    private static WordListViewModel _instance;

    private WordList selectedList = null;


    /**
     * @return a single instance of this class (to prevent having to read from
     * disk multiple times).
     */
    public static WordListViewModel getInstance() {
        _instance = _instance == null ? new WordListViewModel(new WordListRepository()) : _instance;
        return _instance;
    }

    public WordListViewModel(WordListRepository repo) {
        this.wordLists = repo.getWordLists();
    }

    public TopicPreviewScreenDto getTopicPreviewData() {
        String imgUrl = ResourceUtil.getResourceUrl("media/University_of_Auckland_Clock_Tower.png").toString();
        String externalLink = "https://commons.wikimedia.org/wiki/File:University_of_Auckland_Clock_Tower.jpg";
        ImageDto image = new ImageDto("Colin Rose", "CC BY 2.0", imgUrl, externalLink);
        return new TopicPreviewScreenDto(selectedList.getTitle(), selectedList.getDescription(), image);
    }

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

    /**
     * Starts a game with the selected list.
     */
    // TODO: Move this somewhere else
    public void startGameWithCurrentTopic() {
        Game.createInstance(selectedList);
    }

}
