/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.chat;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;
import javax.inject.Inject;
import pt.isep.nsheets.shared.services.MessageDTO;
import pt.isep.nsheets.shared.services.PublicChatDTO;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class ChatView extends ViewImpl implements ChatPresenter.MyView {

    @Override
    public void addClickHandler(ClickHandler ch) {
        btnChat.addClickHandler(ch);
    }

    @Override
    public void addImageHandler(ClickHandler img) {
        btnImage.addClickHandler(img);
    }

    @Override
    public String getMessage() {
        return messageText.getText();
    }

    @Override
    public MaterialPanel getImageChooser() {
        return imageChooser;
    }

    @Override
    public void getStringEmpty() {
        messageText.setText("");
    }

    interface Binder extends UiBinder<Widget, ChatView> {
    }

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialRow htmlPanel2;

    @UiField
    MaterialButton btnChat;

    @UiField
    MaterialButton btnImage;

    @UiField
    MaterialPanel imageChooser;

    @UiField
    MaterialTextBox messageText;

    @Inject
    ChatView(ChatView.Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    private MaterialTextBox createCard(MessageDTO c) {
        MaterialTextBox box = new MaterialTextBox();

        box.setLabel(c.getDate() + " from " + c.getUser());
        box.setText(c.getMessage());

        box.setReadOnly(true);
        box.setLength(c.getMessage().length());

        return box;
    }
    
    private MaterialLabel createImageCard(MessageDTO url){
        MaterialLabel label = new MaterialLabel();
        MaterialImage image = new MaterialImage();
        MaterialLabel infoLabel = new MaterialLabel();
        
        infoLabel.setText(url.getDate() + " from " + url.getUser());
        image.setUrl(url.getMessage());
        
        label.add(infoLabel);
        label.add(image);
        
        return label;
    }

    public void setContents(PublicChatDTO contents) {

        htmlPanel2.clear();
        MaterialRow row = null;

        for (MessageDTO c : contents.getMessage()) {
            row = new MaterialRow();

            if (c.getMessage().startsWith("http:/")) {
                row.add(createImageCard(c));
            } else {
                row.add(createCard(c));
            }
                htmlPanel2.add(row);
        }
    }
}
