/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Entity
public class Access implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private AccessType accessType;

    //For ORM purposes
    protected Access() {
        email = "there is no email";
        accessType = AccessType.VIEW_ONLY;
    }

    public Access(final String email, AccessType accessType) {
        this.email = email;
        this.accessType = accessType;
    }

    public String getEmail() {
        return email;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public Boolean hasThisTypeOfAccess(AccessType accessToCheck) {
        Boolean ret = accessType == accessToCheck;
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public void changeAccessType(final AccessType access) {
        this.accessType = access;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Access other = (Access) obj;
        return this.email.equalsIgnoreCase(other.email) && this.accessType == other.accessType;
    }

    public boolean isSameUser(final String email) {
        return this.email.equalsIgnoreCase(email);
    }

}
