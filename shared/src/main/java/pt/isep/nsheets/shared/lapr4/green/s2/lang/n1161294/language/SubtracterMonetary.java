package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Money;

public class SubtracterMonetary implements BinaryOperator {

    /**
     * Subtracter
     *
     * @param leftOperand left operand expression type monetary
     * @param rightOperand right operand expression type monetary
     *
     * @return Value of type money with the subtraction of the two expressions
     *
     * @throws IllegalValueTypeException
     */
    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
        Money leftOp = leftOperand.evaluate().toMoney();
        Money rightOp = rightOperand.evaluate().toMoney();

        if(leftOp.sameCurrency(rightOp))
            return new Value(leftOp.subtractMoney(rightOp));
        else
            return null;
    }

    @Override
    public String getIdentifier() {
        return "-";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.MONETARY;
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
