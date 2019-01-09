/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Operation;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class NaryOperation extends Operation<NaryOperator> {
    
    /** operands */
    private Expression[] operands;
    
    //private NaryOperator operator;
        
    public NaryOperation(NaryOperator operator, Expression[] operands) {
        super(operator);      
        this.operands=operands;
    }

    @Override
    public Value evaluate() throws IllegalValueTypeException {
        return operator.applyTo(operands);
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitNaryOperation(this);
    }
 
    public Expression[] getOperands() {
        return this.operands;
    }
}
