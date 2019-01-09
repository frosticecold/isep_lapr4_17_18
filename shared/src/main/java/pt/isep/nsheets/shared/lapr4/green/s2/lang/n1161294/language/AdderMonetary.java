package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money.Money;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdderMonetary implements BinaryOperator {

    /**
     * Adder
     *
     * @param leftOperand left operand expression type monetary
     * @param rightOperand right operand expression type monetary
     *
     * @return Value of type money with the sum of the two expressions
     *
     * @throws IllegalValueTypeException
     */
    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
        Money leftOp = leftOperand.evaluate().toMoney();
        Money rightOp = rightOperand.evaluate().toMoney();
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE,"Amount1: " + leftOp.toString());
        logger.log(Level.SEVERE,"Amount2: " + rightOp.toString());
        if(leftOp.sameCurrency(rightOp))
            return new Value(leftOp.addMoney(rightOp));
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
        return "+";
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
     * Devolves the information of adder
     *
     * @return identifier
     */
    @Override
    public String toString() {
        return getIdentifier();
    }
}
