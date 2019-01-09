/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.newNotePage;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author Rúben Santos<1161391@isep.ipp.pt>
 */
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class NewNotePageModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(NewNotePagePresenter.class, NewNotePagePresenter.MyView.class, NewNotePageView.class, NewNotePagePresenter.MyProxy.class);
    }
}
