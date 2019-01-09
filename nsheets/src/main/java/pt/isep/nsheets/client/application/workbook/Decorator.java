package pt.isep.nsheets.client.application.workbook;


import com.google.gwt.user.client.ui.DecoratorPanel;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowCell;
import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowExtension;

/**
 * Decorator.java
 *
 * A static way to decorate a Extensible Field.
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 31/05/2018
 */
public class Decorator {

    public static Spreadsheet decorate(Spreadsheet decorable, Extension extension){


        return extension.extend(decorable);
    }

    public static Cell decorate(Cell decorable, Extension extension) {

        return extension.extend(decorable);


    }



}
