/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.lapr4.green.s1.ipc.n1160818.bootstrap;

import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.GetPropertiesService;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskBootstrapper implements Action {

    private Email email1 = new Email("email1");
    private Email email2 = new Email("email2");
    private Email email3 = new Email("email3");
    private List<Email> list = new ArrayList<>();

    @Override
    public boolean execute() {
        list.add(email1);
        list.add(email2);
        list.add(email3);

        registerTask("task1", "somedescription1", list, 1);
        registerTask("task2", "somedescription2", list, 2);
        registerTask("task3", "somedescription3", list, 3);
        registerTask("task4", "somedescription4", list, 4);
        return true;
    }

    private void registerTask(final String taskName, final String description, final List<Email> list, int priority) {

        try {
            PersistenceContext.setSettings(GetPropertiesService.getPersistenceSettings());
            TaskRepository task = PersistenceContext.repositories().tasks();

            Task newTask = new Task(taskName, description, list, priority);

           task.save(newTask);

        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(Bootstrapper.class.getSimpleName())
                    .info("Exception during bootstrapping: assuming existing record.");
        }
    }

}
