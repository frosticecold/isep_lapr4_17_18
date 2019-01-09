package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Money;

public class DividerMonetary implements BinaryOperator {

    /**
     * Divider
     *
     * @param leftOperand left operand expression type Monetary
     * @param rightOperand right operand expression type double
     *
     * @return Value of type money with the division of the two expressions
     *
     * @throws IllegalValueTypeException
     */
    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {

        if(leftOperand.evaluate().isOfType(Value.Type.MONETARY) && rightOperand.evaluate().isOfType(Value.Type.NUMERIC)){
            Money leftOp = leftOperand.evaluate().toMoney();
            double val = rightOperand.evaluate().toDouble();

            return new Value(leftOp.divideMoney(val));
        }
        else
            return null;
    }

    /**
     * Devolves the identifier
     *
     * @return id
     */
    @Override
    public String getIdentifier() {
        return "/";
    }

    /**
     * Type of operand value
     *
     * @return monetary
     */
    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.MONETARY;
    }

    /**
     * Devolves the information of divider
     *
     * @return identifier
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
