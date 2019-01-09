package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Money;

public class MultiplierMonetary implements BinaryOperator {

    /**
     * Divider, one of the expressions needs to have a value of the type money and the other of the type
     * double, if not so return value will be null
     *
     * @param leftOperand left operand expression
     * @param rightOperand right operand expression
     *
     * @return Value of type money with the multiplication of the two expressions
     *
     * @throws IllegalValueTypeException
     */
    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
        if(leftOperand.evaluate().isOfType(Value.Type.MONETARY) && rightOperand.evaluate().isOfType(Value.Type.NUMERIC)){
            Money leftOp = leftOperand.evaluate().toMoney();
            double val = rightOperand.evaluate().toDouble();

            return new Value(leftOp.multiplyMoney(val));
        }else if(leftOperand.evaluate().isOfType(Value.Type.NUMERIC) && rightOperand.evaluate().isOfType(Value.Type.MONETARY)){
            Money rightOp = rightOperand.evaluate().toMoney();
            double val = leftOperand.evaluate().toDouble();

            return new Value(rightOp.multiplyMoney(val));
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
        return "*";
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
     * Devolves the information of multiplier
     *
     * @return identifier
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
