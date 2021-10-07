package nz.ac.auckland.se206.team27.view.dto;

/**
 * Data object for populating the topic preview screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class TopicPreviewScreenDto implements ViewDto {

    public final String name;
    public final String description;
    public final ImageDto image;
    public final boolean isPractice;


    public TopicPreviewScreenDto(boolean isPractice, String name, String description, ImageDto image) {
        this.isPractice = isPractice;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    /*
     * Inner types
     */

    /**
     * Data object for modelling an image for the topic preview screen.
     */
    public static class ImageDto implements ViewDto {

        public final String creator;
        public final String copyright;
        public final String imgUrl;
        public final String externalLink;


        public ImageDto(String creator, String copyright, String imgUrl, String externalLink) {
            this.creator = creator;
            this.copyright = copyright;
            this.imgUrl = imgUrl;
            this.externalLink = externalLink;
        }

    }

}
