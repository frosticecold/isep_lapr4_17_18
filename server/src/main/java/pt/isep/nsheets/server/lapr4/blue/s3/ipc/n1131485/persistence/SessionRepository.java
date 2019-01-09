package pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.persistence;

import eapli.framework.persistence.repositories.Repository;
import java.util.List;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain.Session;
import pt.isep.nsheets.shared.core.Workbook;


/**
 * Interface for Session Repository
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

public interface SessionRepository extends Repository<Session, Long> {

    Session getSessionOfWorkbook(Workbook wb);
}
