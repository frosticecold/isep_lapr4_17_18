package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;

/**
 * Contract for the the service of Session
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
@RemoteServiceRelativePath("SessionServices")
public interface SessionService extends RemoteService {

    void openSession(Workbook wb);

    void closeSession(Workbook wb);

    Boolean isLocked(lockDTO dto); //check if Cell is locked so the workbook can be refreshed in being realtime updates

    void lockCell(lockDTO dto); //locks activeCell

    void unlockCell(lockDTO dto); //unlocks activeCell
}
