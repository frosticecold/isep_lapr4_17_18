/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.application;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskService {

    public Iterable<Task> getAllTasks(String username) {
        final TaskRepository repo = PersistenceContext.repositories().tasks();
        return repo.findAll();
    }
    
    public Iterable<Task> getAllCompleteTasks(String username) {
        final TaskRepository repo = PersistenceContext.repositories().tasks();
        return repo.findAll();
    }

    public Iterable<User> getAllUsers() {
        final UserRepository repo = PersistenceContext.repositories().users();
        return repo.findAll();
    }

    public void addNewTask(Task task) throws DataConcurrencyException, DataIntegrityViolationException {
        final TaskRepository repo = PersistenceContext.repositories().tasks();
        repo.save(task);
    }

    public void editTask(Task task) throws DataConcurrencyException, DataIntegrityViolationException {
        final TaskRepository repo = PersistenceContext.repositories().tasks();
        repo.save(task);
    }

    public Boolean removeTask(Task task) throws DataConcurrencyException, DataIntegrityViolationException {
        final TaskRepository repo = PersistenceContext.repositories().tasks();
        if (repo.removeTask(task)) {
            return true;
        }
        return false;
    }

}
