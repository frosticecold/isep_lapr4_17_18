/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarioDias
 */
public class ImportFromCSV implements ImportStrategy {

    @Override
    public String[][] importCSV(String fileName) {
        
        ArrayList<String[]> list = new ArrayList<>();
        Path baseFolder = Paths.get(System.getProperty("user.dir"));
        Path fullPath = Paths.get(baseFolder + "/server/export/");
        String line ="";
        
        BufferedReader br = null;
        int lineCounter = 0;
        try {
        	br = new BufferedReader(new FileReader(fullPath.toUri().getPath()+fileName));
        	
            while ((line = br.readLine()) != null) {
            	list.add(line.split(";"));
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String [][] sheet = new String [list.size()][list.get(0).length];
        for(int i =0; i<list.size() ; i++) {
        	sheet[i] = list.get(i);
        }
        return sheet;
    }

}
