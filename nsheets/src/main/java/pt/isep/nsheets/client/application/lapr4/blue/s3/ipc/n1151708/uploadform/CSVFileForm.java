/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.client.application.lapr4.blue.s3.ipc.n1151708.uploadform;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;
import java.io.Serializable;

/**
 *
 * @author MarioDias
 */
public class CSVFileForm implements EntryPoint, Serializable{
     
    private FileUpload upload;
    private MaterialPanel panel;

    public CSVFileForm(MaterialPanel panel) {
        super();
        this.panel = panel;
    }
    
    @Override
    public void onModuleLoad() {
        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction(GWT.getModuleBaseURL() + "uploadService");

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        // Create a panel to hold all of the form widgets.
        HorizontalPanel newpanel = new HorizontalPanel();
        form.setWidget(newpanel);

        // Create a FileUpload widget.
        upload = new FileUpload();
        upload.setName("uploadFormElement");
        MaterialButton button = new MaterialButton();
        button.setBackgroundColor(Color.BLUE);
        button.setIconType(IconType.CLOUD_UPLOAD);
        button.setType(ButtonType.FLOATING);
        newpanel.add(button);
        newpanel.add(upload);

        // Add a 'submit' button.
        button.addClickHandler((ClickHandler) event -> {
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

        panel.setMargin(15);
        panel.add(form);
    }

    public String getImagePath() {
        return GWT.getHostPageBaseURL() + "user/export" + "?name=" + this.upload.getFilename().substring(this.upload.getFilename().lastIndexOf('\\') + 1);
    }
    
}
