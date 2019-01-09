/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.EventDTO;
import pt.isep.nsheets.shared.services.EventsService;
import pt.isep.nsheets.shared.services.EventsServiceAsync;

/**
 *
 * @author MFerreira
 */
public class EventCalendarPresenter extends Presenter<EventCalendarPresenter.MyView, EventCalendarPresenter.MyProxy> {

    private MyView view;

    interface MyView extends View {

        void setContents(ArrayList<EventDTO> contents);

        void addClickHandler(ClickHandler ch);

        void getStringEmpty();

    }

    @NameToken(NameTokens.calendar)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<EventCalendarPresenter> {
    }

    @Inject
    EventCalendarPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

        this.view = view;

        this.view.addClickHandler(event -> {

            EventsServiceAsync eventSvc = GWT.create(EventsService.class);

            // Set up the callback object.
            AsyncCallback<EventDTO> callback = new AsyncCallback<EventDTO>() {
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error aqui mano ?! " + caught.getMessage());
                }

                public void onSuccess(EventDTO result) {

                    MaterialToast.fireToast("New Event Created...", "rounded");
                    refreshView();
                    view.getStringEmpty();

                }

            };

//           
        });
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Calendar", "A list of events", "", "", this);

        refreshView();
    }

    private void refreshView() {
        EventsServiceAsync eventSvc = GWT.create(EventsService.class);

        // Set up the callback object.
        AsyncCallback<ArrayList<EventDTO>> callback = new AsyncCallback<ArrayList<EventDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error refreshing! " + caught.getMessage());
            }

            public void onSuccess(ArrayList<EventDTO> result) {
                MaterialToast.fireToast("Refresh");
                view.setContents(result);
            }
        };

        //eventSvc.getEvents(callback);
    }

}
