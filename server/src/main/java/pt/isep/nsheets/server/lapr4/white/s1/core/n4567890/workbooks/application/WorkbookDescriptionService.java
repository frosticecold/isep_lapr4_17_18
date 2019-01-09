package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.WorkbookDescriptionRepository;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

public class WorkbookDescriptionService {

    public Iterable<WorkbookDescription> allWorkbookDescriptions() {

        final WorkbookDescriptionRepository workbookDescriptionRepository = PersistenceContext.repositories().workbookDescriptions();
        return workbookDescriptionRepository.findAll();
    }

    public Iterable<WorkbookDescription> publicWorkbookDescriptions() {

        final WorkbookDescriptionRepository workbookDescriptionRepository = PersistenceContext.repositories().workbookDescriptions();
        return workbookDescriptionRepository.getPublicWorkbooks(Boolean.TRUE);
    }

    /**
     * CHANGE THIS METHOD SO IT FETCHES ALL PRIVATE WORKBOOK FROM A USER
     *
     * @return
     */
    public Iterable<WorkbookDescription> privateWorkbookDescriptions(String email) {

        final WorkbookDescriptionRepository workbookDescriptionRepository = PersistenceContext.repositories().workbookDescriptions();
        return workbookDescriptionRepository.getPrivateWorkbooks(email);
    }

    public WorkbookDescription addWorkbookDescription(WorkbookDescriptionDTO dto, Boolean isPublic) throws DataConcurrencyException, DataIntegrityViolationException {

        final WorkbookDescriptionRepository workbookDescriptionRepository = PersistenceContext.repositories().workbookDescriptions();

        WorkbookDescription wb = new WorkbookDescription();
        wb.fromDTO(dto);
        wb.getWorkbook().setIsPublic(isPublic);
        wb =workbookDescriptionRepository.save(wb);

        return wb;
    }
}
