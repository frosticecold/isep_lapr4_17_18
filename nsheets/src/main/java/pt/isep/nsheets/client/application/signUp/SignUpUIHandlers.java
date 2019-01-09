package pt.isep.nsheets.client.application.signUp;

import com.gwtplatform.mvp.client.UiHandlers;

public interface SignUpUIHandlers extends UiHandlers {
    void onSignUp(String username, String password, String email, String name, String path);
}
