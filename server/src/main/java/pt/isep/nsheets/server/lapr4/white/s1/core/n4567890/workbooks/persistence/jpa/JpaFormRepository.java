/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.jpa;

import pt.isep.nsheets.server.lapr4.green.s2.lang.n1161838.forms.persistence.FormRepository;
import pt.isep.nsheets.server.lapr4.white.s1.core.n4567890.workbooks.persistence.PersistenceSettings;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1150344.core.Form;

/**
 *
 * @author Joao Rocha <1161838>
 */
public class JpaFormRepository extends NSheetsJpaRepositoryBase<Form, Long> implements FormRepository{
    
    JpaFormRepository(PersistenceSettings settings){
        super(settings);
    }
}
