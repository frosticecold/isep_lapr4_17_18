package pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1150344.profile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.menu.MenuPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.ProfileService;
import pt.isep.nsheets.shared.services.ProfileServiceAsync;
import pt.isep.nsheets.shared.services.UserDTO;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class ProfilePresenter extends Presenter<ProfilePresenter.MyView, ProfilePresenter.MyProxy> implements ProfileUiHandlers {

    @ProxyStandard
    @NameToken(NameTokens.profile)
    interface MyProxy extends ProxyPlace<ProfilePresenter> {
    }

    public interface MyView extends View, HasUiHandlers<ProfilePresenter> {

        MaterialPanel getNormalUserPanel();

        MaterialPanel getSuperUserPanel();

        MaterialTextBox getNameBox();

        MaterialImage getUserImage();

        FlexTable getTable();
    }

    private PlaceManager placeManager;
    private ProfileServiceAsync profileService;

    private UserDTO activeUser = null;

    @Inject
    ProfilePresenter(EventBus eventBus,
            MyView view,
            MyProxy proxy, PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        view.setUiHandlers(this);
        this.placeManager = placeManager;

    }

    public void refreshDatabase() {
        profileService = GWT.create(ProfileService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("An internal error has occurred while starting the database! " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Boolean exists) {
            }
        };

        profileService.refreshDatabase(callback);

    }

    private void isSuperUser() {
        profileService = GWT.create(ProfileService.class);

        AsyncCallback<UserDTO> callback = new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("An internal error has occurred while retrieving the current user! " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserDTO result) {
                if (result.getRole().equalsIgnoreCase("Admin")) {
                    getView().getSuperUserPanel().setVisible(true);
                    getView().getNormalUserPanel().setVisible(false);
                } else {
                    getView().getSuperUserPanel().setVisible(false);
                    getView().getNormalUserPanel().setVisible(true);
                    getView().getNameBox().setText(result.getName());
                    getView().getUserImage().setUrl(result.getPicture());
                }
                activeUser = result;
            }

        };

        profileService.getUserByUsername(CurrentUser.username(), callback);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Profile", "My Profile", "", "", this);

        refreshDatabase();
        isSuperUser();
    }

    @Override
    public void onDeleteAccount() {
        profileService = GWT.create(ProfileService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("An internal error has occurred while deleting the account." + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    MaterialToast.fireToast("The user was deleted successfully.");
                    PlaceRequest placeRequest = new PlaceRequest.Builder()
                            .nameToken(NameTokens.login)
                            .build();
                    placeManager.revealPlace(placeRequest);
                } else {
                    MaterialToast.fireToast("The account was not deleted, invalid user.");
                }
            }

        };

        profileService.deleteAccount(CurrentUser.username(), callback);
    }

    @Override
    public void onSaveChanges(String username, String name, String imagePath) {
        profileService = GWT.create(ProfileService.class);

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("An internal error has occurred while saving the changes." + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    MaterialToast.fireToast("The user was saved successfully.\nLogin again.");
                    PlaceRequest placeRequest = new PlaceRequest.Builder()
                            .nameToken(NameTokens.login)
                            .build();
                    placeManager.revealPlace(placeRequest);
                    CurrentUser.setLoggedOut();
                    MenuPresenter.getPresenter().refresh();
                } else {
                    MaterialToast.fireToast("The changes were not saved.");
                }
            }

        };

        profileService.saveChanges(activeUser, username, name, imagePath, callback);
    }

    @Override
    public void loadUsers(FlexTable table) {
        if (profileService == null) {
            profileService = GWT.create(ProfileService.class);
        }

        AsyncCallback<Iterable<UserDTO>> callback = new AsyncCallback<Iterable<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("An internal error has occurred while loading the users." + caught.getMessage());
            }

            @Override
            public void onSuccess(Iterable<UserDTO> result) {
                if (!result.iterator().hasNext()) {
                    MaterialToast.fireToast("No users available");
                } else {
                    for (UserDTO userDTO : result) {
                        MaterialSwitch ms = new MaterialSwitch("Enable", "Disable", userDTO.isActive());
                        ms.setMargin(8);
                        ms.addValueChangeHandler((ValueChangeEvent<Boolean> event) -> {
                            changeUserStatus(userDTO, event.getValue());
                        });
                        ms.setWidth("160px");

                        MaterialImage ma = new MaterialImage();
                        ma.setUrl(userDTO.getPicture());
                        ma.setWidth("30px");
                        ma.setHeight("30px");
                        ma.setBackgroundColor(Color.WHITE);
                        ma.setCircle(true);
                        ma.setMargin(8);
                        ma.setFloat(Style.Float.RIGHT);

                        MaterialLabel ml = new MaterialLabel(userDTO.getUsername());

                        int numRows = getView().getTable().getRowCount();

                        table.setWidget(numRows, 0, ma);
                        table.setWidget(numRows, 1, ml);
                        table.setWidget(numRows, 2, ms);
                        table.getColumnFormatter().setWidth(0, "40px");
                        table.getColumnFormatter().setWidth(1, "100px");
                        table.getColumnFormatter().setWidth(2, "160px");

                        if (userDTO.getRole().equalsIgnoreCase("admin")) {
                            ((MaterialSwitch) table.getWidget(numRows, 2)).setEnabled(false);
                        }
                    }
                }
            }

        };
        profileService.loadUsers(callback);
    }

    private void changeUserStatus(UserDTO user, boolean status) {
        if (profileService == null) {
            profileService = GWT.create(ProfileService.class);
        }

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Couldn't change the user status.");
            }

            @Override
            public void onSuccess(Boolean result) {
                MaterialToast.fireToast("User status changed");
            }
        };

        profileService.changeStatus(user, status, callback);
    }

}
