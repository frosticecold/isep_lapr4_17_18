package pt.isep.nsheets.client.application.home;

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
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import java.util.ArrayList;
import javax.inject.Inject;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.blue.s2.n1090657.WorkbookManager;
import pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc.AccessType;
import pt.isep.nsheets.shared.services.WorkbookDescriptionDTO;
import pt.isep.nsheets.shared.services.WorkbooksService;
import pt.isep.nsheets.shared.services.WorkbooksServiceAsync;

class HomeView extends ViewImpl implements HomePresenter.MyView {

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton newPublicWorkbookButton;

    @UiField
    MaterialButton newPrivateWorkbookButton;

    @UiField
    MaterialIcon searchButton;

    @UiField
    MaterialWindow popUpView;

    @UiField
    MaterialTextBox nameText;

    @UiField
    MaterialTextBox descText;

    @UiField
    MaterialTextBox filterTextField;

    @UiField
    MaterialButton ShowPublicWorkbook;

    @UiField
    MaterialButton ShowPrivateWorkbook;

    @UiField
    MaterialButton importFromXML;
    @UiField
    MaterialWindow importFromXMLPopUp;
    @UiField
    MaterialButton startImportWorkbookXML;
    @UiField
    MaterialButton startImportSpreadsheetXML;
    @UiField
    MaterialButton startImportPartOfSpreadsheetXML;
    @UiField
    MaterialWindow importWorkbookFromXMLPopUp;
    @UiField
    MaterialWindow importSpreadsheetFromXMLPopUp;
    @UiField
    MaterialWindow importPartOfSpreadsheetFromXMLPopUp;
    @UiField
    MaterialTextBox workbookName;
    @UiField
    MaterialTextBox workbookDescription;
    @UiField
    MaterialButton submitButtonWorkbook;
    @UiField
    MaterialPanel filePanel;
    @UiField
    MaterialWindow shareWorkbookWindow;

    @Inject
    HomeView(Binder uiBinder) {

        initWidget(uiBinder.createAndBindUi(this));
    }

    private MaterialCard createCard(WorkbookDescriptionDTO wb) {
        MaterialCard card = new MaterialCard();
        if (wb.getWorkbook().isPublic) {
            card.setBackgroundColor(Color.BLUE_DARKEN_1);
        } else {
            card.setBackgroundColor(Color.PURPLE_DARKEN_2);
            if (wb.getListOfAccess().hasAccessType(CurrentUser.email(), AccessType.READ_ONLY)) {
                card.setBackgroundColor(Color.ORANGE_DARKEN_1);
            } else {
                if (wb.getListOfAccess().hasAccessType(CurrentUser.email(), AccessType.VIEW_ONLY)) {
                    card.setBackgroundColor(Color.GREY_DARKEN_1);
                }
            }
        }

        MaterialCardContent content = new MaterialCardContent();
        /**
         * Buttons of the card
         */
        MaterialButton btOpen = new MaterialButton();
        MaterialButton btEdit = new MaterialButton();
        MaterialButton btRemove = new MaterialButton();
        //Adding share button 15-06-2018
        MaterialButton btShare = new MaterialButton();
        MaterialWindow popUpView = new MaterialWindow();
        MaterialTextBox nameText = new MaterialTextBox();
        MaterialTextBox descText = new MaterialTextBox();
        MaterialButton check1 = new MaterialButton();
        MaterialButton check2 = new MaterialButton();

        check1.addClickHandler(event -> {

            WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class);

            // Set up the callback object.
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    MaterialToast.fireToast("Changes has been successfully saved!!!");

                }
            };
            String name = nameText.getText();
            workbooksSvc.changeWorkbookName(wb, name, callback);

        });

        check2.addClickHandler(event -> {
            WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class);

            // Set up the callback object.
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        MaterialToast.fireToast("Changes has been successfully saved!!!");
                    }
                    if (!result) {
                        MaterialToast.fireToast("There was a problem while making changes!");
                    }
                }
            };

            String description = descText.getText();
            workbooksSvc.changeWorkbookDescription(wb, description, callback);

        });

        check1.setIconType(IconType.CHECK);
        check2.setIconType(IconType.CHECK);
        MaterialLabel nomeLabel = new MaterialLabel();
        nomeLabel.setText("New name:");
        MaterialLabel descLabel = new MaterialLabel();
        descLabel.setText("New description:");

        popUpView.add(nomeLabel);
        popUpView.add(nameText);
        popUpView.add(check1);
        popUpView.add(descLabel);
        popUpView.add(descText);
        popUpView.add(check2);

        btOpen.addClickHandler(event -> {
            if (wb.getListOfAccess().hasAccessType(CurrentUser.email(), AccessType.VIEW_ONLY)) {
                MaterialToast.fireToast("Error, you cannot open this file!");
            } else {
                if (wb.getListOfAccess().hasAccessType(CurrentUser.email(), AccessType.READ_ONLY)) {
                    wb.setReadOnlyAccess();
                    MaterialToast.fireToast("Opened! You have read only permissions!");
                    WorkbookManager.getInstance().addOpenedWorkbook(wb);
                } else {
                    //It is owner or readwrite permissions
                    WorkbookManager.getInstance().addOpenedWorkbook(wb);
                    MaterialToast.fireToast("Workbook Selected master!");
                }
            }

        });
        btEdit.addClickHandler(event -> popUpView.open());
        btRemove.addClickHandler(event -> {
            WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class);

            // Set up the callback object.
            AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        MaterialToast.fireToast("Workbook was successfully removed!");
                    }
                    if (!result) {
                        MaterialToast.fireToast("There was a problem while making changes!");
                    }
                }
            };
            workbooksSvc.deleteWorkbook(wb, callback);
        });

        btOpen.setIconType(IconType.SEARCH);
        btEdit.setIconType(IconType.EDIT);
        btRemove.setIconType(IconType.REMOVE);

        content.add(btOpen);
        content.add(btEdit);
        content.add(btRemove);
        if (!wb.getWorkbook().isPublic()) {
            if (wb.getListOfAccess().hasAccessType(CurrentUser.email(), AccessType.READ_WRITE) || wb.getOwnerEmail().equalsIgnoreCase(CurrentUser.email())) {
                btShare.setIconType(IconType.SHARE);
                content.add(btShare);
                btShare.addClickHandler(event -> {
                    MaterialTextArea emailTxtArea = new MaterialTextArea("Email");
                    emailTxtArea.setPaddingLeft(20);
                    emailTxtArea.setPaddingRight(20);
                    MaterialListValueBox<AccessType> listbox = new MaterialListValueBox<>();
                    listbox.setPaddingLeft(20);
                    listbox.setPaddingRight(20);
                    MaterialButton addButton = new MaterialButton("Add share.");
                    MaterialButton saveButton = new MaterialButton("Save everything.");
                    for (AccessType at : AccessType.values()) {
                        listbox.addItem(at, at.name());
                    }
                    addButton.addClickHandler(otherevent -> {
                        String email = emailTxtArea.getText();
                        if (email.isEmpty()) {
                            MaterialToast.fireToast("Invalid email");
                        } else {
                            wb.getListOfAccess().addUserAccess(email, listbox.getSelectedValue());
                            MaterialToast.fireToast("Added share!");
                            emailTxtArea.clear();
                            listbox.setSelectedIndex(0);
                        }
                    });
                    saveButton.addClickHandler(saveEvent -> {
                        WorkbooksServiceAsync wbservice = GWT.create(WorkbooksService.class);
                        AsyncCallback<Boolean> saveCallback = new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                MaterialToast.fireToast("Failed to save share changes.");
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result == true) {
                                    MaterialToast.fireToast("Saved changes succesfully");
                                    shareWorkbookWindow.close();
                                }
                            }
                        };
                        wbservice.saveWorkbook(wb, saveCallback);
                    });
                    shareWorkbookWindow.add(emailTxtArea);
                    shareWorkbookWindow.add(listbox);
                    shareWorkbookWindow.add(addButton);
                    shareWorkbookWindow.add(saveButton);
                    shareWorkbookWindow.open();

                });
            }
        }

        card.add(content);

        MaterialCardContent cardContent = new MaterialCardContent();
        cardContent.setTextColor(Color.WHITE);
        MaterialCardContent cardDeleteContent = new MaterialCardContent();
        cardDeleteContent.setTextColor(Color.WHITE);

        MaterialCardTitle cardTitle = new MaterialCardTitle();
        cardTitle.setText(wb.getName());
        cardTitle.setIconType(IconType.INSERT_DRIVE_FILE);
        cardTitle.setIconPosition(IconPosition.RIGHT);

        MaterialLabel label = new MaterialLabel();
        label.setText(wb.getDescription());

        cardContent.add(cardTitle);
        cardContent.add(label);

        card.add(cardContent);

        return card;
    }

    @Override
    public void setContents(ArrayList<WorkbookDescriptionDTO> contents) {
        int colCount = 1;

        MaterialRow row = null;

        htmlPanel.clear();

        for (WorkbookDescriptionDTO wb : contents) {
            MaterialCard card = createCard(wb);

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

    @Override
    public void addClickHandler(ClickHandler ch) {
        // TODO Auto-generated method stub

        newPublicWorkbookButton.addClickHandler(ch);
    }

    @Override
    public void addClickHandler2(ClickHandler ch) {
        // TODO Auto-generated method stub

        newPrivateWorkbookButton.addClickHandler(ch);
    }

    /**
     * Click handler for ImportFromXML button
     *
     * @param ch Click Handler
     */
    @Override
    public void addClickHandler3(ClickHandler ch) {
        importFromXML.addClickHandler(ch);
    }

    @Override
    public MaterialButton getSubmitButtonWorkbook() {
        return this.submitButtonWorkbook;
    }

    @Override
    public void addClickHandlerImportWorkbookButton(ClickHandler ch) {
        submitButtonWorkbook.addClickHandler(ch);
    }

    @Override
    public MaterialTextBox getWorkbookNameText() {
        return workbookDescription;
    }

    @Override
    public MaterialTextBox getWorkbookDescriptionText() {
        return workbookName;
    }

    @Override
    public MaterialPanel getfilePanel() {
        return filePanel;
    }

    @Override
    public MaterialButton getNewPublicWorkbookButton() {
        return newPublicWorkbookButton;
    }

    @Override
    public MaterialTextBox getFilterTextField() {
        return filterTextField;
    }

    @Override
    public MaterialIcon getSearchButton() {
        return this.searchButton;
    }

    @Override
    public MaterialTextBox getNameText() {
        return this.nameText;
    }

    @Override
    public MaterialTextBox getDescText() {
        return this.descText;
    }

    public MaterialWindow getPopUpView() {
        return this.popUpView;
    }

    @Override
    public MaterialWindow getImportFromXMLPopUp() {
        return this.importFromXMLPopUp;
    }

    @Override
    public MaterialButton getStartImportWorkbookXML() {
        return this.startImportWorkbookXML;
    }

    @Override
    public MaterialButton getStartImportSpreadsheetXML() {
        return this.startImportSpreadsheetXML;
    }

    @Override
    public MaterialButton getStartImportPartOfSpreadsheetXML() {
        return this.startImportPartOfSpreadsheetXML;
    }

    @Override
    public MaterialWindow getImportWorkbookFromXMLPopUp() {
        return this.importWorkbookFromXMLPopUp;
    }

    @Override
    public MaterialWindow getImportSpreadsheetFromXMLPopUp() {
        return this.importSpreadsheetFromXMLPopUp;
    }

    @Override
    public MaterialWindow getImportPartOfSpreadsheetFromXMLPopUp() {
        return this.importPartOfSpreadsheetFromXMLPopUp;
    }

    @Override
    public MaterialButton getNewPrivateWorkbookButton() {
        return this.newPrivateWorkbookButton;
    }

    @Override
    public void addClickHandlerPublic(ClickHandler ch) {
        ShowPublicWorkbook.addClickHandler(ch);
    }

    @Override
    public void addClickHandlerPrivate(ClickHandler ch) {
        ShowPrivateWorkbook.addClickHandler(ch);
    }

    @Override
    public void addClickHandlerImportWorkbook(ClickHandler ch) {
        startImportWorkbookXML.addClickHandler(ch);
    }

    @Override
    public void addClickHandlerImportSpreadsheet(ClickHandler ch) {
        startImportSpreadsheetXML.addClickHandler(ch);
    }

    @Override
    public void addClickHandlerImportPartOfSpreadsheet(ClickHandler ch) {
        startImportPartOfSpreadsheetXML.addClickHandler(ch);
    }

    interface Binder extends UiBinder<Widget, HomeView> {
    }
}
