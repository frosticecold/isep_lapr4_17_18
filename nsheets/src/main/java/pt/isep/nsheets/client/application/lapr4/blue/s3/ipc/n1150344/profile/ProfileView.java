/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1150344.profile;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.menu.MenuPresenter;
import pt.isep.nsheets.client.application.signUp.ProfilePictureForm;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
class ProfileView extends ViewWithUiHandlers<ProfilePresenter> implements ProfilePresenter.MyView {

    @UiField
    MaterialPanel normalUser, superUser;

    @UiField
    MaterialTextBox newName, newUsername;

    @UiField
    MaterialPanel imageChooser;

    @UiField
    MaterialButton deleteAccount, saveChanges, refreshTable;

    @UiField(provided = true)
    FlexTable usersStatus;

    @UiField
    MaterialImage userImg;

    @Override
    public MaterialPanel getNormalUserPanel() {
        return normalUser;
    }

    @Override
    public MaterialPanel getSuperUserPanel() {
        return superUser;
    }

    @Override
    public MaterialTextBox getNameBox() {
        return newName;
    }

    @Override
    public MaterialImage getUserImage() {
        return userImg;
    }

    @Override
    public FlexTable getTable() {
        return usersStatus;
    }

    interface Binder extends UiBinder<Widget, ProfileView> {
    }

    @Inject
    ProfileView(Binder uiBinder) {
        usersStatus = new FlexTable();

        initWidget(uiBinder.createAndBindUi(this));

        newUsername.setText(CurrentUser.username());

        ProfilePictureForm form = new ProfilePictureForm(newUsername, imageChooser);
        form.onModuleLoad();

        saveChanges.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().onSaveChanges(newUsername.getText(), newName.getText(), form.getImagePath());
            }
        });

        deleteAccount.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getUiHandlers().onDeleteAccount();
                CurrentUser.setLoggedOut();
                MenuPresenter.getPresenter().refresh();
            }

        });

        refreshTable.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                usersStatus.removeAllRows();
                getUiHandlers().loadUsers(usersStatus);
            }
        });
    }
}
