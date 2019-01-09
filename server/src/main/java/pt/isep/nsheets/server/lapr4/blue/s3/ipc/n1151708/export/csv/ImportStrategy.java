/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv;

/**
 *
 * @author MarioDias
 */
public interface ImportStrategy {
    
    String[][] importCSV(String fileName);
    
}
