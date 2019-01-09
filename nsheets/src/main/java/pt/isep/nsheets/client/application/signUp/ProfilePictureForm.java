package pt.isep.nsheets.client.application.signUp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

import java.io.Serializable;

public class ProfilePictureForm implements EntryPoint, Serializable {

    public MaterialTextBox user;
    public MaterialPanel panelLogin;
    private FileUpload upload;

    public ProfilePictureForm(MaterialTextBox user, MaterialPanel panelLogin) {
        super();
        this.user = user;
        this.panelLogin = panelLogin;
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

        // Create a FileUpload widget.
        upload = new FileUpload();
        upload.setName("uploadFormElement");
        panel.add(upload);

        // Add a 'submit' button.
        MaterialButton button = new MaterialButton("Submit");
        panel.add(button);
        button.addClickHandler((ClickHandler) event -> {

            if (user.getText().isEmpty()) {
                MaterialToast.fireToast("Write down a username first");
                return;
            }
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

        panelLogin.add(form);
    }

    public String getImagePath() {
        return GWT.getHostPageBaseURL() + "user/imgs" + "?name=" + this.upload.getFilename().substring(this.upload.getFilename().lastIndexOf('\\') + 1);
    }

}
