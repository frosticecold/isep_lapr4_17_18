/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.persistence.WorkbookRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.core.Workbook;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 * Class for workbook persistance testing
 */
public class WorkbookBootstraper implements Action {

    @Override
    public boolean execute() {
        registerWorkbook(generateRandomWorkbook());
        registerWorkbook(generateRandomWorkbook());
        registerWorkbook(generateRandomWorkbook());
        registerWorkbook(generateRandomWorkbook());
        registerWorkbook(generateRandomWorkbook());
        registerWorkbook(generateRandomWorkbook());
        return true;
    }

    private void registerWorkbook(final Workbook wb) {
        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            WorkbookRepository repo = PersistenceContext.repositories().workbooks();
            repo.save(wb);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(WorkbookBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(WorkbookBootstraper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Workbook generateRandomWorkbook() {
        /*String content[][] = {
            {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"}, {"1", "2", "3", "4", "5", "6", "7"}, {"8", "7", "6", "5", "4", "3", "2"}};
         */
        final int lines = 5;
        final int cols = 5;
        String content[][] = new String[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                content[i][j]=String.valueOf(Math.random()%100);
            }
        }
        return new Workbook(content, content);
    }

}
