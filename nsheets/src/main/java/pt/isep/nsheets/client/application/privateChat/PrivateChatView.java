/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.privateChat;

import pt.isep.nsheets.client.application.calendar.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161025.calendar.domain.Event;
import pt.isep.nsheets.shared.services.EventDTO;
import pt.isep.nsheets.shared.services.EventsService;
import pt.isep.nsheets.shared.services.EventsServiceAsync;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PrivateChatDTO;
import pt.isep.nsheets.shared.services.PrivateChatService;
import pt.isep.nsheets.shared.services.PrivateChatServiceAsync;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

/**
 *
 * @author MFerreira
 */
public class PrivateChatView extends ViewImpl implements PrivateChatPresenter.MyView {

    MaterialWindow window;
    HTMLPanel htmlMessages, htmlUsers;

    @UiField
    MaterialTextBox nome;

    @UiField
    HTMLPanel htmlPanel2;

    @UiField
    MaterialRow htmlPanel;

    @UiField
    MaterialButton createPrivateChat;

    interface Binder extends UiBinder<Widget, PrivateChatView> {
    }

    @Inject
    PrivateChatView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setContents(ArrayList<PrivateChatDTO> contents) {
        int colCount = 1;

        MaterialRow row = null;
        htmlPanel.clear();

        for (PrivateChatDTO pchat : contents) {
            MaterialCard card = createCard(pchat);

            if (colCount == 1) {
                row = new MaterialRow();
                htmlPanel.add(row);
                ++colCount;
                if (colCount >= 4) {
                    colCount = 1;
                }
            }

            MaterialColumn col = new MaterialColumn();
            col.setGrid("l4");
            row.add(col);

            col.add(card);
        }
    }

    private MaterialCard createCard(PrivateChatDTO pchatDTO) {
        MaterialCard card = new MaterialCard();
        card.setBackgroundColor(Color.BLUE_DARKEN_1);

        MaterialCardContent content = new MaterialCardContent();

        setCardUsers(pchatDTO);


        MaterialButton addNewUser = new MaterialButton();
        MaterialButton openChat = new MaterialButton();

        addNewUser.setBackgroundColor(Color.GREY_DARKEN_3);
        openChat.setBackgroundColor(Color.GREY_DARKEN_3);

        //VAI ADICIONAR UM NOVO USER À CONVERSA
        addNewUser.addClickHandler(event -> {

            MaterialWindow window = new MaterialWindow();
            MaterialTextBox email = new MaterialTextBox();
            email.setLabel("Insert the email");
            email.setIconType(IconType.PERSON_ADD);
            window.add(email);
            MaterialButton invite = new MaterialButton();
            invite.setText("Invite");
            invite.setMarginLeft(40.0);
            invite.setWaves(WavesType.RED);
            invite.setBackgroundColor(Color.GREY_DARKEN_3);
            window.add(invite);

            invite.addClickHandler(event2 -> {

                PrivateChatServiceAsync pchatSvc = GWT.create(PrivateChatService.class);

                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error inviting" + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result == true) {
                            MaterialToast.fireToast("Invite sent", "rounded");
                            window.close();
                        } else {
                            MaterialToast.fireToast("Error inviting", "rounded");
                        }
                    }
                };
                pchatSvc.invitePerson(email.getText(), pchatDTO.getNome(), callback);
            });
            window.open();
        });

        //VAI MANDAR UMA MENSAGEM PARA A CONVERSA
        openChat.addClickHandler(event3 -> {
            window = new MaterialWindow();
            window.setTitle("Chat Room: " + pchatDTO.getNome());
            htmlMessages = new HTMLPanel("");
            MaterialPanel panel = new MaterialPanel();
            MaterialTextBox chat = new MaterialTextBox();
            chat.setLabel("Write a message");
            chat.setIconType(IconType.COMMENT);
            window.add(chat);
            MaterialButton message = new MaterialButton();
            message.setText("Send Message");
            message.setMarginLeft(40.0);
            message.setIconType(IconType.EMAIL);
            message.setWaves(WavesType.RED);
            message.setBackgroundColor(Color.GREY_DARKEN_3);
            window.add(message);
            panel.add(htmlMessages);
            setContentsChat(pchatDTO);
            window.add(panel);
            window.open();

            Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand() {
                @Override
                public boolean execute() {
                    refreshView(pchatDTO.getNome());
                    return true;
                }
            }, 500);

            message.addClickHandler(event2 -> {

                PrivateChatServiceAsync chatSvc = GWT.create(PrivateChatService.class);

                AsyncCallback<MessageDTO> callback = new AsyncCallback<MessageDTO>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MaterialToast.fireToast("Error! " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(MessageDTO result) {
                        MaterialToast.fireToast("To send...Sent", "rounded");
                        setContentsChat(pchatDTO);
                        chat.setText("");
                    }

                };
                if (!chat.getText().isEmpty() && !chat.getText().matches("[ ]+")) {
                    MessageDTO msg = new MessageDTO(chat.getText(), CurrentUser.username());
                    chatSvc.addMessageToChat(pchatDTO.getNome(), msg, callback);
                } else {
                    MaterialToast.fireToast("Error! Please insert Message");
                }
            });

        });        

        addNewUser.setIconType(IconType.PERSON_ADD);
        openChat.setIconType(IconType.QUESTION_ANSWER);

        content.add(htmlUsers);
        content.add(addNewUser);
        content.add(openChat);

        MaterialCardContent cardContent = new MaterialCardContent();
        cardContent.setTextColor(Color.WHITE);
        MaterialCardContent cardDeleteContent = new MaterialCardContent();
        cardDeleteContent.setTextColor(Color.WHITE);

        MaterialCardTitle cardTitle = new MaterialCardTitle();
        cardTitle.setText(pchatDTO.getNome());
        cardTitle.setTextColor(Color.WHITE);
        cardTitle.setIconType(IconType.CHAT);
        cardTitle.setIconPosition(IconPosition.LEFT);

        cardContent.add(cardTitle);

        card.add(cardContent);
        card.add(content);

        return card;
    }

    @Override
    public void addClickHandler(ClickHandler ch) {
        createPrivateChat.addClickHandler(ch);
    }

    @Override
    public Object[] info() {
        Object[] info = {nome.getText()};
        return info;
    }

    private void refreshView(String pchatName) {
        PrivateChatServiceAsync pchatSvc = GWT.create(PrivateChatService.class);

        // Set up the callback object.
        AsyncCallback<PrivateChatDTO> callback = new AsyncCallback<PrivateChatDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error refreshing! " + caught.getMessage());
            }

            @Override
            public void onSuccess(PrivateChatDTO result) {
                //MaterialToast.fireToast("Refresh");
                setContentsChat(result);
            }
        };

        pchatSvc.findPrivateChatByName(pchatName, callback);
    }

    private MaterialTextBox createCardMessage(MessageDTO c) {
        MaterialTextBox box = new MaterialTextBox();

        box.setLabel(c.getDate() + " from " + c.getUser());
        box.setText(c.getMessage());

        box.setReadOnly(true);
        box.setLength(c.getMessage().length());

        return box;
    }

    public void setContentsChat(PrivateChatDTO contents) {

        htmlMessages.clear();
        MaterialRow row = null;

        for (MessageDTO c : contents.getMessage()) {

            row = new MaterialRow();
            row.add(createCardMessage(c));
            row.setMarginLeft(60.0);
            htmlMessages.add(row);
        }
    }

    private void setCardUsers(PrivateChatDTO pchatDTO) {
        htmlUsers = new HTMLPanel("<b> Participants </b>");
        MaterialRow row = null;
        for (String user : pchatDTO.getUsernames()) {
            row = new MaterialRow();
            MaterialLabel label = new MaterialLabel(user);
            row.add(label);
            htmlUsers.add(row);
        }
    }
}
