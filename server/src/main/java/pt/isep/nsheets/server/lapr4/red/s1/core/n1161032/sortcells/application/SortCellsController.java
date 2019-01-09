package pt.isep.nsheets.server.lapr4.red.s1.core.n1161032.sortcells.application;

import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.shared.core.Address;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.Value;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;

public class SortCellsController implements Controller {

    public boolean sortCompleto(Address ad1, Address ad2, Spreadsheet atual, String order) throws FormulaCompilationException {

        Cell[] coluna;
        for (int i = ad1.getColumn(); i <= ad2.getColumn(); i++) { //tratar das colunas individualmente dentro da área selecionada
            coluna = atual.getColumn(i);

            if (typeOfColumn(coluna) == 0 || typeOfColumn(coluna) == 1 || typeOfColumn(coluna) == 2) { //se todos os elementos da coluna forem do mesmo tipo
                if (order.compareTo("ascendingOrder") == 0) {
                    String[] colunaAscendente = ascendingSort(coluna, typeOfColumn(coluna)); //faz o sort ascedente
                    addSortedColumn(atual, i, colunaAscendente); //adiciona a coluna ordenada à spreadsheet atual
                }
                if (order.compareTo("descendingOrder") == 0) {
                    String[] colunaDescendente = descendingSort(coluna, typeOfColumn(coluna));//faz o sort descedente
                    addSortedColumn(atual, i, colunaDescendente); //adiciona a coluna ordenada à spreadsheet atual
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private void addSortedColumn(Spreadsheet atual, int column_index, String[] sortedColumn) throws FormulaCompilationException {
        for (int i = 0; i < sortedColumn.length; i++) {
            atual.getCell(column_index, i).setContent(""); //esvazia o conteudo da celula
            if (sortedColumn[i] != null && !sortedColumn[i].equals("")) { //se a coluna (sortedColumn) não tiver valores nulos nem vazios substitui os seus valores na spreadsheet
                atual.getCell(column_index, i).setContent(sortedColumn[i]);
            }
        }
    }

    private int typeOfColumn(Cell cellList[]) throws FormulaCompilationException { //retorna o tipo de células que a coluna contém, 0 para strings, 1 para numeros, 2 para datas e 3 para diferentes
        int countString = 0, countInt = 0, countDate = 0, i;
        String[] cellAux = new String[cellList.length];

        for (i = 0; i < cellList.length; i++) {
            cellAux[i] = cellList[i].getContent();
        }

        for (i = 0; i < cellList.length; i++) {
            if (!isNumber(cellAux[i]) && !isDate(cellAux[i])) {
                countString++;
            }
            if (isNumber(cellAux[i])) {
                countInt++;
            }
            if (isDate(cellAux[i])) {
                countDate++;
            }
        }

        if (countString == cellList.length) {
            return 0; //return 0 se a coluna só tiver texto
        } else if (countInt == cellList.length) {
            return 1; //return 1 se a coluna só tiver números
        } else if (countDate == cellList.length) {
            return 2; //return 2 se a coluna só tiver datas
        } else {
            return 3; //return 3 se houver diferentes tipos de dados na coluna
        }
    }

    private String[] ascendingSort(Cell cellList[], int columnType) throws FormulaCompilationException { //organiza uma coluna de forma ascendente
        int i, j;
        String[] cellAux = new String[cellList.length];
        String[] ret = new String[cellList.length];

        for (i = 0; i < cellList.length; i++) {
            cellAux[i] = cellList[i].getContent();
        }

        for (i = 0; i < cellAux.length; i++) {
            for (j = i + 1; j < cellAux.length; j++) {
                switch (columnType) {
                    case 0:
                        //se a coluna for de Strings
                        if (cellAux[i].compareTo(cellAux[j]) > 0) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;

                    case 1:
                        //se a coluna for de numeros
                        double n1 = Double.parseDouble(cellAux[i].replace(",", ".")); //passar as , para . para poder ser lido como numero no java
                        double n2 = Double.parseDouble(cellAux[j].replace(",", "."));
                        if (n1 > n2) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;

                    case 2:
                        //se a coluna for de Datas
                        String[] data1 = cellAux[i].split("-");
                        GregorianCalendar gc1 = new GregorianCalendar(Integer.parseInt(data1[2]), Integer.parseInt(data1[1]), Integer.parseInt(data1[0]));
                        String[] data2 = cellAux[i].split("-");
                        GregorianCalendar gc2 = new GregorianCalendar(Integer.parseInt(data2[2]), Integer.parseInt(data2[1]), Integer.parseInt(data2[0]));
                        if (gc1.compareTo(gc2) > 0) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        for (i = 0; i < cellAux.length; i++) {
            if (cellAux[i] != null) {
                ret[i] = cellAux[i];
            }
        }

        return ret;
    }

    private String[] descendingSort(Cell cellList[], int columnType) throws FormulaCompilationException { //organiza uma coluna de forma descendente
        int i, j;
        String[] cellAux = new String[cellList.length];
        String[] ret = new String[cellList.length];

        for (i = 0; i < cellList.length; i++) {
            cellAux[i] = cellList[i].getContent();
        }

        for (i = 0; i < cellAux.length; i++) {
            for (j = i + 1; j < cellAux.length; j++) {
                switch (columnType) {
                    case 0:
                        //se a coluna for de Strings
                        if (cellAux[i].compareTo(cellAux[j]) < 0) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;

                    case 1:
                        //se a coluna for de numeros
                        double n1 = Double.parseDouble(cellAux[i].replace(",", ".")); //passar as , para . para poder ser lido como numero no java
                        double n2 = Double.parseDouble(cellAux[j].replace(",", "."));
                        if (n1 < n2) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;

                    case 2:
                        //se a coluna for de Datas
                        String[] data1 = cellAux[i].split("-");
                        GregorianCalendar gc1 = new GregorianCalendar(Integer.parseInt(data1[2]), Integer.parseInt(data1[1]), Integer.parseInt(data1[0]));
                        String[] data2 = cellAux[i].split("-");
                        GregorianCalendar gc2 = new GregorianCalendar(Integer.parseInt(data2[2]), Integer.parseInt(data2[1]), Integer.parseInt(data2[0]));
                        if (gc1.compareTo(gc2) < 0) {
                            String aux = cellAux[i];
                            cellAux[i] = cellAux[j];
                            cellAux[j] = aux;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        for (i = 0; i < cellAux.length; i++) {
            if (cellAux[i] != null) {
                ret[i] = cellAux[i];
            }
        }

        return ret;
    }

    private boolean isNumber(String s) {  //para ver se uma coluna só tem numeros
        return s != null && (s.matches("[-+]?\\d*\\.?\\d+"));
    }

    private boolean isDate(String s) {  //para ver se uma coluna só tem datas
        return s != null && (s.matches("\\d{2}-\\d{2}-\\d{4}") || s.matches("\\d{4}-\\d{2}-\\d{2}") || s.matches("\\d{2}-\\d{2}-\\d{2}"));
    }
}
