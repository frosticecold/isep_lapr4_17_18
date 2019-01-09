package pt.isep.nsheets.client.application.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.LoggedInGateKeeper;
import pt.isep.nsheets.client.event.ContentPushEvent;

import com.gwtplatform.mvp.client.HasUiHandlers;
import pt.isep.nsheets.shared.services.ReminderDTO;
import pt.isep.nsheets.shared.services.ReminderService;
import pt.isep.nsheets.shared.services.ReminderServiceAsync;

import java.util.ArrayList;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView> implements MenuUiHandlers {

	interface MyView extends View, HasUiHandlers<MenuUiHandlers> {
            void refresh();
            void checkReminders();
	}
        
        private static MenuPresenter presenter;
        
	@Inject
	MenuPresenter(EventBus eventBus, MyView view) {
		super(eventBus, view);

		getView().setUiHandlers(this);
                presenter = this;
	}

	protected void onBind() {
		super.onBind();
	}
        
        public void refresh(){
            getView().refresh();
        }
        
        public static MenuPresenter getPresenter(){
            return presenter;
        }

	@Override
	public void setContentPush() {
		ContentPushEvent.fire(this);
	}

	@Override
	public void onReveal() {
		Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
			@Override
			public boolean execute() {
				if (CurrentUser.isLogged()) {
					getView().checkReminders();
				}
				return true;
			}
		}, 10000);
	}
}