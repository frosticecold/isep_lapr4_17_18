package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Expression;

import org.apache.tapestry.components.Conditional;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.formula.BinaryOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * RangeOptionManager.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 13/06/2018
 */
public class ConditionalManager {

    private static ConditionalManager instace;

    private List<Cell> changedCells;
    private List<ConditionalInfo> infos;
    private List<Cell> cellsInRage;


    public ConditionalManager() {

        changedCells = new ArrayList<>();
        infos = new ArrayList<>();
        cellsInRage = new ArrayList<>();
    }

    public static ConditionalManager getInstace() {

        if ( instace == null){
            instace = new ConditionalManager();
        }

        return instace;
    }

    public boolean hasCell(Cell cell){

        if(changedCells.contains(cell)){
            return true;
        }

        for(Cell c : changedCells ){
            if( c.getAddress().equals(cell.getAddress())){
                return  true;
            }
        }


        return false;
    }

    public List<Cell> getChangedCells() {
        return changedCells;
    }

    public void setChangedCells(List<Cell> changedCells) {
        this.changedCells = changedCells;
    }

    public void addChangedCells(Cell newCell, ConditionalInfo info){
        changedCells.add(newCell);
        infos.add(info);
    }

    public List<ConditionalInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ConditionalInfo> infos) {
        this.infos = infos;
    }

    public void setRangedCells(List<Cell> range) {

        this.cellsInRage.addAll(range);
    }

    public List<Cell> getRangedCells(){

       // cleanRange();


        return cellsInRage;
    }

    public void cleanRange(){


        cellsInRage = new ArrayList<>();

    }


    public void newInstane() {

        instace = new ConditionalManager();
    }
}
