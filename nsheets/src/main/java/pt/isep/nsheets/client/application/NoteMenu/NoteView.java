package pt.isep.nsheets.client.application.NoteMenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.html.Option;
import javafx.scene.paint.Material;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.blue.s2.n1090657.WorkbookManager;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.domain.WorkbookDescription;
import pt.isep.nsheets.shared.core.Workbook;
import pt.isep.nsheets.shared.lapr4.blue.s2.core.n1090657.core.WorkbookDTO;
import pt.isep.nsheets.shared.services.NoteDTO;
import pt.isep.nsheets.shared.services.NoteService;
import pt.isep.nsheets.shared.services.NoteServiceAsync;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class NoteView extends ViewImpl implements NotePresenter.MyView {

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton exportButton;

    @UiField
    MaterialIcon searchButton;

    @UiField
    MaterialTextBox filterTextBox;

    @UiField
    MaterialDatePicker initialdp;

    @UiField
    MaterialDatePicker finaldp;

    @UiField
    MaterialListBox listBoxType;

    @UiField
    MaterialListBox rangeBegginningX;

    @UiField
    MaterialListBox rangeBegginningY;

    @UiField
    MaterialListBox spreadsheetList;

    @UiField
    MaterialButton export4spreadsheet;

    @UiField
    MaterialWindow exportPopUp;

    private List<NoteDTO> auxNoteList;

    @Override
    public MaterialIcon getSearchButton() {
        return this.searchButton;
    }

    @Override
    public MaterialTextBox getFilterTextBox() {
        return this.filterTextBox;
    }

    @Override
    public MaterialDatePicker getInitialDate() {
        return this.initialdp;
    }

    @Override
    public MaterialDatePicker getFinalDate() {
        return this.finaldp;
    }

    @Override
    public MaterialListBox getListBoxType() {
        return this.listBoxType;
    }

    interface Binder extends UiBinder<Widget, NoteView> {
    }

    @Inject
    NoteView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        Workbook currentWorkbook = WorkbookManager.getInstance().getCurrentActiveWorkbook();

        spreadsheetList.add(currentWorkbook.getSpreadsheet(0).getTitle());

        int numCol = currentWorkbook.getSpreadSheets().get(0).getColumnCount();
        int numRow = currentWorkbook.getSpreadSheets().get(0).getRowCount();

        for (int i = 0; i < numCol; i++) {
            Option optCol = new Option(String.valueOf(i + 1));
            rangeBegginningX.add(optCol);
        }

        for (int i = 0; i < numRow; i++) {
            Option optRow = new Option(String.valueOf(i + 1));
            rangeBegginningY.add(optRow);
        }

        exportButton.addClickHandler(event -> {
            NoteServiceAsync notesSvc = GWT.create(NoteService.class);

            // Set up the callback object.
            AsyncCallback<WorkbookDTO> callback = new AsyncCallback<WorkbookDTO>() {
                @Override
                public void onFailure(Throwable throwable) {
                    Logger logger4 = Logger.getLogger("logger");
                    logger4.log(Level.SEVERE, "morreu");
                    MaterialToast.fireToast("Something went wrong! " + throwable.getMessage());
                }

                @Override
                public void onSuccess(WorkbookDTO dto) {
                    MaterialToast.fireToast("Export was successful");
                }
            };
            String choosenSpread = spreadsheetList.getSelectedValue();
            int rangeRow         = Integer.parseInt(rangeBegginningX.getSelectedValue());
            int rangeCol         = Integer.parseInt(rangeBegginningY.getSelectedValue());

            notesSvc.exportNotesToSpreadSheet(currentWorkbook.toDTO(), choosenSpread, auxNoteList
                    , rangeRow
                    , rangeCol
                    , callback);
        });

        export4spreadsheet.addClickHandler(event -> {
            exportPopUp.open();

        });
    }

    private MaterialCard createCard(NoteDTO note) {
        MaterialCard card = new MaterialCard();

        MaterialCardContent content = new MaterialCardContent();
        MaterialCardContent content2 = new MaterialCardContent();
        MaterialButton btOpen = new MaterialButton();

        MaterialWindow popUp = new MaterialWindow();
        popUp.setSize("200px","200px");

        popUp.setTitle(note.getTitle());

        btOpen.setIconType(IconType.SEARCH);
        btOpen.setIconColor(Color.WHITE);


        if (note.getNoteType().equalsIgnoreCase("text")) {
            btOpen.setBackgroundColor(Color.BLUE_DARKEN_1);
            card.setBackgroundColor(Color.BLUE);
        } else {
            card.setBackgroundColor(Color.AMBER);
            btOpen.setBackgroundColor(Color.AMBER_DARKEN_2);
        }

        btOpen.addClickHandler(event -> {
            NoteServiceAsync noteSvc = GWT.create(NoteService.class);

            AsyncCallback<NoteDTO> callback = new AsyncCallback<NoteDTO>() {
                @Override
                public void onFailure(Throwable throwable) {
                    MaterialToast.fireToast("Something went wrong! " + throwable.getMessage());
                }

                @Override
                public void onSuccess(NoteDTO noteDTO) {
                    if (noteDTO != null) {
                        MaterialLabel label2 = new MaterialLabel("Description: ");
                        label2.setTextAlign(TextAlign.CENTER);
                        MaterialLabel description = new MaterialLabel(noteDTO.getDescription());
                        description.setTextAlign(TextAlign.CENTER);
                        label2.setTextAlign(TextAlign.CENTER);
                        description.setTextAlign(TextAlign.CENTER);
                        popUp.add(label2);
                        popUp.add(description);
                        popUp.open();
                    } else MaterialToast.fireToast("There was a problem opening the note");
                }
            };
            noteSvc.openNote(CurrentUser.username(), note, callback);
        });


        MaterialLabel noteName = new MaterialLabel();
        noteName.setText(note.getTitle());
        noteName.setTextColor(Color.WHITE);
        MaterialLabel tipo = new MaterialLabel();
        tipo.setText("Created: " + note.getTimestamp().toString());
        tipo.setTextColor(Color.WHITE);

        content2.add(btOpen);
        content.add(noteName);
        content.add(tipo);

        card.add(content2);
        card.add(content);

        return card;
    }

    @Override
    public void setContents(List<NoteDTO> contents) {
        int colCount = 1;

        auxNoteList = new ArrayList<>();

        MaterialRow row = null;

        htmlPanel.clear();

        for (NoteDTO note : contents) {
            MaterialCard card = createCard(note);

            if (colCount == 1) {
                row = new MaterialRow();
                htmlPanel.add(row);
                ++colCount;
                if (colCount >= 4) colCount = 1;
            }

            MaterialColumn col = new MaterialColumn();
            col.setGrid("14");
            row.add(col);
            col.add(card);

            auxNoteList.add(note);
        }

    }

    @Override
    public void addClickHandlerRefresh(ClickHandler ch) {
        searchButton.addClickHandler(ch);
    }

}
