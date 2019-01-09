/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.settings;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import java.util.ArrayList;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.about.AboutPresenter;
import pt.isep.nsheets.client.application.home.HomePresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author João Magalhães<1160763>
 */
public class SettingsPresenter extends Presenter<SettingsPresenter.MyView, SettingsPresenter.MyProxy> implements SettingsUiHandlers {

    interface MyView extends View, HasUiHandlers<SettingsPresenter> {
    }

    @NameToken(NameTokens.settings)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<SettingsPresenter> {
    }
    
    @Inject
    SettingsPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        getView().setUiHandlers(this);
    }
    
    @Override
    public void onListingContacts(String username) {
        Window.alert(username);
    }
    
    @Override
    public void onListingBlockedUsers(String username) {
        Window.alert(username);
    }
    
    @Override
    public void onCreatingContact(String email) {
        Window.alert(email);
    }

    @Override
    public void onBlockingUser(String email) {
        Window.alert(email);
    }
    
    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Settings", "", "", "", this);
    }
}
