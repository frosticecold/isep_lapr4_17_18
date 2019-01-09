package pt.isep.nsheets.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @Author Rui Almeida LAPR4 - GREEN - S1 - IPC - IPC01.1 - USERAUTH <1160818>
 */
public interface LoginUiHandlers extends UiHandlers {
    void onLogin(String username, String password);
    void onLogout(String username);
}
