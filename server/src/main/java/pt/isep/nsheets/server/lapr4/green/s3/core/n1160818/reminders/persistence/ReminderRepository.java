package pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.persistence;

import eapli.framework.persistence.repositories.DeleteableRepository;
import pt.isep.nsheets.server.lapr4.green.s3.core.n1160818.reminders.domain.Reminder;
import eapli.framework.persistence.repositories.Repository;

import java.util.List;

/**
 * @author Rui Almeida<1160818>
 */
public interface ReminderRepository extends Repository<Reminder,Long>, DeleteableRepository<Reminder, Long> {

    List<Reminder> findRemindersByUser(String username);
    Reminder findReminderByName(String name, String user);
}
