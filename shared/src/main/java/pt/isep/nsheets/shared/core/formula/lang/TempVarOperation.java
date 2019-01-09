package pt.isep.nsheets.shared.core.formula.lang;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.UnaryOperation;
import pt.isep.nsheets.shared.core.formula.UnaryOperator;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitorException;
import pt.isep.nsheets.shared.lapr4.red.s3.lang.n1161025.temporaryVariables.TemporaryVariable;

/**
 *
 * @author MFerreira
 */
public class TempVarOperation extends UnaryOperation implements TemporaryVariable {

    private ArrayList<TemporaryVariable> tp;

    public TempVarOperation(UnaryOperator operator, TemporaryVariable operand) {
        super(operator, operand);
    }

    @Override
    public TemporaryVariable getOperand() {
        return (TemporaryVariable) super.getOperand();
    }

    @Override
    public Value evaluate() {
        try {
            return getOperator().applyTo(getOperand());
        } catch (IllegalValueTypeException ex) {
            Logger.getLogger(TempVarOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) throws ExpressionVisitorException {
        return visitor.visitTemporaryVariable(this);
    }
    
    @Override
    public void addVariable(String name) {
        getOperand().addVariable(name);
    }

    @Override
    public String variableName() {
        return getOperand().variableName();
    }
    
    @Override
    public void changeValue(Value value) {
        getOperand().changeValue(value);
    }

    @Override
    public int compareTo(TemporaryVariable temporaryVariable) {
        TemporaryVariable other = temporaryVariable;
        int firstDiff = getOperand().variableName().compareTo(other.variableName());
        if (firstDiff != 0) {
            return 1;
        } else {
            int secondDiff = getOperand().evaluate().compareTo(other.evaluate());
            if (secondDiff != 0) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public ArrayList<TemporaryVariable> getTemporaryVariables() {
        if (tp == null) {
            tp = new ArrayList<>();
        }
        tp.add(this.getOperand());
        return tp;
    }
    
    

}

