package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression;

import gwt.material.design.client.constants.Color;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;

import java.util.Objects;

/**
 * ConditionalInfo.java
 *
 * basically a data sack.
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 13/06/2018
 */
public class ConditionalInfo {

    private Color trueColor;
    private Color falseColor;
    private Cell conditionalCell;
    private BinaryOperator operator;
    private String value;

    public ConditionalInfo(Color trueColor, Color falseColor, Cell conditionalCell, BinaryOperator operator, String value) {
        this.trueColor = trueColor;
        this.falseColor = falseColor;
        this.conditionalCell = conditionalCell;
        this.operator = operator;
        this.value = value;
    }

    public Color getTrueColor() {
        return trueColor;
    }

    public void setTrueColor(Color trueColor) {
        this.trueColor = trueColor;
    }

    public Color getFalseColor() {
        return falseColor;
    }

    public void setFalseColor(Color falseColor) {
        this.falseColor = falseColor;
    }

    public Cell getConditionalCell() {
        return conditionalCell;
    }

    public void setConditionalCell(Cell conditionalCell) {
        this.conditionalCell = conditionalCell;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConditionalInfo)) return false;
        ConditionalInfo that = (ConditionalInfo) o;
        return Objects.equals(getConditionalCell(), that.getConditionalCell());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getConditionalCell());
    }

    public boolean whatsTheConditionalInfo(Cell cell){

        return cell.getAddress().equals(this.conditionalCell.getAddress());
    }

    @Override
    public String toString() {
        return "ConditionalInfo{" +
                "trueColor=" + trueColor +
                ", falseColor=" + falseColor +
                ", conditionalCell=" + conditionalCell +
                ", operator=" + operator +
                ", value=" + value +
                '}';
    }
}
