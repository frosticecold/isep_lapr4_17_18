package pt.isep.nsheets.client.application;

import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import pt.isep.nsheets.client.CurrentUser;

import javax.inject.Inject;

/**
 * @Author Rui Almeida LAPR4 - GREEN - S1 - IPC - IPC01.1 - USERAUTH <1160818>
 */
@DefaultGatekeeper
public class LoggedInGateKeeper implements Gatekeeper {


    @Inject
    private LoggedInGateKeeper() {}

    /**
     * Checks if the user is logged in
     * @return true if the user is logged in, false if not
     */
    @Override
    public boolean canReveal() {
        return CurrentUser.isLogged();
    }
}
