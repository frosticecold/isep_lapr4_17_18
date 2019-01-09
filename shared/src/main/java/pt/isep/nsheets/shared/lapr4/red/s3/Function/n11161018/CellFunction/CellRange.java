package pt.isep.nsheets.shared.lapr4.red.s3.Function.n11161018.CellFunction;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.*;
import pt.isep.nsheets.shared.core.formula.lang.*;

/**
 * CellRange.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 13/06/2018
 */
public class CellRange implements Function {


    @Override
    public String getIdentifier() {
        return "CellRange";
    }

    @Override
    public Value applyTo(Expression[] args) throws IllegalValueTypeException {
        Expression comparator = args[1];
        Expression value = args[2];

        BinaryOperator op = toOperator(comparator.toString());

        return op.applyTo(args[0], value);
    }


    @Override
    public FunctionParameter[] getParameters() {
        return parameters;
    }

    private BinaryOperator toOperator(String cell) {



        if( cell.contains("<")){

            if( cell.contains("=")){

                return new LessThanOrEqual();

            }

            return new LessThan();

        }else if( cell.contains(">")){

            if( cell.contains("=")){

                return new GreaterThanOrEqual();

            }

            return new GreaterThan();

        }else if( cell.contains("=")){

            return new Equal();

        }


        return null;
    }

    /** The only (but repeatable) parameter: a boolean expression */
    public static final FunctionParameter[] parameters = new FunctionParameter[] {
            new FunctionParameter(Value.Type.BOOLEAN, "Boolean expression", false,
                    "A boolean expression to include")
    };

    @Override
    public boolean isVarArg() {
        return true;
    }
}
