package pt.isep.nsheets.client.application.login;

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
import pt.isep.nsheets.client.application.menu.MenuPresenter;
import pt.isep.nsheets.shared.services.AuthenticationService;
import pt.isep.nsheets.shared.services.AuthenticationServiceAsync;
import pt.isep.nsheets.shared.services.UsersService;
import pt.isep.nsheets.shared.services.UsersServiceAsync;

/**
 * @Author Rui Almeida LAPR4 - GREEN - S1 - IPC - IPC01.1 - USERAUTH <1160818>
 */
public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements LoginUiHandlers {

    @ProxyStandard
    @NameToken(NameTokens.login)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    interface MyView extends View, HasUiHandlers<LoginPresenter> {
    }

    private PlaceManager placeManager;
    private AuthenticationServiceAsync authService;
    private LoggedInGateKeeper keeper;

    @Inject
    LoginPresenter(EventBus eventBus,
            LoginPresenter.MyView view,
            LoginPresenter.MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        this.placeManager = placeManager;
        getView().setUiHandlers(this);

    }

    @Override
    public void onLogin(String username, String password) {

        if (CurrentUser.isLogged()) {
            MaterialToast.fireToast("User " + CurrentUser.username() + " is already logged in.");
        } else {
            authService = GWT.create(AuthenticationService.class);

            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                @Override
                public void onFailure(Throwable throwable) {
                    MaterialToast.fireToast("An internal error has occurred while logging the user in! " + throwable.getMessage());
                }

                @Override
                public void onSuccess(Boolean loggedIn) {
                    if (!loggedIn.booleanValue()) {
                        MaterialToast.fireToast("Could not login. Either username/password combination is wrong or the user does not exist!");
                    } else {
                        MaterialToast.fireToast("Logged in with success. Redirecting..");
                        PlaceRequest placeRequest = new PlaceRequest.Builder()
                                .nameToken(NameTokens.home)
                                .build();
                        placeManager.revealPlace(placeRequest);

                        CurrentUser.setLoggedIn(username);
                        MenuPresenter.getPresenter().refresh();
                        UsersServiceAsync userServ = GWT.create(UsersService.class);
                        AsyncCallback<String> emailcallback = new AsyncCallback<String>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                MaterialToast.fireToast("Cannot find user email...");
                            }

                            @Override
                            public void onSuccess(String result) {
                                CurrentUser.setEmail(result);
                            }
                        };
                        userServ.getUserEmail(CurrentUser.username(),emailcallback);
                    }
                }
            };

            authService.validateCredentials(username, password, callback);

            AsyncCallback<String> callbackURL = new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable throwable) {
                    MaterialToast.fireToast("An internal error has occurred while logging the user in! " + throwable.getMessage());
                }

                @Override
                public void onSuccess(String url) {
                    PlaceRequest placeRequest = new PlaceRequest.Builder()
                            .nameToken(NameTokens.home)
                            .build();
                    placeManager.revealPlace(placeRequest);

                    CurrentUser.setPicture(url);
                    MenuPresenter.getPresenter().refresh();
                }
            };
            authService.getURL(username, callbackURL);
        }

    }

    @Override
    public void onLogout(String username) {
        if (CurrentUser.isLogged()) {
            String aux = CurrentUser.username();
            CurrentUser.setLoggedOut();
            if (!CurrentUser.isLogged()) {
                MaterialToast.fireToast(aux + " was logged out with success.");
            }

            MenuPresenter.getPresenter().refresh();
        }
    }

    public void refreshDatabase() {
        authService = GWT.create(AuthenticationService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("An internal error has occurred while starting the database! " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Boolean loggedIn) {

            }
        };

        authService.refreshDatabase(callback);

    }

    @Override
    protected void onReveal() {
        super.onReveal();

        if (CurrentUser.isLogged()) {
            MaterialToast.fireToast("Already logged in.");
        }

        SetPageTitleEvent.fire("Login", "User Login", "", "", this);

        refreshDatabase();
    }

}
