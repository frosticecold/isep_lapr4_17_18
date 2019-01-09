package pt.isep.nsheets.client.application.signUp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

class SignUpView extends ViewWithUiHandlers<SignUpPresenter> implements SignUpPresenter.MyView {

    @UiField
    MaterialTextBox email, name, pass, user;

    @UiField
    HTMLPanel htmlPanel;

    @UiField
    MaterialButton btnCreateUser;
    
    @UiField
    MaterialPanel imageChooser;

    interface Binder extends UiBinder<Widget, SignUpView> {
    }

    @Inject
    SignUpView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        
        ProfilePictureForm form = new ProfilePictureForm(user, imageChooser);
        form.onModuleLoad();
        
        btnCreateUser.addClickHandler(clickEvent -> {
    
            if (user.getText().isEmpty()){
                MaterialToast.fireToast("The user field is empty, write down an username."); 
            }else if (pass.getText().isEmpty()){
                MaterialToast.fireToast("The password field is empty, write down a password."); 
            }else if (email.getText().isEmpty()){
               MaterialToast.fireToast("The email field is empty, write down an email."); 
            }else if (name.getText().isEmpty()){
                MaterialToast.fireToast("The name field is empty, write down a name."); 
            }else if(!(email.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$"))){
                MaterialToast.fireToast("The email format is not valid, input a valid email."); 
            }else{
                getUiHandlers().onSignUp(user.getText(), pass.getText(), email.getText(), name.getText(), form.getImagePath());
                pass.clear();
            }
        });
    }
}
