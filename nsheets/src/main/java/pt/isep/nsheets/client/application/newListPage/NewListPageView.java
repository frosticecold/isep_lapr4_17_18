/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.newListPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import java.util.Date;
import java.util.LinkedList;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.shared.services.NoteDTO;
import pt.isep.nsheets.shared.services.NoteService;
import pt.isep.nsheets.shared.services.NoteServiceAsync;


/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class NewListPageView extends ViewWithUiHandlers<NewListPageUIHandlers> implements NewListPagePresenter.MyView {
    
    interface Binder extends UiBinder<Widget, NewListPageView> {
    }

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton btnNewList;
    
    @UiField
    MaterialTextBox title;

    @UiField
    MaterialTextArea description;

    @Inject
    NewListPageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        title.addValueChangeHandler(handler -> {
            if (description.getValue().length() > 3 && title.getValue().length() <= title.getLength() && title.getValue().length() > 3) {
                btnNewList.setEnabled(true);
            } else {
                btnNewList.setEnabled(false);
            }
        });

        description.addValueChangeHandler(handler -> {
            if (description.getValue().length() > 3 && title.getValue().length() <= title.getLength() && title.getValue().length() > 3) {
                btnNewList.setEnabled(true);
            } else {
                btnNewList.setEnabled(false);
            }
        });
    }

    @UiHandler("btnNewList")
    void onBtnNewNote(ClickEvent e) {
        NoteServiceAsync nsa = GWT.create(NoteService.class);
        AsyncCallback<NoteDTO> callback = new AsyncCallback<NoteDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error: " + caught.getMessage());
            }

            @Override
            public void onSuccess(NoteDTO result) {
                MaterialToast.fireToast("Inserted a new note successfully!");
            }

        };
        nsa.createNote(createDTO(), callback);
    }
    
    private NoteDTO createDTO(){
        String a = title.getValue(); 
        String b = description.getValue();
        Date c = new Date(); 
        String d = CurrentUser.username();
        LinkedList<NoteDTO> list = new LinkedList<>();
        return new NoteDTO(a, b, c, d, "LIST", list);
    }
}
