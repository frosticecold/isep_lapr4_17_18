package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;

public class IfExpr implements Expression{

	Expression toEvaluate;
	Expression ifTrue;
	Expression ifFalse;
	
	public IfExpr(Expression toEvaluate, Expression ifTrue, Expression ifFalse) {
		this.toEvaluate=toEvaluate;
		this.ifTrue=ifTrue;
		this.ifFalse=ifFalse;
	}
	@Override
	public Value evaluate() throws IllegalValueTypeException {
		if(toEvaluate.evaluate().toBoolean()) {
			return ifTrue.evaluate();
		}else {
			if(ifFalse!= null)
				return ifFalse.evaluate();
			}
		
		return new Value();
	}

	@Override
	public Object accept(ExpressionVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
