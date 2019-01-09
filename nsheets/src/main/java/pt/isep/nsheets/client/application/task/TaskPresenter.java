/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialToast;
import java.util.List;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.LoggedInGateKeeper;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.TaskDTO;
import pt.isep.nsheets.shared.services.TaskService;
import pt.isep.nsheets.shared.services.TaskServiceAsync;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskPresenter extends Presenter<TaskPresenter.MyView, TaskPresenter.MyProxy> {

    private MyView view;
    private String username = CurrentUser.username();

    interface MyView extends View {

        void setContents(List<TaskDTO> contents);

    }

    @NameToken(NameTokens.task)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<TaskPresenter> {
    }

    @Inject
    TaskPresenter(EventBus eventBus, MyView view, MyProxy proxy, CurrentUser user, LoggedInGateKeeper keeper) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
        this.view = view;
    }

    private void refreshView() {
        TaskServiceAsync Svc = GWT.create(TaskService.class);
        MaterialToast.fireToast("Hello " + username);
        AsyncCallback<List<TaskDTO>> callback = new AsyncCallback<List<TaskDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<TaskDTO> result) {
                view.setContents(result);
            }
        };
        Svc.getAllTasks(username, callback);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        SetPageTitleEvent.fire("Tasks", "Issued tasks", "", "", this);
        refreshView();
    }
}
