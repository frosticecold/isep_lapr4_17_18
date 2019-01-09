package pt.isep.nsheets.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.red.s2.ipc.n1161032.application.SignUpController;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.SignUpService;

public class SignUpServiceImpl extends RemoteServiceServlet implements SignUpService {
    
    @Override
    public Boolean createUser(String username, String password, String email, String role, String name, String path) {
        SignUpController controller = new SignUpController();
        
        try {
                return controller.createUser(username, password, email, role, name, path);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(SignUpServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 

    @Override
    public Boolean refreshDatabase() {
        // Setup the persistence settings
        PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
        return true;
    }


}
