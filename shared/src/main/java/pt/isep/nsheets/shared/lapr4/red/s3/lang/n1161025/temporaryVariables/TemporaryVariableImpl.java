package pt.isep.nsheets.shared.lapr4.red.s3.lang.n1161025.temporaryVariables;

import com.google.gwt.regexp.shared.RegExp;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.text.ParseException;
import com.google.gwt.regexp.shared.MatchResult;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.util.ExpressionVisitor;
import java.util.logging.Level;
import java.util.ArrayList;


/**
 *
 * @author MFerreira
 */
public class TemporaryVariableImpl implements TemporaryVariable {

    private final String name;

    private Value value;
    
    private List<TemporaryVariable> tp;

    private static final RegExp PATTERN = RegExp.compile("'_'[a-zA-Z0-9]+");

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TemporaryVariableImpl other = (TemporaryVariableImpl) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }

    public TemporaryVariableImpl(String name) throws ParseException {
        
        MatchResult matcher = PATTERN.exec(name);
        
        if (matcher != null) {
            this.name = name;
        } else {
            throw new ParseException(name, 0);
        }
    }

    public TemporaryVariableImpl(String name, Value value) throws ParseException {
        MatchResult matcher = PATTERN.exec(name);
        if (matcher != null) {
            this.name = name;
            this.value = value;
        } else {
            throw new ParseException(name, 0);
        }
    }

    @Override
    public Value evaluate() {
        return this.value;
    }

    @Override
    public String variableName() {
        return this.name;
    }

    @Override
    public void changeValue(Value value) {
        this.value = value;
    }

    @Override
    public int compareTo(TemporaryVariable temporaryVariable) {
        TemporaryVariable other = temporaryVariable;
        int firstDiff = name.compareTo(other.variableName());
        if (firstDiff != 0) {
            return 1;
        } else {
            int secondDiff = value.compareTo(other.evaluate());
            if (secondDiff != 0) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public Object accept(ExpressionVisitor visitor) {
        return visitor.visitTemporaryVariable(this);
    }

    @Override
    public ArrayList<TemporaryVariable> getTemporaryVariables() {
        ArrayList<TemporaryVariable> temporaryVariables = new ArrayList<>();
        for (int i = 0; i < tp.size(); i++) {
            temporaryVariables.add(tp.get(i));
        }
        return temporaryVariables;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void addVariable(String name) {
        if(tp == null){
            tp = new ArrayList<>();
        }
        try {
            tp.add(new TemporaryVariableImpl(name));
        } catch (ParseException ex) {
            Logger.getLogger(TemporaryVariableImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

