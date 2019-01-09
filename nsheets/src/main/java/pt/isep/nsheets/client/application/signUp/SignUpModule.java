package pt.isep.nsheets.client.application.signUp;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SignUpModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(SignUpPresenter.class, SignUpPresenter.MyView.class, SignUpView.class, SignUpPresenter.MyProxy.class);
    }
}
