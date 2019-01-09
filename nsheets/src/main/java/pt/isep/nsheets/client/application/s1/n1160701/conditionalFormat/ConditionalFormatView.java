/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.s1.n1160701.conditionalFormat;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.lang.Equal;
import pt.isep.nsheets.shared.core.formula.lang.GreaterThan;
import pt.isep.nsheets.shared.core.formula.lang.GreaterThanOrEqual;
import pt.isep.nsheets.shared.core.formula.lang.LessThan;
import pt.isep.nsheets.shared.core.formula.lang.LessThanOrEqual;
import pt.isep.nsheets.shared.ext.ExtensionManager;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class ConditionalFormatView {

    public ConditionalFormatView() {
    }

    List<BinaryOperator> operations = new ArrayList<>();
    List<Color> colorList = new ArrayList<>();
    List<Style.BorderStyle> borderslist = new ArrayList<>();

    public List<BinaryOperator> getOperations() {
        return operations;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public List<Color> listColor() {
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.BLUE);
        colorList.add(Color.ORANGE);
        return colorList;
    }

    public List<BinaryOperator> listBinaryOperations() {
        operations.add(new Equal());
        operations.add(new GreaterThan());
        operations.add(new GreaterThanOrEqual());
        operations.add(new LessThan());
        operations.add(new LessThanOrEqual());
        return operations;
    }
    
   
			  
     public List<Style.BorderStyle> listBorders() {
         for (Style.BorderStyle operation : Style.BorderStyle.values()) {
             borderslist.add(operation);
         }
         return borderslist;
     }


    public boolean evaluate(Cell cell, BinaryOperator operator, String number) {

        String cellvalue = cell.getValue().toString().replace(" ", "");
        number = number.replace(" ", "");

        if(number == null || operator == null || cell == null){
            return true;
        }

        boolean b;
        if (operator.getIdentifier().equals("=")) {

            b = cellvalue.equals(number);

            if( cellvalue.contains("-")){

                b = (Integer.parseInt(cellvalue) == Integer.parseInt(number));
            }

        } else if (operator.getIdentifier().equals(">")) {

            b = (cellvalue.compareTo(number) > 0);


            if( cellvalue.contains("-")){

                b = (Integer.parseInt(cellvalue) > Integer.parseInt(number));
            }



        } else if (operator.getIdentifier().equals("<")) {
            b = (cellvalue.compareTo(number) < 0);


            if( cellvalue.contains("-")){

                b = (Integer.parseInt(cellvalue) < Integer.parseInt(number));
            }

        } else if (operator.getIdentifier().equals("<=")) {
            b = (cellvalue.compareTo(number) <= 0);


            if( cellvalue.contains("-")){

                b = (Integer.parseInt(cellvalue) <= Integer.parseInt(number));
            }
        } else {
            b = (cellvalue.compareTo(number) >= 0);


            if( cellvalue.contains("-")){

                b = (Integer.parseInt(cellvalue) >= Integer.parseInt(number));
            }
        }




        return b;
    }

    public boolean setBackgroundColor(Cell cell, BinaryOperator operator, String number, Color cT, Color cF, MaterialLabel m1) {


        if (evaluate(cell, operator, number)) {
            m1.setBackgroundColor(cT);
            ExtensionManager.getInstance().getRunTimeSettings().changeYellowExtensionColor(cT.name());
            MaterialToast.fireToast("True Condition : " + cT.name());
            return true;
        } else {
            m1.setBackgroundColor(cF);
            ExtensionManager.getInstance().getRunTimeSettings().changeYellowExtensionColor(cF.name());
            MaterialToast.fireToast("False Condition : " + cF.name());
            return false;
        }

    }

    public boolean setTextColor(Cell cell, BinaryOperator operator, String number, Color cT, Color cF, MaterialLabel m1) {
        if (evaluate(cell, operator, number)) {
            m1.setTextColor(cT);
            ExtensionManager.getInstance().getRunTimeSettings().changeTextColorExtension(cT.name());
             MaterialToast.fireToast("True Condition : " + cT.name());
            return true;
        } else {
            m1.setTextColor(cF);
            ExtensionManager.getInstance().getRunTimeSettings().changeTextColorExtension(cF.name());
            MaterialToast.fireToast("False Condition : " + cF.name());
            return false;
        }

    }
    
        public boolean setBorderStyle(Cell cell, BinaryOperator operator, String number, Style.BorderStyle bT,  Style.BorderStyle bF, MaterialLabel m1) {
        if (evaluate(cell, operator, number)) {
            m1.setBorder(bT.getCssName());
            ExtensionManager.getInstance().getRunTimeSettings().changeBorderExtension(bT.name());
             MaterialToast.fireToast("True Condition : " + bT.name());
            return true;
        } else {
            m1.setBorder(bF.getCssName());
            ExtensionManager.getInstance().getRunTimeSettings().changeBorderExtension(bF.name());
            MaterialToast.fireToast("False Condition : " + bF.name());
            return false;
        }

    }


}
