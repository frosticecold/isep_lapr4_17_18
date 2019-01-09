package pt.isep.nsheets.client.application.calendar;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 *
 * @author MFerreira
 */
public class EventCalendarModule extends AbstractPresenterModule{

    @Override
    protected void configure() {
        bindPresenter(EventCalendarPresenter.class, EventCalendarPresenter.MyView.class, EventCalendarView.class, EventCalendarPresenter.MyProxy.class);
    }
    
}
