package pt.isep.nsheets.client.application.Lapr.Red.n1161018.ext;

import gwt.material.design.client.constants.Color;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.CellExtension;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.ext.ExtensionManager;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294.ImageCell;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294.ImageExtension;

import java.util.ArrayList;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1160911.CommentsExtension.CommentExtension;

/**
 * UIController.java
 *
 * Special Controller used in the main project - csheets. CSHEETS EXPLANATION:
 *
 * " A controller for managing the current selection, i.e. the active workbook,
 * spreadsheet and cell, as well as for keeping track of modifications to
 * workbooks and of user interface extensions. " @author Einar Pehrson
 *
 *
 *
 * Is used as facade between ExtensionManager, which belongs into domain -shared
 * module-, and the User interface, which is in the client module.
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 02/06/2018
 */
public class UIController {

    /**
     * Has the informtation about the extensions.
     */
    private ExtensionManager myExtensionManager;

    public UIController() {

        loadExtensionManager();
    }

    public void changeYellowExtensionColor(String color) {

        myExtensionManager.getRunTimeSettings().changeYellowExtensionColor(color);
    }

    public Color showCurrentExtensionColor() {

        return Color.valueOf(myExtensionManager.getRunTimeSettings().currentYellowExtensionColor());
    }

    public void changeCurrentTextColorExtension(String color) {

        myExtensionManager.getRunTimeSettings().changeTextColorExtension(color);
    }

    public Color showCurrentTextColorExtension() {

        return Color.valueOf(myExtensionManager.getRunTimeSettings().currentTextColorExtension());
    }

    private void loadExtensionManager() {

        myExtensionManager = ExtensionManager.getInstance();
    }

    public Extension[] getAllExtensions() {

        return myExtensionManager.getExtensions();
    }

    //===========================================================================================================

    /**
     * @author David Camelo <1161294@isep.ipp.pt
     */

    /**
     *  List with array of image extensions
     */
    private ArrayList<ImageExtension> imageExtensions = new ArrayList<>();

    /**
     * Add image extension
     *
     * @param cellExtension Image extension
     * @return true if added
     */
    public boolean addImageExtension(ImageExtension cellExtension){
        boolean found = false;

        for (int i = 0; i < imageExtensions.size(); i++) {
            if(cellExtension.getCell().getAddress().toString().equals(
                    imageExtensions.get(i).getCell().getAddress().toString())){

                imageExtensions.set(i, cellExtension);
                return true;
            }
        }

        if(!found){
            imageExtensions.add(cellExtension);
            return true;
        }

        return false;
    }

    /**
     * Valid if add image extension is possible
     *
     * @param cell cell which is verified
     * @return true if no image extension in cell
     */
    public boolean validAddImageExtension(Cell cell){
        boolean found = false;

        for (int i = 0; i < imageExtensions.size(); i++) {
            if(cell.getAddress().toString().equals(
                    imageExtensions.get(i).getCell().getAddress().toString())){

                found = true;
            }
        }

        return !found;
    }

    /**
     * Gives the image path of a cell that has an image extension
     *
     * @param cell cell which has an image extension
     * @return path of image
     */
    public String pathOfImage(Cell cell){
        for (int i = 0; i < imageExtensions.size(); i++) {
            if(cell.getAddress().toString().equals(
                    imageExtensions.get(i).getCell().getAddress().toString())){

                return imageExtensions.get(i).getImageUrl();
            }
        }

        return null;
    }

    /**
     * Remove image extension of a given cell
     *
     * @param cell cell where image extension will be removed
     * @return true if removed
     */
    public boolean removeImageExtension(Cell cell){
        for (int i = 0; i < imageExtensions.size(); i++) {
            if(cell.getAddress().toString().equals(
                    imageExtensions.get(i).getCell().getAddress().toString())){

                imageExtensions.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * ===========================================================================================================
     */

        /** Rafael Teixeira <1160911@isep.ipp.pt> */
   
     private ArrayList<CommentExtension> commentExtensions = new ArrayList<>();
     
     
     /* adds comment extension */
     public boolean addCommentExtension(CommentExtension cExtension){
        boolean res = false;

        for (int i = 0; i < commentExtensions.size(); i++) {
            if(cExtension.getCell().getAddress().toString().equals(
                    commentExtensions.get(i).getCell().getAddress().toString())){

                commentExtensions.set(i, cExtension);
                return true;
            }
        }
        
        if(!res){
            commentExtensions.add(cExtension);
            return true;
        }

        return false;
    }
     
     public boolean validationCommentExtension(Cell c){
        boolean res = false;

        for (int i = 0; i < commentExtensions.size(); i++) {
            if(c.getAddress().toString().equals(
                    commentExtensions.get(i).getCell().getAddress().toString())){

                res = true;
            }
        }

        return !res;
    }
     
      public String commentCell(Cell cell){
        for (int i = 0; i < commentExtensions.size(); i++) {
            if(cell.getAddress().toString().equals(
                    commentExtensions.get(i).getCell().getAddress().toString())){

                return commentExtensions.get(i).getComment();
            }
        }

        return null;
    }
      
      public boolean removeCommentExtension(Cell c){
        for (int i = 0; i < commentExtensions.size(); i++) {
            if(c.getAddress().toString().equals(
                    commentExtensions.get(i).getCell().getAddress().toString())){

                commentExtensions.remove(i);
                return true;
            }
        }
        return false;
    }

}
