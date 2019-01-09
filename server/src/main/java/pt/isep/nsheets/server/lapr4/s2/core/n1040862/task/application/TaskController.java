/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.application;

import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import static pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.RoleType.USER;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.User;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Username;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.persistence.UserRepository;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceContext;
import pt.isep.nsheets.shared.services.TaskDTO;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public class TaskController implements Controller {

    public List<Task> getAllTasks(String username) {
        TaskRepository taskRepo = PersistenceContext.repositories().tasks();
        List<Task> taskList = (List) taskRepo.findAll();
        UserRepository userRepo = PersistenceContext.repositories().users();
        User user = userRepo.findUserByUsername(new Username(username));

        if (user.role() == USER) {
            List<Task> newList = new ArrayList<>();
            for (Task task : taskList) {
                for (Email email : task.getTaskContactList()) {
                    if (email.equals(user.getEmail())) {
                        newList.add(task);
                    }
                }
            }
            return newList;
        }
        return taskList;
    }

    public List<Task> getAllCompleteTasks(String username) {
        TaskRepository taskRepo = PersistenceContext.repositories().tasks();
        List<Task> taskList = (List) taskRepo.findAll();
        UserRepository userRepo = PersistenceContext.repositories().users();
        User user = userRepo.findUserByUsername(new Username(username));
        List<Task> userList = new ArrayList<>();
        List<Task> adminList = new ArrayList<>();

        if (user.role() == USER) {
            for (Task task : taskList) {
                for (Email email : task.getTaskContactList()) {
                    if (email.equals(user.getEmail())) {
                        if (task.getCompletionRate() == 100) {
                            userList.add(task);
                        }
                    }
                }
            }
            return userList;
        } else {
            for (Task task : taskList) {
                if (task.getCompletionRate() == 100) {
                    adminList.add(task);
                }
            }
            return adminList;
        }
    }

    public List<User> getAllUsers() {
        TaskService sv = new TaskService();
        return (List) sv.getAllUsers();
    }

    public void addNewTask(TaskDTO task) throws DataConcurrencyException, DataIntegrityViolationException {
        TaskService sc = new TaskService();
        Task t = new Task();
        sc.addNewTask(t.fromDTO(task));
    }

    public void editTask(TaskDTO task, String oldName) throws DataConcurrencyException, DataIntegrityViolationException {
        TaskService sc = new TaskService();
        TaskRepository repo = PersistenceContext.repositories().tasks();
        List<Task> taskList = (List) repo.findAll();

//        for (Task t : taskList) {
//            if(t.getTaskName().toString().equalsIgnoreCase(oldName)){
//                Task editedTask = new Task();
//                editedTask.fromEditedDTO(task, t.getTaskContactList());
//                editedTask.setId(t.getId());
//                sc.editTask(editedTask);
//                return;
//            }
//        }       
//        for (Task t : taskList) {
//            if (t.getTaskName().toString().equalsIgnoreCase(oldName)) {
//                sc.editTask(t.fromEditedDTO(task, t.getTaskContactList()));
//                return;
//            }
//        }
        for (Task t : taskList) {
            if (t.getTaskName().toString().equalsIgnoreCase(oldName)) {
                List<Email> mailList = t.getTaskContactList();
                Task newTask = t.fromEditedDTO(task, mailList);
                sc.removeTask(t);
                sc.addNewTask(newTask);
                return;
            }
        }

    }

    public Boolean removeTask(TaskDTO task) throws DataConcurrencyException, DataIntegrityViolationException {
        TaskService sc = new TaskService();
        TaskRepository repo = PersistenceContext.repositories().tasks();
        List<Task> taskList = (List) repo.findAll();

        for (Task t : taskList) {
            if (t.getTaskName().toString().equalsIgnoreCase(task.getName())) {
                sc.removeTask(t);
                return true;
            }
        }
        return false;
    }

    public List<String> getUserEmails() {
        Iterable<User> userList = getAllUsers();
        List<String> mailList = new ArrayList<>();

        for (User user : userList) {
            mailList.add(user.getEmail().toString());
        }

        return mailList;
    }
}
