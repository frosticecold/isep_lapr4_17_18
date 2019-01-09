/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chartWizzard;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author pedro
 */
public class ChartWizzardModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ChartWizzardPresenter.class, ChartWizzardPresenter.MyView.class, ChartWizzardView.class,ChartWizzardPresenter.MyProxy.class);
    }
    
}
