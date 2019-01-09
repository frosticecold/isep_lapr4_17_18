package pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161182.import_xml.domain;

public interface ImportStrategy {
    /**
     * Imports one workbook
     *
     * @param fileName File Name To Input
     * @return If successful return true
     */
    String[][][] importWorkbook(String fileName);

    /**
     * Imports one spreadsheet
     *
     * @param fileName File Name To Input
     * @return If successful return true
     */
    String[][] importSpreadSheet(String fileName);

    /**
     * Imports a part of a spreadsheet
     *
     * @param fileName File Name To Input
     * @return If successful return true
     */
    String[][] importPartOfSpreadSheet(String fileName);
}
