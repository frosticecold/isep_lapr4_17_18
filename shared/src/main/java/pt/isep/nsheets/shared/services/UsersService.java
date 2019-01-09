package pt.isep.nsheets.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Magalhaes<1160763>
 */
@RemoteServiceRelativePath("usersService")
public interface UsersService extends RemoteService {

    void getContacts(String username);

    void addContact(String contact, String username) throws DataException;

    boolean saveUser(String username);

    List<WorkbookDescriptionDTO> getWorkbooksFromUser(String username);
    
    String getUserEmail(final String username);
}
