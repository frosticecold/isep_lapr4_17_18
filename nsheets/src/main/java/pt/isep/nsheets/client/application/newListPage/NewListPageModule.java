/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.newListPage;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class NewListPageModule extends AbstractPresenterModule {
    
    @Override
    protected void configure() {
        bindPresenter(NewListPagePresenter.class, NewListPagePresenter.MyView.class, NewListPageView.class, NewListPagePresenter.MyProxy.class);
    }
}
