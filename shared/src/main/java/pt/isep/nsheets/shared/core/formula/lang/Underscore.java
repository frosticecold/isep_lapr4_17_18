package pt.isep.nsheets.shared.core.formula.lang;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.UnaryOperator;
import pt.isep.nsheets.shared.lapr4.red.s3.lang.n1161025.temporaryVariables.TemporaryVariable;

/**
 *
 * @author MFerreira
 */
public class Underscore implements UnaryOperator {

    public Underscore() {
    }

    @Override
    public Value applyTo(Expression operand) throws IllegalValueTypeException {

        if (operand instanceof TemporaryVariable) {
            TemporaryVariable tp = (TemporaryVariable) operand;
            for (TemporaryVariable t : tp.getTemporaryVariables()) {
                if (tp.variableName().equals(t.variableName())) {
                    return tp.evaluate();
                }
            }
        }
        return null;
    }

    @Override
    public String getIdentifier() {
        return "_";
    }

    @Override
    public Value.Type getOperandValueType() {
        return Value.Type.TEXT;
    }

    @Override
    public boolean isPrefix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
