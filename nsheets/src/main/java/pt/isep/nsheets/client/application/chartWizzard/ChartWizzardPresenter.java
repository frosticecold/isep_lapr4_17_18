/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chartWizzard;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;

/**
 *
 * @author pedro
 */
public class ChartWizzardPresenter extends Presenter<ChartWizzardPresenter.MyView,ChartWizzardPresenter.MyProxy> {

    interface MyView extends View {
    }
    
    @NameToken(NameTokens.chart)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<ChartWizzardPresenter> {
    }

    @Inject
    ChartWizzardPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
    }
    
     @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("WizzardChart", "Create a chart", "", "", this);
    }
    
    
}
