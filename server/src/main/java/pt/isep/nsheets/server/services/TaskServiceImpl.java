package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.application.TaskController;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.services.TaskDTO;
import pt.isep.nsheets.shared.services.TaskService;

/**
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskServiceImpl extends RemoteServiceServlet implements TaskService {

    private PersistenceSettings getPersistenceSettings() {

        Properties props = new Properties();
        props.put("persistence.repositoryFactory",
                "pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa.JpaRepositoryFactory");
        props.put("persistence.persistenceUnit", "lapr4.NSheetsPU");

        return new PersistenceSettings(props);
    }

    @Override
    public List<TaskDTO> getAllTasks(String username) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        TaskController controller = new TaskController();
        List<Task> taskList = controller.getAllTasks(username);
        List<TaskDTO> dtoList = new ArrayList<>();
        for (Task task : taskList) {
            dtoList.add(task.toDTO());
        }
        return dtoList;
    }

    @Override
    public List<TaskDTO> getAllCompleteTasks(String username) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        TaskController controller = new TaskController();
        List<Task> taskList = controller.getAllCompleteTasks(username);
        List<TaskDTO> dtoList = new ArrayList<>();
        for (Task task : taskList) {
            dtoList.add(task.toDTO());
        }
        return dtoList;
    }

    @Override
    public void addNewTask(TaskDTO dto) {
        PersistenceContext.setSettings(this.getPersistenceSettings());

        TaskController controller = new TaskController();

        try {
            controller.addNewTask(dto);

        } catch (DataConcurrencyException ex) {
            Logger.getLogger(TaskServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(TaskServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editTask(TaskDTO dto, String oldName) {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        TaskController controller = new TaskController();

        try {
            controller.editTask(dto, oldName);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Boolean removeTask(TaskDTO dto) {
        TaskController controller = new TaskController();
        try {
            controller.removeTask(dto);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(NoteServiceImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<String> getUserEmails() {
        PersistenceContext.setSettings(this.getPersistenceSettings());
        TaskController controller = new TaskController();

        List<String> mailList = new ArrayList<>();

        return mailList = controller.getUserEmails();
    }
}
