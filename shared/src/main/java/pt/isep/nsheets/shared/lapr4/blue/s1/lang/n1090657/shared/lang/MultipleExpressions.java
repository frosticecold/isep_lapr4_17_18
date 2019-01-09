package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang;

import java.util.List;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;

public class MultipleExpressions implements Expression {

	private Expression[] args;

	public MultipleExpressions(List<Expression> args) {
		this.args = new Expression[args.size()];
		for(int i = 0; i < args.size();i++) {
			this.args[i]=args.get(i);
		}
	}

	@Override
	public Value evaluate() throws IllegalValueTypeException {
		
		Expression expr = null;
		Value val= null;
		if(args.length==1) {
			return args[0].evaluate();
		}
		for(int i =0; i < args.length;i++) {
			expr=args[i];
			val=expr.evaluate();
		}
		return val;
	}

	@Override
	public Object accept(ExpressionVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
