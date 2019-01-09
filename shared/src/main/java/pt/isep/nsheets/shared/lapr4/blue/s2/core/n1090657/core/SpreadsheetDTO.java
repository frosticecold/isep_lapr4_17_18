/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class SpreadsheetDTO implements Serializable, IsSerializable {

    public String[][] content;

    public String title;

    private int columns;

    private int rows;

    public SpreadsheetDTO() {
        title = "";
        columns = 20;
        rows = 20;
        this.content = new String[20][20];
        for(int i = 0; i < rows; i++){
        for(int j=0; j < columns; j++){
            content[i][j] = " ";
        }
        }
    }

    public SpreadsheetDTO(String title, int columns, int rows, String[][] content) {
        this.title = title;
        this.columns = columns;
        this.rows = rows;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] list) {
        this.content = list;
    }


}
