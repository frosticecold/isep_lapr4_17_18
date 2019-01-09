package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import eapli.framework.persistence.repositories.DeleteableRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Reminder;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.ReminderName;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence.ReminderRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rui Almeida<1160818>
 */
public class JpaReminderRepository extends NSheetsJpaRepositoryBase<Reminder, Long> implements ReminderRepository, DeleteableRepository<Reminder, Long> {

    JpaReminderRepository(PersistenceSettings settings) {
        super(settings);
    }

    /**
     * Given an username, finds the reminder's that the user owns
     * @param username - the user
     * @return an arraylist with all of the user's reminders
     */
    @Override
    public List<Reminder> findRemindersByUser(String username) {
        final Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        try{
            return match("e.user=:username", params);
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * Given a name, finds the reminder.
     * @param name - name of the reminder
     * @param user - name of the user
     * @return the reminder
     */
    public Reminder findReminderByName(String name, String user) {
        final Map<String, Object> params = new HashMap<>();
        ReminderName rname = new ReminderName(name);
        params.put("rname", rname);
        params.put("user", user);
        try{
            return matchOne("e.name=:rname AND e.user=:user", params);
        }catch(Exception ex){
            return null;
        }
    }
}
