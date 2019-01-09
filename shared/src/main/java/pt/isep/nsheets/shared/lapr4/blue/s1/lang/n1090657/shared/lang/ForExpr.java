package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;

public class ForExpr implements Expression{

	Expression start;
	Expression end;
	Expression step;
	Expression arguments;
	
	public ForExpr(Expression start, Expression end, Expression step,Expression arguments) {
		this.start=start;
		this.end=end;
		this.step=step;
		this.arguments=arguments;
	}
	@Override
	public Value evaluate() throws IllegalValueTypeException {
		int sIndx = (int)start.evaluate().toDouble();
		int eIndx = (int)start.evaluate().toDouble();
		int forStep = 1;
		if(step != null) {
			forStep =(int)step.evaluate().toDouble();
		}
		Value val = null;
		for(int i = sIndx;i<eIndx;i+=forStep) {
			val=arguments.evaluate();
		}
		return val;
	}

	@Override
	public Object accept(ExpressionVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
