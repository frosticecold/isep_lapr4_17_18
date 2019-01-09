package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

public class JpaWorkbookDescriptionRepository extends NSheetsJpaRepositoryBase<WorkbookDescription, Long> implements WorkbookDescriptionRepository {

	JpaWorkbookDescriptionRepository(PersistenceSettings settings) {
        super(settings);
    }
        public void delete(WorkbookDescription entity){
        
        final EntityTransaction tx = entityManager().getTransaction();
        
        tx.begin();
        if(entity == null) throw new IllegalArgumentException();
        
        entity = entityManager().merge(entity);
        entityManager().remove(entity);
        tx.commit();
    }

    @Override
    public WorkbookDescription findWorkbookDescriptionByName(String name){
        final Query q;
        q = entityManager().createQuery("SELECT w FROM WorkbookDescription w "
                + "WHERE w.name = :name");
        q.setParameter("name", name);
        return (WorkbookDescription)q.getSingleResult();
    }

    
    @Override
    public Iterable<WorkbookDescription> getPublicWorkbooks(Boolean isPublic) {
        final Query q;
        q = entityManager().createQuery("SELECT w FROM WorkbookDescription w "
                + "WHERE w.workbook.isPublic = :flag");
        q.setParameter("flag", true);
        return q.getResultList();
    }

    /**
     * Modified getPrivateWorkbooks
     * So it can filter workbooks by its type of access
     * @return 
     */
    @Override
    public Iterable<WorkbookDescription> getPrivateWorkbooks(String email) {
       final Query q;

         q = entityManager().createQuery("SELECT w FROM WorkbookDescription w "
                + "WHERE w.workbook.isPublic = :flag");
        q.setParameter("flag", false);

       /**q = entityManager().createQuery("SELECT w FROM WorkbookDescription w WHERE w.listOfAccess.list.access.email = :email");
       q.setParameter("email", email);
       * **/
        return q.getResultList();
    }

    /**
     * We need to workbook descriptions on spreadsheet page
     *
     * Best way to find it is by searching by Workbook
     *
     * @author PedroEmanuelCoelho
     */
    public WorkbookDescription findDescriptioByWorkbook(Workbook wb) {

        String query = "SELECT description FROM WorkbookDescription description" +
                "WHERE workbook := wb";

        final Query q = entityManager().createQuery(query);

        q.setParameter("wb", wb);

        return (WorkbookDescription) q.getSingleResult();
    }

}
