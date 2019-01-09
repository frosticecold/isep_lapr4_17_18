/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;
import javax.inject.Inject;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.server.services.ChatServiceImpl;
import pt.isep.nsheets.shared.services.ChatService;
import pt.isep.nsheets.shared.services.ChatServiceAsync;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PublicChatDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy> {

    private MyView view;

    interface MyView extends View {

        void setContents(PublicChatDTO contents);

        void addClickHandler(ClickHandler ch);

        void addImageHandler(ClickHandler img);

        MaterialPanel getImageChooser();

        String getMessage();

        void getStringEmpty();

    }

    @NameToken(NameTokens.chat)
    @ProxyStandard

    interface MyProxy extends ProxyPlace<ChatPresenter> {
    }

    @Inject
    ChatPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

        this.view = view;

        ImageForm form = new ImageForm(view.getImageChooser());
        form.onModuleLoad();

        this.view.addClickHandler((ClickEvent event) -> {
            ChatServiceAsync chatSvc = GWT.create(ChatService.class);

            AsyncCallback<MessageDTO> callback = new AsyncCallback<MessageDTO>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(MessageDTO result) {
                    MaterialToast.fireToast("To send...Sent", "rounded");
                    refreshView();
                    view.getStringEmpty();

                }

            };

            //Notify mentioned user
            String userReference[] = this.view.getMessage().split(" ");

            for (String string : userReference) {
                if (string.startsWith("@")) {
                    String reference[] = string.split(" ");
                    String username = reference[0].substring(1);
                    ChatServiceAsync chatSvcEmail = GWT.create(ChatService.class);
                    AsyncCallback<Boolean> callbackEmail = new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error! " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            MaterialToast.fireToast("Email sent!", "rounded");
                        }
                    };
                    
                    chatSvcEmail.sendEmail(username, callbackEmail);
                }
            }

            //Send message
            if (!view.getMessage().isEmpty() && !view.getMessage().matches("[ ]+")) {
                MessageDTO msg = new MessageDTO(this.view.getMessage(), CurrentUser.username());
                chatSvc.addMessageToChat(msg, callback);
            } else {
                MaterialToast.fireToast("Please insert Message");
            }
        });

        this.view.addImageHandler((ClickEvent event) -> {
            ChatServiceAsync chatSvc = GWT.create(ChatService.class);

            AsyncCallback<MessageDTO> callback = new AsyncCallback<MessageDTO>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(MessageDTO result) {
                    MaterialToast.fireToast("Sending image...", "rounded");
                    MaterialToast.fireToast("Sent!", "rounded");
                    refreshView();
                    view.getStringEmpty();
                }
            };

            String validation[] = form.getImagePath().split("name=");

            if (!validation[1].isEmpty()) {
                MessageDTO msg = new MessageDTO(form.getImagePath(), CurrentUser.username());
                chatSvc.addMessageToChat(msg, callback);
            } else {
                MaterialToast.fireToast("Please choose an image");
            }
        });
    }

    private void refreshView() {
        ChatServiceAsync chatSvc = GWT.create(ChatService.class);

        AsyncCallback<PublicChatDTO> callback = new AsyncCallback<PublicChatDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(PublicChatDTO result) {
                view.setContents(result);
            }
        };
        chatSvc.getChats(callback);
    }

    private void checkChat() {
        ChatServiceAsync svc = GWT.create(ChatService.class);
        AsyncCallback<Boolean> callback1 = new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    MaterialToast.fireToast("New chat will be created", "rounded");
                }
            }
        };
        svc.createChat(callback1);

    }

    protected void onReveal() {
        super.onReveal();
        checkChat();
        SetPageTitleEvent.fire("Public Chat", "New way to chat", "", "", this);
        refreshView();
        Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
            @Override
            public boolean execute() {
                refreshView();
                return true;
            }
        }, 500);
    }

}
