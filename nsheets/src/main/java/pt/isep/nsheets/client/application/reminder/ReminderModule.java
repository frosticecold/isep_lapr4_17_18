package pt.isep.nsheets.client.application.reminder;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author Rui Almeida<1160818>
 */
public class ReminderModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindPresenter(ReminderPresenter.class, ReminderPresenter.MyView.class, ReminderView.class, ReminderPresenter.MyProxy.class);
    }

}
