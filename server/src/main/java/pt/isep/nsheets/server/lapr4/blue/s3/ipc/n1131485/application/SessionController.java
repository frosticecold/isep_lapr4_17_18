package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.application;

/**
 * General controller for handling Workbook Session features
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

import static cern.clhep.Units.s;
import eapli.framework.application.Controller;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain.Session;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;


public class SessionController implements Controller {

    public SessionController() {
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
    }

    /**
     * Method that checks if a active cell is currently locked on THE session of the public active Workbook
     *
     * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
     *
     * @param dto
     * @return
     */
    public boolean isCellLocked(lockDTO dto) {

        boolean flag = false; //we assume there isnt any lock on current Cell

        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());

       Session session = getActiveSession(dto.workbook());

        if(session != null) {
            if(session.locks().get(dto)) //cell is locked
                flag = true;
        }

        return flag;
    }

    /**
     * Creates or re-open a session
     */
    public void openSession(Workbook wb) {

        Session s = getActiveSession(wb);

        if(s != null) {

            if(!s.isActive()) {
                s.opensSession();
            }
            else {
                s = new Session(wb);
            }
        }

        try {
            PersistenceContext.repositories().workbookSessions().save(s);
        }
        catch(Exception e) {
            System.out.println("Exception = " + e.getMessage());
        }
    }

    /**
     * Closes an opened session
     */
    public void closeSession(Workbook wb) {

        Session s = getActiveSession(wb);

        if(s != null) {
            if(s.isActive()) {
                s.closeSession();
            }
        }

    }

    /**
     * Gets the session of the active Workbook
     */
    private Session getActiveSession(Workbook wb) {

        return PersistenceContext.repositories().workbookSessions().getSessionOfWorkbook(wb);
    }

    /**
     * Locks Cell
     */
    public void locksCell(lockDTO dto) {
        
        Session s = this.getActiveSession(dto.workbook());
        
        s.addNewLock(dto);
    }
    
    /**
     * Unlocks Cell
     */
    public void unlocksCell(lockDTO dto) {
        
        Session s = this.getActiveSession(dto.workbook());
        
        s.disableLock(dto);
    }
}
