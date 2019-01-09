/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.newListPage;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.ContentPushEvent;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class NewListPagePresenter extends Presenter<NewListPagePresenter.MyView, NewListPagePresenter.MyProxy> implements NewListPageUIHandlers{

     private MyView view;

    interface MyView extends View, HasUiHandlers<NewListPageUIHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.newListPage)
    interface MyProxy extends ProxyPlace<NewListPagePresenter> {
    }

    @Inject
    NewListPagePresenter(EventBus eventBus, NewListPagePresenter.MyView view, NewListPagePresenter.MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        this.view = view;

        getView().setUiHandlers(this);

    }

    protected void onBind() {
        super.onBind();
    }

    @Override
    public void setContentPush() {
        ContentPushEvent.fire(this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Notes", "Add a new note.", "", "", this);
    }
}
