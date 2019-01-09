package pt.isep.nsheets.shared.ext;

/**
 * RunTimeExtentionManager.java
 *
 * Class used to have the color of runtime extensions.
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 01/06/2018
 */
public class RunTimeExtentionManager {

    /**
     * Default color for YellowExtension
     */
    private String yellowExtensionColor = "YELLOW";

    private String conditionalExtensionColor = "RED";

    private String textColorExtension = "BLUE";

    private String borderExtension = "SOLID";
    
    private final String commentExtension = "Comment";

    private static final String IMAGE_EXTENSION = "Image";

    public RunTimeExtentionManager() {
    }

    public String currentYellowExtensionColor() {

        return yellowExtensionColor;
    }

    public String currentConditionalExtensionColor() {
        return conditionalExtensionColor;
    }

    public void changeYellowExtensionColor(String yellowExtensionColor) {
        this.yellowExtensionColor = yellowExtensionColor;
    }

    public void changeConditionalExtensionColor(String booleanExtensionColor) {
        conditionalExtensionColor = booleanExtensionColor;
    }

    public void changeBorderExtension(String borderExtension) {
        borderExtension = borderExtension;
    }

    public String currentBorderExtension() {
        return borderExtension;
    }

    public void changeTextColorExtension(String textColorExtension) {
        this.textColorExtension = textColorExtension;
    }

    public String currentTextColorExtension() {
        return textColorExtension;
    }
}
