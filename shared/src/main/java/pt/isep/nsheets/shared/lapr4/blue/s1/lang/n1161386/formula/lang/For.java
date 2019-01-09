/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang;

import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.FunctionParameter;
import pt.isep.nsheets.shared.core.formula.Reference;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class For implements Function {

    public static final FunctionParameter[] parameters = new FunctionParameter[]{
        new FunctionParameter(Value.Type.NUMERIC, "Terms", false,
        "Cycle with a specified number of iterations that"
        + "executes block(s) of instructions")
    };

    @Override
    public Value applyTo(Expression[] args) throws IllegalValueTypeException {
        args[0].evaluate();
        Value value = null;

        do {
            for (int i = 2; i < args.length - 1; i++) {
                value = args[i].evaluate();
            }
        }while(args[1].evaluate().toBoolean());
        return value;
    }

    @Override
    public String getIdentifier() {
        return "FOR";
    }

    @Override
    public FunctionParameter[] getParameters() {
        return parameters;
    }

    @Override
    public boolean isVarArg() {
        return true;
    }

}
