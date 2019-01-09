/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chat;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ChatPresenter.class, ChatPresenter.MyView.class, ChatView.class, ChatPresenter.MyProxy.class);
    }

}
