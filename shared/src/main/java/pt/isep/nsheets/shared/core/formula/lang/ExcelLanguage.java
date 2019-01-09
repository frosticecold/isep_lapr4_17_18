package pt.isep.nsheets.shared.core.formula.lang;

import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core.formula.lang.Form;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang.Attribution;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang.Block;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang.For;
import pt.isep.nsheets.shared.lapr4.red.s3.Function.n11161018.CellFunction.CellRange;

public class ExcelLanguage extends Language{
	
	@Override
	protected void initFunctions() {
		functions.add(new Average());
		functions.add(new And());
		functions.add(new Count());
		functions.add(new Do());
		functions.add(new For());
		functions.add(new Factorial());
		functions.add(new False());
		functions.add(new If());
		functions.add(new Not());
		//functions.add(new NumericFunction());
		functions.add(new Or());
		functions.add(new Sum());
		functions.add(new True());
		functions.add(new Form());
		functions.add(new CellRange());
                functions.add(new Eval());
                functions.add(new WhileDo());
                functions.add(new DoWhile());
	}

	@Override
	protected void initBinaryOperators() {
		binaryOperators.add(new Attribution());
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
        
	public ExcelLanguage(String name,String starter) {
		super(name,starter);
	}
}
