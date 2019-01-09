/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1040862.GlobalSearch.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161027.ipc06.application.ipc06Controller;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Spreadsheet;
import pt.isep.nsheets.shared.core.formula.compiler.FormulaCompilationException;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class GlobalSearchAndReplaceController implements Controller {

    public List<String> searchGlobal(String username, String regularExpression, String replace, String workbookExpression) {

        ipc06Controller controller = new ipc06Controller();
        List<WorkbookDescription> wbList = controller.getWorkbooksFromCurrentUser(username);
        List<WorkbookDescription> finaList = new ArrayList<>();

        List<String> output = new ArrayList<>();

        boolean canReplace = false;
        if (replace != null && !replace.isEmpty()) {
            canReplace = true;
        }

        if (workbookExpression != null && !workbookExpression.isEmpty()) {
            for (WorkbookDescription workbookDescription : wbList) {
                if (Pattern.matches(workbookExpression, workbookDescription.getName())) {
                    finaList.add(workbookDescription);
                }
            }
            wbList = finaList;
        }

        for (WorkbookDescription workbookdesc : wbList) {
            List<Spreadsheet> spreadList = new ArrayList<>();
            spreadList = workbookdesc.getWorkbook().getSpreadSheets();

            for (Spreadsheet spreadsheet : spreadList) {
                int rows = spreadsheet.getRowCount(), cols = spreadsheet.getRowCount();
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        Cell cellToWhat = spreadsheet.getCell(col, row);
                        if (Pattern.matches(regularExpression, cellToWhat.getContent())) {
                            boolean replaced = false;
                            String strout = "workbook: " + workbookdesc.getName() + " spreadsheet: " + spreadsheet.getTitle() + " cell: " + cellToWhat.toString() + " Replaced: %flag";
                            if (canReplace) {
                                try {
                                    cellToWhat.setContent(replace);
                                    replaced = true;
                                } catch (FormulaCompilationException ex) {
                                    Logger.getLogger(GlobalSearchAndReplaceController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            strout = strout.replace("%flag", String.valueOf(replaced));
                            output.add("Match found!");
                            output.add(strout);
                        }
                    }
                }
            }
        }

        if (canReplace) {
            for (WorkbookDescription workbookDescription : wbList) {
                try {
                    WorkbookDescriptionRepository wbRepo = PersistenceContext.repositories().workbookDescriptions();
                    wbRepo.save(workbookDescription);
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(GlobalSearchAndReplaceController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(GlobalSearchAndReplaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return output;
    }
}
