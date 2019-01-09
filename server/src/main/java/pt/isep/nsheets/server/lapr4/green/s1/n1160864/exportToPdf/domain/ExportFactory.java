/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.green.s1.n1160864.exportToPdf.domain;

import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetPartStrategy;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetPartToXML;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetStrategy;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportSpreadSheetToXML;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv.ExportToCSV;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151708.export.csv.NewExportStrategy;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1151186.export.xml.ExportToXML;

/**
 *
 * @author Telmo
 */
public class ExportFactory {

    private static ExportFactory factory = null;

    private ExportFactory() {

    }

    public static ExportFactory instance() {
        if (factory == null) {
            factory = new ExportFactory();
        }

        return factory;
    }

    public ExportStrategy exportStrategy(ExportFormats format) {
        if (format.equals(ExportFormats.PDF)) {
            return new ExportToPDF();
        } else if (format.equals(ExportFormats.CSV)){
            return null;//new ExportToCSV();
        } 
        //return  new ExportToXML();
        return null;
    }
    public NewExportStrategy exportNewStrategy(ExportFormats format) {
        if (format.equals(ExportFormats.CSV)){
            return new ExportToCSV();
        }
        else  if (format.equals(ExportFormats.XML)){
            return new ExportToXML();
        }
        
        return  null;

    }
    
    
    public ExportSpreadSheetStrategy exportSpreadSheetStrategy(ExportFormats format) {
        if (format.equals(ExportFormats.XML)){
            return new ExportSpreadSheetToXML();
        }
        
        
        return  null;

    }
    public ExportSpreadSheetPartStrategy exportSpreadSheetPartStrategy(ExportFormats format) {
        if (format.equals(ExportFormats.XML)){
            return new ExportSpreadSheetPartToXML();
        }
        
        
        return  null;

    }
    
    
    
}
