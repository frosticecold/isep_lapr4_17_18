/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2.NaryOperator;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class Block implements NaryOperator {

    @Override
    public Value applyTo(Expression[] operands) throws IllegalValueTypeException {
        Value value = null;
        
        for (Expression expr : operands) {
            value = expr.evaluate();
        }

        return value;
    }

    @Override
    public String getIdentifier() {
        return "{";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.NUMERIC;
    }

}
