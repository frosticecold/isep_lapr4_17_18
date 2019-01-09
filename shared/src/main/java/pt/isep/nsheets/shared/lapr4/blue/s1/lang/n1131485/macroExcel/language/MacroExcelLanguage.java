package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macroExcel.language;

import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.lang.Adder;
import pt.isep.nsheets.shared.core.formula.lang.And;
import pt.isep.nsheets.shared.core.formula.lang.Average;
import pt.isep.nsheets.shared.core.formula.lang.Concatenator;
import pt.isep.nsheets.shared.core.formula.lang.Count;
import pt.isep.nsheets.shared.core.formula.lang.Divider;
import pt.isep.nsheets.shared.core.formula.lang.Do;
import pt.isep.nsheets.shared.core.formula.lang.Equal;
import pt.isep.nsheets.shared.core.formula.lang.MacroFunc;
import pt.isep.nsheets.shared.core.formula.lang.Exponentiator;
import pt.isep.nsheets.shared.core.formula.lang.Factorial;
import pt.isep.nsheets.shared.core.formula.lang.False;
import pt.isep.nsheets.shared.core.formula.lang.GreaterThan;
import pt.isep.nsheets.shared.core.formula.lang.GreaterThanOrEqual;
import pt.isep.nsheets.shared.core.formula.lang.If;
import pt.isep.nsheets.shared.core.formula.lang.LessThan;
import pt.isep.nsheets.shared.core.formula.lang.LessThanOrEqual;
import pt.isep.nsheets.shared.core.formula.lang.Multiplier;
import pt.isep.nsheets.shared.core.formula.lang.Negator;
import pt.isep.nsheets.shared.core.formula.lang.Not;
import pt.isep.nsheets.shared.core.formula.lang.NotEqual;
import pt.isep.nsheets.shared.core.formula.lang.Or;
import pt.isep.nsheets.shared.core.formula.lang.Percent;
import pt.isep.nsheets.shared.core.formula.lang.RangeReference;
import pt.isep.nsheets.shared.core.formula.lang.Subtracter;
import pt.isep.nsheets.shared.core.formula.lang.Sum;
import pt.isep.nsheets.shared.core.formula.lang.True;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang.Block;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang.Attribution;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class MacroExcelLanguage extends MacroLanguage{
    
    public MacroExcelLanguage(String name, String starter) {
        super(name, starter);
    }
    
     @Override
    protected void initFunctions() {
        functions.add(new Average());
        functions.add(new And());
        functions.add(new Count());
        functions.add(new Do());
        functions.add(new Factorial());
        functions.add(new False());
        functions.add(new If());
        functions.add(new Not());
        //functions.add(new NumericFunction());
        functions.add(new Or());
        functions.add(new Sum());
        functions.add(new True());
        functions.add((Function) new MacroFunc());
    }

    @Override
    protected void initBinaryOperators() {
        binaryOperators.add(new Adder());
        binaryOperators.add(new Concatenator());
        binaryOperators.add(new Divider());
        binaryOperators.add(new Equal());
        binaryOperators.add(new Exponentiator());
        binaryOperators.add(new GreaterThan());
        binaryOperators.add(new GreaterThanOrEqual());
        binaryOperators.add(new LessThan());
        binaryOperators.add(new LessThanOrEqual());
        binaryOperators.add(new Multiplier());
        binaryOperators.add(new NotEqual());
        binaryOperators.add(new RangeReference());
        binaryOperators.add(new Subtracter());
        binaryOperators.add(new Attribution());
    }

    @Override
    protected void initUnaryOperators() {
        // functions.add(new Average());
        unaryOperators.add(new Negator());
        unaryOperators.add(new Percent());
    }
    


    @Override
    protected void initNaryOperators(){
        naryOperators.add(new Block());
     
    }
    

    @Override
    public String toString() {
    	return "MacroExcel";
    }
}
