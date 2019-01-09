/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.privateChat;

import pt.isep.nsheets.client.application.calendar.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import java.util.Date;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.LoggedInGateKeeper;
import pt.isep.nsheets.client.application.about.AboutPresenter;
import pt.isep.nsheets.client.application.home.HomePresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.shared.services.EventDTO;
import pt.isep.nsheets.shared.services.EventsService;
import pt.isep.nsheets.shared.services.EventsServiceAsync;
import pt.isep.nsheets.shared.services.PrivateChatDTO;
import pt.isep.nsheets.shared.services.PrivateChatService;
import pt.isep.nsheets.shared.services.PrivateChatServiceAsync;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author MFerreira
 */
public class PrivateChatPresenter extends Presenter<PrivateChatPresenter.MyView, PrivateChatPresenter.MyProxy> {

    private MyView view;

    interface MyView extends View {

        void setContents(ArrayList<PrivateChatDTO> contents);

        void addClickHandler(ClickHandler ch);

        Object[] info();
    }

    @NameToken(NameTokens.privatechat)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<PrivateChatPresenter> {
    }

    @Inject
    PrivateChatPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

        this.view = view;

        this.view.addClickHandler(event -> {

            PrivateChatServiceAsync pchatSvc = GWT.create(PrivateChatService.class);

            // Set up the callback object.
            AsyncCallback<PrivateChatDTO> callback = new AsyncCallback<PrivateChatDTO>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error aqui mano ?! " + caught.getMessage());
                }

                public void onSuccess(PrivateChatDTO result) {

                    if (result != null) {
                        MaterialToast.fireToast("New Chat Created...", "rounded");

                        refreshView();
                    } else {
                        MaterialToast.fireToast("Already exists a chat with that name.", "rounded");
                    }
                }

            };
            Object[] info = this.view.info();
            String username = CurrentUser.username();
            ArrayList<String> users = new ArrayList<>();
            users.add(username);
            
            PrivateChatDTO pcDto = new PrivateChatDTO((String) info[0], users);
            pchatSvc.createPrivateChat(pcDto, callback);
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Private Chat", "Chat privately with your friends", "", "", this);
        refreshView();
        
        Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
            @Override
            public boolean execute() {
                refreshView();
                return true;
            }
        }, 500);
    }

    private void refreshView() {
        PrivateChatServiceAsync pchatSvc = GWT.create(PrivateChatService.class);

        AsyncCallback<ArrayList<PrivateChatDTO>> callback = new AsyncCallback<ArrayList<PrivateChatDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error refreshing! " + caught.getMessage());
            }

            public void onSuccess(ArrayList<PrivateChatDTO> result) {
                //MaterialToast.fireToast("Refresh");
                view.setContents(result);
            }
        };

        pchatSvc.getPrivateChat(CurrentUser.username(), callback);
    }

}
