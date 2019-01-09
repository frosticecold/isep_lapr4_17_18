/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.colorsExtensions;

import java.util.EventListener;
import pt.isep.nsheets.shared.lapr4.blue.s1.n1160701.conditionalExtensions.ConditionalCell;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public interface ConditionalFormattingCellListener extends EventListener {

    public void condicionalFormattingChanged(ConditionalCell cell);

}
