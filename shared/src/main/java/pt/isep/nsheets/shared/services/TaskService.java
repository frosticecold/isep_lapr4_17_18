package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
@RemoteServiceRelativePath("taskservice")
public interface TaskService extends RemoteService {

    List<TaskDTO> getAllTasks(String username);
    
    List<TaskDTO> getAllCompleteTasks(String username);

    void addNewTask(TaskDTO dto);

    void editTask(TaskDTO dto, String oldName);

    Boolean removeTask(TaskDTO dto);

    List<String> getUserEmails();
}
