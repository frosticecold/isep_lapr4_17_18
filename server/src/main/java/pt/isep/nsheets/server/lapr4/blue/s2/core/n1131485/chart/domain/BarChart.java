package pt.isep.nsheets.server.lapr4.blue.s2.core.n1131485.chart.domain;

import java.util.ArrayList;
import java.util.SortedSet;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.SpreadsheetImpl;
import pt.isep.nsheets.shared.services.ChartDTO;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class BarChart implements Chart {

    //CONSTANT
    private static final String DEFAULT_NAME = "BARCHART001";

    private boolean isRowLabel;
    private String name;
    private String labelName;
    private Address a1 = null;
    private Address a2 = null;
    private ChartType type;
    private String[][] content;

    public BarChart(String name, boolean isFirstRowOrColumnLabel) {
        this.name = name;
        this.isRowLabel = isFirstRowOrColumnLabel;
        this.type = ChartType.BARCHART;
    }

    public BarChart(String name,String labelName, boolean label, String [][] content) {
        this.name = name;
        this.content = content;
        this.isRowLabel = label;
        this.labelName = labelName;
    }

    @Override
    public String[][] makeChart(Address a1, Address a2, SpreadsheetImpl spreadsheet) {
        int RowEnd = a2.getRow();//LAST ROW of RANGE
        int ColumnEnd = a2.getColumn();//LAST COLUMN OF RANGE
        
        this.a1 = a1;
        this.a2 = a2;

        String[][] matrix;
        int rowM = a2.getRow() - a1.getRow() + 1;
        int colM = a2.getColumn() - a1.getColumn() + 1;
        matrix = new String[RowEnd][ColumnEnd];
        SortedSet<Cell> list;
        list = spreadsheet.getCells(a1, a2); //ALL THE CELLS OF THE SPREADSHEET CHOSEN WITHIN THE RANGE

        ArrayList<Cell> arrayCell = new ArrayList<>();

        Cell[] array = list.toArray(new Cell[list.size()]);//SORTEDSET TO ARRAY

        int c = 0;
        while (c < array.length) {
            arrayCell.add((Cell) array[c]);//ADD THE CELL TO THE LIST TO FACILITATE THE FOLLOWING STEPS
            c++;
        }

        if (isRowLabel) { //true
            labelName = dataInRows(ColumnEnd, arrayCell);//DEFINES THE LABEL IF ROW WAS CHOSEN

        } else { //false
            labelName = dataInColumns(RowEnd, arrayCell);//DEFINES THE LABEL IF COLUMN WAS CHOSEN

        }

        int currentPos = 0;

        for (int r = 0; r < rowM; r++) {
            for (int col = 0; col < colM; col++) {
                currentPos = r + col;
                matrix[r][col] = arrayCell.get(currentPos).getContent();//SAVES THE CONTENT OF THE CELLS IN THE MATRIX
            }
        }
        content = matrix;
        return matrix;
    }

    private String dataInRows(int ColumnEnd, ArrayList<Cell> array) {
        String info = "";

        info = array.get(ColumnEnd - 1).getContent();

        return info;
    }

    private String dataInColumns(int RowEnd, ArrayList<Cell> array) {
        String info = "";

        info = array.get(RowEnd - 1).getContent();

        return info;
    }

    public String getLabelName() {
        return labelName;
    }

    //public BarChart(String name,String labelName, boolean label, String [][] content) {
    public BarChart fromDTO(ChartDTO dto) {
        return new BarChart(dto.name(),dto.labelName(), dto.label(), dto.content() );
    }
   
    public String name() {

        return this.name;
    }

    @Override
    public ChartType type() {
        return this.type;
    }

    @Override
    public ChartDTO toDTO(Chart c) {
        return new ChartDTO(this.a1, this.a2, this.name, this.type.toString(), this.content, this.isRowLabel, this.labelName);
    }
}
