package pt.isep.nsheets.shared.lapr4.red.s3.lang.n1161025.temporaryVariables;

import java.util.ArrayList;
import java.util.SortedSet;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;

/**
 *
 * @author MFerreira
 */
public interface TemporaryVariable extends Expression, Comparable<TemporaryVariable> {

    @Override
    public Value evaluate();
    
    public String variableName();
    
    public void changeValue(Value value);
    
    public void addVariable(String name);
    
    public ArrayList<TemporaryVariable> getTemporaryVariables(); 

    @Override
    public int compareTo(TemporaryVariable temporaryVariable);

}

