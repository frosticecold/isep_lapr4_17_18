package pt.isep.nsheets.server.lapr4.green.s3.core.n1161838.Notes.application;

import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.SpreadsheetDTO;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.services.NoteDTO;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Joao Rocha <161838>
 */
public class ExportNoteController implements Controller {

    private NoteRepository noteRepository;

    public ExportNoteController(){
        noteRepository = PersistenceContext.repositories().notes();
    }

    /**
     * Method that exports the notes to a specific spreadsheet
     * It receives a range and adds the not to the cells if there is enough space on the spreadsheet
     * @param spreadsheet
     * @param noteList
     * @param startRow
     * @param startCol
     * @return
     */
    public WorkbookDTO exportNotesToSpreadsheet(WorkbookDTO workbook, SpreadsheetDTO spreadsheet, List<NoteDTO> noteList, int startRow, int startCol){

        int row = startRow;
        int col = startCol;

        int size = noteList.size();
        int idx = 0;

        SpreadsheetDTO spread = new SpreadsheetDTO();
        workbook.getSpreadsheets().add(spread);

        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; i < size; j++) {
                    spread.content[i][j] = noteList.get(idx).getDescription();
                    Logger logger = Logger.getLogger("logger");
                    logger.log(Level.SEVERE, spreadsheet.content[i][j]);
                    idx++;
                }
            }

        }catch(ArrayIndexOutOfBoundsException e){
            Logger logger = Logger.getLogger("logger");
            logger.log(Level.SEVERE, "error exporting notes to a spread");
        }

        return workbook;
    }

}
