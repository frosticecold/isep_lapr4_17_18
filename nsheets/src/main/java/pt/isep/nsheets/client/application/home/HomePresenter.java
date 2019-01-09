package pt.isep.nsheets.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.ui.*;
import java.util.ArrayList;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.application.blue.s2.n1090657.WorkbookManager;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.*;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

    private MyView view;
//    private final FileUploaderForm form;

    private void refreshViewPublicWorkbooks() {
        WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
        );

        // Set up the callback object.
        AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("HUELELELELE: " + caught.getMessage());
            }

            public void onSuccess(ArrayList<WorkbookDescriptionDTO> result) {

                view.getSearchButton().addClickHandler(event -> {

                    WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
                    );
                    // Set up the callback object.
                    AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error: " + caught.getMessage());
                        }

                        public void onSuccess(ArrayList<WorkbookDescriptionDTO> filtered) {
                            view.setContents(filtered);
                        }
                    };
                    String filter = view.getFilterTextField().getText();
                    workbooksSvc.listFilteredWorkbooksDescription(result, filter, callback);
                });
                view.setContents(result);
            }
        };
        workbooksSvc.getPublicWorkbooks(CurrentUser.username(), callback);

    }

    @Inject
    HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {

        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

        this.view = view;

//        form = new FileUploaderForm(this.view.getfilePanel());
//        form.onModuleLoad();

        this.view.addClickHandlerImportWorkbook(event -> {
            this.view.getImportFromXMLPopUp().close();
            this.view.getImportWorkbookFromXMLPopUp().open();
        });

        this.view.addClickHandlerImportSpreadsheet(event -> {
            this.view.getImportFromXMLPopUp().close();
            this.view.getImportSpreadsheetFromXMLPopUp().open();
        });

        this.view.addClickHandlerImportPartOfSpreadsheet(event -> {
            this.view.getImportFromXMLPopUp().close();
            this.view.getImportPartOfSpreadsheetFromXMLPopUp().open();
        });

        //Public workbook
        this.view.addClickHandler(event -> {
            WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class);

            // Set up the callback object.
            AsyncCallback<UserDTO> callback = new AsyncCallback<UserDTO>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(UserDTO result) {
                    Window.alert("--->" + result.getList().size());
                    MaterialToast.fireToast("New  Private Workbook Created...", "rounded");
                    refreshView();
                }
            };

            String name = Window.prompt("Name:", "Untitled");
            String description = Window.prompt("Description:", "No Description");

            WorkbookDescriptionDTO wdDto = new WorkbookDescriptionDTO(name, description,CurrentUser.email());

            WorkbookManager.getInstance().addOpenedWorkbook(wdDto);

            workbooksSvc.addWorkbookDescription(wdDto, true, CurrentUser.username(), callback);

        });


        //privateWorkbook
        this.view.addClickHandler2(event
                -> {

            WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class);

            // Set up the callback object.
            AsyncCallback<UserDTO> callback = new AsyncCallback<UserDTO>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(UserDTO result) {
                    Window.alert("--->" + result.getList().size());
                    MaterialToast.fireToast("New  Private Workbook Created...", "rounded");
                    refreshView();
                }
            };

            String name = Window.prompt("Name:", "Untitled");
            String description = Window.prompt("Description:", "No Description");

            WorkbookDescriptionDTO wdDto = new WorkbookDescriptionDTO(name,
                    description,CurrentUser.email());
            WorkbookManager.getInstance().addOpenedWorkbook(wdDto);

            workbooksSvc.addWorkbookDescription(wdDto, false, CurrentUser.username(), callback);

        });

        this.view.addClickHandler3(event -> {
            this.view.getImportFromXMLPopUp().open();
        });

        this.view.addClickHandlerPublic(event -> {
            refreshViewPublicWorkbooks();
        });

        this.view.addClickHandlerPrivate(event -> {
            refreshViewPrivateWorkbooks();
        });

        this.view.addClickHandlerImportWorkbookButton(event -> {
            ImportXMLServiceAsync serviceAsync = GWT.create(ImportXMLService.class);

            String path = inFile();

            AsyncCallback<WorkbookDescriptionDTO> callback = new AsyncCallback<WorkbookDescriptionDTO>() {
                @Override
                public void onFailure(Throwable throwable) {
                    MaterialToast.fireToast("Failed to import workbook: " + throwable.getMessage());
                }

                @Override
                public void onSuccess(WorkbookDescriptionDTO workbookDescription) {
                    Window.confirm("Success!");
                }
            };

            String name = this.view.getWorkbookNameText().getText();
            String desc = this.view.getWorkbookDescriptionText().getText();


            serviceAsync.importWorkbookToXML(path, name, desc, CurrentUser.username(), callback);
        });
    }

    private String inFile() {
        FileUpload upload;
        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction(GWT.getModuleBaseURL() + "uploadService");

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        // Create a panel to hold all of the form widgets.
        HorizontalPanel panel = new HorizontalPanel();
        form.setWidget(panel);

        // Create a FileUpload widget.
        upload = new FileUpload();
        upload.setName("uploadFormElement");
        panel.add(upload);

        // Add a 'submit' button.
        MaterialButton button = new MaterialButton("Submit");
        panel.add(button);
        button.addClickHandler(event -> form.submit());

        // Add an event handler to the form.
        form.addSubmitHandler(event -> {
            // This event is fired just before the form is submitted. We can take
            // this opportunity to perform validation.

        });
        form.addSubmitCompleteHandler(event -> {
            MaterialToast.fireToast("Done!"); // -> Do a toast saying it was successfully uploaded
        });

        upload.click();
        return GWT.getHostPageBaseURL() + upload.getFilename().substring(upload.getFilename().lastIndexOf('\\') + 1);
    }

    private void refreshView() {
        WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
        );

        // Set up the callback object.
        AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error: " + caught.getMessage());
            }

            public void onSuccess(ArrayList<WorkbookDescriptionDTO> result) {

                view.getSearchButton().addClickHandler(event -> {

                    WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
                    );
                    // Set up the callback object.
                    AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error: " + caught.getMessage());
                        }

                        public void onSuccess(ArrayList<WorkbookDescriptionDTO> filtered) {
                            view.setContents(filtered);
                        }
                    };
                    String filter = view.getFilterTextField().getText();
                    workbooksSvc.listFilteredWorkbooksDescription(result, filter, callback);
                });
                view.setContents(result);
            }
        };
        workbooksSvc.getUsersWorkbooks(CurrentUser.username(), callback);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        SetPageTitleEvent.fire("Home", "The most recent Workbooks", "", "", this);

        refreshView();
    }

    @NameToken(NameTokens.home)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<HomePresenter> {

    }

    private void refreshViewPrivateWorkbooks() {
        WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
        );

        // Set up the callback object.
        AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error: " + caught.getMessage());
            }

            public void onSuccess(ArrayList<WorkbookDescriptionDTO> result) {

                view.getSearchButton().addClickHandler(event -> {

                    WorkbooksServiceAsync workbooksSvc = GWT.create(WorkbooksService.class
                    );
                    // Set up the callback object.
                    AsyncCallback<ArrayList<WorkbookDescriptionDTO>> callback = new AsyncCallback<ArrayList<WorkbookDescriptionDTO>>() {
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error: " + caught.getMessage());
                        }

                        public void onSuccess(ArrayList<WorkbookDescriptionDTO> filtered) {
                            view.setContents(filtered);
                        }
                    };
                    String filter = view.getFilterTextField().getText();
                    workbooksSvc.listFilteredWorkbooksDescription(result, filter, callback);
                });
                view.setContents(result);
            }
        };
        workbooksSvc.getPrivateWorkbooks(CurrentUser.username(), callback);
    }

    interface MyView extends View {

        void setContents(ArrayList<WorkbookDescriptionDTO> contents);

        void addClickHandler(ClickHandler ch);

        void addClickHandler2(ClickHandler ch);

        void addClickHandlerPublic(ClickHandler ch);

        void addClickHandlerPrivate(ClickHandler ch);

        MaterialButton getNewPublicWorkbookButton();

        MaterialButton getNewPrivateWorkbookButton();

        MaterialWindow getImportFromXMLPopUp();

        MaterialButton getStartImportWorkbookXML();

        MaterialButton getStartImportSpreadsheetXML();

        MaterialButton getStartImportPartOfSpreadsheetXML();

        MaterialWindow getImportWorkbookFromXMLPopUp();

        MaterialWindow getImportSpreadsheetFromXMLPopUp();

        MaterialWindow getImportPartOfSpreadsheetFromXMLPopUp();

        MaterialTextBox getFilterTextField();

        MaterialIcon getSearchButton();

        MaterialTextBox getDescText();

        MaterialTextBox getNameText();

        MaterialWindow getPopUpView();

        void addClickHandlerImportWorkbook(ClickHandler ch);

        void addClickHandlerImportSpreadsheet(ClickHandler ch);

        void addClickHandlerImportPartOfSpreadsheet(ClickHandler ch);

        void addClickHandler3(ClickHandler ch);

        MaterialButton getSubmitButtonWorkbook();

        void addClickHandlerImportWorkbookButton(ClickHandler ch);

        MaterialTextBox getWorkbookNameText();

        MaterialTextBox getWorkbookDescriptionText();

        MaterialPanel getfilePanel();
    }

}
