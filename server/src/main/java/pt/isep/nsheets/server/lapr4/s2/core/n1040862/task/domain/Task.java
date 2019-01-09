/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain;

import eapli.framework.domain.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import pt.isep.nsheets.server.lapr4.green.s1.ipc.n1160818.userAuthentication.domain.Email;
import pt.isep.nsheets.shared.services.TaskDTO;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
@Entity
public class Task implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private TaskName taskName;

    private String description;

    @ElementCollection
    private List<Email> taskContactList = new ArrayList<Email>();

    private int priority;
    private int completionRate;

    /**
     * Contructor for ORM
     */
    public Task() {
    }

    public Long getId() {
        return id;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public List<Email> getTaskContactList() {
        return taskContactList;
    }

    public int getPriority() {
        return priority;
    }

    public int getCompletionRate() {
        return completionRate;
    }

    // USES THE CONSTRUCTOR FROM CREATING
    public Task(String name, String description, List<Email> taskContactList, int priority) {

        TaskName temp = new TaskName(name);

        if (description == null || priority < 1 || priority > 5 || taskContactList == null) {
            throw new IllegalArgumentException();
        }
        this.taskName = temp;
        this.description = description;
        this.taskContactList = taskContactList;
        this.priority = priority;
        this.completionRate = 0;
    }

    // USES THE CONSTRUCTOR FROM EDITING
    public Task(String name, String description, List<Email> taskContactList, int priority, int completionRate) {

        TaskName temp = new TaskName(name);

        if (description == null || priority < 1 || priority > 5) {
            throw new IllegalArgumentException();
        }
        this.taskName = temp;
        this.description = description;
        this.taskContactList = taskContactList;
        this.priority = priority;
        this.completionRate = completionRate;
    }

    @Override
    public boolean sameAs(Object other
    ) {
        if (!(other instanceof Task)) {
            return false;
        }
        final Task task = (Task) other;
        if (this == task) {
            return true;
        }
        return (this.taskContactList.equals(task.taskContactList));
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
    @Override
    public boolean is(Long id) {
        return Long.compare(this.id, id) == 0;
    }

    @Override
    public Long id() {
        return this.id;
    }

    public Task fromDTO(TaskDTO dto) {
        final List<Email> contactList = new ArrayList<>();
        final List<String> stringList = dto.getList();

        for (String str : stringList) {
            contactList.add(new Email(str));
        }

        // USES THE CONSTRUCTOR FROM CREATING
        return new Task(dto.getName(), dto.getDescription(), contactList, Integer.parseInt(dto.getPriority()));
    }

    public Task fromEditedDTO(TaskDTO dto, List<Email> oldList) {
        // USES THE CONSTRUCTOR FROM EDITING
        return new Task(dto.getName(), dto.getDescription(), oldList, Integer.parseInt(dto.getPriority()), Integer.parseInt(dto.getCompletion()));
    }

    public TaskDTO toDTO() {
        TaskName task = this.taskName;
        String description = this.description;
        List<String> stringList = new ArrayList<>();
        List<Email> contactList = this.taskContactList;

        for (Email mail : contactList) {
            stringList.add(mail.toString());
        }

        int priority = this.priority;
        int completionRate = this.completionRate;

        // USES CONSTRUCTOR TO CONVERT FROM REPO 
        return new TaskDTO(task.toString(), description, stringList, Integer.toString(priority), Integer.toString(completionRate));
    }

}
