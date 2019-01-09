package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.blue.s2.core.n1161386.notes.domain.NoteType;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.domain.Note;
import pt.isep.nsheets.server.lapr4.red.s1.core.n1161391.notes.persistence.NoteRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.NoteDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Saves hard-coded notes to the database
 *
 * @author Joao Rocha <1161838>
 */
public class NoteBootstrapper implements Action {

    @Override
    public boolean execute(){
        registerNote("note1","description1", "TEXT");
        registerNote("note2","description2", "TEXT");
        registerNote("note3","description3", "LIST");
        return true;
    }

    /**
     * Method that regists notes on the system
     * It is only making notes for the user sa
     * @param name
     * @param description
     */
    private void registerNote(final String name,final String description,String type){

        try{
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            NoteRepository notes = PersistenceContext.repositories().notes();
            UserRepository users = PersistenceContext.repositories().users();

            Username dummyusername = new Username("sa");
            User dummy = users.findUserByUsername(dummyusername);
            Date date = new Date();
            Note note = new Note(name,description,date,dummy,type,new LinkedList<NoteDTO>());
            notes.save(note);
        }catch(DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }
    }
}
