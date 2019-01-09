package pt.isep.nsheets.lapr4.blue.s3.ipc.n1131485.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain.Session;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.core.Workbook;

/**
 * Bootstrapper for Sessions
 *
 * @author PedroEmanuelCoelho <1131485@isep.ipp.pt>
 */
public class SessionBootstrapper implements Action {

    @Override
    public boolean execute() {

        Workbook wb1 = new Workbook();

        this.registerWorkbook(wb1);

        this.registerSession(wb1);
        
        this.closeSession(wb1);

        return true;
    }
    
    /**
     * Method to save workbook
     * 
     * @author PedroEmanuelCoelho 113148@isep.ipp.pt
     */
    private void registerWorkbook(Workbook wb) {
        
        System.out.println("Registering Workbook");
        
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
        
        try{
            PersistenceContext.repositories().workbooks().save(wb);
        }
        catch(DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    /**
     * Method to handle with the Persistence Context
     */
    private void registerSession(Workbook wb) {

        System.out.println("Register Session");

        Session session = new Session(wb);

        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());

        try {

            PersistenceContext.repositories().workbookSessions().save(session);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("Exception1 = " + e.getMessage());
        }
    }

    /**
     * Method to close the Session later
     */
    private void closeSession(Workbook wb) {

        System.out.println("closing Session");

        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());

        Session s = PersistenceContext.repositories().workbookSessions().getSessionOfWorkbook(wb);

        if (s != null) {
            s.closeSession();
        } else {
            System.out.println("Session null");
        }

    }

}
