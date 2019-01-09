package pt.isep.nsheets.client.application.lapr4.green.s3.core.n1161294;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;
import pt.isep.nsheets.client.application.Lapr.Red.n1161018.ext.UIController;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294.ImageExtension;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetImageForm implements EntryPoint, Serializable {


    private FileUpload upload;
    public MaterialPanel material_panel;
    private UIController uiController;
    private Cell activeCell;

    public SetImageForm(MaterialPanel material_panel, UIController uiController, Cell activeCell){
        super();
        this.material_panel = material_panel;
        this.uiController = uiController;
        this.activeCell = activeCell;
    }

    public void onModuleLoad() {
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

        upload = new FileUpload();
        upload.setName("uploadFormElement");
        panel.add(upload);

        // Add a 'submit' button.
        MaterialButton button = new MaterialButton("Submit");
        panel.add(button);
        button.addClickHandler((ClickHandler) event -> {
            uiController.addImageExtension(new ImageExtension(activeCell, getImagePath()));
            form.submit();
        });

        // Add an event handler to the form.
        form.addSubmitHandler(event -> {
            // This event is fired just before the form is submitted. We can take
            // this opportunity to perform validation.

        });
        form.addSubmitCompleteHandler(event -> {
            // When the form submission is successfully completed, this event is
            // fired. Assuming the service returned a response of type text/html,
            // we can get the result text here (see the FormPanel documentation for
            // further explanation).
            MaterialToast.fireToast("Done!"); // -> Do a toast saying it was successfully uploaded
        });

        material_panel.add(form);
    }

    public String getImagePath() {
        return GWT.getHostPageBaseURL() + "user/imgs" + "?name=" +
                this.upload.getFilename().substring(this.upload.getFilename().lastIndexOf('\\') + 1);
    }
}
