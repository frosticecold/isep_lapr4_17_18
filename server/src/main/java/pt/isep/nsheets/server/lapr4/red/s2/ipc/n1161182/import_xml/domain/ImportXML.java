package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161182.import_xml.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pt.isep.nsheets.shared.core.Address;

public class ImportXML implements ImportStrategy {

    @Override
    public String[][][] importWorkbook(String filename) {
        if (filename == null || filename.length() == 0) {
            throw new IllegalArgumentException("File name doesn't exist!");
        }

        String[][][] contents = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        NodeList nl;

        try {
            db = factory.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            Element element = doc.getDocumentElement();

            if (element.getNodeName().equalsIgnoreCase("workbook")) {
                nl = doc.getDocumentElement().getChildNodes();
            } else {
                throw new IllegalArgumentException("File doesn't contain workbook information");
            }

            if (nl != null) {
                int length = nl.getLength();
                contents = new String[length][][];
                for (int i = 0; i < length; i++) {//For each node
                    if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) nl.item(i);
                        if (el.getNodeName().contains("spreadsheet")) { //If it is a spreadsheet element
                            contents[i] = processSpreadsheetNode(el);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    @Override
    public String[][] importSpreadSheet(String filename) {
        if (filename == null || filename.length() == 0) {
            throw new IllegalArgumentException("File name doesn't exist!");
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        NodeList nl;
        try {
            db = factory.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            Element element = doc.getDocumentElement();

            if (element.getNodeName().equalsIgnoreCase("spreadsheet")) {
                nl = doc.getDocumentElement().getChildNodes();
            } else {
                throw new IllegalArgumentException("File doesn't contain spreadsheet information");
            }

            if (nl != null) {
                return (processSpreadsheetNode(element));
            } else {
                throw new Exception("The is no cell information in this file!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[][] importPartOfSpreadSheet(String fileName) {
        return importSpreadSheet(fileName);
    }

    private String[][] processSpreadsheetNode(Element e) {
        String[][] contents;
        Map<Address, String> map = new HashMap<>();
        int auxCol = 0;
        int auxRow = 0;

        NodeList cells = e.getChildNodes();
        if (cells != null) {
            for (int i = 0; i < cells.getLength(); i++) {
                if (cells.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element cell = (Element) cells.item(i);
                    if (cell.getNodeName().equalsIgnoreCase("cells")) {

                        String row = cell.getElementsByTagName("row").item(0).getTextContent();
                        String column = cell.getElementsByTagName("column").item(0).getTextContent();
                        String content = cell.getElementsByTagName("content").item(0).getTextContent();

                        int columnI = Integer.parseInt(column);
                        int rowI = Integer.parseInt(row);

                        if (columnI > auxCol) auxCol = columnI;
                        if (rowI > auxRow) auxRow = rowI;

                        Address add = new Address(columnI, rowI);
                        map.put(add, content);
                    }
                }
            }
        }

        contents = new String[auxRow][auxCol];
        for (Entry<Address, String> entry : map.entrySet()) {
            int row = entry.getKey().getRow();
            int col = entry.getKey().getColumn();
            contents[row][col] = entry.getValue();
        }

        return contents;
    }
}
