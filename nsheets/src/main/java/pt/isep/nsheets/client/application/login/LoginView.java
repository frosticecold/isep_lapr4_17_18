package pt.isep.nsheets.client.application.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @Author Rui Almeida LAPR4 - GREEN - S1 - IPC - IPC01.1 - USERAUTH <1160818>
 */
class LoginView extends ViewWithUiHandlers<LoginPresenter> implements LoginPresenter.MyView {

    @UiField
    MaterialTextBox pass, user;

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton btnLogin;

    @UiField
    MaterialButton btnLogout;

    interface Binder extends UiBinder<Widget, LoginView> {
    }

    @Inject
    LoginView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("btnLogin")
    void onLogin(ClickEvent clickEvent) {
        if (user.getText().isEmpty()) user.setText("Enter login details!");
        if (pass.getText().isEmpty()) pass.setText("password");
        getUiHandlers().onLogin(user.getText(), pass.getText());
        pass.clear();
    }

    @UiHandler("btnLogout")
    void onLogout(ClickEvent clickEvent) {
        getUiHandlers().onLogout(user.getText());
        user.clear();
        pass.clear();
    }


}
