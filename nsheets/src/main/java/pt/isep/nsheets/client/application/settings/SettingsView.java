/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.settings;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;
import javax.inject.Inject;

/**
 *
 * @author João Magalhães<1160763>
 */
public class SettingsView extends ViewWithUiHandlers<SettingsPresenter> implements SettingsPresenter.MyView {
    
    @UiField
    MaterialButton btnListContacts,btnListBlockedUsers,btnCreateContact,btnBlockUser;
    
    @UiField
    MaterialTextBox email1, email2, username1, username2;
    
    interface Binder extends UiBinder<Widget, SettingsView> {
    }

    @Inject
    SettingsView(Binder uiBinder) {
            initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("btnCreateContact")
    void onCreatingContact(ClickEvent clickEvent) {
       getUiHandlers().onCreatingContact(email1.getText());
    }
    
    @UiHandler("btnBlockUser")
    void onBlockingUser(ClickEvent clickEvent) {
       getUiHandlers().onBlockingUser(email2.getText());
    }
    
    @UiHandler("btnListContacts")
    void onListingContacts(ClickEvent clickEvent) {
       getUiHandlers().onListingContacts(username1.getText());
    }
    
    @UiHandler("btnListBlockedUsers")
    void onListingBlockedUsers(ClickEvent clickEvent) {
       getUiHandlers().onListingBlockedUsers(username2.getText());
    }
}
