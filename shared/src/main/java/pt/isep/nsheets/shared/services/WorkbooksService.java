package pt.isep.nsheets.shared.services;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("workbooksService")
public interface WorkbooksService extends RemoteService {

    ArrayList<WorkbookDescriptionDTO> getWorkbooks();

    ArrayList<WorkbookDescriptionDTO> getUsersWorkbooks(String username);
    
    ArrayList<WorkbookDescriptionDTO> getPublicWorkbooks(String username);

    ArrayList<WorkbookDescriptionDTO> getPrivateWorkbooks(String username);

    UserDTO addWorkbookDescription(WorkbookDescriptionDTO wdDto, Boolean isPublic, String username) throws DataException;

    ArrayList<WorkbookDescriptionDTO> listFilteredWorkbooksDescription(ArrayList<WorkbookDescriptionDTO> workbookDescriptions, String filter);

    boolean changeWorkbookDescription(WorkbookDescriptionDTO dto, String description);

    boolean changeWorkbookName(WorkbookDescriptionDTO dto, String name);

    boolean deleteWorkbook(WorkbookDescriptionDTO dto);

    boolean saveWorkbook(WorkbookDescriptionDTO dto);
}
