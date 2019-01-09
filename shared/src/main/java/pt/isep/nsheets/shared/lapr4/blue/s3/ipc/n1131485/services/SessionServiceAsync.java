package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1131485.dto.lockDTO;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

public interface SessionServiceAsync {

    void isLocked(lockDTO dto, AsyncCallback<Boolean> async);

    void closeSession(Workbook wb, AsyncCallback<Void> async);

    void openSession(Workbook wb, AsyncCallback<Void> async);

    void lockCell(lockDTO dto, AsyncCallback<Void> async);

    void unlockCell(lockDTO dto, AsyncCallback<Void> async);
}
