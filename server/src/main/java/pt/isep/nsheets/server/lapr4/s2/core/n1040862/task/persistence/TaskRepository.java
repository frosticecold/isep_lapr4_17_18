/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence;

import eapli.framework.persistence.repositories.Repository;
import java.util.List;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 */
public interface TaskRepository extends Repository<Task, Long> {

    Boolean removeTask(Task task);

}
