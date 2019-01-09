package pt.isep.nsheets.client.application.NoteMenu;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class NoteModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(NotePresenter.class,NotePresenter.MyView.class,NoteView.class,NotePresenter.MyProxy.class);
    }
}
