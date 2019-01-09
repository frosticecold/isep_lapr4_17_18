package pt.isep.nsheets.client.application.NoteMenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.ui.*;
import pt.isep.nsheets.client.CurrentUser;
import pt.isep.nsheets.client.application.ApplicationPresenter;
import pt.isep.nsheets.client.event.SetPageTitleEvent;
import pt.isep.nsheets.client.place.NameTokens;
import pt.isep.nsheets.shared.services.NoteDTO;
import pt.isep.nsheets.shared.services.NoteService;
import pt.isep.nsheets.shared.services.NoteServiceAsync;
import java.util.Date;
import java.util.List;

/**
 * @author Joao Rocha <1161838>
 */
public class NotePresenter extends Presenter<NotePresenter.MyView, NotePresenter.MyProxy> {

    private MyView view;

    interface MyView extends View {
        void setContents(List<NoteDTO> contents);

        MaterialIcon getSearchButton();

        MaterialTextBox getFilterTextBox();

        MaterialDatePicker getInitialDate();

        MaterialDatePicker getFinalDate();

        void addClickHandlerRefresh(ClickHandler ch);

        MaterialListBox getListBoxType();

    }

    @NameToken(NameTokens.note)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<NotePresenter>{

    }

    @Inject
    NotePresenter(EventBus eventBus, MyView view, MyProxy proxy){
        super(eventBus,view,proxy,ApplicationPresenter.SLOT_CONTENT);
        this.view = view;
        this.view.addClickHandlerRefresh(event ->{
            refreshViewUserNotes();
        });
    }

    public void refreshViewUserNotes(){
        NoteServiceAsync noteSvc = GWT.create(NoteService.class);

        AsyncCallback<List<NoteDTO>> callback = new AsyncCallback<List<NoteDTO>>(){

            @Override
            public void onFailure(Throwable throwable) {
                MaterialToast.fireToast("Something went wrong! " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<NoteDTO> noteDTOS) {
                view.getSearchButton().addClickHandler(event ->{
                    NoteServiceAsync noteSvc = GWT.create(NoteService.class);

                    AsyncCallback<List<NoteDTO>> callback = new AsyncCallback<List<NoteDTO>>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            MaterialToast.fireToast("Something went wrong! 2" + throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(List<NoteDTO> noteDTOS) {
                            view.setContents(noteDTOS);
                        }
                    };
                    String filter = view.getFilterTextBox().getText();
                    Date initialDate = view.getInitialDate().getValue();
                    Date finalDate = view.getFinalDate().getValue();
                    noteSvc.findUserFilteredNotesInIntervalTime(CurrentUser.username(),filter,initialDate,finalDate,view.getListBoxType().getValue(),callback);
                });
                view.setContents(noteDTOS);
            }
        };
        Date initial = new Date(0L);
        Date finalDate = new Date();
        noteSvc.findUserFilteredNotesInIntervalTime(CurrentUser.username(),"",initial,finalDate,view.getListBoxType().getValue(),callback);
    }

    @Override
    protected void onReset(){
        super.onReset();
    }

    @Override
    protected void onReveal(){
        super.onReveal();
        refreshViewUserNotes();
        SetPageTitleEvent.fire("Note Menu","Users Notes","","",this);
    }
}
