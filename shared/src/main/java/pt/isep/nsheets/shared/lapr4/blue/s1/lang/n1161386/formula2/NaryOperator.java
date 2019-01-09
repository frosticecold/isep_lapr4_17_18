/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1161386.formula2;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.Expression;
import pt.isep.nsheets.shared.core.formula.Operator;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public interface NaryOperator extends Operator{
    
    public Value applyTo(Expression[] operands) throws IllegalValueTypeException;
}
