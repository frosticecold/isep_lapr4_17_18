package pt.isep.nsheets.client.application.signUp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.LoggedInGateKeeper;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.shared.services.SignUpService;
import pt.isep.nsheets.shared.services.SignUpServiceAsync;

public class SignUpPresenter extends Presenter<SignUpPresenter.MyView, SignUpPresenter.MyProxy> implements SignUpUIHandlers {


    @NameToken(NameTokens.signUp)
    @ProxyStandard
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<SignUpPresenter> {
    }

    interface MyView extends View, HasUiHandlers<SignUpPresenter> {
    }

    private PlaceManager placeManager;
    private SignUpServiceAsync signUpService;
    //private LoggedInGateKeeper keeper;

    @Inject
    SignUpPresenter(EventBus eventBus,
                   SignUpPresenter.MyView view,
                   SignUpPresenter.MyProxy proxy,
                   PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        this.placeManager = placeManager;
        getView().setUiHandlers(this);

    }

    @Override
    public void onSignUp(String username, String password, String email, String name, String path) {
                
        signUpService = GWT.create(SignUpService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("An internal error has occurred while signing up! " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Boolean created) {
                
                if (created.booleanValue()==false) {
                        MaterialToast.fireToast("The user was not created, the username or email are already in use.");
                } else {
                        MaterialToast.fireToast("The user was created successfully. ");
                    PlaceRequest placeRequest = new PlaceRequest.Builder()
                            .nameToken(NameTokens.home)
                            .build();
                    placeManager.revealPlace(placeRequest);
                }
            }
        };
        
        signUpService.createUser(username, password, email, "USER", name, path, callback);
    }

    public void refreshDatabase() {
        signUpService = GWT.create(SignUpService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("An internal error has occurred while starting the database! " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Boolean exists) {

            }
        };

        signUpService.refreshDatabase(callback);

    }


    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Sign Up", "User Sign Up", "", "", this);

        refreshDatabase();
    }
    

}
