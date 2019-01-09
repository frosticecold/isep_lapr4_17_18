package pt.isep.nsheets.client.application.workbook;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.table.cell.WidgetColumn;
import org.apache.tapestry.components.Conditional;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression.ConditionalInfo;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression.ConditionalManager;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.ext.UIController;
import pt.isep.nsheets.client.application.s1.n1160701.conditionalFormat.ConditionalFormatView;
import pt.isep.nsheets.client.application.workbook.WorkbookView.SheetCell;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.ext.ExtensionManager;
import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowExtension;

import java.util.ArrayList;
import java.util.List;

public class SheetWidgetColumn extends WidgetColumn<SheetCell, MaterialLabel> {

    /**
     * The lowest character to be used in a column name
     */
    public static final char LOWEST_CHAR = 'A';

    /**
     * The highest character to be used in a column name
     */
    public static final char HIGHEST_CHAR = 'Z';

    private WorkbookView view = null;

    private int colNumber = -1;

    private UIController uiController = new UIController();

    public String getColumnName() {
        String columnStr;
        int tempColumn = this.colNumber;
        for (columnStr = ""; tempColumn >= 0; tempColumn = tempColumn
                / (HIGHEST_CHAR - LOWEST_CHAR + 1) - 1) {
            columnStr = (char) ((char) (tempColumn % (HIGHEST_CHAR
                    - LOWEST_CHAR + 1)) + LOWEST_CHAR) + columnStr;
        }
        return columnStr;
    }

    // instance initialize
    public SheetWidgetColumn(int column, WorkbookView view) {
        this.view = view;
        this.colNumber = column;
        if (this.colNumber >= 0) {
            this.setName(getColumnName());
        }
    }

    @Override
    public TextAlign textAlign() {
        return TextAlign.CENTER;
    }

    @Override
    public MaterialLabel getValue(SheetCell object) {
        MaterialLabel badge = new MaterialLabel();
        if (this.colNumber == -1) {
            badge.setText("" + (object.getCell(0).getAddress().getRow() + 1));
        } else {
            badge.setText(object.getCell(this.colNumber).getValue().toString());
        }

        badge.setLayoutPosition(Style.Position.RELATIVE);


        if( this.colNumber < 0){

        }else {

            if (badge.getValue().contains("-")) {

                //YELLOW
                if( uiController.showCurrentExtensionColor().toString().compareToIgnoreCase("YELLOW") == 0)
                    badge.setBackgroundColor(uiController.showCurrentExtensionColor());
            }


            for( Cell c : ConditionalManager.getInstace().getRangedCells()){
                if(   object.getCell(this.colNumber).toString().equals(c.toString())){

                    for( ConditionalInfo info : ConditionalManager.getInstace().getInfos() ){

                        if( c.toString().equals(info.getConditionalCell().toString())){

                            ConditionalFormatView cf = new ConditionalFormatView();

                            //              Window.alert(" @" + object.getCell(this.colNumber));

                            if( cf.evaluate(c, info.getOperator(), info.getValue()) ){

                                badge.setBackgroundColor(info.getTrueColor());

                            }else{

                                badge.setBackgroundColor(info.getFalseColor());

                            }

                        }
                    }


                }
            }


        }




        return badge;
    }

    @Override
    public MaterialLabel render(Context context, SheetCell object) {

        MaterialLabel widget = getValue(object);

        // Add a click handler...
        widget.addClickHandler(event -> {
            if (context.getColumn() > 0) {
                Cell newCell=object.getCell(context.getColumn()-1);

                this.view.setActiveCell(newCell,widget);
//				this.view.getTable().getTableTitle().setText(object.getCell(context.getColumn()-1).toString()+": "+object.getCell(context.getColumn()-1).getContent().toString());
//				this.view.getFirstBox().setText(object.getCell(context.getColumn()-1).getContent().toString());

                //widget.setTextColor(uiController.showCurrentTextColorExtension());
            }
        });

        return widget;
    }
}
