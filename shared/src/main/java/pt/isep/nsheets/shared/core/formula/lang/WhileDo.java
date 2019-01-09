/*
 * Copyright (c) 2005 Einar Pehrson <einar@pehrson.nu>.
 *
 * This file is part of
 * CleanSheets - a spreadsheet application for the Java platform.
 *
 * CleanSheets is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * CleanSheets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CleanSheets; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package pt.isep.nsheets.shared.core.formula.lang;

import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.core.formula.FunctionParameter;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaEvalVisitor;

/**
 *
 * @author João Magalhães
 */
public class WhileDo implements Function {
    
    /** The function's parameters */
    public static final FunctionParameter[] parameters = new FunctionParameter[] {
            new FunctionParameter(Value.Type.TEXT, "Condition", false,
                    "An condition to be analised"),
        new FunctionParameter(Value.Type.NUMERIC,"CellPosition", false,
                    "Cell to be incremented"),
    };

    /**
     * Creates a new instance of the FACT function.
     */
    public WhileDo() {}

    public String getIdentifier() {
            return "WHILEDO";
    }

    public Value applyTo(Expression[] arguments) throws IllegalValueTypeException {

        String str = arguments[0].evaluate().toString();
        String number = extractNumber(str);
        double num = Double.parseDouble(number);
        double cell = arguments[1].evaluate().toDouble();

        if (str.contains("<")) {
            if (cell>=num) {
                cell = num-1;
            }
        } else if (str.contains(">")) {
            if (cell<=num) {
                cell = num+1;
            }
        } else if (str.contains("=")||str.contains("<=")||str.contains(">=")) {
            if (cell<num||cell>num) {
                cell = num;
            }
        }

        return new Value(str);
    }

    public FunctionParameter[] getParameters() {
            return parameters;
    }

    public boolean isVarArg() {
            return true;
    }
    
    public static String extractNumber(final String str) {                
        
        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        int count = 0;
        for(char c : str.toCharArray()){
            
            if(Character.isDigit(c)){
                if (count!=0) {
                    sb.append(c);
                    found = true;
                }
                count++;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;                
            }
        }

        return sb.toString();
    }
    
}