package pt.isep.nsheets.shared.services;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WorkbooksServiceAsync {

    void getWorkbooks(AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback);
    void getUsersWorkbooks(String username,AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback);

    void addWorkbookDescription(WorkbookDescriptionDTO wdDto, Boolean isPublic, String username, AsyncCallback<UserDTO> callback);

    void listFilteredWorkbooksDescription(ArrayList<WorkbookDescriptionDTO> workbookDescriptions, String filter, AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback);

    void changeWorkbookDescription(WorkbookDescriptionDTO dto, String description, AsyncCallback<Boolean> callback);

    void changeWorkbookName(WorkbookDescriptionDTO dto, String name, AsyncCallback<Boolean> callback);

    void deleteWorkbook(WorkbookDescriptionDTO dto, AsyncCallback callback);

    void getPublicWorkbooks(String username,AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback);

    void getPrivateWorkbooks(String username,AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback);

    void saveWorkbook(WorkbookDescriptionDTO dto, AsyncCallback<Boolean> callback);
}
