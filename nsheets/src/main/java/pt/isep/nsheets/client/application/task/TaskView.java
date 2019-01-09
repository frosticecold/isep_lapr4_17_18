/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.popupmenu.MaterialPopupMenu;
import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.data.component.RowComponent;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialListBox;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;
import static gwt.material.design.jquery.client.api.JQuery.$;
import gwt.material.design.jquery.client.api.JQueryElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.shared.services.TaskDTO;
import pt.isep.nsheets.shared.services.TaskService;
import pt.isep.nsheets.shared.services.TaskServiceAsync;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
class TaskView extends ViewImpl implements TaskPresenter.MyView {

    interface Binder extends UiBinder<Widget, TaskView> {
    }

    private String username = CurrentUser.username();

    // UI TABLE AND COMPONENTS
    @UiField
    MaterialDataTable<TaskDTO> table;

    @UiField
    MaterialButton contactList;

    @UiField
    MaterialPopupMenu popupMenu;

    @UiField
    MaterialLink removeButton;

    @UiField
    MaterialLink editButton;

    @UiField
    MaterialListBox mailListToSend;

    @Inject
    TaskView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        // TABLE AND PANEL CONFIG
        table.setVisibleRange(0, 200);
        Panel panel = table.getScaffolding().getToolPanel();
        MaterialIcon addButton = new MaterialIcon(IconType.ADD);
        MaterialIcon refreshButton = new MaterialIcon(IconType.REFRESH);
        MaterialIcon completedTask = new MaterialIcon(IconType.CHECK);
        addButton.setWaves(WavesType.LIGHT);
        refreshButton.setWaves(WavesType.LIGHT);
        completedTask.setWaves(WavesType.LIGHT);
        addButton.setCircle(true);
        refreshButton.setCircle(true);
        completedTask.setCircle(true);
        panel.add(addButton);
        panel.add(refreshButton);
        panel.add(completedTask);

        // REFRESH TABLE PANEL BUTTON HANDLER
        refreshButton.addClickHandler(handler -> {
            refreshTableData();
        });

        // SHOW COMPLETED TASKS PANEL BUTTON HANDLER
        completedTask.addClickHandler(handler -> {
            TaskServiceAsync Svc = GWT.create(TaskService.class);
            table.clearRows(true);

            AsyncCallback<List<TaskDTO>> callback = new AsyncCallback<List<TaskDTO>>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(List<TaskDTO> result) {
                    MaterialToast.fireToast("Toogle completed tasks");
                    table.setRowData(0, result);
                }
            };
            Svc.getAllCompleteTasks(username, callback);
        });

        // ADD PANEL BUTTON HANDLER
        addButton.addClickHandler(handler -> {
            name.clear();
            description.clear();
            priority.clear();
            newWindow.open();

            MaterialDropDown drop = new MaterialDropDown();
            TaskServiceAsync Svc = GWT.create(TaskService.class);
            AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
                @Override
                public void onFailure(Throwable caught) {
                    MaterialToast.fireToast("Error! " + caught.getMessage());
                }

                @Override
                public void onSuccess(List<String> result) {

                    for (String string : result) {
                        MaterialLink link = new MaterialLink();
                        link.setText(string);
                        link.addClickHandler(handler -> {
                            mailListToSend.add(link.getValue());
                            MaterialToast.fireToast("selected " + link.getValue());
                            createTask.setEnabled(true);
                        });
                        drop.add(link);
                    }
                    contactList.add(drop);
                    MaterialToast.fireToast("Mails Loaded successfully!");
                }
            };
            Svc.getUserEmails(callback);
        });

        // POPUP MENU FOR ROW SELECTION AND EDIT/REMOVE SCENARIOS
        popupMenu.addSelectionHandler(selectionEvent -> {
            JQueryElement span = $(selectionEvent.getSelectedItem()).find("span");

            if ($(span).asElement().getInnerHTML().equalsIgnoreCase("edit")) {
                if (table.getSelectedRowModels(true).size() > 1) {
                    MaterialToast.fireToast("Please edit only one at a time");
                } else {
                    TaskDTO dto = (TaskDTO) table.getSelectedRowModels(true).get(0);
                    MaterialToast.fireToast("Editing " + dto.getName());
                    editWindow.open();
                    name.setText(dto.getName());
                    editName.setText(dto.getName());
                    editDescription.setText(dto.getDescription());
                    editPriority.setText(dto.getPriority());
                    editCompletion.setText(dto.getCompletion());
                }

            } else {
                for (TaskDTO dto : table.getSelectedRowModels(true)) {
                    MaterialToast.fireToast($(span).asElement().getInnerHTML() + " : " + dto.getName());

                    TaskServiceAsync Svc = GWT.create(TaskService.class);
                    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            MaterialToast.fireToast("Error: " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            MaterialToast.fireToast("Task removed successfuly!");
                            refreshTableData();
                        }
                    };
                    Svc.removeTask(dto, callback);
                }
            }
        });

        // MOUSE RIGHT CLICK TO OPEN POPUP MENU
        table.addRowContextMenuHandler(event -> {
            table.selectRow($(event.getRow()).asElement(), true);
            popupMenu.setSelected(event.getModel());
            popupMenu.setPopupPosition(event.getMouseEvent().getClientX(), event.getMouseEvent().getClientY());
            popupMenu.open();
        });

        createColums();
    }

    // CREATE WINDOW FIELDS AND EVENT
    @UiField
    MaterialWindow newWindow;

    @UiField
    MaterialTextBox name;

    @UiField
    MaterialTextBox description;

    @UiField
    MaterialTextBox priority;

    @UiField
    MaterialButton createTask;

    @UiHandler("createTask")
    void onCreateTask(ClickEvent e) {
        List<String> mailList = new ArrayList<>();

        for (int i = 0; i < mailListToSend.getItemCount(); i++) {
            mailList.add(mailListToSend.getItemText(i));
        }

        TaskServiceAsync Svc = GWT.create(TaskService.class);
        AsyncCallback<TaskDTO> callback = new AsyncCallback<TaskDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error: " + caught.getMessage());
            }

            @Override
            public void onSuccess(TaskDTO result) {
                MaterialToast.fireToast("Task created successfuly!");
                mailListToSend.clear();
                name.clear();
                description.clear();
                priority.clear();
                refreshTableData();
                newWindow.close();
                createTask.setEnabled(false);
            }
        };
        TaskDTO dto = new TaskDTO(name.getValue(), description.getValue(), mailList, priority.getValue());
        Svc.addNewTask(dto, callback);
    }

    // EDIT WINDOW FIELDS AND EVENT
    @UiField
    MaterialWindow editWindow;

    @UiField
    MaterialTextBox editName;

    @UiField
    MaterialTextBox editDescription;

    @UiField
    MaterialTextBox editPriority;

    @UiField
    MaterialTextBox editCompletion;

    @UiField
    MaterialButton editTask;

    @UiHandler("editTask")
    void onEditTask(ClickEvent e) {
        TaskServiceAsync Svc = GWT.create(TaskService.class);
        AsyncCallback<TaskDTO> callback = new AsyncCallback<TaskDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error: " + caught.getMessage());
            }

            @Override
            public void onSuccess(TaskDTO result) {
                MaterialToast.fireToast("Task edited successfuly!");
                name.clear();
                editName.clear();
                editDescription.clear();
                editPriority.clear();
                editCompletion.clear();
                refreshTableData();
                editWindow.close();
            }
        };
        TaskDTO dto = new TaskDTO(editName.getValue(), editDescription.getValue(), editPriority.getValue(), editCompletion.getValue());
        Svc.editTask(dto, name.getValue(), callback);
    }

    // PRIVATE METHODS
    private void refreshTableData() {
        TaskServiceAsync Svc = GWT.create(TaskService.class);
        table.clearRows(true);

        AsyncCallback<List<TaskDTO>> callback = new AsyncCallback<List<TaskDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                MaterialToast.fireToast("Error! " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<TaskDTO> result) {
                MaterialToast.fireToast("Table Refreshed");
                table.setRowData(0, result);
            }
        };
        Svc.getAllTasks(username, callback);
    }

    private void createColums() {
        table.addColumn(new WidgetColumn<TaskDTO, MaterialButton>() {
            @Override
            public Comparator<? super RowComponent<TaskDTO>> sortComparator() {
                return (o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName());
            }

            @Override
            public MaterialButton getValue(TaskDTO task) {
                MaterialCheckBox box = new MaterialCheckBox();
                box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                    @Override
                    public void onValueChange(ValueChangeEvent<Boolean> event) {
                        MaterialToast.fireToast("Clicked");
                    }
                });
                return new MaterialButton(task.getName());
            }

            @Override
            public String width() {
                return "200px";
            }
        }, "Task Name");

        table.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public Comparator<? super RowComponent<TaskDTO>> sortComparator() {
                return (o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription());
            }

            @Override
            public String getValue(TaskDTO task) {
                return task.getDescription();
            }

            @Override
            public String width() {
                return "200px";
            }
        }, "Description");

        table.addColumn(new WidgetColumn<TaskDTO, MaterialComboBox<String>>() {
            @Override
            public MaterialComboBox<String> getValue(TaskDTO task) {
                MaterialComboBox<String> mcb = new MaterialComboBox<>();
                mcb.addItems(task.getList());
                mcb.addClickHandler(event -> {
                    event.getNativeEvent().stopPropagation();
                });
                mcb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

                    @Override
                    public void onValueChange(ValueChangeEvent<List<String>> event) {
                    }
                });
                return mcb;
            }

            @Override
            public String width() {
                return "200px";
            }
        }, "Contact List");

        table.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public Comparator<? super RowComponent<TaskDTO>> sortComparator() {
                return (o1, o2) -> o1.getData().getPriority().compareToIgnoreCase(o2.getData().getPriority());
            }

            @Override
            public String getValue(TaskDTO task) {
                return task.getPriority();
            }

            @Override
            public String width() {
                return "200px";
            }
        }, "Priority");

        table.addColumn(new TextColumn<TaskDTO>() {
            @Override
            public Comparator<? super RowComponent<TaskDTO>> sortComparator() {
                return (o1, o2) -> o1.getData().getCompletion().compareToIgnoreCase(o2.getData().getCompletion());
            }

            @Override
            public String getValue(TaskDTO task) {
                return task.getCompletion() + "";
            }

            @Override
            public String width() {
                return "200px";
            }
        }, "Completion");
    }

    // OVERRIDE TASK PRESENTER METHOD
    @Override
    public void setContents(List<TaskDTO> contents) {
        table.getTableTitle().setText("Available tasks");
        MaterialToast.fireToast("Retrieving your tasks");
        refreshTableData();

    }

    // MOCK DATA METHODS
    private void mockData() {
        List<TaskDTO> people = new ArrayList<>();
        List<String> mails = new ArrayList<>();
        mails.add("mail1");
        mails.add("mail2");
        mails.add("mail3");

        for (int i = 1; i <= 10; i++) {
            people.add(new TaskDTO("name " + i, "desc " + i, mails, "2", "2"));
        }

        table.setRowData(0, people);
    }

    private List<String> mockMailList() {
        List<TaskDTO> people = new ArrayList<>();
        List<String> mails = new ArrayList<>();
        mails.add("mail1");
        mails.add("mail2");
        mails.add("mail3");

        return mails;
    }
}
