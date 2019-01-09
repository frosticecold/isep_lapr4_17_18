package pt.isep.nsheets.client.application.privateChat;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author MFerreira
 */
public class PrivateChatModule extends AbstractPresenterModule{

    @Override
    protected void configure() {
        bindPresenter(PrivateChatPresenter.class, PrivateChatPresenter.MyView.class, PrivateChatView.class, PrivateChatPresenter.MyProxy.class);
    }
    
}
