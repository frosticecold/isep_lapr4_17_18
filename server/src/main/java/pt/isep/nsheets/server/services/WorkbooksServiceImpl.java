package pt.isep.nsheets.server.services;

import java.util.ArrayList;
import java.util.Properties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1090657.application.SaveWorkbookController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application.DeleteWorkbookController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application.EditWorkbookDescriptionController;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1161838.workbooks.application.ListFilteredWorkbooksController;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161027.ipc06.application.ipc06Controller;
import pt.isep.nsheets.shared.services.WorkbooksService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.application.AddWorkbookDescriptionController;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.application.ListWorkbookDescriptionController;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessType;
import pt.isep.nsheets.shared.services.DataException;
import pt.isep.nsheets.shared.services.UserDTO;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

public class WorkbooksServiceImpl extends RemoteServiceServlet implements WorkbooksService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();

        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        // Other JPA properties that one might want to override from the ones in
        // persistence.xml
        // props.put("javax.persistence.jdbc.url",
        // "jdbc:h2:../db/nsheets;MV_STORE=FALSE;MVCC=FALSE");
        // props.put("javax.persistence.jdbc.password", "");
        // props.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        // props.put("javax.persistence.jdbc.user", "");
        // props.put("javax.persistence.schema-generation.database.action", "create");
        // props.put("eclipselink.logging.level", "FINE");
        return new PersistenceSettings(props);
    }

    @Override
    public ArrayList<WorkbookDescriptionDTO> getUsersWorkbooks(String username) {
        // Setup the persistence settings
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<WorkbookDescriptionDTO> workbooks = new ArrayList<WorkbookDescriptionDTO>();

        ipc06Controller c = new ipc06Controller();

        List<WorkbookDescription> list = c.getWorkbooksFromCurrentUser(username);

        for (WorkbookDescription wd : list) {
            workbooks.add(wd.toDTO());
        }

        return workbooks;
    }

    @Override
    public ArrayList<WorkbookDescriptionDTO> getPublicWorkbooks(String username) {
        // Setup the persistence settings
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<WorkbookDescriptionDTO> workbooks = new ArrayList<WorkbookDescriptionDTO>();

        ipc06Controller c = new ipc06Controller();

        //Iterable<WorkbookDescription> wbs = c.listPublicWorkbookDescriptions();
        Iterable<WorkbookDescription> list = c.listPublicWorkbookDescriptions();

        List<WorkbookDescription> listFromUser = new ArrayList<>();

        for (WorkbookDescription wd : list) {
            if (wd.getWorkbook().isIsPublic()) {
                listFromUser.add(wd);
            }
        }

        for (WorkbookDescription wbd : listFromUser) {
            workbooks.add(wbd.toDTO());
        }

        return workbooks;
    }

    @Override
    public ArrayList<WorkbookDescriptionDTO> getPrivateWorkbooks(String username) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        ArrayList<WorkbookDescriptionDTO> workbooks = new ArrayList<WorkbookDescriptionDTO>();

        ipc06Controller c = new ipc06Controller();

        User currentUser = c.getCurrentUser(username);
        String email = currentUser.getEmail().toString();
        Iterable<WorkbookDescription> list = c.listPrivateWorkbookDescriptions(email);

        List<WorkbookDescription> listFromUser = new ArrayList<>();



        for (WorkbookDescription wbd : listFromUser) {
            workbooks.add(wbd.toDTO());
        }

        return workbooks;
    }

    @Override
    public UserDTO addWorkbookDescription(WorkbookDescriptionDTO wdDto, Boolean isPublic, String username)
            throws DataException {
        // Setup the persistence settings
        PersistenceContext.setSettings(this.getPersistenceSettings());

        AddWorkbookDescriptionController ctrl = new AddWorkbookDescriptionController();

        ipc06Controller c = new ipc06Controller();

        List<WorkbookDescription> list = c.getWorkbooksFromCurrentUser(username);

        List<WorkbookDescriptionDTO> dtoList = new ArrayList<>();

        for (WorkbookDescription w : list) {
            dtoList.add(w.toDTO());
        }
        User u = c.getCurrentUser(username);
        try {
            WorkbookDescription newWB = ctrl.addWorkbookDescription(wdDto, isPublic);

            if (!isPublic) {
                c.savePrivateUser(username, newWB);
            } else {
                c.savePublicUser(newWB);
            }

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            throw new DataException((Throwable) ex);
        }

        return u.toDTO();
    }

    /**
     * Implementation of the method listFilteredWorkbooks
     *
     * @param filter
     * @return List of workbooks filtered by name
     */
    @Override
    public ArrayList<WorkbookDescriptionDTO> listFilteredWorkbooksDescription(ArrayList<WorkbookDescriptionDTO> workbookDescriptions, String filter) {
        // Setup the persistence settings
        PersistenceContext.setSettings(this.getPersistenceSettings());
        ListFilteredWorkbooksController controller = new ListFilteredWorkbooksController();
        return controller.findFilteredWorkbookDescriptions(workbookDescriptions, filter);
    }

    /**
     * Method that changes the workbook description It receives a
     * WorkbookDescriptionDTO because the package doesnt have access to the
     * WorkbookDescription
     *
     * @author Joao Rocha <1161838>
     * @param dto
     * @param description
     * @return
     */
    @Override
    public boolean changeWorkbookDescription(WorkbookDescriptionDTO dto, String description) {
        EditWorkbookDescriptionController controller = new EditWorkbookDescriptionController();
        boolean changedDescription = false;

        try {
            changedDescription = controller.editWorkbookDescription(dto, description);
        } catch (DataConcurrencyException ex) {
            return false;
        } catch (DataIntegrityViolationException ex) {
            return false;
        }

        if (!changedDescription) {
            return false;
        }

        return true;
    }

    /**
     * Method that changes the workbook name It receives a
     * WorkbookDescriptionDTO because the package doesnt have access to the
     * WorkbookDescription
     *
     * @author Joao Rocha <1161838>
     * @param dto
     * @param name
     * @return
     */
    @Override
    public boolean changeWorkbookName(WorkbookDescriptionDTO dto, String name) {
        EditWorkbookDescriptionController controller = new EditWorkbookDescriptionController();
        boolean changedName = false;

        try {
            changedName = controller.editWorkbookName(dto, name);
        } catch (DataConcurrencyException ex) {
            return false;
        } catch (DataIntegrityViolationException ex) {
            return false;
        }

        if (!changedName) {
            return false;
        }

        return true;

    }

    /**
     * Method that deletes a certain workbook Not fully implemented because the
     * workbookRepository wasn't implemented at that time
     *
     * @author Joao Rocha <1161838>
     * @param dto
     * @return
     */
    @Override
    public boolean deleteWorkbook(WorkbookDescriptionDTO dto) {
        DeleteWorkbookController controller = new DeleteWorkbookController();
        boolean success = controller.deleteWorkbook(dto);

        if (success) {
            return true;
        }
        return false;

    }

    @Override
    public boolean saveWorkbook(WorkbookDescriptionDTO dto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        SaveWorkbookController controller = new SaveWorkbookController();
        return controller.saveWorkbook(dto);
    }

    @Override
    public ArrayList<WorkbookDescriptionDTO> getWorkbooks() {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        ListWorkbookDescriptionController listwbcontroller = new ListWorkbookDescriptionController();
        ArrayList<WorkbookDescriptionDTO> workbooks = new ArrayList<WorkbookDescriptionDTO>();
        Iterable<WorkbookDescription> wbs = listwbcontroller.listWorkbookDescriptions();

        wbs.forEach(wb -> workbooks.add(wb.toDTO()));
        return workbooks;
    }

}
