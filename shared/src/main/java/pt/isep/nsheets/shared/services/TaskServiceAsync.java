package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public interface TaskServiceAsync {

    void getAllTasks(String username, AsyncCallback<List<TaskDTO>> callback);
    
    void getAllCompleteTasks(String username, AsyncCallback<List<TaskDTO>> callback);

    void addNewTask(TaskDTO dto, AsyncCallback<TaskDTO> callback);

    void editTask(TaskDTO dto, String oldName, AsyncCallback<TaskDTO> callback);

    void removeTask(TaskDTO dto, AsyncCallback<Boolean> callback);
    
    void getUserEmails(AsyncCallback<List<String>> callback);

}
