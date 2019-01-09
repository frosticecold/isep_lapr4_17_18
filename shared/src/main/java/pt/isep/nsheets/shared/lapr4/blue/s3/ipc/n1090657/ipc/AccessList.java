/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isep.nsheets.shared.lapr4.blue.s3.ipc.n1090657.ipc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Entity
public class AccessList implements Iterable<Access>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Access> list = new ArrayList<>();

    public AccessList() {
    }

    @Override
    public Iterator<Access> iterator() {
        return list.iterator();
    }

    public void addUserAccess(String email, AccessType access) {
        Access ac = new Access(email, access);
        if (!list.contains(ac)) {
            list.add(ac);
        }
    }

    public void changeUserAccess(String email, AccessType access) {
        for (Access ac : list) {
            if (ac.isSameUser(email)) {
                ac.changeAccessType(access);
            }
        }
    }

    public void deleteUserAccess(String email) {
        Access toDelete = null;
        for (Access ac : list) {
            if (ac.isSameUser(email)) {
                toDelete = null;
                break;
            }
        }
        if (toDelete != null) {
            list.remove(toDelete);
        }
    }

    public Boolean hasAccessType(String email, AccessType access) {
        Boolean hasIt = Boolean.FALSE;
        for (Access ac : list) {
            if (ac.isSameUser(email)) {
                return ac.hasThisTypeOfAccess(access);
            }
        }
        return false;
    }

    public Boolean hasAnyTypeOfAccess(String email) {
        for (Access ac : list) {
            if (ac.isSameUser(email)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAllUsersWithAccess() {
        ArrayList<String> array = new ArrayList<>();
        for (Access ac : list) {
            array.add(ac.getEmail());
        }
        return array;
    }
    
    
    public void copyOtherAccessList(AccessList otherList){
        this.list.clear();
        this.list.addAll(otherList.list);
    }

}
