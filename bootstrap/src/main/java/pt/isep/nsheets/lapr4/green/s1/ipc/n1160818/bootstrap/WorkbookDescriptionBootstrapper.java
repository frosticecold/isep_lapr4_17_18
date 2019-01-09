/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessType;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class WorkbookDescriptionBootstrapper implements Action {

    @Override
    public boolean execute() {
        registerWorkbookDescriptionBootstrapper("name1", "desc", Boolean.TRUE);
        registerWorkbookDescriptionBootstrapper("workbook", "test", Boolean.TRUE);
        registerWorkbookDescriptionBootstrapper("wbpriv", "priv1", Boolean.FALSE);
        registerWorkbookDescriptionBootstrapper("wbpriv2", "priv2", Boolean.FALSE);
        registerWorkbookDescriptionBootstrapper("wbpriv3", "priv3", Boolean.FALSE);
        registerWorkbookDescriptionBootstrapper("wbpriv4", "priv4", Boolean.FALSE);

        //Register private wb
        registerPrivateWorkbookDescription("test1", "Desc1", "john@isep.ipp.pt", AccessType.READ_WRITE);
        registerPrivateWorkbookDescription("test2", "Desc2", "jane@isep.ipp.pt", AccessType.READ_WRITE);
        registerPrivateWorkbookDescription("test3", "Desc3", "sa@isep.ipp.pt", AccessType.READ_WRITE);

        //Read Only
        registerPrivateWorkbookDescription("test4", "Desc4", "john@isep.ipp.pt", AccessType.READ_ONLY);
        registerPrivateWorkbookDescription("test5", "Desc5", "jane@isep.ipp.pt", AccessType.READ_ONLY);
        registerPrivateWorkbookDescription("test6", "Desc6", "sa@isep.ipp.pt", AccessType.READ_ONLY);

        //View Only
        registerPrivateWorkbookDescription("test7", "Desc7", "john@isep.ipp.pt", AccessType.VIEW_ONLY);
        registerPrivateWorkbookDescription("test8", "Desc8", "jane@isep.ipp.pt", AccessType.VIEW_ONLY);
        registerPrivateWorkbookDescription("test9", "Desc9", "sa@isep.ipp.pt", AccessType.VIEW_ONLY);
        return true;
    }

    /**
     * Calls the persistence unit and saves a workbook description in the
     * database.
     *
     * @param name
     * @param desc
     */
    public void registerWorkbookDescriptionBootstrapper(String name, String desc, Boolean isPublic) {
        String content[][] = {
            {"10", "-9", "8", "7", "1", "2", "3"}, {"8", "7", "6", "5", "4", "3", "2"}, {"1", "2", "3", "4", "5", "6", "7"}, {"8", "7", "6", "5", "4", "3", "2"}};
        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            WorkbookDescriptionRepository wds = PersistenceContext.repositories().workbookDescriptions();
            UserRepository u = PersistenceContext.repositories().users();

            WorkbookDescription dummy = new WorkbookDescription(name, desc, new Workbook(content,generateRandom(), generateRandom()));
            dummy.getWorkbook().setIsPublic(isPublic);
            if (isPublic == false) {
                dummy.getAccessList().addUserAccess("sa@isep.ipp.pt", AccessType.READ_WRITE);
            }
            wds.save(dummy);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }
    }

    private void registerPrivateWorkbookDescription(String name, String desc, String email, AccessType access) {
        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            WorkbookDescriptionRepository wds = PersistenceContext.repositories().workbookDescriptions();

            WorkbookDescription dummy = new WorkbookDescription(name, desc, new Workbook(generateRandom(), generateRandom()));
            dummy.getWorkbook().setIsPublic(false);
            dummy.getAccessList().addUserAccess(email, access);
            wds.save(dummy);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }

    }

    private String[][] generateRandom() {
        final int lines = 5;
        final int cols = 5;
        final int MAX_RANDOM = 100;
        String content[][] = new String[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                content[i][j] = String.valueOf(Math.random() % MAX_RANDOM);
            }
        }
        return content;
    }
}
