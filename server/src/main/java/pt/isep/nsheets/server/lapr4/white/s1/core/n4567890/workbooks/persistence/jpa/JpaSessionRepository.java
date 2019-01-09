package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.domain.Session;
import pt.isep.nsheets.server.lapr4.blue.s3.ipc.n1131485.persistence.SessionRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.core.Workbook;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Query;

/**
 * Concrete class of Session Repository on JPA Framework context
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaSessionRepository extends NSheetsJpaRepositoryBase<Session, Long> implements SessionRepository {

    JpaSessionRepository(PersistenceSettings settings) {
        super(settings);
    }

    /**
     * Returns a session of a certain workbook
     *
     * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
     *
     *
     * @param wb
     * @return
     */
    @Override
    public Session getSessionOfWorkbook(Workbook wb) {

        String query = "SELECT e FROM Session e WHERE e.wb = :wkb";

        try {
            final Query q = entityManager().createQuery(query, Session.class);

            q.setParameter("wkb", wb);

            return (Session) q.getSingleResult();
        } catch (Exception e) {

            System.out.println("Exception = " + e.getMessage());

            return null;
        }
    }
}
