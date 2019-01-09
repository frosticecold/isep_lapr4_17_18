/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.domain.Task;
import pt.isep.nsheets.server.lapr4.s2.core.n1040862.task.persistence.TaskRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaTxRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;

/**
 *
 * @author André Oliveira <1040862@isep.ipp.pt>
 * xD
 */
public class JpaTaskRepository extends JpaTxRepository<Task, Long> implements TaskRepository {

    JpaTaskRepository(PersistenceSettings settings) {
        super(settings.getPersistenceUnitName());
    }

    @Override
    public Boolean removeTask(Task task) {
        delete(task);
        return true;
    }
}
