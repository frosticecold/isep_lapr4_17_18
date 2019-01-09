/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.s2.lang.n1160911.GlobalVariable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import pt.isep.nsheets.shared.core.Value;

/**
 *
 * @author Rafael Teixeira <1160911@isep.ipp.pt>
 */
//@Entity
public class GlobalVariable {
    
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
    
    /**
     * The unique version identifier used for serialization
     */
    private static final long serialVersionUID = -897654289L;
    
    
   @Column
   private String name;
   
   @OneToOne
   private Value value;

    public GlobalVariable() {
    }

    public GlobalVariable(String name) {
        this.name = name;
        this.value = null;
    }

    public GlobalVariable(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
