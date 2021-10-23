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

    /**
     * Creates a new {@link TopicPreviewScreenDto}.
     *
     * @param isPractice Whether practice mode is enabled
     * @param name Topic label
     * @param description Topic description
     * @param image Topic image data object
     */
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

        /**
         * Creates a new {@link ImageDto}.
         *
         * @param creator The copyright holder of the image
         * @param copyright The licence the image is under
         * @param imgUrl The URL of the image resource
         * @param externalLink A link to the original image/website
         */
        public ImageDto(String creator, String copyright, String imgUrl, String externalLink) {
            this.creator = creator;
            this.copyright = copyright;
            this.imgUrl = imgUrl;
            this.externalLink = externalLink;
        }

    }

}
