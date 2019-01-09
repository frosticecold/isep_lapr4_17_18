package pt.isep.nsheets.client.application.login;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @Author Rui Almeida LAPR4 - GREEN - S1 - IPC - IPC01.1 - USERAUTH <1160818>
 */
public class LoginModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class, LoginPresenter.MyProxy.class);
    }
}
