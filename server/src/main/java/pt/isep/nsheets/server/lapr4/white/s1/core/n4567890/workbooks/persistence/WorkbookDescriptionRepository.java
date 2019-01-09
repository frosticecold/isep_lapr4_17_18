package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence;

import eapli.framework.persistence.repositories.Repository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.shared.core.Workbook;

public interface WorkbookDescriptionRepository extends Repository<WorkbookDescription, Long> {

    void delete(WorkbookDescription entity);
    WorkbookDescription findWorkbookDescriptionByName(String name);
    Iterable<WorkbookDescription> getPublicWorkbooks(Boolean isPublic);
    Iterable<WorkbookDescription> getPrivateWorkbooks(String email);

    //@author PedroEmanuelCoelho 1131485@isep.ipp.pt
    WorkbookDescription findDescriptioByWorkbook(Workbook wb);
}
