package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.application.SessionController;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services.SessionService;


/**
 * Concrete class of the SessionService
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

public class SessionServiceImpl extends RemoteServiceServlet implements SessionService{


    @Override
    public void openSession(Workbook wb) {

        SessionController ctrl = new SessionController();

        ctrl.openSession(wb);
    }

    @Override
    public void closeSession(Workbook wb) {

        SessionController ctrl = new SessionController();

        ctrl.closeSession(wb);
    }

    @Override
    public Boolean isLocked(lockDTO dto) {

        SessionController ctrl = new SessionController();

        return ctrl.isCellLocked(dto);
    }


    /**
     * Lock cell - mutual exclusion
     * 
     * @param dto 
     */
    @Override
    public void lockCell(lockDTO dto) {

        SessionController ctrl = new SessionController();
        
        ctrl.locksCell(dto);
    }
    
    /**
     * Unlock cell - mutual exclusion
     * 
     * @param dto 
     */

    @Override
    public void unlockCell(lockDTO dto) {

        SessionController ctrl = new SessionController();
        
        ctrl.unlocksCell(dto);

    }

}
