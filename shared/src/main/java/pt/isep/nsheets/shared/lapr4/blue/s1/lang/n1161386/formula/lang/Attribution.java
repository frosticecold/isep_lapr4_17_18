package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula.lang;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.Value.Type;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Reference;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;
import pt.isep.nsheets.shared.core.formula.lang.CellReference;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class Attribution implements BinaryOperator {

    /**
     * Creates a new attribution
     */
    public Attribution() {
    }

    @Override
    public Value applyTo(Expression leftOperand, Expression rightOperand) throws IllegalValueTypeException {
        Value value = rightOperand.evaluate();
        try {
            if (leftOperand instanceof Reference) {
                Reference cellRef = (Reference) leftOperand;
                String content = "";
                    switch (value.getType()) {
                        case NUMERIC:
                            content = value.toNumber().toString();
                            break;
                        case TEXT:
                            content = value.toText();
                            break;
                        case BOOLEAN:
                            content = value.toBoolean().toString();
                            break;
                        case DATE:
                            content = value.toDate().toString();
                            break;
                        case UNDEFINED:
                            break;
                        default:
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                for (Cell cell : cellRef.getCells()) {
                    cell.setContent(content);
                }
            }
        } catch (FormulaCompilationException ex) {
            Logger.getLogger(Attribution.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    @Override
    public String getIdentifier() {
        return ":=";
    }

    @Override
    public Type getOperandValueType() {
        return Value.Type.UNDEFINED;
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
